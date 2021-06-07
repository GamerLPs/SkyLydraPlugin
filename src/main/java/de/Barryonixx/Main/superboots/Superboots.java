package de.Barryonixx.Main.superboots;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Superboots implements Listener {
    public static final String SUPER_SPEED_BOOTS_NAME = "§eSpeed Boots";
    public static final String SUPER_JUMP_BOOTS_NAME = "§2Jump Boots";


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
    }

}
