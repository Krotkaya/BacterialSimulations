package ru.example.mvvm.model.entities;
import com.badlogic.gdx.math.Vector2;
import ru.example.mvvm.model.ModelContext;

public class Bacteria extends BaseEntity {
    private Vector2 position;
    private float energy;
    private Vector2 cellSize;//ее переренсти в соответсвующие viewmodel для еды и бактерии

//она должна знать про свои координаты внутри сетки, но не знать про размер клетки
    //создать совй класс vector2Int чтобы вместо float хранить int координаты и закинуть в utils





    public Bacteria(int id, Vector2 position, float energy,Vector2 cellSize) {
        super(id);
        this.position = position;
        this.energy = energy;
        this.cellSize = cellSize;

    }

    @Override
    public void update(ModelContext context) {
        MoveDirection randomDirection = MoveDirection.getRandomDirection();
        context.moveBacteria(this, randomDirection);
    }
//выделим отдельный интерфейс у MovableEntity которые могут двигаться, у него будет получение позиции и установка позиции.
    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public Vector2 getCellSize() {
        return cellSize;
    }
}
