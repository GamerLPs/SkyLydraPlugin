package de.Barryonixx.Main.FreundeSystem;

import de.Barryonixx.Main.FileManager;
import de.Barryonixx.Main.SkyLydra;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;

public class FriendCommand implements CommandExecutor, TabCompleter, Listener {
    private int TASKID;

    public static String friend = "§8| §b§lFRIEND §8» §7";

    private static YamlConfiguration friends = FileManager.friends;
    
    @EventHandler
    public void onQuit(PlayerQuitEvent event){
        Player player = event.getPlayer();

        SimpleDateFormat sdf = new SimpleDateFormat("dd:MM:yyyy | HH:mm");
        Date date = new Date();

        FileManager.cfg.set("Player." + player.getName() + ".LastOnline", sdf.format(date));
        FileManager.saveAllFiles();
    }

    @EventHandler
    public void onInvClick(InventoryClickEvent event){
        if(event.getView().getTitle().startsWith("§6§lFreunde:")){
            event.setCancelled(true);
        }
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)){
            sender.sendMessage("§cDu musst diesen Command als Spieler ausführen!");
            return false;
        }

        Player player = (Player) sender;
        if(args.length == 0){
            int count = 0;
            for(String f : friends.getStringList(player.getName() + ".Friends")){
                count++;
            }

            Inventory friendInv = Bukkit.createInventory(null, 9*6, "§6§lFreunde: §8(§e" + count + "§8)");

            for(String f : friends.getStringList(player.getName() + ".Friends")){
                ItemStack head = new ItemStack(Material.PLAYER_HEAD);
                SkullMeta meta = (SkullMeta) head.getItemMeta();
                meta.setOwner(f);
                meta.setDisplayName(f);
                ArrayList<String> info = new ArrayList<>();

                Player p = Bukkit.getPlayer(f);

                info.add("§8▪ §7Zuletzt online: §e" + (FileManager.cfg.isSet("Player." + f + ".LastOnline") ? FileManager.cfg.getString("Player." + f + ".LastOnline") : "Keine Angabe"));

                if(p != null){
                    info.clear();
                    info.add("§a§oonline");
                }
                meta.setLore(info);
                head.setItemMeta(meta);
                friendInv.addItem(head);
            }

            player.openInventory(friendInv);
        }

        if(args.length == 2 && args[0].equalsIgnoreCase("add")){
            Player target = Bukkit.getPlayer(args[1]);
            if(target == null){
                player.sendMessage(friend+ "§cFehler: §7Der Spieler nicht gefunden!");
                return false;
            }

            if(player == target){
                player.sendMessage(friend+ "Du kannst dich nicht selbst als Freund hinzufügen!");
                return false;
            }

            if(frienddoubled(player, target.getName())){
                player.sendMessage(friend+ "Du hast §c" + target.getName() + " §7bereits als Freund!");
                return false;
            }

            player.sendMessage(friend + "Du hast eine Anfrage an §6"+target.getName()+" §7versendet.");

            friends.set(player.getName() + ".Request." + target.getName(), true);
            FileManager.saveAllFiles();
            addFriend(player, target, true);
            startTimer(player, target);
            return true;
        }


        if(args.length == 1 && args[0].equalsIgnoreCase("list")){
            sendFriendList(player);
            return true;
        }

        if(args.length == 2 && args[0].equalsIgnoreCase("accept")){
            Player targetAccept = Bukkit.getPlayer(args[1]);

            if(targetAccept == null){
                player.sendMessage(friend+ "§cFehler: §7Der Spieler nicht gefunden!");
                return false;
            }
            boolean requested = friends.getBoolean(targetAccept.getName() + ".Request." + player.getName());


            if(!requested){
                player.sendMessage(friend+"Der Spieler hat dir keine Freundschaftsanfrage geschickt!");
                return false;
            }

            acceptFriend(player, targetAccept);
            return true;
        }

        if(args.length == 2 && args[0].equalsIgnoreCase("remove")){
            List<String> friendsgot = null;
            if(friends.isSet(player.getName() + ".Friends")){
                friendsgot = friends.getStringList(player.getName() + ".Friends");
            }else{
                player.sendMessage(friend+"Der Spieler ist nicht in deiner Freundesliste!");
                return false;
            }

            if(friendsgot.contains(args[1])){
                friendsgot.remove(args[1]);
                friends.set(player.getName() + ".Friends", friendsgot);
                FileManager.saveAllFiles();

                player.sendMessage(friend+"Du hast §c" + args[1] + " §7aus deiner Freundesliste entfernt!");

                Player removed = Bukkit.getPlayer(args[1]);
                
                if(!(removed == null)){
                    removed.sendMessage("§c"+player.getName() + " §7hat dich aus seiner Freundesliste entfernt!");
                }

                List<String> friendsOfRemoved = friends.getStringList(removed.getName() + ".Friends");

                friendsOfRemoved.remove(player.getName());

                friends.set(removed.getName() + ".Friends", friendsOfRemoved);
                FileManager.saveAllFiles();

                return true;
            }else{
                player.sendMessage(friend+"Der Spieler ist nicht in deiner Freundesliste!");
                return false;
            }
        }

        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> complets = new ArrayList<>();
        if(args.length == 1){
            complets.add("list");
            complets.add("remove");
            complets.add("add");
            complets.add("accept");
        }

        if(args.length == 2){
            complets.clear();
            Bukkit.getOnlinePlayers().forEach(player -> {
                complets.add(player.getName());
            });
        }

        return complets;
    }

    private boolean frienddoubled(Player player, String targetName){
        List<String> friendsgot = null;
        if(friends.isSet(player.getName() + ".Friends")){
            friendsgot = friends.getStringList(player.getName() + ".Friends");
        }else{
            return false;
        }
        return friendsgot.contains(targetName);
    }

    private void acceptFriend(Player player, Player target){
        if(Bukkit.getScheduler().isCurrentlyRunning(TASKID)){
            Bukkit.getScheduler().cancelTask(TASKID);
        }

        friends.set(target.getName() + ".Request." + player.getName(), false);
        FileManager.saveAllFiles();
        target.sendMessage(friend+ "§6" + player.getName() + " §7hat die Freundschaftsanfrage akzeptiert!");

        addFriend(target, player, false);
        addFriend(player, target, false);
        player.sendMessage(friend+"Du bist jetzt mit §c" + target.getName() + " §7befreundet!");
    }

    private void startTimer(Player requester, Player getter){
        Bukkit.getScheduler().runTaskLater(SkyLydra.getInstance(), new Runnable() {
            @Override
            public void run() {
                boolean running = friends.getBoolean(requester.getName() + ".Request." + getter.getName());

                if(running){
                    friends.set(requester.getName() + ".Request." + getter.getName(), false);
                    FileManager.saveAllFiles();
                    getter.sendMessage(friend+ "§cZeit ist abgelaufen...!");
                    requester.sendMessage(friend+"§c" + getter.getName() + " §7hat deine Freundschaftsanfrage nicht akzeptiert!");
                }
            }
        }, 600);
    }


    private void addFriend(Player requester, Player target, boolean requested){

        if(requested){

            ClickEvent cevent = new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/friend accept " + requester.getName());

            net.md_5.bungee.api.chat.TextComponent text = new TextComponent("§e§oKlicke hier");
            text.setClickEvent(cevent);


            //text.addExtra(" §a, oder schreibe /friend accept " + requester.getName() + " um sie anzunehmen!");
            target.sendMessage(friend+ "§7Freundschaftsanfrage von §6" + requester.getName());
            target.spigot().sendMessage(text);
            return;
        }else{
            List<String> friendsgot = null;
            if(friends.isSet(requester.getName() + ".Friends")){
                friendsgot = friends.getStringList(requester.getName() + ".Friends");
            }else{
                friendsgot = new ArrayList<>();
            }

            if(!(friendsgot.contains(target.getName()))){
                friendsgot.add(target.getName());
            }else{
                requester.sendMessage(friend+ "§c" + target.getName() + " ist bereits dein Freund!");
                return;
            }

            friends.set(requester.getName() + ".Friends", friendsgot);

            FileManager.saveAllFiles();
        }
    }

    private void sendFriendList(Player target){
        List<String> friendsgot = null;
        if(friends.isSet(target.getName() + ".Friends")){
            friendsgot = friends.getStringList(target.getName() + ".Friends");
            target.sendMessage("§e▪ Freundes-Liste");

            for(String current : friendsgot){
                String status = null;
                Player p = Bukkit.getPlayer(current);
                if(p == null){
                    status = "§coffline";
                }else{
                    status = "§aonline";
                }
                target.sendMessage("§8▪ §7" + current + " §8| " + status);
            }
        }else{
           target.sendMessage(friend+ "Füge erst einen Freund hinzu!");
           return;
        }


    }
}
