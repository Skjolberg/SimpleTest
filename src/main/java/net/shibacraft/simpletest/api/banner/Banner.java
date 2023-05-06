package net.shibacraft.simpletest.api.banner;

import net.shibacraft.simpletest.SimpleTest;
import net.shibacraft.simpletest.api.loader.Loader;
import net.shibacraft.simpletest.api.logger.CoreLogger;

public class Banner implements Loader {

    private final SimpleTest plugin;

    public Banner(SimpleTest plugin){
        this.plugin = plugin;
    }

    @Override
    public void load() {
        CoreLogger.info("&bPlugin: &e" + plugin.getName());
        CoreLogger.info("&fAuthor: &e" + plugin.getDescription().getAuthors().get(0));
        CoreLogger.info("&fVersion: &e" + plugin.getDescription().getVersion());
    }

    @Override
    public void unload() {

    }

    @Override
    public void reload() {

    }

}
