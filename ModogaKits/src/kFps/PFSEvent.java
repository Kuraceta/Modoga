package kFps;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import api.WarpAPI;
import protection.Protection;

public class PFSEvent implements Listener{
	
	@EventHandler
	public void damgeEvent(EntityDamageEvent e) {
		if (e.getEntity() instanceof Player){
		      Player p = (Player)e.getEntity();
		      if(WarpAPI.getWarp(p).equalsIgnoreCase("FPS")) {
		    	  if(e.getCause() == DamageCause.FALL && Protection.isImortal(p)) {
		    		  Protection.setImortal(p, false);
		    		  e.setCancelled(true);
		    	  }
		      }
		}
	}
	
	@EventHandler
	public void damgeEven(EntityDamageByEntityEvent e) {
		if (e.getEntity() instanceof Player){
		      Player p = (Player)e.getEntity();
		      if(WarpAPI.getWarp(p).equalsIgnoreCase("Lava")) {
		    	  e.setCancelled(true);
		      }
		}
	}

}
