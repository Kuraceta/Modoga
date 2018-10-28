package kCommandCommands;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Bat;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import api.API;
import api.MessageAPI;
import api.WarpAPI;
import br.alkazuz.groupapi.api.GroupAPI;
import main.Main;
import protection.Protection;

public class cAdmin implements CommandExecutor, Listener{

	public static HashMap<Player, ItemStack[]> inventario = new HashMap<Player, ItemStack[]>();
	@SuppressWarnings("deprecation")
	@EventHandler
	private void interact(PlayerInteractEvent e){
		
		Player p = e.getPlayer();
		
		if(p.getItemInHand().getType() == Material.getMaterial(351) && API.admin.contains(p) && e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK && p.getItemInHand().hasItemMeta() && p.getItemInHand().getItemMeta().hasDisplayName() && p.getItemInHand().getItemMeta().getDisplayName().equals("§aTroca Rapida")){
			
			for(Player todos : Bukkit.getOnlinePlayers()){
				
				todos.showPlayer(p);
				p.showPlayer(todos);
			} 
			
			p.setGameMode(GameMode.SURVIVAL);
			API.admin.remove(p);
			p.sendMessage(MessageAPI.Command_Succes+"Fazendo troca rapida...");
			
			p.getInventory().clear();
			p.getInventory().setArmorContents(null);
			
			p.getInventory().setItem(1, API.createItem(p, Material.FEATHER, "§aForcefield", new String[] {""}, 1, (short)0));
			p.getInventory().setItem(4, API.createItem(p, Material.getMaterial(351), "§aTroca Rapida", new String[] {""}, 1, (short)8));
			p.getInventory().setItem(5, API.createItem(p, Material.MUSHROOM_SOUP, "§aAuto-Soup", new String[] {""}, 1, (short)0));
			p.getInventory().setItem(3, API.createItem(p, Material.BEDROCK, "§aJaula", new String[] {""}, 1, (short)0));
			
			
			Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
				public void run() {

					API.admin.add(p);
					p.getInventory().clear();
					p.getInventory().setArmorContents(null);
					ItemStack Item = new ItemStack(Material.BLAZE_ROD);
					ItemMeta kItem = Item.getItemMeta();
					kItem.addEnchant(Enchantment.KNOCKBACK, 3, true);
					kItem.setDisplayName("§aKnockBack");
					Item.setItemMeta(kItem);
					p.getInventory().setItem(7, Item);
					p.getInventory().setItem(1, API.createItem(p, Material.FEATHER, "§aForcefield", new String[] {""}, 1, (short)0));
					p.getInventory().setItem(4, API.createItem(p, Material.getMaterial(351), "§aTroca Rapida", new String[] {""}, 1, (short)8));
					p.getInventory().setItem(5, API.createItem(p, Material.MUSHROOM_SOUP, "§aAuto-Soup", new String[] {""}, 1, (short)0));
					p.getInventory().setItem(3, API.createItem(p, Material.BEDROCK, "§aJaula", new String[] {""}, 1, (short)0));
					
					p.setGameMode(GameMode.CREATIVE);
					
					setAdmin(p);
				}
			}, 20L);
		}
	}
		@EventHandler
		public void autosoup(PlayerInteractEntityEvent e){
		
			Player p = e.getPlayer();
			
			if(p instanceof Player && e.getRightClicked() instanceof Player){
				
				if(p.getItemInHand().getType() == null && API.admin.contains(p)){
					Player t = (Player) e.getRightClicked();
					 p.openInventory(t.getInventory());
				}
                 if(p.getItemInHand().getType() == Material.FEATHER && API.admin.contains(p)){
					
					Player t = (Player) e.getRightClicked();
					
					t.getWorld().spawnEntity(t.getLocation(), EntityType.BAT);
					t.getWorld().spawnEntity(t.getLocation(), EntityType.BAT);
					t.getWorld().spawnEntity(t.getLocation(), EntityType.BAT);
					Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
						public void run() {
							final String world = t.getWorld().getName();
							for (final org.bukkit.entity.Entity entity : Bukkit.getServer().getWorld(world).getEntities()){
								{
									if(entity instanceof Bat){
									 entity.remove();
									}
							     }
				                   
				                }
						}
					}, 100L);
					}
				if(p.getItemInHand().getType() == Material.MUSHROOM_SOUP && API.admin.contains(p)){
					
					Player t = (Player) e.getRightClicked();
					
					if(API.Checando.contains(t)){
						p.sendMessage("Esse player já está sendo checado por outro p.");
						return;
						
					}
				inventario.put(t, t.getInventory().getContents());
				t.getInventory().clear();
				t.setHealth(0.5D);
				API.Checando.add(t);

				p.openInventory(t.getInventory());
					t.getInventory().setItem(20, API.createItem(p, Material.MUSHROOM_SOUP, "§cSopa", new String[] {""}, 1, (short)0));
					
			    p.sendMessage(" ");
			    p.sendMessage(MessageAPI.Command_Succes+"Pegando informações sobre auto-soup...");
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
					public void run() {
					    
						
						if(API.getAmount(t, Material.BOWL) == 1){
							p.sendMessage(" ");
							p.sendMessage("§fResultado: §cPlayer usando auto-soup");
							p.sendMessage("§e> Pode ser que seja um erro antes de banir realmente confirme se ele está de hack.");
						}
						else if(API.getAmount(t, Material.BOWL) == 0){
							p.sendMessage(" ");
							p.sendMessage("§fResultado: §cPlayer não está usando auto-soup");
						}
						API.Checando.remove(t);
						t.getInventory().clear();
						t.closeInventory();
						t.getInventory().setContents(inventario.get(t));
						t.setHealth(20.0D);
					}
				}, 1*20);
				}
			}
			 if(p.getItemInHand().getType() == Material.BEDROCK && API.admin.contains(p)){
					Player t = (Player) e.getRightClicked();
			    p.sendMessage(MessageAPI.Command_Succes+"Você prendeu o player §a(" + p.getName() + ") §7na jaula, assim que terminar seu trabalho com ele quebre a jaula.");
		          t.getLocation().add(0.0D, 13.0D, 0.0D).getBlock().setType(Material.BEDROCK);
		          t.getLocation().add(0.0D, 11.0D, 1.0D).getBlock().setType(Material.BEDROCK);
		          t.getLocation().add(1.0D, 11.0D, 0.0D).getBlock().setType(Material.BEDROCK);
		          t.getLocation().add(0.0D, 11.0D, -1.0D).getBlock().setType(Material.BEDROCK);
		          t.getLocation().add(-1.0D, 11.0D, 0.0D).getBlock().setType(Material.BEDROCK);
		          t.getLocation().add(0.0D, 10.0D, 0.0D).getBlock().setType(Material.BEDROCK);
		          t.teleport(t.getLocation().add(0.0D, 11.0D, -0.05D));
		          p.teleport(t.getLocation().add(3.0D, 0.0D, 0.05D));
			}
			 if(p.getItemInHand().getType() == Material.AIR && API.admin.contains(p)){
					Player t = (Player) e.getRightClicked();
				p.openInventory(t.getInventory());
			}
	}
	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender Sender, Command Cmd, String Label, String[] Args) {
		
		Player p = (Player)Sender;
		
		if(Cmd.getName().equalsIgnoreCase("admin")){
			
			if(!GroupAPI.GroupCanExecute(GroupAPI.getGroup(p), Cmd.getName())){
				p.sendMessage(MessageAPI.Command_Error+"Comando inexistente ou você não tem acesso");
				return true;
		    }
			if(!API.admin.contains(p)){
					API.admin.add(p);
					
					
					p.getInventory().clear();
					p.getInventory().setArmorContents(null);
					ItemStack Item = new ItemStack(Material.BLAZE_ROD);
					ItemMeta kItem = Item.getItemMeta();
					kItem.addEnchant(Enchantment.KNOCKBACK, 3, true);
					kItem.setDisplayName("§aKnockBack");
					Item.setItemMeta(kItem);
					Protection.setImortal(p, false);
					p.sendMessage(MessageAPI.Command_Succes+"Você entrou no modo §2admin");
					WarpAPI.sWarp(p, "admin");
					p.setGameMode(GameMode.CREATIVE);
					p.getInventory().setItem(7, Item);
					p.getInventory().setItem(1, API.createItem(p, Material.FEATHER, "§aForcefield", new String[] {""}, 1, (short)0));
					p.getInventory().setItem(4, API.createItem(p, Material.getMaterial(351), "§aTroca Rapida", new String[] {""}, 1, (short)8));
					p.getInventory().setItem(5, API.createItem(p, Material.MUSHROOM_SOUP, "§aAuto-Soup", new String[] {""}, 1, (short)0));
					p.getInventory().setItem(3, API.createItem(p, Material.BEDROCK, "§aJaula", new String[] {""}, 1, (short)0));
					
					setAdmin(p);
					
					return true;
				}
				if(API.admin.contains(p)){
					
					API.admin.remove(p);
					p.sendMessage(MessageAPI.Command_Succes+"Você saiu do modo §cadmin");
					WarpAPI.sWarp(p, "Spawn");
					p.getInventory().clear();
					p.getInventory().setArmorContents(null);
			     	   p.updateInventory();
			     	   
					for(Player todos : Bukkit.getOnlinePlayers()){
						
						todos.showPlayer(p);
						p.showPlayer(todos);
					} 
				}
		}
		return false;
	}
	@SuppressWarnings("deprecation")
	public static void setAdmin(Player p){
		
		for(Player todos : Bukkit.getOnlinePlayers()){
			if(GroupAPI.GroupCanExecute(GroupAPI.getGroup(todos), "admin")){
				todos.showPlayer(p);
				p.showPlayer(todos);
			}else{
				if(API.admin.contains(p)){
				todos.hidePlayer(p);
				}
					}
			      }
	}
	@SuppressWarnings("deprecation")
	public static void checarAdmin(){
		
		for(Player todos : Bukkit.getOnlinePlayers()){
		if(API.admin.contains(todos)){
			
			setAdmin(todos);
		}
		}
	}
}
