package kCommandCommands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import api.MessageAPI;
import br.alkazuz.groupapi.api.GroupAPI;

public class cChat implements CommandExecutor, Listener{
	
	  public static boolean pausado = false;
	@Override
	public boolean onCommand(CommandSender Sender, Command Cmd, String Label, String[] Args) {
		
		if(Cmd.getName().equalsIgnoreCase("chat")){
			Player p = (Player)Sender;
			
			if(!GroupAPI.GroupCanExecute(GroupAPI.getGroup(p), Cmd.getName())){
				p.sendMessage(MessageAPI.Command_Error+"Comando inexistente ou você não tem acesso");
				return true;
		    }
			
			if(Args.length == 0){
				p.sendMessage(MessageAPI.Command_Error+"Comando incorreto use §c[ /chat (mute : desmute : clear) ]");
				return true;
			}
			if(Args[0].equalsIgnoreCase("clear")){
				for(int i = 0; i < 100; i++){
					Bukkit.broadcastMessage(" ");
				}
				Bukkit.broadcastMessage("§aChat foi limpo por §6"+p.getName());
			}else{
				
				if(Args[0].equalsIgnoreCase("mute")){
					Bukkit.getServer().broadcastMessage(MessageAPI.Command_Error+"O Chat do servidor foi §4pausado.");
					pausado = true;
			}else{
				if(Args[0].equalsIgnoreCase("desmute")){
					Bukkit.getServer().broadcastMessage(MessageAPI.Command_Succes+"O Chat do servidor foi §2liberado.");
					pausado = false;
					}
				}
			}
		}
		return true;
		}
			 @EventHandler
			  public void onChat(AsyncPlayerChatEvent e){
			  {
			    Player p = e.getPlayer();
			    if ((pausado) && GroupAPI.getGroup(p).equalsIgnoreCase("Membro")){
			      e.setCancelled(true);
			      p.sendMessage("§7O Chat do servidor está §cpausado.");
			    }
		  }
	 }
}
