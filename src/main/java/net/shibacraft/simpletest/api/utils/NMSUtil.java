package net.shibacraft.simpletest.api.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class NMSUtil {

    private Class<?> getNMSClass(String className) {
        try {
            return Class.forName("net.minecraft.server." +
                    Bukkit.getServer().getClass().getPackage().getName()
                            .split("\\.")[3] + "." + className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Object buildPacket(Location block, int status) {
        try {
            Constructor<?> blockConstructor = getNMSClass("BlockPosition").getConstructor(
                    int.class, int.class, int.class);
            Object blockPosition = blockConstructor.newInstance(block.getBlockX(), block.getBlockY(), block.getBlockZ());
            Constructor<?> packetConstructor = getNMSClass("PacketPlayOutBlockBreakAnimation")
                    .getConstructor(int.class, getNMSClass("BlockPosition"), int.class);
            return packetConstructor.newInstance(block.getBlockX(), blockPosition, status);
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException |
                NoSuchMethodException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void sendPacket(Location block, int status) {
        try {
            Object packet = buildPacket(block, status);
            for (Player player : Bukkit.getOnlinePlayers()) {
                Object handle = player.getClass().getMethod("getHandle").invoke(player);
                Object playerConnection = handle.getClass().getField("playerConnection").get(handle);
                playerConnection.getClass().getMethod("sendPacket", getNMSClass("Packet"))
                        .invoke(playerConnection, packet);
            }
        } catch (NoSuchMethodException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

}
