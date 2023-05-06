package net.shibacraft.simpletest.api.logger;

import lombok.experimental.UtilityClass;
import net.shibacraft.simpletest.api.chat.TextColor;
import org.bukkit.Bukkit;

@UtilityClass
public class CoreLogger {

    public final String LOGGER_NAME = "[SimpleTest]";

    public void info(String message) {
        Bukkit.getConsoleSender().sendMessage(TextColor.color(LOGGER_NAME + "&7 | &f" + message));
    }

    public void warn(String message) {
        Bukkit.getConsoleSender().sendMessage(TextColor.color(LOGGER_NAME + "&7 | &e" + message));
    }

    public void severe(String message) {
        Bukkit.getConsoleSender().sendMessage(TextColor.color(LOGGER_NAME + "&7 | &c" + message));
    }

}
