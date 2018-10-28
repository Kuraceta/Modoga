package kKitKits;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import kKit.Kit;
import kKit.KitAPI;


public class Switcher extends Kit implements Listener{
	
	public Switcher() {
		//super("Switcher", new String[] {" ","§7Teleporte até algum usando uma bola de neve"},new ItemStack(Material.SNOW_BALL), "kit.Switcher",KitType.BASIC);
		super("Switcher", 2000, false, new ItemStack(Material.SNOW_BALL,16), true, "kit.switcher", true, new String[] {" "," §e§l- §7Teleporte alguém usando uma bola de neve"});
	}
	
	
	@SuppressWarnings("deprecation")
	  @EventHandler
	    public void snowball(EntityDamageByEntityEvent e) {
	      if (((e.getDamager() instanceof Snowball)) && 
	        ((e.getEntity() instanceof Player)))
	      {
	        Snowball s = (Snowball)e.getDamager();
	        if ((s.getShooter() instanceof Player))
	        {
	          Player shooter = (Player)s.getShooter();
	          if (KitAPI.getKitName(shooter) == "Switcher")
	          {
	            Location shooterLoc = shooter.getLocation();
	            shooter.teleport(e.getEntity().getLocation());
	            e.getEntity().teleport(shooterLoc);
	            shooter.getPlayer().getWorld().playEffect(shooterLoc, Effect.ENDER_SIGNAL, 10);
	            shooter.getPlayer().getWorld().playEffect(shooterLoc, Effect.EXTINGUISH, 10);
	            shooter.getWorld().playSound(shooter.getLocation(), Sound.ENDERMAN_TELEPORT, 1.0F, 1.2F);
	          }
	        }
	      }
	  }

}
