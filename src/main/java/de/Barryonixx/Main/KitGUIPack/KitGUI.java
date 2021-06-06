package de.Barryonixx.Main.KitGUIPack;

import de.Barryonixx.Main.Data;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class KitGUI implements CommandExecutor {
    Inventory KITS = Bukkit.createInventory(null, 9*5 , "§8| §c§lSKY-LYDRA §8» §cKits");

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player player = (Player)sender;
            if(command.getName().equalsIgnoreCase("kits")){

                ItemStack respawn = new ItemStack(Material.PLAYER_HEAD);
                SkullMeta respawnm = (SkullMeta)respawn.getItemMeta();
                respawnm.setOwner("Bedrock");
                respawnm.setDisplayName("§8▪ §7Kits: §8(§e§oRespawn§8)");
                respawn.setItemMeta(respawnm);

                ItemStack spieler = new ItemStack(Material.PLAYER_HEAD);
                SkullMeta spielerm = (SkullMeta)spieler.getItemMeta();
                spielerm.setOwner("Bedrock");
                spielerm.setDisplayName("§8▪ §7Kits: §8(§9§oSpieler§8)");
                spieler.setItemMeta(spielerm);

                ItemStack champion = new ItemStack(Material.PLAYER_HEAD);
                SkullMeta championm = (SkullMeta)champion.getItemMeta();
                championm.setOwner("Bedrock");
                championm.setDisplayName("§8▪ §7Kits: §8(§5§lC§7§lH§5§lA§7§lM§5§lP§7§lI§5§lO§7§lN§8)");
                champion.setItemMeta(championm);

                ItemStack hero = new ItemStack(Material.PLAYER_HEAD);
                SkullMeta herom = (SkullMeta)hero.getItemMeta();
                herom.setOwner("Bedrock");
                herom.setDisplayName("§8▪ §7Kits: §8(§a§lHERO§8)");
                hero.setItemMeta(herom);

                ItemStack thor = new ItemStack(Material.PLAYER_HEAD);
                SkullMeta thorm = (SkullMeta)thor.getItemMeta();
                thorm.setOwner("Bedrock");
                thorm.setDisplayName("§8▪ §7Kits: §8(§6§lTHOR§8)");
                thor.setItemMeta(thorm);



//OFFEN FÜR IDEEN
                ItemStack rot = new ItemStack(Material.PLAYER_HEAD);
                SkullMeta rotm = (SkullMeta)rot.getItemMeta();
                rotm.setOwner("Red_Wool");
                rotm.setDisplayName("§7§ooffen für ideen...");
                rot.setItemMeta(rotm);

//PFEIL RECHTS ERSTELLT
                ItemStack rechts = new ItemStack(Material.PLAYER_HEAD);
                SkullMeta skullMeta = (SkullMeta)rechts.getItemMeta();
                skullMeta.setOwner("MHF_ArrowRight");
                skullMeta.setDisplayName("§7 ");
                rechts.setItemMeta(skullMeta);

                ItemStack glass = new ItemStack(Material.WHITE_STAINED_GLASS_PANE, 1);
                ItemMeta glass_m = glass.getItemMeta();
                glass_m.setDisplayName("§6 ");
                glass.setItemMeta(glass_m);

//KITS-AUSWAHL 1-3
                ItemStack auswahl1 = new ItemStack(Material.BOOK, 1);
                ItemMeta a1 = auswahl1.getItemMeta();
                a1.setDisplayName("§8» §7Kits§8: §7Auswahl §8(§71§8)");
                auswahl1.setItemMeta(a1);

                ItemStack auswahl2 = new ItemStack(Material.BOOK, 1);
                ItemMeta a2 = auswahl2.getItemMeta();
                a2.setDisplayName("§8» §7Kits§8: §7Auswahl §8(§72§8)");
                auswahl2.setItemMeta(a2);

                ItemStack auswahl3 = new ItemStack(Material.BOOK, 1);
                ItemMeta a3 = auswahl3.getItemMeta();
                a3.setDisplayName("§8» §7Kits§8: §7Auswahl §8(§73§8)");
                auswahl3.setItemMeta(a3);


//PLATZ-HALTER
                KITS.setItem(0,glass);
                KITS.setItem(1,glass);
                KITS.setItem(2,glass);
                KITS.setItem(3,glass);
                KITS.setItem(4,glass);
                KITS.setItem(5,glass);
                KITS.setItem(6,glass);
                KITS.setItem(7,glass);
                KITS.setItem(8,glass);
                KITS.setItem(9,glass);
                KITS.setItem(12,glass);
                KITS.setItem(17, glass);
                KITS.setItem(18, glass);
                KITS.setItem(21, glass);
                KITS.setItem(26, glass);
                KITS.setItem(27, glass);
                KITS.setItem(30, glass);
                KITS.setItem(35, glass);
                KITS.setItem(36, glass);
                KITS.setItem(37, glass);
                KITS.setItem(38, glass);
                KITS.setItem(39, glass);
                KITS.setItem(40, glass);
                KITS.setItem(41, glass);
                KITS.setItem(42, glass);
                KITS.setItem(43, glass);
                KITS.setItem(44, glass);
//PFEILE-RECHTS
                KITS.setItem(11, rechts);
                KITS.setItem(20, rechts);
                KITS.setItem(29, rechts);


//KITS-AUSWAHL
                KITS.setItem(10, auswahl1);
                KITS.setItem(19, auswahl2);
                KITS.setItem(28, auswahl3);


//ROTE-WOLLE
                KITS.setItem(13, rot);
                KITS.setItem(14, rot);
                KITS.setItem(22, rot);

//AUSWÄHLBARE KITS
                KITS.setItem(15, respawn);
                KITS.setItem(16, spieler);
                KITS.setItem(23, respawn);
                KITS.setItem(24, spieler);
                KITS.setItem(25, champion);
                KITS.setItem(31, spieler);
                KITS.setItem(32, champion);
                KITS.setItem(33, hero);
                KITS.setItem(34, thor);
                player.openInventory(KITS);
            }
        }
        return false;
    }
}
