package me.ryguy.diviniuschat.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import me.ryguy.diviniuschat.StaffChat;
import me.ryguy.diviniuschat.util.StringUtils;

public class PlayerChatListener
  implements Listener
{
  @EventHandler
  public void onPlayerChat(AsyncPlayerChatEvent e) {
    if (e.getMessage().startsWith("/"))
      return;  Player sender = e.getPlayer();
    if (e.getMessage().startsWith("@")) {
      if (!sender.hasPermission("adminc.staffchat"))
        return;  e.setCancelled(true);
      String format = StaffChat.getInstance().getConfig().getString("staff-chat-format");
      format = format.replace("%sender-name%", sender.getName());
      format = format.replace("%message%", e.getMessage());
      format = format.replace("@", "");
      for (Player all : StaffChat.getInstance().getServer().getOnlinePlayers()) {
        if (all.hasPermission("adminc.staffchat")) {
          all.sendMessage(StringUtils.colorize(format));
        }
      } 
      System.out.println(ChatColor.stripColor(StringUtils.colorize(format)));
      return;
    } 
    if (StaffChat.getStaffChatPlayers().contains(sender.getName())) {
      e.setCancelled(true);
      String format = StaffChat.getInstance().getConfig().getString("staff-chat-format");
      format = format.replace("%sender-name%", sender.getName());
      format = format.replace("%message%", e.getMessage());
      for (Player all : StaffChat.getInstance().getServer().getOnlinePlayers()) {
        if (all.hasPermission("adminc.staffchat")) {
          all.sendMessage(StringUtils.colorize(format));
        }
      } 
      System.out.println(ChatColor.stripColor(StringUtils.colorize(format)));
      return;
    } 
  }
}