package com.qee.game;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.BaseAppState;
import com.jme3.asset.AssetManager;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.collision.shapes.CapsuleCollisionShape;
import com.jme3.bullet.control.CharacterControl;
import com.jme3.input.ChaseCamera;
import com.jme3.input.InputManager;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Sphere;

public class CharacterAppState extends BaseAppState {

    private Node characterRootNode;

    private Spatial human;

    private Node cameraNode;

    private AssetManager assetManager;

    private ChaseCamera chaseCamera;

    private SimpleApplication simpleApplication;

    private Node rootNode;

    private CharacterControl player;

    /**
     * 用于计算角色行走方向的变量
     */
    private Vector3f walkDir = new Vector3f();
    private Vector3f camDir = new Vector3f();
    private Quaternion camRot = new Quaternion();


    @Override
    protected void initialize(Application app) {
        simpleApplication = (SimpleApplication) app;
        assetManager = app.getAssetManager();
        characterRootNode = new Node("characterRootNode");
        human = new Geometry("human", new Sphere(8, 8, 1));
        Material material = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        material.setColor("Color", ColorRGBA.Red);
        human.setMaterial(material);

        // 使用胶囊体作为玩家的碰撞形状
        CapsuleCollisionShape capsuleShape = new CapsuleCollisionShape(0.5f, 1.9f, 1);

        // 使用CharacterControl来控制玩家物体
        this.player = new CharacterControl(capsuleShape, 0.5f);
        player.setGravity(new Vector3f(0f, -100f, 0f));
        characterRootNode.addControl(player);

        app.getStateManager().getState(BulletAppState.class).getPhysicsSpace().add(player);


        characterRootNode.attachChild(human);
        cameraNode = new Node("camera");

        createSimpleChaseCamera(app.getCamera(), cameraNode, app.getInputManager());

        cameraNode.setLocalTranslation(0, 0.9f, 0.3f);// 将此节点上移一段距离，使摄像机位于角色的头部。

        characterRootNode.attachChild(cameraNode);
        rootNode = simpleApplication.getRootNode();
        rootNode.attachChild(characterRootNode);

    }

    public ChaseCamera createSimpleChaseCamera(Camera camera, Node tar2
            , InputManager inputManager) {
        chaseCamera = new ChaseCamera(camera, tar2, inputManager);

        // 开启镜头跟随可能让部分人容易犯头晕
        chaseCamera.setSmoothMotion(true);
        chaseCamera.setTrailingEnabled(false);

        chaseCamera.setInvertVerticalAxis(true);
        chaseCamera.setLookAtOffset(Vector3f.UNIT_Y.mult(2f));
        chaseCamera.setZoomSensitivity(0.5f);
        chaseCamera.setRotationSpeed(5f);
        chaseCamera.setRotationSensitivity(5);
        chaseCamera.setMaxDistance(15);
        chaseCamera.setMinDistance(2f);
        chaseCamera.setDefaultDistance(4);
        chaseCamera.setChasingSensitivity(5);
        chaseCamera.setDownRotateOnCloseViewOnly(true);
        chaseCamera.setUpVector(Vector3f.UNIT_Y);
        // 不要隐藏光标,否则在MAC系统下鼠标点击后会上下错位
        chaseCamera.setHideCursorOnRotate(false);
        return chaseCamera;
    }


    @Override
    public void update(float tpf) {
        if (walkDir.lengthSquared() != 0) {
            // 计算摄像机在水平面的方向
            //   camDir.set(cameraCur.getDirection());
            camDir.y = 0;
            camDir.normalizeLocal();

            // 根据摄像机方向，计算旋转角度
            camRot.lookAt(camDir, Vector3f.UNIT_Y);

            // 使用该旋转，改变行走方向。
            camRot.mult(walkDir, camDir);
            System.out.println(camDir);

            // 改变玩家的朝向
            player.setViewDirection(camDir);

            // 调整速度大小
            camDir.multLocal(0.1f);

            player.setWalkDirection(camDir);

        } else {
            camDir.set(0, 0, 0);
        }
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


    /**
     * 让角色走路
     *
     * @param dir
     */
    public void walk(Vector3f dir) {
        if (dir != null) {
            dir.normalizeLocal();
            walkDir.set(dir);
        }
    }
}
