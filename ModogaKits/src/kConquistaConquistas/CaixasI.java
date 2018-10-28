package kConquistaConquistas;

import org.bukkit.entity.Player;

import api.MessageAPI;
import kConquista.Conquista;
import kConquista.ConquistaAPI;
import menu.MenusAPI;
import mysqlManager.Status;
import score.ScoreBoarding;

public class CaixasI extends Conquista{

	public CaixasI() {
		super("Caixas I", new String[] {"","�fJunte �e5 �fcaixas"}, 300);
	}
	
	@Override
	public boolean onComplete(Player p) {
		if(Status.getCaixas(p)>= 5) {
			ConquistaAPI.addConquista(p, this.getName());
			p.sendMessage(MessageAPI.Command_Succes+"Voc� completou essa conquista e ganhou �e"+MenusAPI.money(getPremio()));
			Status.addCoins(p, this.getPremio());
			ScoreBoarding.setScoreBoard(p);
			return true;
		}
		return false;
	}

}
