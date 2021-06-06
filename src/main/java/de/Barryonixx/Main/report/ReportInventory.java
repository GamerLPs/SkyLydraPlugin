package de.Barryonixx.Main.report;

import de.Barryonixx.Main.SystemCommands.ReportCommand;
import de.Barryonixx.Main.Data;
import de.Barryonixx.Main.SkyLydra;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ReportInventory implements Listener {

    private static Map<Player, ItemStack> contentMap = new HashMap<>();
    private Map<Player, UUID> editMap = new HashMap<>();
    
    public static ItemStack[] getContents(){
        return contentMap.values().toArray(new ItemStack[contentMap.size()]);
    }

    public ItemStack[] getDetails(){
        ItemStack[] isArr = new ItemStack[9];
        ItemStack is;
        ItemMeta im;

        is = new ItemStack(Material.ENDER_PEARL);
        im = is.getItemMeta();
        im.setDisplayName("§eTeleportieren...");
        is.setItemMeta(im);
        isArr[2] = is;

        is = new ItemStack(Material.IRON_SWORD);
        im = is.getItemMeta();
        im.setDisplayName("§eVom Server Kicken...");
        is.setItemMeta(im);
        isArr[4] = is;


        /*

        is = new ItemStack(Material.DIAMOND_SWORD);
        im = is.getItemMeta();
        im.setDisplayName("§4Spieler bannen");
        is.setItemMeta(im);
        isArr[5] = is;

        */

        is = new ItemStack(Material.BARRIER);
        im = is.getItemMeta();
        im.setDisplayName("§eReports löschen...");
        is.setItemMeta(im);
        isArr[8] = is;

        return isArr;
    }

    public static void add(Player hacker, String reporter, String cause){
        ItemStack is = new ItemStack(Material.LEGACY_SKULL_ITEM, 1, (byte) 3);
        SkullMeta meta = (SkullMeta) is.getItemMeta();
        meta.setOwner(hacker.getName());
        meta.setDisplayName("§c§o" + hacker.getName());
        meta.setLore(Arrays.asList("§7Reportet von " + reporter, "§7Grund: §e" + cause));
        is.setItemMeta(meta);
        contentMap.put(hacker, is);
    }

    @EventHandler
    public void onClick(InventoryClickEvent e){
        final Player p = (Player) e.getWhoClicked();
        if(e.getView().getTitle().equals("Reports:") && e.getCurrentItem() != null){
            e.setCancelled(true);
            for(Map.Entry<Player, ItemStack> entry : contentMap.entrySet()){
                if(entry.getValue().equals(e.getCurrentItem())){
                    if(entry.getKey() != null){
                        Bukkit.getScheduler().runTaskLater(SkyLydra.getInstance(), new Runnable() {
                            @Override
                            public void run() {
                                Inventory inv = Bukkit.createInventory(p, 9, "Option wählen:");
                                inv.setContents(getDetails());
                                p.openInventory(inv);
                                editMap.put(p, entry.getKey().getUniqueId());
                            }
                        }, 1);
                    }else{
                        contentMap.remove(entry.getKey());
                    }
                }
            }
        }else if(e.getView().getTitle().equals("Option wählen:") && e.getCurrentItem() != null && editMap.containsKey(p)){
            e.setCancelled(true);

            /*SPIELER BANNEN
            if(e.getCurrentItem().getType() == Material.DIAMOND_SWORD){

                return;
            }

             */
            Player wasReported = Bukkit.getPlayer(editMap.get(p));
            if(wasReported != null){
                switch(e.getCurrentItem().getType()){
                    case IRON_SWORD:
                        String msg = Data.prefix+"Kick-Info§8 « \n §7Du wurdest von §e"+p.getName()+ " §7gekickt!§7\n\n§aGrund:\n§7" + "Hacking / Cheating";
                        wasReported.kickPlayer(msg);
                        contentMap.remove(wasReported);
                        p.closeInventory();
                        break;
                    case ENDER_PEARL:
                        p.teleport(wasReported);
                        p.closeInventory();
                        break;
                    case BARRIER:
                        contentMap.remove(wasReported);
                        p.closeInventory();
                        break;
                }
            }else{
                p.closeInventory();
                p.sendMessage(ReportCommand.getRPREFIX() + "§cDer Spieler ist nicht mehr online.");
            }
        }
    }

    @EventHandler
    public void onClose(InventoryCloseEvent e){
        if(editMap.containsKey(e.getPlayer())){
            editMap.remove(e.getPlayer());
        }
    }


}
