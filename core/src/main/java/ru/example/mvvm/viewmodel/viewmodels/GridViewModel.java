package ru.example.mvvm.viewmodel.viewmodels;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import ru.example.mvvm.eventbus.Event;
import ru.example.mvvm.eventbus.EventBus;
import ru.example.mvvm.model.entities.Grid;
import ru.example.mvvm.viewmodel.SpecifiedViewModel;
import ru.example.mvvm.viewmodel.ViewModel;

public class GridViewModel extends SpecifiedViewModel<Grid> implements ViewModel {
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
        this.eventBus.subscribe("WINDOW_RESIZED", this::handleWindowResized);
        this.screenWidth = Gdx.graphics.getWidth();
        this.screenHeight = Gdx.graphics.getHeight();
    }

    private void handleWindowResized(Event event) {
        // Обработка события
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
    protected void updateCasted(Grid entity) {
        this.cellSize = entity.getCellSize();
        this.gridColor = entity.getGridColor();
        this.fieldWidth = entity.getFieldWidth();
        this.fieldHeight = entity.getFieldHeight();
        //здесь считываем актуальные размеры окна

    }

    public void drawShape(ShapeRenderer shapeRenderer) {
        if (cellSize <= 0 || fieldWidth <= 0 || fieldHeight <= 0) return;

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(gridColor);

        // Рассчитываем масштаб для координат
        float scaleX = screenWidth / fieldWidth;
        float scaleY = screenHeight / fieldHeight;

        // Рисуем вертикальные линии
        for (float x = 0; x <= fieldWidth; x += cellSize) {
            float screenX = x * scaleX;
            shapeRenderer.line(screenX, 0, screenX, screenHeight);
        }

        // Рисуем горизонтальные линии
        for (float y = 0; y <= fieldHeight; y += cellSize) {
            float screenY = y * scaleY;
            shapeRenderer.line(0, screenY, screenWidth, screenY);
        }

        shapeRenderer.end();
    }

//    public void drawShape(ShapeRenderer shapeRenderer, float screenWidth, float screenHeight) {
//        if (cellSize <= 0) return;
////Проверить, косяк тут!!!
//        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
//        shapeRenderer.setColor(gridColor);
//
//        for (float x = 0; x <= fieldWidth; x += cellSize) {
//            float screenX = x * (screenWidth / fieldWidth);
//            shapeRenderer.line(screenX, 0, screenX, screenHeight);
//        }
//
//        for (float y = 0; y <= fieldHeight; y += cellSize) {
//            float screenY = y * (screenHeight / fieldHeight);
//            shapeRenderer.line(0, screenY, screenWidth, screenY);
//        }
//
//
//        shapeRenderer.end();
//    }
}
