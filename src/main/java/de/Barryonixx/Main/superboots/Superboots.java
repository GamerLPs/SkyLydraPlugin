package de.Barryonixx.Main.superboots;

import de.Barryonixx.Main.SkyLydra;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class Superboots implements Listener {
    public static final String SUPER_SPEED_BOOTS_NAME = "§eSpeed Boots";
    public static final String SUPER_JUMP_BOOTS_NAME = "§2Jump Boots";
    public static final String DEVELOPER_BOOTS_NAME = "DevBoots";


    /*
    * Nur den Effekt verändern! Niemals die Duration ändern, da der Effekt entweder
    * jede Sekunde neu applied wird, oder direkt gecleart wird!
    *
    * Der Amplifier kann jederzeit verändert werden, um die stärke des jeweiligen Effekts einzustellen
    * */
    private final PotionEffect speed = new PotionEffect(PotionEffectType.SPEED, 4, 2);
    private final PotionEffect jump = new PotionEffect(PotionEffectType.JUMP, 4, 2);

    @EventHandler
    public void onMove(PlayerMoveEvent event){
        Player player = event.getPlayer();

        if(player.getInventory().getBoots() == null){
            return;
        }

        if(player.getInventory().getBoots().getItemMeta() == null){
            return;
        }

        if(player.getInventory().getBoots().getItemMeta().getDisplayName().equals(SUPER_SPEED_BOOTS_NAME) && player.getInventory().getBoots().getType().equals(Material.DIAMOND_BOOTS)){
            player.addPotionEffect(speed);
        }else{
            if(player.hasPotionEffect(PotionEffectType.SPEED)){
                player.removePotionEffect(PotionEffectType.SPEED);
            }
        }

        if(player.getInventory().getBoots().getItemMeta().getDisplayName().equals(SUPER_JUMP_BOOTS_NAME) && player.getInventory().getBoots().getType().equals(Material.DIAMOND_BOOTS)){
            player.addPotionEffect(jump);
        }else{
            if(player.hasPotionEffect(PotionEffectType.JUMP)){
                player.removePotionEffect(PotionEffectType.JUMP);
            }
        }


        if(player.getInventory().getBoots().getItemMeta().getDisplayName().equals(DEVELOPER_BOOTS_NAME) && player.getInventory().getBoots().getType().equals(Material.DIAMOND_BOOTS)){
            Block b = player.getLocation().getBlock().getRelative(BlockFace.DOWN);


            Location location = b.getLocation();
            int radius = 3;

            for(int x = location.getBlockX() - radius; x <= location.getBlockX() + radius; x++) {
                for(int z = location.getBlockZ() - radius; z <= location.getBlockZ() + radius; z++) {
                    Block block = location.getWorld().getBlockAt(x, location.getBlockY(), z);

                    if(player.isSneaking()){
                        if(block.getType() == Material.ICE){
                            block.setType(Material.AIR);
                        }
                    }else{
                        if(block.getType() == Material.AIR){
                            block.setType(Material.ICE);
                        }
                    }

                    new BukkitRunnable(){
                        @Override
                        public void run() {
                            if(block.getType() == Material.ICE){
                                block.setType(Material.AIR);
                            }
                        }
                    }.runTaskLater(SkyLydra.getInstance(), 60);
                }
            }


        }
    }

}
