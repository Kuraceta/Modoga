package kCommandCommands;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import api.ClanAPI;
import api.MessageAPI;
import api.NameTagAPI;
import kClan.Clan;
import kClan.ClanManager;
import kConfig.ClanConfig;
import mysqlManager.Status;
import score.ScoreBoarding;

public class cClan implements CommandExecutor{
	
	@SuppressWarnings("unused")
	public boolean onCommand(CommandSender Sender, Command Cmd, String Label, String[] Args) {
		
		Player p = (Player)Sender;
		
		if(Cmd.getName().equalsIgnoreCase("clan")){
			
			if(Args.length == 0){
				p.sendMessage(" ");
				p.sendMessage("§6§lCLAN");
				p.sendMessage("§a/clan criar [Nome] [Tag] §f- Cria um clan");
				p.sendMessage("§a/clan deletar §f- Deleta o Clan");
				p.sendMessage("§a/clan convidar [Nome] §f- Convida alguém");
				p.sendMessage("§a/clan aceitar [Nome] §f- Aceita um pedido de Clan");
				p.sendMessage("§a/clan expulsar [Nome] §f- Expulsa alguém");
				p.sendMessage("§a/clan sair §f- Sai do clan");
				p.sendMessage("§a/clan msg [Mensagem] §f- Envia mensagem para o clan");
				p.sendMessage("§a/clan info §f- Informações do clan");
				p.sendMessage(" ");
				return true;
			}
			try{
			if(Args[0].equalsIgnoreCase("aceitar")){
				if(ClanAPI.getClanPlayer(p)!=null){
					p.sendMessage(MessageAPI.Command_Error+"Você já possui um Clan");
					return true;
				}
				String clan = Args[1];
				if(!ClanAPI.pedidos.get(clan).contains(p.getName().toLowerCase())){
					p.sendMessage(MessageAPI.Command_Error+"Você não recebeu convite desse Clan");
					return true;
				}
				Clan clann = ClanManager.getClan(Args[1]);
				if(clann==null){
					p.sendMessage(MessageAPI.Command_Error+"Este clan não existe!");
					return true;
				}
				ClanAPI.addPlayerToClan(p, ClanManager.getClan(clan));
				ClanAPI.senMessage(ClanAPI.getClanPlayerString(p), "§a# §f"+p.getName()+" §fse aliou ao Clan");
				ClanAPI.pedidos.get(clan).remove(p.getName().toLowerCase());
				NameTagAPI.setupTag(p);
			}
			if(Args[0].equalsIgnoreCase("convidar")){
				if(ClanAPI.getClanPlayer(p)==null){
					p.sendMessage(MessageAPI.Command_Error+"Você não possui um Clan");
					return true;
				}
				if(!ClanAPI.getOwner(ClanAPI.getClanPlayerString(p)).equalsIgnoreCase(p.getName())){
					p.sendMessage(MessageAPI.Command_Error+"Você não é o Dono desse Clan");
					return true;
				}
				if(Bukkit.getPlayer(Args[1].toLowerCase())!=null && ClanAPI.getClanPlayer(Bukkit.getPlayer(Args[1].toLowerCase()))!=null) {
					p.sendMessage(MessageAPI.Command_Error+"Esse Jogador já possui um Clan");
					return true;
				}
				if(!ClanAPI.pedidos.containsKey(ClanAPI.getClanPlayerString(p))){
					ClanAPI.pedidos.put(ClanAPI.getClanPlayerString(p), new ArrayList<String>());
				}
				if( ClanAPI.pedidos.get(ClanAPI.getClanPlayerString(p)) == null){
					ClanAPI.pedidos.put(ClanAPI.getClanPlayerString(p), new ArrayList<String>());
				}
				String nick = Args[1].toLowerCase();
				ArrayList<String> list = ClanAPI.pedidos.get(ClanAPI.getClanPlayerString(p));
				if(!list.contains(nick)){
					list.add(nick);
					Player vic = Bukkit.getPlayer(nick);
					if(vic !=null){
					vic.sendMessage(" ");
					vic.sendMessage("§6§lClan §fVocê recebeu um Convite do clan §f"+ClanAPI.getClanPlayerString(p)+" §f("+ClanConfig.getConfig().getString(ClanAPI.getClanPlayerString(p)+".Tag").replace("&", "§")+"§f)");
					vic.sendMessage("§6§lClan §fUse §a/clan aceitar "+ClanAPI.getClanPlayerString(p)+" §fou apenas ignore essa Mensagem");
					vic.sendMessage(" ");
					}
					ClanAPI.pedidos.put(ClanAPI.getClanPlayerString(p), list);
					ClanAPI.senMessage(ClanAPI.getClanPlayerString(p), "§a# §f"+nick+" §afoi convidado para o Clan");
					return true;
				}else{
					p.sendMessage(MessageAPI.Command_Error+"Já existe um convite para esse Nick");
					
					return true;
				}
			}
			if(Args[0].equalsIgnoreCase("info")){
				if(ClanAPI.getClanPlayer(p)==null){
					p.sendMessage(MessageAPI.Command_Error+"Você não possui um Clan");
					return true;
				}
				p.sendMessage(" ");
				p.sendMessage("§6§lCLAN§b§lINFO");
				p.sendMessage(" ");
				p.sendMessage("§fNome do Clan: §e"+ClanAPI.getClanPlayerString(p));
				p.sendMessage("§fTag do Clan: "+ClanConfig.getConfig().getString(ClanAPI.getClanPlayerString(p)+".Tag").replace("&", "§"));
				p.sendMessage("§fLider: §e"+ClanAPI.getOwner(ClanAPI.getClanPlayerString(p)));
				p.sendMessage("§fOnline: §e"+ClanAPI.getOnlineSize(ClanAPI.getClanPlayerString(p)));
				p.sendMessage(" ");
			}
			if(Args[0].equalsIgnoreCase("expulsar")){
				if(ClanAPI.getClanPlayer(p)==null){
					p.sendMessage(MessageAPI.Command_Error+"Você não possui um Clan");
					return true;
				}
				if(!ClanAPI.getOwner(ClanAPI.getClanPlayerString(p)).equalsIgnoreCase(p.getName())){
					p.sendMessage(MessageAPI.Command_Error+"Você não é o Dono desse Clan");
					return true;
				}
				Player vic = Bukkit.getPlayer(Args[1]);
				if(vic.getName().equals(p.getName())){
					p.sendMessage(MessageAPI.Command_Error+"Você não pode kikar você mesmo");
					return true;
				}
				if(vic == null){
					@SuppressWarnings("deprecation")
					OfflinePlayer vicoff = Bukkit.getOfflinePlayer(Args[1]);
					if(!ClanAPI.getClanPlayerString(vicoff).equalsIgnoreCase(ClanAPI.getClanPlayerString(p))){
						p.sendMessage(MessageAPI.Command_Error+"Esse jogador nao pertence ao seu Clan");
						return true;
					}
					ClanAPI.kickPlayer(vicoff, ClanAPI.getClanPlayerString(p));
					ClanAPI.senMessage(ClanAPI.getClanPlayerString(p), "§c# §f"+p.getName()+" §fexpulsou o Jogador §e"+vicoff.getName());
					return true;
				}
				if(!ClanAPI.getClanPlayerString(vic).equalsIgnoreCase(ClanAPI.getClanPlayerString(p))){
					p.sendMessage(MessageAPI.Command_Error+"Esse jogador nao pertence ao seu Clan");
					return true;
				}
				ClanAPI.kickPlayer(vic, ClanAPI.getClanPlayerString(p));
				ClanAPI.senMessage(ClanAPI.getClanPlayerString(p), "§c# §f"+p.getName()+" §fexpulsou o Jogador §e"+vic.getName());
				vic.sendMessage("§cVocê foi expulso do Clan");
				ScoreBoarding.setScoreBoard(vic);
				return true;
			}
			if(Args[0].equalsIgnoreCase("msg")){
				if(ClanAPI.getClanPlayer(p)==null){
					p.sendMessage(MessageAPI.Command_Error+"Você não possui um Clan");
					return true;
				}
				String message = " ";
		        for (int i = 1; i < Args.length; i++) {
		          if (i == Args.length - 1) {
		            message = message + Args[i];
		          } else {
		            message = message + Args[i] + " ";
		          }
		        }
		        ClanAPI.senMessage(ClanAPI.getClanPlayerString(p), "§b# §f"+p.getName()+": §e"+message);
			}
			if(Args[0].equalsIgnoreCase("deletar")){
				if(ClanAPI.getClanPlayer(p)==null){
					p.sendMessage(MessageAPI.Command_Error+"Você não possui um Clan");
					return true;
				}
				if(!ClanAPI.getOwner(ClanAPI.getClanPlayerString(p)).equalsIgnoreCase(p.getName())){
					p.sendMessage(MessageAPI.Command_Error+"Você não é o Dono desse Clan");
					return true;
				}
				
				p.sendMessage(MessageAPI.Command_Succes+"Clan deletado com Sucesso");
				Bukkit.broadcastMessage("§6§lCLAN §e"+p.getName()+" §fdeletou o Clan §c"+ClanAPI.getClanPlayerString(p));
				Clan clan = ClanManager.getClan(ClanAPI.getClanPlayerString(p));
				clan.delete();
				ScoreBoarding.setScoreBoard(p);
				NameTagAPI.setupTag(p);
			}
			if(Args[0].equalsIgnoreCase("sair")){
				if(ClanAPI.getClanPlayer(p)==null){
					p.sendMessage(MessageAPI.Command_Error+"Você não possui um Clan");
					return true;
				}
				if(ClanAPI.getOwner(ClanAPI.getClanPlayerString(p)).equalsIgnoreCase(p.getName())){
					p.sendMessage(MessageAPI.Command_Error+"Você não pode sair do seu Clan");
					p.sendMessage(MessageAPI.Command_Error+"Use [/clan deletar] para deletar o Clan");
					return true;
				}
				p.sendMessage(MessageAPI.Command_Succes+"Você saiu do Clan");
				ClanAPI.senMessage(ClanAPI.getClanPlayerString(p), "§c# §f"+p.getName()+" §fsaiu do Clan");
				ClanAPI.kickPlayer(p, ClanAPI.getClanPlayerString(p));
				ScoreBoarding.setScoreBoard(p);
			}
			if(Args[0].equalsIgnoreCase("criar")){
				if(Status.getCoins(p)<500){
					p.sendMessage(MessageAPI.Command_Error+"É necessário ter 500 de coins para criar um Clan");
					return true;
				}
				if(ClanAPI.clanExist(Args[1])){
					p.sendMessage(MessageAPI.Command_Error+"Este clan já existe");
					return true;
				}
				if(ClanAPI.getClanPlayer(p)!=null){
					p.sendMessage(MessageAPI.Command_Error+"Você já possui um Clan");
					return true;
				}
				String tag = Args[2].replace("&", "§").replace("§k", "").replace("§l", "").replace("§m", "").replace("§o", "");
				String tag2 = ChatColor.stripColor(tag);
				if(tag2.length() > 3 || tag2.length()< 3) {
					p.sendMessage(MessageAPI.Command_Error+"A tag do clan deve ter apenas 3 caracteres.");
					return true;
				}
				for(String CLAN :ClanManager.getAllClansString()){
					if(ClanConfig.getConfig().getString(CLAN+".Tag").equalsIgnoreCase(tag)){
						p.sendMessage(MessageAPI.Command_Error+"Já existe um Clan com essa Tag");
						return true;
					}
				}
				ClanAPI.createClan(p, tag, Args[1]);
				p.sendMessage(MessageAPI.Command_Succes+"Clan criado com Sucesso!");
				Bukkit.broadcastMessage("§6§lCLAN §e"+p.getName()+" §fcriou o Clan §c"+Args[1]);
				ScoreBoarding.setScoreBoard(p);
				NameTagAPI.setupTag(p);
			}
			}catch(Exception ex){
				p.sendMessage(" ");
				p.sendMessage("§6§lCLAN");
				p.sendMessage("§a/clan criar [Nome] [Tag] §f- Cria um clan");
				p.sendMessage("§a/clan deletar §f- Deleta o Clan");
				p.sendMessage("§a/clan convidar [Nome] §f- Convida alguém");
				p.sendMessage("§a/clan aceitar [Nome] §f- Aceita um pedido de Clan");
				p.sendMessage("§a/clan expulsar [Nome] §f- Expulsa alguém");
				p.sendMessage("§a/clan sair §f- Sai do clan");
				p.sendMessage("§a/clan msg [Mensagem] §f- Envia mensagem para o clan");
				p.sendMessage("§a/clan info §f- Informações do clan");
				p.sendMessage(" ");
			}
		}
		return false;
	}

}
