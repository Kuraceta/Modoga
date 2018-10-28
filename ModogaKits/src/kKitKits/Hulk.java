package kKitKits;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import api.MessageAPI;
import kCooldown.CooldownAPI;
import kKit.Kit;
import kKit.KitAPI;

public class Hulk extends Kit implements Listener{
	
	public Hulk() {
		/*super("Hulk", new String[]{"§7Coloque seus inimigos em suas costas","§7e bata com facilidade"}
		, new ItemStack(Material.SADDLE), "kit.hulk",KitType.BASIC);*/
		super("Hulk", 1500, false, new ItemStack(Material.SADDLE), false, "kit.hulk", true, new String[]{" §e§l- §7Coloque seus inimigos em suas costas"," §e§l- §7e bata com facilidade"});
	}
	
	  @EventHandler
	  public void hulk(PlayerInteractEntityEvent event)
	  {
	    if (!(event.getRightClicked() instanceof Player)) {
	      return;
	    }
	    final Player p = event.getPlayer();
	    final Player r = (Player)event.getRightClicked();
	    if ((p.getItemInHand().getType() == Material.AIR) && (KitAPI.getKitName(p) == "Hulk") && (!CooldownAPI.Cooldown.containsKey(p.getName()) && (p.getPassenger() == null) && (r.getPassenger() == null))) {
	      p.setPassenger(r);
	      CooldownAPI.addCooldown(p, 15);
	      return;
	  }
	    if ((p.getItemInHand().getType() == Material.AIR) && (KitAPI.getKitName(p) == "Hulk") && (CooldownAPI.Cooldown.containsKey(p.getName()) && (p.getPassenger() == null) && (r.getPassenger() == null))) {
		p.sendMessage(MessageAPI.Command_Error+"Você está com um Cooldown de §e" + CooldownAPI.Cooldown(p) + "s");
	    }
	 }
	    	  @EventHandler
	    	  public static void playerInteract(PlayerInteractEvent event) {
	    	    if (!event.getAction().equals(Action.LEFT_CLICK_AIR)) {
	    	      return;
	    	    }
	    	    Player player = event.getPlayer();
	    	    if ((player.getPassenger() == null) || (!(player.getPassenger() instanceof Player))) {
	    	      return;
	    	    }
	    	    Player pass = (Player)player.getPassenger();
	    	    player.eject();
	    	    pass.damage(0.0D, player);
	    	    pass.setVelocity(new Vector(pass.getVelocity().getX(), 1.0D, pass.getVelocity().getZ()));
	    }
	    	
	}
