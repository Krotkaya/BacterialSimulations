package ru.example.mvvm.viewmodel.viewmodels;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import ru.example.mvvm.model.entities.Background;
import ru.example.mvvm.viewmodel.SpecifiedViewModel;

/**
 * Контроллер для отображения сущности {@link Background}
 */
public class BackgroundViewModel extends SpecifiedViewModel<Background> {
    private float cellSize;
    private float gridColorR;
    private float gridColorG;
    private float gridColorB;

    @Override
    public Class<Background> getTargetEntityClass() {
        return Background.class;
    }

    @Override
    protected void updateCasted(Background entity) {
        this.cellSize = entity.getCellSize();
        this.gridColorR = entity.getGridColorR();
        this.gridColorG = entity.getGridColorG();
        this.gridColorB = entity.getGridColorB();
    }

    public void drawShape(ShapeRenderer shapeRenderer, float screenWidth, float screenHeight) {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(gridColorR, gridColorG, gridColorB, 1f);
//вместо размера окна иметь ограничение сразу поля (50 на 50 например задаю в main) клетки могут стать прямоугольниками
        for (float x = 0; x <= screenWidth; x += cellSize) {
            shapeRenderer.line(x, 0, x, screenHeight);
        }

        for (float y = 0; y <= screenHeight; y += cellSize) {
            shapeRenderer.line(0, y, screenWidth, y);
        }

        shapeRenderer.end();
    }
}
