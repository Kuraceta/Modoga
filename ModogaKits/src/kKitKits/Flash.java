package kKitKits;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import api.MessageAPI;
import kCooldown.CooldownAPI;
import kKit.Kit;
import kKit.KitAPI;

public class Flash extends Kit implements Listener{
	
	public Flash() {
		//super("Flash", new String[] {" ","§7Coloque fogo em seus inimigos."},new ItemStack(Material.FEATHER), "kit.Flash",KitType.BASIC);
		super("Flash", 1100, false, new ItemStack(Material.FEATHER), true, "kit.flash", true, new String[] {""," §e§l- §7Roube a velocidade dos inimos ao redor"});
	}
	
    @EventHandler
    public void onFlashUse(final PlayerInteractEvent e) {
        final Player p = e.getPlayer();
        if (KitAPI.getKitName(p)=="Flash" && (e.getAction() == Action.RIGHT_CLICK_AIR&& p.getItemInHand().getType() == Material.FEATHER || (e.getAction() == Action.RIGHT_CLICK_BLOCK && p.getItemInHand().getType() == Material.FEATHER))) {
            if (Gladiator.noExecut.contains(p)) {
                p.sendMessage("§7Você não pode usar o Kit no §cGladiator §7então você irá aganhar um efeito de §cforça §7e §cspeed");
                p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 10, 1));
                p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 10, 2));
            }
            else {
                e.setCancelled(true);
                if (CooldownAPI.Cooldown.containsKey(p.getName())) {
                	p.sendMessage(MessageAPI.Command_Error+"Você está com um Cooldown de §e" + CooldownAPI.Cooldown(p) + "s");
                    return;
                }
                CooldownAPI.addCooldown(p, 40);
                p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 200, 2));
                for (final Entity pertos : p.getNearbyEntities(6.0, 6.0, 6.0)) {
                	if(!(pertos instanceof Player))continue;
                    ((Player)pertos).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 200, 2));
                    ((Player)pertos).addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 200, 2));
                    ((Player)pertos).sendMessage("§c» §6"+p.getName()+"§7 roubou sua velocidade");
                }
                p.sendMessage("§a» §7Você roubou a velocidade dos Players perto de Você");
            }
        }
    }

}
