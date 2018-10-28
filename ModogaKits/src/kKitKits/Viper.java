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

public class Viper extends Kit implements Listener{
	
	public Viper() {
		//super("Viper", new String[]{"§7Tenha chance de colocar veneno em seus inimigos"}, new ItemStack(Material.SPIDER_EYE), "kit.Viper",KitType.AVANCED);
		super("Viper", 3500, false, new ItemStack(Material.SPIDER_EYE), false, "kit.viper", false, new String[]{" §e§l- §7Tenha chance de colocar veneno em seus inimigos"});
	}
	
	@EventHandler
	public void onPosion(EntityDamageByEntityEvent e) {
		if (((e.getDamager() instanceof Player)) && ((e.getEntity() instanceof Player))) {
			Player p = (Player)e.getEntity();
			Player d = (Player)e.getDamager();
			if (KitAPI.getKitName(d) == "Viper")  {
				Random r = new Random();
				int rand = r.nextInt(100);
				if (rand >= 67)
					p.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 80, 0));
				}
	    	}
	  }
 }
