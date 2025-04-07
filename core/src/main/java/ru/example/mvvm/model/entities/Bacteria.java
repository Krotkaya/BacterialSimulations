package ru.example.mvvm.model.entities;
import com.badlogic.gdx.math.Vector2;
import ru.example.mvvm.model.ModelContext;

public class Bacteria extends BaseEntity {
    private Vector2 position;
    private float energy;
    private float cellSize;
    //хранить ширину и высоту в которую мы будем ее вписывать


    public Bacteria(int id, Vector2 position, float energy,float cellSize) {
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

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public float getCellSize() {
        return cellSize;
    }
}
