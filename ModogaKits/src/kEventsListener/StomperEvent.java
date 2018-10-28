package kEventsListener;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class StomperEvent extends Event implements Cancellable{
	
	private boolean cancel;
	private Player stomper;
	private Player stomped;
	private static final HandlerList handlers = new HandlerList();
	public StomperEvent(Player p, Player d) {
		stomper = p;
		stomped = d;
	}

	public Player getStomper() {
		return stomper;
	}

	public Player getStomped() {
		return stomped;
	}

	@Override
	public boolean isCancelled() {
		return cancel;
	}

	@Override
	public void setCancelled(boolean arg0) {
		cancel = arg0;
		
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}
	
	

}
