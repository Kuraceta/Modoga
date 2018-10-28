package antiCheat;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class API {
	
	public static final ChatColor BLACK = ChatColor.BLACK;
	public static final ChatColor DARK_BLUE = ChatColor.DARK_BLUE;
	public static final ChatColor DARK_GREEN = ChatColor.DARK_GREEN;
	public static final ChatColor DARK_AQUA = ChatColor.DARK_AQUA;
	public static final ChatColor DARK_RED = ChatColor.DARK_RED;
	public static final ChatColor DARK_PURPLE = ChatColor.DARK_PURPLE;
	public static final ChatColor GOLD = ChatColor.GOLD;
	public static final ChatColor GRAY = ChatColor.GRAY;
	public static final ChatColor DARK_GRAY = ChatColor.DARK_GRAY;
	public static final ChatColor BLUE = ChatColor.BLUE;
	public static final ChatColor GREEN = ChatColor.GREEN;
	public static final ChatColor YELLOW = ChatColor.YELLOW;
	public static final ChatColor LIGHT_PURPLE = ChatColor.LIGHT_PURPLE;
	public static final ChatColor RED = ChatColor.RED;
	public static final ChatColor WHITE = ChatColor.WHITE;
	public static final ChatColor AQUA = ChatColor.AQUA;
	public static final ChatColor BOLD = ChatColor.BOLD;
	public static final ChatColor ITALIC = ChatColor.ITALIC;
	public static final ChatColor UNDERLINE = ChatColor.UNDERLINE;
	public static final ChatColor STRIKETHROUGH = ChatColor.STRIKETHROUGH;
	
	public static String PrefixStaffer = GRAY + "» " + API.GRAY;
	@SuppressWarnings("deprecation")
	public static void sendStaffer(Player Jogador, String Menssagem) {
		for (Player Jogadores : Bukkit.getOnlinePlayers()) {
			if (Jogadores != Jogador) {
				Jogadores.sendMessage(PrefixStaffer + Menssagem + GRAY + "!");
			}
		}
	}

/*	public static void applyKnockBack(Entity ent, Location loc, int kb, boolean isSprint) {
		Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
			public void run() {
				Vector v = ent.getLocation().toVector().subtract(loc.toVector()).normalize();
				double d = API.getVelocity() + kb * 0.05D;
				if (isSprint)
					d *= 2.0D;
				ent.setVelocity(v.multiply(d).setY(API.getHeight()));
			}
		});
	}*/

	public static double getVelocity() {
		return 0.6;
	}

	public static double getHeight() {
		return 0.4;
	}
}