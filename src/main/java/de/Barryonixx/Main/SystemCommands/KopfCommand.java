package de.Barryonixx.Main.SystemCommands;

import de.Barryonixx.Main.Data;
import de.Barryonixx.Main.FileManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class KopfCommand implements CommandExecutor {

    private static YamlConfiguration cfgC = FileManager.cfgC;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player p = (Player)sender;
        if (p.hasPermission("skylydra.skull")) {
            if (args.length == 1) {
                if (!p.hasPermission("*")) {
                    long current = System.currentTimeMillis();
                    if (current >= getEnd(p.getName())) {
                        ItemStack skull = new ItemStack(Material.PLAYER_HEAD);
                        SkullMeta skullMeta = (SkullMeta)skull.getItemMeta();
                        skullMeta.setOwningPlayer(Bukkit.getOfflinePlayer(args[0]));
                        skull.setItemMeta(skullMeta);

                        p.sendMessage(Data.prefix+"§7Du erhälst den Kopf von: §e" + args[0]);
                        p.getInventory().addItem(new ItemStack[] { skull });

                        long current1 = System.currentTimeMillis();
                        long end = current1 + 604800000L;
                        cfgC.set(p.getUniqueId().toString() + ".skull.ende", Long.valueOf(end));
                        FileManager.saveAllFiles();
                    } else {
                        p.sendMessage(getTime(p.getName()));
                    }
                } else {
                    ItemStack skull = new ItemStack(Material.PLAYER_HEAD);
                    SkullMeta skullMeta = (SkullMeta)skull.getItemMeta();
                    skullMeta.setOwningPlayer(Bukkit.getOfflinePlayer(args[0]));
                    skull.setItemMeta(skullMeta);
                    p.sendMessage(Data.prefix+"§7Du erhälst den Kopf von: §e" + args[0]);
                    p.getInventory().addItem(skull);
                }
            }
            else
                p.sendMessage(Data.prefix+"Nutze§8: §7/kopf §8(§7Name§8)");
        }
        else {
            p.sendMessage(Data.prefix+Data.NoPerm);
        }
        return false;
    }

    public static long getEnd(String name)
    {
        String uuid = Bukkit.getOfflinePlayer(name).getUniqueId().toString();
        long end = cfgC.getLong(uuid + ".skull.ende");
        return end;
    }
    public static String getTime(String name) {
        String remainingTime = "";
        long current = System.currentTimeMillis();
        long end = getEnd(name);
        long difference = end - current;
        int Sekunden = 0;
        int Minuten = 0;
        int Stunden = 0;
        int Tage = 0;
        while (difference >= 1000L) {
            difference -= 1000L;
            Sekunden++;
        }
        while (Sekunden >= 60) {
            Sekunden -= 60;
            Minuten++;
        }
        while (Minuten >= 60) {
            Minuten -= 60;
            Stunden++;
        }
        while (Stunden >= 24) {
            Stunden -= 24;
            Tage++;
        }
        remainingTime = "§a" + Tage + "§7Tage, §a" + Stunden + " §7Stunden, §a" + Minuten + " §7Minuten, §a" + Sekunden + " §7Sekunden";
        return remainingTime;
    }
}