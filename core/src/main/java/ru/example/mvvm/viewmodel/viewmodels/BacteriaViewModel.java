package ru.example.mvvm.viewmodel.viewmodels;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import ru.example.mvvm.model.entities.Bacteria;
import ru.example.mvvm.viewmodel.SpecifiedViewModel;

public class BacteriaViewModel extends SpecifiedViewModel<Bacteria> {
    private Vector2 position;
    private float energy;

    @Override
    public Class<Bacteria> getTargetEntityClass() {
        return Bacteria.class;
    }

    @Override
    protected void updateCasted(Bacteria entity) {
        this.position = entity.getPosition();
        this.energy = entity.getEnergy();
    }

    @Override
    public void drawShape(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(Color.GREEN);
        shapeRenderer.circle(position.x * 50 + 25, position.y * 50 + 25, 20);
    }

//    @Override
//    public void drawShape(ShapeRenderer shapeRenderer) {
//        float alpha = Math.min(energy / 100f, 1f);
//        shapeRenderer.setColor(0.8f, 0.2f, 0.2f, alpha);
//        shapeRenderer.rect(position.x * 50, position.y * 50, 50, 50);
//    }
}
