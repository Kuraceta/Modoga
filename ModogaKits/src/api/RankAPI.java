package api;

import org.bukkit.entity.Player;

import mysqlManager.Status;


public class RankAPI {
	
	public static String getRankName(Player p)
	{
		int a = Status.getkills(p);
		if (a <= 49) {
			return "§f⚊ UNRANKED";//6≅ Bronze
	    }
	    if (a >= 50 && a <=100) {
	    	return "§a☰ PRIMARY";//§7♦ Silver
	    }
	    if (a >= 101 && a <=150) {
	    	return "§e☲ EXPERT";//§e● Gold
	    }
	    if (a >= 151 && a <=200) {
	    	return "§1☲ ADVANCED";
	    }
	    if (a >= 201 && a <=250) {
	    	return "§c☳ KING";
	    }
	    if (a >= 251&&a <=300) {
	    	return "§3☴ MITO";
	    }
	    if (a >= 500) {
	    	return "§4☵ LENDA";
	    }
	    return "§4☵ LENDA";
	}
	
	public static String getRank(Player p)
	{
		int a = Status.getkills(p);
		if (a <= 49) {
			return "§f⚊";//6≅ Bronze
	    }
	    if (a >= 50 && a <=100) {
	    	return "§a☰";//§7♦ Silver
	    }
	    if (a >= 101 && a <=150) {
	    	return "§e☲";//§e● Gold
	    }
	    if (a >= 151 && a <=200) {
	    	return "§1☲";
	    }
	    if (a >= 201 && a <=250) {
	    	return "§c☳";
	    }
	    if (a >= 251&&a <=300) {
	    	return "§3☴";
	    }
	    if (a >= 500) {
	    	return "§4☵";
	    }
	    return "§4☵";
	}
	
	public static String getKd(Player p){
		double n1 = Status.getkills(p) / Status.getDeaths(p);
		return String.format ("%.2f", n1, n1);
	}

}
