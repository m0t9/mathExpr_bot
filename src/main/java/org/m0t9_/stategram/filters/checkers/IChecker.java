package org.m0t9_.stategram.filters.checkers;

import com.pengrad.telegrambot.model.Update;
import org.m0t9_.stategram.handlers.Handler;

/**
 * Interface for annotation update filters running.
 *
 * @param <T> type of dialogue state.
 */
public interface IChecker<T> {
    /**
     * Check whether given update passes filter attached to provided handler.
     *
     * @param update  incoming update.
     * @param handler handler with enabled filter.
     * @return true if update passes filter of provided handler, false - otherwise.
     */
    boolean isFiltered(Update update, Handler<T> handler);

    /**
     * Check whether attached filter is enabled.
     *
     * @param handler handler to check.
     * @return true if filter is enabled, false - otherwise.
     */
    boolean isEnabled(Handler<T> handler);
}