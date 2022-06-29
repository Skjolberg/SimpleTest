package net.shibacraft.simpledropinventory.commands;

import lombok.Getter;
import me.fixeddev.commandflow.annotated.CommandClass;
import me.fixeddev.commandflow.annotated.annotation.Command;
import me.fixeddev.commandflow.bukkit.annotation.Sender;
import net.shibacraft.simpledropinventory.commands.Internal.CommandTranslatorProvider;
import net.shibacraft.simpledropinventory.files.messages.Messages;
import net.shibacraft.simpledropinventory.module.MainModule;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Command(names = {"sdi", "simpledropinventory"}, permission = "sdi.use", desc = "SimpleDropInventory main command")
public class MainCommand implements CommandClass {

    @Getter
    public static final Set<UUID> drop = new HashSet<>();
    private final MainModule mainModule = MainModule.getMainModule();

    @Command(names = "")
    public void onMainCommand(@Sender CommandSender sender) {
        sender.sendMessage("It needs arguments.");
    }

    @Command(names = "reload", permission = "sdi.admin")
    public void onReloadCommand(@Sender CommandSender sender) {
        mainModule.reload();
        CommandTranslatorProvider.commandTranslatorProvider.reload();
        sender.sendMessage(Messages.RELOAD.get());
    }

    @Command(names = "drop")
    public void onBypassCommand(@Sender Player player) {

        if (drop.contains(player.getUniqueId())) {
            drop.remove(player.getUniqueId());
            player.sendMessage(Messages.DROP_OFF.get());
        } else {
            drop.add(player.getUniqueId());
            player.sendMessage(Messages.DROP_ON.get());
        }

    }
}
