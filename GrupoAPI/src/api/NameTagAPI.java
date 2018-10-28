package api;

import org.bukkit.entity.Player;

import com.nametagedit.plugin.NametagEdit;

public class NameTagAPI {
	
	public static String getName(Player p) {
		return p.getName();
	}
	
	public static void setTag(Player p , String tag){
			NametagEdit.getApi().setPrefix(getName(p), GroupAPI.getFormat(tag));
			p.setDisplayName(GroupAPI.getFormat(tag)+getName(p));
	}
	
	public static void setupTag(Player p){
		NametagEdit.getApi().setPrefix(getName(p), GroupAPI.getFormat(GroupAPI.getGroup(p)));
			p.setDisplayName(GroupAPI.getFormat(GroupAPI.getGroup(p))+getName(p));
	}
	
}
