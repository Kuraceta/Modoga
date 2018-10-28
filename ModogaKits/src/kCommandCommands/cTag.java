package kCommandCommands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import api.MessageAPI;
import api.NameTagAPI;
import br.alkazuz.groupapi.api.GroupAPI;

public class cTag implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender Sender, Command Cmd, String Label, String[] Args) {
		Player p = (Player)Sender;
		if(Cmd.getName().equalsIgnoreCase("tag")){
			if(Args.length == 0){
				p.sendMessage(MessageAPI.Command_Error+"Use /tag <tag> use /tags para a lista de Tags.");
				return true;
			}
			String tag = GroupAPI.getGroup(Args[0]);
			
			if(tag == null){
				p.sendMessage(MessageAPI.Command_Error+"Essa tag não existe");
				return true;
			}
			if(!GroupAPI.getGroup(p).equalsIgnoreCase(tag)&&!GroupAPI.getGroup(p).equalsIgnoreCase("Dono")&&!tag.equalsIgnoreCase("Membro")){
				p.sendMessage(MessageAPI.Command_Error+"Você não possui essa tag");
				return true;
			}
			NameTagAPI.setTag(p, tag);
			p.sendMessage(MessageAPI.Command_Succes+"§7Sua tag foi alterada para "+GroupAPI.getColor(tag)+tag);
		}
		if(Cmd.getName().equalsIgnoreCase("tags")){
			String grupos = "";
			for(String tags : GroupAPI.grupos){
				if(GroupAPI.getGroup(p).equalsIgnoreCase(tags)){
					grupos += GroupAPI.getColor(tags)+tags;
				}
			}
			p.sendMessage(MessageAPI.Command_Succes+"Suas Tags: "+grupos+(GroupAPI.getGroup(p).equalsIgnoreCase("Membro")? "":", §fMembro"));
		}
		return false;
	}

}
