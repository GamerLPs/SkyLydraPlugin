package de.Barryonixx.Main.ShopSystem;

import de.Barryonixx.Main.CoinSystem.CoinSystem;
import de.Barryonixx.Main.Data;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class ShopMain implements CommandExecutor, Listener {

    private int THOR_PREIS = 15000000;
    private int DELUXE_PREIS = 500000;
    private int TITAN_PREIS = 300000;
    private int HERO_PREIS = 250000;
    private int CHAMPION_PREIS = 5000;


    private int SHARPV_PREIS = 500;
    private int UNBREAKINGIII_PREIS = 500;
    private int POWERV_PREIS = 500;
    private int INFINITY_PREIS = 500;
    private int MENDING_PREIS = 500;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)){
            sender.sendMessage("§cDu musst diesen Command als Spieler benutzen!");
            return false;
        }

        Player player = (Player) sender;

        Inventory inventory = Bukkit.createInventory(null, 9*1, "Shop");

        ItemStack ranks = new ItemStack(Material.BOOK);
        ItemMeta ranksMeta = ranks.getItemMeta();
        ranksMeta.setDisplayName("§aRänge");
        ranks.setItemMeta(ranksMeta);

        inventory.setItem(4, ranks);
        player.openInventory(inventory);

        return false;
    }

    @EventHandler
    public void onClickShop(InventoryClickEvent event){
        if(!(event.getWhoClicked() instanceof Player)) return;
        if(event.getView().getTitle().equals("Shop")){
            event.setCancelled(true);
            Player player = (Player) event.getWhoClicked();

            if(event.getCurrentItem() == null){
                return;
            }

            switch(event.getCurrentItem().getItemMeta().getDisplayName()){
                case "§aRänge":
                    player.closeInventory();
                    openRanks(player);
                    break;
            }
        }


        if(event.getView().getTitle().equals("RÄNGE")){
            event.setCancelled(true);
            Player player = (Player) event.getWhoClicked();

            if(event.getCurrentItem() == null){
                return;
            }

            switch(event.getCurrentItem().getItemMeta().getDisplayName()){
                case "§7Rang: §6§lTHOR":
                    if(hasCoins(player, THOR_PREIS)){
                        removeCoins(player, THOR_PREIS);
                        player.closeInventory();
                        player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 5, 5);
                        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "lp user " + player.getName() + " parent set thor");
                        player.sendMessage("§8▪ §7Du hast den Rang §6§lTHOR §7gekauft!");
                    }else{
                        player.sendMessage("§8▪ §7Du hast nicht genug Coins!");
                        player.closeInventory();
                        return;
                    }
                    break;
                case "§7Rang: §6§lD§e§lELUX§6§lE":
                    if(hasCoins(player, DELUXE_PREIS)){
                        removeCoins(player, DELUXE_PREIS);
                        player.closeInventory();
                        player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 5, 5);
                        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "lp user " + player.getName() + " parent set deluxe");
                        player.sendMessage("§8▪ §7Du hast den Rang §6§lD§e§lELUX§6§lE §7gekauft!");
                    }else{
                        player.sendMessage("§8▪ §7Du hast nicht genug Coins!");
                        player.closeInventory();
                        return;
                    }
                    break;
                case "§7Rang: §b§lT§3§lI§b§lT§3§lA§b§lN":
                    if(hasCoins(player, TITAN_PREIS)){
                        removeCoins(player, TITAN_PREIS);
                        player.closeInventory();
                        player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 5, 5);
                        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "lp user " + player.getName() + " parent set titan");
                        player.sendMessage("§8▪ §7Du hast den Rang §b§lT§3§lI§b§lT§3§lA§b§lN §7gekauft!");
                    }else{
                        player.sendMessage("§8▪ §7Du hast nicht genug Coins!");
                        player.closeInventory();
                        return;
                    }
                    break;
                case "§7Rang: §a§lHERO":
                    if(hasCoins(player, HERO_PREIS)){
                        removeCoins(player, HERO_PREIS);
                        player.closeInventory();
                        player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 5, 5);
                        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "lp user " + player.getName() + " parent set hero");
                        player.sendMessage("§8▪ §7Du hast den Rang §a§lHERO §7gekauft!");
                    }else{
                        player.sendMessage("§8▪ §7Du hast nicht genug Coins!");
                        player.closeInventory();
                        return;
                    }
                    break;
                case "§7Rang: §5§lC§7§lH§5§lA§7§lM§5§lP§7§lI§5§lO§7§lN":
                    if(hasCoins(player, CHAMPION_PREIS)){
                        removeCoins(player, CHAMPION_PREIS);
                        player.closeInventory();
                        player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 5, 5);
                        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "lp user " + player.getName() + " parent set champion");
                        player.sendMessage("§8▪ §7Du hast den Rang §5§lC§7§lH§5§lA§7§lM§5§lP§7§lI§5§lO§7§lN §7gekauft!");
                    }else{
                        player.sendMessage("§8▪ §7Du hast nicht genug Coins!");
                        player.closeInventory();
                        return;
                    }
                    break;
            }
        }

    }

    private void openRanks(Player player){
        Inventory inventory = Bukkit.createInventory(null, 9*1, "RÄNGE");

        ItemStack thor = new ItemStack(Material.BOOK);
        ItemMeta thorMeta = thor.getItemMeta();
        ArrayList<String> thorLore = new ArrayList<>();
        thorLore.add("§8▪ §e§oPreis: §7§o" + THOR_PREIS);
        thorLore.add("§c   §8▪ §7/hat");
        thorLore.add("§c   §8▪ §7/enderchest");
        thorLore.add("§c   §8▪ §7/kopf <Spieler>");
        thorLore.add("§c   §8▪ §7Fabrig schreiben.");
        thorLore.add("§c   §8▪ §7Kit §8| §7§oIn Arbeit...");
        thorMeta.setLore(thorLore);
        thorMeta.setDisplayName("§7Rang: §6§lTHOR");
        thor.setItemMeta(thorMeta);

        ItemStack deluxe = new ItemStack(Material.BOOK);
        ItemMeta deluxeMeta = deluxe.getItemMeta();
        ArrayList<String> deluxeLore = new ArrayList<>();
        deluxeLore.add("§8▪ §e§oPreis: §7§o" + DELUXE_PREIS);
        deluxeLore.add("§c   §8▪ §7/hat");
        deluxeLore.add("§c   §8▪ §7/enderchest");
        deluxeLore.add("§c   §8▪ §7/kopf <Spieler>");
        deluxeLore.add("§c   §8▪ §7Fabrig schreiben.");
        deluxeLore.add("§c   §8▪ §7Kit §8| §7§oIn Arbeit...");
        deluxeMeta.setLore(deluxeLore);
        deluxeMeta.setDisplayName("§7Rang: §6§lD§e§lELUX§6§lE");
        deluxe.setItemMeta(deluxeMeta);

        ItemStack titan = new ItemStack(Material.BOOK);
        ItemMeta titanMeta = titan.getItemMeta();
        ArrayList<String> titanLore = new ArrayList<>();
        titanLore.add("§8▪ §e§oPreis: §7§o" + TITAN_PREIS);
        titanLore.add("§c   §8▪ §7/hat");
        titanLore.add("§c   §8▪ §7/enderchest");
        titanLore.add("§c   §8▪ §7/kopf <Spieler>");
        titanLore.add("§c   §8▪ §7Fabrig schreiben.");
        titanLore.add("§c   §8▪ §7Kit §8| §7§oIn Arbeit...");
        titanMeta.setLore(titanLore);
        titanMeta.setDisplayName("§7Rang: §b§lT§3§lI§b§lT§3§lA§b§lN");
        titan.setItemMeta(titanMeta);

        ItemStack hero = new ItemStack(Material.BOOK);
        ItemMeta heroMeta = hero.getItemMeta();
        ArrayList<String> heroLore = new ArrayList<>();
        heroLore.add("§8▪ §e§oPreis: §7§o" + HERO_PREIS);
        heroLore.add("§c   §8▪ §7/hat");
        heroLore.add("§c   §8▪ §7/enderchest");
        heroLore.add("§c   §8▪ §7/kopf <Spieler>");
        heroLore.add("§c   §8▪ §7Fabrig schreiben.");
        heroLore.add("§c   §8▪ §7Kit §8| §a&lHERO");
        heroMeta.setLore(heroLore);
        heroMeta.setDisplayName("§7Rang: §a§lHERO");
        hero.setItemMeta(heroMeta);

        ItemStack champion = new ItemStack(Material.BOOK);
        ItemMeta championMeta = champion.getItemMeta();
        ArrayList<String> championLore = new ArrayList<>();
        championLore.add("§8▪ §e§oPreis: §7§o" + CHAMPION_PREIS);
        championLore.add("§c   §8▪ §7/hat");
        championLore.add("§c   §8▪ §7/enderchest");
        championLore.add("§c   §8▪ §7Kit §8| §5§lC§7§lH§5§lA§7§lM§5§lP§7§lI§5§lO§7§lN");
        championMeta.setLore(championLore);
        championMeta.setDisplayName("§7Rang: §5§lC§7§lH§5§lA§7§lM§5§lP§7§lI§5§lO§7§lN");
        champion.setItemMeta(championMeta);


        inventory.setItem(2, thor);
        inventory.setItem(3, deluxe);
        inventory.setItem(4, titan);
        inventory.setItem(5, hero);
        inventory.setItem(6, champion);

        player.openInventory(inventory);
    }

    private boolean hasCoins(Player player, int amount){
        if(CoinSystem.getEco() == null){
            return false;
        }

        return CoinSystem.getEco().getBalance(player) >= amount;
    }

    private void removeCoins(Player player, int amount){
        if(CoinSystem.getEco() == null){
            return;
        }
        CoinSystem.getEco().withdrawPlayer(player, amount);
    }
}
