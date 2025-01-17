package server.natural.chatwithgroup.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import server.natural.chatwithgroup.Utils;

import java.io.File;

public class CommandCWG implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(strings.length != 1){
            return false;
        }

        if (strings[0].equalsIgnoreCase("version")) {
            strings[0] = "ver";
        } else if (strings[0].equalsIgnoreCase("rl")) {
            strings[0] = "reload";

        }

        if (strings.length == 1) {
            switch (strings[0].toLowerCase()) {
                case "reload":
                    if (commandSender.hasPermission("cwg.reload")) {
                        commandSender.sendMessage(ChatColor.GREEN + "Reloading Config File...");
                        File configFile = new File(Bukkit.getPluginManager().getPlugin("ChatWithGroup").getDataFolder(), "config.yml");
                        if (configFile.exists()) {
                            try {
                                Utils.config.load(configFile);
                                commandSender.sendMessage(ChatColor.GREEN + "Config File Reloaded Completed!");
                            } catch (Exception e) {
                                commandSender.sendMessage(ChatColor.RED + "An Exception happened. Config file can't reload");
                                commandSender.sendMessage(ChatColor.RED + "Exception Message:");
                                commandSender.sendMessage(ChatColor.RED + e.getMessage());
                            }
                        }
                        commandSender.sendMessage(Utils.reloadCache());
//                        File cacheFile = Utils.Cfile;
//                        commandSender.sendMessage(ChatColor.GREEN + "Reloading Cache File...");
//                        if (cacheFile.exists()) {
//                            try {
//                                Utils.cacheFile.load(cacheFile);
//                                commandSender.sendMessage(ChatColor.GREEN + "Cache File Reloaded Completed!");
//                            } catch (Exception e) {
//                                commandSender.sendMessage(ChatColor.RED + "An Exception happened. Cache file can't reload");
//                                commandSender.sendMessage(ChatColor.RED + "Exception Message:");
//                                commandSender.sendMessage(ChatColor.RED + e.getMessage());
//                            }
//                        }
//                        File MFCFile = Utils.MFCFile;
//                        if (MFCFile.exists()) {
//                            try {
//                                Utils.mfcfc.load(MFCFile);
//                                commandSender.sendMessage(ChatColor.GREEN + "Cache File Reloaded Completed");
//                            } catch (Exception e) {
//                                commandSender.sendMessage(ChatColor.RED + "An Exception happened. Message Forwarding Cache file can't reload");
//                                commandSender.sendMessage(ChatColor.RED + "Exception Message:");
//                                commandSender.sendMessage(ChatColor.RED + e.getMessage());
//                            }
//                        }
//                        File PCCFile = Utils.PCCFile;
//                        if (PCCFile.exists()) {
//                            try {
//                                Utils.pccfc.load(PCCFile);
//                                commandSender.sendMessage(ChatColor.GREEN + "Cache File Reloaded Completed");
//                            } catch (Exception e) {
//                                commandSender.sendMessage(ChatColor.RED + "An Exception happened. Player Chancel Cache file can't reload");
//                                commandSender.sendMessage(ChatColor.RED + "Exception Message:");
//                                commandSender.sendMessage(ChatColor.RED + e.getMessage());
//                            }
//                        }
                    } else {
                        commandSender.sendMessage(ChatColor.RED + Utils.NoPermission);
                    }
                    return true;
                case "ver":
                    String group = null;
                    String owner = null;
                    for(Long l:Utils.group){
                        if(group==null) group= String.valueOf(l);
                        else group = group + "," + l;
                    }
                    for(Long l:Utils.owner){
                        if(owner==null)owner = String.valueOf(l);
                        else owner = owner + "," + l;
                    }
                    if (commandSender.hasPermission("cwg.ver")) {
                        commandSender.sendMessage("Server Version: " + Bukkit.getVersion());
                        commandSender.sendMessage("Server Port: " + Bukkit.getPort());
                        commandSender.sendMessage("CWG Version: " + Utils.ver);
                        commandSender.sendMessage("Is it a Beta Version: " + Utils.isBetaVersion);
                        commandSender.sendMessage("The config-ver in the config.yml of CWG is：" + Utils.config.getInt("config-ver"));
                        commandSender.sendMessage("The Group I listen: " + group);
                        commandSender.sendMessage("The owner of this robot: " + owner);
                        commandSender.sendMessage("ChatWithGroup v" + Utils.ver + " was made by NaT_Jerry and Novau233");
                    } else {
                        commandSender.sendMessage(ChatColor.RED + "你没有权限");
                    }
                    return true;
                default:
                    if (commandSender.hasPermission("cwg.reload") || commandSender.hasPermission("cwg.ver")) {
                        commandSender.sendMessage(ChatColor.RED + "你隔着说啥呢");
                        return false;
                    } else {
                        commandSender.sendMessage(ChatColor.RED + Utils.NoPermission);
                        return true;
                    }
            }
        } else {
            commandSender.sendMessage(ChatColor.RED + "格式错误");
            return false;
        }
    }
}
