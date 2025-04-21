package ru.example.mvvm.eventbus;

public record WindowResizedEvent(int width, int height) implements Event {
}
