package kConquistaConquistas;

import org.bukkit.entity.Player;

import api.MessageAPI;
import kConquista.Conquista;
import kConquista.ConquistaAPI;
import menu.MenusAPI;
import mysqlManager.Status;
import score.ScoreBoarding;

public class InicianteIII extends Conquista{

	public InicianteIII() {
		super("Iniciante III", new String[] {"","§fMate §c35 §fJogadores na Arena"}, 300);
	}
	
	@Override
	public boolean onComplete(Player p) {
		if(Status.getkills(p)>= 35) {
			ConquistaAPI.addConquista(p, this.getName());
			p.sendMessage(MessageAPI.Command_Succes+"Você completou essa conquista e ganhou §e"+MenusAPI.money(getPremio()));
			Status.addCoins(p, this.getPremio());
			ScoreBoarding.setScoreBoard(p);
			return true;
		}
		return false;
	}

}
