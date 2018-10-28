package mlg;

import java.util.HashMap;

import org.bukkit.entity.Player;

import kConfig.MLGConfig;

public class MLGAPI {
	
	private static HashMap<Player, Integer> streak = new HashMap<Player, Integer>();
	
	public static void setupPlayer(Player p) {
		streak.put(p, 0);
		if(MLGConfig.getConfig().get(p.getUniqueId().toString())==null) {
			MLGConfig.getConfig().set(p.getUniqueId().toString()+".Nick",p.getName());
			MLGConfig.getConfig().set(p.getUniqueId().toString()+".Acertos",0);
			MLGConfig.getConfig().set(p.getUniqueId().toString()+".Erros",0);
			MLGConfig.saveConfigs();
		}
	}
	
	public static void addKs(Player p,int i) {
		Integer d = streak.get(p);
		streak.put(p, ++d);
	}
	
	public static int getKS(Player p) {
		return streak.get(p);
	}
	
	public static void clearKs(Player p) {
		streak.put(p, 0);
	}
	
	public static int getErros(Player p) {
		return MLGConfig.getConfig().getInt(p.getUniqueId().toString()+".Erros");
	}
	
	public static int getAcertos(Player p) {
		return MLGConfig.getConfig().getInt(p.getUniqueId().toString()+".Acertos");
	}
	
	public static void addAcertos(Player p) {
		MLGConfig.getConfig().set(p.getUniqueId().toString()+".Acertos",getAcertos(p)+1);
		MLGConfig.saveConfigs();
	}
	
	public static void addErros(Player p) {
		MLGConfig.getConfig().set(p.getUniqueId().toString()+".Erros",getErros(p) + 1);
		MLGConfig.saveConfigs();
	}

}
