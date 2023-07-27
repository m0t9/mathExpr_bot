package org.m0t9_.stategram.filters.checkers;

import com.pengrad.telegrambot.model.Update;
import org.m0t9_.stategram.filters.OnInlineQuery;
import org.m0t9_.stategram.handlers.Handler;

public class OnInlineQueryChecker<T> implements IChecker<T> {
    @Override
    public boolean isFiltered(Update update, Handler<T> handler) {
        var pattern = handler.getClass().getAnnotation(OnInlineQuery.class).pattern();
        return update.inlineQuery() != null &&
                update.inlineQuery().query().matches(pattern);
    }

    @Override
    public boolean isEnabled(Handler<T> handler) {
        var onInlineQuery = handler.getClass().getAnnotation(OnInlineQuery.class);
        return onInlineQuery != null;
    }
}
