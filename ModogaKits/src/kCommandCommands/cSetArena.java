package kCommandCommands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import api.MessageAPI;
import api.WarpAPI;
import br.alkazuz.groupapi.api.GroupAPI;

public class cSetArena implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender Sender, Command Cmd, String Label, String[] Args) {
		Player p = (Player)Sender;
		if(Cmd.getName().equalsIgnoreCase("setarena")){
			if(!GroupAPI.GroupCanExecute(GroupAPI.getGroup(p), Cmd.getName())){
				p.sendMessage(MessageAPI.Command_Error+"Comando inexistente ou você não tem acesso");
				return true;
		    }
			if(Args.length == 0){
				p.sendMessage(MessageAPI.Command_Error+"Use /setarena <1 até 5>");
				return true;
			}
			if (Args[0].equalsIgnoreCase("1")) {
				WarpAPI.Set(p, "Arena1");
				p.sendMessage(MessageAPI.Command_Succes+"§7Local setado com sucesso");
				return true;
			}
			if (Args[0].equalsIgnoreCase("2")) {
				WarpAPI.Set(p, "Arena2");
				p.sendMessage(MessageAPI.Command_Succes+"§7Local setado com sucesso");
				return true;
			}
			if (Args[0].equalsIgnoreCase("3")) {
				WarpAPI.Set(p, "Arena3");
				p.sendMessage(MessageAPI.Command_Succes+"§7Local setado com sucesso");
				return true;
			}
			if (Args[0].equalsIgnoreCase("4")) {
				WarpAPI.Set(p, "Arena4");
				p.sendMessage(MessageAPI.Command_Succes+"§7Local setado com sucesso");
				return true;
			}
			if (Args[0].equalsIgnoreCase("5")) {
				WarpAPI.Set(p, "Arena5");
				p.sendMessage(MessageAPI.Command_Succes+"§7Local setado com sucesso");
				return true;
			}
		}
		return false;
	}

}
