package ru.example.mvvm.model;

import com.badlogic.gdx.math.Vector2;
import ru.example.mvvm.model.entities.Food;

import java.util.List;
//предоставляет леегальные методы изменения модели
public class ModelContext {
    private final float gameZoneWidth;
    private final float gameZoneHeight;
    private final GameModel gameModel;
//перенсти бактерию и принимать enum
    //
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
}
