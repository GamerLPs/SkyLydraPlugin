package de.Barryonixx.Main.kopfgeld;

import de.Barryonixx.Main.CoinSystem.CoinSystem;
import de.Barryonixx.Main.SkyLydra;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BountyCommand implements CommandExecutor, TabCompleter, Listener {
    private final String INVENTORY_TITLE = "Kopfgelder";

    private BountyManager bountyManager = SkyLydra.getInstance().getBountyManager();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)){
            return false;
        }

        Player player = (Player) sender;


        //Bounty Anzeigen
        if(args.length == 0 || args[0].equalsIgnoreCase("list")){
            HashMap<String, Integer> bountys = bountyManager.getBountys();
            Inventory bountyView = Bukkit.createInventory(null, 9*6, INVENTORY_TITLE);

            for(String actualPlayer : bountys.keySet()){
                ItemStack is = new ItemStack(Material.PLAYER_HEAD);
                SkullMeta sm = (SkullMeta) is.getItemMeta();
                sm.setOwner(actualPlayer);
                ArrayList<String> lore = new ArrayList<>();
                lore.add("Kopfgeld: " + bountys.get(actualPlayer) + " Coins");

                sm.setDisplayName(actualPlayer);
                sm.setLore(lore);
                is.setItemMeta(sm);

                bountyView.addItem(is);
            }

            player.openInventory(bountyView);

            return true;
        }

        //Bounty Entfernen
        if(args[0].equalsIgnoreCase("remove") && args.length == 2){
            String targetName = args[1];

            if(!bountyManager.hasBountySetByString(targetName)){
                player.sendMessage("§cAuf diesen Spieler ist kein Kopfgeld gesetzt!");
                return false;
            }

            if(!bountyManager.hasPlayerBountySetter(targetName, player.getName())){
                player.sendMessage("§cDu kannst kein Kopfgeld von einem anderen Spieler entfernen, da du das Kopfgeld nicht ausgeschrieben hast!");
                return false;
            }

            bountyManager.removeBounty(targetName);

            player.sendMessage("§aDu hast das Kopfgeld entfernt!");

            return true;
        }

        //Kopfgeld hinzufügen
        if(args[0].equalsIgnoreCase("add") && args.length == 3) {
            Player target = Bukkit.getPlayer(args[1]);
            int amount = Integer.parseInt(args[2]);

            if(target == player){
                player.sendMessage("§cDu kannst kein Kopfgeld auf dich selbst setzen!");
                return false;
            }

            if(target == null){
                player.sendMessage("§cDer Spieler wurde nicht gefunden!");
                return false;
            }

            if(amount <= 0){
                player.sendMessage("§cDu kannst nicht 0 Coins, oder weniger an Kopfgeld setzen!");
                return false;
            }

            if(CoinSystem.getEco().getBalance(player) < amount){
                player.sendMessage("§cDu hast nicht so viel geld!");
                return false;
            }


            boolean done = bountyManager.addBounty(target, player, amount);

            if(done){
                target.sendMessage("§c Es wurde ein kopfgeld auf dich ausgeschrieben!" );
                player.sendMessage("§aDu hast erfolgreich §6" + amount + " Coins Kopfgeld §aauf §6" + target.getName() + " §agesetzt!");

                CoinSystem.getEco().withdrawPlayer(player, amount);
            }else{
                player.sendMessage("§cEs ist etwas schief gelaufen!");
                return false;
            }

            return true;
        }

        return false;
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event){
        Player death = event.getEntity();

        if(bountyManager.getBountys().keySet().contains(death.getName())){
            if(bountyManager.getBountyAmount(death.getName()) != 0){
                CoinSystem.getEco().depositPlayer(death.getKiller(), bountyManager.getBountyAmount(death.getName()));
                death.getKiller().sendMessage("§aDu hast " + bountyManager.getBountyAmount(death.getName()) + " Coins erhalten für den Tod von " + death.getName());
            }
        }
    }

    @EventHandler
    public void onInvClick(InventoryClickEvent event){
        if(event.getView().getTitle().equalsIgnoreCase(INVENTORY_TITLE)) {
            event.setCancelled(true);
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if(!(sender instanceof Player)) return null;

        List<String> complets = new ArrayList<String>();

        if(args.length == 1){
            complets.clear();

            complets.add("add");

            if(bountyManager.getBountys() != null){
                if(bountyManager.getBountys().size() > 0){
                    complets.add("remove");
                }
            }

            complets.add("list");

            return complets;
        }

        if(args.length == 2 && args[0].equalsIgnoreCase("remove")){
            complets.clear();

            HashMap<String, Integer> bountys = bountyManager.getBountys();

            for(String s : bountys.keySet()){
                complets.add(s);
            }

            return complets;
        }
        return null;
    }
}
