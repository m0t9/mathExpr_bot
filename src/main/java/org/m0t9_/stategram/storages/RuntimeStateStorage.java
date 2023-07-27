package org.m0t9_.stategram.storages;

import java.util.HashMap;
import java.util.Map;

public class RuntimeStateStorage<T> implements StateStorage<T> {
    private final Map<Long, T> userState = new HashMap<>();

    public boolean hasState(Long userId) {
        return userState.containsKey(userId);
    }

    public void setState(Long userId, T state) {
        userState.put(userId, state);
    }

    public T getState(Long userId) {
        return userState.get(userId);
    }

    public void removeState(Long userId) {
        userState.remove(userId);
    }
}
