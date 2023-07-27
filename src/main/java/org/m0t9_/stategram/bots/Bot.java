package org.m0t9_.stategram.bots;

import org.m0t9_.stategram.handlers.Handler;
import org.m0t9_.stategram.storages.StateStorage;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Overall bot abstraction description.
 *
 * @param <T> type of the dialogue state.
 */
public abstract class Bot<T> {
    // Token of bot
    protected final String token;
    // Storage of users' states of dialogue.
    protected final StateStorage<T> stateStorage;
    // List of handlers available on each dialogue state.
    protected final List<Handler<T>> sharedHandlers = new LinkedList<>();
    // List of handlers available only for users without any dialogue state.
    protected final List<Handler<T>> primaryHandlers = new LinkedList<>();
    // Mapping for dialogue states and handlers attached to them.
    protected final Map<T, List<Handler<T>>> stateHandlers = new HashMap<>();

    protected Bot(String token, StateStorage<T> stateStorage) {
        this.stateStorage = stateStorage;
        this.token = token;
    }

    /**
     * Add new primary handler.
     *
     * @param handler new handler.
     */
    public void addPrimaryHandler(Handler<T> handler) {
        primaryHandlers.add(handler);
    }

    /**
     * Add new shared handler.
     *
     * @param handler new handler.
     */
    public void addSharedHandler(Handler<T> handler) {
        sharedHandlers.add(handler);
    }

    /**
     * Add new state handler.
     *
     * @param state   state attach handler to.
     * @param handler new handler.
     */
    public void attachHandler(T state, Handler<T> handler) {
        stateHandlers.putIfAbsent(state, new LinkedList<>());
        stateHandlers.get(state).add(handler);
    }

    /**
     * Opportunity to skip missed updates on bot startup.
     *
     * @param flag if true - enables missed updates skipping on bot startup.
     *             Disabled by default.
     */
    public abstract void skipUpdates(boolean flag);

    /**
     * Bot starts listening & answering on incoming updates.
     */
    public abstract void listen();

    /**
     * Interrupt bot listening.
     */
    public abstract void stop();
}
