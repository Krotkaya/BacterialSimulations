
package ru.example.mvvm.model.entities;

import com.badlogic.gdx.math.Vector2;
import ru.example.mvvm.model.ModelContext;

public class Food extends BaseEntity {
    private final Vector2 position;
    private final float nutritionValue;
    private Vector2 cellSize;
    private static final float MIN_SIZE_RATIO = 0.3f; // Минимум 30% от клетки

    public Food(int id, Vector2 position, float nutritionValue, Vector2 cellSize) {
        super(id);
        this.position = position;
        this.nutritionValue = nutritionValue;
        this.cellSize = cellSize;
    }

    @Override
    public void update(ModelContext context) {}

    public Vector2 getPosition() {
        return position;
    }

    public float getNutritionValue() {
        return nutritionValue;
    }
    public Vector2 getCellSize() {
        return cellSize;
    }
}
