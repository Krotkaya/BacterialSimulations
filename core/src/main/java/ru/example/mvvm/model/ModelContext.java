// ModelContext.java
package ru.example.mvvm.model;

import com.badlogic.gdx.math.Vector2;
import ru.example.mvvm.model.entities.Bacteria;
import ru.example.mvvm.model.entities.Food;
import ru.example.mvvm.model.entities.MoveDirection;

import java.util.List;

public class ModelContext {
    private final float gameZoneWidth;
    private final float gameZoneHeight;
    private final GameModel gameModel;

    public ModelContext(float gameZoneWidth, float gameZoneHeight, GameModel gameModel) {
        this.gameZoneWidth = gameZoneWidth;
        this.gameZoneHeight = gameZoneHeight;
        this.gameModel = gameModel;
    }

    public float getGameZoneWidth() {
        return gameZoneWidth;
    }

    public float getGameZoneHeight() {
        return gameZoneHeight;
    }

    public void removeEntity(Entity entity) {
        gameModel.removeEntity(entity);
    }

    public Food getFoodAt(Vector2 position) {
        return gameModel.getFoodAt(position);
    }

    public boolean isPositionAvailable(Vector2 position) {
        return gameModel.isPositionAvailable(position);
    }

    public List<Entity> getEntities() {
        return gameModel.getEntities();
    }

    public void moveBacteria(Bacteria bacteria, MoveDirection direction) {
        Vector2 newPosition = direction.getOffset().cpy().add(bacteria.getPosition());
        if (isPositionAvailable(newPosition)) {
            bacteria.setPosition(newPosition);
        }
    }
}
