package ru.example.mvvm.model.entities;

import com.badlogic.gdx.graphics.Color;
import ru.example.mvvm.model.ModelContext;

/**
 * Сущность для отображения заднего фона окна
 */
public class Grid extends BaseEntity {
    private float cellSize;
    private Color gridColor;
    private float fieldWidth;
    private float fieldHeight;
//размер ячеек должна быть не у сущности а у viewmodel
    public Grid(int id, float cellSize,Color gridColor,
                float fieldWidth, float fieldHeight) {
        super(id);
        this.cellSize = cellSize;
        this.gridColor = gridColor;
        this.fieldWidth = fieldWidth;
        this.fieldHeight = fieldHeight;
    }

    public Color getGridColor() {
        return gridColor;
    }

    @Override
    public void update(ModelContext context) { }

    public float getCellSize() {
        return cellSize;
    }

    public float getFieldWidth() {
        return fieldWidth;
    }

    public float getFieldHeight() {
        return fieldHeight;
    }


}
