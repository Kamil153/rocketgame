package dev.kamilpolak.rocketgame;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.XmlReader;

import java.util.ArrayList;

public class BodyLoader extends AsynchronousAssetLoader<BodyData, BodyLoader.BodyParameter> {
    BodyData data;
    private static final XmlReader xmlReader = new XmlReader();


    public BodyLoader(FileHandleResolver resolver) {
        super(resolver);
    }

    @Override
    public void loadAsync(AssetManager manager, String fileName, FileHandle file, BodyParameter parameter) {
        data = null;
        ArrayList<FixtureData> fixtures = new ArrayList<>();
        boolean dynamic = false;
        boolean allowSleep = false;
        XmlReader.Element bodyElement = xmlReader.parse(file);
        if(bodyElement.hasChild("is_dynamic")) {
            dynamic = true;
        }
        if(bodyElement.hasChild("allow_sleep")) {
            allowSleep = true;
        }
        float linearDamping = Float.parseFloat(bodyElement.getChildByName("linear_damping").getText());
        float angularDamping = Float.parseFloat(bodyElement.getChildByName("angular_damping").getText());
        for(XmlReader.Element fixtureElement: bodyElement.getChildrenByName("fixture")) {
            float density = Float.parseFloat(fixtureElement.getChildByName("density").getText());
            float friction = Float.parseFloat(fixtureElement.getChildByName("friction").getText());
            float restitution = Float.parseFloat(fixtureElement.getChildByName("restitution").getText());
            for(XmlReader.Element polygonElement: fixtureElement.getChildrenByName("polygon")) {
                String[] verticesStr = polygonElement.getText().split(", ");
                float[] vertices = new float[verticesStr.length];
                for(int i = 0; i < vertices.length; i++) {
                    vertices[i] = Float.parseFloat(verticesStr[i]);
                }
                fixtures.add(new FixtureData(density, friction, restitution, vertices));
            }
        }
        data = new BodyData(dynamic, linearDamping, angularDamping, allowSleep, fixtures);
    }

    @Override
    public BodyData loadSync(AssetManager manager, String fileName, FileHandle file, BodyParameter parameter) {
        BodyData data = this.data;
        this.data = null;
        return data;
    }

    @Override
    public Array<AssetDescriptor> getDependencies(String fileName, FileHandle file, BodyParameter parameter) {
        return null;
    }

    static public class BodyParameter extends AssetLoaderParameters<BodyData> {

    }
}
