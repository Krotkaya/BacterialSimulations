package ru.example.mvvm.utils;

import com.badlogic.gdx.math.Vector2;

/**
 * Утилитарные классы для работы с двумерными векторами
 */
public class Vector2Utils {
    /**
     * Генерирует случайный двумерный вектор единичной длины
     */
    public static Vector2 randomOneVector2() {
        return new Vector2(1, 0).setToRandomDirection();
    }

    /**
     * Генерирует случайный двумерный вектор длины <code>magnitude</code>
     * @param magnitude длина вектора
     */
    public static Vector2 randomVector2(float magnitude) {
        return randomOneVector2().scl(magnitude);
    }

    private Vector2Utils() { }
}
