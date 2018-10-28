package kCommandCommands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class cYoutuber implements CommandExecutor{
	
	@Override
	public boolean onCommand(CommandSender Sender, Command Cmd, String Label, String[] Args) {
		
		Player p = (Player)Sender;
		
		if(Cmd.getName().equalsIgnoreCase("youtuber")){
			p.sendMessage(" ");
			p.sendMessage(
					"§ePara você ser um §b§lYoutuber");
			p.sendMessage("§e* §fGravar um video no servidor no 3 minutos");
			p.sendMessage("§e* §fInsirir o IP do Servidor na descrição");
			p.sendMessage("§e* §fTer 300 inscritos");
			p.sendMessage("§e* §fTer no mínimo 20 likes do Vídeo");
			p.sendMessage(" ");
			p.sendMessage("§ePara você ser um §b§lYoutuber+ §f");
			p.sendMessage("§e* §fGravar um video no servidor no 3 minutos");
			p.sendMessage("§e* §fInsirir o IP do Servidor na descrição");
			p.sendMessage("§e* §fTer 2,5k inscritos");
			p.sendMessage("§e* §fTer no mínimo 50 likes do Vídeo");
			p.sendMessage(" ");
			p.sendMessage("§4§lOBS: §fEnvie seu vídeo para o Tell de um Staff");
			return true;
		}
		return false;
	}
}
