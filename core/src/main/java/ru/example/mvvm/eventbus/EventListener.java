package ru.example.mvvm.eventbus;

public interface EventListener<T extends Event> {
    void handleEvent(T event);
}
