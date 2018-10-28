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

public class VipMenuCash {
    
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
    	 Inventory inv = Bukkit.createInventory(p, 9,"§aLoja de VIP");
    	 for (int i = 0; i < 9; i++) {
			inv.setItem(i,MenusAPI.getRandomGlass());
		 }
    	 inv.setItem(2, createItemMenu(Material.EMERALD_BLOCK, "§f§lVIP §a§lLIGHT", new String[] {" ","§f30 dias"," ","§e§lVANTAGENS","§a- §fTag §a§lLIGHT §fno chat/tab","§a- §fAcesso 11 Kits gratuitamente","§a- §fRecebe 1k money ao Ativar","§a- §fPode falar colorido","" ,"§fPreço §e5 cash"}));
    	 inv.setItem(4, createItemMenu(Material.DIAMOND_BLOCK, "§f§lVIP §9§lMVP", new String[] {" ","§f30 dias"," ","§e§lVANTAGENS","§a- §fTag §9§lMVP §fno chat/tab","§a- §fAcesso 17 Kits gratuitamente","§a- §fRecebe 2k money ao Ativar","§a- §fPode falar colorido","" ,"§fPreço §e10 cash"}));
    	 inv.setItem(6, createItemMenu(Material.GOLD_BLOCK, "§f§lVIP §6§lPRO", new String[] {" ","§f30 dias"," ","§e§lVANTAGENS","§a- §fTag §6§lPRO §fno chat/tab","§a- §fAcesso a todos os Kits gratuitamente","§a- §fRecebe 3k money ao Ativar","§a- §fPode falar colorido","§a- §fAcesso ao comando /fly" ," ","§fPreço §e15 cash"}));
    	 p.openInventory(inv);
     }

}
