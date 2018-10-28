package kConfig;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

public class WarpConfig {
	
	public static FileConfiguration Caixas;
	public static File FileCaixas;
	
	public WarpConfig(Plugin plugin){
		if(!plugin.getDataFolder().exists()){
			plugin.getDataFolder().mkdir();
		}
		FileCaixas = new File(plugin.getDataFolder(), "warps.yml");
		if(!FileCaixas.exists()){
			try {
				FileCaixas.createNewFile();
			}catch (Exception e) {
				// TODO: handle exception
			}
		}
		Caixas = YamlConfiguration.loadConfiguration(FileCaixas);
	}
	public static FileConfiguration getConfig(){
		return Caixas;
	}
	public static void saveConfigs(){
		try{
			Caixas.save(FileCaixas);
		} catch(IOException e){
			e.printStackTrace();
		}
	}

}
