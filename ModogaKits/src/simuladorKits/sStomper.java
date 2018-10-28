package simuladorKits;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import main.Main;
import simulador.EstadoHG;
import simulador.EventosAPI;
import simulador.KitAPI;

public class sStomper implements Listener, CommandExecutor
{
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String commandLabel, final String[] args) {
        final Player p = (Player)sender;
        if (commandLabel.equalsIgnoreCase("sstomper")) {
            if (!KitAPI.kits.contains(p.getName())) {
                    if (!EventosAPI.playerPlayingHG(p)) {
                        return true;
                    }
                    p.playSound(p.getLocation(), Sound.LEVEL_UP, 4.0f, 4.0f);
                    KitAPI.kits.add(p.getName());
                    KitAPI.stomper.add(p.getName());
                    p.sendMessage("§aVocê pegou o Kit §fStomper");
                    p.getInventory().clear();
            }
            else {
                p.sendMessage("§cVoce já selecionou um kit!");
            }
            return true;
        }
        return false;
    }
    
    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerStomp(final EntityDamageEvent e) {
        if (!(e.getEntity() instanceof Player)) {
            return;
        }
        final Player p = (Player)e.getEntity();
        if (e.getCause() != EntityDamageEvent.DamageCause.FALL) {
            return;
        }
        if (!KitAPI.stomper.contains(p.getName()) && !EventosAPI.playerPlayingHG(p)) {
            return;
        }
        for (final Entity ent : p.getNearbyEntities(4.0, 1.5, 4.0)) {
            if (ent instanceof Player) {
                final Player plr = (Player)ent;
                if(Main.EstadoHG == EstadoHG.INICIANDO || Main.EstadoHG == EstadoHG.INVENCIVEL)return;
                if (e.getDamage() <= 4.0) {
                    e.setCancelled(true);
                    return;
                }
                if (plr.isSneaking()) {
                    plr.damage(6.0, (Entity)p);
                    plr.sendMessage(ChatColor.RED + "Voce foi stompado por: " + ChatColor.DARK_RED + p.getName());
                }
                else {
                    plr.damage(e.getDamage(), (Entity)p);
                    plr.sendMessage(ChatColor.RED + "Voce foi stompado por: " + ChatColor.DARK_RED + p.getName());
                }
            }
        }
        e.setDamage(4.0);
    }

}
