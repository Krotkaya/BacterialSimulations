package ru.example.mvvm.viewmodel;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import ru.example.mvvm.eventbus.Event;
import ru.example.mvvm.eventbus.EventBus;
import ru.example.mvvm.model.Entity;

/**
 * Контроллер для отрисовки сущностей модели в MVVM. Устроен по принципу привязки контроллера к конкретным сущностям
 * из модели. Благодаря такому подходу можно реализовывать модель, которая ничего не будет знать о том, что её кто-то
 * собирается отрисовывать.
 */
public interface ViewModel {
    /**
     * Возвращает класс сущностей модели, для которых предназначен данный контроллер
     */
    Class<? extends Entity> getTargetEntityClass();


    /**
     * Обновляет внутреннее состояния контроллера перед отрисовкой
     * @param boundEntity сущность, которой сопоставлен контроллер
     */
    void update(Entity boundEntity);

    /**
     * Отрисовка спрайтов контроллера
     */
    default void drawSprite(SpriteBatch batch) { }

    /**
     * Отрисовка геометрических фигур контроллера
     */
    default void drawShape(ShapeRenderer shapeRenderer) { }

    default void setEventBus(EventBus eventBus) {}

    default int getDrawPriority() {
        return 0;
    }
}
