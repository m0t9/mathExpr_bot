package org.m0t9_.stategram.handlers;

import com.pengrad.telegrambot.model.Update;
import org.m0t9_.stategram.filters.checkers.*;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Incoming Telegram updates handler.
 *
 * @param <T> type of the state of the dialog.
 */
public abstract class Handler<T> {
    private final List<IChecker<T>> recognizers = new LinkedList<>();

    /**
     * Preload preliminary checks to handler.
     */
    protected Handler() {
        recognizers.add(new OnMessageChecker<>());
        recognizers.add(new OnCallbackChecker<>());
        recognizers.add(new OnWebAppInfoChecker<>());
        recognizers.add(new OnInlineQueryChecker<>());
    }

    /**
     * Handle update as usually.
     *
     * @param update incoming update.
     * @return bot's response on the update.
     */
    public abstract BotResponse<T> handle(Update update) throws Exception;

    /**
     * Handle update in case of exception in {@link #handle(Update) handle} method.
     * By default, it does nothing.
     *
     * @param update    update to respond.
     * @param exception caught exception.
     * @return bot's response on the update with raised exception.
     */
    public BotResponse<T> handleException(Update update, Exception exception) {
        return new BotResponse<>(Action.PRESERVE);
    }

    /**
     * Handling flow of update.
     *
     * @param update incoming update.
     * @return bot's response on update.
     */
    public final BotResponse<T> handleCarefully(Update update) {
        try {
            return handle(update);
        } catch (Exception exception) {
            return handleException(update, exception);
        }
    }

    /**
     * User defined update filter.
     *
     * @param update incoming update.
     * @return true if the handler can respond on update,
     * false otherwise.
     */
    public boolean isUserFiltered(Update update) {
        return true;
    }

    /**
     * Check whether update passes default filters.
     *
     * @param update incoming update.
     * @return true if update passes filters, false otherwise.
     */
    public final boolean isFiltered(Update update) {
        AtomicBoolean isRecognized = new AtomicBoolean(true);
        recognizers.forEach(recognizer -> {
            if (recognizer.isEnabled(this)) {
                isRecognized.set(
                        isRecognized.get() && recognizer.isFiltered(update, this));
            }
        });
        return isRecognized.get();
    }

    /**
     * Check whether update passes default and user-defined filters.
     *
     * @param update incoming update to check.
     * @return true if update passes all filters, false - otherwise.
     */
    public final boolean isFullyFiltered(Update update) {
        return isFiltered(update) && isUserFiltered(update);
    }
}
