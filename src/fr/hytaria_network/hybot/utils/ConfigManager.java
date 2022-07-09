package fr.hytaria_network.hybot.utils;

import fr.mrcubee.annotation.spigot.config.Config;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

public class ConfigManager {
	
	// ---------------------------------------- \\
	
			// ----- GLOBAL ----- \\
	
	@Config(path = "GLOBAL.PREFIX.PREFIX", color = true)
	private String prefix = "&e&lHytaria &6&l×";
	
	@Config(path = "GLOBAL.PREFIX.ERROR_PREFIX", color = true)
	private String errorPrefix = "&4&l(!)";
	
	@Config(path = "GLOBAL.WEBSITE", color = false)
	private String website = "www.hytaria.fr";
	
			// ----- DISCORD ----- \\

	@Config(path = "DISCORD.TOKEN", color = false)
	private String discordToken = "";
	
	@Config(path = "DISCORD.PREFIX", color = false)
	private String discordPrefix = ".";
	
	@Config(path = "DISCORD.STATUS", color = false)
	private String discordStatus = "ONLINE";
	
	@Config(path = "DISCORD.ACTIVITY.TYPE", color = false)
	private String discordActivityType = "WATCHING";
	
	@Config(path = "DISCORD.ACTIVITY.MESSAGE", color = false)
	private String discordActivityMessage = "{DISCORD_PREFIX}help | {WEBSITE}";
	
	// ---------------------------------------- \\
	
			// ----- GLOBAL ----- \\
	
	public String getPrefix() {
		return prefix;
	}
	
	public String getErrorPrefix() {
		return errorPrefix;
	}
	
	public String getWebsite() {
		return website;
	}
	
			// ----- DISCORD ----- \\
	
	public String getDiscordToken() {
		return discordToken;
	}
	
	public String getDiscordPrefix() {
		return discordPrefix;
	}
	
	public OnlineStatus getDiscordStatus() throws Exception {
		switch(discordStatus) {
			case "ONLINE":
				return OnlineStatus.ONLINE;
				
			case "DO_NOT_DISTURB":
				return OnlineStatus.DO_NOT_DISTURB;
				
			case "IDLE":
				return OnlineStatus.IDLE;
				
			case "INVISIBLE":
				return OnlineStatus.INVISIBLE;
				
			default:
				throw new Exception("Invalid Statut");
		}
	}
	
	public Activity getDiscordActivityType() throws Exception {
		switch(discordActivityType) {
			case "PLAYING":
				return Activity.playing(getDiscordActivityMessage());
				
			case "LISTENING":
				return Activity.listening(getDiscordActivityMessage());
				
			case "WATCHING":
				return Activity.watching(getDiscordActivityMessage());
				
			default:
				throw new Exception("Invalid Activity");
		}
	}
	
	public String getDiscordActivityMessage() {
		return discordActivityMessage
				.replace("{DISCORD_PREFIX}", getDiscordPrefix())
				.replace("{WEBSITE}", getWebsite());
	}
	
}
