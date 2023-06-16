package com.mystore.shared.core.event;

public abstract class EventBase<T> {

    private final String eventName = getClass().getSimpleName();
    private final T payload;

    protected EventBase(T payload) {
        this.payload = payload;
    }

    public String getEventName() {
        return eventName;
    }

    public T getPayload() {
        return payload;
    }

}
