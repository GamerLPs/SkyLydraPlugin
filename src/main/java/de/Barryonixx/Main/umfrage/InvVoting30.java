package de.Barryonixx.Main.umfrage;

import de.Barryonixx.Main.SkyLydra;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InvVoting30 {
    private final String PREFIX = "§3VOTING >> ";

    private HashMap<Player, VotingAnswer> answers = new HashMap<>();
    private List<Player> votedPlayers = new ArrayList<>();

    private boolean running;

    private final Player creator;
    private final String name;
    private final VotingType votingType;

    public InvVoting30(VotingType votingType, String name, Player creator){
        this.name = name;
        this.running = true;
        this.votingType = votingType;
        this.creator = creator;
    }


    public void sendVoting(){
        for(Player player : Bukkit.getOnlinePlayers()){
            ClickEvent event = new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/umfrage");
            TextComponent text = new TextComponent(PREFIX + "Klicke Hier zum Voten");
            text.setClickEvent(event);

            player.spigot().sendMessage(text);
        }

        UmfrageCMD.inv30votings.add(this);

        new BukkitRunnable(){
            @Override
            public void run() {
                setRunning(false);
                int yesCount = 0;
                int noCount = 0;

                int all = 0;

                for(VotingAnswer answer : answers.values()){
                    if(answer.equals(VotingAnswer.yes)){
                        yesCount++;
                    }else if(answer.equals(VotingAnswer.no)){
                        noCount++;
                    }

                    all++;
                }

                if(all != 0){
                    int yesPercent = (int) (yesCount * 100) / all;
                    int noPercent = (int) (noCount * 100) / all;


                    creator.sendMessage("§e--------------------------------------------------\n");
                    creator.sendMessage("§aDie Umfrage Ergebnisse von: \""+ name +"\" sind da!\n \n");
                    creator.sendMessage("§aGesamte Stimmen: " + all + "\n");
                    creator.sendMessage("§aJA: §e" + yesPercent + " %");
                    creator.sendMessage("§cNEIN: §e" + noPercent + " %");

                    if(votingType == VotingType.publicvoting){
                        creator.sendMessage("\n \n§aHier die Einzelnen Votes:");

                        for(Player player : answers.keySet()){
                            if(answers.get(player).equals(VotingAnswer.yes)){
                                creator.sendMessage("§e" + player.getName() + " : §aJA" );
                            }else{
                                creator.sendMessage("§e" + player.getName() + " : §cNEIN" );
                            }
                        }
                    }

                    creator.sendMessage("\n§aTYP: §e" + (votingType == VotingType.privatevoting ? "PRIVAT" : "ÖFFENTLICH") );
                    creator.sendMessage("\n§aErstellt von: §e" + creator.getName());
                    creator.sendMessage("§e--------------------------------------------------");
                }else{
                    creator.sendMessage("§e--------------------------------------------------");
                    creator.sendMessage("§cFür deine Umfrage \""+ name +"\" hat leider niemand gevoted!");
                    creator.sendMessage("§e--------------------------------------------------");
                    cancel();
                }
                cancel();
            }
        }.runTaskLater(SkyLydra.getInstance(), 20 * 30);
    }

    public boolean hasPlayerVoted(Player player){
        if(votedPlayers.contains(player)){
            return true;
        }

        return false;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean value){
        this.running = value;
    }

    public Player getCreator(){
        return creator;
    }

    public VotingType getVotingType(){
        return votingType;
    }

    public String getName(){
        return name;
    }

    public void addVote(VotingAnswer answer, Player player){
        votedPlayers.add(player);
        answers.put(player, answer);
    }

    public void openVoteInventory(Player player){
        Inventory inventory = Bukkit.createInventory(null, 9*1, name);
    }


}
