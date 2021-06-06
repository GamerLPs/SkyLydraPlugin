package de.Barryonixx.Main.SystemCommands;

import de.Barryonixx.Main.Data;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.awt.*;

public class VoteCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player){
            Player player = (Player)sender;
            if(command.getName().equalsIgnoreCase("vote")){
                TextComponent msg1 = new TextComponent("§8                        ▪ §c§oKlick hier");
                msg1.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§a§oKlick auf mich!").create()));
                msg1.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://sky-lydra.de/index.php?votes/"));


                player.sendMessage("");
                player.sendMessage("");
                player.sendMessage("");
                player.sendMessage(Data.prefix+ "Deine Belohnung anfordern mit §c/claim");
                player.spigot().sendMessage(msg1);
                player.sendMessage("");
                player.sendMessage("");
            }
        }else{
            sender.sendMessage("Du musst ein Spieler sein!");
        }
        return false;
    }
}
