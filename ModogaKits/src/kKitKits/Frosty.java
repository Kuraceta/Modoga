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

public class Frosty extends Kit implements Listener{

	public Frosty() {
		super("Frosty", 1200, false, new ItemStack(Material.PACKED_ICE), false, "kit.frosty", true, new String[] {" ","§e§l- §7Fique mais forte no gelo"});
	}

	@EventHandler
    public void onPlayerCamel(final PlayerMoveEvent e) {
        final Player p = e.getPlayer();
        if ((e.getTo().getBlock().getRelative(BlockFace.DOWN).getType() == Material.SNOW_BLOCK ||e.getTo().getBlock().getRelative(BlockFace.DOWN).getType() == Material.PACKED_ICE|| e.getTo().getBlock().getRelative(BlockFace.DOWN).getType() == Material.ICE) && KitAPI.getKitName(p).equalsIgnoreCase("Frosty")) {
        	API.darEfeito(p, PotionEffectType.INCREASE_DAMAGE, 3, 1);
            API.darEfeito(p, PotionEffectType.DAMAGE_RESISTANCE, 3, 1);
            API.darEfeito(p, PotionEffectType.SPEED, 4, 0);
        }
    }
	
}
