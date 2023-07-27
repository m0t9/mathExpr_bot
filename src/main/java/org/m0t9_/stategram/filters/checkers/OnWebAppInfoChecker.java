package org.m0t9_.stategram.filters.checkers;

import com.pengrad.telegrambot.model.Update;
import org.m0t9_.stategram.filters.OnWebAppInfo;
import org.m0t9_.stategram.handlers.Handler;

public class OnWebAppInfoChecker<T> implements IChecker<T> {
    public boolean isFiltered(Update update, Handler<T> handler) {
        var pattern = handler.getClass().getAnnotation(OnWebAppInfo.class).pattern();
        return update.message() != null && update.message().webAppData() != null &&
                update.message().webAppData().data().matches(pattern);
    }

    public boolean isEnabled(Handler<T> handler) {
        var onWebApp = handler.getClass().getAnnotation(OnWebAppInfo.class);
        return onWebApp != null;
    }
}
