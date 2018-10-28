package api;

import java.util.ArrayList;

import org.bukkit.entity.Player;

import br.alkazuz.groupapi.api.GroupAPI;
import kConquista.ConquistaAPI;
import kConquista.ConquistaManager;
import mysqlManager.Status;

public class Account {
	
	private Player p;
	
	public Account(Player p) {
		this.p = p;
	}

	public ArrayList<String> getInfo() {
		ArrayList<String> array = new ArrayList<String>();
		array.add(" ");
		array.add("§fNick: §e"+p.getName());
		array.add("§fNick Mostrado: §e"+p.getDisplayName());
		array.add("§fUUID: §e"+p.getUniqueId().toString());
		array.add(" ");
		array.add("§fKills: §e"+Status.getkills(p));
		array.add("§fMortes: §e"+Status.getDeaths(p));
		array.add("§fMoney: §e"+Status.getCoins(p));
		array.add("§fCaixas: §e"+Status.getCaixas(p));
		array.add("§fConquistas: §e"+ConquistaAPI.getAmount(p)+"/"+ConquistaManager.conquistas.size());
		array.add(" ");
		array.add("§fGrupo: §e"+GroupAPI.getColor(GroupAPI.getGroup(p))+GroupAPI.getGroup(p));
		array.add("§fGrupo Expira em: §e"+GroupAPI.getTime(p));
		array.add(" ");
		return array;
	}
	
}
