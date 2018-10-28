package kCommandCommands;

import java.util.Random;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import api.MessageAPI;
import api.WarpAPI;
import br.alkazuz.groupapi.api.GroupAPI;
import kKit.Kit;
import kKit.KitAPI;
import kKit.KitManager;
import kKs.KillStreakAPI;
import menu.KitMenu;
import protection.Protection;
import score.ScoreBoarding;

public class cKit implements CommandExecutor{

	public void run(String name, String[] args, CommandSender Sender) {
		if(Sender instanceof Player) {
			Player p = (Player)Sender;
			if(!WarpAPI.getWarp(p).equalsIgnoreCase("Spawn")) {
				p.sendMessage("§4» §cVá para o spawn para pegar um Kit");
				return;
			}
		if(args.length == 0) {
			KitMenu.OpenInventory(p);
			return;
		}
		Kit kit = KitManager.getKit(args[0]);
		if(kit == null) {
			p.sendMessage("§4» §cEste kit não existe.");
			return;
		}
		if(!p.hasPermission(kit.getPerm())&&!kit.isFree()&&!KitAPI.hasKit(GroupAPI.getGroup(p), kit)) {
			p.sendMessage("§4» §cVocê não possui esse Kit");
			return ;
		}
		int i = new Random().nextInt(5);
		if(i == 0){
			i = 3;
		}
		KitAPI.setKit(p, kit);
		p.sendMessage(MessageAPI.Command_Succes+"Você selecionou o Kit §e"+kit.getName());
		WarpAPI.setWarp(p, String.valueOf("Arena"+i));
		Protection.setImortal(p, false);
		KitAPI.giveKit(p,kit);
		KillStreakAPI.resetKS(p);
		
		ScoreBoarding.setScoreBoard(p);
		}
	}

	@Override
	public boolean onCommand(CommandSender arg0, org.bukkit.command.Command arg1, String arg2, String[] arg3) {
		if(arg1.getName().equalsIgnoreCase("kit")) {
		run(arg1.getName(),arg3,arg0);
		}
		return true;
	}
	
}
