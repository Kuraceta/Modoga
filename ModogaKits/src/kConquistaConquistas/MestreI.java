package kConquistaConquistas;

import org.bukkit.entity.Player;

import api.MessageAPI;
import kConquista.Conquista;
import kConquista.ConquistaAPI;
import menu.MenusAPI;
import mysqlManager.MySQLFunctions;
import mysqlManager.Status;
import score.ScoreBoarding;

public class MestreI extends Conquista{

	public MestreI() {
		super("MestreX1 I", new String[] {"","§fTenha §e10 §fvitórias na warp 1v1"}, 300);
	}
	
	@Override
	public boolean onComplete(Player p) {
		if(MySQLFunctions.getWins(p)>= 10) {
			ConquistaAPI.addConquista(p, this.getName());
			p.sendMessage(MessageAPI.Command_Succes+"Você completou essa conquista e ganhou §e"+MenusAPI.money(getPremio()));
			Status.addCoins(p, this.getPremio());
			ScoreBoarding.setScoreBoard(p);
			return true;
		}
		return false;
	}

}
