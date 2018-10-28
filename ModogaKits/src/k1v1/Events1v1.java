 package k1v1;
 import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;

import api.MessageAPI;
import api.WarpAPI;
import main.Main;
import protection.Protection;
import score.ScoreBoarding;
import tab.TituloAPI;

@SuppressWarnings("deprecation")
public class Events1v1 implements Listener {
public static List<Player> ChamouDuelo = new ArrayList<Player>();
  public static List<Player> AceitarDuelo = new ArrayList<Player>();
  public static List<Player> Jogando1v1Player = new ArrayList<Player>();
  public static List<Player> congelar = new ArrayList<Player>();
  public static List<Player> inPvP = new ArrayList<Player>();
  public static HashMap<String, String> JogandoSair = new HashMap<String, String>();

  @EventHandler
   public void PlayerInteractEntityEvent4(PlayerInteractEntityEvent event) { 
	 final Player p = event.getPlayer();

     if (!(event.getRightClicked() instanceof Player)) {
       return;
    }
     if (p.getItemInHand().equals(Join1v1.Item_))
     {
       if (ChamouDuelo.contains(p))
       {
         p.sendMessage(MessageAPI.Command_Error+"Aguarde para convidar outra pessoa.");
         return;
      }
       final Player Player2 = (Player)event.getRightClicked();
 
       AceitarDuelo.add(Player2);
       ChamouDuelo.add(p);
       p.sendMessage(MessageAPI.Command_Succes+"Você convidou o jogador §c" + Player2.getName() + " §apara 1v1");
       TituloAPI.mandarfull(Player2, "§6§l1v1", "§e"+p.getName()+" §7te desafiou.");
      Player2.sendMessage(MessageAPI.Command_Succes+"§aVocê foi convidado por §c" + p.getName() + " §apara 1v1");
       Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable()
      {
        public void run()
       {
          if (ChamouDuelo.contains(p))
         {
        ChamouDuelo.remove(p);
          AceitarDuelo.remove(Player2);
          }
        }
      }
      , 100L);
     }
   }

  @EventHandler
   public void onPlayerMove(PlayerMoveEvent event)
   {
     final Player player = event.getPlayer();
     if (congelar.contains(player))
    {
      event.setTo(player.getLocation());
      Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable()
     {
       public void run()
        {
        congelar.remove(player);
        }
      }
      , 60L);
    }
  }


 @EventHandler
   public void PlayerInteractEntityEvent3(PlayerInteractEntityEvent event)
  {
     final Player p = event.getPlayer();

    if (!(event.getRightClicked() instanceof Player)) {
      return;
     }
     if (AceitarDuelo.contains(p))
    {
     if (p.getItemInHand().equals(Join1v1.Item_))
       {
       final Player Player2 = (Player)event.getRightClicked();
        if (ChamouDuelo.contains(Player2))
       {
           Jogando1v1Player.add(p);
          Jogando1v1Player.add(Player2);
         JogandoSair.put(p.getName(), Player2.getName());
          JogandoSair.put(Player2.getName(), p.getName());
         congelar.add(p);
          congelar.add(Player2);

					Protection.setImortal(Player2, false);
                    Protection.setImortal(p, false);

        	WarpAPI.setWarp(Player2, "1v1Loc1");
					WarpAPI.setWarp(p, "1v1Loc2");
					
         ChamouDuelo.remove(Player2);
           AceitarDuelo.remove(Player2);
          ChamouDuelo.remove(p);
         AceitarDuelo.remove(p);
         
         inPvP.add(p);
         inPvP.add(Player2);
         
         p.getInventory().clear();
         Player2.getInventory().clear();
          ItemStack Espada = new ItemStack(Material.DIAMOND_SWORD);
         Espada.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 1);
          ItemMeta Espada2 = Espada.getItemMeta();
          Espada2.setDisplayName("§eEspada");
          Espada.setItemMeta(Espada2);
          p.getInventory().addItem(new ItemStack[] { Espada });
           Player2.getInventory().addItem(new ItemStack[] { Espada });

           Player2.getInventory().setHelmet(new ItemStack(Material.IRON_HELMET));
          Player2.getInventory().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
          Player2.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
           Player2.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS));

          p.getInventory().setHelmet(new ItemStack(Material.IRON_HELMET));
          p.getInventory().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
        p.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
         p.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS));
 
         
          for (int i = 1; i < 9; i++) {
            ItemStack Fisherman = new ItemStack(Material.MUSHROOM_SOUP);
           p.getInventory().addItem(new ItemStack[] { Fisherman });
             Player2.getInventory().addItem(new ItemStack[] { Fisherman });
            Player2.updateInventory();
            p.updateInventory();
             Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
            		public void run() {
            	        for (Player plr : Bukkit.getOnlinePlayers()) {
            	        Player2.hidePlayer(plr);
            	   p.hidePlayer(plr);
            	         p.showPlayer(Player2);
            	        Player2.showPlayer(p);
            	        }
            		}
            	},2L);		
           Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable()
             {
           public void run()
                {
              if (congelar.contains(p))
                 {
                    p.setMaxHealth(20);
                    p.setHealth(20);
                    Player2.setMaxHealth(20);
                     Player2.setHealth(20);
                 }
               }
            }
               , 20L);
               Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable()
               {
                public void run()
                {
                  if (congelar.contains(p))
                  {
               p.setMaxHealth(20);
               p.setHealth(20);
               Player2.setMaxHealth(20);
                Player2.setHealth(20);
                  }
               }
             }
             , 40L);
           Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable()
             {
                 public void run()
                 {
                  if (congelar.contains(p))
                   {
                     p.setMaxHealth(20);
                    p.setHealth(20);
                    Player2.setMaxHealth(20);
                     Player2.setHealth(20);
                     congelar.remove(p);
                  }
               }
              }
              , 60L);
          }
           }
       }
   }
  }

  
  @EventHandler
   public void Morrer2(PlayerDeathEvent e) { Player v = e.getEntity();
    e.setDeathMessage("");
   if ((e.getEntity().getKiller() instanceof Player))
    {
     Player m = e.getEntity().getKiller();
      if (m != v)
      {
     if ((Jogando1v1Player.contains(m)) || (Jogando1v1Player.contains(v)))
        {
        int amountv = itemsInInventory(v.getInventory(), new Material[] { Material.MUSHROOM_SOUP });
         int amountm = itemsInInventory(m.getInventory(), new Material[] { Material.MUSHROOM_SOUP });

         inPvP.remove(m);
         inPvP.remove(v);
         
				m.sendMessage("     ");
				m.sendMessage("§7Você ganhou de jogador §c" + v.getName() + " §7e ele ficou com §c" + amountv + " §7sopas.");
					Protection.setImortal(v, true);
					Protection.setImortal(m, true);
					
				v.sendMessage("     ");
				v.sendMessage("§7Você perdeu para o jogador §c" + m.getName() + " §7e ele ficou com §c" + amountm + " §7sopas.");
				for(Player player :Bukkit.getOnlinePlayers()){
					if(api.API.admin.contains(player)){
						m.hidePlayer(player);
						v.hidePlayer(player);
					}
				}
				
				
				WarpAPI.setWarp(m, "1v1");
				WarpAPI.setWarp(v, "1v1");
				ScoreBoarding.setScoreBoard(m);
				ScoreBoarding.setScoreBoard(v);
				m.setGameMode(GameMode.SURVIVAL);
				m.getInventory().setArmorContents(null);
				m.getInventory().clear();

				for (PotionEffect effect : m.getActivePotionEffects()) {
					m.removePotionEffect(effect.getType());
				}
				Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
					public void run() {
						WarpAPI.setWarp(v, "1v1");
						v.setGameMode(GameMode.SURVIVAL);
						v.getInventory().setArmorContents(null);
						v.getInventory().clear();
						ScoreBoarding.setScoreBoard(m);
						ScoreBoarding.setScoreBoard(v);
						for (PotionEffect effect : v.getActivePotionEffects()) {
							v.removePotionEffect(effect.getType());
						}
					}
				}, 1L);
						ScoreBoarding.setScoreBoard(v);
						ScoreBoarding.setScoreBoard(m);
           Jogando1v1Player.remove(v);
           Jogando1v1Player.remove(m);
           JogandoSair.remove(m.getName());
           JogandoSair.remove(v.getName());
           
           Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
			public void run() {
		           v.getInventory().setItem(4, Join1v1.Item_);
		           m.getInventory().setItem(4, Join1v1.Item_);
		           m.updateInventory();
		           v.updateInventory();
		       	WarpAPI.setWarp(v, "1v1");
   		       	WarpAPI.setWarp(m, "1v1");
   		     ScoreBoarding.setScoreBoard(m);
				ScoreBoarding.setScoreBoard(v);
			}
		},2L);

          m.setHealth(20);
          v.setHealth(20);
           for (Player plr : Bukkit.getOnlinePlayers())
          {
            v.showPlayer(plr);
            m.showPlayer(plr);
           }
         } else{
          Jogando1v1Player.remove(v);
          Jogando1v1Player.remove(m);
           return;
         }
       }
    }
   } 
   
