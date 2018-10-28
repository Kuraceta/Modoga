package kKitKits;

import java.text.DecimalFormat;
import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.ItemStack;

import api.MessageAPI;
import kCooldown.CooldownAPI;
import kKit.Kit;
import kKit.KitAPI;
import main.Main;

public class Ninja extends Kit implements Listener{
	
	public Ninja() {
		//super("Ninja", new String[]{"§7Teleporte até o ultimo jogador hitado"}, new ItemStack(Material.NETHER_STAR), "kit.ninja",KitType.AVANCED);
		super("Ninja", 5000, false,  new ItemStack(Material.NETHER_STAR), false, "kit.ninja", false, new String[]{" §e§l- §7Teleporte até o ultimo jogador hitado"});
	}
	
  public static HashMap<Player, Player> a = new HashMap<Player, Player>();
  public static HashMap<Player, Long> b = new HashMap<Player, Long>();
  public static Main plugin;

  @EventHandler
  public void a(EntityDamageByEntityEvent paramEntityDamageByEntityEvent) {
	  try {
		  if (((paramEntityDamageByEntityEvent.getDamager() instanceof Player)) && ((paramEntityDamageByEntityEvent.getEntity() instanceof Player)))
		    {
		      final Player localPlayer1 = (Player)paramEntityDamageByEntityEvent.getDamager();
		      Player localPlayer2 = (Player)paramEntityDamageByEntityEvent.getEntity();
		      if (KitAPI.getKitName(localPlayer1) == "Ninja")
		      {
		        a.put(localPlayer1, localPlayer2);

		      }
		    }
	} catch (Exception e) {}
    
  }

  @EventHandler
  public void a(PlayerToggleSneakEvent paramPlayerToggleSnakeEvent) {
	  try {
		  Player localPlayer1 = paramPlayerToggleSnakeEvent.getPlayer();
		    if ((paramPlayerToggleSnakeEvent.isSneaking()) && (KitAPI.getKitName(localPlayer1) == "Ninja") && (CooldownAPI.Cooldown.containsKey(localPlayer1.getName()))){
		    	localPlayer1.sendMessage(MessageAPI.Command_Error+"Você está com um Cooldown de §e" + CooldownAPI.Cooldown(localPlayer1) + "s");
		    	return;
		    }
		    if ((paramPlayerToggleSnakeEvent.isSneaking()) && (KitAPI.getKitName(localPlayer1) == "Ninja") && (a.containsKey(localPlayer1))){
		      Player localPlayer2 = (Player)a.get(localPlayer1);
		      if ((localPlayer2 != null) && (!localPlayer2.isDead()))
		      {
		    	  if(Gladiator.noExecut.contains(localPlayer2)){
		    		  localPlayer1.sendMessage("§7» Esse player está em um gladiator");
		    		  return;
		    	  }
		    	  if(Gladiator.noExecut.contains(localPlayer1)){
		    		  localPlayer1.sendMessage("§7» Habilidade proibida em gladiator");
		    		  return;
		    	  }
		        @SuppressWarnings("unused")
				String str = null;
		        if (b.get(localPlayer1) != null)
		        {
		          long l = ((Long)b.get(localPlayer1)).longValue() - System.currentTimeMillis();
		          DecimalFormat localDecimalFormat = new DecimalFormat("##");
		          int i = (int)l / 1000;
		          str = localDecimalFormat.format(i);
		          }
		        }
		        if ((b.get(localPlayer1) == null) || (((Long)b.get(localPlayer1)).longValue() < System.currentTimeMillis())) {
		          if (localPlayer1.getLocation().distance(localPlayer2.getLocation()) < 100.0D){
		            localPlayer1.teleport(localPlayer2.getLocation());
		            CooldownAPI.addCooldown(localPlayer1, 7);
		            b.put(localPlayer1, Long.valueOf(System.currentTimeMillis() + 10000L));
		          }
		          else{
		            localPlayer1.sendMessage("§7» O player está muito longe de você");
		          }
		        }
		    }
	} catch (Exception e) {}
    
  }
}