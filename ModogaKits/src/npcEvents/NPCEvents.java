package npcEvents;

import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import api.WarpAPI;
import menu.KitDiarioMenu;
import menu.LojaKit;
import menu.MenusAPI;

public class NPCEvents implements Listener{
	
	@EventHandler
	public void onNpcDamage(EntityDamageByEntityEvent e) {
		if(e.getEntity() instanceof Villager && e.getDamager() instanceof Player) {
			Player p = (Player)e.getDamager();
			if(p.getGameMode() == GameMode.CREATIVE)return;
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEntityEvent e) {
		Player p = e.getPlayer();
		if(WarpAPI.getWarp(p).equalsIgnoreCase("Spawn")) {
			if(e.getRightClicked() instanceof Villager) {
				Villager vil = (Villager)e.getRightClicked();
				if(vil.getCustomName().equalsIgnoreCase("�a�lCaixas �7- Clique")) {
					MenusAPI.caixasmenu(p);
					e.setCancelled(true);
				}
				if(vil.getCustomName().equalsIgnoreCase("�a�lAjudante �7- Clique")) {
					p.getWorld().playSound(p.getLocation(), Sound.VILLAGER_YES, 0.2f, 0.2f);
					p.sendMessage(" ");
					p.sendMessage("�a�lNPC: �fOl� �e"+p.getName()+" �fSeja bem vindo(a) ao Servidor, SpectrePvP � uma comunidade de minecraft online criado por Alkazuz & LaraGomess. N�s amamos construir, criar e compartilhar no minecraft. � por isso que criamos a rede SpectrePvP. Nosso servidor est� sendo executado em hardware High-End para garantir que voc� obtenha a melhor qualidade e conex�o poss�vel, por isso somos capazes de lidar com mais de 90 jogadores ao mesmo tempo. Voc� est� no lugar certo aqui. Isso � SpectrePvP!\n\n\n�fAlguns comandos:\n�e/clan �f- O sistema de clan � inovador, com ele voc� criar grandes amigos e grupos\n�/report �f- Reporte um Jogador caso ele esteja infringir as regras da rede utilizando este comando\n�e/aplicar �f- Caso queira ser da Equipe, use este comando\n�e/youtuber �f- Use este comando para ver os requisitos para se tornar um Youtuber\n\n\n�fOutros:\n�fTemos sistema de quests, complete as quests do NPS, assim voc� conseguir� moedas para gastar livremente no Servidor");
					p.sendMessage(" ");
					e.setCancelled(true);
				}
				if(vil.getCustomName().equalsIgnoreCase("�e�lConquistas �7- Clique")) {
					MenusAPI.OnpenConquista(p);
					e.setCancelled(true);
				}
				if(vil.getCustomName().equalsIgnoreCase("�c�lLoja de Kits �7- Clique")) {
					LojaKit.OpenInventory(p);
					e.setCancelled(true);
				}
				if(vil.getCustomName().equalsIgnoreCase("�d�lKit Di�rio �7- Clique")) {
					new KitDiarioMenu(p).newScroll();
					e.setCancelled(true);
				}
			}
		}
	}

}