@EventHandler
   public void Morrer3(PlayerDeathEvent e)   {
     Player v = e.getEntity();
    if ((e.getEntity().getKiller() instanceof Player))    {
       Player m = e.getEntity().getKiller();
      if (m != v)
     {
        if ((Jogando1v1Player.contains(v)) || (Jogando1v1Player.contains(m)))
        {
          int amountv = itemsInInventory(v.getInventory(), new Material[] { Material.MUSHROOM_SOUP });
         int amountm = itemsInInventory(m.getInventory(), new Material[] { Material.MUSHROOM_SOUP });
 
         m.sendMessage("     ");
			m.sendMessage("§7Você ganhou de jogador §c" + v.getName() + " §7e ele ficou com §c" + amountv + " §7sopas.");

	         inPvP.remove(m);
	         inPvP.remove(v);
				Protection.setImortal(v, true);
				Protection.setImortal(m, true);
				
			v.sendMessage("     ");
			v.sendMessage("§7Você perdeu para o jogador §c" + m.getName() + " §7e ele ficou com §c" + amountm + " §7sopas.");
			for(Player player :Bukkit.getOnlinePlayers()){
				if(api.API.admin.contains(player)){
					m.hidePlayer(player);
					v.hidePlayer(player);
				}
			}
			
			ScoreBoarding.setScoreBoard(m);
			ScoreBoarding.setScoreBoard(v);
			WarpAPI.setWarp(m, "1v1");
			WarpAPI.setWarp(v, "1v1");
			m.setGameMode(GameMode.SURVIVAL);
			m.getInventory().setArmorContents(null);
			m.getInventory().clear();
			ScoreBoarding.setScoreBoard(m);
			ScoreBoarding.setScoreBoard(v);
			for (PotionEffect effect : m.getActivePotionEffects()) {
				m.removePotionEffect(effect.getType());
			}
			Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
				public void run() {
					WarpAPI.setWarp(v, "1v1");
					v.setGameMode(GameMode.SURVIVAL);
					v.getInventory().setArmorContents(null);
					v.getInventory().clear();
					ScoreBoarding.setScoreBoard(m);
					ScoreBoarding.setScoreBoard(v);
					for (PotionEffect effect : v.getActivePotionEffects()) {
						v.removePotionEffect(effect.getType());
					}
				}
			}, 1L);
			Jogando1v1Player.remove(v);
	           Jogando1v1Player.remove(m);
	           JogandoSair.remove(m.getName());
	           JogandoSair.remove(v.getName());
	           Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
	   			public void run() {
	   		           v.getInventory().setItem(4, Join1v1.Item_);
	   		           m.getInventory().setItem(4, Join1v1.Item_);
	   		           m.updateInventory();
	   		           v.updateInventory();
	   		       	WarpAPI.setWarp(v, "1v1");
	      		       	WarpAPI.setWarp(m, "1v1");
	      		      ScoreBoarding.setScoreBoard(m);
	  				ScoreBoarding.setScoreBoard(v);
	   			}
	   		},2L);

	          m.setHealth(20);
	          v.setHealth(20);
	           for (Player plr : Bukkit.getOnlinePlayers())
	          {
	            v.showPlayer(plr);
	            m.showPlayer(plr);
	           }
        } else{
            Jogando1v1Player.remove(v);
            Jogando1v1Player.remove(m);
             return;
        }
      }
   }
  } 
   
