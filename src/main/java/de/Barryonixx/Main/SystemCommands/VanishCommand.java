package de.Barryonixx.Main.SystemCommands;

import de.Barryonixx.Main.Data;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class VanishCommand implements CommandExecutor {

    ArrayList<Player> vanish = new ArrayList();

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        Player p = (Player)sender;
        if (p.hasPermission("system.vanish")) {
            if (args.length == 0)
                if (!this.vanish.contains(p)) {
                    for (Player all : Bukkit.getOnlinePlayers()) {
                        if (all.hasPermission("system.vanish"))
                            all.sendMessage("§e" + p.getName() + " §7ist nun im Vanish");
                        else {
                            all.hidePlayer(p);
                        }
                    }
                    this.vanish.add(p);
                } else {
                    for (Player all : Bukkit.getOnlinePlayers()) {
                        if (all.hasPermission("system.vanish"))
                            all.sendMessage("§e" + p.getName() + " §7ist nun nicht mehr im Vanish");
                        else {
                            all.showPlayer(p);
                        }
                    }
                    this.vanish.remove(p);
                }
        }
        else {
            p.sendMessage(Data.prefix+Data.NoPerm);
        }
        return false;
    }
}
