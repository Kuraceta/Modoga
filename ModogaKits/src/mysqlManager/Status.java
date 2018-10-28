package mysqlManager;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;

import com.minepag.cash.Cash;

import kLogs.LogManager;

public class Status {

	public static Map<Player, Integer> coins = new HashMap<Player, Integer>();
    public static Map<Player, Integer> kills = new HashMap<Player, Integer>();
    public static Map<Player, Integer> deaths = new HashMap<Player, Integer>();
    public static Map<Player, Integer> caixas = new HashMap<Player, Integer>();
	
	public static void addPlayer(Player p, int amount) {
        coins.put(p, amount);
    }
	
	public static int getCash(String nick) {
		return (int) Cash.getCash(nick);
	}
	
	public static void removeCash(String nick, int i) {
		Cash.setCash(nick, (double)getCash(nick) - i);
	}
	
	public static int getCaixas(Player p) {
        return caixas.get(p);
    }
	
	public static int addCaixas(Player p,int i) {
		LogManager.createLogStatus("Caixas de "+p.getName()+" alteradas para "+(caixas.get(p)+i));
        return caixas.put(p,caixas.get(p)+i);
    }
	
	public static int removerCaixas(Player p,int i) {
		LogManager.createLogStatus("Caixas de "+p.getName()+" alteradas para "+(caixas.get(p)-i));
        return caixas.put(p,caixas.get(p)-i);
    }

    public static void removePlayer(Player p) {
        coins.remove(p);
    }

    public static int getCoins(Player p) {
        return MySQLFunctions.Money(p);
    }

    public static void setCoins(Player p, int amount) {
    	LogManager.createLogStatus("Coins de "+p.getName()+" alterados para "+amount);
        MySQLFunctions.setMoney(p, amount);
    }

    public static void addCoins(Player p, int coins) {
        setCoins(p, getCoins(p) + coins);
    }

    public static void removeCoins(Player p, int coins) {
    	if(getCoins(p) <= 0)return;
        setCoins(p, getCoins(p) - coins);
    }

    public static int getDeaths(Player p){
    	return deaths.get(p);
    }
    
    public static void addDeath(Player p, int i) {
        deaths.put(p, getDeaths(p) + i);
        LogManager.createLogStatus("Mortes de "+p.getName()+" alteradas para "+getDeaths(p));
    }
    
    public static void setDeath(Player p, int i) {
        deaths.put(p, i);
        LogManager.createLogStatus("Mortes de "+p.getName()+" alteradas para "+getDeaths(p));
    }
    
    public static int getkills(Player p){
    	return kills.get(p);
    }
    
    public static void addKills(Player p, int i) {
    	kills.put(p, getkills(p) + i);
    	LogManager.createLogStatus("Mortes de "+p.getName()+" alteradas para "+getkills(p));
    }
    
    public static void setKills(Player p, int i) {
    	kills.put(p, i);
    	LogManager.createLogStatus("Mortes de "+p.getName()+" alteradas para "+getkills(p));
    }

}
