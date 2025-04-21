package ru.example.mvvm.eventbus;

public interface EventListener {//получает generic параметр
    //public interface EventListener<T extends Event>
    //   void handleEvent(T event);
    void handleEvent(Event event);
}
