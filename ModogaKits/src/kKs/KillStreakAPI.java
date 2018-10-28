package kKs;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class KillStreakAPI {
	
	public static HashMap<Player, Integer> ks = new HashMap<Player, Integer>();
	
	public static void resetKS(Player p){
		ks.put(p, 0);
	}
	
	public static void addKS(Player p){
		Integer i = ks.get(p);
		ks.put(p, ++i);
	}
	
	public static int getKS(Player p){
		return (ks.containsKey(p) ? ks.get(p) : 0);
	}
	
	public static String getTopKS2(){
		Player p = getTopKS();
		if(p== null || getKS(p)==0){
			return "Ninguém";
		}
		return p.getName() + "§7 - §e"+getKS(p);
	}
	@SuppressWarnings("deprecation")
	public static Player getTopKS(){
		Player best = null;
		for(Player p : Bukkit.getOnlinePlayers()){
			if(best != null){
				int kill = getKS(p);
				int kill2 = getKS(best);
				if(kill<=kill2)continue;
				best = p;
			}else{
				best = p;
			}
		}
		return best;
	}

}
