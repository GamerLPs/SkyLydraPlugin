package de.Barryonixx.Main.SystemCommands;

import de.Barryonixx.Main.Data;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GiveAllCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Data.prefix+"Du musst ein Spieler sein!");
        }
        Player p = (Player)sender;
        if (!p.hasPermission("Skylydra.giveall"))
        {
            p.sendMessage(Data.prefix+Data.NoPerm);
            return true;
        }
        ItemStack hand = p.getItemInHand();
        if ((hand == null) || (hand.getType() == Material.AIR)) {
            p.sendMessage(Data.prefix+"§cFehler: §7Du musst ein Item in der Hand halten");
            return false;
        }
        int amount = hand.getAmount();
        String name = hand.getItemMeta().getDisplayName() == null ? hand.getType().name() : hand.getItemMeta() == null ? hand.getType().name() : hand.getItemMeta().getDisplayName();
        for (Player all : Bukkit.getOnlinePlayers())
        {
            all.sendMessage(Data.prefix+"Kleines Geschenk für jeden §8(§e" + amount + "§8)");
            if (all != p) {
                if (all.getInventory().firstEmpty() == -1) {
                    all.getWorld().dropItemNaturally(all.getLocation(), hand);
                } else {
                    all.getInventory().addItem(new ItemStack[] { hand });
                }
            }
        }
        return false;
    }
}
