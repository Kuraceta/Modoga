package kKitKits;

import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import kKit.Kit;
import kKit.KitAPI;

public class Critical extends Kit
  implements Listener
{
	
	public Critical() {
		//super("Critical", new String[]{""," §e§l- Tenha chance de dar um golpe Critico"}, new ItemStack(Material.REDSTONE), "kit.critical",KitType.AVANCED);
		super("Critical", 1500, false, new ItemStack(Material.REDSTONE), false, "kit.critical", true, new String[]{""," §e§l- Tenha chance de dar um golpe Critico"});
	}
	
  @EventHandler
  public void dano(EntityDamageByEntityEvent e)
  {
    if (((e.getEntity() instanceof Player)) && ((e.getDamager() instanceof Player)))
    {
      Player p = (Player)e.getEntity();
      Player d = (Player)e.getDamager();
      if (KitAPI.getKitName(d) == "Critical")
      {
        Random r = new Random();
        int c = r.nextInt(100);
        if (c <= 30)
        {
          e.setDamage(e.getDamage() + 1.0D);
          p.getWorld().playEffect(p.getLocation(), Effect.STEP_SOUND, Material.REDSTONE_BLOCK, 10);
          p.sendMessage("§6§lCritical §7Voce recebeu um golpe critico de " + ChatColor.DARK_RED + d.getName());
        }
      }
    }
  }
}
