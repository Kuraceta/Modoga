package kKitKits;


import java.util.HashSet;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import api.MessageAPI;
import kCooldown.CooldownAPI;
import kKit.Kit;
import kKit.KitAPI;

public class Thor extends Kit implements Listener{
	
	public Thor() {
		//super("Thor", new String[]{"§7Crie um radio com um Maxado do Thor"}, new ItemStack(Material.GOLD_AXE), "kit.thor",KitType.BASIC);
		super("Thor", 2300, false, new ItemStack(Material.GOLD_AXE), true, "kit.thor", true, new String[]{" §e§l-§7Crie um radio com um Maxado do Thor"});
	}
	
	  @SuppressWarnings("deprecation")
	@EventHandler
	  public void ThorKit(PlayerInteractEvent e)
	  {
	    Player p = e.getPlayer();
	    if ((p.getItemInHand().getType() == Material.GOLD_AXE) && (e.getAction() == Action.RIGHT_CLICK_BLOCK) && 
	      (KitAPI.getKitName(p) == "Thor")) {
	      if (CooldownAPI.Cooldown.containsKey(p.getName())) {
	    		p.sendMessage(MessageAPI.Command_Error+"Você está com um Cooldown de §e" + CooldownAPI.Cooldown(p) + "s");
	        return;
	      }

	     CooldownAPI.addCooldown(p, 5);
	      Location loc = p.getTargetBlock((HashSet<Byte>) null, 25).getLocation();
	      p.getWorld().strikeLightning(loc);
	      e.setCancelled(true);
	      p.damage(0.0D);
	    }
	  }

}
