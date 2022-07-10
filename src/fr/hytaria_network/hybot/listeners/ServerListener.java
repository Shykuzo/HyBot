package fr.hytaria_network.hybot.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import fr.hytaria_network.hybot.Main;

public class ServerListener implements Listener {

	 @EventHandler
	 public void onJoin(PlayerJoinEvent event) {
		 Main.getInstance().getLogManager().log(
					event.getPlayer(),
					0
		).send(Main.getInstance().getConfigManager().getChannelID("LOGS_SERVEUR"));
	 }
	 
	 @EventHandler
	 public void onQuit(PlayerQuitEvent event) {
		 Main.getInstance().getLogManager().log(
					event.getPlayer(),
					1
		).send(Main.getInstance().getConfigManager().getChannelID("LOGS_SERVEUR"));
	 }
	
}
