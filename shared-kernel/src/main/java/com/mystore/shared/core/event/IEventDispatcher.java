package com.mystore.shared.core.event;

public interface IEventDispatcher {

    void sendEvent(EventBase<?> eventToSend);

}
