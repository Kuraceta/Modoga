package kRdm;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import api.WarpAPI;
import protection.Protection;

public class RDMApi {
	
	public static Player player1;
	public static Player player2;
	public static ArrayList<Player> playing = new ArrayList<Player>();
	public static RDMStatus status = RDMStatus.FINALIZADO;
	
	public static void addPlayer(Player p) {
		playing.add(p);
		Bukkit.broadcastMessage("§4§lRDM §e"+p.getName()+" §fentrou no evento");
		p.getInventory().clear();
		WarpAPI.setWarp(p, "RDMSpawn");
		
	}
	
	public static void delPlayer(Player p) {
		if(playing.contains(p)) {
			playing.remove(p);
			WarpAPI.setWarp(p, "Spawn");
			
		}
		
	}
	
	public static void iniciar() {
		player1 = playing.get(new Random().nextInt(playing.size()));
		player2 = playing.get(new Random().nextInt(playing.size()));
		while (player1 == player2) {
			player2 = playing.get(new Random().nextInt(playing.size()));
		}
		enviarItem(player1);
		enviarItem(player2);
		WarpAPI.setWarp(player1, "RDMLoc1");
		WarpAPI.setWarp(player2, "RDMLoc2");
		status = RDMStatus.RODANDO;
	}
	
	public static void enviarItem(Player p) {
		ItemStack sopa = new ItemStack(Material.MUSHROOM_SOUP);
        for(int i = 0; i < 36; i++){
     	   p.getInventory().setItem(i, sopa);
     	  
		}
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
	
	public static void proximaRodada() {;
		if(player1 != null && player1.isOnline() && isPlaying(player1)) {
			WarpAPI.setWarp(player1, "RDMSpawn");
			player1 = null;
			player1 = playing.get(new Random().nextInt(playing.size()));
			while (player1 == player2) {
				player1 = playing.get(new Random().nextInt(playing.size()));
			}
		}
		if(player2 != null && player2.isOnline() && isPlaying(player2)) {
			WarpAPI.setWarp(player2, "RDMSpawn");
			player2 = null;
			player2 = playing.get(new Random().nextInt(playing.size()));
			while (player2 == player1) {
				player2 = playing.get(new Random().nextInt(playing.size()));
			}
		}
		enviarItem(player1);
		enviarItem(player2);
		WarpAPI.setWarp(player1, "RDMLoc1");
		WarpAPI.setWarp(player2, "RDMLoc2");
		Protection.setImortal(RDMApi.getPlayerOne(), false);
		Protection.setImortal(RDMApi.getPlayerTwo(), false);
	}
	
	public static boolean isPlaying(Player p) {
		return playing.contains(p);
	}
	
	public static Player getPlayerOne() {
		return player1;
	}
	
	public static Player getPlayerTwo() {
		return player2;
	}
	
	public static boolean ChecarGanhador(Player p) {
		if(playing.size() <= 1) {
			return true;
		}
		return false;
	}
}


