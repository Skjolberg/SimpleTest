package net.shibacraft.simpletest.module;

import de.leonhard.storage.Yaml;
import net.shibacraft.simpletest.SimpleTest;
import net.shibacraft.simpletest.files.FileManager;
import net.shibacraft.simpletest.listeners.BlockBreakListener;
import net.shibacraft.simpletest.listeners.LegacyBlockBreakListener;
import net.shibacraft.simpletest.api.loader.Loader;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.PluginManager;
import net.shibacraft.simpletest.listeners.BlockDropItemListener;
import net.shibacraft.simpletest.listeners.PlayerJoinListener;

public class EventsModule implements Loader {

    private final SimpleTest plugin;
    private final PluginManager pluginManager;
    private final int version;
    private final BlockBreakListener blockBreakListener;
    private final Yaml config;

    public EventsModule(SimpleTest plugin){
        this.plugin = plugin;
        this.pluginManager = SimpleTest.getPlugin().getServer().getPluginManager();
        this.version = SimpleTest.getVERSION();
        this.blockBreakListener = new BlockBreakListener();
        this.config = FileManager.getFilesYaml().get("Config");
    }

    @Override
    public void load() {

        if (version >= 13) {
            BlockDropItemListener blockDropItemListener = new BlockDropItemListener();
            pluginManager.registerEvents(blockDropItemListener, plugin);
        } else {
            LegacyBlockBreakListener legacyBlockBreakListener = new LegacyBlockBreakListener();
            pluginManager.registerEvents(legacyBlockBreakListener, plugin);
        }

        if (config.getBoolean("Player-Join-Drop")) {
            PlayerJoinListener playerJoinListener = new PlayerJoinListener();
            pluginManager.registerEvents(playerJoinListener, plugin);
        }
        pluginManager.registerEvents(blockBreakListener, plugin);
        //pluginManager.registerEvents(new TestMethod(), plugin);
    }

    @Override
    public void unload() {
        HandlerList.unregisterAll(plugin);
    }

    @Override
    public void reload() {

    }


}
