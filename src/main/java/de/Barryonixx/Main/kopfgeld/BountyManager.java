package de.Barryonixx.Main.kopfgeld;

import de.Barryonixx.Main.FileManager;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BountyManager {
    private YamlConfiguration playerData;

    public BountyManager(YamlConfiguration dataFile){
        this.playerData = dataFile;
    }

    public int getBountyAmount(String playerName){
        if (playerData.isSet("Bounty." + playerName + ".Amount")) {
            return playerData.getInt("Bounty." + playerName + ".Amount");
        }
        return 0;
    }

    public boolean removeBounty(String playerName){

        if(!hasBountySetByString(playerName)){
            return false;
        }

        List<String> bountySet = new ArrayList<String>();

        if(playerData.isSet("BountySets")){
            bountySet = playerData.getStringList("BountySets");
        }

        if(bountySet.contains(playerName)){
            bountySet.remove(playerName);
        }

        playerData.set("BountySets", bountySet);

        playerData.set("Bounty." + playerName + ".Status", false);
        playerData.set("Bounty." + playerName + ".Amount", null);
        playerData.set("Bounty." + playerName + ".Setter", null);

        FileManager.saveAllFiles();

        return false;
    }

    public boolean addBounty(Player player, Player setter, int amount){
        if(hasBountsSet(player)){
            return false;
        }
        List<String> bountySet = new ArrayList<String>();

        if(playerData.isSet("BountySets")){
            bountySet = playerData.getStringList("BountySets");
        }

        bountySet.add(player.getName());

        playerData.set("BountySets", bountySet);

        playerData.set("Bounty." + player.getName() + ".Status", true);
        playerData.set("Bounty." + player.getName() + ".Amount", amount);
        playerData.set("Bounty." + player.getName() + ".Setter", setter.getName());

        FileManager.saveAllFiles();

        return true;
    }

    public HashMap<String, Integer> getBountys(){
        HashMap<String, Integer> bountys = new HashMap<>();

        if(!(playerData.isSet("BountySets"))){
            return null;
        }

        for(String s : playerData.getStringList("BountySets")){
            if(playerData.isSet("Bounty." + s + ".Amount")){
                bountys.put(s, playerData.getInt("Bounty." + s + ".Amount"));
            }
        }

        return bountys;
    }

    public boolean hasPlayerBountySetter(String targetName, String setterName){
        if(playerData.isSet("Bounty." + targetName + ".Setter")){
            String s = playerData.getString("Bounty." + targetName + ".Setter");

            if(s.equals(setterName)){
                return true;
            }
        }
        return false;
    }

    public boolean hasBountySetByString(String playerName){
        if(playerData.isSet("Bounty." + playerName)){
            return true;
        }

        return false;
    }

    public boolean hasBountsSet(Player player){
        if(playerData.isSet("Bounty." + player.getName() + ".Status")){
            return playerData.getBoolean("Bounty." + player.getName() + ".Status");
        }
        return false;
    }
}
