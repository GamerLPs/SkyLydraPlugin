package de.Barryonixx.Main.SystemCommands;

import de.Barryonixx.Main.Data;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GameModeCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        Player p = (Player)sender;
        if ((cmd.getName().equalsIgnoreCase("gamemode")) || (cmd.getName().equalsIgnoreCase("gm"))) {
            if (p.hasPermission("system.gamemode")) {
                if (args.length == 0)
                    p.sendMessage(Data.prefix+"Nutze§8: §7/gm <§b0 §7| §b1 §7| §b2 §7| §b3§7> <Spieler>");
                else if (args.length == 1) {
                    if ((args[0].equalsIgnoreCase("0")) || (args[0].equalsIgnoreCase("survival"))) {
                        if (p.hasPermission("system.gamemode.survival")) {
                            p.setGameMode(GameMode.SURVIVAL);
                            p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("§7» §7Du bist nun im §eSurvivalmodus§7!"));
                            //p.sendMessage("§7» §7Du bist nun im §eSurvivalmodus§7!");
                        } else {
                            p.sendMessage("§7» §7Du darfst den Survivalmodus nicht betreten!");
                        }
                    } else if ((args[0].equalsIgnoreCase("1")) || (args[0].equalsIgnoreCase("creative"))) {
                        if (p.hasPermission("system.gamemode.creative")) {
                            p.setGameMode(GameMode.CREATIVE);
                            p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("§7» §7Du bist nun im §eKreativmodus§7!"));
                            //p.sendMessage("§7» §7Du bist nun im §eKreativmodus§7!");
                        } else {
                            p.sendMessage("§7» §7Du darfst den Kreativmodus nicht betreten!");
                        }
                    } else if ((args[0].equalsIgnoreCase("2")) || (args[0].equalsIgnoreCase("adventure"))) {
                        if (p.hasPermission("system.gamemode.adventure")) {
                            p.setGameMode(GameMode.ADVENTURE);
                            p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("§7» §7Du bist nun im §eAbenteuermodus§7!"));
                            //p.sendMessage("§7» §7Du bist nun im §eAbenteuermodus§7!");
                        } else {
                            p.sendMessage("§7» §7Du darfst den Abenteuermodus nicht betreten!");
                        }
                    } else if ((args[0].equalsIgnoreCase("3")) || (args[0].equalsIgnoreCase("spectator"))) {
                        if (p.hasPermission("system.gamemode.spectator")) {
                            p.setGameMode(GameMode.SPECTATOR);
                            p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("§7» §7Du bist nun im §eZuschauermodus§7!"));
                            //p.sendMessage("§7» §7Du bist nun im §eZuschauermodus§7!");
                        } else {
                            p.sendMessage("§7» §7Du darfst den Zuschauermodus nicht betreten!");
                        }
                    }
                    else p.sendMessage(Data.prefix+"Nutze§8: §7/gm <§b0 §7| §b1 §7| §b2 §7| §b3§7> <Spieler>");
                }
                if (args.length == 2) {
                    Player t = Bukkit.getPlayer(args[1]);
                    if ((args[0].equalsIgnoreCase("0")) || (args[0].equalsIgnoreCase("survival"))) {
                        if (p.hasPermission("system.gamemode.survival.other")) {
                            if (t != null) {
                                t.setGameMode(GameMode.SURVIVAL);
                                t.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("§7» Du wurdest in den §eSurvivalmodus §7gesetzt!"));
                                //t.sendMessage("§7Du wurdest von §a" + p.getName() + " §7in den §eSurvivalmodus §7gesetzt!");

                                p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("§7» Du hast §a" + t.getName() + " §7in den §eSurvivalmodus §7gesetzt!"));
                                //p.sendMessage("§7» Du hast §a" + t.getName() + " §7in den §eSurvivalmodus §7gesetzt!");
                            } else {
                                p.sendMessage("§cDu bist kein Spieler!");
                            }
                        }
                        else p.sendMessage("§7Du darfst keinen anderen Spieler in den Survivalmodus setzten!");
                    }
                    else if ((args[0].equalsIgnoreCase("1")) || (args[0].equalsIgnoreCase("creative"))) {
                        if (p.hasPermission("system.gamemode.creative.other")) {
                            if (t != null) {
                                t.setGameMode(GameMode.CREATIVE);
                                t.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("§7» Du wurdest in den §eKreativmodus §7gesetzt!"));
                                //t.sendMessage("§7Du wurdest von §a" + p.getName() + " §7in den §eKreativmodus §7gesetzt!");
                                p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("§7» Du hast §a" + t.getName() + " §7in den §eKreativmodus §7gesetzt!"));
                                //p.sendMessage("§7Du hast §a" + t.getName() + " §7in den §eKreativmodus §7gesetzt!");
                            } else {
                                p.sendMessage("§cDu bist kein Spieler!");
                            }
                        }
                        else p.sendMessage("§7Du darfst keinen anderen Spieler in den Kreativmodus setzten!");
                    }
                    else if ((args[0].equalsIgnoreCase("2")) || (args[0].equalsIgnoreCase("adventure"))) {
                        if (p.hasPermission("system.gamemode.adventure.other")) {
                            if (t != null) {
                                t.setGameMode(GameMode.ADVENTURE);
                                t.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("§7» Du wurdest in den §eAbenteuermodus §7gesetzt!"));
                                //t.sendMessage("§7Du wurdest von §a" + p.getName() + " §7in den §eAbentuermodus §7gesetzt!");
                                p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("§7» Du hast §a" + t.getName() + " §7in den §eAbenteuermodus §7gesetzt!"));
                                //p.sendMessage("§7Du hast §a" + t.getName() + " §7in den §eAbenteuermodus §7gesetzt!");
                            } else {
                                p.sendMessage("§cDu bist kein Spieler!");
                            }
                        }
                        else p.sendMessage("§7Du darfst keinen anderen Spieler in den Abenteuermodus setzten!");
                    }
                    else if ((args[0].equalsIgnoreCase("3")) || (args[0].equalsIgnoreCase("spectator"))) {
                        if (p.hasPermission("system.gamemode.spectator.other")) {
                            if (t != null) {
                                t.setGameMode(GameMode.SPECTATOR);
                                t.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("§7» Du wurdest in den §eZuschauermodus §7gesetzt!"));
                                //t.sendMessage("§7Du wurdest von §a" + p.getName() + " §7in den §eZuschauermodus §7gesetzt!");
                                p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("§7» Du hast §a" + t.getName() + " §7in den §eZuschauermodus §7gesetzt!"));
                                //p.sendMessage("§7Du hast §a" + t.getName() + " §7in den §eZuschauermodus §7gesetzt!");
                            } else {
                                p.sendMessage("§cDu bist kein Spieler!");
                            }
                        }
                        else p.sendMessage("§7Du darfst keinen anderen Spieler in den Zuschauermodus setzten!");
                    }
                    else
                        p.sendMessage(Data.prefix+"Nutze§8: §7/gm <§b0 §7| §b1 §7| §b2 §7| §b3§7> <Spieler>");
                }
            }
            else {
                p.sendMessage(Data.prefix+Data.NoPerm);
            }
        }
        return false;
    }

}
