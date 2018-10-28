package kKitKits;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import api.API;
import api.MessageAPI;
import kCooldown.CooldownAPI;
import kKit.Kit;
import kKit.KitAPI;

public class Lucky extends Kit
implements Listener
{
	
	public Lucky() {
		/*super("Lucky", new String[]{"","§7Tenha uma LuckyBlock",
				"§7e ganhe efeitos negativos ou positivos"}, new ItemStack(Material.SPONGE), "kit.lucky",KitType.BASIC);*/
		super("Lucky", 2000, false, new ItemStack(Material.SPONGE), true, "kit.lucky", true, new String[]{""," §e§l- §7Tenha uma LuckyBlock",
				" §e§l- §7e ganhe efeitos negativos ou positivos"});
	}

	@EventHandler
	  public void onLucky(PlayerInteractEvent e)
	  {
	    final Player p = e.getPlayer();
	    if (((p.getItemInHand().getType() == Material.SPONGE) && (e.getAction() == Action.RIGHT_CLICK_AIR)) || (
	      (e.getAction() == Action.RIGHT_CLICK_BLOCK) && (
	      (KitAPI.getKitName(p).equalsIgnoreCase("Lucky"))))) {
	      if (!CooldownAPI.Cooldown.containsKey(p.getName()))
	      {
	        Random r = new Random();
	        int rand = r.nextInt(5);
	        som(p, p.getLocation(), Sound.NOTE_PLING, 2.0F);
	        if (rand == 1)
	        {
	          p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 480, 0));
	          msg("§cVoce ficou com força por 20 segundos!", p);
	        }
	        if (rand == 2)
	        {
	          msg("§cSeu inventario foi preenchido de sopas!", p);
	          API.darSopas(p);
	        }
	        if (rand == 3)
	        {
	          p.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 480, 0));
	          msg("§cVoce ficou com poison por 20 segundos!", p);
	        }
	        if (rand == 4)
	        {
	          p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 480, 0));
	          msg("§cVoce ficou com BLINDNESS por 20 segundos!", p);
	        }
	        if (rand == 5)
	        {
	          p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 480, 0));
	          msg("§cVoce ficou com JUMP por 20 segundos!", p);
	        }
	        CooldownAPI.addCooldown(p, 45);
	      }
	      else
	      {
	        p.sendMessage(MessageAPI.Command_Error+"Você está com um Cooldown de §e" + CooldownAPI.Cooldown(p) + "s");
	      }
	    }
	  }
	  
	  public static void som(Player p, Location location, Sound som, float valor)
	  {
	    p.playSound(location, som, valor, valor);
	  }
	  
	  public static void msg(String msg, Player p)
	  {
	    p.sendMessage(msg);
	  }
	
}
