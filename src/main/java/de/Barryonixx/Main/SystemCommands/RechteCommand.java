package de.Barryonixx.Main.SystemCommands;

import de.Barryonixx.Main.Data;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.util.EulerAngle;

import java.util.ArrayList;
import java.util.List;

public class RechteCommand implements CommandExecutor, Listener {
    private final String RECHTE_INVENTORY_NAME = Data.prefix + "§cRechte:";
    private final String PERMISSION = "skylydra.info";


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) return false;
        Player player = (Player) sender;

        if(!(args.length == 0)) return false;

        Inventory inventory = Bukkit.createInventory(null, 9*3, RECHTE_INVENTORY_NAME);

        RechtStack champion = new RechtStack("§8» §7Rechte-Liste §8(§71§8)");
        champion.addInfo("§7Ränge§8: §5§lC§7§lH§5§lA§7§lM§5§lP§7§lI§5§lO§7§lN§8 §7- §6§lTHOR");

        inventory.setItem( 4 ,champion.getStack());

        ItemStack fillUp = new ItemStack(Material.WHITE_STAINED_GLASS_PANE);
        ItemMeta meta = fillUp.getItemMeta();
        meta.setDisplayName(" ");
        fillUp.setItemMeta(meta);

        for(int slot = 0; slot < inventory.getSize(); slot++){
            if(inventory.getItem(slot) == null){
                inventory.setItem(slot, fillUp);
            }
        }

        player.openInventory(inventory);


        return false;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event){
        if(event.getView().getTitle().equals(RECHTE_INVENTORY_NAME)){
            event.setCancelled(true);
        }
    }


    private class RechtStack{
        private final String NAME;
        private final ArrayList<String> info;

        public RechtStack(String name){
            this.NAME = name;
            this.info = new ArrayList<>();
        }

        public void addInfo(String info){
            this.info.add(info);
        }

        public void addInfoInArray(ArrayList<String> infoList){
            for(String s : infoList){
                this.info.add(s);
            }
        }

        public ArrayList<String> getInfo(){
            return info;
        }

        public ItemStack getStack(){
            ItemStack item = new ItemStack(Material.BOOK);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(NAME);
            meta.setLore(info);
            item.setItemMeta(meta);

            return item;
        }
    }
}
