package kConquista;

import org.bukkit.entity.Player;

public class Conquista {
	
	public String name;
	public String[] desc;
	public int premio;
	
	public Conquista(String name, String[] decsc, int pre){
		this.name= name;
		this.desc = decsc;
		this.premio = pre;
		
	}
	
	public boolean onComplete(Player p) {return false;}
	
	public String[] getDesc(){
		return desc;
	}
	
	public String getName(){
		return name;
	}
	
	public int getPremio(){
		return premio;
	}

}
