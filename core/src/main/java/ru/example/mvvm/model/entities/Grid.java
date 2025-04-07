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

    //Gdx.graphics.getWidth(), Gdx.graphics.getHeight() вытаскиеваем текущие размеры окна, на этом этапе высчитать шаг для вертикальных и горизонтальных линий
    //ввести шину (паттерн проектирования наблюдатель, viewModelContext позволяет публиковать события, на конкретные виды событий подписывать обработчиков), передаем при создании viewModel (viewModel инициализация и destroy)
    //в джаве есть готовые реализации наблюдателя и наблюдаемого, если я его введу то это баллы за сложность будут
    //альтернатива (не берем, делаем шину), контекст содержит только механизм публикации каких-то событий, в три этапа при создании, при удалении, и в петоде update (можем захотеть публиковать)

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
