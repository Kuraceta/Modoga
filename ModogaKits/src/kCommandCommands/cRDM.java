package kCommandCommands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import api.MessageAPI;
import api.WarpAPI;
import br.alkazuz.groupapi.api.GroupAPI;
import kRdm.RDMApi;
import kRdm.RDMContador;
import kRdm.RDMStatus;

public class cRDM implements CommandExecutor{
	
	@Override
	public boolean onCommand(CommandSender Sender, Command Cmd, String Label, String[] Args) {
		
		Player p = (Player)Sender;
		if(Cmd.getName().equalsIgnoreCase("rdm")){
			
			if(!GroupAPI.GroupCanExecute(GroupAPI.getGroup(p), Cmd.getName())){
				p.sendMessage(MessageAPI.Command_Error+"Comando inexistente ou você não tem acesso");
				return true;
		    }
			if(Args.length < 1) {
				p.sendMessage(MessageAPI.Command_Error+"Use §4/rdm <Iniciar | Parar | Tempo | Forcar>");
				return true;
			}
			if(Args[0].equalsIgnoreCase("forcar")) {
				RDMApi.proximaRodada();
			}
			if(Args[0].equalsIgnoreCase("tempo")) {
				if(Args.length < 2) {
					p.sendMessage(MessageAPI.Command_Error+"Use §4/rdm Tempo <Tempo em Segundos>");
					return true;
				}
				if(RDMApi.status != RDMStatus.INICIANDO)return true;
				RDMContador.Iniciando = Integer.parseInt(Args[1]);
				Bukkit.broadcastMessage("§4§lRDM §fO Tempo foi alterado para §e"+Integer.parseInt(Args[1]));
				return true;
			}
			if(Args[0].equalsIgnoreCase("iniciar")) {
				if(RDMApi.status == RDMStatus.RODANDO) {
					p.sendMessage(MessageAPI.Command_Error+"O Evento já está rodando");
					return true;
				}
				if(RDMApi.status == RDMStatus.FINALIZANDO) {
					p.sendMessage(MessageAPI.Command_Error+"O Evento está sendo finalizado, aguarde...");
					return true;
				}
				if(RDMApi.status == RDMStatus.INICIANDO) {
					p.sendMessage(MessageAPI.Command_Error+"O Evento já está sendo iniciado.");
					return true;
				}
				RDMApi.status = RDMStatus.INICIANDO;
				new RDMContador();
			}
			
			if(Args[0].equalsIgnoreCase("parar")) {
				if(RDMApi.status == RDMStatus.RODANDO) {
					p.sendMessage(MessageAPI.Command_Error+"O Evento já está rodando");
					return true;
				}
				Bukkit.broadcastMessage("§4§lRDM §fO Evento foi cancelado por um Administrador.");
				for(Player ps : RDMApi.playing) {
					WarpAPI.setWarp(ps, "Spawn");
					RDMApi.delPlayer(ps);
				}
			}
			
		}
		if(Cmd.getName().equalsIgnoreCase("entrar")){
			if(RDMApi.status == RDMStatus.FINALIZADO) {
				p.sendMessage(MessageAPI.Command_Error+"O Evento não está acontecendo");
				return true;
			}
			if(RDMApi.status == RDMStatus.RODANDO) {
				p.sendMessage(MessageAPI.Command_Error+"O Evento já foi iniciado");
				return true;
			}
			if(RDMApi.status == RDMStatus.FINALIZANDO) {
				p.sendMessage(MessageAPI.Command_Error+"O Evento está sendo finalizado, aguarde...");
				return true;
			}
			if(!WarpAPI.getWarp(p).equalsIgnoreCase("Spawn")) {
				p.sendMessage(MessageAPI.Command_Error+"Vá para o Spawn para poder participar");
				return true;
			}
			if(RDMApi.isPlaying(p)) {
				p.sendMessage(MessageAPI.Command_Error+"Você já está no evento");
				return true;
			}
			
			RDMApi.addPlayer(p);
		}
		
		return false;
	}

}
