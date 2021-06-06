package de.Barryonixx.Main.SystemCommands;

import de.Barryonixx.Main.Data;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TeleportCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        Player p = (Player) sender;
        if (cmd.getName().equalsIgnoreCase("teleport")) {
            if (p.hasPermission("skylydra.teleport")) {
                if (args.length == 1) {
                    Player target = Bukkit.getPlayer(args[0]);
                    if (target != null) {
                        p.sendMessage(Data.prefix+"§7Du bist nun bei §e" + target.getName());
                        p.teleport(target.getLocation());
                    } else {
                        p.sendMessage(Data.prefix+"§cFehler: §7Der Spieler nicht gefunden.");
                    }
                } else {
                    p.sendMessage(Data.prefix+"§7Nutze§8: §7/tp §8(§7Spieler§8)");
                }
            } else {
                p.sendMessage(Data.prefix + Data.NoPerm);
            }

        }

        return false;
    }
}
