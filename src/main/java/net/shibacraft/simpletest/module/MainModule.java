package net.shibacraft.simpletest.module;

import lombok.Getter;
import net.shibacraft.simpletest.SimpleTest;
import net.shibacraft.simpletest.api.analytics.Updater;
import net.shibacraft.simpletest.api.banner.Banner;
import net.shibacraft.simpletest.files.FileManager;
import net.shibacraft.simpletest.api.analytics.MetricsProvider;
import net.shibacraft.simpletest.commands.Internal.CommandLoader;
import net.shibacraft.simpletest.commands.Internal.CommandTranslatorProvider;
import net.shibacraft.simpletest.api.loader.Loader;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;

import java.util.List;

import static org.bukkit.Bukkit.getServer;

public class MainModule implements Loader {

    @Getter
    private static MainModule mainModule;
    private StorageModule storageModule;
    private FileManager fileManager;
    private EventsModule eventsModule;
    private final SimpleTest plugin;

    public MainModule(SimpleTest plugin){
        this.plugin = plugin;
    }

    @Override
    public void load() {
        mainModule = this;


        Plugin sbr = Bukkit.getPluginManager().getPlugin("SimplePlugin");

        if(sbr != null) {
            System.out.println("No es null");
        } else {
            System.out.println("Es null");
        }




        final Banner banner = new Banner(plugin);
        banner.load();

        fileManager = new FileManager(plugin);
        fileManager.load();

        storageModule = new StorageModule();
        storageModule.load();

        final CommandLoader commandLoader = new CommandLoader();
        commandLoader.load();

        eventsModule = new EventsModule(plugin);
        eventsModule.load();

        /*final Updater updater = new Updater(plugin);
        updater.load();

        final MetricsProvider metrics = new MetricsProvider(plugin);
        metrics.load();*/

    }

    @Override
    public void unload() {
        fileManager.unload();
        storageModule.unload();
        eventsModule.unload();
    }

    @Override
    public void reload() {
        fileManager.reload();
        eventsModule.reload();
        CommandTranslatorProvider.commandTranslatorProvider.reload();
    }

}
