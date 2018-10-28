package kConquistaConquistas;

import org.bukkit.entity.Player;

import api.MessageAPI;
import kConquista.Conquista;
import kConquista.ConquistaAPI;
import menu.MenusAPI;
import mysqlManager.Status;
import score.ScoreBoarding;

public class CaixasIII extends Conquista{

	public CaixasIII() {
		super("Caixas III", new String[] {"","§fJunte §e15 §fcaixas"}, 900);
	}
	
	@Override
	public boolean onComplete(Player p) {
		if(Status.getCaixas(p)>= 15) {
			ConquistaAPI.addConquista(p, this.getName());
			p.sendMessage(MessageAPI.Command_Succes+"Você completou essa conquista e ganhou §e"+MenusAPI.money(getPremio()));
			Status.addCoins(p, this.getPremio());
			ScoreBoarding.setScoreBoard(p);
			return true;
		}
		return false;
	}

}
