package ru.example.mvvm.viewmodel;

import java.util.*;
import java.util.function.Supplier;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.IntMap;

import ru.example.mvvm.eventbus.*;
import ru.example.mvvm.eventbus.EventListener;
import ru.example.mvvm.model.Entity;
import ru.example.mvvm.viewmodel.viewmodels.GridViewModel;

/**
 * Менеджер работы всей {@link ViewModel} части игры. Производит биндинг контроллеров отображения для каждой сущности
 * в игре. Также управляет обновлением состояния всех забиндженых ViewModel'ов и их отрисовкой
 */
public class ViewModelManager implements EventListener<Event> {

    private final Map<Class<? extends Entity>, Supplier<ViewModel>> viewModelsMapping = new HashMap<>();
    private final IntMap<ViewModel> boundViewModels = new IntMap<>();
    private final EventBus eventBus;

    public ViewModelManager(EventBus eventBus) {
        this.eventBus = eventBus;
        this.eventBus.subscribe(EntityCreatedEvent.class, e -> handleEvent(e));
        this.eventBus.subscribe(EntityRemovedEvent.class, e -> handleEvent(e));
        this.eventBus.subscribe(WindowResizedEvent.class, e -> handleEvent(e));

    }

    @Override
    public void handleEvent(Event event) {
        if (event instanceof EntityCreatedEvent created) {
            bindViewModel(created.entity());
        } else if (event instanceof EntityRemovedEvent removed) {
            unbindViewModel(removed.entity());
        } else if (event instanceof WindowResizedEvent resized) {
            // broadcast to all view models
            for (ViewModel vm : boundViewModels.values()) {
                if (vm instanceof EventListener<?>) {
                    @SuppressWarnings("unchecked")
                    EventListener<WindowResizedEvent> listener = (EventListener<WindowResizedEvent>) vm;
                    listener.handleEvent(resized);
                }
            }
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
            // Need to properly handle unsubscription for all event types
            // This is a simplified version - you might need to track all subscribed event types
            if (viewModel instanceof EventListener<?>) {
                eventBus.unsubscribe(WindowResizedEvent.class, (EventListener<WindowResizedEvent>) viewModel);
            }
        }
    }

    public void updateViewModelBounds(List<Entity> addedEntities, List<Entity> removedEntities) {
        for (Entity entity : removedEntities) {
            eventBus.publish(new EntityRemovedEvent(entity));
        }

        for (Entity entity : addedEntities) {
            eventBus.publish(new EntityCreatedEvent(entity));
        }
    }

    public void addViewModelMapping(Class<? extends Entity> entityType, Supplier<ViewModel> viewModel) {
        if (viewModelsMapping.put(entityType, viewModel) != null) {
            throw new IllegalArgumentException("Duplicate view model factory found: " + entityType);
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
        // Create a list from IntMap values
        List<ViewModel> sortedViewModels = new ArrayList<>();
        for (ViewModel vm : boundViewModels.values()) {
            sortedViewModels.add(vm);
        }

        // Sort by draw priority
        sortedViewModels.sort(Comparator.comparingInt(ViewModel::getDrawPriority));

        // Draw filled shapes (except Grid)
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        for (ViewModel viewModel : sortedViewModels) {
            if (!(viewModel instanceof GridViewModel)) {
                viewModel.drawShape(shapeRenderer);
            }
        }
        shapeRenderer.end();

        // Draw Grid lines
        for (ViewModel viewModel : sortedViewModels) {
            if (viewModel instanceof GridViewModel) {
                shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
                ((GridViewModel) viewModel).drawShape(shapeRenderer);
                shapeRenderer.end();
            }
        }

        // Draw sprites
        spriteBatch.begin();
        for (ViewModel viewModel : sortedViewModels) {
            if (!(viewModel instanceof GridViewModel)) {
                viewModel.drawSprite(spriteBatch);
            }
        }
        spriteBatch.end();
    }

    public ViewModel getViewModel(int entityId) {
        return boundViewModels.get(entityId);
    }

    public ViewModel getViewModel(Entity entity) {
        return boundViewModels.get(entity.getId());
    }
}
