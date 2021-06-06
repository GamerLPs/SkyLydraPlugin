package de.Barryonixx.Main.SystemCommands;

import de.Barryonixx.Main.Data;
import de.Barryonixx.Main.FileManager;
import de.Barryonixx.Main.SkyLydra;
import de.Barryonixx.Main.report.ReportCause;
import de.Barryonixx.Main.report.ReportInventory;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ReportCommand implements CommandExecutor, TabCompleter {
    private static final String RPREFIX = "§8| §e§lREPORT §8» §7";

    private HashMap<String, String> reports = new HashMap<>();

    private SkyLydra skyLydra = SkyLydra.getInstance();

    private YamlConfiguration cfg = FileManager.cfg;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)){
            sender.sendMessage("§cDu musst diesen Befehl als Spieler ausführen!");
            return false;
        }

        Player player = (Player) sender;

        if(args.length == 2){
            String playerN = args[0];
            Player toReport = Bukkit.getPlayer(playerN);

            if(toReport == player){
                player.sendMessage(RPREFIX + "§cDu kannst dich nicht selbst reporten!");
                return false;
            }

            List<String> allPlayers = new ArrayList<>();

            Bukkit.getOnlinePlayers().forEach(player1 -> {
                if(!(player1 == null)){
                    allPlayers.add(player1.getName());
                }
            });

            cfg.set("teammates", allPlayers);
            FileManager.saveAllFiles();

            if(toReport != null){
                String cause = args[1];

                cause = ReportCause.getReportCause(cause);

                if(cause == null){
                    sendUsage(player);
                    return false;
                }

                List<String> teammates = cfg.getStringList("teammates");
                List<Player> sups = new ArrayList<>();

                for(String mate : teammates){
                    Player sup = Bukkit.getPlayer(mate);
                    if(sup != null){
                        sups.add(sup);
                    }
                }

                ReportInventory.add(toReport, player.getName(), cause);

                player.sendMessage( RPREFIX + "§c" + playerN + " §7Reportet mit dem grund: §e" + cause);


                reports.put(toReport.getName(), cause);
                return true;
            }else{
                player.sendMessage(RPREFIX + "§cFehler: Spieler nicht gefunde!");
                return false;
            }
        }

        if(args.length == 1 && args[0].equalsIgnoreCase("list")) {
            Inventory inv = Bukkit.getServer().createInventory(player, 27, "Reports:");
            inv.setContents(ReportInventory.getContents());
            player.openInventory(inv);

            /*

            for(Entry<String, String> report : reports.entrySet()) {
                List<String> teammates = cfg.getStringList("teammates");
                for(String mate : teammates){
                    Player sup = Bukkit.getPlayer(mate);
                    if(sup != null){
                        sup.sendMessage(RPREFIX + report.getKey() + " §awegen " + report.getValue() + " §areportet.");
                    }
                }
            }

            */
        }else{
            player.sendMessage(Data.prefix + "Nutze§8: §7/report §8(§7Spieler§8) (§7Grund§8)");
        }
        return false;
    }

    private void sendUsage(Player player){
        player.sendMessage("§c-----Report-----");
        player.sendMessage("§6/report SPIELER GRUND \n");
        player.sendMessage("Gültige Reportgründe: \n ");
        player.sendMessage("§aFLYING, KILLAURA, HACKING, TEAMING, CHEATING, AUTOCLICKER");
        player.sendMessage("§c----------------");
    }

    public static String getRPREFIX() {
        return RPREFIX;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        ArrayList<String> completsN = new ArrayList<>();
        ArrayList<String> completsS = new ArrayList<>();

        if(!(sender instanceof Player)){
            return null;
        }

        Player p = (Player) sender;
        if(p.hasPermission("skylydra.report.list") && args.length == 1){
            completsS.add("list");
            Bukkit.getOnlinePlayers().forEach(player -> {
                completsS.add(player.getName());
            });
            return completsS;
        }else if(args.length == 2){
            for(ReportCause rc : ReportCause.values()){
                completsN.add(rc.toString());
            }
            return completsN;
        }else if(args.length == 1){
            Bukkit.getOnlinePlayers().forEach(player -> {
                completsN.add(player.getName());
            });
            return completsN;
        }
        return null;
    }
}
