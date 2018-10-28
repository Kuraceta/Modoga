package kCommandCommands;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.help.HelpTopic;

import api.MessageAPI;

public class ComandInexistente implements Listener
{
    @EventHandler
    public void onPlayerCommandPreprocess(final PlayerCommandPreprocessEvent e) {
    	
        if (!e.isCancelled()) {
            final Player player = e.getPlayer();
            final String cmd = e.getMessage().split(" ")[0];
            final HelpTopic topic = Bukkit.getServer().getHelpMap().getHelpTopic(cmd);
            if (topic == null) {
            	
                player.sendMessage(MessageAPI.Command_Error+"Comando inexistente ou você não tem acesso");
                e.setCancelled(true);
            }
        }
    }

}
