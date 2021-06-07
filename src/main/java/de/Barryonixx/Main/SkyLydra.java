package de.Barryonixx.Main;

import de.Barryonixx.Main.KitGUIPack.KITClickListener;
import de.Barryonixx.Main.KitGUIPack.KitGUI;
import de.Barryonixx.Main.SystemCommands.*;
import de.Barryonixx.Main.CoinSystem.CoinSystem;
import de.Barryonixx.Main.Crate.VaultCrate;
import de.Barryonixx.Main.Crate.VoteKeyCommand;
import de.Barryonixx.Main.FreundeSystem.FriendCommand;
import de.Barryonixx.Main.FreundeSystem.FriendListener;
import de.Barryonixx.Main.Listeners.ChatListener;
import de.Barryonixx.Main.ShopSystem.ShopMain;
import de.Barryonixx.Main.report.ReportInventory;
import de.Barryonixx.Main.superboots.SuperBootsCommand;
import de.Barryonixx.Main.superboots.Superboots;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;


public final class SkyLydra extends JavaPlugin {

    private static SkyLydra plugin;
    private static SkyLydra instance;

    @Override
    public void onEnable() {
        instance = this;
        plugin = this;

        FileManager.setup();
        if(FileManager.cfgFile.exists()){
            if(FileManager.cfg != null){

            }

        }else{
            System.out.println("Fehler beim Laden der Datei");
        }
        FileManager.saveAllFiles();

        loadCommands();
        loadListeners();

        Data.wartung = FileManager.cfg.isSet("Plugin.Wartung") ? FileManager.cfg.getBoolean("Plugin.Wartung") : false;
        //Data.wartung = cfg.get("Wartung") != null && cfg.getBoolean("Wartung");


        //Vault
        if(CoinSystem.setupEconomy()){
            System.out.println("-----------------------------------");
            System.out.println("Vault-Bridge geladen");
            System.out.println("-----------------------------------");
        }


        Bukkit.getConsoleSender().sendMessage("");
        Bukkit.getConsoleSender().sendMessage(Data.prefix + "Wurde geladen & läuft nun.");
        Bukkit.getConsoleSender().sendMessage("");
    }

    @Override
    public void onDisable() {

        Bukkit.getConsoleSender().sendMessage("");
        Bukkit.getConsoleSender().sendMessage(Data.prefix + "Wurde deaktiviert & läuft nun nicht mehr.");
        Bukkit.getConsoleSender().sendMessage("");
    }

    public static SkyLydra getPlugin() {
        return plugin;
    }

    // Commands Laden lassen
    public void loadCommands(){
        getCommand("boots").setExecutor(new SuperBootsCommand());
        getCommand("boots").setTabCompleter(new SuperBootsCommand());
        getCommand("kits").setExecutor(new KitGUI());
        getCommand("discord").setExecutor(new DiscordCommand());
        getCommand("vote").setExecutor(new VoteCommand());
        getCommand("freebuild").setExecutor(new FreeBuildCommand());
        getCommand("claim").setExecutor(new VoteRewardCommand());
        getCommand("coins").setExecutor(new CoinSystem());
        getCommand("coins").setTabCompleter(new CoinSystem());
        getCommand("friend").setExecutor(new FriendCommand());
        getCommand("friend").setTabCompleter(new FriendCommand());
        getCommand("npc").setExecutor(new VaultCrate());
        getCommand("giveVoteKey").setExecutor(new VoteKeyCommand());
        getCommand("wartung").setExecutor(new WartungCommand());
        getCommand("report").setExecutor(new ReportCommand());
        getCommand("report").setTabCompleter(new ReportCommand());
        getCommand("wartung").setTabCompleter(new WartungCommand());
        getCommand("msg").setExecutor(new MessageCommand());
        getCommand("replay").setExecutor(new ReplayCommand());
        getCommand("teleport").setExecutor(new TeleportCommand());
        getCommand("tphere").setExecutor(new TeleportHereCommand());
        getCommand("kick").setExecutor(new KickCommand());
        getCommand("clearchat").setExecutor(new ClearChatCommand());
        getCommand("day").setExecutor(new DayCommand());
        getCommand("night").setExecutor(new NightCommand());
        getCommand("gamemode").setExecutor(new GameModeCommand());
        getCommand("müll").setExecutor(new MüllCommand());
        getCommand("giveall").setExecutor(new GiveAllCommand());
        getCommand("clearinventory").setExecutor(new ClearInvCommand());
        getCommand("scull").setExecutor(new KopfCommand());
        getCommand("vanish").setExecutor(new VanishCommand());
        getCommand("globalmute").setExecutor(new GlobalMuteCommand());
        getCommand("broadcast").setExecutor(new BroadcastCommand());
        getCommand("enderchest").setExecutor(new EnderChestCommand());
        getCommand("hat").setExecutor(new HatCommand());
        getCommand("gesucht").setExecutor(new GesuchtCommand());
        getCommand("sun").setExecutor(new SonnenCommand());
        getCommand("heal").setExecutor(new HealCommand());
        getCommand("shop").setExecutor(new ShopMain());
    }

    //Listener/Events laden lassen
    public void loadListeners(){
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new ChatListener(), this);
        pm.registerEvents(new CoinSystem(), this);
        pm.registerEvents(new VaultCrate(), this);
        pm.registerEvents(new ReportInventory(), this);
        pm.registerEvents(new FriendListener(), this);
        pm.registerEvents(new FriendCommand(), this);
        pm.registerEvents(new ShopMain(), this);
        pm.registerEvents(new KITClickListener(), this);
        pm.registerEvents(new Superboots(), this);

    }

    public static SkyLydra getInstance() {
        return instance;
    }
}
