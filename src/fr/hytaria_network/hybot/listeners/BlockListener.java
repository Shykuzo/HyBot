package fr.hytaria_network.hybot.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import fr.hytaria_network.hybot.Main;

public class BlockListener implements Listener {

	@EventHandler
	public void onPlace(BlockPlaceEvent event) {
		Main.getInstance().getLogManager().log(
				event.getPlayer(),
				event.getBlock(), 
				0
		).send(Main.getInstance().getConfigManager().getChannelID("LOGS_SERVEUR"));
	}
	
	@EventHandler
	public void onBreak(BlockBreakEvent event) {		
		Main.getInstance().getLogManager().log(
				event.getPlayer(),
				event.getBlock(),
				1
		).send(Main.getInstance().getConfigManager().getChannelID("LOGS_SERVEUR"));
	}
	
}
