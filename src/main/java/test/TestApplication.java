package test;

import com.jme3.app.SimpleApplication;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Spatial;

public class TestApplication extends SimpleApplication {

    public void simpleInitApp() {
        Vector3f loc = new Vector3f(0,0,7f);

        Vector3f left = new Vector3f(-4,0,0);

        Vector3f up = new Vector3f(0,1,0);

        Vector3f dir = new Vector3f(0,0,-1);

        cam.setFrame(loc, left, up, dir);

        // 添加平行光
        DirectionalLight sun = new DirectionalLight();
        sun.setColor(new ColorRGBA(8f, 7f, 7f, 0f));
        sun.setDirection(new Vector3f(-3, -4, -5).normalizeLocal());
        rootNode.addLight(sun);
        // 添加环境光
        AmbientLight ambient = new AmbientLight();
        ambient.setColor(new ColorRGBA(3f, 6f, 7f, 0f));
        rootNode.addLight(ambient);


        // 导入模型
        final Spatial model = assetManager.loadModel("models/env/scene.gltf");
        model.scale(0.002f);// 按比例缩小
       // model.center();// 将模型的中心移到原点
        model.setLocalTranslation(0,-2f,0);
        // 将模型添加到场景图中。
        rootNode.attachChild(model);

    }

    public static void main(String[] args) {
        // 启动jME3程序
        TestApplication app = new TestApplication();
        app.start();
    }
}
