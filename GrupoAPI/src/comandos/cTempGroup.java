package comandos;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import api.GroupAPI;
import api.MessageAPI;
import api.Methods;
import api.NameTagAPI;
import apiDaTab.TituloAPI;

public class cTempGroup implements CommandExecutor{

	public void msg(CommandSender sender, String msg)
	  {
	    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
	  }
	
	@Override
	public boolean onCommand(CommandSender Sender, Command Cmd, String Label, String[] Args) {
		if(Sender instanceof Player){
		Player p = (Player)Sender;
		if(Cmd.getName().equalsIgnoreCase("tempgrupo")){
			if(!GroupAPI.GroupCanExecute(GroupAPI.getGroup(p), Cmd.getName())){
				p.sendMessage(MessageAPI.Command_Error+"Comando inexistente ou você não tem acesso");
				return true;
		    }
			String groups = "";
			for(String gp : GroupAPI.grupos){
				groups +=gp+", ";
			}
			if(Args.length <= 2){
				msg(Sender, "&cExemplo de uso: /tempgrupo Notch 2h,10m Pro");
	            msg(Sender, "&cVoce pode usar d, h, m, e/ou s para indicar o tempo");
				return true;
			}
			long total = 0L;
	        String[] time = Args[1].split(",");
	        for (int i = 0; i < time.length; i++)
	        {
	          if (time[i].replaceAll("[0-9]", "").length() != 1)
	          {
	            msg(Sender, "&cExemplo de uso: /tempgrupo Notch 2h,10m Pro");
	            msg(Sender, "&cVoce pode usar d, h, m, e/ou s para indicar o tempo");
	            return true;
	          }
	          if ((!time[i].replaceAll("[0-9]", "").contains("d")) && (!time[i].replaceAll("[0-9]", "").contains("h")) && (!time[i].replaceAll("[0-9]", "").contains("m")) && (!time[i].replaceAll("[0-9]", "").contains("s")))
	          {
	        	  msg(Sender, "&cExemplo de uso: /tempgrupo Notch 2h,10m Pro");
		           msg(Sender, "&cVoce pode usar d, h, m, e/ou s para indicar o tempo");
	            return true;
	          }
	          long day = 0L;long hour = 0L;long minute = 0L;long second = 0L;
	          if (time[i].replaceAll("[0-9]", "").contains("d")) {
	            day = Integer.parseInt(time[i].replaceAll("[A-Za-z]", "")) * 86400;
	          }
	          if (time[i].replaceAll("[0-9]", "").contains("h")) {
	            hour = Integer.parseInt(time[i].replaceAll("[A-Za-z]", "")) * 3600;
	          }
	          if (time[i].replaceAll("[0-9]", "").contains("m")) {
	            minute = Integer.parseInt(time[i].replaceAll("[A-Za-z]", "")) * 60;
	          }
	          if (time[i].replaceAll("[0-9]", "").contains("s")) {
	            second = Integer.parseInt(time[i].replaceAll("[A-Za-z]", ""));
	          }
	          total += day + hour + minute + second;
	        }
	        long newTime = total * 1000L + System.currentTimeMillis();
			Player vic = Bukkit.getPlayer( Args[0]);
			if(vic == null){
				String Group = Args[2];
				@SuppressWarnings("deprecation")
				OfflinePlayer vicoff = Bukkit.getOfflinePlayer(Args[0]);
				if(!groups.toLowerCase().contains(Group.toLowerCase())){
					p.sendMessage(MessageAPI.Command_Error+"Grupo não encontrado.");
					return true;
				}
				GroupAPI.setGroupTemp(vicoff, GroupAPI.getGroup(Group),Long.valueOf(newTime));
				p.sendMessage(MessageAPI.Command_Succes+"Você alterou o grupo de §c"+vicoff.getName()+" §apara §e"+GroupAPI.getGroup(Group));
				return true;
			}
			String Group = Args[2];
			if(!groups.toLowerCase().contains(Group.toLowerCase())){
				p.sendMessage(MessageAPI.Command_Error+"Grupo não encontrado.");
				return true;
			}
			GroupAPI.setGroupTemp(vic, GroupAPI.getGroup(Group),Long.valueOf(newTime));
			vic.sendMessage("§a§lGRUPO §7Seu grupo foi alterado para "+GroupAPI.getColor(GroupAPI.getGroup(vic))+GroupAPI.getGroup(vic));
			vic.sendMessage("§a§lGRUPO §7Ele expira em §c"+Methods.getRemainingTime(Long.valueOf(newTime)));
			Sender.sendMessage(MessageAPI.Command_Succes+"Você alterou o grupo de §c"+vic.getName()+" §apara §e"+GroupAPI.getColor(GroupAPI.getGroup(vic))+GroupAPI.getGroup(vic));
			TituloAPI.mandarTitulo(vic, "§a§lGrupo");
			TituloAPI.mandarSubTitulo(vic, "§7Seu grupo foi alterado para "+GroupAPI.getColor(GroupAPI.getGroup(vic))+GroupAPI.getGroup(vic));
			NameTagAPI.setupTag(vic);
			return true;
		}
		}else{
			if(Cmd.getName().equalsIgnoreCase("tempgrupo")){
				String groups = "";
				for(String gp : GroupAPI.grupos){
					groups +=gp+", ";
				}
				if(Args.length <= 2){
					msg(Sender, "&cExemplo de uso: /tempgrupo Notch 2h,10m Pro");
		            msg(Sender, "&cVoce pode usar d, h, m, e/ou s para indicar o tempo");
					return true;
				}
				long total = 0L;
		        String[] time = Args[1].split(",");
		        for (int i = 0; i < time.length; i++)
		        {
		          if (time[i].replaceAll("[0-9]", "").length() != 1)
		          {
		            msg(Sender, "&cExemplo de uso: /tempgrupo Notch 2h,10m Pro");
		            msg(Sender, "&cVoce pode usar d, h, m, e/ou s para indicar o tempo");
		            return true;
		          }
		          if ((!time[i].replaceAll("[0-9]", "").contains("d")) && (!time[i].replaceAll("[0-9]", "").contains("h")) && (!time[i].replaceAll("[0-9]", "").contains("m")) && (!time[i].replaceAll("[0-9]", "").contains("s")))
		          {
		        	  msg(Sender, "&cExemplo de uso: /tempgrupo Notch 2h,10m Pro");
			           msg(Sender, "&cVoce pode usar d, h, m, e/ou s para indicar o tempo");
		            return true;
		          }
		          long day = 0L;long hour = 0L;long minute = 0L;long second = 0L;
		          if (time[i].replaceAll("[0-9]", "").contains("d")) {
		            day = Integer.parseInt(time[i].replaceAll("[A-Za-z]", "")) * 86400;
		          }
		          if (time[i].replaceAll("[0-9]", "").contains("h")) {
		            hour = Integer.parseInt(time[i].replaceAll("[A-Za-z]", "")) * 3600;
		          }
		          if (time[i].replaceAll("[0-9]", "").contains("m")) {
		            minute = Integer.parseInt(time[i].replaceAll("[A-Za-z]", "")) * 60;
		          }
		          if (time[i].replaceAll("[0-9]", "").contains("s")) {
		            second = Integer.parseInt(time[i].replaceAll("[A-Za-z]", ""));
		          }
		          total += day + hour + minute + second;
		        }
		        long newTime = total * 1000L + System.currentTimeMillis();
				Player vic = Bukkit.getPlayer( Args[0]);
				if(vic == null){
					String Group = Args[2];
					@SuppressWarnings("deprecation")
					OfflinePlayer vicoff = Bukkit.getOfflinePlayer(Args[0]);
					if(!groups.toLowerCase().contains(Group.toLowerCase())){
						Sender.sendMessage(MessageAPI.Command_Error+"Grupo não encontrado.");
						return true;
					}
					GroupAPI.setGroupTemp(vicoff, GroupAPI.getGroup(Group),Long.valueOf(newTime));
					Sender.sendMessage(MessageAPI.Command_Succes+"Você alterou o grupo de §c"+vicoff.getName()+" §apara §e"+GroupAPI.getGroup(Group));
					return true;
				}
				String Group = Args[2];
				if(!groups.toLowerCase().contains(Group.toLowerCase())){
					Sender.sendMessage(MessageAPI.Command_Error+"Grupo não encontrado.");
					return true;
				}
				GroupAPI.setGroupTemp(vic, GroupAPI.getGroup(Group),Long.valueOf(newTime));
				vic.sendMessage("§a§lGRUPO §7Seu grupo foi alterado para "+GroupAPI.getColor(GroupAPI.getGroup(vic))+GroupAPI.getGroup(vic));
				vic.sendMessage("§a§lGRUPO §7Ele expira em §c"+Methods.getRemainingTime(Long.valueOf(newTime)));
				Sender.sendMessage(MessageAPI.Command_Succes+"Você alterou o grupo de §c"+vic.getName()+" §apara §e"+GroupAPI.getColor(GroupAPI.getGroup(vic))+GroupAPI.getGroup(vic));
				TituloAPI.mandarTitulo(vic, "§a§lGrupo");
				TituloAPI.mandarSubTitulo(vic, "§7Seu grupo foi alterado para "+GroupAPI.getColor(GroupAPI.getGroup(vic))+GroupAPI.getGroup(vic));
				NameTagAPI.setupTag(vic);
				return true;
			}
		}
		return false;
	}

}
