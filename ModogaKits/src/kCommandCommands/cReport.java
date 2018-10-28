package kCommandCommands;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import api.MessageAPI;
import br.alkazuz.groupapi.api.GroupAPI;
import kConfig.Config;
import main.Main;
import tab.TituloAPI;

public class cReport implements CommandExecutor{
	
	public static ArrayList<Player> delay = new ArrayList<Player>();
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender Sender, Command Cmd, String Label, String[] Args) {
		if(!(Sender instanceof Player)){
			return false;
		}
		Player p = (Player)Sender;
		if(Cmd.getName().equalsIgnoreCase("report")){
			if(delay.contains(p)){
				p.sendMessage(MessageAPI.Command_Error+"Você está em cooldown desse comando");
				return true;
			}
			if(Args.length <= 1){
				p.sendMessage(MessageAPI.Command_Error+"Use /report <Nick> Motivo");
				return true;
			}
			String reportmsg = "";
			for(int i = 1;i<Args.length;i++){
				reportmsg += Args[i]+" ";
			}
			Player reported = Bukkit.getPlayer(Args[0]);
			if(reported == p){
				p.sendMessage(MessageAPI.Command_Error+"Você não pode reportar você mesmo");
			}
			if(reported == null){
				p.sendMessage(MessageAPI.Command_Error+"Este jogador não está online.");
				return true;
			}
			for(Player playeres: Bukkit.getOnlinePlayers()){
				if(Config.getConfig().getBoolean("Grupo."+GroupAPI.getGroup(playeres)+".VerReportes")){
					playeres.sendMessage(" ");
					playeres.sendMessage("  §c§lREPORT");
					playeres.sendMessage("§eAcusado: §c"+reported.getName());
					playeres.sendMessage("§eVítima: §f"+p.getName());
					playeres.sendMessage("§eMotivo: §b"+reportmsg);
					playeres.sendMessage(" ");
					TituloAPI.mandarTitulo(playeres, "§c§lREPORT");
					TituloAPI.mandarSubTitulo(playeres, "§7Há um novo report, você pode resolve-lo.");
					}
				
			}
			p.sendMessage(MessageAPI.Command_Succes+"Seu reporte foi enviado para a Equipe, aguarde.");
			delay.add(p);
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), (Runnable)new Runnable() {
		          @Override
		          public void run() {
		        	  delay.remove(p);
		          }
		      }, 20L * 10L);
		}
		return false;
	}

}
