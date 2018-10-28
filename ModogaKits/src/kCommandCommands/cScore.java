package kCommandCommands;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import api.MessageAPI;
import main.Main;
import score.ScoreBoarding;

public class cScore implements CommandExecutor{

	public static ArrayList<Player> delay = new ArrayList<Player>();
	
	@Override
	public boolean onCommand(CommandSender Sender, Command Cmd, String Label, String[] Args) {
		if(!(Sender instanceof Player)){
			return false;
		}
		Player p = (Player)Sender;
		if(Cmd.getName().equalsIgnoreCase("scoreboard")){
			if(delay.contains(p)){
				p.sendMessage(MessageAPI.Command_Error+"Você está em cooldown desse comando");
				return true;
			}
			if(ScoreBoarding.Score.contains(p)){
				ScoreBoarding.Score.remove(p);
			}else{
				ScoreBoarding.Score.add(p);
			}
			
			p.sendMessage(MessageAPI.Command_Succes+"Sua Scoreboard foi "+ (ScoreBoarding.Score.contains(p) ? "§aativada" : "§4desativada"));
			delay.add(p);
			ScoreBoarding.setScoreBoard(p);
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
