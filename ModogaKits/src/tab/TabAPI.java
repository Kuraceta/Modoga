package tab;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.spigotmc.ProtocolInjector;

import api.RankAPI;
import kKit.KitAPI;
import main.Main;
import net.minecraft.server.v1_7_R4.ChatSerializer;
import net.minecraft.server.v1_7_R4.IChatBaseComponent;
import net.minecraft.server.v1_7_R4.PlayerConnection;

public class TabAPI implements Listener{
	private static int VERSION = 47;

	  @EventHandler
	  void TabDoServidor(PlayerJoinEvent evento) {
		 Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), new Runnable()
	    {
	      public void run()
	      {
	    	  Player jogador = evento.getPlayer();
	        PlayerConnection connect = ((CraftPlayer)jogador).getHandle().playerConnection;
	        IChatBaseComponent top = ChatSerializer.a("{'extra': [{text: '', color: 'aqua'}],'color': gold, 'text': '              §2§lSpectrePvP §7Servidor de KitPvP          \n\n§fUm Conceito de diversão digitalizada\n'}");
			IChatBaseComponent bottom = ChatSerializer.a("{'extra': [{'color': 'aqua', 'text': '\n§8| §fKit: §7"+KitAPI.getKitName(jogador)+" §8| §fRank: "+RankAPI.getRank(jogador) +" §8| §fLiga: "+RankAPI.getRankName(jogador)+ " §8|', 'underline': 'true'}], 'color': 'gold', 'text': ''}");
	        if (((CraftPlayer)jogador).getHandle().playerConnection.networkManager.getVersion() < VERSION) {
	          return;
	        }
	        connect.sendPacket(new ProtocolInjector.PacketTabHeader(top, bottom));
	      }
	    }
	    , 3L, 20L);
	  }
}