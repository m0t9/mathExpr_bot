package org.m0t9_.stategram.storages;

/**
 * Interface for determination of dialogue state of user.
 *
 * @param <T> type of dialogue state.
 */
public interface StateStorage<T> {
    /**
     * Check whether user with given ID has dialogue state.
     *
     * @param userId provided user ID.
     * @return true if user has a dialogue state, false - otherwise.
     */
    boolean hasState(Long userId);

    /**
     * Set dialogue state to user with given ID.
     *
     * @param userId ID of user to set new state.
     * @param state  new dialogue state.
     */
    void setState(Long userId, T state);

    /**
     * Extract dialogue state of user with given ID.
     *
     * @param userId ID of user to extract dialogue state.
     * @return dialogue state of user with provided ID.
     */
    T getState(Long userId);

    /**
     * Remove state of user with provided ID.
     *
     * @param userId ID of user to delete state.
     */
    void removeState(Long userId);
}
