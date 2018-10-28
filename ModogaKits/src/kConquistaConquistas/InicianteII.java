package kConquistaConquistas;

import org.bukkit.entity.Player;

import api.MessageAPI;
import kConquista.Conquista;
import kConquista.ConquistaAPI;
import menu.MenusAPI;
import mysqlManager.Status;
import score.ScoreBoarding;

public class InicianteII extends Conquista{

	public InicianteII() {
		super("Iniciante II", new String[] {"","§fMate §c20 §fJogadores na Arena"}, 200);
	}
	
	@Override
	public boolean onComplete(Player p) {
		if(Status.getkills(p)>= 20) {
			ConquistaAPI.addConquista(p, this.getName());
			p.sendMessage(MessageAPI.Command_Succes+"Você completou essa conquista e ganhou §e"+MenusAPI.money(getPremio()));
			Status.addCoins(p, this.getPremio());
			ScoreBoarding.setScoreBoard(p);
			return true;
		}
		return false;
	}

}
