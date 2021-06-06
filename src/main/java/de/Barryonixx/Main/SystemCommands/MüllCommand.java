package de.Barryonixx.Main.SystemCommands;

import de.Barryonixx.Main.Data;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class MüllCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

            Player p = (Player)sender;
            if(command.getName().equalsIgnoreCase("müll")){
                Inventory invMüll = Bukkit.getServer().createInventory(p, 45, Data.prefix+"§cMülleimer");
                p.openInventory(invMüll);
            }

        return false;

    }
}
