package ru.example.mvvm.viewmodel.viewmodels;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import ru.example.mvvm.eventbus.Event;
import ru.example.mvvm.eventbus.EventBus;
import ru.example.mvvm.eventbus.EventListener;
import ru.example.mvvm.eventbus.WindowResizedEvent;
import ru.example.mvvm.model.entities.Grid;
import ru.example.mvvm.viewmodel.SpecifiedViewModel;
import ru.example.mvvm.viewmodel.ViewModel;

public class GridViewModel extends SpecifiedViewModel<Grid> implements EventListener<WindowResizedEvent> {
    private EventBus eventBus;
    private float cellSize;
    private Color gridColor;
    private float fieldWidth;
    private float fieldHeight;
    private float screenWidth;
    private float screenHeight;
    //ширина высота в клетках, размеры окна

    @Override
    public Class<Grid> getTargetEntityClass() {
        return Grid.class;
    }

    @Override
    public void setEventBus(EventBus eventBus) {
        this.eventBus = eventBus;
        this.eventBus.subscribe(WindowResizedEvent.class, this);
        this.screenWidth = Gdx.graphics.getWidth();
        this.screenHeight = Gdx.graphics.getHeight();
    }

    @Override
    public void handleEvent(WindowResizedEvent event) {
        this.screenWidth = event.width();
        this.screenHeight = event.height();
    }


    @Override
    protected void updateCasted(Grid entity) {
        this.cellSize = entity.getCellSize();
        this.gridColor = entity.getGridColor();
        this.fieldWidth = entity.getFieldWidth();
        this.fieldHeight = entity.getFieldHeight();
        //считать размер клетки тут!!!
    }

    public void drawShape(ShapeRenderer shapeRenderer) {
        if (cellSize <= 0 || fieldWidth <= 0 || fieldHeight <= 0) return;
        shapeRenderer.end();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(gridColor);

        float scaleX = screenWidth / fieldWidth;
        float scaleY = screenHeight / fieldHeight;

        for (float x = 0; x <= fieldWidth; x += cellSize) {
            float screenX = x * scaleX;
            shapeRenderer.line(screenX, 0, screenX, screenHeight);
        }

        for (float y = 0; y <= fieldHeight; y += cellSize) {
            float screenY = y * scaleY;
            shapeRenderer.line(0, screenY, screenWidth, screenY);
        }

        shapeRenderer.end();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
    }

    @Override
    public int getDrawPriority() {
        return 10; // Higher priority - drawn last (on top)
    }
}
