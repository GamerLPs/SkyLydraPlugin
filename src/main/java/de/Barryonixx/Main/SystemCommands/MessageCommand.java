package de.Barryonixx.Main.SystemCommands;

import de.Barryonixx.Main.Data;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;

public class MessageCommand implements CommandExecutor {

    public static HashMap<Player, Player> reply = new HashMap();
    public static String msgprefix = "§8<§e§lMSG§8> ";
    static ArrayList<Player> msg = new ArrayList();

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        if ((args.length == 0) || ((args.length == 1) && (!args[0].equalsIgnoreCase("toggle")))) {
            p.sendMessage(Data.prefix+"Nutze§8: §7/msg §8(§7Spieler§8) §8(§7Nachricht§8)");
        } else if ((args.length == 1) && (args[0].equalsIgnoreCase("toggle"))) {
            if (msg.contains(p)) {
                msg.remove(p);
                p.sendMessage(msgprefix + "§7Du erhälst nun wieder Nachrichten!");
            } else {
                msg.add(p);
                p.sendMessage(msgprefix + "§7Du erhälst nun keine Nachrichten mehr!");
            }
        } else if (args.length >= 1) {
            Player t = Bukkit.getPlayer(args[0]);
            if (t != null) {
                if (t != p) {
                    if (!msg.contains(t)) {
                        StringBuilder sb = new StringBuilder();
                        int i = 1;
                        while (i < args.length) {
                            sb.append(args[i] + " ");
                            i++;
                        }
                        String message = sb.toString();
                        p.sendMessage(msgprefix + "§cDu §8» §6" + t.getName() + "§f: " + message);
                        t.sendMessage(msgprefix + "§6" + p.getName() + " §8» §7dir§f: " + message);
                        reply.put(t, p);
                        reply.put(p, t);
                    } else {
                        p.sendMessage(msgprefix + "§7Der Spieler erhält keine Nachrichten");
                    }
                } else p.sendMessage(msgprefix + "§7Du kannst dir selbst keine Nachrichten schreiben!");
            } else {
                p.sendMessage(msgprefix + "§cDieser Spieler ist nicht Online!");
            }
        }
        return false;
    }
}
