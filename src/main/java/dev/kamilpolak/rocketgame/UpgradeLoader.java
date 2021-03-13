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

public class UpgradeLoader extends AsynchronousAssetLoader<UpgradeData, UpgradeLoader.UpgradeParameter> {
    UpgradeData data;
    private static final XmlReader xmlReader = new XmlReader();

    public UpgradeLoader(FileHandleResolver resolver) {
        super(resolver);
    }

    @Override
    public void loadAsync(AssetManager manager, String fileName, FileHandle file, UpgradeParameter parameter) {
        data = null;
        XmlReader.Element upgradeElement = xmlReader.parse(file);
        String name = upgradeElement.getChildByName("name").getText();
        int price = Integer.parseInt(upgradeElement.getChildByName("price").getText());
        String description = upgradeElement.getChildByName("description").getText();
        data = new UpgradeData(name, price, description);
    }

    @Override
    public UpgradeData loadSync(AssetManager assetManager, String s, FileHandle fileHandle, UpgradeParameter upgradeParameter) {
        UpgradeData data = this.data;
        this.data = null;
        return data;
    }

    @Override
    public Array<AssetDescriptor> getDependencies(String s, FileHandle fileHandle, UpgradeParameter upgradeParameter) {
        return null;
    }

    static public class UpgradeParameter extends AssetLoaderParameters<UpgradeData> {

    }
}
