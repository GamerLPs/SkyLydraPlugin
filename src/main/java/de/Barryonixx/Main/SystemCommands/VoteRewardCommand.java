package de.Barryonixx.Main.SystemCommands;

import de.Barryonixx.Main.CoinSystem.CoinSystem;
import de.Barryonixx.Main.Data;
import de.Barryonixx.Main.SkyLydra;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class VoteRewardCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player){
            Player player = (Player)sender;
            if(player.hasPermission("skylydra.votereward")){
                //BELOHNUNG ERHALTEN
                //--------------------------------------------------
                player.getInventory().addItem(CoinSystem.getMoney(1000));
                //--------------------------------------------------

                //VOTE-KEY
                //---------------------------------------------------------
                ItemStack voteKey = new ItemStack(Material.TRIPWIRE_HOOK);
                ItemMeta key = voteKey.getItemMeta();
                key.setDisplayName("§8» §7Crate §8| §eSchlüssel");
                voteKey.setItemMeta(key);
                player.getInventory().addItem(voteKey);
                //---------------------------------------------------------

                ItemStack sword = new ItemStack(Material.NETHERITE_SWORD);
                player.getInventory().addItem(sword);


                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "lp user " + player.getName() + " permission unset skylydra.votereward");
                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "lp user " + player.getName() + " permission set skylydra.heal");
                player.sendMessage("");
                player.sendMessage("");
                player.sendMessage("");
                player.sendMessage("");
                player.sendMessage("§8| §d§lVOTE §8» §7Du hast deine Belohnung erfolgreich abgeholt.");
                player.sendMessage("§8       ▪ §7§oDu kannst dich einmal §e§oHeilen");
                player.sendMessage("§8       ▪ §7§o1000 Coins als Item");
                player.sendMessage("§8       ▪ §7§oEinen Vote-Key");
                player.sendMessage("§8       ▪ §7§oEin Netherite Schwert");
                player.sendMessage("");
                player.sendMessage("");


            }else{
                player.sendMessage("§8| §d§lVOTE §8» §7Du kannst keine Belohnung anfordern!");
            }
        }else{
            sender.sendMessage("Du musst ein Spieler sein");
        }

        return false;
    }
}
