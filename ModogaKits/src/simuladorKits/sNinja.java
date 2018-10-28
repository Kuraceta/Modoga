package simuladorKits;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;

import main.Main;
import simulador.EventosAPI;
import simulador.KitAPI;

public class sNinja implements Listener, CommandExecutor
{
    public HashMap<Player, Player> a=new HashMap<Player, Player>();
    public HashMap<Player, Long> b= new HashMap<Player, Long>();
    static ArrayList<Player> cooldownbk= new ArrayList<Player>();
   
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String commandLabel, final String[] args) {
        final Player p = (Player)sender;
        if (commandLabel.equalsIgnoreCase("sninja")) {
            if (!KitAPI.kits.contains(p.getName())) {
                    if (!EventosAPI.playerPlayingHG(p)) {
                        return true;
                    }
                    p.sendMessage("§aVocê pegou o Kit §fNinja");
                    p.playSound(p.getLocation(), Sound.LEVEL_UP, 4.0f, 4.0f);
                    KitAPI.kits.add(p.getName());
                    KitAPI.ninja.add(sender.getName());
                    p.getInventory().clear();
            }
            else {
                p.sendMessage("§cVoce ja contem um kit!");
            }
            return true;
        }
        return false;
    }
    
    @EventHandler
    public void a(final EntityDamageByEntityEvent paramEntityDamageByEntityEvent) {
        if (paramEntityDamageByEntityEvent.getDamager() instanceof Player && paramEntityDamageByEntityEvent.getEntity() instanceof Player) {
            final Player localPlayer1 = (Player)paramEntityDamageByEntityEvent.getDamager();
            final Player localPlayer2 = (Player)paramEntityDamageByEntityEvent.getEntity();
            if (KitAPI.ninja.contains(localPlayer1.getName())) {
                a.put(localPlayer1, localPlayer2);
                Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
                    @Override
                    public void run() {
                        cooldownbk.remove(localPlayer1);
                    }
                }, 200L);
            }
        }
    }
    
    @EventHandler
    public void a(final PlayerToggleSneakEvent paramPlayerToggleSneakEvent) {
        if (!EventosAPI.playerPlayingHG(paramPlayerToggleSneakEvent.getPlayer())) {
            return;
        }
        final Player localPlayer1 = paramPlayerToggleSneakEvent.getPlayer();
        if (paramPlayerToggleSneakEvent.isSneaking() && KitAPI.ninja.contains(localPlayer1.getName()) && a.containsKey(localPlayer1)) {
            final Player localPlayer2 = a.get(localPlayer1);
            if (localPlayer2 != null && !localPlayer2.isDead()) {
                String str = null;
                if (b.get(localPlayer1) != null) {
                    final long l = b.get(localPlayer1) - System.currentTimeMillis();
                    final DecimalFormat localDecimalFormat = new DecimalFormat("##");
                    final int i = (int)l / 1000;
                    str = localDecimalFormat.format(i);
                }
                if (b.get(localPlayer1) == null || b.get(localPlayer1) < System.currentTimeMillis()) {
                    if (localPlayer1.getLocation().distance(localPlayer2.getLocation()) < 100.0) {
                        localPlayer1.teleport(localPlayer2.getLocation());
                        localPlayer1.sendMessage(ChatColor.GREEN + "Teleportado");
                        b.put(localPlayer1, System.currentTimeMillis() + 10000L);
                    }
                    else {
                        localPlayer1.sendMessage(ChatColor.RED + "O Ultimo jogador hitado esta muito longe!");
                    }
                }
                else {
                    localPlayer1.sendMessage(ChatColor.RED + "Ninja em cooldown de " + str + " segundos!");
                }
            }
        }
    }

}
