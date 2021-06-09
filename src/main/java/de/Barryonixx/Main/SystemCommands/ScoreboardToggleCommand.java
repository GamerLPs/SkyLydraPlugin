package de.Barryonixx.Main.SystemCommands;

import de.Barryonixx.Main.ScorboardPack.MainScoreBoard;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;

import java.util.ArrayList;
import java.util.List;

public class ScoreboardToggleCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) return false;

        Player player = (Player) sender;

        if(args[0].equalsIgnoreCase("on")){
            new MainScoreBoard(player);
        }

        if(args[0].equalsIgnoreCase("off")){
            player.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> complets = new ArrayList<>();

        if(args.length == 1){
            complets.add("off");
            complets.add("on");

            return complets;
        }
        return null;
    }
}
