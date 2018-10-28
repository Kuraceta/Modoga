package kEvents;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerAchievementAwardedEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.inventory.ItemStack;

import api.API;
import api.MessageAPI;
import api.NameTagAPI;
import api.WarpAPI;
import br.alkazuz.groupapi.api.GroupAPI;
import hi.Reaction.BossBar.BossBarAPI;
import kCommandCommands.cChat;
import kCommandCommands.cSc;
import kCommandCommands.cTopKill;
import kConfig.Config;
import kKit.KitAPI;
import kKitKits.Gladiator;
import kKs.KillStreakAPI;
import main.Main;
import mysqlManager.MySQLFunctions;
import mysqlManager.Status;
import protection.Protection;
import score.ScoreBoarding;
import tab.TituloAPI;
import utils.CapsLock;

public class EventListener implements Listener{
	
	@EventHandler
	public void onjoin(PlayerQuitEvent e) {
		e.setQuitMessage(null);
	}
	
	@EventHandler
	  public void doLogin(PlayerJoinEvent e) {
		  if(e.getPlayer().getName().equalsIgnoreCase(cTopKill.Top1)) {
			  Bukkit.broadcastMessage("§6§l[RANK] §e"+e.getPlayer().getName() +" §6é um jogador §6§lTOP1 §6e está conectado no Servidor \\o/");
		  }
		  if(e.getPlayer().getName().equalsIgnoreCase(cTopKill.Top2)) {
			  Bukkit.broadcastMessage("§6§l[RANK] §e"+e.getPlayer().getName() +" §6é um jogador §6§lTOP2 §6e está conectado no Servidor \\o/");
		  }
		  if(e.getPlayer().getName().equalsIgnoreCase(cTopKill.Top3)) {
			  Bukkit.broadcastMessage("§6§l[RANK] §e"+e.getPlayer().getName() +" §6é um jogador §6§lTOP3 §6e está conectado no Servidor \\o/");
		  }
	  }
	
	@EventHandler
	public void onDamage(EntityDamageByEntityEvent e) {
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
	      
	      BossBarAPI.setMessage(hitter, p.getDisplayName()+" §f§l- "+KitAPI.getKitName(p));
	      Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), (Runnable)new Runnable() {
	          @Override
	          public void run() {
	              BossBarAPI.removeBar(hitter);
	          }
	      }, 20L * 3L);
	    }
	}
	
