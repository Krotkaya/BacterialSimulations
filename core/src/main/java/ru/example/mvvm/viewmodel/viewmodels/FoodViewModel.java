package ru.example.mvvm.viewmodel.viewmodels;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import ru.example.mvvm.model.entities.Food;
import ru.example.mvvm.viewmodel.SpecifiedViewModel;

public class FoodViewModel extends SpecifiedViewModel<Food> {
    private Vector2 position;
    private float size;

    @Override
    public Class<Food> getTargetEntityClass() {
        return Food.class;
    }

    @Override
    protected void updateCasted(Food entity) {
        this.position = entity.getPosition();
        this.size = entity.getNutritionValue() / 2f;
    }
    @Override
    public void drawShape(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(Color.BLUE);
     shapeRenderer.rect(position.x * 50, position.y * 50, 50, 50);
    }

//    @Override
//    public void drawShape(ShapeRenderer shapeRenderer) {
//        shapeRenderer.setColor(Color.GREEN);
//        shapeRenderer.circle(position.x * 50 + 25, position.y * 50 + 25, size);
//    }
}
