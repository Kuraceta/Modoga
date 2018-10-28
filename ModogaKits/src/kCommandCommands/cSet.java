package kCommandCommands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import api.MessageAPI;
import api.WarpAPI;
import br.alkazuz.groupapi.api.GroupAPI;

public class cSet implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender Sender, Command Cmd, String Label, String[] Args) {
		Player p = (Player)Sender;
		if(Cmd.getName().equalsIgnoreCase("set")){
			if(!GroupAPI.GroupCanExecute(GroupAPI.getGroup(p), Cmd.getName())){
				p.sendMessage(MessageAPI.Command_Error+"Comando inexistente ou você não tem acesso");
				return true;
		    }
			if(Args.length == 0){
				p.sendMessage(MessageAPI.Command_Error+"Use /set <warp>");
				return true;
			}
			if (Args[0].equalsIgnoreCase("parkour")) {
				WarpAPI.Set(p, "Parkour");
				p.sendMessage(MessageAPI.Command_Succes+"§7Local §a{Parkour} §7setado com sucesso");
				return true;
			}
			if (Args[0].equalsIgnoreCase("mlg")) {
				WarpAPI.Set(p, "MLG");
				p.sendMessage(MessageAPI.Command_Succes+"§7Local §a{MLG} §7setado com sucesso");
				return true;
			}
			if (Args[0].equalsIgnoreCase("spawn")) {
				WarpAPI.Set(p, "Spawn");
				p.sendMessage(MessageAPI.Command_Succes+"§7Local §a{Spawn} §7setado com sucesso");
				return true;
			}
			if (Args[0].equalsIgnoreCase("1v1")) {
				WarpAPI.Set(p, "1v1");
				p.sendMessage(MessageAPI.Command_Succes+"§7Local §a{1v1} §7setado com sucesso");
				return true;
			}
			if (Args[0].equalsIgnoreCase("1v1loc1")) {
				WarpAPI.Set(p, "1v1Loc1");
				p.sendMessage(MessageAPI.Command_Succes+"§7Local §a{1v1Loc1} §7setado com sucesso");
				return true;
			}
			if (Args[0].equalsIgnoreCase("1v1loc2")) {
				WarpAPI.Set(p, "1v1Loc2");
				p.sendMessage(MessageAPI.Command_Succes+"§7Local §a{1v1Loc2} §7setado com sucesso");
				return true;
			}
			if (Args[0].equalsIgnoreCase("lava")) {
				WarpAPI.Set(p, "Lava");
				p.sendMessage(MessageAPI.Command_Succes+"§7Local §a{LavaChallenge} §7setado com sucesso");
				return true;
			}
			if (Args[0].equalsIgnoreCase("fps")) {
				WarpAPI.Set(p, "FPS");
				p.sendMessage(MessageAPI.Command_Succes+"§7Local §a{FPS} §7setado com sucesso");
				return true;
			}
			if (Args[0].equalsIgnoreCase("HGSpawn")) {
				WarpAPI.Set(p, "HGSpawn");
				p.sendMessage(MessageAPI.Command_Succes+"§7Local §a{HGSpawn} §7setado com sucesso");
				return true;
			}
			if (Args[0].equalsIgnoreCase("HGInicio")) {
				WarpAPI.Set(p, "HGInicio");
				p.sendMessage(MessageAPI.Command_Succes+"§7Local §a{HGInicio} §7setado com sucesso");
				return true;
			}
			if (Args[0].equalsIgnoreCase("RDMSpawn")) {
				WarpAPI.Set(p, "RDMSpawn");
				p.sendMessage(MessageAPI.Command_Succes+"§7Local §a{RDMSpawn} §7setado com sucesso");
				return true;
			}
			if (Args[0].equalsIgnoreCase("RDMLoc1")) {
				WarpAPI.Set(p, "RDMLoc1");
				p.sendMessage(MessageAPI.Command_Succes+"§7Local §a{RDMLoc1} §7setado com sucesso");
				return true;
			}
			if (Args[0].equalsIgnoreCase("RDMLoc2")) {
				WarpAPI.Set(p, "RDMLoc2");
				p.sendMessage(MessageAPI.Command_Succes+"§7Local §a{RDMLoc2} §7setado com sucesso");
				return true;
			}
			if (Args[0].equalsIgnoreCase("holograma")) {
				WarpAPI.Set(p, "Holograma");
				p.sendMessage(MessageAPI.Command_Succes+"§7Local §a{Holograma} §7setado com sucesso");
				return true;
			}
		}
		return false;
	}

}
