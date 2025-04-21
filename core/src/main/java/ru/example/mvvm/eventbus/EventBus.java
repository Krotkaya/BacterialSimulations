// EventBus.java
package ru.example.mvvm.eventbus;

public interface EventBus {
    <T extends Event> void subscribe(Class<T> eventType, EventListener<T> listener);
    <T extends Event> void unsubscribe(Class<T> eventType, EventListener<T> listener);
    void publish(Event event);
}
