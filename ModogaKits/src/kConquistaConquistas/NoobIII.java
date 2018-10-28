package kConquistaConquistas;

import org.bukkit.entity.Player;

import api.MessageAPI;
import kConquista.Conquista;
import kConquista.ConquistaAPI;
import menu.MenusAPI;
import mysqlManager.Status;
import score.ScoreBoarding;

public class NoobIII extends Conquista{

	public NoobIII() {
		super("Noob III", new String[] {"","§fMorra §c30 §fvezes na Arena"}, 500);
	}
	

	@Override
	public boolean onComplete(Player p) {
		if(Status.getDeaths(p)>= 30) {
			ConquistaAPI.addConquista(p, this.getName());
			p.sendMessage(MessageAPI.Command_Succes+"Você completou essa conquista e ganhou §e"+MenusAPI.money(getPremio()));
			Status.addCoins(p, this.getPremio());
			ScoreBoarding.setScoreBoard(p);
			return true;
		}
		return false;
	}
	
}
