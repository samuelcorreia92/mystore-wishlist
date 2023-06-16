package com.mystore.wishlist.infraestructure.event;

import com.mystore.shared.core.event.EventBase;
import com.mystore.shared.core.event.IEventDispatcher;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * RabbitMQ implementation of {@link IEventDispatcher}
 * WIP: work in progress
 */
@RequiredArgsConstructor
public class EventDispatcherRabbitMQImpl implements IEventDispatcher {

    private final RabbitTemplate rabbitTemplate;

    @Override
    public void sendEvent(EventBase<?> eventToSend) {
        this.rabbitTemplate.convertAndSend("wishlist-exchange", getEventName(eventToSend), eventToSend.getPayload(), message -> {
            message.getMessageProperties().setAppId("wishlist-app");
            return message;
        });
    }

    private static String getEventName(EventBase<?> eventToSend) {
        return Arrays.stream(eventToSend.getEventName().split("(?=[A-Z])"))
                .map(String::toLowerCase)
                .collect(Collectors.joining("."));
    }

}
