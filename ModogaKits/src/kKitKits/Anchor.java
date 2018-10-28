package kKitKits;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

import kKit.Kit;
import kKit.KitAPI;
import main.Main;

public class Anchor extends Kit implements Listener{
	
	public Anchor() {
		/*super("Anchor", new String[] {" ",
				"§7Faz você nao sofrer knockback",""
						+ "§7Você não dá knockback"},new ItemStack(Material.ANVIL), "kit.anchor",KitType.BASIC);*/
		super("Anchor", 1300, false, new ItemStack(Material.ANVIL), false, "kit.anchor", true, new String[] {" ",
				" §e§l⚊  §7Faz você nao sofrer knockback",""
						+ " §e§l⚊  §7Você não dá knockback"});
	}
    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerHitAnchor(final EntityDamageByEntityEvent e) {
        if (!(e.getEntity() instanceof Player)) {
            return;
        }
        if (!(e.getDamager() instanceof Player)) {
            return;
        }
        final Player p = (Player)e.getEntity();
        final Player a = (Player)e.getDamager();
        if (KitAPI.getKitName(p).equalsIgnoreCase("Anchor")) {
            p.setVelocity(new Vector());
            p.playSound(p.getLocation(), Sound.ANVIL_BREAK, 4.0f, 4.0f);
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask((Plugin)Main.getPlugin(), (Runnable)new Runnable() {
                @Override
                public void run() {
                    p.setVelocity(new Vector());
                }
            }, 1L);
        }
        if (KitAPI.getKitName(a).equalsIgnoreCase("Anchor")) {
            a.playSound(a.getLocation(), Sound.ANVIL_BREAK, 4.0f, 4.0f);
            p.setVelocity(new Vector());
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask((Plugin)Main.getPlugin(), (Runnable)new Runnable() {
                @Override
                public void run() {
                    p.setVelocity(new Vector());
                }
            }, 1L);
        }
    }

}
