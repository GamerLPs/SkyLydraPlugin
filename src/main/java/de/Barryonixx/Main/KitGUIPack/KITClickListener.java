package de.Barryonixx.Main.KitGUIPack;

import de.Barryonixx.Main.Data;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import java.util.HashMap;

public class KITClickListener implements Listener {
    public String KITS = "§8| §e§lKITS §8» §7";

    public static HashMap<String, Long> respawn = new HashMap<>();//X
    public static HashMap<String, Long> spieler = new HashMap<>();//X
    public static HashMap<String, Long> champion = new HashMap<>();//X
    public static HashMap<String, Long> hero = new HashMap<>();
    public static HashMap<String, Long> titan = new HashMap<>();
    public static HashMap<String, Long> deluxe = new HashMap<>();
    public static HashMap<String, Long> thor = new HashMap<>();


    @EventHandler
    public void onKITS(InventoryClickEvent event){
        if(!(event.getWhoClicked() instanceof Player)) return;
        if(event.getView().getTitle().equals("§8| §c§lSKY-LYDRA §8» §cKits")){
            event.setCancelled(true);
            Player player = (Player) event.getWhoClicked();

            if(event.getCurrentItem() == null){
                return;
            }
            switch(event.getCurrentItem().getItemMeta().getDisplayName()){
                case "§8▪ §7Kits: §8(§e§oRespawn§8)":
                    giveRespawnKit(player);
                    break;
                case "§8▪ §7Kits: §8(§9§oSpieler§8)":
                    giveSpielerKit(player);
                    break;
                case "§8▪ §7Kits: §8(§5§lC§7§lH§5§lA§7§lM§5§lP§7§lI§5§lO§7§lN§8)":
                    giveChampionKit(player);
                    break;
            }

        }
    }

    /*
    *
    * sek = ms/1000;
    * min = ms/1000/60;
    * std = ms/1000/60/60;
    * tag = ms/1000/60/60/24;
    * */

    private void giveChampionKit(Player player){
        long jetzt = System.currentTimeMillis();
        if(champion.containsKey(player.getName())){
            long benutzt = champion.get(player.getName());

            int rest = (int) ((benutzt+24*1000*60*60)-jetzt);
            if(rest > 0){
                int std = rest/1000/60/60;
                rest = rest-(std*1000*60*60);
                int min = rest/1000/60;
                rest = rest -(min*1000*60);
                int sek =rest/1000;

                player.sendMessage(KITS + "Bitte warte noch: §c"+std+"h §c" + min +"m §7und §c" + sek+"s");
                player.closeInventory();
                return;
            }
        }
        champion.put(player.getName(), jetzt);

        ItemStack schwert = new ItemStack(Material.DIAMOND_SWORD,1 );
        ItemMeta schwertM = schwert.getItemMeta();
        schwertM.setDisplayName("§8(§5§lC§7§lH§5§lA§7§lM§5§lP§7§lI§5§lO§7§lN§8) §c▪ §7Kit");
        schwert.setItemMeta(schwertM);
        schwert.addEnchantment(Enchantment.DAMAGE_ALL, 3);
        schwert.addEnchantment(Enchantment.KNOCKBACK, 2);
        schwert.addEnchantment(Enchantment.FIRE_ASPECT, 1);

        ItemStack essen = new ItemStack(Material.COOKED_BEEF, 48);
        ItemMeta essenM = essen.getItemMeta();
        essen.setItemMeta(essenM);

        ItemStack perls = new ItemStack(Material.ENDER_PEARL, 6);
        ItemMeta perlsM = perls.getItemMeta();
        perls.setItemMeta(perlsM);

        ItemStack helm = new ItemStack(Material.DIAMOND_HELMET, 1);
        ItemMeta helmM = helm.getItemMeta();
        helmM.setDisplayName("§8(§5§lC§7§lH§5§lA§7§lM§5§lP§7§lI§5§lO§7§lN§8) §c▪ §7Kit");
        helm.setItemMeta(helmM);
        helm.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);

