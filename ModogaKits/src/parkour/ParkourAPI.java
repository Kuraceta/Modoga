package parkour;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class ParkourAPI {
	
	private static HashMap<Player, Location> saves = new HashMap<Player, Location>();
	private static ArrayList<Player> playing = new ArrayList<Player>();
	
	public static void addPlayer(Player p) {
		playing.add(p);
	}
	
	public static void delPlayer(Player p) {
		if(playing.contains(p)) {
			playing.remove(p);
		}
	}
	
	public static boolean isPlaying(Player p) {
		return playing.contains(p);
	}
	
	public static void saveLocation(Player p) {
		saves.put(p, p.getLocation());
	}

	public static void delLocation(Player p) {
		if(saves.containsKey(p)) {
			saves.remove(p);
		}
	}
	
	public static Location getLocation(Player p) {
		if(saves.containsKey(p)) {
			return saves.get(p);
		}
		return null;
	}
	
}
