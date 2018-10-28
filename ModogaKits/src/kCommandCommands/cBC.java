package kCommandCommands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import api.MessageAPI;
import br.alkazuz.groupapi.api.GroupAPI;

public class cBC implements CommandExecutor, Listener{
	@Override
	public boolean onCommand(CommandSender Sender, Command Cmd, String Label, String[] Args) {
		
		if(Cmd.getName().equalsIgnoreCase("bc")){
			Player p = (Player)Sender;
			if(!GroupAPI.GroupCanExecute(GroupAPI.getGroup(p), Cmd.getName())){
				p.sendMessage(MessageAPI.Command_Error+"Comando inexistente ou você não tem acesso");
				return true;
		    }
			String message = " ";
	        for (int i = 0; i < Args.length; i++) {
	          if (i == Args.length - 1) {
	            message = message + Args[i];
	          } else {
	            message = message + Args[i] + " ";
	          }
	        }
	        Bukkit.broadcastMessage(" ");
			Bukkit.broadcastMessage("§e§lBroadCast §b» §f§o"+message.replace("&", "§"));
			Bukkit.broadcastMessage(" ");
		}
		return true;
	}

}
