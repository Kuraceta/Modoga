package kKitKits;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import api.MessageAPI;
import kCooldown.CooldownAPI;
import kKit.Kit;
import kKit.KitAPI;
import main.Main;

public class Gladiator extends Kit implements Listener{

	public boolean generateGlass = true;
	public static ArrayList<Player> noExecut = new ArrayList<Player>();
	  public static HashMap<String, Location> oldl = new HashMap<String, Location>();
	  public static HashMap<String, String> fighting = new HashMap<String, String>();
	  public HashMap<Integer, ArrayList<Location>> blocks = new HashMap<Integer, ArrayList<Location>>();
	  public static HashMap<Player, Location> localizacao = new HashMap<Player, Location>();
	  public static HashMap<Location, Block> bloco = new HashMap<Location, Block>();
	  public HashMap<Integer, String[]> players = new HashMap<Integer, String[]>();
	  public HashMap<String, Integer> tasks = new HashMap<String, Integer>();
	  int nextID = 0;
	  public static int id1;
	  public static int id2;
	
	public Gladiator() {
		//super("Gladiator", new String[] {" ","§7Crie uma arena 1v1 no Céu"},new ItemStack(Material.IRON_FENCE), "kit.gladiator",KitType.AVANCED);
		super("Gladiator", 3000, false, new ItemStack(Material.IRON_FENCE), true, "kit.gladiador", false, new String[] {" "," §e§l⚊  §7Crie uma arena 1v1 no Céu"});
	}
	
