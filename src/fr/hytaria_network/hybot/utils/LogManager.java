package fr.hytaria_network.hybot.utils;

import java.awt.Color;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import fr.hytaria_network.hybot.Main;
import net.dv8tion.jda.api.EmbedBuilder;

public class LogManager {
	
	private EmbedBuilder embed;
	
	// ---------------------------------------- \\
	
	public LogManager() {
		this.embed = new EmbedBuilder();
	}
	
	// ---------------------------------------- \\
	
	public LogManager log(Player player, int type) {
		this.embed.setTitle(Main.getInstance().getConfigManager().getDiscordEmote("CUSTOM_CHAT") + " | " + "Information");
		
		embed.setDescription(
				"\n" + "× Date : **" + Main.getInstance().getConfigManager().getFormattedDate() + "**"
				+ "\n" + "× Pseudo : **" + player.getName() + "**"
		);
		
		this.embed.setColor(type == 0 ? Color.GREEN : Color.RED);
		
		return this;
	}
	
	public LogManager log(Player player, String message) {
		this.embed.setTitle(Main.getInstance().getConfigManager().getDiscordEmote("CUSTOM_CHAT") + " | " + "Information");
		
		embed.setDescription(
				"\n" + "× Serveur : **" + player.getWorld().getName() + "**"
				+ "\n" + "× Date : **" + Main.getInstance().getConfigManager().getFormattedDate() + "**"
				+ "\n" + "× Pseudo : **" + player.getName() + "**"
				+ "\n\n" + "× Message : **" + message + "**"
		);
		
		this.embed.setColor(Color.WHITE);
		
		return this;
	}
	
	public LogManager log(Player player, Block block, int type) {
		this.embed.setTitle(Main.getInstance().getConfigManager().getDiscordEmote("CUSTOM_CHAT") + " | " + "Information");
		
		String gamemode = null;
		String location = String.format(
				"(%s) | X : %s / Y : %s / Z : %s",
				block.getWorld().toString(),
				block.getX(),
				block.getY(),
				block.getZ()
		);
		
		switch(player.getGameMode()) {
			case SURVIVAL:
				gamemode = "Survie";
				break;
			case CREATIVE:
				gamemode = "Créatif";
				break;
			case ADVENTURE:
				gamemode = "Aventure";
				break;
			case SPECTATOR:
				gamemode = "Spectateur";
				break;
		}
		
		embed.setDescription(
				"\n" + "× Serveur : **" + player.getWorld().getName() + "**"
				+ "\n" + "× Date : **" + Main.getInstance().getConfigManager().getFormattedDate() + "**"
				+ "\n" + "× Pseudo : **" + player.getName() + "**"
				+ "\n\n" + "× Mode de Jeu : **" + gamemode + "**"
				+ "\n" + "× Type de Bloc : **" + block.getType() + "**"
				+ "\n" + "× Position du Bloc : **" + location + "**"
		);
		
		this.embed.setColor(type == 0 ? Color.GREEN : Color.RED);
		
		return this;
	}
	
	// ---------------------------------------- \\
	
	public void send(long channelID) {
		Main.getInstance().getDiscordAPI().getTextChannelById(channelID).sendMessageEmbeds(this.embed.build()).queue();
	}

}
