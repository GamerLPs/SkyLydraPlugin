package de.Barryonixx.Main.SystemCommands;

import de.Barryonixx.Main.Data;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class KickCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String lable, String[] args) {

        if(sender instanceof Player){
            Player p = (Player)sender;
            if (cmd.getName().equalsIgnoreCase("kick")) {
                if (p.hasPermission("skylydra.kick")) {
                    if ((args.length == 0) || (args.length == 1)) {
                        p.sendMessage(Data.prefix+"§7Nutze§8: §7/kick §8(§7Spieler§8) §8(§7Grund§8)");
                    } else {
                        Player t = Bukkit.getPlayer(args[0]);
                        if (t != null) {
                            if (!t.hasPermission("*")) {
                                String msg = "";
                                Integer count = Integer.valueOf(1);
                                while (count.intValue() < args.length) {
                                    msg = String.valueOf(msg) + " " + args[count.intValue()];
                                    count = Integer.valueOf(count.intValue() + 1);
                                }
                                t.kickPlayer(Data.prefix+"Kick-Info§8 « \n §7Du wurdest von §e"+p.getName()+ " §7gekickt!§7\n\n§aGrund:\n§7" + msg);
                                for (Player all : Bukkit.getOnlinePlayers())
                                    if (all.hasPermission("system.kick")) {
                                        all.sendMessage(Data.prefix+"§c" + t.getName() + " §7wurde von §a" + p.getName() + " §7gekickt!");
                                        all.sendMessage("§7Grund: §9" + msg);
                                    }
                            } else {
                                p.sendMessage(Data.prefix+"Du darfst keine §eAdministratoren §7kicken!");
                            }
                        }
                        else p.sendMessage(Data.prefix+"§cFehler: §7Spieler nicht gefunden.");
                    }
                }
                else {
                    p.sendMessage(Data.prefix+Data.NoPerm);
                }
            }
        }

        return false;
    }
}
