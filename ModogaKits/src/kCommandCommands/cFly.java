package kCommandCommands;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import api.MessageAPI;
import api.WarpAPI;
import br.alkazuz.groupapi.api.GroupAPI;

public class cFly implements CommandExecutor{
	
	public static ArrayList<Player> Fly = new ArrayList<Player>();
	
	public boolean onCommand(CommandSender Sender, Command Cmd, String Label, String[] Args) {
		
		Player p = (Player)Sender;
		
		if(Cmd.getName().equalsIgnoreCase("fly")){
			if(!GroupAPI.GroupCanExecute(GroupAPI.getGroup(p), Cmd.getName())){
				p.sendMessage(MessageAPI.Command_Error+"Comando inexistente ou você não tem acesso");
				return true;
		    }
			if(Args.length == 0 && WarpAPI.getWarp(p) == "Spawn"){
				if(!Fly.contains(p)){
					p.sendMessage(MessageAPI.Command_Succes+"Seu modo fly foi §2ativado");
					p.setAllowFlight(true);
					Fly.add(p);
					return true;
				}
					p.sendMessage(MessageAPI.Command_Succes+"Seu modo fly foi §4desativado");
					p.setAllowFlight(false);
					Fly.remove(p);
					return false;
				}else {
					p.sendMessage(MessageAPI.Command_Error+"Você só pode usar o Fly no Spawn");
				}
			if(Args.length > 0) {
			if((!GroupAPI.getGroup(p).equalsIgnoreCase("Dono") &&(!GroupAPI.getGroup(p).equalsIgnoreCase("Gerente") && (!GroupAPI.getGroup(p).equalsIgnoreCase("Administrador"))))){
				p.sendMessage(MessageAPI.Command_Error+"Comando inexistente ou você não tem acesso");
				return true;
		}
				Player target = Bukkit.getPlayer(Args[0]);
				  if ((target == null) || (!(target instanceof Player))){
					p.sendMessage(MessageAPI.Command_Error+"Esse player não está online");
					return true;
				}
				if(!Fly.contains(target)){
					target.sendMessage(MessageAPI.Command_Succes+"Seu modo fly foi §2ativado §apelo o player §e" + p.getName());
					p.sendMessage(MessageAPI.Command_Succes+"Você §2ativou §ao modo fly foi de §e" + target.getName());
					Fly.add(target);
					target.setAllowFlight(true);
				}else{
					target.sendMessage(MessageAPI.Command_Succes+"Seu modo fly foi §4desativado §apelo o player §4" + p.getName());
					p.sendMessage(MessageAPI.Command_Succes+"Você §4desativou §ao modo fly foi de §4" + target.getName());
					Fly.remove(target);
					target.setAllowFlight(false);
				}
			}
		}
		return false;
	}
}
