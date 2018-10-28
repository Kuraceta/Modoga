package menuCash;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import kKit.Kit;
import kKit.KitManager;
import menu.MenusAPI;

public class KitMenuCash {
	
   public static ItemStack createItemMenu(Kit kit){
		
		ItemStack item = kit.getItem();
		ItemMeta kitem = item.getItemMeta();
		kitem.setDisplayName("§7Kit §a§l"+kit.getName());
		kitem.setLore(Arrays.asList(new String[] {"","§fPreço desse Kit §e"+MenusAPI.money(getPrice(kit))+" cash"}));
		item.setItemMeta(kitem);
		
		return item;
	}
    
   public static int getPrice(Kit kit) {
	   String preco = String.valueOf(kit.getPrice()).substring(0, 1);
	   return Integer.parseInt(preco);
   }
   
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
     
     public static void OpenInventory(Player p) {
    	 Inventory inv = Bukkit.createInventory(p, 54,"§aLoja de Kits (CASH)");
    	 ItemStack vidroverde = createItemMenu(Material.STAINED_GLASS_PANE, "§a-", new String[] {" "},(short)5);
    	 for (int i = 0; i < 9; i++) {
			inv.setItem(i,vidroverde);
		 }
    	 for (int i = 9; i < 18; i++) {
 			inv.setItem(i,MenusAPI.getRandomGlass());
 		 }
    	 int d = 18;
    	 for(Kit kits : KitManager.kits) {
    		 if(!p.hasPermission(kits.getPerm())&& !kits.isFree()) {
    			 inv.addItem(createItemMenu(kits));
    			 d++;
    		 }
    	 }
    	 
    	 for (int i = d; i < 54; i++) {
    		 inv.setItem(i,MenusAPI.getRandomGlass());
		}
        if(d== 18) {
        	inv.setItem((54/2)+ 4,createItemMenu(Material.BEACON, "§c§lVocê possui todos os KITS", new String[] {" "}));
    	 }
    	 p.openInventory(inv);
     }

}
