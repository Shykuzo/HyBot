package fr.hytaria_network.hybot.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import fr.hytaria_network.hybot.Main;

public class ChatListener implements Listener {
	
	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent event) {
		Main.getInstance().getLogManager().log(
				event.getPlayer(), 
				event.getMessage()
		).send(Main.getInstance().getConfigManager().getChannelID("LOGS_CHAT"));
	}

}
