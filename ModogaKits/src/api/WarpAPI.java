package api;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import kCommandCommands.cFly;
import kConfig.WarpConfig;
import protection.Protection;

public class WarpAPI {

	public static HashMap<Player, String> warp = new HashMap<Player, String>();
	
	public static String getWarp(Player p) {
			return warp.get(p);
	}
	
	public static void Set(Player p,String Warp){
		WarpConfig.getConfig().set("Warps." + Warp + ".X", p.getLocation().getX());
		WarpConfig.getConfig().set("Warps." + Warp + ".Y", p.getLocation().getY());
		WarpConfig.getConfig().set("Warps." + Warp + ".Z", p.getLocation().getZ());
		WarpConfig.getConfig().set("Warps." + Warp + ".Pitch", p.getLocation().getPitch());
		WarpConfig.getConfig().set("Warps." + Warp + ".Yaw", p.getLocation().getYaw());
		WarpConfig.getConfig().set("Warps." + Warp + ".World", p.getLocation().getWorld().getName());
		WarpConfig.saveConfigs();
	}
	
	public static void sWarp(Player p, String Warp){
		warp.put(p, Warp);
	}
	
	public static void setWarp(Player p, String Warp){
		if(cFly.Fly.contains(p) && p.getGameMode() != GameMode.CREATIVE) {
        	cFly.Fly.remove(p);
        	p.setAllowFlight(false);
        	p.setFlying(false);
        }
		warp.put(p, Warp);
		if(Warp.equalsIgnoreCase("spawn")) {
			Protection.setImortal(p, true);
			API.sendItems(p);
		}
		if(WarpConfig.getConfig().get("Warps." + Warp) != null){
			double x = WarpConfig.getConfig().getDouble("Warps." + Warp + ".X");
			double y = WarpConfig.getConfig().getDouble("Warps." + Warp + ".Y");
			double z = WarpConfig.getConfig().getDouble("Warps." + Warp + ".Z");
			float Pitch = (float)WarpConfig.getConfig().getDouble("Warps." + Warp + ".Pitch");
			float Yaw = (float)WarpConfig.getConfig().getDouble("Warps." + Warp + ".Yaw");
			World world = Bukkit.getWorld(WarpConfig.getConfig().getString("Warps." + Warp + ".World"));
			
			p.teleport(new Location(world, x, y, z, Yaw, Pitch));
		}else {
			p.sendMessage(MessageAPI.Command_Error+"§cEsta warp ainda não esta setada.");
		}
	}
}
