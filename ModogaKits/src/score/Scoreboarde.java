package score;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import main.Main;

public class Scoreboarde {

	public static void ScoreBoard(Player p) {

		Scoreboard sb = Bukkit.getScoreboardManager().getNewScoreboard();
		Objective obj = sb.registerNewObjective("score", "dummy");
		obj.setDisplaySlot(DisplaySlot.SIDEBAR);

		FastOfflinePlayer line13 = new FastOfflinePlayer("§a");
		FastOfflinePlayer line12 = new FastOfflinePlayer("§fGrupo: ");
		FastOfflinePlayer line11 = new FastOfflinePlayer("§fDeaths");
		FastOfflinePlayer line10 = new FastOfflinePlayer("§fStreak");
		FastOfflinePlayer line9 = new FastOfflinePlayer("§5");
		FastOfflinePlayer line8 = new FastOfflinePlayer("§fGrupo");
		FastOfflinePlayer line7 = new FastOfflinePlayer("§fOnline");
		FastOfflinePlayer line6 = new FastOfflinePlayer("§e");
		FastOfflinePlayer line5 = new FastOfflinePlayer("§fXP");
		FastOfflinePlayer line4 = new FastOfflinePlayer("§fKit");
		FastOfflinePlayer line3 = new FastOfflinePlayer("§fClan");
		FastOfflinePlayer line2 = new FastOfflinePlayer("§8");
		FastOfflinePlayer line1 = new FastOfflinePlayer("§7/score");

		Team l13 = sb.registerNewTeam("line13");
		Team l12 = sb.registerNewTeam("line12");
		Team l11 = sb.registerNewTeam("line11");
		Team l10 = sb.registerNewTeam("line10");
		Team l9 = sb.registerNewTeam("line9");
		Team l8 = sb.registerNewTeam("line8");
		Team l7 = sb.registerNewTeam("line7");
		Team l6 = sb.registerNewTeam("line6");
		Team l5 = sb.registerNewTeam("line5");
		Team l4 = sb.registerNewTeam("line4");
		Team l3 = sb.registerNewTeam("line3");
		Team l1 = sb.registerNewTeam("line1");

		l13.setSuffix("");
		l12.setSuffix("");
		l11.setSuffix("");
		l10.setSuffix("");
		l9.setSuffix("");
		l8.setSuffix("");
		l7.setSuffix("");
		l6.setSuffix("");
		l5.setSuffix("");
		l4.setSuffix("");
		l3.setSuffix("");
		l1.setSuffix("");

		l13.addPlayer(line13);
		l12.addPlayer(line12);
		l11.addPlayer(line11);
		l10.addPlayer(line10);
		l9.addPlayer(line9);
		l8.addPlayer(line8);
		l7.addPlayer(line7);
		l6.addPlayer(line6);
		l5.addPlayer(line5);
		l4.addPlayer(line4);
		l3.addPlayer(line3);
		l1.addPlayer(line1);

		obj.getScore(line13.getName()).setScore(13);
		obj.getScore(line12.getName()).setScore(12);
		obj.getScore(line11.getName()).setScore(11);
		obj.getScore(line10.getName()).setScore(10);
		obj.getScore(line9.getName()).setScore(9);
		obj.getScore(line8.getName()).setScore(8);
		obj.getScore(line7.getName()).setScore(7);
		obj.getScore(line6.getName()).setScore(6);
		obj.getScore(line5.getName()).setScore(5);
		obj.getScore(line4.getName()).setScore(4);
		obj.getScore(line3.getName()).setScore(3);
		obj.getScore(line2.getName()).setScore(2);
		obj.getScore(line1.getName()).setScore(1);

		p.setScoreboard(sb);
	}

	public static void update(final Player p) {
		//PlayerData data = DataManager.getPlayerData(p);
		Thread th = new Thread(new Runnable() {
			@SuppressWarnings("deprecation")
			@Override
			public void run() {

				if (p.getScoreboard() != null) {
					if (p.getScoreboard().getObjective("score") != null) {

						Team l13 = p.getScoreboard().getTeam("line13");
						Team l12 = p.getScoreboard().getTeam("line12");
						Team l11 = p.getScoreboard().getTeam("line11");
						Team l10 = p.getScoreboard().getTeam("line10");
						Team l9 = p.getScoreboard().getTeam("line9");
						Team l8 = p.getScoreboard().getTeam("line8");
						Team l7 = p.getScoreboard().getTeam("line7");
						Team l5 = p.getScoreboard().getTeam("line5");
						Team l4 = p.getScoreboard().getTeam("line4");
						Team l3 = p.getScoreboard().getTeam("line3");
						Team l1 = p.getScoreboard().getTeam("line1");

						l13.setSuffix("");
						l12.setSuffix(" §7");
						l11.setSuffix(" §7" );
						l10.setSuffix(" §7" );
						l9.setSuffix(" ");
						l8.setSuffix(" ");
						l7.setSuffix(" §a" + Bukkit.getOnlinePlayers().length + "/130");
						l5.setSuffix(" §a");
						l4.setSuffix(" §a" );
						l3.setSuffix(" §a" );
						l1.setSuffix("");
					}
				}
			}
		});

		th.start();
	}

	public static void run() {
		try {
			new BukkitRunnable() {

				@SuppressWarnings("deprecation")
				@Override
				public void run() {
					for (Player p : Bukkit.getOnlinePlayers()) {
						if (p.getScoreboard().getObjective("score") != null) {
							p.getScoreboard().getObjective("score").setDisplayName("§b§lKITPVP");
						}
						update(p);
					}

				}
			}.runTaskTimer(Main.plugin, 0, 10L);
		} catch (Error e) {
		}
	}
}
