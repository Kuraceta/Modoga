package kConquistaConquistas;

import org.bukkit.entity.Player;

import api.MessageAPI;
import kConquista.Conquista;
import kConquista.ConquistaAPI;
import menu.MenusAPI;
import mysqlManager.Status;
import score.ScoreBoarding;

public class RicoIII extends Conquista{

	public RicoIII() {
		super("Prozinho III", new String[] {"","§fJunte §e5000 §fmoney"}, 500);
	}
	
	@Override
	public boolean onComplete(Player p) {
		if(Status.getCoins(p)>= 5000) {
			ConquistaAPI.addConquista(p, this.getName());
			p.sendMessage(MessageAPI.Command_Succes+"Você completou essa conquista e ganhou §e"+MenusAPI.money(getPremio()));
			Status.addCoins(p, this.getPremio());
			ScoreBoarding.setScoreBoard(p);
			return true;
		}
		return false;
	}

}
