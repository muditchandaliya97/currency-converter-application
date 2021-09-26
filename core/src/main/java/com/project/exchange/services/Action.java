package com.project.exchange.services;

/**
 * @param <T> A class to hold one elemental business logic/ functional logic to produce/ create an atomic response
 */
public interface Action<T> {
    /**
     * @return an object of clazz<T>
     * method to invoke to execute the action class
     */
    T invoke();
}
