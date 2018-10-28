package kCommandCommands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import api.MessageAPI;
import br.alkazuz.groupapi.api.GroupAPI;

public class cTpAll implements CommandExecutor
{

	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String Label, String[] args) {
		  Player p = (Player)sender;
		    if (cmd.getName().equalsIgnoreCase("tpall")){
		    	if(!GroupAPI.GroupCanExecute(GroupAPI.getGroup(p), cmd.getName())){
					p.sendMessage(MessageAPI.Command_Error+"Comando inexistente ou você não tem acesso");
					return true;
			    }
		      Player s = (Player)sender;
		      for (Player player : Bukkit.getServer().getOnlinePlayers()) {
		        player.teleport(s.getLocation());
		      }
		      Bukkit.getServer().broadcastMessage(MessageAPI.Command_Succes+"Todos os Jogadores foram Teleportados para §6"+p.getName());
		      return true;
		    }
		    return false;
		  }
	}

