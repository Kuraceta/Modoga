package kEvents;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class PlacaRecraft implements Listener {
	
	@EventHandler
	public void sign(SignChangeEvent e) {
		if(e.getLine(0).equalsIgnoreCase("recraft")&&e.getPlayer().getGameMode() == GameMode.CREATIVE) {
				e.setLine(0, "§e§lRECRAFT");
				e.setLine(1, "§2§lSpectrePvP");
				e.setLine(2, "");
				e.setLine(3, "§c§lClique");
			return;
		}
		for(int danielF = 0; danielF <= 3; danielF++){
		e.setLine(danielF, e.getLine(danielF).replace("&", "§"));
		}
	}
	
	@EventHandler
	public void inv(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if((e.getAction() == Action.RIGHT_CLICK_BLOCK))
			if(e.getClickedBlock() != null)
			   if (e.getClickedBlock().getType() == Material.WALL_SIGN || (e.getClickedBlock().getType() == Material.SIGN_POST)) {
			Sign s = (Sign)e.getClickedBlock().getState();
			String[] lines = s.getLines();
			if ((lines.length > 0) && (lines[0].equals("§e§lRECRAFT") && 
					(lines.length > 1) && (lines[1].equals("§2§lSpectrePvP"))&&
					(lines.length > 2) && (lines[2].equals("")) &&
					(lines.length > 3) && (lines[3].equals("§c§lClique")))) {
				
				ItemStack cogu1 = new ItemStack(Material.BROWN_MUSHROOM, 64);
				
				ItemStack cogu2 = new ItemStack(Material.RED_MUSHROOM, 64);
	
				ItemStack pote = new ItemStack(Material.BOWL, 64);
	
				p.sendMessage("§cRecrafitado!");
		           p.getInventory().addItem(cogu1);
		           p.getInventory().addItem(cogu2);
		           p.getInventory().addItem(pote);
		           p.updateInventory();
			
		}}

	    }
} 