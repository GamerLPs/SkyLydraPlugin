package de.Barryonixx.Main.SystemCommands;

import de.Barryonixx.Main.Data;
import de.Barryonixx.Main.FileManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GesuchtCommand implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)){
            return false;
        }

        Player player = (Player) sender;

        int maxcount = FileManager.cfg.isSet("Groups.MaxCount") ? FileManager.cfg.getInt("Groups.MaxCount") + 1: 5;

        player.sendMessage(Data.prefix + " Gesucht wird:");

        for(int i = 0; i < maxcount; i++){
            if(i != 0){
                player.sendMessage("§7   " + FileManager.cfg.getString("Groups.ID" + i + ".Name") + " (§e"+FileManager.cfg.getInt("Groups.ID" + i + ".Count")+"x§7)");
                continue;
            }
        }

        return false;
    }
}
