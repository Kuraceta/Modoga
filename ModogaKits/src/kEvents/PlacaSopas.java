package kEvents;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import kCommandCommands.cTopKill;
import mysqlManager.MySQLFunctions;

public class PlacaSopas implements Listener {
	
	@EventHandler
	public void TopKill(SignChangeEvent e) {
		if(e.getLine(0).equalsIgnoreCase("TOP1")&&e.getPlayer().getGameMode() == GameMode.CREATIVE) {
			e.setLine(0, "§6§lTOP 1");
			e.setLine(1, "§bJogador:");
			e.setLine(2, "§f"+cTopKill.Top1);
			e.setLine(3, "§bKills: §e"+MySQLFunctions.Kills(Bukkit.getOfflinePlayer(cTopKill.Top1)));
		}
		if(e.getLine(0).equalsIgnoreCase("TOP2")&&e.getPlayer().getGameMode() == GameMode.CREATIVE) {
			e.setLine(0, "§e§lTOP 2");
			e.setLine(1, "§bJogador:");
			e.setLine(2, "§f"+cTopKill.Top2);
			e.setLine(3, "§bKills: §e"+MySQLFunctions.Kills(Bukkit.getOfflinePlayer(cTopKill.Top2)));
		}
		if(e.getLine(0).equalsIgnoreCase("TOP3")&&e.getPlayer().getGameMode() == GameMode.CREATIVE) {
			e.setLine(0, "§c§lTOP 3");
			e.setLine(1, "§bJogador:");
			e.setLine(2, "§f"+cTopKill.Top3);
			e.setLine(3, "§bKills: §e"+MySQLFunctions.Kills(Bukkit.getOfflinePlayer(cTopKill.Top3)));
		}
	}
	
	@EventHandler
	public void Escrever(SignChangeEvent e) {
		if(e.getLine(0).equalsIgnoreCase("sopas")&&e.getPlayer().getGameMode() == GameMode.CREATIVE) {
			e.setLine(0, "§a§lSOPAS");
			e.setLine(1, "§2§lSpectrePvP");
			e.setLine(2, " ");
			e.setLine(3, "§c§lClique");
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
			if ((lines.length > 0) && (lines[0].equals("§a§lSOPAS")) && 
					(lines.length > 1) && (lines[1].equals("§2§lSpectrePvP"))&&
					(lines.length > 2) && (lines[2].equals(" ")) &&
					(lines.length > 3) && (lines[3].equals("§c§lClique"))) {
				Inventory inv = Bukkit.createInventory(p, 54, "§eSopas");
				
				ItemStack sopa = new ItemStack(Material.MUSHROOM_SOUP);
				
				for(int i =0; i != 54; i++) {
					inv.setItem(i, sopa);
				}
				p.openInventory(inv);
			
		}}

	    }
} 