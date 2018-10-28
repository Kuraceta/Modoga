package comandos;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import api.GroupAPI;
import api.MessageAPI;
import api.NameTagAPI;
import apiDaTab.TituloAPI;
import main.Main;

public class cGroup implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender Sender, Command Cmd, String Label, String[] Args) {
		if(Sender instanceof Player){
		Player p = (Player)Sender;
		if(Cmd.getName().equalsIgnoreCase("grupo")){
			if(!GroupAPI.GroupCanExecute(GroupAPI.getGroup(p), Cmd.getName())){
				p.sendMessage(MessageAPI.Command_Error+"Comando inexistente ou você não tem acesso");
				return true;
		    }
			String groups = "";
			for(String gp : GroupAPI.grupos){
				groups +=gp+", ";
			}
			if(Args.length <= 1){
				p.sendMessage(MessageAPI.Command_Error+"Use /grupo <jogador> "+groups);
				return true;
			}
			Player vic = Bukkit.getPlayer( Args[0]);
			if(vic == null){
				String Group = Args[1];
				@SuppressWarnings("deprecation")
				OfflinePlayer vicoff = Bukkit.getOfflinePlayer(Args[0]);
				if(!groups.toLowerCase().contains(Group.toLowerCase())){
					p.sendMessage(MessageAPI.Command_Error+"Grupo não encontrado.");
					return true;
				}
				GroupAPI.setGroup(vicoff, Group);
				Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
					public void run() {
						Sender.sendMessage(MessageAPI.Command_Succes+"Você alterou o grupo de §e"+vicoff.getName()+" §apara §e"+GroupAPI.getGroup(Group));
					}
				}, 10L);
				return true;
			}
			String Group = Args[1];
			if(!groups.toLowerCase().contains(Group.toLowerCase())){
				p.sendMessage(MessageAPI.Command_Error+"Grupo não encontrado.");
				return true;
			}
			GroupAPI.setGroup(vic, Group);
			Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
				public void run() {
					Sender.sendMessage(MessageAPI.Command_Succes+"Você alterou o grupo de §c"+vic.getName()+" §apara §e"+GroupAPI.getColor(GroupAPI.getGroup(vic))+GroupAPI.getGroup(vic));
					TituloAPI.mandarTitulo(vic, "§a§lGrupo");
					TituloAPI.mandarSubTitulo(vic, "§7Seu grupo foi alterado para "+GroupAPI.getColor(GroupAPI.getGroup(vic))+GroupAPI.getGroup(vic));
					NameTagAPI.setupTag(vic);
				}
			}, 10L);
			
			return true;
		}
		}else{
			if(Cmd.getName().equalsIgnoreCase("grupo")){
				String groups = "";
				for(String gp : GroupAPI.grupos){
					groups +=gp+", ";
				}
				if(Args.length == 1){
					Sender.sendMessage(MessageAPI.Command_Error+"Use /grupo <jogador> "+groups);
					return true;
				}
				Player vic = Bukkit.getPlayer( Args[0]);
				if(vic == null){
					String Group = Args[1];
					@SuppressWarnings("deprecation")
					OfflinePlayer vicoff = Bukkit.getOfflinePlayer(Args[0]);
					if(!groups.toLowerCase().contains(Group.toLowerCase())){
						Sender.sendMessage(MessageAPI.Command_Error+"Grupo não encontrado.");
						return true;
					}
					GroupAPI.setGroup(vicoff, Group);
					
					Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
						public void run() {
							Sender.sendMessage(MessageAPI.Command_Succes+"Você alterou o grupo de §e"+vicoff.getName()+" §apara §e"+GroupAPI.getGroup(Group));
						}
					}, 10L);
					return true;
				}
				String Group = Args[1];
				if(!groups.toLowerCase().contains(Group.toLowerCase())){
					Sender.sendMessage(MessageAPI.Command_Error+"Grupo não encontrado.");
					return true;
				}
				GroupAPI.setGroup(vic, Group);

				Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
					public void run() {
						Sender.sendMessage(MessageAPI.Command_Succes+"Você alterou o grupo de §c"+vic.getName()+" §apara §e"+GroupAPI.getColor(GroupAPI.getGroup(vic))+GroupAPI.getGroup(vic));
						TituloAPI.mandarTitulo(vic, "§a§lGrupo");
						TituloAPI.mandarSubTitulo(vic, "§7Seu grupo foi alterado para "+GroupAPI.getColor(GroupAPI.getGroup(vic))+GroupAPI.getGroup(vic));
						NameTagAPI.setupTag(vic);
					}
				}, 10L);
				
				return true;
			}
		}
		return false;
	}

}
