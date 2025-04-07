// EventBus.java
package ru.example.mvvm.eventbus;

public interface EventBus {
    void subscribe(String eventType, EventListener listener);
    void unsubscribe(String eventType, EventListener listener);
    void publish(Event event);
}
