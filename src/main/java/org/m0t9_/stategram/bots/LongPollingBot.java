package org.m0t9_.stategram.bots;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.GetUpdates;
import okhttp3.OkHttpClient;
import org.m0t9_.stategram.handlers.Action;
import org.m0t9_.stategram.handlers.BotResponse;
import org.m0t9_.stategram.handlers.Handler;
import org.m0t9_.stategram.storages.RuntimeStateStorage;
import org.m0t9_.stategram.storages.StateStorage;
import org.m0t9_.stategram.tools.UpdateTools;

import java.util.*;

/**
 * Implementation of Bot abstraction as Long Polling bot.
 *
 * @param <T> type of the dialogue state.
 */
public class LongPollingBot<T> extends Bot<T> {
    private final Set<Integer> missedUpdates = new HashSet<>();
    private boolean skipUpdates = false;
    private OkHttpClient client = null;
    private TelegramBot bot = null;

    public LongPollingBot(String token, StateStorage<T> stateStorage) {
        super(token, stateStorage);
    }

    public LongPollingBot(String token) {
        super(token, new RuntimeStateStorage<>());
    }

    public void skipUpdates(boolean flag) {
        skipUpdates = flag;
    }

    private void preloadMissedUpdates() {
        missedUpdates.clear();
        if (!skipUpdates) {
            return;
        }
        missedUpdates.addAll(
                bot.execute(new GetUpdates())
                        .updates()
                        .stream()
                        .map(Update::updateId).toList()
        );
    }

    private boolean isMissed(Update update) {
        var id = update.updateId();
        if (missedUpdates.contains(id)) {
            missedUpdates.remove(id);
            return true;
        }
        return false;
    }

    public void listen() {
        client = new OkHttpClient();
        bot = new TelegramBot.Builder(token).okHttpClient(client).build();
        preloadMissedUpdates();
        bot.setUpdatesListener(this::handleUpdates);
    }

    public void stop() {
        bot.removeGetUpdatesListener();
        bot.shutdown();
        client.dispatcher().executorService().shutdown();
        client.connectionPool().evictAll();
    }

    private int handleUpdates(List<Update> updates) {
        updates.forEach(this::handleUpdate);
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

    private void handleUpdate(Update update) {
        if (!UpdateTools.isUserUpdate(update) || isMissed(update)) {
            return;
        }

        var userId = UpdateTools.extractSender(update);

        if (!stateStorage.hasState(userId)) {
            handlePrimarily(userId, update);
            return;
        }

        if (!handleShared(userId, update)) {
            handleOnState(userId, update);
        }
    }

    private void handleOnState(Long userId, Update update) {
        var state = stateStorage.getState(userId);
        stateHandlers.putIfAbsent(state, new LinkedList<>());
        isHandled(userId, update, stateHandlers.get(state));
    }

    private boolean handleShared(Long userId, Update update) {
        return isHandled(userId, update, sharedHandlers);
    }

    private void handlePrimarily(Long userId, Update update) {
        isHandled(userId, update, primaryHandlers);
    }

    private void respond(Long userId, BotResponse<T> response) {
        if (response.getStateAction() == Action.REMOVE) {
            stateStorage.removeState(userId);
        } else if (response.getStateAction() == Action.UPDATE) {
            stateStorage.setState(userId, response.getUpdatedState());
        }
        Arrays.stream(response.getRequests()).forEach(bot::execute);
    }

    private boolean isHandled(Long userId, Update update, List<Handler<T>> handlers) {
        for (Handler<T> handler : handlers) {
            if (handler.isFullyFiltered(update)) {
                respond(userId, handler.handleCarefully(update));
                return true;
            }
        }
        return false;
    }
}
