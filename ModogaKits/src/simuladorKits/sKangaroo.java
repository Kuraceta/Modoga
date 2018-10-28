package simuladorKits;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

import simulador.EventosAPI;
import simulador.KitAPI;

public class sKangaroo implements Listener, CommandExecutor
{
    ArrayList<Player> kangaroo= new ArrayList<Player>();
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String commandLabel, final String[] args) {
        final Player p = (Player)sender;
        if (commandLabel.equalsIgnoreCase("skangaroo")) {
            if (!KitAPI.kits.contains(p.getName())) {
                    if (!EventosAPI.playerPlayingHG(p)) {
                        return true;
                    }
                    p.sendMessage("§aVocê pegou o Kit §fKangaroo");
                    p.playSound(p.getLocation(), Sound.LEVEL_UP, 4.0f, 4.0f);
                    KitAPI.kits.add(p.getName());
                    KitAPI.kangaroo.add(p.getName());
                    p.getInventory().clear();
                }else {
                    p.sendMessage("§cVoce ja contem um kit!");
                }
            return true;
            }
            return true;
    }
    
    @EventHandler
    public void onInteract(final PlayerInteractEvent event) {
        final Player p = event.getPlayer();
        if (p.getItemInHand().getType() == Material.FIREWORK) {
            if (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) {
                event.setCancelled(true);
                if (!EventosAPI.playerPlayingHG(p)) {
                    return;
                }
            }
            if (!this.kangaroo.contains(p)) {
                if (!p.isSneaking()) {
                    p.setFallDistance(-5.0f);
                    final Vector vector = p.getEyeLocation().getDirection();
                    vector.multiply(0.6f);
                    vector.setY(1.2f);
                    p.setVelocity(vector);
                }
                else {
                    p.setFallDistance(-5.0f);
                    final Vector vector = p.getEyeLocation().getDirection();
                    vector.multiply(1.2f);
                    vector.setY(0.8);
                    p.setVelocity(vector);
                }
                this.kangaroo.add(p);
            }
        }
    }
    
    @EventHandler
    public void onMove(final PlayerMoveEvent event) {
        final Player p = event.getPlayer();
        if (this.kangaroo.contains(p)) {
            final Block b = p.getLocation().getBlock();
            if (b.getType() != Material.AIR || b.getRelative(BlockFace.DOWN).getType() != Material.AIR) {
                this.kangaroo.remove(p);
            }
        }
    }
    
    @EventHandler
    public void onDamage(final EntityDamageEvent event) {
        final Entity e = event.getEntity();
        if (e instanceof Player) {
            final Player player = (Player)e;
            if (event.getEntity() instanceof Player && event.getCause() == EntityDamageEvent.DamageCause.FALL && player.getInventory().contains(Material.FIREWORK) && event.getDamage() >= 7.0) {
                event.setDamage(7.0);
            }
        }
    }

}
