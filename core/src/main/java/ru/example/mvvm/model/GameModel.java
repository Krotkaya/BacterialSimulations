package ru.example.mvvm.model;

import com.badlogic.gdx.math.Vector2;
import ru.example.mvvm.model.entities.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class GameModel {
    private final List<Entity> entities = new ArrayList<>();
    private final AtomicInteger idCounter = new AtomicInteger(1);
    private ModelContext context;
    private Vector2 cellSize = new Vector2(50f, 50f);
    private static final int GRID_WIDTH = 10; // Ширина сетки в клетках
    private static final int GRID_HEIGHT = 10;

    public GameModel() {
        this.context = new ModelContext(0, 0, this);
    }

    public void setGameZoneSize(float width, float height) {
        this.context = new ModelContext(width, height, this);
        this.cellSize.set(width / GRID_WIDTH, height / GRID_HEIGHT);
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

    boolean isPositionAvailable(Vector2 position) {

        if (position.x < 0 || position.x >= GRID_WIDTH ||
            position.y < 0 || position.y >= GRID_HEIGHT) {
            return false;
        }
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
