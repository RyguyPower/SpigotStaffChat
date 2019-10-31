package me.ryguy.diviniuschat;


import java.util.ArrayList;
import org.bukkit.plugin.java.JavaPlugin;

import me.ryguy.diviniuschat.commands.StaffChatCommand;
import me.ryguy.diviniuschat.listeners.PlayerChatListener;



public class StaffChat
  extends JavaPlugin
{
  private static StaffChat instance;
  private static ArrayList<String> staffChatPlayers;
  
  public void onEnable() {
    getServer().getPluginManager().registerEvents(new PlayerChatListener(), this);
    getCommand("staffchat").setExecutor(new StaffChatCommand());
    instance = this;
    staffChatPlayers = new ArrayList<String>();
    saveDefaultConfig();
    getServer().getLogger().info("Registered 1 command and 1 event!");
    getLogger().info("Finished setting up!");
  }
  
  public static StaffChat getInstance() { return instance; }
  public static ArrayList<String> getStaffChatPlayers() { return staffChatPlayers; }
}
