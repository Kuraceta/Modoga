package menu;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import br.alkazuz.groupapi.api.GroupAPI;
import kKit.Kit;
import kKit.KitAPI;
import kKit.KitManager;

public class KitMenu {
	
   public static ItemStack createItemMenu(Kit kit){
		
		ItemStack item = kit.getItem();
		ItemMeta kitem = item.getItemMeta();
		kitem.setDisplayName("§7Kit §a§l"+kit.getName());
		kitem.setLore(Arrays.asList(kit.getDesc()));
		item.setItemMeta(kitem);
		
		return item;
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
    	 Inventory inv = Bukkit.createInventory(p, 54,"§aKits");
    	 
    	 ItemStack vidropreto = createItemMenu(Material.STAINED_GLASS_PANE, "§a-", new String[] {" "},(short)15);
    	 ItemStack vidroverde = createItemMenu(Material.STAINED_GLASS_PANE, "§a-", new String[] {" "},(short)5);
    	 for (int i = 0; i < 9; i++) {
			inv.setItem(i,vidropreto);
		 }
    	 for (int i = 9; i < 18; i++) {
 			inv.setItem(i,vidroverde);
 		 }
    	 int d = 18;
    	 for(Kit kits : KitManager.kits) {
    		 if(p.hasPermission(kits.getPerm())|| kits.isFree()||KitAPI.hasKit(GroupAPI.getGroup(p), kits)) {
    			 inv.addItem(createItemMenu(kits));
    			 d++;
    		 }
    	 }
    	 for (int i = d; i < 54; i++) {
    		 inv.setItem(i,vidropreto);
		}
    	 inv.setItem(0, createItemMenu(Material.INK_SACK, "§7Página anterior", new String[] {" "},(short)8));
    	 inv.setItem(8, createItemMenu(Material.INK_SACK, "§aPróxima pagina", new String[] {" "},(short)10));
    	 inv.setItem(4, createItemMenu(Material.MINECART, "§a§lLoja de Kits", new String[] {"§fCompre Kits com coins","§fganhados jogando."}));
    	 p.openInventory(inv);
     }

}
