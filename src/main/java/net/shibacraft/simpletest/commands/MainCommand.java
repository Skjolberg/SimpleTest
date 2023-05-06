package net.shibacraft.simpletest.commands;

import lombok.Getter;
import me.fixeddev.commandflow.annotated.CommandClass;
import me.fixeddev.commandflow.annotated.annotation.Command;
import me.fixeddev.commandflow.annotated.annotation.Named;
import me.fixeddev.commandflow.bukkit.annotation.Sender;
import net.shibacraft.api.SimpleApi;
import net.shibacraft.simpletest.files.messages.Messages;
import net.shibacraft.simpletest.module.MainModule;
import org.bukkit.command.CommandSender;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Command(names = {"st", "simpletest"}, desc = "SimpleTest main command")
public class MainCommand implements CommandClass {

    @Getter
    public static final Set<UUID> drop = new HashSet<>();
    private final MainModule mainModule;

    public MainCommand(){
        mainModule = MainModule.getMainModule();
    }

    @Command(names = "")
    public void onMainCommand(@Sender CommandSender sender) {
        sender.sendMessage("It needs arguments.");
    }

    @Command(names = "reload")
    public void onReloadCommand(@Sender CommandSender sender) {
        mainModule.reload();
        sender.sendMessage(Messages.RELOAD.get());
    }

    /*@Command(names = "apiget")
    public void onApiCommand(@Sender CommandSender sender) {
        sender.sendMessage("Mensaje desde la API: " + SimpleApi.getSimpleCore().getMessage());
    }

    @Command(names = "apiset")
    public void onApiCommand(@Sender CommandSender sender, @Named("value") String value) {
        SimpleApi.getSimpleCore().setMessage(value);
    }*/


}
