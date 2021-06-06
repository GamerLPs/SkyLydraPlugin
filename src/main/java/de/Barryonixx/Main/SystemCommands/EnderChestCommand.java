package de.Barryonixx.Main.SystemCommands;

import de.Barryonixx.Main.Data;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EnderChestCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player)sender;
        if(command.getName().equalsIgnoreCase("enderchest")){
            if(p.hasPermission("skylydra.enderchest")){
                p.openInventory(p.getEnderChest());
            }else{
                p.sendMessage(Data.prefix+Data.NoPerm);
            }

            if(args.length == 1){
                Player p2 = Bukkit.getPlayer(args[0]);
                if(p.hasPermission("skylydra.enderchest.spieler")){
                    p.openInventory(p2.getEnderChest());
                }else{
                    p.sendMessage(Data.prefix+Data.NoPerm);
                }
            }
        }
        return false;
    }
}