@EventHandler(priority=EventPriority.HIGHEST)
   public void onPlayerLeft(PlayerQuitEvent e){
     Player p = e.getPlayer();
     if (JogandoSair.containsKey(p.getName())){
      Player t = Bukkit.getServer().getPlayer((String)JogandoSair.get(p.getName()));
      
      WarpAPI.setWarp(t, "1v1");
      JogandoSair.remove(t.getName());
       JogandoSair.remove(p.getName());
      Jogando1v1Player.remove(p);
       Jogando1v1Player.remove(t);
       inPvP.remove(p);
       inPvP.remove(t);
       t.setMaxHealth(20.0D);
       t.setHealth(20.0D);
      Protection.setImortal(t, true);
       t.setGameMode(GameMode.SURVIVAL);
       t.getInventory().setArmorContents(null);
       t.getInventory().clear();
       for (PotionEffect effect : t.getActivePotionEffects()) {
         t.removePotionEffect(effect.getType());
       }
       t.sendMessage("§c§l1v1: §fO jogador §c" + p.getName() + " §7deslogou no 1v1.");
 
      t.getInventory().setItem(4, Join1v1.Item_);
      t.updateInventory();
      for (Player plr : Bukkit.getOnlinePlayers())
      {
         t.showPlayer(plr);
        p.showPlayer(plr);
       }
     }
   }

   @EventHandler
   public void cmd(PlayerCommandPreprocessEvent event) {
     Player p = event.getPlayer();
     if ((WarpAPI.getWarp(p) == "1v1") && 
       (!event.getMessage().startsWith("/spawn"))) {
      event.getPlayer().sendMessage(MessageAPI.Command_Error+"Você não pode executar comandos no 1v1, digite §4/spawn");
       event.setCancelled(true);
     }
   } 
   @EventHandler
   public void cmda(PlayerCommandPreprocessEvent event) {
    Player p = event.getPlayer();
     if (Jogando1v1Player.contains(p)){
      event.getPlayer().sendMessage("§7Sem comandos em batalha.");
       event.setCancelled(true);
     }
  }

public int itemsInInventory(Inventory inventory, Material[] search) {
     @SuppressWarnings("rawtypes")
	List wanted = Arrays.asList(search);
    int found = 0;
    ItemStack[] arrayOfItemStack;
     int j = (arrayOfItemStack = inventory.getContents()).length;
    for (int i = 0; i < j; i++)
     {
     ItemStack item = arrayOfItemStack[i];
     if ((item != null) && (wanted.contains(item.getType()))) {
        found += item.getAmount();
      }
     }
    return found;
  }
   
@EventHandler
   public void PlayerInteractEntityEvent2(PlayerJoinEvent e) {
    Player p = e.getPlayer();
     for (Player pl : Bukkit.getOnlinePlayers())
      if (Jogando1v1Player.contains(pl))
      {
        pl.hidePlayer(p);
     }
   }
 }
