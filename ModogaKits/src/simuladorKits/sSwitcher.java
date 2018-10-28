package simuladorKits;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import simulador.EventosAPI;
import simulador.KitAPI;

public class sSwitcher implements Listener, CommandExecutor
{
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String commandLabel, final String[] args) {
        final Player p = (Player)sender;
        if (commandLabel.equalsIgnoreCase("sswitcher")) {
            if (!KitAPI.kits.contains(p.getName())) {
                    if (!EventosAPI.playerPlayingHG(p)) {
                        return true;
                    }
                    p.sendMessage("§aVocê pegou o kit §fSwitcher");
                    p.playSound(p.getLocation(), Sound.LEVEL_UP, 4.0f, 4.0f);
                    KitAPI.kits.add(p.getName());
                    KitAPI.switcher.add(sender.getName());
                    p.getInventory().clear();
                    p.getInventory().setHelmet(new ItemStack(Material.REDSTONE_BLOCK));
                }
            else {
                p.sendMessage("§cVoce ja contem um kit!");
            }
            return true;
        }
        return false;
    }
    
    @SuppressWarnings("deprecation")
	@EventHandler
    public void snowball(final EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Snowball && e.getEntity() instanceof Player) {
            if (!EventosAPI.playerPlayingHG((Player) e.getEntity())) {
                return;
            }
            final Snowball s = (Snowball)e.getDamager();
            if (s.getShooter() instanceof Player) {
                final Player shooter = (Player)s.getShooter();
                final Location shooterLoc = shooter.getLocation();
                shooter.teleport(e.getEntity().getLocation());
                e.getEntity().teleport(shooterLoc);
            }
        }
    }

}
