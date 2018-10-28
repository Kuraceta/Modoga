package kCommandCommands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import api.MessageAPI;
import br.alkazuz.groupapi.api.GroupAPI;
import npc.NPC;

public class cNPC implements CommandExecutor{
	
	
	@Override
	public boolean onCommand(CommandSender Sender, Command Cmd, String Label, String[] Args) {
		
		if(Cmd.getName().equalsIgnoreCase("npc")){
			Player p = (Player)Sender;
			
			if(!GroupAPI.GroupCanExecute(GroupAPI.getGroup(p), Cmd.getName())){
				p.sendMessage(MessageAPI.Command_Error+"Comando inexistente ou você não tem acesso");
				return true;
		    }
			if(Args[0].equalsIgnoreCase("caixas")) {
				NPC.createNPC(p.getLocation(), "§a§lCaixas §7- Clique");
				
			}
			if(Args[0].equalsIgnoreCase("conquistas")) {
				NPC.createNPC(p.getLocation(), "§e§lConquistas §7- Clique");
				
			}
			if(Args[0].equalsIgnoreCase("lojakits")) {
				NPC.createNPC(p.getLocation(), "§c§lLoja de Kits §7- Clique");
			}
			if(Args[0].equalsIgnoreCase("kitdiario")) {
				NPC.createNPC(p.getLocation(), "§d§lKit Diário §7- Clique");
			}
			if(Args[0].equalsIgnoreCase("ajuda")) {
				NPC.createNPC(p.getLocation(), "§a§lAjudante §7- Clique");
			}
		}
		return true;
	}

}
