package kConquista;

import java.util.ArrayList;

import org.bukkit.entity.Player;

import kConfig.ConquistaConfig;

public class ConquistaAPI {
	
	public static void setupPlayer(Player p){
		if(ConquistaConfig.getConfig().get(p.getName().toLowerCase())==null){
			ArrayList<String> list = new ArrayList<String>();
			ConquistaConfig.getConfig().set(p.getName().toLowerCase(), list);
			ConquistaConfig.saveConfigs();
		}
	}
	
	public static int getAmount(Player p) {
		int i = 0;
		if(ConquistaConfig.getConfig().get(p.getName().toLowerCase())==null){
			return 0;
		}
		for(Conquista cq : ConquistaManager.conquistas) {
			if(hasConquista(p, cq.getName())) {
				i++;
			}
		}
		return i;
	}
	
	public static void addConquista(Player p,String conquista){
		setupPlayer(p);
		ArrayList<String> list = (ArrayList<String>) ConquistaConfig.getConfig().getStringList(p.getName().toLowerCase());
		list.add(conquista);
		ConquistaConfig.getConfig().set(p.getName().toLowerCase(), list);
		ConquistaConfig.saveConfigs();
	}
	
	public static boolean hasConquista(Player p, String conquista){
		ArrayList<String> list = (ArrayList<String>) ConquistaConfig.getConfig().getStringList(p.getName().toLowerCase());
		for(String str : list){
			if(str.equalsIgnoreCase(conquista)){
				return true;
			}
		}
		return false;
	}

}
