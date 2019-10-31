package me.ryguy.diviniuschat.commands;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.ryguy.diviniuschat.StaffChat;
import me.ryguy.diviniuschat.util.StringUtils;



public class StaffChatCommand
  implements CommandExecutor
{
  public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
    if (sender instanceof org.bukkit.command.ConsoleCommandSender) {
      if (args.length == 0) {
        sender.sendMessage("/staffchat <message>");
        return true;
      } 
      String message = String.join(" ", args);
      String format = StaffChat.getInstance().getConfig().getString("staff-chat-format");
      format = format.replace("%sender-name%", "CONSOLE");
      format = format.replace("%message%", message);
      for (Player all : StaffChat.getInstance().getServer().getOnlinePlayers()) {
        if (all.hasPermission("adminc.staffchat")) {
          all.sendMessage(StringUtils.colorize(format));
        }
      } 
      System.out.println(ChatColor.stripColor(StringUtils.colorize(format)));
      return true;
    } 
    if (!sender.hasPermission("adminc.staffchat")) {
      sender.sendMessage(StringUtils.colorize("&cNo permission."));
      return true;
    } 
    if (args.length == 0) {
      if (!StaffChat.getStaffChatPlayers().contains(sender.getName())) {
        StaffChat.getStaffChatPlayers().add(sender.getName());
        String msg = StaffChat.getInstance().getConfig().getString("staff-chat-toggle-on-msg");
        sender.sendMessage(StringUtils.colorize(msg));
      } else {
        StaffChat.getStaffChatPlayers().remove(sender.getName());
        String msg = StaffChat.getInstance().getConfig().getString("staff-chat-toggle-off-msg");
        sender.sendMessage(StringUtils.colorize(msg));
      } 
    } else {
      String message = String.join(" ", args);
      String format = StaffChat.getInstance().getConfig().getString("staff-chat-format");
      format = format.replace("%sender-name%", sender.getName());
      format = format.replace("%message%", message);
      
      for (Player all : StaffChat.getInstance().getServer().getOnlinePlayers()) {
        if (all.hasPermission("adminc.staffchat")) {
          all.sendMessage(StringUtils.colorize(format));
        }
      } 
      System.out.println(ChatColor.stripColor(StringUtils.colorize(format)));
    } 
    return true;
  }
}