public static ArrayList<Player> delayChat = new ArrayList<Player>();
	
	public static ArrayList<Player> delay = new ArrayList<Player>();
	
	
	@EventHandler
	public void loginEvent(PlayerLoginEvent evento) {
		
		if(evento.getResult() == PlayerLoginEvent.Result.KICK_WHITELIST) {
			evento.setKickMessage("§4§lErro \n§cServidor em §4§lManutenção§c, volte novamente mais Tarde!");
		}
	}
	@EventHandler
	  public void clicarBussola(PlayerInteractEvent e){
		  
		  boolean parar =false;
		  if(delay.contains(e.getPlayer()))return;
		if(e.getPlayer().getItemInHand() !=null && e.getPlayer().getItemInHand().getType() != null && e.getPlayer().getItemInHand().getType() == Material.COMPASS&&e.getPlayer().getItemInHand().getItemMeta().getDisplayName().equals("§eBussola")){
			for(Entity entidades : e.getPlayer().getNearbyEntities(100, 150, 100)){
				
				if((entidades instanceof Player) && e.getPlayer().getLocation().distance(entidades.getLocation()) >= 9){
					Player pl = (Player)entidades;
					if(KitAPI.getKitName(pl).equalsIgnoreCase("Nenhum"))continue;
					if(!API.admin.contains(pl)){
						delay.add(e.getPlayer());
					parar=true;
					e.getPlayer().setCompassTarget(entidades.getLocation());
					e.getPlayer().sendMessage(MessageAPI.Command_Succes + "Bússula apontando para §c" + ((Player)entidades).getName());
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), (Runnable)new Runnable() {
				          @Override
				          public void run() {
				              if(delay.contains(e.getPlayer())){
				              	delay.remove(e.getPlayer());
				              }
				          }
				      }, 20L * 5L);
					return;
					}
				}
		}
                      if(!parar){	
                    	  delay.add(e.getPlayer());
					e.getPlayer().sendMessage(MessageAPI.Command_Error + "Nenhum jogador encontrado.");
					e.getPlayer().setCompassTarget(new Location(e.getPlayer().getWorld(), 0, 100, 0));
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), (Runnable)new Runnable() {
				          @Override
				          public void run() {
				              if(delay.contains(e.getPlayer())){
				              	delay.remove(e.getPlayer());
				              }
				          }
				      }, 20L * 5L);
					return;
			}
		}
 }
	@EventHandler
	public void onWeatherChange(WeatherChangeEvent e)
	  {
		e.setCancelled(true);
	  }
	@EventHandler
  	public void motd(final ServerListPingEvent e){
  		e.setMotd("   §2§lSpectrePvP §7Servidor de KitPvP §f1.7 e 1.8\n     §e§l  NOVIDADE: §bSistema de Clans e Muito mais!");
  		if (Bukkit.hasWhitelist()) {
  			e.setMotd("         §2§lSpectrePvP\n     §cServidor em manutençao!");
  		}
	}
	
	@EventHandler
	public void onExplode(BlockDamageEvent e){
		e.setCancelled(true);
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onJoin(PlayerJoinEvent e){

		Player p = e.getPlayer();
		
		e.setJoinMessage(null);
		
		p.setLevel(0);
		e.getPlayer().setGameMode(GameMode.ADVENTURE);
		if(cSc.staff.contains(e.getPlayer())) {
			cSc.staff.remove(e.getPlayer());
		}
		
		p.setExp(0);
		p.spigot().setCollidesWithEntities(false);
		for(Player player :Bukkit.getOnlinePlayers()){
			if(API.admin.contains(player)){
				p.hidePlayer(player);
			}
		}
		
		boolean sendtitle = true;
		MySQLFunctions.loadPlayer(e.getPlayer());
		Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
			public void run() {
				NameTagAPI.setupTag(p);
			}
		}, 20L);
		
		KillStreakAPI.ks.put(p, 0);
		
		Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
			public void run() {
				API.sendItems(p);
			}
		}, 1L);
		
		WarpAPI.setWarp(p, "Spawn");
		
		Protection.setImortal(p, true);
		p.setFoodLevel(20);
		KitAPI.removeKit(p);
		for (int i = 0; i < 100; i++) {
			p.sendMessage(" ");
		}
		
		p.sendMessage("§2§l➡ §fOlá §e"+p.getName()+" §f!");
		p.sendMessage("§2§l➡ §fSeja muito Bem Vindo(a) ao Servidor §2§lSpectrePvP");
		p.sendMessage("§2§l➡ §fNunca use sua senha em outros Servidores!");
		p.sendMessage("§2§l➡ §fSite do Servidor §4Em Breve");
		p.sendMessage("§2§l➡ §fConvide seu amigos para jogador no Servidor também.");
		p.sendMessage("§2§l➡ §fTenha um bom Jogo :D");
		p.sendMessage(" ");
		if(sendtitle) {
		TituloAPI.mandarTitulo(p, "§2§lSpectrePvP");
		TituloAPI.mandarSubTitulo(p, "§7§oBem Vindo ao Servidor");
		}
		Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
			public void run() {
				ScoreBoarding.Score.add(e.getPlayer());
				ScoreBoarding.setScoreBoard(e.getPlayer());
			}
		}, 30L);
				
	}
	
	@EventHandler
	public void PlayerLogin(PlayerLoginEvent e) {
		if (!MySQLFunctions.VerificarRegistro(e.getPlayer().getUniqueId())) {
			MySQLFunctions.Registro(e.getPlayer().getUniqueId(),e.getPlayer().getName());
		}
		MySQLFunctions.loadPlayer(e.getPlayer());	
	}
	
	@EventHandler
	public void onQuit(PlayerKickEvent e){
		MySQLFunctions.savePlayer(e.getPlayer());
		e.getPlayer().getInventory().clear();
		e.getPlayer().getInventory().setArmorContents(null);
	}
	
	
	
	@EventHandler
	void BuildConstruct(BlockPlaceEvent evento) {
		Player jogador = evento.getPlayer();
		if(Gladiator.noExecut.contains(jogador) && (KitAPI.getKitPlayer(jogador) != null && KitAPI.getKitPlayer(jogador).getName().equalsIgnoreCase("gladiator"))) {
			jogador.sendMessage(" t");
		    evento.setCancelled(false);
		}
		if (jogador.getGameMode() != GameMode.CREATIVE || (KitAPI.getKitPlayer(jogador) != null && KitAPI.getKitPlayer(jogador).getName().equalsIgnoreCase("gladiator"))) {
			evento.setCancelled(true);
			return;
		}
	}
	@EventHandler
	void BuildBreak(BlockBreakEvent e) {
		
		Player jogador = e.getPlayer();
		if (jogador.getGameMode() != GameMode.CREATIVE) {
			e.setCancelled(true);
		}
		
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e){
		e.setQuitMessage(null);
		MySQLFunctions.savePlayer(e.getPlayer());
		e.getPlayer().getInventory().clear();
		e.getPlayer().getInventory().setArmorContents(null);
	}
	
	@EventHandler
	public void death(PlayerDeathEvent e){
		e.setDeathMessage(null);
		
        Player p = e.getEntity();	
        if(WarpAPI.getWarp(p).toLowerCase().contains("rdm"))return;
        KitAPI.removeKit(p);
		if(e.getEntity().getKiller() instanceof Player){
			Player d = e.getEntity().getKiller();
			if(d == p) {
				Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
					public void run() {
						p.spigot().respawn();
						WarpAPI.setWarp(p, "Spawn");
					}
				}, 6L);
				
				return;
			}
			e.getDrops().clear();
			d.sendMessage("§c§lKILL §fVocê matou o jogador §e"+p.getName());
			d.sendMessage("§a+15 §fde Money");
			Status.addCoins(d, 15);
			Status.addKills(d, 1);
			p.sendMessage("§c§lMORTE §fVocê foi morto por §e"+d.getName());
			p.sendMessage("§c-6 §fde Money");
			Status.removeCoins(p, 6);
			Status.addDeath(p, 1);
			MySQLFunctions.savePlayer(p);
			KillStreakAPI.addKS(d);
			KillStreakAPI.resetKS(p);
			if(WarpAPI.getWarp(p).equalsIgnoreCase("Lava")) {
				Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
					public void run() {
						Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
							public void run() {
								p.spigot().respawn();
								WarpAPI.setWarp(p, "Lava");
							}
						}, 20L);
						p.getInventory().clear();
						Protection.setImortal(p, false);
						ItemStack sopa = new ItemStack(Material.MUSHROOM_SOUP);
						for(int i = 0; i < 36; i++){
					     	   p.getInventory().setItem(i, sopa);
					     	   p.updateInventory();
							}
							p.getInventory().setItem(13, API.createItem(p, Material.RED_MUSHROOM, "§cCogumelo Vermelho", new String[] {""}, 64, (short)0));
							p.getInventory().setItem(14, API.createItem(p, Material.BROWN_MUSHROOM, "§eCogumelo Marrom", new String[] {""}, 64, (short)0));
							p.getInventory().setItem(15, API.createItem(p, Material.BOWL, "§7Tigela", new String[] {""}, 64, (short)0));
								ScoreBoarding.setScoreBoard(p);
								p.updateInventory();
								
					}
				}, 10);
				return;
			}
		Protection.setImortal(p, true);
		
		Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
			public void run() {
				p.spigot().respawn();
				if(CombatLog.Sair.contains(p.getName())){
					CombatLog.Sair.remove(p.getName());
				}
				WarpAPI.setWarp(p, "Spawn");
			}
		}, 6L);
		ScoreBoarding.setScoreBoard(p);
		ScoreBoarding.setScoreBoard(d);
		if(KillStreakAPI.getKS(d) == 5){
			Bukkit.broadcastMessage("§4§lKS §c"+d.getName()+" §fconseguiu um §cKillStreak §fde §e"+KillStreakAPI.getKS(d));
		}
		if(KillStreakAPI.getKS(d) == 10){
			Bukkit.broadcastMessage("§4§lKS §c"+d.getName()+" §fconseguiu um §cKillStreak §fde §e"+KillStreakAPI.getKS(d));
		}
		if(KillStreakAPI.getKS(d) == 15){
			Bukkit.broadcastMessage("§4§lKS §c"+d.getName()+" §fconseguiu um §cKillStreak §fde §e"+KillStreakAPI.getKS(d));
		}
		if(KillStreakAPI.getKS(d) == 20){
			Bukkit.broadcastMessage("§4§lKS §c"+d.getName()+" §fconseguiu um §cKillStreak §fde §e"+KillStreakAPI.getKS(d));
		}
		if(KillStreakAPI.getKS(d) == 25){
			Bukkit.broadcastMessage("§4§lKS §c"+d.getName()+" §fconseguiu um §cKillStreak §fde §e"+KillStreakAPI.getKS(d));
		}
		if(KillStreakAPI.getKS(d) == 30){
			Bukkit.broadcastMessage("§4§lKS §c"+d.getName()+" §fconseguiu um §cKillStreak §fde §e"+KillStreakAPI.getKS(d));
		}
		if(KillStreakAPI.getKS(d) == 35){
			Bukkit.broadcastMessage("§4§lKS §c"+d.getName()+" §fconseguiu um §cKillStreak §fde §e"+KillStreakAPI.getKS(d));
		}
		if(KillStreakAPI.getKS(d) == 40){
			Bukkit.broadcastMessage("§4§lKS §c"+d.getName()+" §fconseguiu um §cKillStreak §fde §e"+KillStreakAPI.getKS(d));
		}
		if(KillStreakAPI.getKS(d) == 45){
			Bukkit.broadcastMessage("§4§lKS §c"+d.getName()+" §fconseguiu um §cKillStreak §fde §e"+KillStreakAPI.getKS(d));
		}
		if(KillStreakAPI.getKS(d) == 50){
			Bukkit.broadcastMessage("§4§lKS §c"+d.getName()+" §fconseguiu um §cKillStreak §fde §e"+KillStreakAPI.getKS(d));
		}
		if(KillStreakAPI.getKS(d) == 55){
			Bukkit.broadcastMessage("§4§lKS §c"+d.getName()+" §fconseguiu um §cKillStreak §fde §e"+KillStreakAPI.getKS(d));
		}
		
		Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
			public void run() {
				API.sendItems(p);
			}
		}, 10L);
		}else{
			if(WarpAPI.getWarp(p).equalsIgnoreCase("fps")) {
				Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
					public void run() {
						p.spigot().respawn();
						WarpAPI.setWarp(p, "Spawn");
					}
				}, 6L);
				
				return;
			}
			Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
				public void run() {
					p.spigot().respawn();
					WarpAPI.setWarp(p, "Spawn");
					if(CombatLog.Sair.contains(p.getName())){
						CombatLog.Sair.remove(p.getName());
					}
				}
			}, 6L);
			Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
				public void run() {
					API.sendItems(p);
				}
			}, 10L);
		}
	}
	
	@EventHandler
    public void Damage(final EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Player && e.getDamager() instanceof Player) {
            final Player p = (Player)e.getDamager();
            if(p.getItemInHand() != null && p.getItemInHand().getType() != null ) {
            if(p.getItemInHand().getType() == Material.STONE_SWORD||p.getItemInHand().getType() == Material.IRON_SWORD||p.getItemInHand().getType() == Material.DIAMOND_SWORD||p.getItemInHand().getType() == Material.WOOD_SWORD){
            	e.setDamage(e.getDamage() / 2);
            }
            }
        }
    }
	
	@SuppressWarnings("deprecation")
	@EventHandler
    public void onSoup(PlayerInteractEvent e)
    {
      Player p = e.getPlayer();
      Damageable hp = p;
      if (hp.getHealth() != 20.0D)
      {
        int sopa = 7;
        if (((e.getAction() == Action.RIGHT_CLICK_AIR) || (e.getAction() == Action.RIGHT_CLICK_BLOCK)) && (p.getItemInHand().getTypeId() == 282))
        {
          p.setHealth(hp.getHealth() + sopa > hp.getMaxHealth() ? hp.getMaxHealth() : hp.getHealth() + sopa);
          p.getItemInHand().setType(Material.BOWL);
          if(KitAPI.getKitName(e.getPlayer()).equalsIgnoreCase("QuickDrop")){
        	  p.getInventory().removeItem(p.getInventory().getItemInHand());
          }
        }
      }
    }
	
	@EventHandler
    public void nerfArmor(EntityDamageEvent e) {
     if ((e.getEntity() instanceof Player)) {
      Player p = (Player)e.getEntity();
            if (p.getInventory().getChestplate() != null) {
              p.getInventory().getChestplate().setDurability((short)0);
            }
            if (p.getInventory().getBoots() != null) {
             p.getInventory().getBoots().setDurability((short)0);
            }
            if (p.getInventory().getLeggings() != null) {
             p.getInventory().getLeggings().setDurability((short)0);
            }
            if (p.getInventory().getHelmet() != null) {
             p.getInventory().getHelmet().setDurability((short)0);
            }
        }
    }
	
	@EventHandler
	public void onDrop(PlayerDropItemEvent e){
	       if((e.getItemDrop().getItemStack().getType() != Material.MUSHROOM_SOUP) && (e.getItemDrop().getItemStack().getType() != Material.BOWL) && (e.getItemDrop().getItemStack().getType() != Material.ENDER_PEARL) && (e.getItemDrop().getItemStack().getType() != Material.RED_MUSHROOM) && (e.getItemDrop().getItemStack().getType() != Material.BROWN_MUSHROOM)
	    		   && (e.getItemDrop().getItemStack().getType() != Material.GLASS_BOTTLE)){
	    	   e.setCancelled(true);
		}
	}
	
	@EventHandler
    public void fome(FoodLevelChangeEvent e){
 	   e.setCancelled(true);
    }
    @EventHandler
    public void achivments(PlayerAchievementAwardedEvent e){
 	   e.setCancelled(true);
    }
    
    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent e)
    {
      if ((e.getEntity() instanceof Player) &&e.getDamager() instanceof Player)
      {
        Player d = (Player)e.getDamager();
        if(d.getItemInHand() != null) {
        if ((d.getItemInHand().getType() == Material.DIAMOND_SWORD) || 
          (d.getItemInHand().getType() == Material.STONE_SWORD) || 
          (d.getItemInHand().getType() == Material.WOOD_SWORD) || 
          (d.getItemInHand().getType() == Material.STONE_SWORD) || 
          (d.getItemInHand().getType() == Material.IRON_SWORD) || 
          (d.getItemInHand().getType() == Material.GOLD_SWORD) || 
          (d.getItemInHand().getType() == Material.FISHING_ROD) || 
          (d.getItemInHand().getType() == Material.DIAMOND_AXE) || 
          (d.getItemInHand().getType() == Material.GOLD_AXE) || 
          (d.getItemInHand().getType() == Material.STONE_AXE) || 
          (d.getItemInHand().getType() == Material.WOOD_AXE) || 
          (d.getItemInHand().getType() == Material.IRON_AXE)) {
          d.getItemInHand().setDurability((short)0);
          d.updateInventory();
        }
        }
      }
    }
    @EventHandler
    public void noBreaking(PlayerInteractEvent e){
  	  
  	  Player p = e.getPlayer();
  	  if(p.getItemInHand().getType() == Material.FISHING_ROD){
  		  if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK){
  			  p.getItemInHand().setDurability((short)0);
  			  p.updateInventory();
  		  }
  	  }
    }
    
    public String[] comandos = new String[] {"/hs","/sab","/ne","/pex","/promote","/demote","/skin","/skinreset","/setskin","/backup"};
    
    public boolean isinList(String i) {
    	for(String cmd : comandos) {
    		if((cmd).equalsIgnoreCase(i)) {
    			return true;
    		}
    	}
    	return false;
    }
    
    public String[] comdsbukkit = new String[] {"/plugins","/pl","/ver","/help","/?","/version","/me"};
    @EventHandler(ignoreCancelled=true)
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
    	Player p = event.getPlayer();
    	String msg = event.getMessage();
    	for(String cmds : comdsbukkit) {if(msg.toLowerCase().startsWith(cmds)) { p.sendMessage(MessageAPI.Command_Error+"Comando inexistente ou você não tem acesso");event.setCancelled(true); return;}}
    	String cmd = msg.split(" ")[0];
    	if(!GroupAPI.GroupCanExecute(GroupAPI.getGroup(p), cmd)&&isinList(cmd)) {
    		p.sendMessage(MessageAPI.Command_Error+"Comando inexistente ou você não tem acesso");
    		event.setCancelled(true);
    	}
    }
    
    public String[] hosts = new String[] {".com",".br",".net","desire.host","virtus.host",".tk",".ml",".cl"};
    @EventHandler
    public void Chat(AsyncPlayerChatEvent e){
    	Player p = e.getPlayer();
    	if(e.isCancelled())return;
    	if(cSc.staff.contains(p))return;
    	if(cChat.pausado&& GroupAPI.getGroup(p).equalsIgnoreCase("Membro")) {e.setCancelled(true);return;}
    	if(delayChat.contains(e.getPlayer())){
			e.getPlayer().sendMessage("§cVocê está falando muito rápido!");
			e.setCancelled(true);
			return;
		}
		if(!p.hasPermission("kitpvp.admin")){
		delayChat.add(e.getPlayer());
		}
	
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), (Runnable)new Runnable() {
          @Override
          public void run() {
              if(delayChat.contains(p)){
              	delayChat.remove(p);
              }
          }
      }, 20L * 3L);
    	String msg = e.getMessage().replace("%", "%%");
    	String msg2 = "";
    	for(int i = 0; i<msg.split(" ").length;i++) {
    		if(Config.getConfig().getBoolean("Grupo."+GroupAPI.getGroup(p)+".ChatColorido")) {
    		msg2 += msg.split(" ")[i]+" ";
    		}else{
    			msg2 += "§7"+msg.split(" ")[i]+" ";
    		}
    	}
    	msg = msg2;
    	for(String host : hosts) {
    		if(e.getPlayer().hasPermission("kitpvp.admin"))break;
    		if(msg.toLowerCase().contains(host)||msg.toLowerCase().contains(host.replace(".", ","))) {
    			e.getPlayer().sendMessage(MessageAPI.Command_Error+"Proibido divulgar IPs");
    			e.setCancelled(true);
    			return;
    		}
    	}
    	if(Config.getConfig().getBoolean("Grupo."+GroupAPI.getGroup(p)+".ChatColorido")) {
    		msg = "§f"+msg.replace("&", "§").replace("§l", "").replace("§L", "").replace("§0", "").replace("§m", "").replace("§M", "").replace("§K", "").replace("§k", "").replace("§o", "§o").replace("§O", "");
    	}
    	if(!Config.getConfig().getBoolean("Grupo."+GroupAPI.getGroup(p)+".ChatColorido")) {
    		msg = CapsLock.getFixed(msg);
    	}
    	e.setFormat(e.getPlayer().getDisplayName()+" §e» §7"+msg);
    }
    
    @EventHandler
	  public void onDrop(ItemSpawnEvent e){
		  if(e.getEntity().getItemStack().getType() == Material.BOWL){
			  e.getEntity().remove();
			  return;
		  }
		  Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
			public void run() {
				e.getEntity().remove();
			}
		}, 2 * 20);
	  }
	
}
