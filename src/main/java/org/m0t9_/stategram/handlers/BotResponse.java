package org.m0t9_.stategram.handlers;

import com.pengrad.telegrambot.request.BaseRequest;

public class BotResponse<T> {
    // requests from bot to react on user update
    private final BaseRequest[] requests;
    // how to update user's state of the dialogue
    private final Action action;
    // new state of dialogue (if exists)
    private final T updatedState;

    /**
     * Constructor for response with REMOVE or PRESERVE actions.
     *
     * @param action   state action
     * @param requests bot respond
     */
    public BotResponse(Action action, BaseRequest... requests) {
        this.action = action;
        this.requests = requests;
        updatedState = null;
    }

    /**
     * Constructor for response with UPDATE action.
     *
     * @param action       state action.
     * @param updatedState state to set user to
     * @param requests     bot responds
     */
    public BotResponse(Action action, T updatedState, BaseRequest... requests) {
        this.action = action;
        this.requests = requests;
        this.updatedState = updatedState;
    }

    public BaseRequest[] getRequests() {
        return requests;
    }

    public Action getStateAction() {
        return action;
    }

    public T getUpdatedState() {
        return updatedState;
    }
}
