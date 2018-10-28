package api;

import org.bukkit.entity.Player;

import com.nametagedit.plugin.NametagEdit;
import com.nametagedit.plugin.utils.Utils;

import br.alkazuz.groupapi.api.GroupAPI;

public class NameTagAPI {
	
	public static String getName(Player p) {
		return p.getName();
	}
	
	public static void setTag(Player p , String tag){
		if(ClanAPI.getClanPlayer(p)!=null) {
			NametagEdit.getApi().setPrefix(getName(p), GroupAPI.getFormat(tag));
			NametagEdit.getApi().setSuffix(getName(p), " §7("+RankAPI.getRank(p)+"§7)");
			//NametagEdit.getApi().reloadNametag(p);
			p.setDisplayName(GroupAPI.getFormat(GroupAPI.getGroup(p))+" "+(ClanAPI.getClanPlayerFormat(p))+"§8.§f"+getName(p)+" §7("+RankAPI.getRank(p)+"§7)");
	
		}else {
			NametagEdit.getApi().setPrefix(getName(p), GroupAPI.getFormat(tag));
			NametagEdit.getApi().setSuffix(getName(p), " §7("+RankAPI.getRank(p)+"§7)");
			//NametagEdit.getApi().reloadNametag(p);
			p.setDisplayName(GroupAPI.getFormat(GroupAPI.getGroup(p))+" §f"+getName(p)+" §7("+RankAPI.getRank(p)+"§7)");
		}
	}
	
	public static void setupTag(Player p){
		if(ClanAPI.getClanPlayer(p)!=null) {
			NametagEdit.getApi().setPrefix(getName(p), GroupAPI.getFormat(GroupAPI.getGroup(p)));
			NametagEdit.getApi().setSuffix(getName(p), " §7("+RankAPI.getRank(p)+"§7)");
			//NametagEdit.getApi().reloadNametag(p);
			p.setDisplayName(GroupAPI.getFormat(GroupAPI.getGroup(p))+" "+(ClanAPI.getClanPlayerFormat(p))+"§8.§f"+getName(p)+" §7("+RankAPI.getRank(p)+"§7)");
	
		}else {
			NametagEdit.getApi().setPrefix(getName(p), GroupAPI.getFormat(GroupAPI.getGroup(p)));
			NametagEdit.getApi().setSuffix(getName(p), " §7("+RankAPI.getRank(p)+"§7)");
			//NametagEdit.getApi().reloadNametag(p);
			p.setDisplayName(GroupAPI.getFormat(GroupAPI.getGroup(p))+" §f"+getName(p)+" §7("+RankAPI.getRank(p)+"§7)");
		}
	}
	
	public static void setupFake(Player player,String nick) {
		NametagEdit.getApi().setNametag(nick, "§7", " §7("+RankAPI.getRank(player)+"§7)");
	}
	
	 public static String formatWithPlaceholders(final Player player, String input) {
	        if (input == null) {
	            return "";
	        }
	        if (player == null) {
	            return input;
	        }
	        return Utils.format(input, true);
	    }
	
}
