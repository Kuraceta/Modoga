package kConfig;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class KitDiario {
	
	public static FileConfiguration Caixas;
	public static File FileCaixas;
	
	public static void addTempo(Player p) {
		long total = 86400;
		long newTime = total * 1000L + System.currentTimeMillis();
		getConfig().set(p.getUniqueId().toString()+".Nick", p.getName());
		getConfig().set(p.getUniqueId().toString()+".Tempo", Long.valueOf(newTime));
		saveConfigs();
	}
	
	public KitDiario(Plugin plugin){
		if(!plugin.getDataFolder().exists()){
			plugin.getDataFolder().mkdir();
		}
		FileCaixas = new File(plugin.getDataFolder(), "KitDiario.yml");
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
