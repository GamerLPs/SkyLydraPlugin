package de.Barryonixx.Main.SystemCommands;

import de.Barryonixx.Main.Data;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TeleportHereCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;
        if (command.getName().equalsIgnoreCase("tphere")) {
            if (p.hasPermission("skylydra.teleport.here")) {
                if (args.length == 1) {
                    Player target = Bukkit.getPlayer(args[0]);
                    p.sendMessage(Data.prefix+"§e" + target.getName() + " §7ist nun bei dir.");
                    target.teleport(p);
                    target.sendMessage(Data.prefix+"§7Du bist nun bei §e" + p.getName());
                } else {
                    p.sendMessage(Data.prefix+"§7Nutze§8: §7/tphere §8(§7Spieler§8)");
                }
            } else {
                p.sendMessage(Data.prefix+Data.NoPerm);
            }
        }

        return false;
    }
}
