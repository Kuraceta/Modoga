package simuladorKits;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

import main.Main;
import simulador.EstadoHG;
import simulador.EventosAPI;
import simulador.KitAPI;

public class sMonk implements Listener, CommandExecutor
{
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String commandLabel, final String[] args) {
        final Player p = (Player)sender;
        if (commandLabel.equalsIgnoreCase("smonk")) {
            if (!KitAPI.kits.contains(p.getName())) {
                    if (!EventosAPI.playerPlayingHG(p)) {
                        return true;
                    }
                    p.sendMessage("§aVocê pegou o kit §fMonk");
                    p.playSound(p.getLocation(), Sound.LEVEL_UP, 4.0f, 4.0f);
                    KitAPI.kits.add(p.getName());
                    KitAPI.monk.add(p.getName());
                    p.getInventory().clear();
                    final ItemStack grappl = new ItemStack(Material.BLAZE_ROD);
                    final ItemMeta grap = grappl.getItemMeta();
                    grap.setDisplayName("§6Monk");
                    grappl.setItemMeta(grap);
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
    public void monk(final PlayerInteractEntityEvent event) {
        if (!EventosAPI.playerPlayingHG(event.getPlayer())) {
            return;
        }
        if(Main.EstadoHG != EstadoHG.ANDAMENTO)return;
        final ItemStack item = event.getPlayer().getItemInHand();
        if (KitAPI.monk.contains(event.getPlayer().getName()) && event.getRightClicked() instanceof Player && item.getTypeId() ==  Material.BLAZE_ROD.getId()) {
            long lastUsed = 0L;
            if (KitAPI.monkStaff.containsKey(item)) {
                lastUsed = KitAPI.monkStaff.get(item);
            }
            if (lastUsed + 1000 * KitAPI.cooldownmonk > System.currentTimeMillis()) {
                event.getPlayer().sendMessage(String.format("§cVoce pode monkar denovo em %s segundos!", -((System.currentTimeMillis() - (lastUsed + 1000 * 15)) / 1000L)));
            }
            else {
                final PlayerInventory inv = ((Player)event.getRightClicked()).getInventory();
                final int slot = new Random().nextInt(36);
                ItemStack replaced = inv.getItemInHand();
                if (replaced == null) {
                    replaced = new ItemStack(0);
                }
                ItemStack replacer = inv.getItem(slot);
                if (replacer == null) {
                    replacer = new ItemStack(0);
                }
                inv.setItemInHand(replacer);
                inv.setItem(slot, replaced);
                KitAPI.monkStaff.put(item, System.currentTimeMillis());
                event.getPlayer().sendMessage("§1Monkado!");
            }
        }
    }

}
