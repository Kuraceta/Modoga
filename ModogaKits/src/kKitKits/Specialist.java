package kKitKits;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ExpBottleEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import kKit.Kit;
import kKit.KitAPI;

public class Specialist extends Kit implements Listener{
	
	public Specialist() {
		/*super("Specialist", new String[] {" ","§7Ao matar você ganha XP","§7Para encantar sua Espada"},
				new ItemStack(Material.ENCHANTED_BOOK), "kit.specialist",KitType.BASIC);*/
		super("Specialist", 2300, false, new ItemStack(Material.ENCHANTED_BOOK), true, "kit.specialist", true, new String[] {" "," §e§l- §7Ao matar você ganha XP","§7Para encantar sua Espada"});
	}

	@EventHandler
    public void usarLivro(final PlayerInteractEvent e) {
        final Player player = e.getPlayer();
        if (KitAPI.getKitName(player).equalsIgnoreCase("Specialist") && e.getAction().name().contains("RIGHT") && player.getItemInHand().getType() == Material.ENCHANTED_BOOK) {
            player.openEnchanting((Location)null, true);
        }
    }
	
    @EventHandler
    public void darXp(final PlayerDeathEvent e) {
        final Player killer = e.getEntity().getKiller();
        if (e.getEntity() instanceof Player && e.getEntity().getKiller() instanceof Player && KitAPI.getKitName(killer).equalsIgnoreCase("Specialist")) {
            killer.sendMessage("§aVoc\u00ea ganhou 1 XP por matar!");
            killer.getInventory().addItem(new ItemStack[] { new ItemStack(Material.EXP_BOTTLE, 1) });
        }
    }
    @SuppressWarnings("deprecation")
	@EventHandler
    public void specialist(final ExpBottleEvent event) {
        if (event.getEntity().getShooter() instanceof Player) {
            final Player p = (Player)event.getEntity().getShooter();
            if (KitAPI.getKitName(p).equalsIgnoreCase("Specialist")) {
                event.setExperience(0);
                p.setLevel(p.getLevel() + 1);
            }
        }
    }
	
}
