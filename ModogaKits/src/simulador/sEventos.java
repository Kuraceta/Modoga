package simulador;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import main.Main;
import score.ScoreBoarding;

public class sEventos implements Listener{
	
	public static ArrayList<Block> blocksPlaced = new ArrayList<Block>();
	Main plugin;
	@EventHandler
	public void aoSair(PlayerQuitEvent e){
		if(EventosAPI.playerPlayingHG(e.getPlayer())){
			
			EventosAPI.playersHG.remove(e.getPlayer().getName());
		}
		if(KitAPI.kits.contains(e.getPlayer().getName())){
	        KitAPI.kits.remove(e.getPlayer().getName());
	    	}
		if(KitAPI.grappler.contains(e.getPlayer().getName())){
	        KitAPI.grappler.remove(e.getPlayer().getName());
	    	}
	    	if(KitAPI.grappler.contains(e.getPlayer().getName())){
	        KitAPI.kangaroo.remove(e.getPlayer().getName());
	    	}
	    	if(KitAPI.grappler.contains(e.getPlayer().getName())){
	        KitAPI.monk.remove(e.getPlayer().getName());
	    	}
	    	if(KitAPI.grappler.contains(e.getPlayer().getName())){
	        KitAPI.ninja.remove(e.getPlayer().getName());
	    	}
	    	if(KitAPI.grappler.contains(e.getPlayer().getName())){
	        KitAPI.stomper.remove(e.getPlayer().getName());
	    	}
	    	if(KitAPI.grappler.contains(e.getPlayer().getName())){
	        KitAPI.switcher.remove(e.getPlayer().getName());
	    	}
	    	if(KitAPI.grappler.contains(e.getPlayer().getName())){
	        KitAPI.thor.remove(e.getPlayer().getName());
	    	}
	}
	@EventHandler
    public void onSair(final PlayerQuitEvent evt) {
		if(EventosAPI.playerPlayingHG(evt.getPlayer())){
            lightning(evt.getPlayer());
		}
    }
	
	@EventHandler
	  public void onMe2(PlayerCommandPreprocessEvent event)
	  {
	    Player p = event.getPlayer();
	    if ((EventosAPI.playerPlayingHG(p)) && (
	      (!event.getMessage().toLowerCase().startsWith("/spawn"))))
	    {
	      event.setCancelled(true);
	      p.sendMessage("§cVocê está no Simulador, use §4/spawn §cpara executar comandos");
	    }
	  }
	
	@EventHandler
    public void onPlayerDeathEvent(final EntityDeathEvent evt) {
        final Entity entity = (Entity)evt.getEntity();
        if (entity instanceof Player) {
        	Player p = (Player)evt.getEntity();
        	Player d = evt.getEntity().getKiller();
        	
        	if(EventosAPI.playerPlayingHG(p)) {
        		for(Player pd : Bukkit.getOnlinePlayers()) {
        			if(EventosAPI.playerPlayingHG(pd)) {
        				ScoreBoarding.setScoreBoard(pd);
        			}
        		}
            lightning(entity);
            EventosAPI.playersHG.remove(p.getName());
            if(KitAPI.kits.contains(p.getName())){
		        KitAPI.kits.remove(p.getName());
		    	}
            if(KitAPI.kits.contains(p.getName())){
		        KitAPI.kits.remove(p.getName());
		    	}
			if(KitAPI.grappler.contains(p.getName())){
		        KitAPI.grappler.remove(p.getName());
		    	}
		    	if(KitAPI.grappler.contains(p.getName())){
		        KitAPI.kangaroo.remove(p.getName());
		    	}
		    	if(KitAPI.grappler.contains(p.getName())){
		        KitAPI.monk.remove(p.getName());
		    	}
		    	if(KitAPI.grappler.contains(p.getName())){
		        KitAPI.ninja.remove(p.getName());
		    	}
		    	if(KitAPI.grappler.contains(p.getName())){
		        KitAPI.stomper.remove(p.getName());
		    	}
		    	if(KitAPI.grappler.contains(p.getName())){
		        KitAPI.switcher.remove(p.getName());
		    	}
		    	if(KitAPI.grappler.contains(p.getName())){
		        KitAPI.thor.remove(p.getName());
		    	}
        	}
        	if(EventosAPI.playerPlayingHG(d)) {
        		EventosAPI.CheckarGanhador(d);
        	}
        }
        
        
    }
    
    public void lightning(final Entity entity) {
                entity.getWorld().strikeLightningEffect(entity.getLocation());
    }
	
	@EventHandler
	public void onPlayerBucketEmpty(PlayerBucketEmptyEvent e) {
		if(EventosAPI.playerPlayingHG(e.getPlayer())){
			e.setCancelled(true);
		}
	}
	@EventHandler
	public void Escrever(SignChangeEvent e) {
		if(EventosAPI.playerPlayingHG(e.getPlayer())){
			e.setCancelled(true);
		}
		
	}

}
