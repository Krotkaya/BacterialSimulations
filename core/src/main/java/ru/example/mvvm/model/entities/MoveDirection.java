
package ru.example.mvvm.model.entities;

import com.badlogic.gdx.math.Vector2;

public enum MoveDirection {
    UP(new Vector2(0, 1)),
    DOWN(new Vector2(0, -1)),
    LEFT(new Vector2(-1, 0)),
    RIGHT(new Vector2(1, 0));

    private final Vector2 offset;

    MoveDirection(Vector2 offset) {
        this.offset = offset;
    }

    public Vector2 getOffset() {
        return offset;
    }

    public static MoveDirection getRandomDirection() {
        return values()[(int)(Math.random() * values().length)];
    }
}
