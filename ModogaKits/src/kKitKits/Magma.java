package kKitKits;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;

import kKit.Kit;
import kKit.KitAPI;


public class Magma extends Kit implements Listener{
	
	public Magma() {
		//super("Magma", new String[]{"§7Não leve dano de fogo/lava"}, new ItemStack(Material.FIREBALL), "kit.magma",KitType.AVANCED);
		super("Magma", 3000, false,  new ItemStack(Material.FIREBALL), false, "kit.magma", true, new String[]{" §e§l - §7Não leve dano de fogo/lava"});
	}
	
	 @EventHandler
	  public void damage(EntityDamageEvent e) {
	    if ((e.getEntity() instanceof Player))
	    {
	      Player p = (Player)e.getEntity();
	      if ((KitAPI.getKitName(p) == "Magma") && (
	        (e.getCause() == EntityDamageEvent.DamageCause.LAVA) || 
	        (e.getCause() == EntityDamageEvent.DamageCause.FIRE) || 
	        (e.getCause() == EntityDamageEvent.DamageCause.FIRE_TICK)))
	        e.setCancelled(true); 
	    }
	  }

	  @EventHandler
	  public void snailEventd(EntityDamageByEntityEvent event) {
	    if ((!(event.getEntity() instanceof Player)) || 
	      (!(event.getDamager() instanceof Player))) {
	      return;
	    }

	    Player player = (Player)event.getDamager();
	    Player player1 = (Player)event.getEntity();

	    if (KitAPI.getKitName(player) != "Magma") {
	      return;
	    }
	    if ((Math.random() > 0.5D) && (Math.random() < 0.4D))
	      player1.setFireTicks(150);
	  }
	}
