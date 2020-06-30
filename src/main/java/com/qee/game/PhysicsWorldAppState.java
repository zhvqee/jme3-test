package com.qee.game;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.BaseAppState;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.PhysicsSpace;
import com.jme3.bullet.collision.shapes.BoxCollisionShape;
import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.bullet.util.CollisionShapeFactory;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;

public class PhysicsWorldAppState extends BaseAppState {


    private PhysicsSpace physicsSpace;

    private RigidBodyControl rigidBodyControl;

    private Node world ;


    @Override
    protected void initialize(Application app) {
        world =new Node();


        BulletAppState state = app.getStateManager().getState(BulletAppState.class);
        physicsSpace = state.getPhysicsSpace();


        Box box = new Box(10, 0.5f, 10);
        Geometry geometry = new Geometry("floor", box);
        Material material = new Material(app.getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
        material.setColor("Color", ColorRGBA.Brown);
        geometry.setMaterial(material);
        geometry.center();

        Box brige = new Box(10, 2f, 2);
        Geometry geometry2 = new Geometry("brige", brige);
        Material material2 = new Material(app.getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
        material2.setColor("Color", ColorRGBA.Brown);
        geometry2.setMaterial(material2);
        geometry2.move(new Vector3f(0,0,-10));

        world.attachChild(geometry);
        world.attachChild(geometry2);


        CollisionShape meshShape = CollisionShapeFactory.createMeshShape(world);
        rigidBodyControl = new RigidBodyControl(meshShape);
        rigidBodyControl.setMass(0);
        physicsSpace.add(rigidBodyControl);

        SimpleApplication simpleApplication = (SimpleApplication) app;
        simpleApplication.getRootNode().attachChild(world);
        state.setDebugEnabled(true);

    }

    @Override
    protected void cleanup(Application app) {

    }

    @Override
    protected void onEnable() {

    }

    @Override
    protected void onDisable() {

    }
}
