﻿package hacks;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import antiCheat.Utills;
import antiCheat.Utills.Hack;

public class AutoSoup implements Listener {

	private static HashMap<UUID, Long> JogadorSendoAtacado = new HashMap<UUID, Long>();
	
	@SuppressWarnings("deprecation")
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onSoup(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		Damageable hp = p;
		if (hp.getHealth() != 20.0D) {
			int sopa = 7;
	        if (((e.getAction() == Action.RIGHT_CLICK_AIR) || (e.getAction() == Action.RIGHT_CLICK_BLOCK)) && (p.getItemInHand().getTypeId() == 282)) {
	        	p.setHealth(hp.getHealth() + sopa > hp.getMaxHealth() ? hp.getMaxHealth() : hp.getHealth() + sopa);
	        	p.getItemInHand().setType(Material.BOWL);
	        }
		}
	}
	  
	@EventHandler
	private void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
		if (!(event.getDamager() instanceof Player)) {
			return;
		}
	    Player player = (Player)event.getDamager();
	    SetarAtacandoTempo(player.getUniqueId());
	}
	
	@EventHandler
	public void onInventoryInteract(InventoryClickEvent Evento) {
		
		Player Jogador = (Player)Evento.getWhoClicked();
		if (!(Evento.getWhoClicked() instanceof Player)) {
			return;
		}
		if (Evento.isCancelled()) {
			return;
		}
		if (Evento.getAction() != InventoryAction.MOVE_TO_OTHER_INVENTORY) {
			return;
		}
		if (Evento.getSlot() == -1) {
			return;
		}
		long Tempo = System.currentTimeMillis() - PegarAtacandoTempo(Jogador.getUniqueId());
		int Botes = 0;
		ItemStack[] ItemStacks;
		int ASoup = (ItemStacks = Jogador.getInventory().getContents()).length;
		for (int i = 0; i < ASoup; i++) {
			ItemStack ItemStack = ItemStacks[i];
			if ((ItemStack != null) && (ItemStack.getType() == Material.BOWL)) {
				Botes += ItemStack.getAmount();
			}
		}
		if (Tempo >= 99L) {
			return;
		}
		if (Botes < 5) {
			return;
		}
		Utills.Hack AutoSoupTalvez = Hack.AUTOSOUPTALVEZ;
		Utills.Hack AutoSoupProvavelmente = Hack.AUTOSOUPPROVAVELMENTE;
		Utills.Hack AutoSoupDefinitivamente = Hack.AUTOSOUPDEFINITIVAMENTE;
		if (Tempo < 30L) {
			Utills.AutoSoup = AutoSoupTalvez.getMenssagem().replace("nick", Jogador.getDisplayName()).replace("avisos", Utills.AvisosAutoSoup.get(Jogador) + "");
			if (Utills.AutoSoup != null) {
				Utills.AvisosAutoSoup.put(Jogador, Integer.valueOf(Utills.AvisosAutoSoup.get(Jogador) + 1));
				Utills.sendAntiCheat(Utills.AutoSoup);
			}
			Utills.AutoSoup = null;
		} 
		else if (Tempo < 60L) {
			Utills.AutoSoup = AutoSoupProvavelmente.getMenssagem().replace("nick", Jogador.getDisplayName()).replace("avisos", Utills.AvisosAutoSoup.get(Jogador) + "");
			if (Utills.AutoSoup != null) {
				Utills.AvisosAutoSoup.put(Jogador, Integer.valueOf(Utills.AvisosAutoSoup.get(Jogador) + 1));
				Utills.sendAntiCheat(Utills.AutoSoup);
			}
			Utills.AutoSoup = null;
		} 
		else if (Tempo < 99L) {
			Utills.AutoSoup = AutoSoupDefinitivamente.getMenssagem().replace("nick", Jogador.getDisplayName()).replace("avisos", Utills.AvisosAutoSoup.get(Jogador) + "");
			if (Utills.AutoSoup != null) {
				Utills.AvisosAutoSoup.put(Jogador, Integer.valueOf(Utills.AvisosAutoSoup.get(Jogador) + 1));
				Utills.sendAntiCheat(Utills.AutoSoup);
			}
			Utills.AutoSoup = null;
		}  
	}
	  
	public static long PegarAtacandoTempo(UUID uuid) {
		if (!JogadorSendoAtacado.containsKey(uuid)) {
			JogadorSendoAtacado.put(uuid, Long.valueOf(System.currentTimeMillis() / 1000L));
	    }
		return ((Long)JogadorSendoAtacado.get(uuid)).longValue();
	}
	  
	public static void SetarAtacandoTempo(UUID uuid) {
	    JogadorSendoAtacado.put(uuid, Long.valueOf(System.currentTimeMillis()));
	}
}