	@EventHandler
	public void onplace(BlockPlaceEvent e) {
		if(KitAPI.getKitPlayer(e.getPlayer()) != null && KitAPI.getKitPlayer(e.getPlayer()).getName().equalsIgnoreCase("gladiator") ) {
			e.setCancelled(false);
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable()
	  	      {
	  	        public void run()
	  	        {
	  	          e.getBlock().setType(Material.AIR);
	  	        }
	  	      }, 20L * 7L);
		}
	}
	
	@SuppressWarnings({ "static-access" })
	@EventHandler
	  public void OnGladiat0orKit(PlayerInteractEntityEvent event) {
	    final Player p = event.getPlayer();
	    if ((event.getRightClicked() instanceof Player)){
	      final Player r = (Player)event.getRightClicked();
	      if (p.getItemInHand().getType() == Material.IRON_FENCE && KitAPI.getKitName(p).equalsIgnoreCase(this.getName()) && CooldownAPI.Cooldown.containsKey(p.getName())){
	    	  p.sendMessage(MessageAPI.Command_Error+"Você está com um Cooldown de §e" + CooldownAPI.Cooldown(p) + "s");
	    		  return;
	      }
	      if (p.getItemInHand().getType() == Material.IRON_FENCE && KitAPI.getKitName(p).equalsIgnoreCase(this.getName()) && !CooldownAPI.Cooldown.containsKey(p.getName())){
	        event.setCancelled(true);
	        Location loc = new Location(p.getWorld(), p.getLocation().getBlockX(), p.getLocation().getBlockY() + 70, p.getLocation().getBlockZ());
	        this.localizacao.put(p, loc);
	        this.localizacao.put(r, loc);
	        Location loc2 = new Location(p.getWorld(), p.getLocation().getBlockX() + 8, p.getLocation().getBlockY() + 73, p.getLocation().getBlockZ() + 8);
	        Location loc3 = new Location(p.getWorld(), p.getLocation().getBlockX() - 8, p.getLocation().getBlockY() + 73, p.getLocation().getBlockZ() - 8);
	        if ((fighting.containsKey(p.getName())) || (fighting.containsKey(r.getName())))
	        {
	          event.setCancelled(true);
	          return;
	        }
	        Integer currentID = Integer.valueOf(this.nextID);
	        this.nextID += 1;
	        ArrayList<String> list = new ArrayList<String>();
	        list.add(p.getName());
	        list.add(r.getName());
	        this.players.put(currentID, (String[])list.toArray(new String[1]));
	        this.oldl.put(p.getName(), p.getLocation());
	        this.oldl.put(r.getName(), r.getLocation());
	        if (this.generateGlass)
	        {
	          List<Location> cuboid = new ArrayList<Location>();
	          cuboid.clear();
	          int bY;
	          for (int bX = -10; bX <= 10; bX++) {
	            for (int bZ = -10; bZ <= 10; bZ++) {
	              for (bY = -1; bY <= 10; bY++)
	              {
	                Block b = loc.clone().add(bX, bY, bZ).getBlock();
	                if (!b.isEmpty())
	                {
	                  event.setCancelled(true);
	                  p.sendMessage(MessageAPI.Command_Error+"Você não pode puxar nesse LOCAL");
	                  return;
	                }
	                if (bY == 10) {
	                  cuboid.add(loc.clone().add(bX, bY, bZ));
	                } else if (bY == -1) {
	                  cuboid.add(loc.clone().add(bX, bY, bZ));
	                } else if ((bX == -10) || (bZ == -10) || (bX == 10) || (bZ == 10)) {
	                  cuboid.add(loc.clone().add(bX, bY, bZ));
	                }
	              }
	            }
	          }
	          for (Location loc1 : cuboid)
	          {
	            loc1.getBlock().setType(Material.GLASS);
	            this.bloco.put(loc1, loc1.getBlock());
	          }
	          loc2.setYaw(135.0F);
	          p.teleport(loc2);
	          loc3.setYaw(-45.0F);
	          r.teleport(loc3);
	          p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 110, 5));
	          r.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 110, 5));
	      	  p.getInventory().remove(Material.IRON_FENCE);
	      	  r.getInventory().remove(Material.IRON_FENCE);
	      	 CooldownAPI.addCooldown(p, 35);
	          noExecut.add(p);
	          noExecut.add(r);
	          fighting.put(p.getName(), r.getName());
	          fighting.put(r.getName(), p.getName());
	          this.id2 = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable()
	          {
	            public void run()
	            {
	              if ((Gladiator.fighting.containsKey(p.getName())) && (((String)Gladiator.fighting.get(p.getName())).equalsIgnoreCase(r.getName())) && (Gladiator.fighting.containsKey(r.getName())) && (((String)Gladiator.fighting.get(r.getName())).equalsIgnoreCase(p.getName())))
	              {
	              }
	            }
	          }, 2400L);
	          this.id1 = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable()
	          {
	            public void run()
	            {
	              if ((Gladiator.fighting.containsKey(p.getName())) && (((String)Gladiator.fighting.get(p.getName())).equalsIgnoreCase(r.getName())) && (Gladiator.fighting.containsKey(r.getName())) && (((String)Gladiator.fighting.get(r.getName())).equalsIgnoreCase(p.getName())))
	              {
	                Gladiator.fighting.remove(p.getName());
	                Gladiator.fighting.remove(r.getName());
	                noExecut.remove(p);
	                noExecut.remove(r);
	                p.teleport((Location)Gladiator.this.oldl.get(p.getName()));
	                r.teleport((Location)Gladiator.this.oldl.get(r.getName()));
	                Gladiator.this.oldl.remove(p.getName());
	                Gladiator.this.oldl.remove(r.getName());
	                Location loc = (Location)Gladiator.this.localizacao.get(p);
	                List<Location> cuboid = new ArrayList<Location>();
	                int bY;
	                for (int bX = -10; bX <= 10; bX++) {
	                  for (int bZ = -10; bZ <= 10; bZ++) {
	                    for (bY = -1; bY <= 10; bY++) {
	                      if (bY == 10) {
	                        cuboid.add(loc.clone().add(bX, bY, bZ));
	                      } else if (bY == -1) {
	                        cuboid.add(loc.clone().add(bX, bY, bZ));
	                      } else if ((bX == -10) || (bZ == -10) || (bX == 10) || (bZ == 10)) {
	                        cuboid.add(loc.clone().add(bX, bY, bZ));
	                      }
	                    }
	                  }
	                }
	                for (Location loc1 : cuboid)
	                {
	                  loc1.getBlock().setType(Material.AIR);
	                  ((Block)Gladiator.this.bloco.get(loc1)).setType(Material.AIR);
	                }
	              }
	            }
	          }, 100000L);
	        }
	      }
	    }
	  }
	  @EventHandler
	  public void onPlayerInteractGlad(PlayerInteractEvent e)
	  {
	    Player p = e.getPlayer();
	    if ((p.getItemInHand().getType() == Material.IRON_FENCE) || (KitAPI.getKitName(p).equalsIgnoreCase(this.getName())))
	    {
	      e.setCancelled(true);
	      p.updateInventory();
	      return;
	    }
	  }
	  @EventHandler(priority = EventPriority.MONITOR)
	    public void I(final PlayerInteractEvent playerInteractEvent) {
	        if (playerInteractEvent.getAction() == Action.LEFT_CLICK_BLOCK && playerInteractEvent.getClickedBlock().getType() == Material.GLASS && playerInteractEvent.getPlayer().getGameMode() != GameMode.CREATIVE) {
	            playerInteractEvent.setCancelled(true);
	            playerInteractEvent.getClickedBlock().setType(Material.BEDROCK);
	            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable()
	  	      {
	  	        public void run()
	  	        {
	  	          if (noExecut.contains(playerInteractEvent.getPlayer())) {
	  	        	playerInteractEvent.getClickedBlock().setType(Material.GLASS);
	  	          }
	  	        }
	  	      }, 30L);
	        }
	    }
	@SuppressWarnings("static-access")
	@EventHandler(priority=EventPriority.HIGHEST)
	  public void onPlayerLeft(PlayerQuitEvent e)
	  {
	    Player p = e.getPlayer();
	    if (fighting.containsKey(p.getName()))
	    {
	      Player t = Bukkit.getServer().getPlayer((String)fighting.get(p.getName()));
	      fighting.remove(t.getName());
	      fighting.remove(p.getName());
	      noExecut.remove(p);
	      noExecut.remove(t);
			ItemStack Item = new ItemStack(Material.IRON_FENCE);
			ItemMeta kItem = Item.getItemMeta();
			kItem.setDisplayName("§e" + KitAPI.getKitName(p));
			Item.setItemMeta(kItem);
			
	      fighting.remove(t.getName());
	      fighting.remove(p.getName());
	      noExecut.remove(p);
	      noExecut.remove(t);
			if(KitAPI.getKitName(p).equalsIgnoreCase(this.getName())){
				p.getInventory().setItem(1, Item);
			}
			if(KitAPI.getKitName(t).equalsIgnoreCase(this.getName())){
				t.getInventory().setItem(1, Item);
			}
	      Location old = (Location)this.oldl.get(t.getName());
	      t.teleport(old);
	      Bukkit.getScheduler().cancelTask(this.id1);
	      Bukkit.getScheduler().cancelTask(this.id2);
	      t.removePotionEffect(PotionEffectType.WITHER);
	      Location loc = (Location)this.localizacao.get(p);
	      List<Location> cuboid = new ArrayList<Location>();
	      int bY;
	      for (int bX = -10; bX <= 10; bX++) {
	        for (int bZ = -10; bZ <= 10; bZ++) {
	          for (bY = -1; bY <= 10; bY++) {
	            if (bY == 10) {
	              cuboid.add(loc.clone().add(bX, bY, bZ));
	            } else if (bY == -1) {
	              cuboid.add(loc.clone().add(bX, bY, bZ));
	            } else if ((bX == -10) || (bZ == -10) || (bX == 10) || (bZ == 10)) {
	              cuboid.add(loc.clone().add(bX, bY, bZ));
	            }
	          }
	        }
	      }
	      for (Location loc1 : cuboid)
	      {
	        loc1.getBlock().setType(Material.AIR);
	        ((Block)this.bloco.get(loc1)).setType(Material.AIR);
	      }
	      for (Location loc1 : cuboid)
	      {
	        loc1.getBlock().setType(Material.AIR);
	        ((Block)this.bloco.get(loc1)).setType(Material.AIR);
	      }
	      for (Location loc1 : cuboid)
	      {
	        loc1.getBlock().setType(Material.AIR);
	        ((Block)this.bloco.get(loc1)).setType(Material.AIR);
	      }
	    }
	  }
	  
	  @SuppressWarnings("static-access")
	@EventHandler(priority=EventPriority.HIGHEST)
	  public void onDeathGladiator(PlayerDeathEvent e)
	  {
	    Player p = e.getEntity();
	    if (fighting.containsKey(p.getName())){
	      Player k = Bukkit.getServer().getPlayer((String)fighting.get(p.getName()));
	      Location old = (Location)this.oldl.get(p.getName());
	      k.teleport(old);
	      Bukkit.getScheduler().cancelTask(this.id1);
	      Bukkit.getScheduler().cancelTask(this.id2);
	      k.removePotionEffect(PotionEffectType.WITHER);
	      k.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 100, 10));
	      
			ItemStack Item = new ItemStack(Material.IRON_FENCE);
			ItemMeta kItem = Item.getItemMeta();
			kItem.setDisplayName("§e" + KitAPI.getKitName(p));
			Item.setItemMeta(kItem);
			
	      fighting.remove(k.getName());
	      fighting.remove(p.getName());
	      noExecut.remove(p);
	      noExecut.remove(k);
			if(KitAPI.getKitName(p).equalsIgnoreCase(this.getName())){
				p.getInventory().setItem(1, Item);
			}
			if(KitAPI.getKitName(k).equalsIgnoreCase(this.getName())){
				k.getInventory().setItem(1, Item);
			}
	      Location loc = (Location)this.localizacao.get(p);
	      List<Location> cuboid = new ArrayList<Location>();
	      cuboid.clear();
	      int bY;
	      for (int bX = -10; bX <= 10; bX++) {
	        for (int bZ = -10; bZ <= 10; bZ++) {
	          for (bY = -1; bY <= 10; bY++) {
	            if (bY == 10) {
	              cuboid.add(loc.clone().add(bX, bY, bZ));
	            } else if (bY == -1) {
	              cuboid.add(loc.clone().add(bX, bY, bZ));
	            } else if ((bX == -10) || (bZ == -10) || (bX == 10) || (bZ == 10)) {
	              cuboid.add(loc.clone().add(bX, bY, bZ));
	            }
	          }
	        }
	      }
	      for (Location loc1 : cuboid)
	      {
	        loc1.getBlock().setType(Material.AIR);
	        if (this.bloco.containsKey(loc1)) {
	          ((Block)this.bloco.get(loc1)).setType(Material.AIR);
	        }
	      }
	      return;
	    }
	  }
	    @EventHandler
	    public void processocommand(PlayerCommandPreprocessEvent e){
	    	Player p = e.getPlayer();
	    	if(noExecut.contains(p)){
	    		e.setCancelled(true);
	    		p.sendMessage(MessageAPI.Command_Error+"Sem comandos em gladiator.");
	    	}
	  }
}
