package com.qee.game;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.BaseAppState;
import com.jme3.asset.AssetManager;
import com.jme3.scene.Geometry;
import com.jme3.scene.Mesh;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Box;
import com.jme3.scene.shape.Sphere;

public class CharacterAppState extends BaseAppState {

    private AssetManager assetManager;


    private Node rootNode;

    private Node character;

    private Spatial human;

    private Mesh humanMesh;


    protected void initialize(Application application) {
        assetManager = application.getAssetManager();
        SimpleApplication simpleApplication = (SimpleApplication) application;
        rootNode = simpleApplication.getRootNode();
        character = new Node("character");

        humanMesh =new Sphere(1,1,1);

        human = new  Geometry();
        human.setMaterial();
        human.setM


    }

    protected void cleanup(Application application) {

    }

    protected void onEnable() {

    }

    protected void onDisable() {

    }
}
