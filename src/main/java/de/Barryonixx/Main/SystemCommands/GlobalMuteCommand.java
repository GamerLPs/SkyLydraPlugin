package de.Barryonixx.Main.SystemCommands;

import de.Barryonixx.Main.Data;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GlobalMuteCommand implements CommandExecutor  {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        if(sender instanceof Player) {
            Player p = (Player) sender;
        if (cmd.getName().equalsIgnoreCase("globalmute")) {
            if (p.hasPermission("system.globalmute")) {
                if (args.length == 0) {
                    if (!Data.globalmute)
                        for (Player all : Bukkit.getOnlinePlayers()) {
                            all.sendMessage(Data.prefix+"Der Globalmute wurde §aaktiviert§7!");
                            Data.globalmute = true;
                        }
                    else
                        for (Player all : Bukkit.getOnlinePlayers()) {
                            all.sendMessage(Data.prefix+"Der Globalmute wurde §cdeaktiviert§7!");
                            Data.globalmute = false;
                        }
                } else
                    p.sendMessage(Data.prefix+"Nutze§8: §7/globalmute");
            } else {
                p.sendMessage(Data.prefix+Data.NoPerm);
             }
            }
        }
        return false;
    }


}
