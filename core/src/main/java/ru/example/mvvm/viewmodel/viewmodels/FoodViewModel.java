package ru.example.mvvm.viewmodel.viewmodels;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import ru.example.mvvm.eventbus.Event;
import ru.example.mvvm.eventbus.EventBus;
import ru.example.mvvm.eventbus.EventListener;
import ru.example.mvvm.model.entities.Food;
import ru.example.mvvm.viewmodel.SpecifiedViewModel;

public class FoodViewModel extends SpecifiedViewModel<Food>  implements EventListener {
    private Vector2 position;
    private float nutritionValue;
    private float cellSize;
    private EventBus eventBus;
    private float screenWidth;
    private float screenHeight;

    @Override
    public void setEventBus(EventBus eventBus) {
        this.eventBus = eventBus;
        this.eventBus.subscribe("WINDOW_RESIZED", this);
    }

    @Override
    public void handleEvent(Event event) {
        if ("WINDOW_RESIZED".equals(event.getType())) {
            int[] dimensions = (int[]) event.getData();
            this.screenWidth = dimensions[0];
            this.screenHeight = dimensions[1];
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
        this.cellSize = entity.getCellSize();
    }

    @Override
    public void drawShape(ShapeRenderer shapeRenderer) {
        float minSize = cellSize * 0.2f;
        float maxSize = cellSize * 0.7f;
        float size = Math.max(minSize, Math.min(maxSize, nutritionValue / 5f));

        float x = position.x * cellSize + (cellSize - size) / 2;
        float y = position.y * cellSize + (cellSize - size) / 2;

        shapeRenderer.setColor(0.2f, 0.8f, 0.2f, 0.8f);
        shapeRenderer.circle(x + size/2, y + size/2, size/2);
    }
}