        ItemStack hemd = new ItemStack(Material.DIAMOND_CHESTPLATE, 1);
        ItemMeta hemdM = hemd.getItemMeta();
        hemdM.setDisplayName("§8(§5§lC§7§lH§5§lA§7§lM§5§lP§7§lI§5§lO§7§lN§8) §c▪ §7Kit");
        hemd.setItemMeta(hemdM);
        hemd.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
        hemd.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 3);

        ItemStack hose = new ItemStack(Material.DIAMOND_LEGGINGS, 1);
        ItemMeta hoseM = hose.getItemMeta();
        hoseM.setDisplayName("§8(§5§lC§7§lH§5§lA§7§lM§5§lP§7§lI§5§lO§7§lN§8) §c▪ §7Kit");
        hose.setItemMeta(hoseM);
        hose.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);

        ItemStack schuhe = new ItemStack(Material.DIAMOND_BOOTS, 1);
        ItemMeta schuheM = schuhe.getItemMeta();
        schuheM.setDisplayName("§8(§5§lC§7§lH§5§lA§7§lM§5§lP§7§lI§5§lO§7§lN§8) §c▪ §7Kit");
        schuhe.setItemMeta(schuheM);
        schuhe.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);

        ItemStack opa = new ItemStack(Material.ENCHANTED_GOLDEN_APPLE, 4);

        ItemStack bogen = new ItemStack(Material.BOW, 1);
        ItemMeta bogenM = bogen.getItemMeta();
        bogenM.setDisplayName("§8(§5§lC§7§lH§5§lA§7§lM§5§lP§7§lI§5§lO§7§lN§8) §c▪ §7Kit");
        bogen.setItemMeta(bogenM);
        bogen.addEnchantment(Enchantment.ARROW_KNOCKBACK, 2);


        player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 5, 5);
        player.getInventory().addItem(schwert, bogen, essen, perls,helm, hemd, hose, schuhe, opa);
        player.sendMessage(KITS + "§7Kit §cChampion §7ausgewählt");
        player.closeInventory();
    }

    private void giveSpielerKit(Player player){
        long jetzts = System.currentTimeMillis();
        if(spieler.containsKey(player.getName())){
            long benutzt = spieler.get(player.getName());

            int rest = (int) ((benutzt+24*1000*60*60)-jetzts);

            if(rest > 0){
                int stunden = rest/1000/60/60;

                rest = rest - (stunden*1000*60*60);

                int min = rest/1000/60;
                rest = rest -(min*1000*60);

                int sek = rest/1000;

                player.sendMessage(KITS + "Bitte warte noch: §c"+stunden+"h §c" + min +"m §7und §c" + sek+"s");
                player.closeInventory();
                return;
            }
        }
        spieler.put(player.getName(), jetzts);

        ItemStack schwert = new ItemStack(Material.DIAMOND_SWORD, 1);
        ItemMeta schwertm = schwert.getItemMeta();
        schwertm.setDisplayName("§8(§9§lSpieler§8) §c▪ §7Kit");
        schwert.setItemMeta(schwertm);
        schwert.addEnchantment(Enchantment.DAMAGE_ALL, 3);
        schwert.addEnchantment(Enchantment.KNOCKBACK , 1);
        schwert.addEnchantment(Enchantment.LOOT_BONUS_MOBS, 2);

        ItemStack essen = new ItemStack(Material.COOKED_BEEF, 32);

        ItemStack helm = new ItemStack(Material.DIAMOND_HELMET, 1);
        ItemMeta helmM = helm.getItemMeta();
        helmM.setDisplayName("§8(§9§lSpieler§8) §c▪ §7Kit");
        helm.setItemMeta(helmM);
        helm.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);

        ItemStack hemd = new ItemStack(Material.DIAMOND_CHESTPLATE, 1);
        ItemMeta hemdM = hemd.getItemMeta();
        hemdM.setDisplayName("§8(§9§lSpieler§8) §c▪ §7Kit");
        hemd.setItemMeta(hemdM);
        hemd.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);

        ItemStack hose = new ItemStack(Material.DIAMOND_LEGGINGS, 1);
        ItemMeta hoseM = hose.getItemMeta();
        hoseM.setDisplayName("§8(§9§lSpieler§8) §c▪ §7Kit");
        hose.setItemMeta(hoseM);
        hose.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);

        ItemStack schuhe = new ItemStack(Material.DIAMOND_BOOTS, 1);
        ItemMeta schuheM = schuhe.getItemMeta();
        schuheM.setDisplayName("§8(§9§lSpieler§8) §c▪ §7Kit");
        schuhe.setItemMeta(schuheM);
        schuhe.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL,3 );

        ItemStack opa = new ItemStack(Material.ENCHANTED_GOLDEN_APPLE, 2);


        player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 5, 5);
        player.getInventory().addItem(schwert, essen, helm, hemd, hose, schuhe, opa);
        player.sendMessage(KITS + "§7Kit §cSpieler §7ausgewählt");
        player.closeInventory();

    }

    private void giveRespawnKit(Player player){
        long jetzt = System.currentTimeMillis();
        if(respawn.containsKey(player.getName())){
            long benutz = respawn.get(player.getName());

            int rest = (int) ((benutz+(5*1000*60))-jetzt);

            if(rest > 0){
                int minute = rest/1000/60;
                rest = rest -(minute*1000*60);
                int sek = rest/1000;

                player.sendMessage(KITS + "Bitte warte noch: §c"+minute+"m §7und §c" + sek+"s");
                player.closeInventory();
                return;
            }

        }
        respawn.put(player.getName(), jetzt);


        ItemStack schwert = new ItemStack(Material.DIAMOND_SWORD , 1);
        ItemMeta schwertM = schwert.getItemMeta();
        schwertM.setDisplayName("§8(§e§oRespawn§8) §c▪ §7Kit");
        schwert.setItemMeta(schwertM);
        schwert.addEnchantment(Enchantment.DAMAGE_ALL, 2);
        schwert.addEnchantment(Enchantment.KNOCKBACK, 1);

        ItemStack essen = new ItemStack(Material.COOKED_BEEF, 16);

        ItemStack helm = new ItemStack(Material.DIAMOND_HELMET, 1);
        ItemMeta HelmM = helm.getItemMeta();
        HelmM.setDisplayName("§8(§e§oRespawn§8) §c▪ §7Kit");
        helm.setItemMeta(HelmM);
        helm.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);

        ItemStack hemd = new ItemStack(Material.DIAMOND_CHESTPLATE,1);
        ItemMeta hemdm = hemd.getItemMeta();
        hemdm.setDisplayName("§8(§e§oRespawn§8) §c▪ §7Kit");
        hemd.setItemMeta(hemdm);
        hemd.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);

        ItemStack hose = new ItemStack(Material.DIAMOND_LEGGINGS, 1);
        ItemMeta hoseM = hose.getItemMeta();
        hoseM.setDisplayName("§8(§e§oRespawn§8) §c▪ §7Kit");
        hose.setItemMeta(hoseM);
        hose.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL,2);

        ItemStack schuhe = new ItemStack(Material.DIAMOND_BOOTS);
        ItemMeta schuheM = schuhe.getItemMeta();
        schuheM.setDisplayName("§8(§e§oRespawn§8) §c▪ §7Kit");
        schuhe.setItemMeta(schuheM);
        schuhe.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL,2);

        ItemStack opa = new ItemStack(Material.ENCHANTED_GOLDEN_APPLE, 1);

        player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 5, 5);
        player.getInventory().addItem(schwert, essen, helm, hemd, hose, schuhe, opa);
        player.sendMessage(KITS + "§7Kit §cRespawn §7ausgewählt");
        player.closeInventory();
    }

}
