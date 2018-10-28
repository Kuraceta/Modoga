package kKitKits;

import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import kKit.Kit;

public class AntiStomper extends Kit
  implements Listener
{
	
	public AntiStomper() {
		//super("Achilles", new String[]{" §e§l⚊ §7De mais dano segurando Shift."}, new ItemStack(Material.BONE), "kit.Achilles",KitType.BASIC);
	    super("AntiStomper", 1100, true, new ItemStack(Material.DIAMOND_BOOTS), false, "kit.antistomper", true, new String[]{" ","§fNão morra para Stomper's"});
	}
	
}
