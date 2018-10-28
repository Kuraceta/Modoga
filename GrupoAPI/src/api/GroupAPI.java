package api;

import java.util.ArrayList;
import java.util.Set;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import config.Config;
import mysqlManager.MySQLFunctions;

public class GroupAPI {
	
	public static ArrayList<String> grupos = new ArrayList<String>();
	
	public static boolean canSeeAC(String grupo){
		return Config.getConfig().getBoolean("Grupo."+grupo+".VerAntiCheat");
	}
	
	public static void loadGroups() {
		grupos.clear();
		Set<String> vals = Config.getConfig().getConfigurationSection("Grupo").getKeys(false);
		for(String val : vals){
			System.out.println(val);
			grupos.add(val);
		}
	}
	
	public static boolean isStaff(String grupo) {
		return grupo.equalsIgnoreCase("Dono") ||grupo.equalsIgnoreCase("Gerente")||grupo.equalsIgnoreCase("Moderador")||grupo.equalsIgnoreCase("Administrador")||grupo.equalsIgnoreCase("Trial")||grupo.equalsIgnoreCase("Youtuber+");
	}
	
	public static String getTime(Player p) {
		if(inTempGroup(p)) {
			return Methods.getRemainingTime(MySQLFunctions.getLenght(p));
		}
		return "Nunca";
	}
	
	public static boolean GroupCanExecute(String grupo, String cmd){
		ArrayList<String> cmds = (ArrayList<String>) Config.getConfig().getStringList("Grupo."+grupo+".Comandos");
		if(cmds.size() == 0) {
			return false;
		}
		for(String cmdsallow : Config.getConfig().getStringList("Grupo."+grupo+".Comandos")) {
			if(cmdsallow.equalsIgnoreCase(cmd)) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean inTempGroup(Player p) {
		return !String.valueOf(MySQLFunctions.getLenght(p)).contains("-1");
	}
	
	public static String getFormat(String gp){
		if(getGroup(gp)!=null) {
			return Config.getConfig().getString("Grupo."+gp+".Tag").replace("&", "§");
		}
		return "§7";
	}
	
	public static String getColor(String gp){
		if(getGroup(gp)!=null) {
			return Config.getConfig().getString("Grupo."+gp+".Cor").replace("&", "§");
		}
		return "§7";
	}
	
	public static String getGroup(String gp){
		for(String grup : grupos){
			if(grup.equalsIgnoreCase(gp)){
				return grup;
			}
		}
		return null;
	}
	
	public static String getGroup(Player p){
		return MySQLFunctions.getGroup(p);
	}
	
	public static Long getLenght(Player p) {
		return MySQLFunctions.getLenght(p);
	}
	
	public static void setGroup(Player p, String Grupo){
		Grupo = GroupAPI.getGroup(Grupo);
		MySQLFunctions.setGroup(p, Grupo);
	}
	
	public static void setGroupTemp(Player p, String Grupo,long lenght){
		Grupo = GroupAPI.getGroup(Grupo);
		MySQLFunctions.setGroup(p, Grupo,lenght);
	}
	
	public static void setGroupTemp(OfflinePlayer p, String Grupo,long lenght){
		Grupo = GroupAPI.getGroup(Grupo);
		MySQLFunctions.setGroup(p, Grupo,lenght);
	}
	
	public static void setGroup(OfflinePlayer p, String Grupo){
		Grupo = GroupAPI.getGroup(Grupo);
		MySQLFunctions.setGroup(p, Grupo);
	}

}
