package de.Barryonixx.Main.Listeners;

import de.Barryonixx.Main.CoinSystem.CoinSystem;
import de.Barryonixx.Main.Data;
import de.Barryonixx.Main.FileManager;
import de.Barryonixx.Main.ScorboardPack.MainScoreBoard;
import de.Barryonixx.Main.SkyLydra;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.entity.EntityInteractEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.help.HelpTopic;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.util.Vector;

import java.util.List;

public class ChatListener implements Listener {

    @EventHandler
    public void Command(PlayerCommandPreprocessEvent e) {
        Player p = e.getPlayer();
        String m = e.getMessage();
        String cmd = e.getMessage().split(" ")[0];
        HelpTopic topic = Bukkit.getServer().getHelpMap().getHelpTopic(cmd);
        if ((Bukkit.getPluginCommand(m) == null) && (topic == null)) {
            p.sendMessage(Data.prefix + "Dieser Befehl existiert nicht.");
            e.setCancelled(true);
        }

        if (((cmd.equalsIgnoreCase("/plugins")) || (cmd.equalsIgnoreCase("/pl")) || (cmd.equalsIgnoreCase("/bukkit:pl")) ||
                (cmd.equalsIgnoreCase("/bukkit:plugins")) || (cmd.startsWith("/plugins")) || (cmd.startsWith("/bukkit:")) ||
                (cmd.equalsIgnoreCase("/bukkit:help")) || (cmd.equalsIgnoreCase("/?")) || (cmd.startsWith("/minecraft:")) ||
                (cmd.equalsIgnoreCase("/me")) || (cmd.startsWith("/?")) || (cmd.startsWith("/pex")) ||
                (cmd.startsWith("about")) || (cmd.startsWith("ver"))) &&
                (!p.hasPermission("system.plugins"))) {
            p.sendMessage(Data.prefix + "Dieser Befehl existiert nicht.");
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onChatFARBE(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        String msg = e.getMessage();

        if(Data.globalmute){
            if(p.hasPermission("skylydra.globalmute.use")){
                e.setCancelled(true);
                p.sendMessage(Data.prefix + "Der Globalmute ist Aktiv");
            }
        }

        if (p.hasPermission("skylydra.chat.farbe")) {
            msg = msg.replace("&", "§");
        }
        e.setMessage(msg);

    }

    @EventHandler
    public void onClck(PlayerInteractEvent event){

        //THOR RANGGUTSCHEIN!!
        Player player  = (Player) event.getPlayer();
        ItemStack book_Thor = new ItemStack(Material.BOOK);
        ItemMeta thorBook_meta = book_Thor.getItemMeta();
        thorBook_meta.setDisplayName("§8» §7Ranggutschein§c: §6§lTHOR");
        book_Thor.setItemMeta(thorBook_meta);

        if(event.getAction().equals(Action.RIGHT_CLICK_AIR)){

            if(!(event.getPlayer().getItemInHand().getType().equals(Material.BOOK))){
                return;
            }

            if(event.getPlayer().getItemInHand().getItemMeta().getDisplayName().equals("§8» §7Ranggutschein§c: §6§lTHOR")){
                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "lp user " + player.getName() + " parent set thor");
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("§7Du hast nun den Rang §6§lTHOR §7erhalten."));

                player.getInventory().removeItem(player.getItemInHand());
            }

            if(event.getPlayer().getItemInHand().getItemMeta().getDisplayName().equals("§8» §7Ranggutschein§c: §5§lC§7§lH§5§lA§7§lM§5§lP§7§lI§5§lO§7§lN")){
                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "lp user " + player.getName() + " parent set champion");
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("§7Du hast nun den Rang §5§lC§7§lH§5§lA§7§lM§5§lP§7§lI§5§lO§7§lN §7erhalten."));

                player.getInventory().removeItem(player.getItemInHand());
            }

