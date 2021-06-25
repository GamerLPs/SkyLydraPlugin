package de.Barryonixx.Main.umfrage;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class UmfrageCMD implements CommandExecutor, Listener {
    private final String INVENTORY_NAME = "Umfragen";

    public static List<InvVoting30> inv30votings = new ArrayList();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)){
            sender.sendMessage("§cDu musst diesen Command als Spieler ausführen!");
            return false;
        }
        Player player = (Player) sender;

        Inventory inventory = Bukkit.createInventory(null, 9*6, INVENTORY_NAME);

        for(InvVoting30 voting30 : inv30votings){
            ItemStack is = new ItemStack(Material.BLUE_TERRACOTTA);
            ItemMeta meta = is.getItemMeta();
            ArrayList<String> lore = new ArrayList<>();
            lore.add("Erstellt von: " + voting30.getCreator().getName());
            if(voting30.getVotingType().equals(VotingType.publicvoting)){
                lore.add("§aÖFFENTLICH");
            }else{
                lore.add("§CANONYM");
            }

            meta.setLore(lore);
            meta.setDisplayName(voting30.getName());

            is.setItemMeta(meta);

            if(!voting30.hasPlayerVoted(player)){
                inventory.addItem(is);
            }
        }

        player.openInventory(inventory);

        return false;
    }

    @EventHandler
    public void pnInventoryClick(InventoryClickEvent event){
        if(!(event.getWhoClicked() instanceof Player)) return;

        Player player = (Player) event.getWhoClicked();

        if(event.getView().getTitle().equalsIgnoreCase(INVENTORY_NAME)){
            event.setCancelled(true);
            if(event.getCurrentItem() == null) return;

            for(InvVoting30 voting30 : inv30votings){
                String itemName = event.getCurrentItem().getItemMeta().getDisplayName();
                if(voting30.getName().equalsIgnoreCase(itemName)){
                    openVote30Inventory(voting30, player);
                }
            }
        }

        for(InvVoting30 voting30 : inv30votings){
            if(event.getCurrentItem() == null) return;
            if(event.getView().getTitle().equalsIgnoreCase(voting30.getName())){
                VotingAnswer answer;
                Material itemType = event.getCurrentItem().getType();
                if(itemType.equals(Material.GREEN_TERRACOTTA)){
                    answer = VotingAnswer.yes;
                }else{
                    answer = VotingAnswer.no;
                }
                if(voting30.isRunning()){
                    voting30.addVote(answer, player);
                    player.closeInventory();
                    player.sendMessage("§aDanke für deine Meinung!");
                }
            }
        }
    }


    private void openVote30Inventory(InvVoting30 voting, Player player){
        String name = voting.getName();
        Inventory inventory = Bukkit.createInventory(null, 9*1, name);

        ItemStack yes = new ItemStack(Material.GREEN_TERRACOTTA);
        ItemMeta yesMeta = yes.getItemMeta();
        yesMeta.setDisplayName("§aJa");
        ArrayList<String> lore = new ArrayList<>();
        lore.add("§aKlicke zum Voten!");
        yesMeta.setLore(lore);
        yes.setItemMeta(yesMeta);

        ItemStack no = new ItemStack(Material.RED_TERRACOTTA);
        ItemMeta noMeta = yes.getItemMeta();
        noMeta.setDisplayName("§cNein");
        ArrayList<String> noLore = new ArrayList<>();
        noLore.add("§aKlicke zum Voten!");
        noMeta.setLore(noLore);
        no.setItemMeta(noMeta);

        inventory.setItem(3, yes);
        inventory.setItem(5, no);

        player.openInventory(inventory);
    }
}
