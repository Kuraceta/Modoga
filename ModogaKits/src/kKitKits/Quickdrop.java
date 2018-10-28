package kKitKits;

import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import kKit.Kit;

public class Quickdrop extends Kit implements Listener{

	public Quickdrop() {
		//super("Quickdrop", new String[] {" ","§7Drope suas pots automaticamente"},new ItemStack(Material.BOWL), "kit.Quickdrop",KitType.AVANCED);
		super("Quickdrop", 1300, false, new ItemStack(Material.BOWL), false, "kit.quickdrop", true, new String[] {" "," §e§l- Drope suas pots automaticamente"});
	}

}
