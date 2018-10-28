package kConquistaConquistas;

import org.bukkit.entity.Player;

import api.MessageAPI;
import kConquista.Conquista;
import kConquista.ConquistaAPI;
import menu.MenusAPI;
import mysqlManager.MySQLFunctions;
import mysqlManager.Status;
import score.ScoreBoarding;

public class MestreIII extends Conquista{

	public MestreIII() {
		super("MestreX1 III", new String[] {"","§fTenha §e30 §fvitórias na warp 1v1"}, 700);
	}
	
	@Override
	public boolean onComplete(Player p) {
		if(MySQLFunctions.getWins(p)>= 30) {
			ConquistaAPI.addConquista(p, this.getName());
			p.sendMessage(MessageAPI.Command_Succes+"Você completou essa conquista e ganhou §e"+MenusAPI.money(getPremio()));
			Status.addCoins(p, this.getPremio());
			ScoreBoarding.setScoreBoard(p);
			return true;
		}
		return false;
	}

}
