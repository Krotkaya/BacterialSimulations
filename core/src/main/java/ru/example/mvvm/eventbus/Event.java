package ru.example.mvvm.eventbus;

public class Event {
    private final String type;
    private final Object data;

    public Event(String type, Object data) {
        this.type = type;
        this.data = data;
    }

    // Добавляем геттеры
    public String getType() {
        return type;
    }

    public Object getData() {
        return data;
    }
}
