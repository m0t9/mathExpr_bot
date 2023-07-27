package org.m0t9_.stategram.filters.checkers;

import com.pengrad.telegrambot.model.Update;
import org.m0t9_.stategram.filters.OnCallback;
import org.m0t9_.stategram.handlers.Handler;

public class OnCallbackChecker<T> implements IChecker<T> {
    public boolean isFiltered(Update update, Handler<T> handler) {
        var pattern = handler.getClass().getAnnotation(OnCallback.class).pattern();
        return update.callbackQuery() != null && update.callbackQuery().data() != null
                && update.callbackQuery().data().matches(pattern);
    }

    public boolean isEnabled(Handler<T> handler) {
        var onMessage = handler.getClass().getAnnotation(OnCallback.class);
        return onMessage != null;
    }
}
