package kKitKits;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;

import kKit.Kit;
import kKit.KitAPI;

public class Fisherman extends Kit implements Listener{
	
	public Fisherman() {
		/*super("Fisherman", new String[] {" ","§7Pesque seus inimigos usando uma vara de pescar."}
		,new ItemStack(Material.FISHING_ROD), "kit.Fisherman",KitType.BASIC);*/
		super("Fisherman", 0, true, new ItemStack(Material.FISHING_ROD), true, "kit.fisherman", true, new String[] {" ","§e§l- §7Pesque seus inimigos usando uma vara de pescar."});
	}
	
	@EventHandler
    public void onPlayerFish(final PlayerFishEvent event) {
        final Entity caught = event.getCaught();
        final Block block = event.getHook().getLocation().getBlock();
        if (caught != null && caught != block && KitAPI.getKitName(event.getPlayer()).equalsIgnoreCase(this.getName())) {
            caught.teleport(event.getPlayer().getLocation());
        }
    }
}
