package ru.example.mvvm.model;

import com.badlogic.gdx.math.Vector2;
import ru.example.mvvm.model.entities.*;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class GameModel {
    private final List<Entity> entities = new ArrayList<>();
    private final AtomicInteger idCounter = new AtomicInteger(1);
    private ModelContext context;

    public GameModel() {
        this.context = new ModelContext(0, 0, this);
    }

    public void setGameZoneSize(float width, float height) {
        this.context = new ModelContext(width, height, this);
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    public void removeEntity(Entity entity) {
        entities.remove(entity);
    }

    public void start() {
    }

    public void update() {
        new ArrayList<>(entities).forEach(e -> e.update(context));
    }

    public List<Entity> getEntities() {
        return new ArrayList<>(entities);
    }

    public int getNextId() {
        return idCounter.getAndIncrement();
    }


    Food getFoodAt(Vector2 position) {
        return (Food) entities.stream()
            .filter(e -> e instanceof Food)
            .filter(e -> ((Food) e).getPosition().equals(position))
            .findFirst()
            .orElse(null);
    }
//логику уносить в ModelContext, а тут данные и состояние
    // проверить int векторы для сетки как вариант
    boolean isPositionAvailable(Vector2 position) {
        return entities.stream()
            .filter(e -> e instanceof Bacteria || e instanceof Food)
            .noneMatch(e -> {
                if (e instanceof Bacteria) {
                    return ((Bacteria) e).getPosition().equals(position);
                } else if (e instanceof Food) {
                    return ((Food) e).getPosition().equals(position);
                }
                return false;
            });
    }
}
