package fr.hytaria_network.hybot;

import org.bukkit.plugin.java.JavaPlugin;

import fr.hytaria_network.hybot.utils.ConfigManager;
import fr.mrcubee.annotation.spigot.config.ConfigAnnotation;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class Main extends JavaPlugin {
	
	private static Main instance;
	private ConfigManager configManager;
	private JDA discordAPI;
	
	// ---------------------------------------- \\
	
	@Override
	public void onEnable() {
		instance = this;
		configManager = new ConfigManager();
		
		ConfigAnnotation.loadClass(getConfig(), getConfigManager());
		saveDefaultConfig();
		
		try {
			isDiscordTokenDefined();
			
			discordAPI = JDABuilder
					.createDefault(configManager.getDiscordToken())
					.setStatus(configManager.getDiscordStatus())
					.setActivity(configManager.getDiscordActivity())
					.enableIntents(
							GatewayIntent.GUILD_MEMBERS,
							GatewayIntent.GUILD_MESSAGES,
							GatewayIntent.GUILD_PRESENCES
					)
					.build();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
	
	// ---------------------------------------- \\
	
	public void isDiscordTokenDefined() throws Exception {
		if(configManager.getDiscordToken() == null) {
			throw new Exception("Token Undefined");
		}
	}
	
	// ---------------------------------------- \\
	
	public static Main getInstance() {
		return instance;
	}
	
	public JDA getDiscordAPI() {
		return discordAPI;
	}
	
	public ConfigManager getConfigManager() {
		return configManager;
	}
	
}
