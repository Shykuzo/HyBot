package fr.hytaria_network.hybot;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import fr.hytaria_network.hybot.listeners.BlockListener;
import fr.hytaria_network.hybot.listeners.ChatListener;
import fr.hytaria_network.hybot.listeners.ServerListener;
import fr.hytaria_network.hybot.utils.ConfigManager;
import fr.hytaria_network.hybot.utils.LogManager;
import fr.mrcubee.annotation.spigot.config.ConfigAnnotation;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class Main extends JavaPlugin {
	
	private static Main instance;
	private JDA discordAPI;
	
	private ConfigManager configManager;
	private LogManager logManager;
	
	// ---------------------------------------- \\
	
	@Override
	public void onEnable() {
		instance = this;
		configManager = new ConfigManager();
		logManager = new LogManager();
		
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
		
		Bukkit.getPluginManager().registerEvents(new ChatListener(), getInstance());
		Bukkit.getPluginManager().registerEvents(new BlockListener(), getInstance());
		Bukkit.getPluginManager().registerEvents(new ServerListener(), getInstance());
	}
	
	@Override
	public void onDisable() {
		if(discordAPI != null) {
			discordAPI.shutdown();
		}
	}
	
	@Override
	public void reloadConfig() {
		super.reloadConfig();
		ConfigAnnotation.loadClass(getConfig(), getConfigManager());
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
	
	public LogManager getLogManager() {
		return logManager;
	}
	
}
