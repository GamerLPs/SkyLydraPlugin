package de.Barryonixx.Main.Crate;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class VoteKeyCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player){
            Player player = (Player) sender;
            if(!player.hasPermission("npc.key")){
                return false;
            }
        }

        if(args.length == 2){
            int amount = Integer.parseInt(args[1]);
            Player target = Bukkit.getPlayer(args[0]);

            if(target == null){
                sender.sendMessage("§cDer Spieler wurde nicht gefunden!");
                return false;
            }

            ItemStack voteKey = new ItemStack(Material.TRIPWIRE_HOOK);
            ItemMeta key = voteKey.getItemMeta();
            key.setDisplayName("§8» §7Crate §8| §eSchlüssel");
            voteKey.setItemMeta(key);

            target.getInventory().addItem(voteKey);
            target.sendMessage("§7Du hast §6" + amount + "x §8» §7Crate §8| §eSchlüssel §7erhalten!");
            return true;
        }
        return false;
    }
}
