package kConquistaConquistas;

import org.bukkit.entity.Player;

import api.MessageAPI;
import kConquista.Conquista;
import kConquista.ConquistaAPI;
import menu.MenusAPI;
import mlg.MLGAPI;
import mysqlManager.Status;
import score.ScoreBoarding;

public class MLGII extends Conquista{

	public MLGII() {
		super("Mestre do MLG II", new String[] {"","§fAcerte §e40 §fMLG"}, 600);
	}
	
	@Override
	public boolean onComplete(Player p) {
		if(MLGAPI.getAcertos(p) >= 40) {
			ConquistaAPI.addConquista(p, this.getName());
			p.sendMessage(MessageAPI.Command_Succes+"Você completou essa conquista e ganhou §e"+MenusAPI.money(getPremio()));
			Status.addCoins(p, this.getPremio());
			ScoreBoarding.setScoreBoard(p);
			return true;
		}
		return false;
	}

}
