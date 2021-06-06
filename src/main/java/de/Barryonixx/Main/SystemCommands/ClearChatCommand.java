package de.Barryonixx.Main.SystemCommands;

import de.Barryonixx.Main.Data;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ClearChatCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;
        if (command.getName().equalsIgnoreCase("clearchat")) {
            if (p.hasPermission("skylydra.clearchat")) {
                if (args.length == 0) {
                    for (int i = 0; i < 100; i++) {
                        Bukkit.broadcastMessage("");
                    }
                    Bukkit.broadcastMessage(Data.prefix + "Der Chat wurde erfolgreich geleert.");
                } else {
                    p.sendMessage(Data.prefix+"Nutze§8: §7/clearchat");
                }
            } else {

                p.sendMessage(Data.prefix + Data.NoPerm);
            }
        }

        return false;
    }
}
