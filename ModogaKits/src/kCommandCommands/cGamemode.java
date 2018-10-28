package kCommandCommands;

import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import api.MessageAPI;
import br.alkazuz.groupapi.api.GroupAPI;

public class cGamemode implements CommandExecutor{
	
	public boolean onCommand(CommandSender Sender, Command Cmd, String Label, String[] Args) {
		
		Player p = (Player)Sender;
		
		if(Cmd.getName().equalsIgnoreCase("gamemode") || Cmd.getName().equalsIgnoreCase("gm")){
			if(!GroupAPI.GroupCanExecute(GroupAPI.getGroup(p), Cmd.getName())){
				p.sendMessage(MessageAPI.Command_Error+"Comando inexistente ou você não tem acesso");
				return true;
		    }

			if(Args.length == 0){
				p.sendMessage(MessageAPI.Command_Error+"Comando incorreto use §c[ /gamemode (1 | 0)");
				return true;
			}
			if(Args[0].equalsIgnoreCase("1")){
				
				p.setGameMode(GameMode.CREATIVE);
				p.sendMessage(MessageAPI.Command_Succes+"Seu gamemode foi alterado");
			}
			if(Args[0].equalsIgnoreCase("0")){
				
				p.setGameMode(GameMode.SURVIVAL);
				p.sendMessage(MessageAPI.Command_Succes+"Seu gamemode foi alterado");
			}
		}
		return false;
	}
}
