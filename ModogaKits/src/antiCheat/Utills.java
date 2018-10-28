package antiCheat;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import br.alkazuz.groupapi.api.GroupAPI;

public class Utills {

	public static HashMap<Player, Integer> FastAttackClicks = new HashMap<Player, Integer>();
	public static HashMap<Player, Integer> MacroClicks = new HashMap<Player, Integer>();
	public static ArrayList<Player> fly = new ArrayList<>();
	public static String FastAttack = null;
	public static String Forcefield = null;
	public static String Macro = null;
	public static String Fly = null;
	public static String Speed = null;
	public static String ForjandoAutoSoup = null;
	public static String AutoSoup = null;
	
	public static HashMap<Player, Integer> ClicksFastClicks = new HashMap<Player, Integer>();
	public static HashMap<Player, Integer> ClicksMacro = new HashMap<Player, Integer>();
	
	public static HashMap<Player, Integer> AvisosForcefield = new HashMap<Player, Integer>();
	public static HashMap<Player, Integer> AvisosFastClick = new HashMap<Player, Integer>();
	public static HashMap<Player, Integer> AvisosFly = new HashMap<Player, Integer>();
	public static HashMap<Player, Integer> AvisosSpeed = new HashMap<Player, Integer>();
	public static HashMap<Player, Integer> AvisosMacro = new HashMap<Player, Integer>();
	public static HashMap<Player, Integer> AvisosAutoSoup = new HashMap<Player, Integer>();
	
	public static enum Hack {
		
		FASTCLICKTALVEZ(API.PrefixStaffer + "(" + API.YELLOW + "nick" + API.GRAY + ") Talvez estar (" + API.RED + "Clicando muito Rápido" +  API.GRAY + ") clicks Click's " + API.DARK_RED + "[" + "avisos" + "]" + API.GRAY + "!"),
		FASTCLICKPROVAVELMENTE(API.PrefixStaffer + "(" + API.YELLOW + "nick" + API.GRAY + ") Provavelmente estar (" + API.RED + "Clicando muito Rápido" +  API.GRAY + ") clicks Click's " + API.DARK_RED + "[" + "avisos" + "]" + API.GRAY + "!"),
		FASTCLICKDEFINITIVAMENTE(API.PrefixStaffer + "(" + API.YELLOW + "nick" + API.GRAY + ") Definitivamente estar (" + API.RED + "Clicando muito Rápido" +  API.GRAY + ") clicks Click's " + API.DARK_RED + "[" + "avisos" + "]" + API.GRAY + "!"),
		
		MACROTALVEZ(API.PrefixStaffer + "(" + API.YELLOW + "nick" + API.GRAY + ") Talvez estar usando (" + API.RED + "Macro" +  API.GRAY + ") clicks Click's " + API.DARK_RED + "[" + "avisos" + "]" + API.GRAY + "!"),
		MACROPROVAVELMENTE(API.PrefixStaffer + "(" + API.YELLOW + "nick" + API.GRAY + ") Provavelmente estar usando (" + API.RED + "Macro" +  API.GRAY + ") clicks Click's " + API.DARK_RED + "[" + "avisos" + "]" + API.GRAY + "!"),
		MACRODEFINITIVAMENTE(API.PrefixStaffer + "(" + API.YELLOW + "nick" + API.GRAY + ") Definitivamente estar usando (" + API.RED + "Macro" +  API.GRAY + ") clicks Click's " + API.DARK_RED + "[" + "avisos" + "]" + API.GRAY + "!"),
		
		FORCEFIELDTALVEZ(API.PrefixStaffer + "(" + API.YELLOW + "nick" + API.GRAY + ") Talvez estar usando (" + API.RED + "ForceField" + API.GRAY + ") " +  API.DARK_RED + "[" + "avisos" + "]" + API.GRAY + "!"),
		FORCEFIELDPROVAVELMENTE(API.PrefixStaffer + "(" + API.YELLOW + "nick" + API.GRAY + ") Provavelmente estar usando (" + API.RED + "ForceField" + API.GRAY + ") " +  API.DARK_RED + "[" + "avisos" + "]" + API.GRAY + "!"),
		FORCEFIELDDEFINITIVAMENTE(API.PrefixStaffer + "(" + API.YELLOW + "nick" + API.GRAY + ") Definitivamente estar usando (" + API.RED + "ForceField" + API.GRAY + ") " +  API.DARK_RED + "[" + "avisos" + "]" + API.GRAY + "!"),
		
		FLY(API.PrefixStaffer + "(" + API.YELLOW + "nick" + API.GRAY + ") pode estar (" + API.RED + "Voando" +  API.GRAY + ") ping de MS " + API.DARK_RED + "[" + "avisos" + "]" + API.GRAY + "!"),
		
		SPEED(API.PrefixStaffer + "(" + API.YELLOW + "nick" + API.GRAY + ") pode estar usando (" + API.RED + "Speed" +  API.GRAY + ") ping de MS " + API.DARK_RED + "[" + "avisos" + "]" + API.GRAY + "!"),
		
		AUTOSOUPTALVEZ(API.PrefixStaffer + "(" + API.YELLOW + "nick" + API.GRAY + ") Talvez estar usando (" + API.RED + "Auto-Soup" +  API.GRAY + ") " + API.DARK_RED + "[" + "avisos" + "]" + API.GRAY + "!"),
		AUTOSOUPPROVAVELMENTE(API.PrefixStaffer + "(" + API.YELLOW + "nick" + API.GRAY + ") Provavelmente estar usando (" + API.RED + "Auto-Soup" +  API.GRAY + ") " + API.DARK_RED + "[" + "avisos" + "]" + API.GRAY + "!"),
		AUTOSOUPDEFINITIVAMENTE(API.PrefixStaffer + "(" + API.YELLOW + "nick" + API.GRAY + ") Definitivamente estar usando (" + API.RED + "Auto-Soup" +  API.GRAY + ") " + API.DARK_RED + "[" + "avisos" + "]" + API.GRAY + "!");
		
		private String Menssagem;
		
		private Hack(String Menssagem) { this.Menssagem = Menssagem; }

		public String getMenssagem() {
			return this.Menssagem;
		}
	}
	@SuppressWarnings("deprecation")
	public static void sendAntiCheat(String Menssagem) {
		
		for (Player playeres : Bukkit.getOnlinePlayers()) {
			if(GroupAPI.canSeeAC(GroupAPI.getGroup(playeres))){
			playeres.sendMessage(Menssagem);
			}
		}
	}

	public static boolean Flight(Player playeres) {
		return (playeres.getGameMode() == GameMode.CREATIVE) || (playeres.getAllowFlight());
	}

	public static boolean Speeding(Player playeres) {
	    return (playeres.getGameMode() == GameMode.CREATIVE) || (playeres.getAllowFlight());
	}

	public static void setPlayer(Player player) {
		AvisosFastClick.put(player, Integer.valueOf(1));
		AvisosMacro.put(player, Integer.valueOf(1));
		AvisosFly.put(player, Integer.valueOf(1));
		AvisosSpeed.put(player, Integer.valueOf(1));
		AvisosForcefield.put(player, Integer.valueOf(1));
		AvisosAutoSoup.put(player, Integer.valueOf(1));
		
		ClicksFastClicks.put(player, Integer.valueOf(1));
		ClicksMacro.put(player, Integer.valueOf(1));
	}
}
