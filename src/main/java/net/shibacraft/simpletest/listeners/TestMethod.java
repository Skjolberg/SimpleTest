package net.shibacraft.simpletest.listeners;

import net.shibacraft.simpletest.SimpleTest;
import net.shibacraft.simpletest.api.utils.NMSUtil;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class TestMethod implements Listener {


    @EventHandler(priority = EventPriority.HIGHEST)
    public void onInteract (PlayerInteractEvent event){
        event.getPlayer().sendMessage("Entra al evento");
        if(!event.hasItem()) return;
        if(!event.hasBlock()) return;
        if(event.getItem().getType() != Material.CHEST) return;
        if(event.getClickedBlock().getType() != Material.COBBLESTONE) return;

        Block chestBlock = event.getClickedBlock().getLocation().add(0, 1, 0).getBlock();
        if(chestBlock.getType() != Material.AIR) return;

        event.setCancelled(true);
        ItemStack fee = new ItemStack(Material.CHEST, 1);
        event.getPlayer().getInventory().remove(fee);
        chestBlock.setType(Material.CHEST);
        executeAutoBreaker(event.getClickedBlock());
        event.getPlayer().sendMessage("Evento ejecutado");
    }

    private void executeAutoBreaker(Block cobble) {
        NMSUtil nmsUtil = new NMSUtil();
        new BukkitRunnable(){
            int status = 6;
            Block chestBlock = cobble.getLocation().add(0, 1, 0).getBlock();
            Chest chest = (Chest) chestBlock.getState();
            public void run(){
                if(cobble.getType() == Material.AIR) return;
                if(chestBlock.getType() != Material.CHEST){
                    cobble.setType(Material.COBBLESTONE);
                    nmsUtil.sendPacket(cobble.getLocation(), 10);
                    cancel();
                    return;
                }
                if(status == 0){
                    cobble.setType(Material.AIR);
                    chest.getInventory().addItem(new ItemStack(Material.COBBLESTONE));
                    status = 6;
                    return;
                }
                nmsUtil.sendPacket(cobble.getLocation(), status);
                status--;
            }
        }.runTaskTimer(SimpleTest.getPlugin(), 1, 10);

    }

}
