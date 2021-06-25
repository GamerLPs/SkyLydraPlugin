package de.Barryonixx.Main.umfrage;

import de.Barryonixx.Main.Data;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Inventory30SecVotingCMD implements CommandExecutor, TabCompleter {
    private final String PERMISSION_NAME = "skylydra.voting";

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)){
            sender.sendMessage("§cDu musst diesen Command als Spieler ausführen!");
            return false;
        }

        Player player = (Player) sender;

        if(!player.hasPermission(PERMISSION_NAME)){
            player.sendMessage(Data.NoPerm);
            return false;
        }

        if(!(args.length >= 2)){
            sendUsage(player);
            return false;
        }


        VotingType votingType;

        if(args[0].equalsIgnoreCase("public")){
            votingType = VotingType.publicvoting;
        }else if(args[0].equalsIgnoreCase("private")){
            votingType = VotingType.privatevoting;
        }else{
            sendUsage(player);
            return false;
        }

        String fullName = "";

        for(int i = 1; i < args.length; i++){
            fullName = fullName + " " + args[i].replace("&", "§");
        }

        if(fullName.length() <= 0){
            sendUsage(player);
            return false;
        }

        for(InvVoting30 invVoting30 : UmfrageCMD.inv30votings){
            if(invVoting30.getName().equalsIgnoreCase(fullName) && invVoting30.isRunning()){
                player.sendMessage("§cEs existiert bereits eine solche Umfrage!");
                return false;
            }
        }

        player.sendMessage("§e--------------------------------------------------");
        player.sendMessage("§aDu hast das Voting erstellt!");
        player.sendMessage("§e--------------------------------------------------");

        InvVoting30 voting = new InvVoting30(votingType, fullName, player);
        voting.sendVoting();

        return true;
    }

    private void sendUsage(Player player){
        player.sendMessage("§cUSAGE");
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if(!(sender instanceof Player)) return null;

        Player player = (Player) sender;

        if(!player.hasPermission(PERMISSION_NAME)) return null;

        if(args.length == 1){
            List<String> complets = new ArrayList<>();

            complets.add("public");
            complets.add("private");

            return complets;
        }
        return null;
    }
}
