package ru.example.mvvm.model.entities;

import ru.example.mvvm.model.ModelContext;

/**
 * Сущность для отображения заднего фона окна
 */
public class Background extends BaseEntity {
    private final float cellSize;
    private final float gridColorR;
    private final float gridColorG;
    private final float gridColorB;

    public Background(int id, float cellSize, float gridColorR, float gridColorG, float gridColorB) {
        super(id);
        this.cellSize = cellSize;//класс color
        this.gridColorR = gridColorR;
        this.gridColorG = gridColorG;
        this.gridColorB = gridColorB;
    }

    @Override
    public void update(ModelContext context) { }

    public float getCellSize() {
        return cellSize;
    }

    public float getGridColorR() {
        return gridColorR;
    }

    public float getGridColorG() {
        return gridColorG;
    }

    public float getGridColorB() {
        return gridColorB;
    }
    }
