package de.Barryonixx.Main.SystemCommands;

import de.Barryonixx.Main.Data;
import de.Barryonixx.Main.SkyLydra;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HealCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (command.getName().equalsIgnoreCase("heal")) {
                if (player.hasPermission("skylydra.heal")) {
                    if (args.length == 0) {

                                player.setHealth(20D);
                                player.setFoodLevel(20);

                        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "lp user " + player.getName() + " permission unset skylydra.heal");
                                player.sendMessage("§8| §d§lVOTE §8» §7Du hast dich erfolgreich geheilt.");
                    }
                }else {
                    player.sendMessage("§8| §d§lVOTE §8» §7Dies ist eine Vote Belohnung. §e/vote");
                }
            }

        }
        return false;
    }

}
