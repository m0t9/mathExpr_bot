package org.m0t9_.stategram.filters.checkers;

import com.pengrad.telegrambot.model.Update;
import org.m0t9_.stategram.filters.OnMessage;
import org.m0t9_.stategram.handlers.Handler;

public class OnMessageChecker<T> implements IChecker<T> {
    public boolean isFiltered(Update update, Handler<T> handler) {
        var pattern = handler.getClass().getAnnotation(OnMessage.class).pattern();
        return update.message() != null && update.message().text() != null
                && update.message().text().matches(pattern);
    }

    public boolean isEnabled(Handler<T> handler) {
        var onMessage = handler.getClass().getAnnotation(OnMessage.class);
        return onMessage != null;
    }
}
