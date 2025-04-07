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
        // Бактерия занимает 80% клетки
        float size = cellSize * 0.8f;
        float x = position.x * cellSize + (cellSize - size) / 2;
        float y = position.y * cellSize + (cellSize - size) / 2;

    }
}
