package com.mystore.wishlist.infraestructure.event;

import com.mystore.shared.core.event.EventBase;
import com.mystore.shared.core.event.IEventDispatcher;

public class EventDispatcherNoOpImpl implements IEventDispatcher {

    @Override
    public void sendEvent(EventBase<?> eventToSend) {
        // nothing to do
    }

}
