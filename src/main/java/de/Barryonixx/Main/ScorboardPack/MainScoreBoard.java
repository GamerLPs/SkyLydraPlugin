package de.Barryonixx.Main.ScorboardPack;

import de.Barryonixx.Main.CoinSystem.CoinSystem;
import de.Barryonixx.Main.FileManager;
import de.Barryonixx.Main.SkyLydra;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.text.DecimalFormat;
import java.text.NumberFormat;
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
        setScore("§e   >>" + getKillsDeathString(player), 6);
        setScore(ChatColor.YELLOW.toString(), 5);
        setScore("§7 |  §aCoins:" , 4);
        setScore("§7   >>" + formatValue(CoinSystem.getEco().getBalance(player)), 3);
        setScore(ChatColor.BLACK.toString(), 2);
        setScore("§7 |  §aUhrzeit: ", 1);
        setScore("§6   >>" + dtf.format(now), 0);
    }

    @Override
    public void update() {

    }

    private String getKillsDeathString(Player player){
        YamlConfiguration playerData = FileManager.playerData;

        String s = "";
        int kills = 0;
        int tode = 0;

        if(playerData.isSet("Kills." + player.getName())){
            kills = playerData.getInt("Kills." + player.getName());
        }

        if(playerData.isSet("Tode." + player.getName())){
            tode = playerData.getInt("Tode." + player.getName());
        }

        s = kills + " | " + tode;

        return s;
    }


    public static String formatValue(double value) {
        int power;
        String suffix = " kmbt";
        String formattedNumber = "";

        NumberFormat formatter = new DecimalFormat("#,###.#");
        power = (int)StrictMath.log10(value);
        value = value/(Math.pow(10,(power/3)*3));
        formattedNumber=formatter.format(value);
        formattedNumber = formattedNumber + suffix.charAt(power/3);
        return formattedNumber.length()>4 ?  formattedNumber.replaceAll("\\.[0-9]+", "") : formattedNumber;
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
                        setScore("§e   >>" + getKillsDeathString(player), 6);
                        setScore("§7   » §e" + formatValue(CoinSystem.getEco().getBalance(player)), 3);
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
