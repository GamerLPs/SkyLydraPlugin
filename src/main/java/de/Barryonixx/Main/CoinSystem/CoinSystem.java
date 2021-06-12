package de.Barryonixx.Main.CoinSystem;

import de.Barryonixx.Main.Crate.VaultCrate;
import de.Barryonixx.Main.Data;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.RegisteredServiceProvider;

import java.util.ArrayList;
import java.util.List;

public class CoinSystem implements CommandExecutor, Listener, TabCompleter {
    private static CoinSystem instance = new CoinSystem();

    private static Economy eco = null;
    public String Konto = "§8| §9§lKONTO §8» §7";

    public static CoinSystem getInstance() {
        return instance;
    }

    public static Economy getEco(){
        return eco;
    }

    public static boolean setupEconomy() {
        if (Bukkit.getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }

        RegisteredServiceProvider<Economy> rsp = Bukkit.getServer().getServicesManager().getRegistration(Economy.class);

        if (rsp == null) {
            return false;
        }

        eco = rsp.getProvider();
        return (eco != null);
    }

    public static ItemStack getMoney(int amount){
        ItemStack money = new ItemStack(Material.COAL, 1);
        ItemMeta moneyMeta = money.getItemMeta();
        moneyMeta.setDisplayName("§8» §7Gutschein§c: §e" + amount + " Coins");
        ArrayList<String> lore = new ArrayList<>();
        lore.add("§a§oRechtsklicke zum einzahlen!");
        moneyMeta.setLore(lore);
        money.setItemMeta(moneyMeta);

        return money;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)){
            return false;
        }
        Player player = (Player) sender;

        if(args.length == 0){
            if(eco == null){
                player.sendMessage(Konto+ "§cFehler: §7Zurzeit nicht möglich!");
                return false;
            }

            player.sendMessage(Konto+"Du besitzt: §e" + eco.getBalance(player) + " §7Coins.");
            return true;
        }

        if(args[0].equalsIgnoreCase("überweisen") && args.length == 3){
            Player target = Bukkit.getPlayer(args[1]);
            int amount = Integer.parseInt(args[2]);

            if(target == null){
                return false;
            }

            if(amount == 0){
                return false;
            }


            addMoney(target, amount);
            removeMoney(player, amount);

            target.sendMessage(Konto + "Du hast §e" + amount + " §7Coins bekommen.");
            player.sendMessage(Konto + "Du hast §c" + target.getName() + " §e" + amount + " §7Coins überwiesen!");
            return true;

        }

        if(args.length == 1 && !(args[0].equalsIgnoreCase("überweisen"))){
            if(player.hasPermission("skylydra.coins")){
                Player target = Bukkit.getPlayer(args[0]);
                if(target == null){
                    player.sendMessage(Konto+ "§cFehler: §7Der Spieler nicht gefunden!");
                    return false;
                }

                player.sendMessage(Konto + "§e" + target.getName() + "§7: §6" + eco.getBalance(target)+ "§7 Coins");
                return true;
            }else{
                player.sendMessage(Data.prefix+Data.NoPerm);
                return false;
            }
        }


        if(args.length == 2 && args[0].equalsIgnoreCase("drop")){
            int amount = Integer.parseInt(args[1]);

            if(amount <= 0){
                player.sendMessage("§cDas geht nicht!");
                return false;
            }

            ItemStack money = new ItemStack(Material.COAL, 1);
            ItemMeta moneyMeta = money.getItemMeta();
            moneyMeta.setDisplayName("§8» §7Gutschein§c: §e" + amount + " Coins");
            ArrayList<String> lore = new ArrayList<>();
            lore.add("§a§oRechtsklicke zum einzahlen!");
            moneyMeta.setLore(lore);
            money.setItemMeta(moneyMeta);


            player.getInventory().addItem(money);

            player.sendMessage(Konto + "Du hast §6" + amount + " Coins §7als Item erhalten!");

            return true;
        }
        
        if(args.length == 3 && args[0].equalsIgnoreCase("set")){
            Player target = Bukkit.getPlayer(args[1]);
            int amount = Integer.parseInt(args[2]);

            if(target == null){
                player.sendMessage(Konto + "§cFehler: §7Der Spieler nicht gefunden!");
                return false;
            }

            if(eco == null){
                player.sendMessage("§cEs ist ein Fehler in Vault aufgetreten!");
                return false;
            }

            eco.withdrawPlayer(target, eco.getBalance(target));
            eco.depositPlayer(target, amount);
            player.sendMessage(Konto+ "Du hast den Kontostand von §e" + target.getName() + " §7auf §e" + amount + " §7gesetzt!");
            target.sendMessage(Konto + "Dein Kontostand wurde auf §e" + amount + " §7gesetzt!");
            return true;
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if(!(sender instanceof Player)){
            return null;
        }

        Player player = (Player) sender;

        ArrayList<String> complets = new ArrayList<>();

        if(args.length == 1 && player.hasPermission("skylydra.coins")) {
            complets.add("überweisen");
            complets.add("set");
            complets.add("drop");
            return complets;
        }else if(args.length == 1){
            complets.add("überweisen");
            return complets;
        }

        return null;
    }




    @EventHandler
    public void onCoinClick(PlayerInteractEvent event){
        Player player = event.getPlayer();

        if(!(player.getItemInHand().getType() == Material.COAL && player.getItemInHand().getItemMeta().getDisplayName().contains("§8» §7Gutschein§c: §e"))){
            return;
        }

        int amount = Integer.parseInt(player.getItemInHand().getItemMeta().getDisplayName().replace("§8» §7Gutschein§c: §e", "").replace(" Coins", ""));

        if(eco == null){
            player.sendMessage("§aGeld ist zurzeit nicht verfügbar!");
            return;
        }

        if(amount == 0){
            player.sendMessage("§cEs ist ein Fehler aufgetretten!");
            return;
        }

        addMoney(player, amount);
    }

    private void removeMoney(Player target, int amount){
        eco.withdrawPlayer(target, amount);
    }

    private void addMoney(Player target, int amount){

        if(eco.hasAccount(target)){
            eco.createPlayerAccount(target);
            System.out.println("ECO: Account erstellt für " + target.getDisplayName());
        }

        eco.depositPlayer(target, amount);

        ItemStack gotMoney = new ItemStack(Material.COAL, 1);
        ItemMeta gotMoneyMeta = gotMoney.getItemMeta();
        gotMoneyMeta.setDisplayName("§8» §7Gutschein§c: §e" + amount + " Coins");
        gotMoney.setItemMeta(gotMoneyMeta);

        if(target.getItemInHand().getAmount() >= 2){
            target.getItemInHand().setAmount(target.getItemInHand().getAmount() - 1);
        }else{
            target.getInventory().removeItem(target.getItemInHand());
        }

        target.sendMessage(Konto + "Gutschein von §6" + amount + " Coins §7eingelöst!");

    }


}
