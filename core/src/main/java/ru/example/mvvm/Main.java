package ru.example.mvvm;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;

import ru.example.mvvm.model.GameModel;
import ru.example.mvvm.model.entities.Background;
import ru.example.mvvm.model.entities.Bacteria;
import ru.example.mvvm.model.entities.Food;
import ru.example.mvvm.viewmodel.viewmodels.BackgroundViewModel;
import ru.example.mvvm.viewmodel.viewmodels.BacteriaViewModel;
import ru.example.mvvm.viewmodel.viewmodels.FoodViewModel;
import ru.example.mvvm.viewmodel.ViewModelManager;

import java.util.List;

public class Main extends ApplicationAdapter {
    private SpriteBatch spriteBatch;
    private ShapeRenderer shapeRenderer;
    private ViewModelManager viewModelManager;
    private GameModel gameModel;
    private float spawnTimer = 0;

    @Override
    public void create() {
        spriteBatch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();

        viewModelManager = new ViewModelManager();
        viewModelManager.addViewModelMapping(Background.class, () -> new BackgroundViewModel());
        viewModelManager.addViewModelMapping(Bacteria.class, BacteriaViewModel::new);
        viewModelManager.addViewModelMapping(Food.class, FoodViewModel::new);

        gameModel = new GameModel();
        gameModel.setGameZoneSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        gameModel.addEntity(new Background(0, 50f, 0.5f, 0.5f, 0.5f));

        spawnInitialEntities();
        gameModel.start();
        viewModelManager.updateViewModelBounds(gameModel.getEntities(), List.of());
    }

    private void spawnInitialEntities() {
        for (int i = 0; i < 10; i++) {
            Vector2 pos = getRandomGridPosition();
            gameModel.addEntity(new Bacteria(
                gameModel.getNextId(),
                pos,
                MathUtils.random(50f, 100f),
                1f,
                0.8f
            ));
        }

        for (int i = 0; i < 20; i++) {
            Vector2 pos = getRandomGridPosition();
            gameModel.addEntity(new Food(
                gameModel.getNextId(),
                pos,
                MathUtils.random(10f, 30f)
            ));
        }
    }

    private Vector2 getRandomGridPosition() {
        int gridWidth = Gdx.graphics.getWidth() / 50;
        int gridHeight = Gdx.graphics.getHeight() / 50;
        return new Vector2(
            MathUtils.random(0, gridWidth - 1),
            MathUtils.random(0, gridHeight - 1)
        );
    }



    @Override
    public void render() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);

//        // Спавн новой еды раз в 2 секунды
//        spawnTimer += Gdx.graphics.getDeltaTime();
//        if (spawnTimer > 2f) {
//            spawnTimer = 0;
//            Vector2 pos = getRandomGridPosition();
//            gameModel.addEntity(new Food(
//                gameModel.getNextId(),
//                pos,
//                MathUtils.random(10f, 30f)
//            ));
//        }

        gameModel.update();
        viewModelManager.updateViewModels(gameModel.getEntities());
        viewModelManager.drawView(spriteBatch, shapeRenderer, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    @Override
    public void dispose() {
        spriteBatch.dispose();
        shapeRenderer.dispose();
    }
}
