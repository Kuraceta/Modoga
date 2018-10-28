package kConfig;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

public class MLGConfig {
	
	public static FileConfiguration Caixas;
	public static File FileCaixas;
	
	public MLGConfig(Plugin plugin){
		if(!plugin.getDataFolder().exists()){
			plugin.getDataFolder().mkdir();
		}
		FileCaixas = new File(plugin.getDataFolder(), "MLG.yml");
		if(!FileCaixas.exists()){
			try {
				FileCaixas.createNewFile();
			}catch (Exception e) {}
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
