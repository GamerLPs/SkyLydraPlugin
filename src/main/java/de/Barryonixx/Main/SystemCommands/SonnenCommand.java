package de.Barryonixx.Main.SystemCommands;

import de.Barryonixx.Main.Data;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SonnenCommand implements
        CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player)sender;
        if(command.getName().equalsIgnoreCase("sun")){
            if(sender.hasPermission("skylydra.sun")){
                p.getWorld().setStorm(false);
                p.sendMessage(Data.prefix  + "Es scheint nun die §cSonne §7wieder");
            }else{
                sender.sendMessage(Data.prefix+Data.NoPerm);
            }
        }
        return false;
    }
}
