package de.Barryonixx.Main.SystemCommands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FreeBuildCommand implements CommandExecutor{
    private final String WORLDNAME = "Freebuild";
    public String fp = "§8| §b§lFREEBUILD §8» §7";



    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)){
            sender.sendMessage("§cDu kannst diesen Command nur als Spieler ausführen!");
            return false;
        }

        Player player = (Player) sender;
        player.sendMessage(fp+"Du wurdest zu §eFreebuild §7teleportiert!");
        player.teleport(Bukkit.getWorld(WORLDNAME).getSpawnLocation());
        return true;
    }
}
