package kKitKits;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import kKit.Kit;
import kKit.KitAPI;


public class Snail extends Kit implements Listener{
	
	public Snail() {
		//super("Snail", new String[] {" ","§7Tenha chance de deixar seu inimigo lento"},new ItemStack(Material.WEB), "kit.Snail",KitType.AVANCED);
		super("Snail", 3500, false, new ItemStack(Material.WEB), false, "kit.snail", false, new String[] {" "," §e§l- §7Tenha chance de deixar seu inimigo lento"});
	}
	
	  @EventHandler
	  public void onPosion(EntityDamageByEntityEvent e) {
	    if (((e.getDamager() instanceof Player)) && ((e.getEntity() instanceof Player)))
	    {
	      Player p = (Player)e.getEntity();
	      Player d = (Player)e.getDamager();
	      if (KitAPI.getKitName(d) == "Snail")  {
	        Random r = new Random();
	        int rand = r.nextInt(100);
	        if (rand >= 67)
	          p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 80, 0));
	      }
	    }
	  }
 }
