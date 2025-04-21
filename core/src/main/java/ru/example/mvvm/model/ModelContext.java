// ModelContext.java
package ru.example.mvvm.model;

import com.badlogic.gdx.math.Vector2;
import ru.example.mvvm.model.entities.Bacteria;
import ru.example.mvvm.model.entities.Food;
import ru.example.mvvm.model.entities.MoveDirection;

import java.util.List;

public class ModelContext {
    private final float gameZoneWidth;
    private final float gameZoneHeight;
    private final GameModel gameModel;

    public ModelContext(float gameZoneWidth, float gameZoneHeight, GameModel gameModel) {
        this.gameZoneWidth = gameZoneWidth;
        this.gameZoneHeight = gameZoneHeight;
        this.gameModel = gameModel;
    }

    public boolean isPositionAvailable(Vector2 position) {
        return gameModel.isPositionAvailable(position);
    }

    public List<Entity> getEntities() {
        return gameModel.getEntities();
    }

    public void moveBacteria(Bacteria bacteria, MoveDirection direction) {
        Vector2 newPosition = direction.getOffset().cpy().add(bacteria.getPosition());
        if (isPositionAvailable(newPosition)) {
            bacteria.setPosition(newPosition);
        }
    }//от частности переходим к интерфейсу от передвижение бактерии к передвижению entity
}
//нужно добавить сущность яд (аналогично еде) и добавим все возможные команды для бактерии сюда, есть еду, есть яд, уметь смотреть в каком-то направлении и выдаст какую-то обратную реакцию(целое число) (условная команда внутри генома бактерии, в зависимости от того что там находится), например если там пусто то 0, еда-1, яд-2, клетка за границами экрана-4, чтобы бактерии научились не выходить за клетки экрана или бегать по границе
