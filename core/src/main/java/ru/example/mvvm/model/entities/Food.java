// Food.java
package ru.example.mvvm.model.entities;

import com.badlogic.gdx.math.Vector2;
import ru.example.mvvm.model.ModelContext;

public class Food extends BaseEntity {
    private final Vector2 position;
    private final float nutritionValue;

    public Food(int id, Vector2 position, float nutritionValue) {
        super(id);
        this.position = position;
        this.nutritionValue = nutritionValue;
    }

    @Override
    public void update(ModelContext context) {}

    public Vector2 getPosition() {
        return position;
    }

    public float getNutritionValue() {
        return nutritionValue;
    }
}
