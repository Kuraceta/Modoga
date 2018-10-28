package mlg;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import api.MessageAPI;
import api.WarpAPI;
import kConfig.WarpConfig;
import main.Main;
import score.ScoreBoarding;

public class MLGEvents implements Listener{
	
	public static ArrayList<Player> acertou = new ArrayList<Player>();
	
	@EventHandler
	public void fewf(EntityDamageEvent e) {
		if(e.getEntity() instanceof Player) {
			Player p = (Player)e.getEntity();
			if(WarpAPI.getWarp(p).equalsIgnoreCase("MLG")) {
				if (e.getCause() == EntityDamageEvent.DamageCause.FALL) {
					if(acertou.contains(p))return;
					p.sendMessage(MessageAPI.Command_Error+"Você errou o MLG");
					MLGAPI.addErros(p);
					MLGAPI.clearKs(p);
					ScoreBoarding.setScoreBoard(p);
					WarpAPI.setWarp(p, "MLG");
					e.setCancelled(true);
				}
			}
		}
	}
	
	@EventHandler(priority = EventPriority.HIGH)
    public void a(final PlayerInteractEvent e) {
        final Player p = e.getPlayer();
        if (WarpAPI.getWarp(p).equalsIgnoreCase("MLG") && e.getAction() == Action.RIGHT_CLICK_BLOCK && e.getClickedBlock().getLocation().getBlockY() > p.getLocation().getBlockY() + 2) {
            e.setCancelled(true);
            p.updateInventory();
        }
    }
	
	@EventHandler
	public void wefwef(PlayerBucketEmptyEvent e) {
		Player p = e.getPlayer();
		if(!WarpAPI.getWarp(e.getPlayer()).equalsIgnoreCase("MLG"))return;
		double y = WarpConfig.getConfig().getDouble("Warps.MLG.Y");
		e.setCancelled(true);
        p.updateInventory();
		if(e.getBucket() == Material.WATER_BUCKET &&e.getBlockFace() == BlockFace.UP && e.getPlayer().getLocation().getY() + 2 < WarpConfig.getConfig().getInt("Warps.MLG.Y") && (int)p.getLocation().getY()!= (int)y) {
			if (p.getLocation().subtract(0.0, 3.0, 0.0).getBlock().getType() != Material.AIR) {
			e.getPlayer().sendMessage(MessageAPI.Command_Succes+"Você acertou o MLG");
			WarpAPI.setWarp(e.getPlayer(), "MLG");
			MLGAPI.addAcertos(p);
			MLGAPI.addKs(p, 1);
			ScoreBoarding.setScoreBoard(p);
			acertou.add(p);
			Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
				public void run() {
					acertou.remove(p);
				}
			}, 20l);
			}
		}
	}

}
