package de.Barryonixx.Main;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class FileManager {

    public static File folder = new File("/var/opt/minecraft/server/plugins/SkyLydra");
    public static File cfgFile = new File(folder, "config.yml");
    public static File friendFile = new File(folder, "friends.yml");
    public static File cfgcooldown = new File(folder, "cooldowns.yml");
    public static File playerDataFile = new File(folder, "playerdata.yml");

    public static YamlConfiguration friends;
    public static YamlConfiguration cfg;
    public static YamlConfiguration cfgC;
    public static YamlConfiguration playerData;

    public static void setup(){
        if(!folder.exists()){
            folder.mkdirs();
        }

        if(!cfgFile.exists()){
            try{
                cfgFile.createNewFile();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        if(!cfgcooldown.exists()){
            try{
                cfgcooldown.createNewFile();
            }catch (IOException e){
                e.printStackTrace();
            }
        }

        if(!friendFile.exists()){
            try{
                friendFile.createNewFile();
            }catch (IOException e){
                e.printStackTrace();
            }
        }

        if(!playerDataFile.exists()){
            try{
                playerDataFile.createNewFile();
            }catch(IOException e){
                e.printStackTrace();
            }
        }


        if(cfgcooldown.exists()){
            System.out.println("Datei gefunden");
            cfgC = YamlConfiguration.loadConfiguration(cfgcooldown);
        }

        if(cfgFile.exists()){
            System.out.println("Datei gefunden");
            cfg = YamlConfiguration.loadConfiguration(cfgFile);
        }

        if(friendFile.exists()){
            System.out.println("Datei gefunden");
            friends = YamlConfiguration.loadConfiguration(friendFile);
        }

        if(playerDataFile.exists()){
            System.out.println("Datei gefunden");
            playerData = YamlConfiguration.loadConfiguration(playerDataFile);
        }
    }

    public static void saveAllFiles(){
      try{
          cfg.save(cfgFile);
          friends.save(friendFile);
          cfgC.save(cfgcooldown);
          playerData.save(playerDataFile);
      }catch (IOException e){
          e.printStackTrace();
      }
    }
}
