package kCommandCommands;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import api.MessageAPI;
import br.alkazuz.groupapi.api.GroupAPI;



public class cSc  implements Listener, CommandExecutor{
  
public static ArrayList<Player> staff = new ArrayList<Player>();

@SuppressWarnings("deprecation")
@EventHandler
  public void onChat(AsyncPlayerChatEvent e) { Player p = e.getPlayer();
    for (Player online : Bukkit.getOnlinePlayers()) {
      if (!isStaffChatting(p))
        continue;
      e.setCancelled(true);
      if ((GroupAPI.GroupCanExecute(GroupAPI.getGroup(online), "sc")) || (isStaffChatting(online)))
        online.sendMessage("§4§l{SC} §f" + p.getDisplayName() + " §6» §b" + e.getMessage());
    }
  }

  public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
    Player p = (Player)sender;
    if (cmd.getName().equalsIgnoreCase("sc")){
    	if(!GroupAPI.GroupCanExecute(GroupAPI.getGroup(p), cmd.getName())){
			p.sendMessage(MessageAPI.Command_Error+"Comando inexistente ou você não tem acesso");
			return true;
	    }
      if (isStaffChatting(p)){
        staff.remove(p);
        p.sendMessage(MessageAPI.Command_Succes+"Você saiu do §cStaff Chat");
      }else  {
        staff.add(p);
        p.sendMessage(MessageAPI.Command_Succes+"Você entrou no §2Staff Chat");
      }
    }
    return true;
  }

  public boolean isStaffChatting(Player player)
  {
    return staff.contains(player);
  }
}