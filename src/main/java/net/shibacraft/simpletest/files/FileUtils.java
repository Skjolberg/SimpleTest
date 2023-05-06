package net.shibacraft.simpletest.files;

import net.shibacraft.simpletest.SimpleTest;

import java.io.File;
import java.util.Set;

public class FileUtils {

    static SimpleTest plugin = SimpleTest.getPlugin();

    public static void createFileIfNotExists(String file){
        File fileCheck = new File(plugin.getDataFolder(), file);

        if (!fileCheck.exists()) {
            plugin.saveResource(file, false);
        }
    }

    public static void checkFiles(Set<String> filesCheck){

        for (String file : filesCheck) {
            createFileIfNotExists(file);
        }

    }

}
