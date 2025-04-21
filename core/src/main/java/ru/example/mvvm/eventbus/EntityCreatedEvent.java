package ru.example.mvvm.eventbus;

import ru.example.mvvm.model.Entity;

public record EntityCreatedEvent(Entity entity) implements Event {
}
