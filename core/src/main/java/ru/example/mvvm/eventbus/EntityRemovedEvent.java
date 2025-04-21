package ru.example.mvvm.eventbus;

import ru.example.mvvm.model.Entity;

public record EntityRemovedEvent(Entity entity) implements Event {
}
