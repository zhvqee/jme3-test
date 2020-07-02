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

        // 定向光
        DirectionalLight sunLight = new DirectionalLight();
        sunLight.setDirection(new Vector3f(1, 2, 3));
        sunLight.setColor(new ColorRGBA(0.8f, 0.8f, 0.8f, 1f));

        // 环境光
        AmbientLight ambientLight = new AmbientLight();
        ambientLight.setColor(new ColorRGBA(0.2f, 0.2f, 0.2f, 1f));

        // 将光源添加到场景图中
        rootNode.addLight(sunLight);
        rootNode.addLight(ambientLight);

    }

    public static void main(String[] args) {
        Main main = new Main();
        main.start();
    }
}
