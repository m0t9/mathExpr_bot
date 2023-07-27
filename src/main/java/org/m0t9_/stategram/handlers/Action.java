package org.m0t9_.stategram.handlers;

/**
 * Enum describing actions to update user's state in dialogue.
 */
public enum Action {
    // do nothing
    PRESERVE,
    // set new state
    UPDATE,
    // remove current state
    REMOVE
}
