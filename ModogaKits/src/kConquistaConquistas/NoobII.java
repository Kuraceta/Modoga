package kConquistaConquistas;

import org.bukkit.entity.Player;

import api.MessageAPI;
import kConquista.Conquista;
import kConquista.ConquistaAPI;
import menu.MenusAPI;
import mysqlManager.Status;
import score.ScoreBoarding;

public class NoobII extends Conquista{

	public NoobII() {
		super("Noob II", new String[] {"","�fMorra �c20 �fvezes na Arena"}, 300);
	}
	

	@Override
	public boolean onComplete(Player p) {
		if(Status.getDeaths(p)>= 20) {
			ConquistaAPI.addConquista(p, this.getName());
			p.sendMessage(MessageAPI.Command_Succes+"Voc� completou essa conquista e ganhou �e"+MenusAPI.money(getPremio()));
			Status.addCoins(p, this.getPremio());
			ScoreBoarding.setScoreBoard(p);
			return true;
		}
		return false;
	}
	
}
