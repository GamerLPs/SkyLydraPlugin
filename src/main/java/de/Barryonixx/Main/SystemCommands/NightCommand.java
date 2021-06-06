package de.Barryonixx.Main.SystemCommands;

import de.Barryonixx.Main.Data;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class NightCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player=(Player)sender;
        if(command.getName().equalsIgnoreCase("night")){
            if(player.hasPermission("skylydra.night")){
                player.getWorld().setTime(13000);
                player.sendMessage(Data.prefix+"Zeit wurde auf §cNacht §7gesetzt.");
            }else{
                player.sendMessage(Data.prefix+Data.NoPerm);
            }
        }


        return false;


    }
}
