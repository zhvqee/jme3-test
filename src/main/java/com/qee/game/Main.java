package com.qee.game;

import com.jme3.app.SimpleApplication;
import com.jme3.bullet.BulletAppState;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;

public class Main extends SimpleApplication {
    @Override
    public void simpleInitApp() {
        stateManager.attach(new PhysicsWorldAppState());

        stateManager.attach(new CharacterAppState());
        stateManager.attach(new BulletAppState());

        stateManager.attach(new InputAppState());

        initLight();
    }

    private void initLight() {
        // 添加平行光
        DirectionalLight sun = new DirectionalLight();
        sun.setColor(new ColorRGBA(0.7f, 0.7f, 0.7f, 1f));
        sun.setDirection(new Vector3f(-3, -4, -5).normalizeLocal());
        rootNode.addLight(sun);
// 添加环境光
        AmbientLight ambient = new AmbientLight();
        ambient.setColor(new ColorRGBA(0.2f, 0.2f, 0.2f, 1f));
        rootNode.addLight(ambient);
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.start();
    }
}
