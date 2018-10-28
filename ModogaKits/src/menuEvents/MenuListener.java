package menuEvents;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffectType;

import api.API;
import api.MessageAPI;
import api.VIPAPI;
import api.WarpAPI;
import br.alkazuz.groupapi.api.GroupAPI;
import kConquista.Conquista;
import kConquista.ConquistaAPI;
import kConquista.ConquistaManager;
import kKit.Kit;
import kKit.KitAPI;
import kKit.KitManager;
import kKs.KillStreakAPI;
import main.Main;
import menu.CaixaMenu;
import menu.KitDiarioMenu;
import menu.KitMenu;
import menu.LojaKit;
import menu.LojaMenu;
import menu.MenusAPI;
import menu.WarpsMenu;
import menuCash.KitMenuCash;
import menuCash.VipMenuCash;
import mlg.MLGAPI;
import mysqlManager.Status;
import parkour.ParkourAPI;
import protection.Protection;
import score.ScoreBoarding;

public class MenuListener implements Listener{
	
	public static ArrayList<Player> delay = new ArrayList<Player>();
	Random rand = new Random();
	
	@EventHandler
	public void abrirMenuKits(PlayerInteractEvent e){
		Player p = e.getPlayer();
		if(e.getItem()==null||e.getPlayer() == null || e.getItem().getItemMeta() == null)return;
		if(delay.contains(p)){
			e.setCancelled(true);
			return;
		}
		if(WarpAPI.getWarp(p).equalsIgnoreCase("Spawn")) {
			delay.add(p);
		}
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), (Runnable)new Runnable() {
	          @Override
	          public void run() {
	              if(delay.contains(p)){
	            	  delay.remove(p);
	              }
	          }
	      }, 20L * 1L);
		if(p.getItemInHand().getType() == Material.ENCHANTED_BOOK && WarpAPI.getWarp(p).equalsIgnoreCase("Spawn") && e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK && p.getItemInHand().getType() == Material.ENCHANTED_BOOK && WarpAPI.getWarp(p).equalsIgnoreCase("Spawn")){
			WarpsMenu.inventory(p);
			e.setCancelled(true);
		}
		
		
		if(p.getItemInHand().getType() == Material.EXP_BOTTLE && WarpAPI.getWarp(p).equalsIgnoreCase("Spawn") && e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK && p.getItemInHand().getType() == Material.EXP_BOTTLE && WarpAPI.getWarp(p).equalsIgnoreCase("Spawn")){
			MenusAPI.rankMenu(p);
			e.setCancelled(true);
		}
		
		if(p.getItemInHand().getType() == Material.CHEST && WarpAPI.getWarp(p).equalsIgnoreCase("Spawn") && e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK && p.getItemInHand().getType() == Material.CHEST && WarpAPI.getWarp(p).equalsIgnoreCase("Spawn")){
			KitMenu.OpenInventory(p);
			p.playSound(p.getLocation(), Sound.CHEST_OPEN, 20L, 20L);
			e.setCancelled(true);
		}
		if(p.getItemInHand().getType() == Material.EMERALD && WarpAPI.getWarp(p).equalsIgnoreCase("Spawn") && e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK && p.getItemInHand().getType() == Material.EMERALD && WarpAPI.getWarp(p).equalsIgnoreCase("Spawn")){
			LojaMenu.OpenInventoryMain(p);
			p.playSound(p.getLocation(), Sound.CHEST_OPEN, 20L, 20L);
			e.setCancelled(true);
		}
		if(p.getItemInHand().getType() == Material.ENDER_CHEST && WarpAPI.getWarp(p).equalsIgnoreCase("Spawn") && e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK && p.getItemInHand().getType() == Material.ENDER_CHEST && WarpAPI.getWarp(p).equalsIgnoreCase("Spawn")){
			new KitDiarioMenu(p).newScroll();
			p.playSound(p.getLocation(), Sound.LEVEL_UP, 20L, 20L);
			e.setCancelled(true);
		}
		if(p.getItemInHand().getType() == Material.SKULL_ITEM && WarpAPI.getWarp(p).equalsIgnoreCase("Spawn") && e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK && p.getItemInHand().getType() == Material.SKULL_ITEM && WarpAPI.getWarp(p).equalsIgnoreCase("Spawn")){
			MenusAPI.openInfo(p);
			e.setCancelled(true);
		}
	}
	
	public static void runFogos(final Location loc) {
        final Firework fw = (Firework)loc.getWorld().spawnEntity(loc, EntityType.FIREWORK);
        final FireworkMeta fwm = fw.getFireworkMeta();
        final Random r = new Random();
        final int rt = r.nextInt(4) + 1;
        FireworkEffect.Type type = FireworkEffect.Type.BALL;
        if (rt == 1) {
            type = FireworkEffect.Type.BALL;
        }
        if (rt == 2) {
            type = FireworkEffect.Type.BALL_LARGE;
        }
        if (rt == 3) {
            type = FireworkEffect.Type.BURST;
        }
        if (rt == 4) {
            type = FireworkEffect.Type.CREEPER;
        }
        if (rt == 5) {
            type = FireworkEffect.Type.STAR;
        }
        final int r1i = r.nextInt(17) + 1;
        final int r2i = r.nextInt(17) + 1;
        final Color c1 = getColor(r1i);
        final Color c2 = getColor(r2i);
        final FireworkEffect effect = FireworkEffect.builder().flicker(r.nextBoolean()).withColor(c1).withFade(c2).with(type).trail(r.nextBoolean()).build();
        fwm.addEffect(effect);
        fwm.setPower(0);
        fw.setFireworkMeta(fwm);
    }
    
    private static Color getColor(final int i) {
        Color c = null;
        if (i == 1) {
            c = Color.AQUA;
        }
        if (i == 2) {
            c = Color.BLACK;
        }
        if (i == 3) {
            c = Color.BLUE;
        }
        if (i == 4) {
            c = Color.FUCHSIA;
        }
        if (i == 5) {
            c = Color.GRAY;
        }
        if (i == 6) {
            c = Color.GREEN;
        }
        if (i == 7) {
            c = Color.LIME;
        }
        if (i == 8) {
            c = Color.MAROON;
        }
        if (i == 9) {
            c = Color.NAVY;
        }
        if (i == 10) {
            c = Color.OLIVE;
        }
        if (i == 11) {
            c = Color.ORANGE;
        }
        if (i == 12) {
            c = Color.PURPLE;
        }
        if (i == 13) {
            c = Color.RED;
        }
        if (i == 14) {
            c = Color.SILVER;
        }
        if (i == 15) {
            c = Color.TEAL;
        }
        if (i == 16) {
            c = Color.WHITE;
        }
        if (i == 17) {
            c = Color.YELLOW;
        }
        return c;
    }
	
	@EventHandler
	public void onClick(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		if(e.getCurrentItem() == null || e.getCurrentItem().getItemMeta() == null)return;
		
		if(WarpAPI.getWarp(p).equalsIgnoreCase("Spawn")&&e.getInventory().getTitle().equalsIgnoreCase("§aInformações")){
			e.setCancelled(true);
		}
		
		if(WarpAPI.getWarp(p).equalsIgnoreCase("Spawn")&&e.getInventory().getTitle().equals("Warps")){
			p.getInventory().clear();
			if(e.getSlot() == 15) {
				p.sendMessage("§aTeleportando para Warp MLG...");
				p.closeInventory();
				Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
					public void run() {
						WarpAPI.setWarp(p, "MLG");
						MLGAPI.setupPlayer(p);
						p.setGameMode(GameMode.SURVIVAL);
					p.getInventory().clear();
					p.getInventory().setItem(4, MenusAPI.createItemMenu(Material.WATER_BUCKET, "§b§lMLG", new String[] {""}));
						ScoreBoarding.setScoreBoard(p);
						p.updateInventory();
					}
				}, 2 * 20);
			}
			if(e.getSlot() == 12) {
				p.sendMessage("§aTeleportando para Warp FPS...");
				
				p.closeInventory();
				Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
					public void run() {
						WarpAPI.setWarp(p, "FPS");
						ItemStack sopa = new ItemStack(Material.MUSHROOM_SOUP);
						for(int i = 0; i < 36; i++){
					     	   p.getInventory().setItem(i, sopa);
					     	   p.updateInventory();
							}
						
							p.getInventory().setItem(13, API.createItem(p, Material.RED_MUSHROOM, "§cCogumelo Vermelho", new String[] {""}, 64, (short)0));
							p.getInventory().setItem(14, API.createItem(p, Material.BROWN_MUSHROOM, "§eCogumelo Marrom", new String[] {""}, 64, (short)0));
							p.getInventory().setItem(15, API.createItem(p, Material.BOWL, "§7Tigela", new String[] {""}, 64, (short)0));
							p.removePotionEffect(PotionEffectType.SLOW);
							p.removePotionEffect(PotionEffectType.BLINDNESS);
							p.removePotionEffect(PotionEffectType.SPEED);
							p.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
								ItemStack Espada = new ItemStack(Material.DIAMOND_SWORD);
								
								ItemMeta kEspada = Espada.getItemMeta();
								kEspada.setDisplayName("§eEspada " + KitAPI.getKitName(p));
								Espada.setItemMeta(kEspada);
							    Protection.setImortal(p, true);
					            p.getInventory().setItem(0, Espada);  
					            
					            ItemStack Peito = new ItemStack(Material.IRON_CHESTPLATE);
								ItemMeta kPeito = Peito.getItemMeta();
								kPeito.setDisplayName("§ePeitoral");
								Peito.setItemMeta(kPeito);
								p.getInventory().setChestplate(Peito);
								
								ItemStack Calça = new ItemStack(Material.IRON_LEGGINGS);
								ItemMeta kCalça = Calça.getItemMeta();
								kCalça.setDisplayName("§eCalça");
								Calça.setItemMeta(kCalça);
								//p.getInventory().setLeggings(Calça);
								
								ItemStack bota = new ItemStack(Material.IRON_BOOTS);
								ItemMeta kbota = bota.getItemMeta();
								kbota.setDisplayName("§eBota");
								bota.setItemMeta(kbota);
								//p.getInventory().setBoots(bota);
								
								ItemStack Capacete = new ItemStack(Material.IRON_HELMET);
								ItemMeta kCapacete = Capacete.getItemMeta();
								kCapacete.setDisplayName("§eCapacete");
								Capacete.setItemMeta(kCapacete);
								//p.getInventory().setHelmet(Capacete);
								ScoreBoarding.setScoreBoard(p);
								p.updateInventory();
								
					}
				}, 2 * 20);
			}
			
			if(e.getSlot() == 14) {
				p.sendMessage("§aTeleportando para Warp LavaChallenge...");
				p.closeInventory();
				Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
					public void run() {
						WarpAPI.setWarp(p, "Lava");
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
				}, 2 * 20);
			}
			
			if(e.getSlot() == 13) {
				p.sendMessage("§aTeleportando para Warp 1v1...");
				p.closeInventory();
				Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
					public void run() {
						WarpAPI.setWarp(p, "1v1");
						p.getInventory().clear();
						Protection.setImortal(p, true);
						
						ItemStack Espada = new ItemStack(Material.BLAZE_ROD);
						ItemMeta kEspada = Espada.getItemMeta();
						kEspada.setDisplayName("§e1v1");
						Espada.setItemMeta(kEspada);
						
						p.removePotionEffect(PotionEffectType.SPEED);
						p.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
			            p.getInventory().setItem(4, Espada);  
						
						p.updateInventory();
						ScoreBoarding.setScoreBoard(p);
					}
				}, 2 * 20);
			}
			if(e.getSlot() == 11) {
				p.sendMessage("§aTeleportando para Warp Parkour...");
				p.closeInventory();
				Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
					public void run() {
						WarpAPI.setWarp(p, "Parkour");
					p.getInventory().clear();
					p.getInventory().setItem(4, MenusAPI.createItemMenu(Material.REDSTONE_BLOCK, "§cSair", new String[] {""}));
						ScoreBoarding.setScoreBoard(p);
						p.updateInventory();
						ParkourAPI.addPlayer(p);
					}
				}, 2 * 20);
			}
			e.setCancelled(true);
		}
		
		if(WarpAPI.getWarp(p).equalsIgnoreCase("Spawn")&&e.getInventory().getTitle().equalsIgnoreCase("§aConquistas")){
			if(e.getCurrentItem().getTypeId() == 159) {
				for(Conquista conq : ConquistaManager.conquistas) {
					if(conq.getName().equalsIgnoreCase(ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName()))) {
						if(ConquistaAPI.hasConquista(p, conq.getName())) {
							e.setCancelled(true);
							return;
						}
						if(!conq.onComplete(p)) {
							p.sendMessage(MessageAPI.Command_Error+"Você ainda não completou essa conquista");
							p.closeInventory();
						}else {
							p.closeInventory();
							MenusAPI.OnpenConquista(p);
							runFogos(p.getLocation());
						}
					}
				}
			}
			e.setCancelled(true);
		}
		
		if(WarpAPI.getWarp(p).equalsIgnoreCase("Spawn")&&e.getInventory().getTitle().equalsIgnoreCase("§aRanks")){
			e.setCancelled(true);
		}
		
		if(WarpAPI.getWarp(p).equalsIgnoreCase("Spawn")&&e.getInventory().getTitle().equalsIgnoreCase("§aLoja de Cash")){
			if(e.getSlot() ==  3) {
				KitMenuCash.OpenInventory(p);
				e.setCancelled(true);
			}
			if(e.getSlot() ==  5) {
				VipMenuCash.OpenInventory(p);
				e.setCancelled(true);
			}
		}
		
		if(WarpAPI.getWarp(p).equalsIgnoreCase("Spawn")&&e.getInventory().getTitle().equalsIgnoreCase("§aLoja")){
			if(e.getSlot() ==  3) {
				LojaKit.OpenInventory(p);
				e.setCancelled(true);
			}
			if(e.getSlot() ==  5) {
				LojaMenu.OpenInventoryCash(p);
				e.setCancelled(true);
			}
		}
		
		if(WarpAPI.getWarp(p).equalsIgnoreCase("Spawn")&&e.getInventory().getTitle().equalsIgnoreCase("§aLoja de Kits (CASH)")){
			if(e.getCurrentItem() == null || e.getCurrentItem().getItemMeta() == null)return;
			   for(Kit kits : KitManager.kits) {
				   if(e.getCurrentItem().isSimilar(kits.getItem())){
					   if(Status.getCash(p.getName())< KitMenuCash.getPrice(kits)) {
						   p.sendMessage(MessageAPI.Command_Error+"Cash insuficientes");
						   p.closeInventory();
					   }else {
						   p.sendMessage(MessageAPI.Command_Succes+"Você comprou o Kit §e"+kits.getName());
						   KitAPI.darKit(p, kits);
						   Status.removeCash(p.getName(), KitMenuCash.getPrice(kits));
						   p.closeInventory();
						   ScoreBoarding.setScoreBoard(p);
					   }
				   }
			   }
			   e.setCancelled(true);
		}
		
		if(WarpAPI.getWarp(p).equalsIgnoreCase("Spawn")&&e.getInventory().getTitle().equalsIgnoreCase("§aLoja de VIP")){
			if(e.getCurrentItem() == null || e.getCurrentItem().getItemMeta() == null)return;
			if(e.getSlot() == 2) {
				if(Status.getCash(p.getName()) < 5) {
					p.sendMessage(MessageAPI.Command_Error+"Cash insuficiente");
					e.setCancelled(true);
					p.closeInventory();
					return;
				}
				if(GroupAPI.getGroup(p).equalsIgnoreCase("pro") || GroupAPI.getGroup(p).equalsIgnoreCase("mvp")) {
					p.sendMessage(MessageAPI.Command_Error+"Você não pode trocar de VIP, espere o atual expirar!");
					e.setCancelled(true);
					return;
				}
				Status.removeCash(p.getName(), 5);
				p.sendMessage(MessageAPI.Command_Succes+"Você comprou esse VIP com Sucesso");
				VIPAPI.addVIP(p, "light", 30);
				p.sendMessage("§aEle expira em §c"+GroupAPI.getTime(p));
				p.closeInventory();
			}
			if(e.getSlot() == 4) {
				if(Status.getCash(p.getName()) < 10) {
					p.sendMessage(MessageAPI.Command_Error+"Cash insuficiente");
					e.setCancelled(true);
					p.closeInventory();
					return;
				}
				if(GroupAPI.getGroup(p).equalsIgnoreCase("pro") || GroupAPI.getGroup(p).equalsIgnoreCase("light")) {
					p.sendMessage(MessageAPI.Command_Error+"Você não pode trocar de VIP, espere o atual expirar!");
					e.setCancelled(true);
					return;
				}
				Status.removeCash(p.getName(), 10);
				p.sendMessage(MessageAPI.Command_Succes+"Você comprou esse VIP com Sucesso");
				VIPAPI.addVIP(p, "mvp", 30);
				p.sendMessage("§aEle expira em §c"+GroupAPI.getTime(p));
				p.closeInventory();
			}
			if(e.getSlot() == 6) {
				if(Status.getCash(p.getName()) < 15) {
					p.sendMessage(MessageAPI.Command_Error+"Cash insuficiente");
					e.setCancelled(true);
					p.closeInventory();
					return;
				}
				if(GroupAPI.getGroup(p).equalsIgnoreCase("light") || GroupAPI.getGroup(p).equalsIgnoreCase("light")) {
					p.sendMessage(MessageAPI.Command_Error+"Você não pode trocar de VIP, espere o atual expirar!");
					e.setCancelled(true);
					return;
				}
				Status.removeCash(p.getName(), 15);
				p.sendMessage(MessageAPI.Command_Succes+"Você comprou esse VIP com Sucesso");
				VIPAPI.addVIP(p, "pro", 30);
				p.sendMessage("§aEle expira em §c"+GroupAPI.getTime(p));
				p.closeInventory();
				
			}
			ScoreBoarding.setScoreBoard(p);
			e.setCancelled(true);
		}
		
		if(WarpAPI.getWarp(p).equalsIgnoreCase("Spawn")&&e.getInventory().getTitle().equalsIgnoreCase("§aLoja de Kits")){
			if(e.getCurrentItem() == null || e.getCurrentItem().getItemMeta() == null)return;
		   for(Kit kits : KitManager.kits) {
			   if(e.getCurrentItem().isSimilar(kits.getItem())){
				   if(Status.getCoins(p)< kits.getPrice()) {
					   p.sendMessage(MessageAPI.Command_Error+"Coins insuficientes");
					   p.closeInventory();
				   }else {
					   p.sendMessage(MessageAPI.Command_Succes+"Você comprou o Kit §e"+kits.getName());
					   KitAPI.darKit(p, kits);
					   Status.removeCoins(p, kits.getPrice());
					   p.closeInventory();
					   ScoreBoarding.setScoreBoard(p);
				   }
			   }
		   }
		   e.setCancelled(true);
		}
		
		if(WarpAPI.getWarp(p).equalsIgnoreCase("Spawn")&&e.getInventory().getTitle().equalsIgnoreCase("§aLoja de Caixas")){
			e.setCancelled(true);
			if(e.getSlot() == 12) {
				if(Status.getCoins(p) < 1500) {
					
					p.sendMessage(MessageAPI.Command_Error+"Coins insuficientes.");
					p.closeInventory();
				}else {
					Status.addCaixas(p, 1);
					Status.removeCoins(p, 1500);
					p.sendMessage(MessageAPI.Command_Succes+"Compra realizada com Sucesso");
					ScoreBoarding.setScoreBoard(p);
				}
			}
			if(e.getSlot() == 13) {
				if(Status.getCoins(p) < 1500 * 5) {
					p.sendMessage(MessageAPI.Command_Error+"Coins insuficientes.");
					Status.removeCoins(p, 1500 * 5);
					p.closeInventory();
				}else {
					Status.addCaixas(p, 5);
					Status.removeCoins(p, 1500 * 5);
					p.sendMessage(MessageAPI.Command_Succes+"Compra realizada com Sucesso");
					ScoreBoarding.setScoreBoard(p);
				}
			}
			if(e.getSlot() == 14) {
				if(Status.getCoins(p) < 1500 * 10) {
					p.sendMessage(MessageAPI.Command_Error+"Coins insuficientes.");
					p.closeInventory();
				}else {
					Status.addCaixas(p, 10);
					Status.removeCoins(p, 1500 * 10);
					p.sendMessage(MessageAPI.Command_Succes+"Compra realizada com Sucesso");
					ScoreBoarding.setScoreBoard(p);
				}
			}
		}
		
		if(WarpAPI.getWarp(p).equalsIgnoreCase("Spawn")&&e.getInventory().getTitle().equalsIgnoreCase("§aKit diário")){
			e.setCancelled(true);
		}
		
		if(WarpAPI.getWarp(p).equalsIgnoreCase("Spawn")&&e.getInventory().getTitle().equalsIgnoreCase("§aAbrindo caixa...")){
			e.setCancelled(true);
		}
		
		if(WarpAPI.getWarp(p).equalsIgnoreCase("Spawn")&&e.getInventory().getTitle().equalsIgnoreCase("§aCaixas")){
			if(e.getSlot() == 8) {
				MenusAPI.lojaCaixas(p);
			}
			if(e.getSlot() == 13) {
				if(Status.getCaixas(p) > 0) {
					new CaixaMenu(p).newScroll();
				}else {
					p.sendMessage(MessageAPI.Command_Error+"Você não possui nenhuma caixa");
				}
			}
			e.setCancelled(true);
		}
		if(WarpAPI.getWarp(p).equalsIgnoreCase("Spawn")&&e.getInventory().getTitle().equalsIgnoreCase("§aKits")){
			if(e.getSlot() == 4) {
				LojaKit.OpenInventory(p);
			}
			for(Kit kits : KitManager.kits){
				if(e.getCurrentItem() == null||e.getCurrentItem().getItemMeta() == new ItemStack(Material.AIR)){
					return;
				}
				if(e.getCurrentItem().isSimilar(kits.getItem())){
					int i = rand.nextInt(5);
					if(i == 0){
						i = 3;
					}
					KitAPI.setKit(p, kits);
					p.sendMessage(MessageAPI.Command_Succes+"Você selecionou o Kit §e"+kits.getName());
					WarpAPI.setWarp(p, String.valueOf("Arena"+i));
					Protection.setImortal(p, false);
					KitAPI.giveKit(p,kits);
					KillStreakAPI.resetKS(p);
					
					ScoreBoarding.setScoreBoard(p);
					e.setCancelled(true);
					p.closeInventory();
				}
			}
			e.setCancelled(true);
		}
	}
	

}
