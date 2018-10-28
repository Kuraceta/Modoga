package kKitKits;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Bat;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import api.MessageAPI;
import kCooldown.CooldownAPI;
import kKit.Kit;
import kKit.KitAPI;
import main.Main;

public class Batman extends Kit implements Listener{
	
	public Batman() {
		/*super("Batman", new String[] {" ","§7Atrapalhe seus inimigos com Morcegos"},
				new ItemStack(Material.MONSTER_EGG, 1, 
						(short)EntityType.BAT.ordinal()), 
				"kit.batman",KitType.BASIC);*/
		super("Batman", 1000, false, new ItemStack(Material.MONSTER_EGG, 1, 
						(short)EntityType.BAT.ordinal()), true, "kit.batman", true, new String[] {" "," §e§l- §7Atrapalhe seus inimigos com Morcegos"});
	}
	
	@EventHandler
	public void batmanHabilidade(PlayerInteractEntityEvent evento) {
		final Player jogador = evento.getPlayer();
		if (KitAPI.getKitName(jogador).equalsIgnoreCase("Batman")) {
			if (jogador.getItemInHand().getType().equals(Material.MONSTER_EGG)) {
				if ((evento.getRightClicked() instanceof Player)) {
					if (CooldownAPI.Cooldown.containsKey(jogador.getName())) {
						jogador.sendMessage(MessageAPI.Command_Error+"Você está com um Cooldown de §e" + CooldownAPI.Cooldown(jogador) + "s");
	                    return;
	                }
					CooldownAPI.addCooldown(jogador, 45);
					final Bat bat1 = (Bat) Bukkit.getWorld(jogador.getWorld().getName())
							.spawnEntity(jogador.getEyeLocation(), EntityType.BAT);
					final Bat bat2 = (Bat) Bukkit.getWorld(jogador.getWorld().getName())
							.spawnEntity(jogador.getEyeLocation(), EntityType.BAT);
					final Bat bat3 = (Bat) Bukkit.getWorld(jogador.getWorld().getName())
							.spawnEntity(jogador.getEyeLocation(), EntityType.BAT);

					final Bat bat4 = (Bat) Bukkit.getWorld(jogador.getWorld().getName())
							.spawnEntity(jogador.getEyeLocation(), EntityType.BAT);
					final Bat bat5 = (Bat) Bukkit.getWorld(jogador.getWorld().getName())
							.spawnEntity(jogador.getEyeLocation(), EntityType.BAT);
					final Bat bat6 = (Bat) Bukkit.getWorld(jogador.getWorld().getName())
							.spawnEntity(jogador.getEyeLocation(), EntityType.BAT);

					final Bat bat7 = (Bat) Bukkit.getWorld(jogador.getWorld().getName())
							.spawnEntity(jogador.getEyeLocation(), EntityType.BAT);
					final Bat bat8 = (Bat) Bukkit.getWorld(jogador.getWorld().getName())
							.spawnEntity(jogador.getEyeLocation(), EntityType.BAT);
					final Bat bat9 = (Bat) Bukkit.getWorld(jogador.getWorld().getName())
							.spawnEntity(jogador.getEyeLocation(), EntityType.BAT);
					bat1.setVelocity(jogador.getLocation().getDirection().multiply(0.5D));
					bat2.setVelocity(jogador.getLocation().getDirection().multiply(0.5D));
					bat3.setVelocity(jogador.getLocation().getDirection().multiply(0.5D));

					bat4.setVelocity(jogador.getLocation().getDirection().multiply(0.5D));
					bat5.setVelocity(jogador.getLocation().getDirection().multiply(0.5D));
					bat6.setVelocity(jogador.getLocation().getDirection().multiply(0.5D));

					bat7.setVelocity(jogador.getLocation().getDirection().multiply(0.5D));
					bat8.setVelocity(jogador.getLocation().getDirection().multiply(0.5D));
					bat9.setVelocity(jogador.getLocation().getDirection().multiply(0.0D));

					bat1.setCustomNameVisible(false);
					bat2.setCustomNameVisible(false);
					bat3.setCustomNameVisible(false);
					bat4.setCustomNameVisible(false);
					bat5.setCustomNameVisible(false);
					bat6.setCustomNameVisible(false);
					bat7.setCustomNameVisible(false);
					bat8.setCustomNameVisible(false);
					bat9.setCustomNameVisible(false);
					for (Entity entidades : jogador.getNearbyEntities(4.0D, 4.0D, 4.0D)) {
						if ((entidades instanceof Player)) {
							Player jogadores = (Player) entidades;

							jogadores.damage(4.0D, jogador);
							jogadores.setVelocity(new Vector(0.0D, 1.7D, 0.0D));
						}
					}
					jogador.getWorld().playSound(jogador.getLocation(), Sound.BAT_HURT, 5.0F, 5.0F);

					Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
						public void run() {
							jogador.getWorld().createExplosion(bat1.getLocation(), 2.0F);
							jogador.getWorld().createExplosion(bat2.getLocation(), 2.0F);
							jogador.getWorld().createExplosion(bat3.getLocation(), 2.0F);
							jogador.getWorld().createExplosion(bat4.getLocation(), 2.0F);
							jogador.getWorld().createExplosion(bat5.getLocation(), 2.0F);
							jogador.getWorld().createExplosion(bat6.getLocation(), 2.0F);
							jogador.getWorld().createExplosion(bat7.getLocation(), 2.0F);
							jogador.getWorld().createExplosion(bat8.getLocation(), 2.0F);
							jogador.getWorld().createExplosion(bat9.getLocation(), 2.0F);
							bat1.remove();
							bat2.remove();
							bat3.remove();
							bat4.remove();
							bat5.remove();
							bat6.remove();
							bat7.remove();
							bat8.remove();
							bat9.remove();
						}
					}, 60L);
				}
			}
		}
	}

}
