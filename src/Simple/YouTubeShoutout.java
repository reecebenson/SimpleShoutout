package Simple;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.logging.Logger;

public class YouTubeShoutout extends JavaPlugin implements Listener {
	public Logger logger;
	Server server = Bukkit.getServer();
	long lastBroadcast = 0;
	boolean bcFinished = false;
	
	public void onEnable() {
		Bukkit.getPluginManager().registerEvents(this,this);
		logger = getLogger();
		logger.info("[SIMPLE] 'YouTube Shoutout' enabled!");
		// console.sendMessage("[SIMPLE] 'YouTube Shoutout' enabled!");
	}
	
	public void onDisable() {
		logger = getLogger();
		logger.info("[SIMPLE] 'YouTube Shoutout' disabled!");
		//console.sendMessage("[SIMPLE] 'YouTube Shoutout' disabled!");
	}
	
	@EventHandler
	public boolean onCommand(CommandSender sender, Command cmd, String lavel, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(cmd.getName().equalsIgnoreCase("channelshout")) {
				p.sendMessage(ChatColor.RED + "You cannot manually send YouTube Channel shoutouts!");
			}
		}else{
			logger = getLogger();
			logger.info("[SIMPLE] Requesting shoutout...");
			String[] youtubeUrl = args[1].split("/user/");
			String[] youtubeCUrl = args[1].split("/channel/");
			String player_name = args[0];
			boolean notUserUrl = false;
			
			// clear the chat
			for(int i = 0; i < 18; i++) { server.broadcastMessage(" "); }
			server.broadcastMessage(ChatColor.BOLD + "" + ChatColor.LIGHT_PURPLE + "> Check out this " + ChatColor.WHITE + "You" + ChatColor.RED + "Tube" + ChatColor.LIGHT_PURPLE + " Channel");
			
			for(int i = 0; i < youtubeUrl.length; i++) {
				if(youtubeUrl[i].contains("/channel/")) { notUserUrl = true; }else{
					// /user/ URL
					if(i == 1) {
						server.broadcastMessage("  " + ChatColor.YELLOW + "Check out " + ChatColor.AQUA + player_name + "'s" + ChatColor.YELLOW + " Channel by visiting here:");
						server.broadcastMessage(ChatColor.AQUA + "  http://youtube.com/user/" + youtubeUrl[i]);
					}
				}
			}
			
			if(notUserUrl == true) {
				// /channel/ URL
				for(int i = 0; i < youtubeCUrl.length; i++) {
					if(i == 1) {
						server.broadcastMessage("  " + ChatColor.YELLOW + "Check out " + ChatColor.AQUA + player_name + "'s" + ChatColor.YELLOW + " Channel by visiting here:");
						server.broadcastMessage(ChatColor.AQUA + "  http://youtube.com/channel/" + youtubeCUrl[i]);
					}
				}
			}

			//server.broadcastMessage("        ");
			//server.broadcastMessage(ChatColor.BOLD + "" + ChatColor.RED + "> Make sure to subscribe and comment saying '" + ChatColor.GREEN + "IndieCraft" + ChatColor.RED + "'!");
			server.broadcastMessage("        ");
			server.broadcastMessage("--------------------------------------------------");
			lastBroadcast = System.currentTimeMillis() / 1000L;
			logger.info("[SIMPLE] Shoutout complete!");
		}
		return false;
	}
	
	@EventHandler
	public void onPlayerChatEvent(AsyncPlayerChatEvent e){
		if(e.isCancelled()) return;

        // Add 3 seconds and see if its underneath the lastBroadcast timestamp
    	long checkStamp = (lastBroadcast + 4) - (System.currentTimeMillis() / 1000L);
        if(checkStamp > 0) {
        	Player p = (Player)e.getPlayer();
        	p.sendMessage(ChatColor.DARK_RED + "You cannot send a message at this time.");
        	e.setCancelled(true);
        }
	}
}
