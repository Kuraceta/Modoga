package kCooldown;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import main.Main;

public class CooldownAPI {
	
	public static HashMap<String, Long> Cooldown = new HashMap<String, Long>();
	
	public static void addCooldown(Player jogador, int Tempo){
	    Cooldown.put(jogador.getName(), Long.valueOf(System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(Tempo)));
	    
	    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
			public void run() {
				tirarCooldown(jogador);
			}
		}, Tempo*20);
	    
	  }

	  public static void tirarCooldown(Player p){
	    Cooldown.remove(p.getName());
	  }

	  public static boolean SemCooldown(Player p){
	    if ((!Cooldown.containsKey(p.getName())) || (((Long)Cooldown.get(p.getName())).longValue() <= System.currentTimeMillis())) {
	      return true;
	    }
	    return false;
	  }

	  public static long Cooldown(Player p) {
	    long tempo = TimeUnit.MILLISECONDS.toSeconds(((Long)Cooldown.get(p.getName())).longValue() - System.currentTimeMillis());

	    if ((Cooldown.containsKey(p.getName())) || (((Long)Cooldown.get(p.getName())).longValue() > System.currentTimeMillis())){
	      return tempo;
	    }
	    return 0L;
	  }
}
