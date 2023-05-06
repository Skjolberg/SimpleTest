package net.shibacraft.simpletest;

import lombok.Getter;
import net.shibacraft.api.SimpleApi;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import net.shibacraft.simpletest.module.MainModule;

public class SimpleTest extends JavaPlugin {

    @Getter
    static SimpleTest plugin;
    @Getter
    static int VERSION;
    private MainModule mainModule;

    @Override
    public void onLoad() {
        ManagerLib managerLib = new ManagerLib(this);
        managerLib.load();
    }

    @Override
    public void onEnable() {
        plugin = this;
        mainModule = new MainModule(this);
        mainModule.load();


        Bukkit.getScheduler().runTaskTimer(plugin, () -> {
            System.out.println(SimpleApi.getSimpleCore().getMessage());
        }, 30, 30);

        Bukkit.getScheduler().runTaskTimer(plugin, () -> {
            System.out.println(SimpleApi.getSimpleRegion().isRegion());
        }, 30, 30);

    }



    @Override
    public void onDisable() {
        mainModule.unload();
    }

}
