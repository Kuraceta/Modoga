package kCommandCommands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class cAplicar implements CommandExecutor{
	
	  public static boolean pausado = false;
	@Override
	public boolean onCommand(CommandSender Sender, Command Cmd, String Label, String[] Args) {
		
		if(Cmd.getName().equalsIgnoreCase("aplicar")){
			Player p = (Player)Sender;
			
			p.sendMessage("§eQue tal se aplica em uma de nossas equipes? Já pensou receber vantagens por fazer o que gosta?");
			p.sendMessage(" ");
			p.sendMessage("§d§lTrial §fSomente pelo site §ewww.spectre-pvp.tk §f(necessário criar uma Conta)");
			p.sendMessage(" ");
			p.sendMessage("§4§lOBS: §fVale lembrar que os pedidos de TAG  §bYoutuber §fe §bYoutuber+ §fdeverão ser pedidas no forum §ewww.spectre-pvp.tk.");
		}
		return true;
		}

}
