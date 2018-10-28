package kEvents;

import java.util.ArrayList;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Blaze;
import org.bukkit.entity.CaveSpider;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.Cow;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.Ghast;
import org.bukkit.entity.Horse;
import org.bukkit.entity.MagmaCube;
import org.bukkit.entity.MushroomCow;
import org.bukkit.entity.Ocelot;
import org.bukkit.entity.Pig;
import org.bukkit.entity.PigZombie;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;
import org.bukkit.entity.Silverfish;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Slime;
import org.bukkit.entity.Spider;
import org.bukkit.entity.Squid;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Witch;
import org.bukkit.entity.Wither;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

public class Esponja
  implements Listener
{
  public static ArrayList<String> jump = new ArrayList<>();
  
  @EventHandler(priority = EventPriority.HIGHEST)
  public void onPlayerJump(PlayerMoveEvent e) {
    Player p = e.getPlayer();
    if (e.getTo().getBlock().getRelative(BlockFace.DOWN).getType() == Material.SPONGE)
    {
      jump.remove(p.getName());
      Location loc = e.getTo().getBlock().getLocation();
      Vector sponge = p.getLocation().getDirection().multiply(0).setY(4.4);
      p.setVelocity(sponge);
      p.playSound(loc, Sound.SLIME_WALK2, 4.0F, 0.7F);
      p.getEyeLocation().getWorld().playEffect(p.getLocation(), Effect.MOBSPAWNER_FLAMES, 5);
      p.getLocation().getWorld().playEffect(p.getLocation(), Effect.GHAST_SHOOT, 4);
      p.getLocation().getWorld().playEffect(p.getLocation(), Effect.BLAZE_SHOOT, 3);
      jump.add(p.getName());
      jump.remove(p.getName());
      jump.add(p.getName());
      return;
    }
  }

  @EventHandler(priority = EventPriority.HIGHEST)
  public void onPlayerDamageSponge(EntityDamageEvent e) {
    if (!(e.getEntity() instanceof Player)) {
      return;
    }
    Player p = (Player)e.getEntity();
    if (e.getCause() == EntityDamageEvent.DamageCause.FALL) {
      return;
    }
    if (jump.contains(p.getName()))
    {
      jump.remove(p.getName());
      return;
    }
  }

  @EventHandler(priority=EventPriority.HIGHEST)
  public void onEntityDamageSponge(EntityDamageEvent event) {
    if ((event.getEntity() instanceof Player))
    {
      Player player = (Player)event.getEntity();
      Location loc = player.getLocation();
      Location below = loc.subtract(0.0D, 0.7D, 0.0D);
      Block blockBelow = below.getBlock();
      if (event.getCause() == EntityDamageEvent.DamageCause.FALL)
      {
        if (blockBelow.getType() == Material.SPONGE)
        {
          jump.add(player.getName());
          event.setDamage(0.0D);
          return;
        }
        return;
      }
      return;
    }
  }

  @EventHandler(priority=EventPriority.MONITOR)
  public void onPlayerSpongeDamage(EntityDamageEvent e) {
    if (!(e.getEntity() instanceof Player)) {
      return;
    }
    Player p = (Player)e.getEntity();
    if (e.getCause() == EntityDamageEvent.DamageCause.FALL)
    {
      if (jump.contains(p.getName()))
      {
        jump.remove(p.getName());
        e.setDamage(0.0D);
        return;
      }
      return;
    }
  }
  
  @EventHandler(priority = EventPriority.HIGH)
  public void onCreeperExplosion(final EntityExplodeEvent event) {
      event.setCancelled(true);
  }
  
  @EventHandler
  public void onFogoNaoNoChao(final BlockIgniteEvent e) {
      e.setCancelled(true);
  }
  
  @EventHandler
  public void NaoSpawnarMobs(final CreatureSpawnEvent e) {
      if (e.getEntity() instanceof Creeper) {
          e.setCancelled(true);
      }
      if (e.getEntity() instanceof Skeleton) {
          e.setCancelled(true);
      }
      if (e.getEntity() instanceof Spider) {
          e.setCancelled(true);
      }
      if (e.getEntity() instanceof Wither) {
          e.setCancelled(true);
      }
      if (e.getEntity() instanceof Zombie) {
          e.setCancelled(true);
      }
      if (e.getEntity() instanceof Slime) {
          e.setCancelled(true);
      }
      if (e.getEntity() instanceof Ghast) {
          e.setCancelled(true);
      }
      if (e.getEntity() instanceof PigZombie) {
          e.setCancelled(true);
      }
      if (e.getEntity() instanceof Pig) {
          e.setCancelled(true);
      }
      if (e.getEntity() instanceof Enderman) {
          e.setCancelled(true);
      }
      if (e.getEntity() instanceof CaveSpider) {
          e.setCancelled(true);
      }
      if (e.getEntity() instanceof Silverfish) {
          e.setCancelled(true);
      }
      if (e.getEntity() instanceof Blaze) {
          e.setCancelled(true);
      }
      if (e.getEntity() instanceof MagmaCube) {
          e.setCancelled(true);
      }
      if (e.getEntity() instanceof Witch) {
          e.setCancelled(true);
      }
      if (e.getEntity() instanceof Sheep) {
          e.setCancelled(true);
      }
      if (e.getEntity() instanceof Cow) {
          e.setCancelled(true);
      }
      if (e.getEntity() instanceof Chicken) {
          e.setCancelled(true);
      }
      if (e.getEntity() instanceof Squid) {
          e.setCancelled(true);
      }
      if (e.getEntity() instanceof MushroomCow) {
          e.setCancelled(true);
      }
      if (e.getEntity() instanceof Ocelot) {
          e.setCancelled(true);
      }
      if (e.getEntity() instanceof Villager) {
          e.setCancelled(false);
      }
      if (e.getEntity() instanceof Horse) {
          e.setCancelled(false);
      }
      if (e.getEntity() instanceof EnderDragon) {
          e.setCancelled(true);
      }
  }
  
}