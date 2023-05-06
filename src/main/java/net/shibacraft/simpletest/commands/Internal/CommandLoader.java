package net.shibacraft.simpletest.commands.Internal;

import me.fixeddev.commandflow.CommandManager;
import me.fixeddev.commandflow.annotated.AnnotatedCommandTreeBuilder;
import me.fixeddev.commandflow.annotated.AnnotatedCommandTreeBuilderImpl;
import me.fixeddev.commandflow.annotated.part.PartInjector;
import me.fixeddev.commandflow.annotated.part.SimplePartInjector;
import me.fixeddev.commandflow.annotated.part.defaults.DefaultsModule;
import me.fixeddev.commandflow.bukkit.BukkitCommandManager;
import me.fixeddev.commandflow.bukkit.factory.BukkitModule;
import me.fixeddev.commandflow.translator.DefaultTranslator;
import net.shibacraft.simpletest.SimpleTest;
import net.shibacraft.simpletest.api.loader.Loader;
import net.shibacraft.simpletest.commands.MainCommand;

public class CommandLoader implements Loader {

    private final CommandManager commandManager;

    public CommandLoader() {
        this.commandManager = new BukkitCommandManager(SimpleTest.getPlugin().getName());
        commandManager.setTranslator(new DefaultTranslator(new CommandTranslatorProvider()));
    }

    @Override
    public void load() {
        PartInjector partInjector = new SimplePartInjector();
        partInjector.install(new DefaultsModule());
        partInjector.install(new BukkitModule());

        AnnotatedCommandTreeBuilder treeBuilder = new AnnotatedCommandTreeBuilderImpl(partInjector);

        commandManager.registerCommands(treeBuilder.fromClass(new MainCommand()));


    }

    @Override
    public void unload() {
        commandManager.unregisterAll();
    }

    @Override
    public void reload() {

    }

}
