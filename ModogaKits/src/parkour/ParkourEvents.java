package parkour;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import api.MessageAPI;
import api.WarpAPI;

public class ParkourEvents implements Listener{
	
	@EventHandler
	public void onMove(PlayerMoveEvent ev) {
		Player p = ev.getPlayer();
		if(!ParkourAPI.isPlaying(p))return;
		Block block = p.getWorld().getBlockAt(p.getLocation().getBlockX(), p.getLocation().getBlockY() - 1, p.getLocation().getBlockZ());
		if(block.getTypeId() == 41) {
			ParkourAPI.saveLocation(p);
		}
		if(p.getLocation().getY() < 30) {
			p.teleport(ParkourAPI.getLocation(p));
			p.sendMessage(MessageAPI.Command_Succes+"Você foi teleportado para o ultimo Save");
		}
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEvent ev) {
		Player p = ev.getPlayer();
		if(!ParkourAPI.isPlaying(p))return;
		if(p.getItemInHand() != null &&p.getItemInHand().getType() == Material.REDSTONE_BLOCK) {
			WarpAPI.setWarp(p, "Spawn");
			api.API.sendItems(p);
			ParkourAPI.delLocation(p);
			ParkourAPI.delPlayer(p);
		}
	}
	
	@EventHandler
	  public void onMe2(PlayerCommandPreprocessEvent event)
	  {
	    Player p = event.getPlayer();
	    if (ParkourAPI.isPlaying(p) && (
	      (event.getMessage().toLowerCase().startsWith("/"))))
	    {
	      event.setCancelled(true);
	      p.sendMessage(MessageAPI.Command_Error+"Você não pode executar comandos no Parkour");
	    }
	  }

}