            if(event.getPlayer().getItemInHand().getItemMeta().getDisplayName().equals("§8» §7Ranggutschein§c: §a§lHERO")){
                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "lp user " + player.getName() + " parent set hero");
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("§7Du hast nun den Rang §a§lHERO §7erhalten."));

                player.getInventory().removeItem(player.getItemInHand());
            }

            if(event.getPlayer().getItemInHand().getItemMeta().getDisplayName().equals("§8» §7Ranggutschein§c: §b§lT§3§lI§b§lT§3§lA§b§lN")){
                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "lp user " + player.getName() + " parent set titan");
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("§7Du hast nun den Rang §b§lT§3§lI§b§lT§3§lA§b§lN §7erhalten."));

                player.getInventory().removeItem(player.getItemInHand());
            }

            if(event.getPlayer().getItemInHand().getItemMeta().getDisplayName().equals("§8» §7Ranggutschein§c: §6§lD§e§lELUX§6§lE")){
                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "lp user " + player.getName() + " parent set deluxe");
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("§7Du hast nun den Rang §6§lD§e§lELUX§6§lE §7erhalten."));

                player.getInventory().removeItem(player.getItemInHand());
            }
        }

    }

    public static ItemStack getChampionBook(){
        ItemStack book_Thor = new ItemStack(Material.BOOK);
        ItemMeta thorBook_meta = book_Thor.getItemMeta();
        thorBook_meta.setDisplayName("§8» §7Ranggutschein§c: §5§lC§7§lH§5§lA§7§lM§5§lP§7§lI§5§lO§7§lN");
        book_Thor.setItemMeta(thorBook_meta);

        return book_Thor;
    }

    @EventHandler
    public void onFallDown(PlayerMoveEvent event){
        Player player = event.getPlayer();

        if(!(player.getVelocity().getY() < 0)) return;
        if(!(player.getLocation().getY() < 30)) return;

        int tode = 0;

        YamlConfiguration playerData = FileManager.playerData;

        if(playerData.isSet("Tode." + player.getName())){
            tode = playerData.getInt("Tode." + player.getName());
        }

        tode++;

        playerData.set("Tode." + player.getName(), tode);
        FileManager.saveAllFiles();

        player.teleport(player.getWorld().getSpawnLocation());
    }

    @EventHandler
    public void onKick(PlayerKickEvent e){
        Player p = e.getPlayer();
        e.setLeaveMessage("§8[§c§l-§8] §e"+p.getName());
    }



    @EventHandler
    public void onLogin(PlayerLoginEvent e) {
        if (Data.wartung == true) {
            Player p = e.getPlayer();
            if (!p.hasPermission("skylydra.wartung.join")) {
                p.kickPlayer("§c§lSKY-LYDRA - Info\n" +
                        "§7Wir sind gerade in den §cWartungsarbeiten§7!\n" +
                        "\n" +
                        "§e§oDanke für dein Verständniss.");
            }
        }
    }
    @EventHandler
    public void onTOD(PlayerDeathEvent event){

        if(!((event.getEntity()) instanceof Player)) return;
        if(!(event.getEntity().getKiller() instanceof Player)) return;

        Player player = event.getEntity();
        Player killer = event.getEntity().getKiller();
        event.setDeathMessage(null);
        player.sendMessage("§7Du wurdest von §c"+killer.getName() + " §7gekillt.");
        killer.sendMessage("§7Du hast §c" + player.getName() + " §7gekillt.");

        YamlConfiguration playerData = FileManager.playerData;

        int kills = 0;
        int tode = 0;



        /*
        * Wenn Tode / Kills nicht gesetzt ist wird 0 als wert übernommen,
        * ansonsten der Wert der bereits gesetzt ist.
        * */
        if(playerData.isSet("Kills." + killer.getName())){
            kills = playerData.getInt("Kills." + killer.getName());
        }

        if(playerData.isSet("Tode." + player.getName())){
            tode = playerData.getInt("Tode." + player.getName());
        }

        tode++;
        kills++;

        System.out.println(playerData.isSet("Tode." + player.getName()));
        System.out.println(tode);

        playerData.set("Kills." + killer.getName(), kills);
        playerData.set("Tode." + player.getName(), tode);

        FileManager.saveAllFiles();

        if(CoinSystem.getEco() == null){
            return;
        }

        CoinSystem.getEco().depositPlayer(killer, 25);
        killer.sendMessage("§8» §7Du hast §625 Coins §7erhalten!");
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent event){
        Player player = event.getPlayer();

        new MainScoreBoard(player);
    }

    @EventHandler
    public void onJoin (PlayerJoinEvent e){
        Player p = e.getPlayer();

        new MainScoreBoard(p);
        e.setJoinMessage("§8[§a§l+§8] §e"+p.getName());


        boolean isNew = getNew(p);

        if(isNew){
            Bukkit.broadcastMessage("§7Willkommen §c" + p.getName() + " §7auf §c§lSky-Lydra.de §e#" + getNum(p));
        }

    }

    //PlayerJoinEvent?
    private boolean getNew(Player player){
        YamlConfiguration cfg = FileManager.cfg;

        List<String> players = cfg.getStringList("Players.NeuAufDemServer");

        if(players == null || !(players.contains(player.getName()))){
            return true;
        }

        return false;
    }

    private int getNum(Player player){
        YamlConfiguration cfg = FileManager.cfg;

        List<String> players = cfg.getStringList("Players.NeuAufDemServer");

        if(players == null){
            return 1;
        }else{
            int count = 0;

            for(String s : players){
                count++;
            }

            players.add(player.getName());

            cfg.set("Players.NeuAufDemServer", players);
            FileManager.saveAllFiles();


            return count + 1;
        }
    }

    @EventHandler
    public void onQuit (PlayerQuitEvent e){
        Player p = e.getPlayer();
        e.setQuitMessage("§8[§c§l-§8] §e"+p.getName());
    }

    @EventHandler
    public void onMOTD(ServerListPingEvent e) {

        e.setMotd("§e§l          Mc.Sky-Lydra.de §8(§71.8§8-§71.16§8)\n" +
                  "§b§o             ➥ §cSkyPvP & Freebuild");
        e.setMaxPlayers(120);

        if (Data.wartung) {
            e.setMotd(Data.prefix+ "§eWartungsarbeiten\n" +
                    "§e                        Bald verfügbar!");
            e.setMaxPlayers(0);
        }
    }

    @EventHandler
    public void onEntityInteract(PlayerInteractEvent event) {
        if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            Player player = event.getPlayer();
                if (event.getClickedBlock().getState() instanceof Sign) {
                    Sign sign = (Sign) event.getClickedBlock().getState();

                    if (sign.getLine(0).equals("§1[Kostenlos]")) {
                           Inventory inventory = Bukkit.createInventory(null, 9 * 3, "§7[§1Kostenlos§7] §e" + sign.getLine(3));

                           String line = sign.getLine(3).replace("§e", "");

                           if(line.equalsIgnoreCase("EXP_BOTTLE")){
                               ItemStack bottle = new ItemStack(Material.EXPERIENCE_BOTTLE, Integer.parseInt(sign.getLine(1)));

                               if(bottle != null){
                                   inventory.setItem(13, bottle);
                               }else{
                                   player.sendMessage("Bottle == null");
                               }
                           }else{
                               ItemStack item = new ItemStack(Material.getMaterial(sign.getLine(3).replace("§e", "")), Integer.parseInt(sign.getLine(1)));
                               inventory.setItem(13, item);
                           }
                           player.openInventory(inventory);
                    }
            }

        }
    }

    @EventHandler
    public void signUpdate(SignChangeEvent event){
        Player player = event.getPlayer();
        Sign sign = (Sign) event.getBlock().getState();

        if(player.hasPermission("skylydra.schild.erstellen")) {
            if (event.getLine(0).equals("[Kostenlos]")) {
                String line3 = event.getLine(3);
                event.setLine(0, "§1[Kostenlos]");
                event.setLine(3, "§e" + line3);
            }
        }else{
            player.sendMessage(Data.prefix+Data.NoPerm);
        }

    }



}
