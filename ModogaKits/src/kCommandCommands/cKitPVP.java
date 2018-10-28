package kCommandCommands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import api.MessageAPI;
import br.alkazuz.groupapi.api.GroupAPI;
import br.alkazuz.groupapi.config.GroupConfig;
import kConfig.ConquistaConfig;
import kConfig.KitDiario;
import kConfig.KitsConfig;
import kConfig.MLGConfig;
import kConfig.WarpConfig;
import main.Main;

public class cKitPVP implements CommandExecutor{
	
	@Override
	public boolean onCommand(CommandSender Sender, Command Cmd, String Label, String[] Args) {
		
		if(Cmd.getName().equalsIgnoreCase("reloadconfig")){
			
			if(Sender instanceof Player && !GroupAPI.GroupCanExecute(GroupAPI.getGroup((Player	)Sender), Cmd.getName())){
				Sender.sendMessage(MessageAPI.Command_Error+"Comando inexistente ou você não tem acesso");
				return true;
		    }
			
			new kConfig.Config(Main.getPlugin());
			new GroupConfig(Main.getPlugin());
			new WarpConfig(br.alkazuz.groupapi.main.Main.getPlugin());
			new KitDiario(Main.getPlugin());
			new ConquistaConfig(Main.getPlugin());
			new MLGConfig(Main.getPlugin());
			new KitsConfig(Main.getPlugin());
			Sender.sendMessage(MessageAPI.Command_Succes+"Config recarregada.");
			
		}
		return false;
	}

}
