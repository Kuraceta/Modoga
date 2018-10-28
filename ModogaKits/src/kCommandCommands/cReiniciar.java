package kCommandCommands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import api.MessageAPI;
import api.TempoReiniciar;
import br.alkazuz.groupapi.api.GroupAPI;

public class cReiniciar implements CommandExecutor, Listener{
	
	public static boolean inInteger(String text){
		try {
			Integer.parseInt(text);
			return true;
		} catch(NumberFormatException e){
			return false;
		}
	}
	@Override
	public boolean onCommand(CommandSender Sender, Command Cmd, String Label, String[] Args) {
		
		
		if(Cmd.getName().equalsIgnoreCase("reiniciar")){
			if(Sender instanceof Player) {
				Player p = (Player)Sender;
			if(!GroupAPI.GroupCanExecute(GroupAPI.getGroup(p), Cmd.getName())){
				p.sendMessage(MessageAPI.Command_Error+"Comando inexistente ou você não tem acesso");
				return true;
		    }
			if(Args.length == 0) {
			new TempoReiniciar();
			}else {
				TempoReiniciar.CancelarTempo();
				TempoReiniciar.Tempo = 301;
				p.sendMessage("§aO Reinicio foi Cancelado");
			}}else {
				if(Args.length == 0) {
					new TempoReiniciar();
					}else {
						TempoReiniciar.CancelarTempo();
						TempoReiniciar.Tempo = 301;
						Sender.sendMessage("§aO Reinicio foi Cancelado");
					}
			}
		}
		return false;
	}
}
