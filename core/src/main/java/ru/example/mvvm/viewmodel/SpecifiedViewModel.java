package ru.example.mvvm.viewmodel;

import ru.example.mvvm.model.Entity;

/**
 * Абстрактный адаптер {@link ViewModel} для упрощения создания новых контроллеров отрисовки
 * @param <E>
 */
public abstract class SpecifiedViewModel<E extends Entity> implements ViewModel {
    @Override
    @SuppressWarnings("uncheced")
    public void update(Entity boundEntity) {
        updateCasted((E)boundEntity);
    }

    /** Специализируем метод {@link #getTargetEntityClass()} чтобы случайно не ошибиться в реализации */
    @Override
    public abstract Class<E> getTargetEntityClass();

    /**
     * Спецификация метода {@link #update(Entity)} для сущностей, определённых в Generic
     */
    protected abstract void updateCasted(E entity);
}
