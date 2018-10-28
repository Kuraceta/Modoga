package comandos;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import api.GroupAPI;
import api.MessageAPI;
import config.Config;
import main.Main;

public class cGroupos implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender Sender, Command Cmd, String Label, String[] Args) {
		if(Cmd.getName().equalsIgnoreCase("grupos")){
			if(Sender instanceof Player && !GroupAPI.GroupCanExecute(GroupAPI.getGroup((Player)Sender), Cmd.getName())){
				Sender.sendMessage(MessageAPI.Command_Error+"Comando inexistente ou você não tem acesso");
				return true;
		    }
			new Config(Main.getPlugin());
			GroupAPI.loadGroups();
			Sender.sendMessage("§aConfig recarregada");
		}
		return true;
	}

}
