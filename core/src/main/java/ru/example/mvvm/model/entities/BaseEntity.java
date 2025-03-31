package ru.example.mvvm.model.entities;

import ru.example.mvvm.model.Entity;

/**
 * Абстрактный класс, содержащий общую логику всех игровых сущностей
 */
public abstract class BaseEntity implements Entity {
    private final int id;

    public BaseEntity(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }
}
