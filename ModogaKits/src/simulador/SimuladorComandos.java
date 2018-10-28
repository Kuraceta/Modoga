package simulador;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import api.API;
import api.WarpAPI;
import main.Main;
import score.ScoreBoarding;

public class SimuladorComandos implements CommandExecutor, Listener{
	@Override
	public boolean onCommand(CommandSender Sender, Command Cmd, String Label, String[] Args) {
		Player p = (Player)Sender;
		if(Cmd.getName().equalsIgnoreCase("asimulador")){
			if(p.hasPermission("spectrepvp.admin")){
				
			}
		}
		if(Cmd.getName().equalsIgnoreCase("simulador")){
			if(EventosAPI.playerPlayingHG(p)){
				p.sendMessage("§cVocê já está no Simulador");
				return true;
			}
			if(Main.EstadoHG == EstadoHG.ANDAMENTO|| Main.EstadoHG == EstadoHG.INVENCIVEL){
				p.sendMessage("§cA partida está em andamento, você será notificado quando a partida terminar!");
				return true;
			}
			if(!(WarpAPI.getWarp(p).equalsIgnoreCase("spawn"))){
				p.sendMessage("§cPara poder entrar você precisa ir para o Spawn");
				return true;
			}
			WarpAPI.setWarp(p, "HGSpawn");
			EventosAPI.playersHG.add(p.getName());
			p.sendMessage("§aVocê entrou no Simulador");
			p.sendMessage("§6§l! §fVocê pode craftar sopas usando: cacto, flores, cacau ou até mesmo os cogumelos!");
			p.sendMessage("§6§l! §fMinigame em §eBETA §fqualquer coisa reporte!");
			ScoreBoarding.setScoreBoard(p);
			p.getInventory().clear();
			for(Player pd : Bukkit.getOnlinePlayers()) {
    			if(EventosAPI.playerPlayingHG(pd)) {
    				ScoreBoarding.setScoreBoard(pd);
    			}
    		}
			p.getInventory().setItem(4, API.createItem(p, Material.FEATHER, "§eKits", new String[] {""}, 1, (short)0));
		if(EventosAPI.playersHG.size()<=1){
			Main.EstadoHG = EstadoHG.INICIANDO;
			sTempoIniciar.CancelarTempo();
			sTempoIniciar.Iniciando = 121;
			new sTempoIniciar();
		}
		p.setGameMode(GameMode.SURVIVAL);
		}
		return true;
	}

}
