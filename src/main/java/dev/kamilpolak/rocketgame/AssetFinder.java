package dev.kamilpolak.rocketgame;

import com.badlogic.gdx.files.FileHandle;

public class AssetFinder {
    FileHandle directory;

    public AssetFinder(FileHandle directory) {
        if(!directory.isDirectory()) {
            throw new IllegalArgumentException("Provided FileHandle must be a directory");
        }
        this.directory = directory;
    }
}
