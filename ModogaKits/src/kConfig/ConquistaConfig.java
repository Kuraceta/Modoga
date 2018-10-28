package kConfig;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

public class ConquistaConfig {
	
	public static FileConfiguration Conquista;
	public static File FileConquista;
	
	public ConquistaConfig(Plugin plugin){
		if(!plugin.getDataFolder().exists()){
			plugin.getDataFolder().mkdir();
		}
		FileConquista = new File(plugin.getDataFolder(), "Conquistas.yml");
		if(!FileConquista.exists()){
			try{
				FileConquista.createNewFile();
			} catch(IOException e){
				e.printStackTrace();
			}
		}
		Conquista = YamlConfiguration.loadConfiguration(FileConquista);
	}
	public static FileConfiguration getConfig(){
		return Conquista;
	}
	public static void saveConfigs(){
		try{
			Conquista.save(FileConquista);
		} catch(IOException e){
			e.printStackTrace();
		}
	}

}
