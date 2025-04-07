package ru.example.mvvm.viewmodel.viewmodels;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import ru.example.mvvm.eventbus.Event;
import ru.example.mvvm.eventbus.EventBus;
import ru.example.mvvm.eventbus.EventListener;
import ru.example.mvvm.model.entities.Food;
import ru.example.mvvm.viewmodel.SpecifiedViewModel;

public class FoodViewModel extends SpecifiedViewModel<Food> implements EventListener {
    private Vector2 position; // Позиция в сетке (целые числа)
    private float nutritionValue;
    private Vector2 cellSize; // Теперь Vector2 вместо float
    private EventBus eventBus;

    @Override
    public void setEventBus(EventBus eventBus) {
        this.eventBus = eventBus;
        this.eventBus.subscribe("WINDOW_RESIZED", this);
    }

    @Override
    public void handleEvent(Event event) {
        if ("WINDOW_RESIZED".equals(event.getType())) {
            // Размер клетки будет обновляться через updateCasted
        }
    }

    @Override
    public Class<Food> getTargetEntityClass() {
        return Food.class;
    }

    @Override
    protected void updateCasted(Food entity) {
        this.position = entity.getPosition();
        this.nutritionValue = entity.getNutritionValue();
        // Получаем размер клетки как float и создаем Vector2
        this.cellSize = entity.getCellSize();
    }

    @Override
    public void drawShape(ShapeRenderer shapeRenderer) {
        // Рассчитываем позицию центра клетки
        float centerX = position.x * cellSize.x + cellSize.x / 2;
        float centerY = position.y * cellSize.y + cellSize.y / 2;

        // Размер еды (20-70% от меньшей стороны клетки)
        float minSize = Math.min(cellSize.x, cellSize.y) * 0.2f;
        float maxSize = Math.min(cellSize.x, cellSize.y) * 0.7f;
        float size = Math.max(minSize, Math.min(maxSize, nutritionValue / 5f));

        // Отрисовка еды по центру клетки
        shapeRenderer.setColor(0.2f, 0.8f, 0.2f, 0.8f);
        shapeRenderer.circle(centerX, centerY, size/2);
    }
}
