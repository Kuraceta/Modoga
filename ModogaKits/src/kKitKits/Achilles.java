package kKitKits;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import kKit.Kit;
import kKit.KitAPI;
import main.Main;

public class Achilles extends Kit
  implements Listener
{
	
	public Achilles() {
		//super("Achilles", new String[]{" §e§l⚊ §7De mais dano segurando Shift."}, new ItemStack(Material.BONE), "kit.Achilles",KitType.BASIC);
	    super("Achilles", 1100, false, new ItemStack(Material.BONE), false, "kit.Achilles", true, new String[]{" §e§l⚊ §7De mais dano segurando Shift."});
	}
	
  public static HashMap<Player, Player> hit = new HashMap<Player, Player>();
  
  @EventHandler
  public void onachillesDamage(EntityDamageByEntityEvent e)
  {
    if (((e.getEntity() instanceof Player)) && ((e.getDamager() instanceof Player)))
    {
      final Player p = (Player)e.getEntity();
      final Player d = (Player)e.getDamager();
      if ((KitAPI.getKitName(p) == "Achilles") || (KitAPI.getKitName(p) == "Achilles")) {
        if (d.isSneaking())
        {
          e.setDamage(e.getDamage() * 1.0D);
        }
        else
        {
          e.setDamage(e.getDamage() / 2.0D);
          if ((hit.get(d) == null) || (hit.get(d) != p))
          {
            hit.put(d, p);
            d.sendMessage("§cEle é um Achillees , agaiche para dar mais dano nele!");
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable()
            {
              public void run()
              {
                if (Achilles.hit.get(d) == p) {
                  Achilles.hit.remove(d);
                }
              }
            }, 200L);
          }
        }
      }
    }
  }
}
