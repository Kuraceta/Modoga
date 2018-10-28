package kCommandCommands;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import br.alkazuz.groupapi.api.GroupAPI;
import main.Main;

public class cTell implements CommandExecutor, Listener{
	
	public static ArrayList<Player> cooldown = new ArrayList<>();
	public static ArrayList<Player> disabled = new ArrayList<>();
	public static HashMap<Player,Player> tells = new HashMap<Player,Player>();
	
@SuppressWarnings("deprecation")
public boolean onCommand(CommandSender Sender, Command Cmd, String Label, String[] Args){
    final Player p = (Player)Sender;
    if (Cmd.getName().equalsIgnoreCase("r")){
    	if(Args.length == 0){
    		p.sendMessage("§cUse: /r <mensagem>");
    		return true;
    	}
    	Player aw = tells.get(p);
    	if(aw == null){
    		p.sendMessage("§cVocê possui mensagem para responder");
    		return true;
    	}
    	if (aw == null||api.API.admin.contains(aw)&&!GroupAPI.GroupCanExecute(GroupAPI.getGroup(p), "banid")){
     		p.sendMessage("§7Esse player não está online");	
          return true;
        }
    	if(cooldown.contains(p)&&!GroupAPI.GroupCanExecute(GroupAPI.getGroup(p), "banid")){
        	p.sendMessage("§cAguarde 03 segundos para usar o TELL novamente");
        	return true;
        }
    	if(disabled.contains(aw)&&!GroupAPI.GroupCanExecute(GroupAPI.getGroup(p), "banid")){
        	p.sendMessage("§7Esse player está com o tell desativado.");
        	return true;
        }
    	String message = " ";
        for (int i = 0; i < Args.length; i++) {
          if (i == Args.length - 1) {
            message = message + Args[i];
          } else {
            message = message + Args[i] + " ";
          }
        }
    	aw.sendMessage("§c" + p.getName()  + " §7para §cvocê §6» §f" + message);
    	p.sendMessage("§cVocê §7para §c" + aw.getName() + " §6» §f" + message);
    	for(Player staff : Bukkit.getOnlinePlayers()){
        	if(GroupAPI.GroupCanExecute(GroupAPI.getGroup(staff), "banid")){
        		staff.sendMessage("§7[De "+p.getName()+" para "+aw.getName()+"] "+message);
        	}
        }
    	cooldown.add(p);
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
			public void run() {
				cooldown.remove(p);
			}
		}, 3L * 10L);
    }
    if (Cmd.getName().equalsIgnoreCase("tell")){
        if (Args.length == 0){
        	p.sendMessage("§7Comando incorreto use §c[ /tell (mensagem) | (off) | (on) ]");
          return true;
        }
        if(Args[0].equalsIgnoreCase("on")){
        	if(disabled.contains(p)){
        	p.sendMessage("§2§l!§7 §7Seu tell foi §aativado");
        	disabled.remove(p);
        	}else{
        		p.sendMessage("§7Seu tell já está ativado");
        	}
        	return true;
        }
        if(Args[0].equalsIgnoreCase("off")){
        	if(!disabled.contains(p)){
        		disabled.add(p);
        		p.sendMessage("§a§l! §7Seu tell foi §cdesligado");
        	}else{
        		p.sendMessage("§7Seu tell já está desativado");
        	}
        	return true;
        }
        Player tell = Bukkit.getPlayer(Args[0]);
        if (tell == null||api.API.admin.contains(tell)&&!p.hasPermission("kitpvp.admin")){
     		p.sendMessage("§7Esse player não está online");	
          return true;
        }
        if (tell == p){
        	p.sendMessage("§7Você não pode mandar um tell para você mesmo.");
          return true;
        }
    	if(disabled.contains(tell)&&!p.hasPermission("kitpvp.admin")){
        	p.sendMessage("§7Esse player está com o tell desativado.");
        	return true;
        }
    	if(api.API.admin.contains(tell)&&!p.hasPermission("kitpvp.admin")){
     		p.sendMessage("§7Esse player não está online");	
        	return true;
        }
    	if(cooldown.contains(p)&&!p.hasPermission("kitpvp.admin")){
        	p.sendMessage("§cAguarde 03 segundos para usar o TELL novamente");
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
        
        p.sendMessage("§cVocê §7para §c" + tell.getName() + " §6» §f" + message);
        if(disabled.contains(p)){
        	disabled.remove(p);
        }
        tell.sendMessage(p.getName()  + " §7para §cvocê §6» §f" + message);
        tells.put(tell, p);
        tells.put(p, tell);
        for(Player staff : Bukkit.getOnlinePlayers()){
        	if(GroupAPI.GroupCanExecute(GroupAPI.getGroup(staff), "banid")){
        		staff.sendMessage("§7[De "+p.getName()+" para "+tell.getName()+"] "+message);
        	}
        }
        cooldown.add(p);
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
			public void run() {
				cooldown.remove(p);
			}
		}, 3L * 10L);
        }
    return false;
  }
}
