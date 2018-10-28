package menu;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import api.WarpAPI;

public class WarpsMenu {
	
    public static ItemStack createItemMenu(Material material, String nome, String[] habilidade){
		
		ItemStack item = new ItemStack(material);
		ItemMeta kitem = item.getItemMeta();
		kitem.setDisplayName(nome);
		kitem.setLore(Arrays.asList(habilidade));
		item.setItemMeta(kitem);
		
		return item;
	}
	public static void inventory(Player p){
		Inventory menu = Bukkit.createInventory(p, 27, "Warps");
		
		for (int i = 0; i < 27; i++) {
			menu.setItem(i, MenusAPI.getRandomGlass());
		}
		
		menu.setItem(11, createItemMenu(Material.LADDER,"§b§lParkour",new String[] {"","§fQue tal um lugar de ação?","§fGosta de correr? subir escadas? Pular? e subir paredes?","§fEntão este é um","§flugar perfeito para você"," ","§aJogadores nessa Warp §f"+getQuantidate("Parkour")}));
		menu.setItem(12, createItemMenu(Material.GLASS,"§b§lFPS",new String[] {"","§fUm Lugar livre, sem lag","§fConsiga o máximo de FPS aqui."," ","§aJogadores nessa Warp §f"+getQuantidate("fps")}));
		menu.setItem(13, createItemMenu(Material.BLAZE_ROD,"§b§l1v1",new String[] {"","§fDesafie qualquer jogador nessa Warp","§fNinguém irá lhe atrapalhar."," ","§aJogadores nessa Warp §f"+getQuantidate("1v1")}));
		menu.setItem(14, createItemMenu(Material.LAVA_BUCKET,"§b§lLavaChallenge",new String[] {"","§fTreine seu refil e seu resoup ao mesmo tempo","§fIsso não é para qualquer um."," ","§aJogadores nessa Warp §f"+getQuantidate("Lava")}));
		menu.setItem(15, createItemMenu(Material.WATER_BUCKET,"§b§lMLG",new String[] {"","§fVocê se acha bom e não tomar dano de queda?","§fQue tal tirar essa dúvida agora?!","§fEssa warp foi feita para isso"," ","§aJogadores nessa Warp §f"+getQuantidate("mlg")}));
		menu.setItem(8, createItemMenu(Material.DIAMOND_SWORD,"§b§lNoDelay",new String[] {"","§fUma warp sem delay para Hit","§fDizem que ela feita para quem consegue tankar"," ","§aJogadores nessa Warp §f"+getQuantidate("nodelay")}));
		
		p.openInventory(menu);
	}

	public static int getQuantidate(String warp) {
		int quantidade = 0;
		for(Player p : Bukkit.getOnlinePlayers()) {
			if(WarpAPI.getWarp(p).equalsIgnoreCase(warp)) {
				quantidade++;
			}
		}
		return quantidade;
	}
	
}
