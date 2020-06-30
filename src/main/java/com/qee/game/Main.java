package com.qee.game;

import com.jme3.app.SimpleApplication;
import com.jme3.bullet.BulletAppState;

public class Main extends SimpleApplication {
    @Override
    public void simpleInitApp() {
        stateManager.attach(new PhysicsWorldAppState());

       stateManager.attach(new CharacterAppState());
        stateManager.attach(new BulletAppState());

        stateManager.attach(new InputAppState());


    }

    public static void main(String[] args) {
        Main main =new Main();
        main.start();
    }
}
