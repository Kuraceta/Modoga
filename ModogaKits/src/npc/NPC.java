package npc;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Villager;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class NPC {
	
	public static void createNPC(Location loc, String nome) {
		Entity ent = loc.getWorld().spawnEntity(loc, EntityType.VILLAGER);
		((Villager)ent).setCustomName(nome);
		((Villager)ent).setCustomNameVisible(true);
		((LivingEntity) ent).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, Integer.MAX_VALUE, 6776767));
	}

}
