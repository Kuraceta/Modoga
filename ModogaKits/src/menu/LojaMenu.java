package menu;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class LojaMenu {
    
   public static ItemStack createItemMenu(Material material, String nome, String[] habilidade,short data){
		
		ItemStack item = new ItemStack(material,1,data);
		ItemMeta kitem = item.getItemMeta();
		kitem.setDisplayName(nome);
		kitem.setLore(Arrays.asList(habilidade));
		item.setItemMeta(kitem);
		
		return item;
	}
   
     public static ItemStack createItemMenu(Material material, String nome, String[] habilidade){
		
		ItemStack item = new ItemStack(material);
		ItemMeta kitem = item.getItemMeta();
		kitem.setDisplayName(nome);
		kitem.setLore(Arrays.asList(habilidade));
		item.setItemMeta(kitem);
		
		return item;
	}
     
     public static void OpenInventoryMain(Player p) {
    	 Inventory inv = Bukkit.createInventory(p, 9,"§aLoja");
    	 inv.setItem(3, createItemMenu(Material.DIAMOND, "§eMoney", new String[] {" ","§fCompre algo usando Money"}));
    	 inv.setItem(5, createItemMenu(Material.EMERALD, "§eCash", new String[] {" ","§fCompre algo usando Cash"}));
    	 p.openInventory(inv);
     }
     
     public static void OpenInventoryCash(Player p) {
    	 Inventory inv = Bukkit.createInventory(p, 9,"§aLoja de Cash");
    	 inv.setItem(3, createItemMenu(Material.NETHER_STAR, "§eKits", new String[] {" ","§fCompre kits usando Cash"}));
    	 inv.setItem(5, createItemMenu(Material.GOLD_BLOCK, "§eVIP", new String[] {" ","§fCompre VIP usando Cash"}));
    	 p.openInventory(inv);
     }

}
