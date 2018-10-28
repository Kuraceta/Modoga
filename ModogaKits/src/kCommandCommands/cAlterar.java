package kCommandCommands;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import api.MessageAPI;
import br.alkazuz.groupapi.api.GroupAPI;
import mysqlManager.MySQLFunctions;
import mysqlManager.Status;

public class cAlterar implements CommandExecutor, Listener{
	
	public static boolean inInteger(String text){
		try {
			Integer.parseInt(text);
			return true;
		} catch(NumberFormatException e){
			return false;
		}
	}
	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender Sender, Command Cmd, String Label, String[] Args) {
		
		Player p = (Player)Sender;
		
		if(Cmd.getName().equalsIgnoreCase("alterar")){
			
			if(!GroupAPI.GroupCanExecute(GroupAPI.getGroup(p), Cmd.getName())){
				p.sendMessage(MessageAPI.Command_Error+"Comando inexistente ou você não tem acesso");
				return true;
		    }
			if(Args.length <= 2) {
				Sender.sendMessage(MessageAPI.Command_Error+"Use: /alterar <Kill/Morte> <Player> <Quantidade>");
				return true;
			}
			
			OfflinePlayer target = Bukkit.getOfflinePlayer(Args[1]);
			if(!inInteger(Args[2])){
				Sender.sendMessage(MessageAPI.Command_Error+"Você só pode usar números");
				return true;
			}
			if(Args[0].equalsIgnoreCase("kill")) {
			    if(target.isOnline()) {
			    	Player target2 = Bukkit.getPlayer(Args[1]);
			    	Status.setKills(target2, Integer.parseInt(Args[2]));
			    }
			    MySQLFunctions.setKills(target,Integer.parseInt(Args[2]));
			    Sender.sendMessage(MessageAPI.Command_Succes+"Informação alterada com Sucesso");
			    return true;
			}
			if(Args[0].equalsIgnoreCase("death")) {
			    if(target.isOnline()) {
			    	Player target2 = Bukkit.getPlayer(Args[1]);
			    	Status.setDeath(target2, Integer.parseInt(Args[2]));
			    }
			    MySQLFunctions.setDeath(target,Integer.parseInt(Args[2]));
			    Sender.sendMessage(MessageAPI.Command_Succes+"Informação alterada com Sucesso");
			    return true;
			}
		}
		return false;
	}
}
