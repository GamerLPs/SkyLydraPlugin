package de.Barryonixx.Main.SystemCommands;

import de.Barryonixx.Main.Data;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class HatCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;
        if(command.getName().equalsIgnoreCase("hat")){
            if(p.hasPermission("skylydra.hat")){
                PlayerInventory inv = p.getInventory();
                ItemStack held = inv.getItemInHand();
                ItemStack helm = inv.getHelmet();
            if((held.getAmount() == 1) || (held.getType() == Material.AIR)) {
                if (held.getType() != Material.AIR) {
                    inv.setHelmet(held);
                    inv.setItemInHand(helm);
                    p.updateInventory();
                    p.sendMessage(Data.prefix + "Viel Spaß mit deinem neuen Kopf.");
                }else{
                    p.sendMessage(Data.prefix+"Nimm ein Item in die Hand.");
                }
            } else {
                p.sendMessage(Data.prefix + "Nimm nur x1 Item in die Hand.");
            }
            }else{
                p.sendMessage(Data.prefix+Data.NoPerm);
            }
        }

        return false;
    }
}
