package kCommandCommands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import api.MessageAPI;
import br.alkazuz.groupapi.api.GroupAPI;
import mysqlManager.Status;
import score.ScoreBoarding;

public class cEco implements CommandExecutor, Listener{
	
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
		if(Sender instanceof Player){
			Player p = (Player)Sender;
			if(Cmd.getName().equalsIgnoreCase("eco")){
				if(!GroupAPI.GroupCanExecute(GroupAPI.getGroup(p), Cmd.getName())){
					p.sendMessage(MessageAPI.Command_Error+"Comando inexistente ou você não tem acesso");
					return true;
			    }
				if(Args.length <=1){
					Sender.sendMessage(MessageAPI.Command_Error+"Use: /eco <Jogador> <Quantidade>");
					return true;
				}
				if(!inInteger(Args[1])){
					Sender.sendMessage(MessageAPI.Command_Error+"Você só pode usar números");
					return true;
				}
				Player alvo = Bukkit.getPlayer(Args[0]);
				if(alvo == null){
					Sender.sendMessage(MessageAPI.Command_Error+"Jogador não encontrado");
					return true;
				}
				Status.addCoins(alvo, Integer.parseInt(Args[1]));
				ScoreBoarding.setScoreBoard(alvo);
				Sender.sendMessage(MessageAPI.Command_Succes+"Coins enviados com Sucesso");
				alvo.sendMessage(MessageAPI.Command_Succes+"Você recebeu §e"+Args[1]+" §ade §f"+Sender.getName());
				return true;
			}
		}else{
			if(Cmd.getName().equalsIgnoreCase("eco")){
				if(Args.length <=1){
					Sender.sendMessage(MessageAPI.Command_Error+"Use: /eco <Jogador> <Quantidade>");
					return true;
				}
				if(!inInteger(Args[1])){
					Sender.sendMessage(MessageAPI.Command_Error+"Você só pode usar números");
					return true;
				}
				Player alvo = Bukkit.getPlayer(Args[0]);
				if(alvo == null){
					Sender.sendMessage(MessageAPI.Command_Error+"Jogador não encontrado");
					return true;
				}
				Status.addCoins(alvo, Integer.parseInt(Args[1]));
				ScoreBoarding.setScoreBoard(alvo);
				alvo.sendMessage(MessageAPI.Command_Succes+"Você recebeu §e"+Args[1]+" §ade §f"+Sender.getName());
				Sender.sendMessage(MessageAPI.Command_Succes+"Coins enviados com Sucesso");
				return true;
			}
		}
		return false;
	}

}
