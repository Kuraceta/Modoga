package kEvents;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import kKit.KitAPI;
import main.Main;
import mysqlManager.Status;

public class CombatLog implements Listener
{
	  
	  public static List<String> Sair = new ArrayList<String>();
	  
	  @EventHandler
	  public void onEntityDamage(EntityDamageByEntityEvent e)
	  {
		  if(e.isCancelled()){
			  return;
		  }
	    if (((e.getDamager() instanceof Player)) && ((e.getEntity() instanceof Player)))
	    {
	      final Player p = (Player)e.getEntity();
	      final Player hitter = (Player)e.getDamager();
	      if(KitAPI.getKitName(p) == "Nenhum" || KitAPI.getKitName(hitter) == "Nenhum"){
	    	  return;
	      }
	      if(hitter.getGameMode() == GameMode.CREATIVE){
	    	  return;
	      }
	      if ((!Sair.contains(p.getName())) && (!Sair.contains(hitter.getName())))
	      {
	        Sair.add(p.getName());
	        Sair.add(hitter.getName());
	        p.sendMessage("§4§lPvP §fVoce entrou em combate com §c" + hitter.getName());
	        hitter.sendMessage("§4§lPvP §fVoce entrou em combate com §c" + p.getName());
	        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable()
	        {
	          public void run()
	          {
	            CombatLog.Sair.remove(p.getName());
	            CombatLog.Sair.remove(hitter.getName());
	            p.sendMessage("§4§lPvP §fVoce nao esta mais em combate !");
	            hitter.sendMessage("§4§lPvP §fVoce nao esta mais em combate !");
	          }
	        }, 200L);
	      }
	    }
	  }
	  
	  @EventHandler(priority = EventPriority.HIGHEST)
	  public void onPlayerExit(PlayerQuitEvent e) throws SQLException
	  {
	    Player p = e.getPlayer();
	    if (Sair.contains(p.getName()))
	    {
	      p.setHealth(0.0D);
	      p.teleport(p.getWorld().getSpawnLocation());
	      Bukkit.broadcastMessage( "§4§lPvP §c" + p.getName() + " §fdeslogou em combate e perdeu §610 §7de §eXP §7!");
	      Status.removeCoins(p, 10);
	    }
	  }
	  
	  @EventHandler
	  public void onMe2(PlayerCommandPreprocessEvent event)
	  {
	    Player p = event.getPlayer();
	    if ((Sair.contains(p.getName())) && (
	      (event.getMessage().toLowerCase().startsWith("/"))))
	    {
	      event.setCancelled(true);
	      p.sendMessage("§4§lPvP §cVoce está em combate !");
	    }
	  }
}
