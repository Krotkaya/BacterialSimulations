// SimpleEventBus.java
package ru.example.mvvm.eventbus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimpleEventBus implements EventBus {
    private final Map<String, List<EventListener>> listeners = new HashMap<>();

    @Override
    public void subscribe(String eventType, EventListener listener) {
        listeners.computeIfAbsent(eventType, k -> new ArrayList<>()).add(listener);
    }

    @Override
    public void unsubscribe(String eventType, EventListener listener) {
        List<EventListener> eventListeners = listeners.get(eventType);
        if (eventListeners != null) {
            eventListeners.remove(listener);
        }
    }

    @Override
    public void publish(Event event) {//беру ивент достаю класс и все остальное также как было
        List<EventListener> eventListeners = listeners.get(event.getType());
        if (eventListeners != null) {
            for (EventListener listener : eventListeners) {
                listener.handleEvent(event);
            }
        }
    }
}
