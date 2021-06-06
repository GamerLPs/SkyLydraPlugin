package de.Barryonixx.Main.FreundeSystem;

import de.Barryonixx.Main.FileManager;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.ArrayList;
import java.util.List;

public class FriendListener implements Listener {
    private YamlConfiguration friends = FileManager.friends;


    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event){
        if(event.getEntity() instanceof Player && event.getDamager() instanceof Player){
            Player damager = (Player) event.getDamager();
            Player hurted = (Player) event.getEntity();

            List<String> friendsgot = null;
            if(friends.isSet(hurted.getName() + ".Friends")){
                friendsgot = friends.getStringList(hurted.getName() + ".Friends");
            }else{
                return;
            }

            if(friendsgot.contains(damager.getName())){
                //nachricht.
                event.setCancelled(true);
            }
        }
    }
}
