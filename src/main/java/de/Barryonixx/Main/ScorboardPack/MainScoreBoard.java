package de.Barryonixx.Main.ScorboardPack;

import de.Barryonixx.Main.CoinSystem.CoinSystem;
import de.Barryonixx.Main.SkyLydra;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MainScoreBoard extends ScoreboardBuilder{
    private int animationID;

    public MainScoreBoard(Player player){
        super(player, ChatColor.DARK_PURPLE.toString() + "SKYLYDRA.DE");
        animationID = 0;

        run();
    }

    @Override
    public void createScoreboard(){

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();


        setScore("§c " + player.getWorld().getName(), 9);
        setScore(ChatColor.DARK_AQUA.toString(), 8);
        setScore("§7 |  §aKills/Tode:", 7);
        setScore("§e   >>Hier kommen die Tode/Kills", 6);
        setScore(ChatColor.YELLOW.toString(), 5);
        setScore("§7 |  §aCoins:" , 4);
        setScore("§7   >>" + CoinSystem.getEco().getBalance(player), 3);
        setScore(ChatColor.BLACK.toString(), 2);
        setScore("§7 |  §aUhrzeit: ", 1);
        setScore("§6   >>" + dtf.format(now), 0);



        //TODO: Kills und TOD
    }

    @Override
    public void update() {

    }

    private void run(){
        new BukkitRunnable(){

            @Override
            public void run() {
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();

                switch(animationID){
                    case 0:
                        setDisplayName("§4§lS§c§lky§7-§4§lL§c§lydra");

                        setScore("§7§o        " + player.getWorld().getName(), 9);
                        setScore("§7   » §e0 | 0", 6);
                        setScore("§7   » §e" + CoinSystem.getEco().getBalance(player), 3);
                        setScore("§7   » §e" + dtf.format(now), 0);
                        break;
                }

                animationID++;

                if(animationID >= 2){
                    animationID = 0;
                }
            }
        }.runTaskTimer(SkyLydra.getInstance(), 20, 20);
    }
}
