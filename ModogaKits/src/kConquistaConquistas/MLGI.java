package kConquistaConquistas;

import org.bukkit.entity.Player;

import api.MessageAPI;
import kConquista.Conquista;
import kConquista.ConquistaAPI;
import menu.MenusAPI;
import mlg.MLGAPI;
import mysqlManager.Status;
import score.ScoreBoarding;

public class MLGI extends Conquista{

	public MLGI() {
		super("Mestre do MLG I", new String[] {"","§fAcerte §e20 §fMLG"}, 300);
	}
	
	@Override
	public boolean onComplete(Player p) {
		if(MLGAPI.getAcertos(p) >= 20) {
			ConquistaAPI.addConquista(p, this.getName());
			p.sendMessage(MessageAPI.Command_Succes+"Você completou essa conquista e ganhou §e"+MenusAPI.money(getPremio()));
			Status.addCoins(p, this.getPremio());
			ScoreBoarding.setScoreBoard(p);
			return true;
		}
		return false;
	}

}
