package kCommandCommands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import api.Account;
import api.MessageAPI;

public class cAccount implements CommandExecutor{
	
	@Override
	public boolean onCommand(CommandSender Sender, Command Cmd, String Label, String[] Args) {
		
		Player p = (Player)Sender;
		
		if(Cmd.getName().equalsIgnoreCase("acc")){
			if(Args.length == 0) {
				for(String n : new Account(p).getInfo()) {
					p.sendMessage(n);
				}
				return true;
			}else {
				Player pl = Bukkit.getPlayer(Args[0]);
				if(pl == null) {
					p.sendMessage(MessageAPI.Command_Error+"Jogador não econtrado");
					return true;
				}
				for(String n : new Account(pl).getInfo()) {
					p.sendMessage(n);
				}
			}
			return true;
		}
		return false;
	}
}
