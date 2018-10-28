﻿package kKitKits;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;

import api.MessageAPI;
import kCooldown.CooldownAPI;
import kKit.Kit;
import kKit.KitAPI;

public class Monk extends Kit implements Listener{
	
	public Monk() {
		//super("Monk", new String[]{"§7Confunda o inventario de seus inimigos."}, new ItemStack(Material.BLAZE_ROD), "kit.hulk",KitType.AVANCED);
		super("Monk", 2000, false,  new ItemStack(Material.BLAZE_ROD), true, "kit.monk", false, new String[]{" §e§l- §7Confunda o inventario de seus inimigos."});
	}
	
	 @EventHandler
	  public void monkHabilidade(PlayerInteractEntityEvent e) {
	    final Player p = e.getPlayer();

	    if ((e.getRightClicked() instanceof Player)){
	      Player jogadorClicado = (Player)e.getRightClicked();
        	 if ((p.getItemInHand().getType() == Material.BLAZE_ROD) && (KitAPI.getKitName(p) == "Monk") && (CooldownAPI.Cooldown.containsKey(p.getName()))){
         		e.setCancelled(true);
         		p.sendMessage(MessageAPI.Command_Error+"Você está com um Cooldown de §e" + CooldownAPI.Cooldown(p) + "s");
         		return;
        	 }
	      if ((p.getItemInHand().getType() == Material.BLAZE_ROD) && (KitAPI.getKitName(p) == "Monk") && (!CooldownAPI.Cooldown.containsKey(p.getName()))){
 
	              

	              int random = new Random().nextInt(jogadorClicado.getInventory().getSize() - 10 + 1 + 10);

	              ItemStack ItemSelecionado = jogadorClicado.getInventory().getItem(random);
	              ItemStack ItemMudado = jogadorClicado.getItemInHand();

	              jogadorClicado.setItemInHand(ItemSelecionado);
	              jogadorClicado.getInventory().setItem(random, ItemMudado);
	              jogadorClicado.sendMessage("§cVocê foi monkado.");
	              
	              CooldownAPI.addCooldown(p, 35);
	      }
	      return;
	            }
	          }
	  }
