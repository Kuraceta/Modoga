package kKitKits;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;

import api.MessageAPI;
import kCooldown.CooldownAPI;
import kKit.Kit;
import kKit.KitAPI;
import main.Main;

public class Timelord extends Kit implements Listener{
	
	public Timelord() {
		//super("Timelord", new String[]{"§7Congele seus inimigos"}, new ItemStack(Material.WATCH), "kit.Timerlord",KitType.BASIC);
		super("Timelord", 2100, false, new ItemStack(Material.WATCH), true, "kit.timelord", true, new String[]{" §e§l- §7Congele seus inimigos"});
	}
	
	public static ArrayList<String> frozenPlayers = new ArrayList<String>();

	  
	  @EventHandler
	  public void onTimeLord(PlayerInteractEvent e)
	  {
	    final Player p = e.getPlayer();
	    if ((p.getItemInHand().getType() == Material.WATCH) && ((e.getAction() == Action.RIGHT_CLICK_AIR) || (e.getAction() == Action.RIGHT_CLICK_BLOCK))) {
	    	  if (KitAPI.getKitName(p) == "Timelord"){
	    	if (CooldownAPI.Cooldown.containsKey(p.getName()))   {
	    		p.sendMessage(MessageAPI.Command_Error+"Você está com um Cooldown de §e" + CooldownAPI.Cooldown(p) + "s");
	        return;
	    	}
	      }
	      if (KitAPI.getKitName(p) == "Timelord"){
	    	  
	        List<Entity> ne = e.getPlayer().getNearbyEntities(5.0D, 5.0D, 5.0D);
	        for (Entity t : ne) {
	          if ((t instanceof Player)){
	            final Player ta = (Player)t;
	            frozenPlayers.add(((Player)t).getName());
	            ((Player)t).sendMessage("§7» Você foi congelado por um §c§lTIMELORD");
	            
	           CooldownAPI.addCooldown(p, 35);
	            p.sendMessage("§7» Você congelou os players envolta de você");
	            
	            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable()
	            {
	              public void run()
	              {
	                frozenPlayers.remove(ta.getName());
	              }
	            }, 5 * 20);
	          }
	        }
	      }
	    }
	  }
	  
	  @EventHandler
	  public void onTimeLordado(PlayerMoveEvent e)
	  {
	    if (frozenPlayers.contains(e.getPlayer().getName())) {
	      e.getPlayer().teleport(e.getPlayer());
	    }
	  }
	}

