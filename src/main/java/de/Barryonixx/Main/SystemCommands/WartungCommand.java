package de.Barryonixx.Main.SystemCommands;

import de.Barryonixx.Main.Data;
import de.Barryonixx.Main.FileManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class WartungCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player = (Player) sender;

        if(command.getName().equalsIgnoreCase("wartung")){
            if(player.hasPermission("skylydra.wartung") || player.isOp()){
                if(args.length == 1){
                    if(args[0].equalsIgnoreCase("an")){

                        Data.wartung = true;

                        if(FileManager.cfgFile.exists()){
                            if(FileManager.cfg != null){
                                FileManager.cfg.set("Plugin.Wartung", true);
                                FileManager.saveAllFiles();
                            }

                        }

                        player.sendMessage(Data.prefix + "Der §9Wartungs-Modus §7ist §a§l§oAN");
                    }
                    if(args[0].equalsIgnoreCase("aus")){

                        Data.wartung = false;

                        if(FileManager.cfgFile.exists()){
                            if(FileManager.cfg != null){
                                FileManager.cfg.set("Plugin.Wartung", false);
                                FileManager.saveAllFiles();
                            }

                        }

                        player.sendMessage(Data.prefix + "Der §9Wartungs-Modus §7ist §c§l§oAUS");

                    }
                }else{
                    player.sendMessage(Data.prefix+"Nutze§8: §7/wartung §8(§7an§8 | §7aus§8)");
                }
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        ArrayList<String> complets = new ArrayList<>();
        complets.add("an");
        complets.add("aus");
        return complets;
    }
}
