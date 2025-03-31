// Bacteria.java
package ru.example.mvvm.model.entities;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import ru.example.mvvm.model.ModelContext;

import java.util.ArrayList;
import java.util.List;

public class Bacteria extends BaseEntity {
    private Vector2 position;
    private float energy;
    private final float moveCost;
    private final float eatEfficiency;

    public Bacteria(int id, Vector2 position, float energy, float moveCost, float eatEfficiency) {
        super(id);
        this.position = position;
        this.energy = energy;
        this.moveCost = moveCost;
        this.eatEfficiency = eatEfficiency;
    }

    @Override
    public void update(ModelContext context) {
        Vector2 newPosition = findAvailablePosition(context);
        if (newPosition != null) {
            position = newPosition;
        }
    }
//private static final List<Vector2> - список смещений
    //enum с внутренним состоянием и полем вектор 2, будет перекрывать все возможные смещения сущностей
    private Vector2 findAvailablePosition(ModelContext context) {
        List<Vector2> availableMoves = new ArrayList<>();
        Vector2[] possibleMoves = {
            new Vector2(position.x + 1, position.y),
            new Vector2(position.x - 1, position.y),
            new Vector2(position.x, position.y + 1),
            new Vector2(position.x, position.y - 1)
        };

        for (Vector2 move : possibleMoves) {
            if (context.isPositionAvailable(move)) {
                availableMoves.add(move);
            }
        }

        if (!availableMoves.isEmpty()) {
            return availableMoves.get(MathUtils.random(availableMoves.size() - 1));
        }

        return null;
    }


    public Vector2 getPosition() {
        return position;
    }

    public float getEnergy() {
        return energy;
    }
}
