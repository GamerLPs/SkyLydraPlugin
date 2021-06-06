package de.Barryonixx.Main.SystemCommands;

import de.Barryonixx.Main.Data;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DayCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player = (Player)sender;
        if(command.getName().equalsIgnoreCase("day")){
            if(player.hasPermission("skylydra.day")){
                player.getWorld().setTime(1000);
                player.sendMessage(Data.prefix+"Zeit wurde auf §cTag §7gesetzt.");
            }else{
                player.sendMessage(Data.prefix+Data.NoPerm);
            }
        }

        return false;
    }
}
