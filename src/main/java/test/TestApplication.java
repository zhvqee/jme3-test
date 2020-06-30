package test;

import com.jme3.app.SimpleApplication;
import com.jme3.bullet.control.CharacterControl;
import com.jme3.input.ChaseCamera;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Mesh;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Sphere;

public class TestApplication extends SimpleApplication {


    private Material newLightingMaterial(ColorRGBA color) {
        // 创建材质
        Material mat = new Material(assetManager, "Common/MatDefs/Light/Lighting.j3md");

        mat.setColor("Diffuse", color);
        mat.setColor("Ambient", color);
        mat.setColor("Specular", ColorRGBA.White);
        mat.setFloat("Shininess", 24);
        mat.setBoolean("UseMaterialColors", true);

        return mat;
    }

    /**
     * 角色的尺寸
     */
    private float radius = 0.3f;// 胶囊半径0.3米
    private float height = 1.8f;// 胶囊身高1.8米
    private float stepHeight = 0.5f;// 角色步高0.5米

    /**
     * 角色相关模型
     */
    private Node character;
    private Spatial model;// 角色模型
    private Node camNode;// 辅助摄像机节点
    private CharacterControl player;// 角色控制器

    public void simpleInitApp() {
       /* Vector3f loc = new Vector3f(0,0,7f);

        Vector3f left = new Vector3f(-4,0,0);

        Vector3f up = new Vector3f(0,1,0);

        Vector3f dir = new Vector3f(0,0,-1);

        cam.setFrame(loc, left, up, dir);*/

        // 添加平行光
        DirectionalLight sun = new DirectionalLight();
        sun.setColor(new ColorRGBA(8f, 7f, 7f, 0f));
        sun.setDirection(new Vector3f(-3, -4, -5).normalizeLocal());
        rootNode.addLight(sun);
        // 添加环境光
        AmbientLight ambient = new AmbientLight();
        ambient.setColor(new ColorRGBA(3f, 6f, 7f, 0f));
        rootNode.addLight(ambient);

        flyCam.setDragToRotate(true);

        // 导入模型
        final Spatial model = assetManager.loadModel("/models/env/scene.gltf");
        model.scale(0.002f);// 按比例缩小
       // model.center();// 将模型的中心移到原点
        model.setLocalTranslation(0,-2f,0);
        // 将模型添加到场景图中。
        rootNode.attachChild(model);

        initCharacter();
        initChaseCamera();

    }

    public static void main(String[] args) {
        // 启动jME3程序
        TestApplication app = new TestApplication();
        app.start();
    }

    /**
     * 初始化角色节点
     */
    private void initCharacter() {
        this.character = new Node("Character");
        character.setLocalTranslation(0, height / 2 + radius, 0);

        // 球体网格
        Mesh mesh = new Sphere(16, 24, 1);

        // 创建2个球体
        Geometry geomA = new Geometry("红色气球", mesh);
        geomA.setMaterial(newLightingMaterial(ColorRGBA.Red));

        this.model=geomA;
        //this.model = assetManager.loadModel("Models/Jaime/Jaime.j3o");
        character.attachChild(model);// 挂到角色根节点下

        model.setLocalTranslation(0, -(height / 2 + radius), 0);
        model.scale(1.8f);

        // 创造一个辅助节点，用于修正摄像机的位置。
        this.camNode = new Node("Camera");
        character.attachChild(camNode);
        camNode.setLocalTranslation(0, height / 2, radius);// 将此节点上移一段距离，使摄像机位于角色的头部。
    }

    /**
     * 第三人称摄像机
     */
    private void initChaseCamera() {
        ChaseCamera chaseCam = new ChaseCamera(cam, camNode, inputManager);
        chaseCam.setInvertVerticalAxis(true);// 垂直反转
        chaseCam.setMinDistance(0.1f);// 相机离焦点的最近距离
        chaseCam.setDefaultDistance(10f);// 默认距离
    }
}
