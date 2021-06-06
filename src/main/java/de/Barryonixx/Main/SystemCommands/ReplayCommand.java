package de.Barryonixx.Main.SystemCommands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ReplayCommand implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        Player t = MessageCommand.reply.get(p);
        if (t != null) {
            if (!MessageCommand.msg.contains(t)) {
                StringBuilder sb = new StringBuilder();
                int i = 0;
                while (i < args.length) {
                    sb.append(args[i] + " ");
                    i++;
                }
                String message = sb.toString();
                p.sendMessage(MessageCommand.msgprefix + "§cDu §8» §e" + t.getName() + "§f: " + message);
                t.sendMessage(MessageCommand.msgprefix + "§e" + p.getName() + " §8» §cdir§f: " + message);
                MessageCommand.reply.put(t, p);
            } else {
                p.sendMessage(MessageCommand.msgprefix + "§7Der Spieler erhält keine Nachrichten");
            }
        } else p.sendMessage("§cDu hast bisher noch keine Nachricht erhalten!");

        return false;
    }
}
