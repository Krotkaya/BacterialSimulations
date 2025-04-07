package ru.example.mvvm.viewmodel.viewmodels;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import ru.example.mvvm.eventbus.Event;
import ru.example.mvvm.eventbus.EventBus;
import ru.example.mvvm.eventbus.EventListener;
import ru.example.mvvm.model.entities.Bacteria;
import ru.example.mvvm.viewmodel.SpecifiedViewModel;

public class BacteriaViewModel extends SpecifiedViewModel<Bacteria> implements EventListener {
    private Vector2 position;
    private Vector2 cellSize;
    private EventBus eventBus;


    @Override
    public void setEventBus(EventBus eventBus) {
        this.eventBus = eventBus;
        this.eventBus.subscribe("WINDOW_RESIZED", this);
    }

    @Override
    public void handleEvent(Event event) {
        if ("WINDOW_RESIZED".equals(event.getType())) {
        }
    }



    @Override
    public Class<Bacteria> getTargetEntityClass() {
        return Bacteria.class;
    }

    @Override
    protected void updateCasted(Bacteria entity) {
        this.position = entity.getPosition();
        this.cellSize = entity.getCellSize();
    }
//контекст который будет передаваться в updateCasted, для grid установить размер клетки, а потом этот же контекст передаем дальше
    @Override
    public void drawShape(ShapeRenderer shapeRenderer) {
        float centerX = position.x * cellSize.x + cellSize.x / 2;
        float centerY = position.y * cellSize.y + cellSize.y / 2;


        float size = Math.min(cellSize.x, cellSize.y) * 0.8f;

        shapeRenderer.setColor(0f, 0f, 1f, 1f);
        shapeRenderer.circle(centerX, centerY, size/2);
    }
}
