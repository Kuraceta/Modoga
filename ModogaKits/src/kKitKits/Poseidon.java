package kKitKits;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

import api.API;
import kKit.Kit;
import kKit.KitAPI;

public class Poseidon extends Kit implements Listener{
	
	public Poseidon() {
		//super("Poseidon", new String[]{"§7Fique forte na água"}, new ItemStack(Material.WATER_BUCKET), "kit.Poseidon",KitType.AVANCED);
		super("Poseidon", 4000, false, new ItemStack(Material.WATER_BUCKET), false, "kit.phantom", false, new String[]{" §e§l- §7Fique forte na água"});
	}
    @EventHandler
    public void aoPoseidon(final PlayerMoveEvent e) {
        final Player p = e.getPlayer();
        final Block b = p.getLocation().getBlock();
        if (KitAPI.getKitName(p).equalsIgnoreCase("Poseidon") && (b.getType() == Material.WATER || b.getType() == Material.STATIONARY_WATER)) {
            API.darEfeito(p, PotionEffectType.INCREASE_DAMAGE, 3, 1);
            API.darEfeito(p, PotionEffectType.DAMAGE_RESISTANCE, 3, 1);
            API.darEfeito(p, PotionEffectType.SPEED, 4, 0);
        }
    }

}
