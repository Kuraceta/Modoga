package kEvents;

import java.util.ArrayList;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

public class BlocoDeEsmeralda
  implements Listener
{
  public static ArrayList<String> jump = new ArrayList<>();
  ArrayList<String> nofalldamage = new ArrayList<>();

  
  @EventHandler(priority = EventPriority.HIGHEST)
  public void onPlayerFrente(PlayerMoveEvent e)
  {
    Player p = e.getPlayer();
    if (e.getTo().getBlock().getRelative(BlockFace.DOWN).getType() == Material.EMERALD_BLOCK)
    {
        Location loc = e.getTo().getBlock().getLocation();
      Vector sponge = p.getLocation().getDirection().multiply(3.1D).setY(1.0D);
      p.playSound(loc, Sound.SLIME_WALK2, 6.0F, 1.0F);
      p.getEyeLocation().getWorld().playEffect(p.getLocation(), Effect.SMOKE, 10);
      p.getLocation().getWorld().playEffect(p.getLocation(), Effect.ENDER_SIGNAL, 8);
      p.setVelocity(sponge);
      if (!this.nofalldamage.contains(p.getName())) {
        this.nofalldamage.add(p.getName());
      }
      return;
    }
  }

  @EventHandler(priority = EventPriority.HIGHEST)
  public void onEntityDamage(EntityDamageEvent event)
  {
    if ((event.getEntity() instanceof Player))
    {
      Player player = (Player)event.getEntity();
      if ((this.nofalldamage.contains(player.getName())) && 
        (event.getCause().equals(EntityDamageEvent.DamageCause.FALL)))
      {
        event.setCancelled(true);
        this.nofalldamage.remove(player.getName());
      }
    }
  }
}