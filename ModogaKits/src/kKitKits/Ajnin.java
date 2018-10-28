package kKitKits;

import java.util.HashMap;

import org.bukkit.Bukkit;
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

public class Ajnin extends Kit implements Listener{

	public Ajnin() {
		//super("Ajnin", new String[] {" ","§7Teletransporte o ultimo jogador","§7hitado até você usando Shift"},new ItemStack(Material.CARPET), "kit.Ajnin",KitType.BASIC);
	    super("Ajnin", 2000, false, new ItemStack(Material.CARPET), false, "kit.Ajnin", true,  new String[] {" "," §e§l⚊  §7Teletransporte o ultimo jogador"," §e§l⚊  §7hitado até você usando Shift"});
	}
	
	public static HashMap<Player, Player> a = new HashMap<Player, Player>();

	  @EventHandler
	  public void a(EntityDamageByEntityEvent e) {
	    if (((e.getDamager() instanceof Player)) && ((e.getEntity() instanceof Player))){
	      final Player hitou = (Player)e.getDamager();
	      Player hitado = (Player)e.getEntity();
	      if (KitAPI.getKitName(hitou).equalsIgnoreCase(this.getName()) && !a.containsKey(hitou)){
	        a.put(hitou, hitado);
	        
	        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
				public void run() {
					a.remove(hitou);
					a.remove(hitado);
				}
			}, 11*20);
	      }
	    }
	  }

	  @EventHandler
	  public void a(PlayerToggleSneakEvent e) {
	    Player hitou = e.getPlayer();
	    if ((e.isSneaking()) && (KitAPI.getKitName(hitou).equalsIgnoreCase(this.getName())) && (CooldownAPI.Cooldown.containsKey(hitou.getName()))){
	    	hitou.sendMessage(MessageAPI.Command_Error+"Você está com um Cooldown de §e" + CooldownAPI.Cooldown(hitou) + "s");
	    	return;
	    }
	    
	    if ((e.isSneaking()) && (KitAPI.getKitName(hitou).equalsIgnoreCase(this.getName())) && (a.containsKey(hitou))){
	      Player hitado = (Player)a.get(hitou);
	     
	      if ((hitado != null)){
	    	  if(Gladiator.noExecut.contains(hitado)){
	    		  hitou.sendMessage(MessageAPI.Command_Error+"Esse player está em um gladiator");
	    		  return;
	    	  }
	    	  if(Gladiator.noExecut.contains(hitou)){
	    		  hitou.sendMessage(MessageAPI.Command_Error+"Habilidade proibida em gladiator");
	    		  return;
	    	  }
	          if (hitou.getLocation().distance(hitado.getLocation()) < 40.0D){
	        	  
	            hitado.teleport(hitou.getLocation());
	            CooldownAPI.addCooldown(hitou, 7);
	          }else{
	            hitou.sendMessage(MessageAPI.Command_Error+"O player está muito longe de você");
	          }
	        }
	    }
	  }
}
