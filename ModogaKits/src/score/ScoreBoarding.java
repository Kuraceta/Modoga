package score;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import api.ClanAPI;
import api.RankAPI;
import api.WarpAPI;
import br.alkazuz.groupapi.api.GroupAPI;
import kClan.Clan;
import kConfig.ClanConfig;
import kKit.KitAPI;
import kKs.KillStreakAPI;
import main.Main;
import menu.MenusAPI;
import mlg.MLGAPI;
import mysqlManager.Status;
import simulador.EventosAPI;

public class ScoreBoarding implements Listener {

	public static Main plugin;

	public static ArrayList<Player> Score = new ArrayList<Player>();
	
	public static String StringClan(Player p){
		Clan clan = ClanAPI.getClanPlayer(p);
		if(clan == null){
			return "§fNenhum";
		}
		return ClanConfig.getConfig().getString(clan.getName()+".Tag").replace("&", "§");
	}
	
	public static void setScoreBoard(Player p) {
		if(Score.contains(p)){
			if(EventosAPI.playerPlayingHG(p)){
				SimpleScoreboard scoreboard = new SimpleScoreboard("§6§lSimulador");
				scoreboard.blankLine();
				scoreboard.add("§fGrupo: §e"  + GroupAPI.getColor(GroupAPI.getGroup(p))+GroupAPI.getGroup(p));
				scoreboard.blankLine();
				scoreboard.add("§fJogadores: §a"  + EventosAPI.playersHG.size());
				scoreboard.blankLine();
				scoreboard.add("  §6jogar.spectre-pvp.tk");
				scoreboard.build();
				scoreboard.send(p);
				return;
			}
			if(WarpAPI.getWarp(p).equalsIgnoreCase("MLG")) {
				SimpleScoreboard scoreboard = new SimpleScoreboard("§b§lMLG");
				scoreboard.blankLine();
				scoreboard.add("§fGrupo: §e"  + GroupAPI.getColor(GroupAPI.getGroup(p))+GroupAPI.getGroup(p));
				scoreboard.blankLine();
				scoreboard.add("§fAcertos: §a"  + MLGAPI.getAcertos(p));
				scoreboard.add("§fErros:  §c" + MLGAPI.getErros(p));
				scoreboard.add("§fStreak:  §e" + MLGAPI.getKS(p));
				scoreboard.blankLine();
				scoreboard.add("  §6jogar.spectre-pvp.tk");
				scoreboard.build();
				scoreboard.send(p);
				return;
			}
			else {
			SimpleScoreboard scoreboard = new SimpleScoreboard("§2§lSpectrePvP");
			scoreboard.blankLine();
			scoreboard.add("§fGrupo: §e"  + GroupAPI.getColor(GroupAPI.getGroup(p))+GroupAPI.getGroup(p));
			scoreboard.blankLine();
			scoreboard.add("§fKills: §e"  + Status.getkills(p));
			scoreboard.add("§fDeaths:  §e" + Status.getDeaths(p));
			//scoreboard.add("§fKD:  §e" + getKd(p));
			scoreboard.add("§fKS:  §e" + KillStreakAPI.getKS(p));
			 scoreboard.add("§fClan: §e"+StringClan(p));
			scoreboard.blankLine();
			scoreboard.add("§fCaixas:  §7" + Status.getCaixas(p));
			scoreboard.add("§fKit:  §7" + KitAPI.getKitName(p));
			scoreboard.add("§fRank:  §7" + RankAPI.getRankName(p));
			scoreboard.blankLine();
			scoreboard.add("§fMoney:  §7" + MenusAPI.money(Status.getCoins(p)));
			scoreboard.add("§fCash:  §7" + MenusAPI.money(Status.getCash(p.getName())));
			scoreboard.blankLine();
			scoreboard.add("  §6jogar.spectre-pvp.tk");
			scoreboard.build();
			scoreboard.send(p);
			}
		}else {
			removeScoreBoard(p);
		}
	}
	
    public static String getKd(Player p) {
    	final long KILL_TOTAL = Status.getkills(p);
	    final long Kill_FREE = Status.getDeaths(p);
	    final long Kill_USED = KILL_TOTAL - Kill_FREE;
	    final double Kill_USED_PERCENTAGE = (Kill_USED * 100) / KILL_TOTAL;
	    return Kill_USED_PERCENTAGE + "%";
    }
	
	public static void removeScoreBoard(Player p) {
		Scoreboard score = Bukkit.getScoreboardManager().getNewScoreboard();
		Objective o = score.registerNewObjective("kit", "PvP");
		o.setDisplaySlot(DisplaySlot.SIDEBAR);
		o.setDisplayName("");
		p.setScoreboard(score);


	}
}