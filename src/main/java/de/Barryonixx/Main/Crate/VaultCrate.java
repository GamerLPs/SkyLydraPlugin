package de.Barryonixx.Main.Crate;

import de.Barryonixx.Main.CoinSystem.CoinSystem;
import de.Barryonixx.Main.Listeners.ChatListener;
import de.Barryonixx.Main.SkyLydra;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class VaultCrate implements Listener, CommandExecutor {

    private static final String VNAME = "§eVote-Crate";

    private int TASKID, TASKID2;

    private final int SPIN_TIME = 25;

    private final int SPIN_DELAY = 5;

    private boolean isReady = false;
    private boolean isUsed = false;

    public ItemStack[] itemstacks = new ItemStack[] {
            CoinSystem.getMoney(5),
            CoinSystem.getMoney(20),
            CoinSystem.getMoney(5),
            CoinSystem.getMoney(10),
            ChatListener.getChampionBook(),
            CoinSystem.getMoney(5),
            CoinSystem.getMoney(50),
            CoinSystem.getMoney(20),
            CoinSystem.getMoney(10),
            CoinSystem.getMoney(10),
            CoinSystem.getMoney(5),
            CoinSystem.getMoney(20),
            CoinSystem.getMoney(5),
            CoinSystem.getMoney(50),

    };

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent event){
        if(!(event.getEntity() instanceof Villager)) return;
        Villager villager = (Villager) event.getEntity();

        if(!(villager.getCustomName().equals(VNAME))) return;
        
        if(event.getDamager() instanceof Player){
            Player player = (Player) event.getDamager();
            if(player.isOp()){
                villager.setHealth(0);
            }else{
                event.setCancelled(true);
                villager.setHealth(20);
            }
        }else{
            event.setCancelled(true);
            villager.setHealth(20);
        }
     }

    @EventHandler
    public void onInvClick(PlayerInteractEntityEvent event){
        if(event.getRightClicked() instanceof Villager){
            Villager villager = (Villager) event.getRightClicked();

            if(!(villager.getCustomName().equals(VNAME))){
                event.setCancelled(false);
                return;
            }

            if(isUsed){
                event.getPlayer().sendMessage("§cBitte warte noch einen Augenblick!");
                return;
            }

            ItemStack voteKey = new ItemStack(Material.TRIPWIRE_HOOK);
            ItemMeta key = voteKey.getItemMeta();
            key.setDisplayName("§8» §7Crate §8| §eSchlüssel");
            voteKey.setItemMeta(key);

            if(!(event.getPlayer().getItemInHand().equals(voteKey))){
                event.getPlayer().sendMessage("§8| §d§lVOTE §8» §7Du musst erst voten um dein Gewinn zu bekommen.");
                return;
            }else{
                if(event.getPlayer().getItemInHand().getAmount() >= 1){
                    event.getPlayer().getItemInHand().setAmount(event.getPlayer().getItemInHand().getAmount() - 1);
                }else{
                    event.getPlayer().getInventory().remove(event.getPlayer().getItemInHand());
                }

            }

            if(villager.getCustomName().equals(VNAME)){
                Inventory vault = Bukkit.createInventory(null, 9*5, VNAME);

                event.getPlayer().openInventory(vault);

                animateInv(vault, itemstacks, event.getPlayer());
            }
        }

    }

    @EventHandler
    public void onPreGet(InventoryClickEvent event){
        if(event.getView().getTitle().equals(VNAME)){
            if(!isReady){
                event.setCancelled(true);
            }
        }
    }

    private void animateInv(Inventory inventory, ItemStack[] items, Player player){
        isReady = false;
        isUsed = true;
        TASKID = Bukkit.getScheduler().scheduleAsyncRepeatingTask(SkyLydra.getInstance(), new Runnable() {
            int count = 0;
            int randomness = (int) (Math.random() * (20 - 0)) + 0;

            @Override
            public void run() {
                if(count <= randomness){
                    count++;
                }else{
                    showWin(inventory, player);
                    Bukkit.getScheduler().cancelTask(TASKID);
                }

                switch(count){
                    case 1:
                        inventory.setItem(4, items[0]);
                        inventory.setItem(13, items[1]);
                        inventory.setItem( 22, items[2]);
                        inventory.setItem(31, items[3]);
                        inventory.setItem(40, items[4]);
                        break;

                    case 2:
                        inventory.setItem(4, items[5]);
                        inventory.setItem(13, items[6]);
                        inventory.setItem( 22, items[7]);
                        inventory.setItem(31, items[8]);
                        inventory.setItem(40, items[9]);
                        break;

                    case 3:
                        inventory.setItem(4, items[10]);
                        inventory.setItem(13, items[11]);
                        inventory.setItem( 22, items[12]);
                        inventory.setItem(31, items[13]);
                        inventory.setItem(40, items[0]);
                        break;
                    case 4:
                        inventory.setItem(4, items[1]);
                        inventory.setItem(13, items[2]);
                        inventory.setItem( 22, items[3]);
                        inventory.setItem(31, items[4]);
                        inventory.setItem(40, items[5]);
                        break;
                    case 5:
                        inventory.setItem(4, items[6]);
                        inventory.setItem(13, items[7]);
                        inventory.setItem( 22, items[8]);
                        inventory.setItem(31, items[9]);
                        inventory.setItem(40, items[10]);
                        break;
                    case 6:
                        inventory.setItem(4, items[11]);
                        inventory.setItem(13, items[12]);
                        inventory.setItem( 22, items[13]);
                        inventory.setItem(31, items[0]);
                        inventory.setItem(40, items[1]);
                        break;

                    case 7:
                        inventory.setItem(4, items[2]);
                        inventory.setItem(13, items[3]);
                        inventory.setItem( 22, items[4]);
                        inventory.setItem(31, items[5]);
                        inventory.setItem(40, items[6]);
                        break;

                    case 8:
                        inventory.setItem(4, items[7]);
                        inventory.setItem(13, items[8]);
                        inventory.setItem( 22, items[9]);
                        inventory.setItem(31, items[10]);
                        inventory.setItem(40, items[11]);
                        break;
                    case 9:
                        inventory.setItem(4, items[12]);
                        inventory.setItem(13, items[13]);
                        inventory.setItem( 22, items[0]);
                        inventory.setItem(31, items[1]);
                        inventory.setItem(40, items[2]);
                        break;
                    case 10:
                        inventory.setItem(4, items[3]);
                        inventory.setItem(13, items[4]);
                        inventory.setItem( 22, items[5]);
                        inventory.setItem(31, items[6]);
                        inventory.setItem(40, items[7]);
                        break;
                    case 11:
                        inventory.setItem(4, items[8]);
                        inventory.setItem(13, items[9]);
                        inventory.setItem( 22, items[10]);
                        inventory.setItem(31, items[11]);
                        inventory.setItem(40, items[12]);
                        break;

                    case 12:
                        inventory.setItem(4, items[13]);
                        inventory.setItem(13, items[0]);
                        inventory.setItem( 22, items[1]);
                        inventory.setItem(31, items[2]);
                        inventory.setItem(40, items[3]);
                        break;

                    case 13:
                        inventory.setItem(4, items[4]);
                        inventory.setItem(13, items[5]);
                        inventory.setItem( 22, items[6]);
                        inventory.setItem(31, items[7]);
                        inventory.setItem(40, items[8]);
                        break;
                    case 14:
                        inventory.setItem(4, items[9]);
                        inventory.setItem(13, items[10]);
                        inventory.setItem( 22, items[11]);
                        inventory.setItem(31, items[12]);
                        inventory.setItem(40, items[13]);
                        break;
                    case 15:
                        inventory.setItem(4, items[0]);
                        inventory.setItem(13, items[1]);
                        inventory.setItem( 22, items[2]);
                        inventory.setItem(31, items[3]);
                        inventory.setItem(40, items[4]);
                        break;
                    case 16:
                        inventory.setItem(4, items[5]);
                        inventory.setItem(13, items[6]);
                        inventory.setItem( 22, items[7]);
                        inventory.setItem(31, items[8]);
                        inventory.setItem(40, items[9]);
                        break;

                    case 17:
                        inventory.setItem(4, items[10]);
                        inventory.setItem(13, items[11]);
                        inventory.setItem( 22, items[12]);
                        inventory.setItem(31, items[13]);
                        inventory.setItem(40, items[0]);
                        break;

                    case 18:
                        inventory.setItem(4, items[1]);
                        inventory.setItem(13, items[2]);
                        inventory.setItem( 22, items[3]);
                        inventory.setItem(31, items[4]);
                        inventory.setItem(40, items[5]);
                        break;
                    case 19:
                        inventory.setItem(4, items[6]);
                        inventory.setItem(13, items[7]);
                        inventory.setItem( 22, items[8]);
                        inventory.setItem(31, items[9]);
                        inventory.setItem(40, items[10]);
                        break;
                    case 20:
                        inventory.setItem(4, items[4]);
                        inventory.setItem(13, items[0]);
                        inventory.setItem( 22, items[1]);
                        inventory.setItem(31, items[2]);
                        inventory.setItem(40, items[3]);
                        break;
                    case 21:
                        inventory.setItem(4, items[0]);
                        inventory.setItem(13, items[1]);
                        inventory.setItem( 22, items[2]);
                        inventory.setItem(31, items[3]);
                        inventory.setItem(40, items[4]);
                        break;

                    case 22:
                        inventory.setItem(4, items[5]);
                        inventory.setItem(13, items[6]);
                        inventory.setItem( 22, items[7]);
                        inventory.setItem(31, items[8]);
                        inventory.setItem(40, items[9]);
                        break;

                    case 23:
                        inventory.setItem(4, items[10]);
                        inventory.setItem(13, items[11]);
                        inventory.setItem( 22, items[12]);
                        inventory.setItem(31, items[13]);
                        inventory.setItem(40, items[0]);
                        break;
                    case 24:
                        inventory.setItem(4, items[1]);
                        inventory.setItem(13, items[2]);
                        inventory.setItem( 22, items[3]);
                        inventory.setItem(31, items[4]);
                        inventory.setItem(40, items[5]);
                        break;
                    case 25:
                        inventory.setItem(4, items[6]);
                        inventory.setItem(13, items[7]);
                        inventory.setItem( 22, items[8]);
                        inventory.setItem(31, items[9]);
                        inventory.setItem(40, items[10]);
                        break;

                }
            }
        },20, SPIN_DELAY);
    }

    private void showWin(Inventory inventory, Player player){

        isUsed = false;

        player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 5, 5);

        ItemStack redGlas = new ItemStack(Material.RED_STAINED_GLASS);
        ItemStack yellowGlas = new ItemStack(Material.YELLOW_STAINED_GLASS);
        ItemStack greenGlas = new ItemStack(Material.LIME_STAINED_GLASS);


        inventory.removeItem(new ItemStack[]{
                inventory.getItem(13),
                inventory.getItem(4),
                inventory.getItem(31),
                inventory.getItem(40)

        });

        inventory.setItem(19, yellowGlas);
        inventory.setItem(20, greenGlas);
        inventory.setItem(21, redGlas);
        inventory.setItem(23, redGlas);
        inventory.setItem(24, yellowGlas);
        inventory.setItem(25, greenGlas);

        Bukkit.getScheduler().scheduleAsyncDelayedTask(SkyLydra.getInstance(), new Runnable() {
            @Override
            public void run() {
                player.getInventory().addItem(inventory.getItem(22));
            }
        }, 50);


    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (!player.isOp()) {
                return false;
            }

            Villager villager = (Villager) player.getWorld().spawnEntity(player.getLocation(), EntityType.VILLAGER);
            villager.setCustomName(VNAME);
            villager.setSilent(true);
            villager.setAI(false);
            player.sendMessage("§8| §d§lVOTE §8» §7Vote Crate erstellt.");
            return true;
        }
        return false;
    }

    public static String getVNAME() {
        return VNAME;
    }
}
