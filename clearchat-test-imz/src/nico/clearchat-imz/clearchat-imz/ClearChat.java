package nico.clearchat-imz;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class ClearChat extends JavaPlugin {

	FileConfiguration config;
	File cfile;

	@Override
	public void onEnable() {
		config = getConfig();
		config.options().copyDefaults(true);
		saveConfig();
		cfile = new File(getDataFolder(), "config.yml");
	}

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {

		final Player player = (Player) sender;
		String clearchat = getConfig().getString("clearchat");
		clearchat = ChatColor.translateAlternateColorCodes('&', getConfig().getString("clearchat").replace("{name}", player.getDisplayName()));

		if (!(sender instanceof Player)) {
			for (int i = 0; i < 90; i++)
				getServer().broadcastMessage("");
		}

		if (cmd.getName().equalsIgnoreCase("clearchat.reload") && (player.hasPermission("clearchat.reload"))) {
			reloadConfig();
			player.sendMessage(ChatColor.GREEN + "Successfully reloaded ClearChat Configuration!");
		} else if (!player.hasPermission("clearchat.reload")) {
			player.sendMessage(ChatColor.RED + "You do not have permission to use that command!");
		}

		if (cmd.getName().equalsIgnoreCase("clearchat") && (player.hasPermission("clearchat.use"))) {
			for (int i = 0; i < 90; i++)
				getServer().broadcastMessage("");
			getServer().broadcastMessage(clearchat);
		} else if (!player.hasPermission("clearchat.use")) {
			player.sendMessage(ChatColor.RED + "You do not have permission to use that command!");
		}

		return false;
	}
}