package net.shibacraft.simpletest.module;

import de.leonhard.storage.Yaml;
import net.shibacraft.simpletest.api.loader.Loader;
import net.shibacraft.simpletest.files.FileManager;
import net.shibacraft.simpletest.files.playerData.PlayerDataManager;

public class StorageModule implements Loader {

    private PlayerDataManager playerDataManager;
    private final Yaml config;

    public StorageModule(){
        config = FileManager.getFilesYaml().get("Config");
    }

    @Override
    public void load() {
        if(config.getBoolean("Save-PlayerData")){
            playerDataManager = new PlayerDataManager();
            playerDataManager.load();
        }

    }

    @Override
    public void unload() {
        if(config.getBoolean("Save-PlayerData")){
            playerDataManager.unload();
        }
    }

    @Override
    public void reload() {

    }
}
