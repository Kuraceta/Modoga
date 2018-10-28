package api;

import org.bukkit.entity.Player;

import br.alkazuz.groupapi.api.GroupAPI;
import br.alkazuz.groupapi.mysql.manager.MySQLFunctions;

public class VIPAPI {

	public static void applyVIP(Player p,int dia){
		long total = 0L;
		total += dia * 86400;
		long newTime = total;
	    newTime = total* 1000L + (isVip(p)?(long)GroupAPI.getLenght(p):System.currentTimeMillis());
	    MySQLFunctions.setLenght(p,Long.valueOf(newTime));
	}
	
	public static void addVIP(Player p,String grupo,int dia){
		long total = 0L;
		total += dia * 86400;
		long newTime = total;
	    newTime = total* 1000L + (isVip(p)?(long)GroupAPI.getLenght(p):System.currentTimeMillis());
	    MySQLFunctions.setGroup(p, grupo);
	    MySQLFunctions.setLenght(p,Long.valueOf(newTime));
	}
	
	public static boolean isVip(Player p) {
		return GroupAPI.inTempGroup(p);
	}
	
}
