package hacks;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.messaging.PluginMessageListener;

public class AntiVape implements Listener, PluginMessageListener{
	
	@EventHandler
    public void onPlayerJoin(final PlayerJoinEvent event) {
        event.getPlayer().sendMessage("§8 §8 §1 §3 §3 §7 §8 ");
    }
    
    @Override
	public void onPluginMessageReceived(final String channel, final Player player, final byte[] data) {
        try {
             new String(data);
        }
        catch (Exception ex) {}
        Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "ban " + player.getPlayer().getName() + " Vape detectado!");
        Bukkit.broadcastMessage("§4§lBAN §ECONSOLE §Fbaniu o Jogador §c"+player.getName()+" §4§lPERMANENTEMENTE §Fpelo motivo §3\"Vape detectado!\"");
    }
}
