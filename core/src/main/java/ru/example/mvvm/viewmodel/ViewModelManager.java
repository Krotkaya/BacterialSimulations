package ru.example.mvvm.viewmodel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.IntMap;

import ru.example.mvvm.eventbus.Event;
import ru.example.mvvm.eventbus.EventBus;
import ru.example.mvvm.eventbus.EventListener;
import ru.example.mvvm.model.Entity;
import ru.example.mvvm.viewmodel.viewmodels.GridViewModel;

/**
 * Менеджер работы всей {@link ViewModel} части игры. Производит биндинг контроллеров отображения для каждой сущности
 * в игре. Также управляет обновлением состояния всех забиндженых ViewModel'ов и их отрисовкой
 */
public class ViewModelManager implements EventListener {
    /** Словарь фабрик ViewModel'ей. Позволяет автоматически собирать ViewModel для новой игровой сущности */
    private final Map<Class<? extends Entity>, Supplier<ViewModel>> viewModelsMapping = new HashMap<>();
    private final IntMap<ViewModel> boundViewModels = new IntMap<>();
    private final EventBus eventBus;

    public ViewModelManager(EventBus eventBus) {
        this.eventBus = eventBus;
        this.eventBus.subscribe("ENTITY_CREATED", this);
        this.eventBus.subscribe("ENTITY_REMOVED", this);
    }

    @Override
    public void handleEvent(Event event) {
        switch (event.getType()) {
            case "ENTITY_CREATED":
                Entity createdEntity = (Entity) event.getData();
                bindViewModel(createdEntity);
                break;
            case "ENTITY_REMOVED":
                Entity removedEntity = (Entity) event.getData();
                unbindViewModel(removedEntity);
                break;
        }
    }

    private void bindViewModel(Entity entity) {
        Supplier<ViewModel> viewModelFactory = viewModelsMapping.get(entity.getClass());
        if (viewModelFactory == null) {
            throw new IllegalArgumentException("Unregistered view model factory for entity type: " + entity.getClass());
        }

        ViewModel viewModel = viewModelFactory.get();
        viewModel.setEventBus(eventBus);

        boundViewModels.put(entity.getId(), viewModel);
    }

    private void unbindViewModel(Entity entity) {
        ViewModel viewModel = boundViewModels.remove(entity.getId());
        if (viewModel instanceof EventListener) {
            eventBus.unsubscribe("WINDOW_RESIZED", (EventListener) viewModel);
        }
    }

    public void updateViewModelBounds(List<Entity> addedEntities, List<Entity> removedEntities) {
        for (Entity entity : removedEntities) {
            eventBus.publish(new Event("ENTITY_REMOVED", entity));
        }

        for (Entity entity : addedEntities) {
            eventBus.publish(new Event("ENTITY_CREATED", entity));
        }
    }

    public void addViewModelMapping(Class<? extends Entity> entityType, Supplier<ViewModel> viewModel) {
        if (viewModelsMapping.put(entityType, viewModel) != null) {
            throw new IllegalArgumentException("Duplicate view model factory found: " + entityType);
        }
    }

    private void unbind(List<Entity> entities) {
        for (Entity removedEntity : entities) {
            boundViewModels.remove(removedEntity.getId());
        }
    }

    public void updateViewModels(List<Entity> entities) {
        for (Entity entity : entities) {
            ViewModel viewModel = boundViewModels.get(entity.getId());
            if (viewModel == null) continue;

            viewModel.update(entity);
        }
    }

    public void drawView(SpriteBatch spriteBatch, ShapeRenderer shapeRenderer) {
        for (ViewModel viewModel : boundViewModels.values()) {
            if (viewModel instanceof GridViewModel) {
                ((GridViewModel) viewModel).drawShape(shapeRenderer);
            }
        }

        spriteBatch.begin();
        for (ViewModel viewModel : boundViewModels.values()) {
            if (!(viewModel instanceof GridViewModel)) {
                viewModel.drawSprite(spriteBatch);
            }
        }
        spriteBatch.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        for (ViewModel viewModel : boundViewModels.values()) {
            if (!(viewModel instanceof GridViewModel)) {
                viewModel.drawShape(shapeRenderer);
            }
        }
        shapeRenderer.end();
    }

    /**
     * Получение ViewModel по ID сущности
     * @param entityId ID сущности
     * @return ViewModel или null, если не найдена
     */
    public ViewModel getViewModel(int entityId) {
        return boundViewModels.get(entityId);
    }

    /**
     * Получение ViewModel по сущности
     * @param entity сущность
     * @return ViewModel или null, если не найдена
     */
    public ViewModel getViewModel(Entity entity) {
        return boundViewModels.get(entity.getId());
    }
}
