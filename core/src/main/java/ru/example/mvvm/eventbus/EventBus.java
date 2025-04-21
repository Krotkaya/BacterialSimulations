// EventBus.java
package ru.example.mvvm.eventbus;

public interface EventBus {
    void subscribe(String eventType, EventListener listener);
    void unsubscribe(String eventType, EventListener listener);
    //    String eventType -> Class<? extend Event>
    //String eventType будут не строковые а специального класса
    //record более простой способ определить какие-то данные
    void publish(Event event);
}
