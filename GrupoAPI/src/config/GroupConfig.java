package config;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

public class GroupConfig {
	public static FileConfiguration Warps;
	public static File FileWarps;
	public GroupConfig(Plugin plugin){
		if(!plugin.getDataFolder().exists()){
			plugin.getDataFolder().mkdir();
		}
		FileWarps = new File(plugin.getDataFolder(), "Grupos.yml");
		if(!FileWarps.exists()){
			try{
				FileWarps.createNewFile();
			} catch(IOException e){
				e.printStackTrace();
			}
		}
		Warps = YamlConfiguration.loadConfiguration(FileWarps);
	}
	public static FileConfiguration getConfig(){
		return Warps;
	}
	public static void saveConfigs(){
		try{
			Warps.save(FileWarps);
		} catch(IOException e){
			e.printStackTrace();
		}
	}
}
