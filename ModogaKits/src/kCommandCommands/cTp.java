package kCommandCommands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import api.MessageAPI;
import br.alkazuz.groupapi.api.GroupAPI;

public class cTp implements CommandExecutor{
	
	private boolean CheckarNumero(String Numero){
		try {
			Integer.parseInt(Numero);
			return true;
		} catch(NumberFormatException e){
			return false;
		}
		
	}
	
	@Override
	public boolean onCommand(CommandSender Sender, Command Cmd, String Label, String[] Args) {
		
		Player p = (Player)Sender;
		
		if(Cmd.getName().equalsIgnoreCase("tp")){
			
			if(!GroupAPI.GroupCanExecute(GroupAPI.getGroup(p), Cmd.getName())){
				p.sendMessage(MessageAPI.Command_Error+"Comando inexistente ou você não tem acesso");
				return true;
		    }
			
			if(Args.length == 0){
				p.sendMessage(MessageAPI.Command_Succes+"Comando incorreto use §c[ /tp (player) | (x) (y) (z) ]");
				return true;
			}
			if(Args.length > 2 || Args.length == 3){
				if (!CheckarNumero(Args[0])) {
					p.sendMessage(MessageAPI.Command_Error+"As cordenadas deve ser um numero.");
					return true;
				}
				Integer X = Integer.parseInt(Args[0]);
				if(Args.length == 1){
					p.sendMessage(MessageAPI.Command_Error+"Comando incorreto use §4[ /tp " + Args[0] + " (y) (z) ]");
					return true;
				}
				if (!CheckarNumero(Args[1])) {
					p.sendMessage(MessageAPI.Command_Error+"As cordenadas deve ser um numero.");
					return true;
				}
				Integer Y = Integer.parseInt(Args[1]);
				if(Args.length == 2){
					p.sendMessage("§7Comando incorreto use §c[ /tp " + Args[0] + " " + Args[1] + " (z) ]");
					return true;
				}
				if (!CheckarNumero(Args[2])) {
					p.sendMessage(MessageAPI.Command_Error+"As cordenadas deve ser um numero.");
					return true;
				}
				Integer Z = Integer.parseInt(Args[2]);
				p.teleport(new Location(p.getWorld(), X, Y, Z));
				p.sendMessage(MessageAPI.Command_Succes+"Você foi teleportado para §2(§a" + X + " " + Y + " " + Z + "§2)");
				return true;
			}
			Player t = Bukkit.getPlayerExact(Args[0]);
			if((t == null || (!(t instanceof Player)))){
				return true;
			}
			if (Args.length > 1) {
				if (Args.length == 1) {
					return true;
				}
				Player t2 = Bukkit.getPlayer(Args[1]);
				t.teleport(t2.getLocation());
				p.sendMessage(MessageAPI.Command_Succes+"Você teleportou o jogador(a) §2(§a" + t.getName() + "§2) §aaté você");
				return true;
			}
			p.teleport(t.getLocation());
			p.sendMessage(MessageAPI.Command_Succes+"Você foi teleportado até o jogador(a) §2(§a" + t.getName() + "§2)");
		
		}
		return false;	
	}
}
