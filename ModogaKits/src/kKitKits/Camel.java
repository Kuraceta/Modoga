package kKitKits;

import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

import api.API;
import kKit.Kit;
import kKit.KitAPI;

public class Camel extends Kit implements Listener{
	
	public Camel() {
		//super("Camel", new String[] {" ","§7Fique mais forte no deserto"},new ItemStack(Material.SAND), "kit.camel",KitType.BASIC);
		super("Camel", 1300, false, new ItemStack(Material.SAND), false, "kit.camel", true, new String[] {" "," §e§l- §7Fique mais forte no deserto"});
	}

	@EventHandler
    public void onPlayerCamel(final PlayerMoveEvent e) {
        final Player p = e.getPlayer();
        if ((e.getTo().getBlock().getRelative(BlockFace.DOWN).getType() == Material.SAND || e.getTo().getBlock().getRelative(BlockFace.DOWN).getType() == Material.SANDSTONE) && KitAPI.getKitName(p).equalsIgnoreCase("Camel")) {
        	API.darEfeito(p, PotionEffectType.INCREASE_DAMAGE, 3, 1);
            API.darEfeito(p, PotionEffectType.DAMAGE_RESISTANCE, 3, 1);
            API.darEfeito(p, PotionEffectType.SPEED, 4, 0);
        }
    }
	
}
