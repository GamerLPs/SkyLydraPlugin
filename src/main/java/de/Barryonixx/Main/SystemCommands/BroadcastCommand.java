package de.Barryonixx.Main.SystemCommands;

import de.Barryonixx.Main.Data;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BroadcastCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String lable, String[] args) {
        if(sender instanceof Player){
            Player p = (Player)sender;
            if(cmd.getName().equalsIgnoreCase("broadcast")){
                if(p.hasPermission("system.broadcast")){
                    if(args.length >= 1){
                        String msg = "";
                        for (int i = 0; i < args.length; i++){
                            msg = msg + args[i] + " ";
                        }
                        Bukkit.broadcastMessage("§7» §aBroadcast§7: "+ChatColor.translateAlternateColorCodes('&', msg));
                    }else{
                        sender.sendMessage(Data.prefix+"Nutze§8: §7/bc §8(§7Nachricht§8)");
                    }
                }else{
                    p.sendMessage(Data.prefix+Data.NoPerm);
                }

            }

        }
        return false;
    }
}
