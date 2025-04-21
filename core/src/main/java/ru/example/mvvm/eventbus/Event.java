package ru.example.mvvm.eventbus;

public class Event {
    private final String type;
    private final Object data;

    public Event(String type, Object data) {
        this.type = type;
        this.data = data;
    }
    public String getType() {
        return type;
    }

    public Object getData() {
        return data;
    }
}

//тип события не строка, а на класс event, event не единственный класс, а какая-то иерархия событий
//Например событие изменения окна, абстрактный класс event без каких-либо данных, и наследуемся от него создаем класс событие изменения окна (два внутренних поля размеры окна по высоте и ширине, ключевое слово вместо класса record в джаве - это объект у которого все поля константы
