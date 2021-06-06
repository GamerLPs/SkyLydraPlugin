package de.Barryonixx.Main.SystemCommands;

import de.Barryonixx.Main.Data;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ClearInvCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;
        if(command.getName().equalsIgnoreCase("Clearinventory")){
            if(p.hasPermission("skylydra.clearinv")){
                if(args.length  == 0) {
                    p.getInventory().clear();
                    p.sendMessage(Data.prefix + "Dein Inventar wurde geleert!");

                }else if(args.length == 1){

                    if(p.hasPermission("skylydra.clean.user")){

                        Player target = Bukkit.getPlayer(args[0]);
                        if (target != null) {
                            target.getInventory().clear();
                            target.sendMessage(Data.prefix+"Alle Inventargegenstände wurden entfernt.");
                        }
                        else p.sendMessage(Data.prefix+"§cFehler: §7Spieler nicht gefunden.");
                    }else{
                        p.sendMessage(Data.prefix+Data.NoPerm);
                    }
                }
            }else{
                p.sendMessage(Data.prefix+Data.NoPerm);
            }
        }
        return false;
    }
}
