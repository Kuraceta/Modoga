package events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.nametagedit.plugin.api.INametagApi;

public class TagChangeEvent extends Event{

	private final Player player;
	private final INametagApi tagapi;
	private final String tag;
	private static final HandlerList handlers = new HandlerList();
	
	public TagChangeEvent(Player p, INametagApi tag, String tagg) {
		player = p;
		tagapi= tag;
		this.tag = tagg;
	}

	public String getTag() {
		return tag;
	}

	public Player getPlayer() {
		return player;
	}

	public INametagApi getTagapi() {
		return tagapi;
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}
	
}
