package kCommandCommands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import api.MessageAPI;
import br.alkazuz.groupapi.api.GroupAPI;

public class cLimpar implements CommandExecutor{
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender Sender, Command Cmd, String Label, String[] Args) {
		
		Player p = (Player)Sender;
		if(Cmd.getName().equalsIgnoreCase("limpar")){
			
			if(!GroupAPI.GroupCanExecute(GroupAPI.getGroup(p), Cmd.getName())){
				p.sendMessage(MessageAPI.Command_Error+"Comando inexistente ou você não tem acesso");
				return true;
		    }
			Bukkit.broadcastMessage(MessageAPI.Command_Succes + "Executando limpeza do Servidor, o servidor pode travar, mas voltará");
			final Runtime r2 = Runtime.getRuntime();
		    final long Lused2 = (r2.totalMemory() - r2.freeMemory()) / 1024L / 1024L;
		    System.gc();
		    final long Lused3 = (r2.totalMemory() - r2.freeMemory()) / 1024L / 1024L;
		    for(Player s : Bukkit.getOnlinePlayers()){
		    	if(GroupAPI.getGroup(s).equalsIgnoreCase("Dono")|| (GroupAPI.getGroup(s).equalsIgnoreCase("Gerente") || (GroupAPI.getGroup(s).equalsIgnoreCase("Administrador") || (GroupAPI.getGroup(s).equalsIgnoreCase("Moderador") || (GroupAPI.getGroup(s).equalsIgnoreCase("Trial")))))){
		    		s.sendMessage(MessageAPI.Command_Succes + "Foram removidos §6"+Long.toString(Lused2 - Lused3) +"M §aRAM do Servidor");		    	}
		    }
		    Bukkit.broadcastMessage(MessageAPI.Command_Succes + "Limpeza concluída!");
		}
		return false;
	}

}
