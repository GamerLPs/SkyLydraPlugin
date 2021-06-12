package de.Barryonixx.Main.superboots;

import de.Barryonixx.Main.Data;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class SuperBootsCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)){
            return false;
        }

        Player player = (Player) sender;


        //Hier die Permission ändern!!!
        if(!(player.hasPermission("skylydra.boots"))){
            player.sendMessage(Data.NoPerm);
            return false;
        }

        if(args.length == 1){

            ItemStack boot = new ItemStack(Material.DIAMOND_BOOTS);
            ItemMeta bootMeta = boot.getItemMeta();
            ArrayList<String> bootLore = new ArrayList<>();
            switch(args[0].toLowerCase()){
                case "jump":
                    bootLore.add("Lässt dich Super hoch springen!");
                    bootMeta.setLore(bootLore);
                    bootMeta.setDisplayName(Superboots.SUPER_JUMP_BOOTS_NAME);
                    boot.setItemMeta(bootMeta);

                    player.getInventory().addItem(boot);
                    player.sendMessage("§aDu hast Super Jump Boots erhalten!");
                    break;
                case "speed":
                    bootLore.add("Lässt dich Super schnell laufen!");
                    bootMeta.setLore(bootLore);
                    bootMeta.setDisplayName(Superboots.SUPER_SPEED_BOOTS_NAME);
                    boot.setItemMeta(bootMeta);

                    player.getInventory().addItem(boot);
                    player.sendMessage("§aDu hast Super Speed Boots erhalten!");
                    break;
                case "devboots":
                    bootLore.add("Speziele Schuhe");
                    bootMeta.setLore(bootLore);
                    bootMeta.setDisplayName(Superboots.DEVELOPER_BOOTS_NAME);
                    boot.setItemMeta(bootMeta);

                    player.getInventory().addItem(boot);
                    player.sendMessage("§aDu hast Developer Boots erhalten!");
                    break;
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> complets = new ArrayList<>();

        if(args.length == 1){
            complets.add("jump");
            complets.add("speed");
            complets.add("devboots");

            return complets;
        }
        return null;
    }
}
