package kConquistaConquistas;

import org.bukkit.entity.Player;

import api.MessageAPI;
import kConquista.Conquista;
import kConquista.ConquistaAPI;
import menu.MenusAPI;
import mysqlManager.Status;
import score.ScoreBoarding;

public class NoobI extends Conquista{

	public NoobI() {
		super("Noob I", new String[] {"","§fMorra §c10 §fvezes na Arena"}, 100);
	}
	

	@Override
	public boolean onComplete(Player p) {
		if(Status.getDeaths(p)>= 10) {
			ConquistaAPI.addConquista(p, this.getName());
			p.sendMessage(MessageAPI.Command_Succes+"Você completou essa conquista e ganhou §e"+MenusAPI.money(getPremio()));
			Status.addCoins(p, this.getPremio());
			ScoreBoarding.setScoreBoard(p);
			return true;
		}
		return false;
	}
	
}
