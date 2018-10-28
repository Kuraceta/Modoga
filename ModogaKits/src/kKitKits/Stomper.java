package kKitKits;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;

import kKit.Kit;
import kKit.KitAPI;

public class Stomper extends Kit implements Listener
{
    
	public Stomper() {
		/*super("Stomper", new String[]{" ","§7Pisoteie um Jogador","§7usando sua bota poderosa"},
				new ItemStack(Material.IRON_BOOTS), "kit.stomper",KitType.AVANCED);*/
		super("Stomper", 5000, false, new ItemStack(Material.IRON_BOOTS), false, "kit.stomper", false, new String[]{" "," §e§l- §7Pisoteie um Jogador"," §e§l- §7usando sua bota poderosa"});
	}
	
	@EventHandler(priority=EventPriority.HIGH)
	  public void onPlayerStomp(EntityDamageEvent e)  {
		if(e.isCancelled())return;
	    if (!(e.getEntity() instanceof Player)) {
	      return;
	    }
	    Player p = (Player)e.getEntity();
	    if (e.getCause() == EntityDamageEvent.DamageCause.FALL) {
	      if (KitAPI.getKitName(p).equalsIgnoreCase("Stomper")){
	        for (Entity ent : p.getNearbyEntities(5.0D, 3.0D, 5.0D)) {
	          if (!(ent instanceof Player))
	            continue;
	          Player plr = (Player)ent;
	          if (e.getDamage() <= 4.0D) {
	            e.setCancelled(true);
	            return;
	          }
	          if (plr.isSneaking() || KitAPI.getKitName(plr).equalsIgnoreCase("antistomper"))continue;
	            plr.damage(e.getDamage(), p);
	            plr.getKiller();
	        }

	        e.setDamage(4.0D);
	        return;
	      }
	      return;
	    }
    }
}
