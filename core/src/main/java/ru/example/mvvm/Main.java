package ru.example.mvvm;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import ru.example.mvvm.eventbus.Event;
import ru.example.mvvm.eventbus.EventBus;
import ru.example.mvvm.eventbus.SimpleEventBus;
import ru.example.mvvm.eventbus.WindowResizedEvent;
import ru.example.mvvm.model.GameModel;
import ru.example.mvvm.model.entities.Grid;
import ru.example.mvvm.model.entities.Bacteria;
import ru.example.mvvm.model.entities.Food;
import ru.example.mvvm.viewmodel.viewmodels.GridViewModel;
import ru.example.mvvm.viewmodel.viewmodels.BacteriaViewModel;
import ru.example.mvvm.viewmodel.viewmodels.FoodViewModel;
import ru.example.mvvm.viewmodel.ViewModelManager;

import java.util.List;
public class Main extends ApplicationAdapter {
    private EventBus eventBus;
    private SpriteBatch spriteBatch;
    private ShapeRenderer shapeRenderer;
    private ViewModelManager viewModelManager;
    private GameModel gameModel;
    private static final float CELL_SIZE = 50f; // Размер клетки
    private static final int GRID_WIDTH = 10;
    private static final int GRID_HEIGHT = 10;

    @Override
    public void create() {
        eventBus = new SimpleEventBus();
        spriteBatch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        Color gridColor = new Color(0.5f, 0.5f, 0.5f, 1f);

        viewModelManager = new ViewModelManager(eventBus);
        eventBus.subscribe(WindowResizedEvent.class, this::handleWindowResized);
        viewModelManager.addViewModelMapping(Grid.class, () -> new GridViewModel());
        viewModelManager.addViewModelMapping(Bacteria.class, BacteriaViewModel::new);
        viewModelManager.addViewModelMapping(Food.class, FoodViewModel::new);

        gameModel = new GameModel();
        gameModel.setGameZoneSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        gameModel.addEntity(new Grid(0, CELL_SIZE, gridColor,
            GRID_WIDTH * CELL_SIZE, GRID_HEIGHT * CELL_SIZE));
        spawnInitialEntities();
        gameModel.start();
        viewModelManager.updateViewModelBounds(gameModel.getEntities(), List.of());
    }

    private void handleWindowResized(WindowResizedEvent event) {
        gameModel.setGameZoneSize(event.width(), event.height());
    }


    private void spawnInitialEntities() {
        Vector2 cellSize = new Vector2(
            Gdx.graphics.getWidth() / (float)GRID_WIDTH,
            Gdx.graphics.getHeight() / (float)GRID_HEIGHT
        );

        for (int i = 0; i < 10; i++) {
            Vector2 pos = getRandomGridPosition();
            gameModel.addEntity(new Bacteria(
                gameModel.getNextId(),
                pos,
                MathUtils.random(50f, 100f),
                cellSize
            ));
        }

        for (int i = 0; i < 20; i++) {
            Vector2 pos = getRandomGridPosition();
            gameModel.addEntity(new Food(
                gameModel.getNextId(),
                pos,
                MathUtils.random(30f, 50f),
                cellSize
            ));
        }
    }

    private Vector2 getRandomGridPosition() {
        return new Vector2(
            MathUtils.random(0, GRID_WIDTH - 1),
            MathUtils.random(0, GRID_HEIGHT - 1)
        );
    }



    @Override
    public void render() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        gameModel.update();
        viewModelManager.updateViewModels(gameModel.getEntities());
        viewModelManager.drawView(spriteBatch, shapeRenderer);
    }

    @Override
    public void dispose() {
        spriteBatch.dispose();
        shapeRenderer.dispose();
    }

    @Override
    public void resize(int width, int height) {
        eventBus.publish(new WindowResizedEvent(width, height));
    }
}
