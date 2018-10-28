package kKit;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import api.API;
import kCommandCommands.cFly;

public class KitAPI {
	
	private static HashMap<Player, Kit> kits = new HashMap<Player, Kit>();
	
	public static void darKit(Player p, Kit kit) {
		Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "pex user "+p.getName()+" add " + kit.getPerm());
	}
	
	public static Kit getKitPlayer(Player p) {
		if(kits.containsKey(p)) {
			return kits.get(p);
		}
		return null;
	}
	
	public static boolean hasKit(String grupo, Kit kit) {
		ArrayList<String> cmds = (ArrayList<String>) kConfig.Config.getConfig().getStringList("Grupo."+grupo+".Kits");
		if(cmds.size() == 0) {
			return false;
		}
		for(String cmdsallow : kConfig.Config.getConfig().getStringList("Grupo."+grupo+".Kits")) {
			if(cmdsallow.equalsIgnoreCase("*")) {
				return true;
			}
			if(cmdsallow.equalsIgnoreCase(kit.getName())) {
				return true;
			}
		}
		return false;
	}
	
	public static void giveKit(Player p, Kit kit2){
		ItemStack sopa = new ItemStack(Material.MUSHROOM_SOUP);
        for(int i = 0; i < 36; i++){
     	   p.getInventory().setItem(i, sopa);
     	  
		}
        if(cFly.Fly.contains(p)) {
        	cFly.Fly.remove(p);
        	p.setAllowFlight(false);
        	p.setFlying(false);
        }
        p.getInventory().setItem(13, API.createItem(p, Material.RED_MUSHROOM, "§cCogumelo Vermelho", new String[] {""}, 64, (short)0));
		p.getInventory().setItem(14, API.createItem(p, Material.BROWN_MUSHROOM, "§eCogumelo Marrom", new String[] {""}, 64, (short)0));
		p.getInventory().setItem(15, API.createItem(p, Material.BOWL, "§7Tigela", new String[] {""}, 64, (short)0));
		p.getInventory().setItem(8, API.createItem(p, Material.COMPASS, "§eBussola", new String[] {""}, 1, (short)0));
		if(kit2.getName().equalsIgnoreCase("gladiator")) {
			p.getInventory().setItem(2, new ItemStack(Material.COBBLESTONE ,32));
		}
		if(getKitName(p).equalsIgnoreCase("PvP")){
            ItemStack Espada = new ItemStack(Material.STONE_SWORD);
			
			ItemMeta kEspada = Espada.getItemMeta();
			kEspada.setDisplayName("§eEspada");
			Espada.setItemMeta(kEspada);
			Espada.addEnchantment(Enchantment.DAMAGE_ALL, 1);
            p.getInventory().setItem(0, Espada);  
            

			ItemStack Peito = new ItemStack(Material.LEATHER_CHESTPLATE);
			LeatherArmorMeta kPeito = (LeatherArmorMeta)Peito.getItemMeta();
			kPeito.setDisplayName("§cPeitoral");
			kPeito.setColor(Color.RED);
			Peito.setItemMeta(kPeito);
			p.getInventory().setChestplate(Peito);
		}else{
            ItemStack Espada = new ItemStack(Material.STONE_SWORD);
			
			ItemMeta kEspada = Espada.getItemMeta();
			kEspada.setDisplayName("§eEspada");
			Espada.setItemMeta(kEspada);
			
			ItemStack Peito = new ItemStack(Material.LEATHER_CHESTPLATE);
			LeatherArmorMeta kPeito = (LeatherArmorMeta)Peito.getItemMeta();
			kPeito.setDisplayName("§6Peitoral");
			kPeito.setColor(Color.ORANGE);
			Peito.setItemMeta(kPeito);
			p.getInventory().setChestplate(Peito);
			
            p.getInventory().setItem(0, Espada); 
		}
		if(kit2.isSendItem()) {
             ItemStack Espada = kit2.getItem();
			
			ItemMeta kEspada = Espada.getItemMeta();
			kEspada.setDisplayName("§e"+kit2.getName());
			Espada.setItemMeta(kEspada);
			p.getInventory().setItem(1, Espada); 
		}
		p.spigot().setCollidesWithEntities(true);
		p.setGameMode(GameMode.SURVIVAL);
	}
	
	public static String getKitName(Player p) {
		if(getKitPlayer(p)==null) {
			return "Nenhum";
		}
		return getKitPlayer(p).getName();
	}
	
	public static void setKit(Player p , Kit kit) {
		kits.put(p, kit);
	}
	
	public static void clearKit(Player p) {
		if(kits.containsKey(p)) {
			kits.remove(p);
		}
	}

	public static void removeKit(Player p) {
		if(kits.containsKey(p)) {
			kits.remove(p);
		}
	}

}
