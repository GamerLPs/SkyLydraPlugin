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

public class DiscordCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player){
            Player player = (Player)sender;
            if(command.getName().equalsIgnoreCase("discord")){
                TextComponent ouu = new TextComponent("");
                TextComponent msg1 = new TextComponent(Data.prefix+ "Unser Discord-Server erreichst du hier.");
                TextComponent msg2 = new TextComponent("§8                        ▪ §c§oKlick hier");
                msg2.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§a§oKlick auf mich!").create()));
                msg2.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://discord.gg/FTYFJyBNuT"));

                player.spigot().sendMessage(ouu);
                player.spigot().sendMessage(ouu);
                player.spigot().sendMessage(ouu);
                player.spigot().sendMessage(msg1);
                player.spigot().sendMessage(msg2);
                player.spigot().sendMessage(ouu);
            }
        }else{
            sender.sendMessage("Du musst ein Spieler sein!");
        }
        return false;
    }
}
