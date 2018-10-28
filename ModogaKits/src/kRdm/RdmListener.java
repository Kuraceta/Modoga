package kRdm;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import api.API;
import api.NameTagAPI;
import api.VIPAPI;
import api.WarpAPI;
import br.alkazuz.groupapi.api.GroupAPI;
import main.Main;
import mysqlManager.Status;
import protection.Protection;
import score.ScoreBoarding;

public class RdmListener implements Listener{
	
	
	@EventHandler
	public void onDeath(PlayerDeathEvent e) {
		if(e.getEntity().getKiller() instanceof Player){
			Player killer = (Player)e.getEntity().getKiller();
			Player p = e.getEntity();
			if(RDMApi.isPlaying(p)&&RDMApi.isPlaying(killer) && ((RDMApi.getPlayerOne() == p || RDMApi.getPlayerTwo() == p)&&(RDMApi.getPlayerTwo() == killer || RDMApi.getPlayerTwo() == killer))) {
				RDMApi.playing.remove(p);
				Bukkit.broadcastMessage(" ");
				Bukkit.broadcastMessage("§4§lRDM §c"+killer.getName()+" §fvenceu a rodada contra §e"+p.getName());
				Bukkit.broadcastMessage("§4§lRDM §fIniciando a próxima rodada...");
				Bukkit.broadcastMessage(" ");
				WarpAPI.setWarp(p, "Spawn");
				Protection.setImortal(killer, true);
				if(RDMApi.ChecarGanhador(killer)) {
					RDMApi.status = RDMStatus.FINALIZANDO;
					Bukkit.broadcastMessage(" ");
					Bukkit.broadcastMessage("§4§lRDM §c"+killer.getName()+" §fvenceu o Evento RDM!!");
					Bukkit.broadcastMessage(" ");
					boolean melhor = false;
					if(GroupAPI.getGroup(p)=="Youtuber" || GroupAPI.getGroup(p)=="Youtuber+") {
						melhor = true;
					}
					if(melhor) {
						killer.sendMessage("§4§lRDM §eSeu Grupo é melhor que o Grupo §6§lPRO§e, seu prêmio será 5k de coins");
						Status.addCoins(p, 5000);
						
					}else {
						if(VIPAPI.isVip(killer)) {
							VIPAPI.applyVIP(killer,1);
						}else {
							GroupAPI.setGroupTemp(killer, "Pro", ((long)86400 + 1000L + System.currentTimeMillis()));
						}
						NameTagAPI.setupTag(killer);
					}
					ScoreBoarding.setScoreBoard(killer);
					Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
						public void run() {
							killer.sendMessage("§4§lRDM §aVocê venceu o Evento, mais um dia de §6§lPRO §afoi adicionado em sua conta.");
						}
					}, 20L);
					Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
						public void run() {
							killer.sendMessage("§4§lRDM §aVocê venceu o Evento, mais um dia de §6§lPRO §afoi adicionado em sua conta.");
						}
					}, 40L);
					Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
						public void run() {
							killer.sendMessage("§4§lRDM §aVocê venceu o Evento, mais um dia de §6§lPRO §afoi adicionado em sua conta.");
						}
					}, 60L);
					Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
						public void run() {
							killer.sendMessage("§4§lRDM §aVocê venceu o Evento, mais um dia de §6§lPRO §afoi adicionado em sua conta.");
						}
					}, 80L);
					Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
						public void run() {
							
							RDMApi.playing.clear();
							WarpAPI.setWarp(p, "Spawn");
							RDMApi.status = RDMStatus.FINALIZADO;
						}
					}, 120L);
					return;
				}else {
					if(p == RDMApi.getPlayerOne()) {
						RDMApi.player1 = null;
					}
					if(p == RDMApi.getPlayerTwo()) {
						RDMApi.player2 = null;
					}
					Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
						public void run() 	{
							RDMApi.proximaRodada();
							Bukkit.broadcastMessage(" ");
							Bukkit.broadcastMessage("§4§lRDM §fRodada iniciada...");
							Bukkit.broadcastMessage("§4§lRDM §c"+RDMApi.player1.getName()+" §fvs §c"+RDMApi.player2.getName());
							Bukkit.broadcastMessage(" ");
							}
						}, 20L * 10L);
				}
				Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
					public void run() {
						p.spigot().respawn();
						API.sendItems(p);
					}
				}, 6L);
				
			}
		}
	}

	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		if(RDMApi.isPlaying(p)) {
			RDMApi.delPlayer(p);
		}
		if(RDMApi.isPlaying(e.getPlayer())) {
			RDMApi.delPlayer(e.getPlayer());
			if(p == RDMApi.getPlayerOne()) {
				RDMApi.player1 = null;
				RDMApi.proximaRodada();
			}
			if(p == RDMApi.getPlayerTwo()) {
				RDMApi.player2 = null;
				RDMApi.proximaRodada();
			}
			for(Player killer : RDMApi.playing) {
				if(RDMApi.ChecarGanhador(killer)) {
					RDMApi.status = RDMStatus.FINALIZANDO;
					Bukkit.broadcastMessage(" ");
					Bukkit.broadcastMessage("§4§lRDM §c"+killer.getName()+" §fvenceu o Evento RDM!!");
					Bukkit.broadcastMessage(" ");
					boolean melhor = false;
					if(GroupAPI.getGroup(p)=="Youtuber" || GroupAPI.getGroup(p)=="Youtuber+") {
						melhor = true;
					}
					if(melhor) {
						killer.sendMessage("§4§lRDM §eSeu Grupo é melhor que o Grupo §6§lPRO§e, seu prêmio será 5k de coins");
						Status.addCoins(p, 5000);
						
					}else {
						if(VIPAPI.isVip(killer)) {
							VIPAPI.applyVIP(killer,1);
						}else {
							GroupAPI.setGroupTemp(killer, "Pro", ((long)86400 + 1000L + System.currentTimeMillis()));
						}
						NameTagAPI.setupTag(killer);
					}
					ScoreBoarding.setScoreBoard(killer);
					Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
						public void run() {
							killer.sendMessage("§4§lRDM §aVocê venceu o Evento, mais um dia de §6§lPRO §afoi adicionado em sua conta.");
						}
					}, 20L);
					Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
						public void run() {
							killer.sendMessage("§4§lRDM §aVocê venceu o Evento, mais um dia de §6§lPRO §afoi adicionado em sua conta.");
						}
					}, 40L);
					Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
						public void run() {
							killer.sendMessage("§4§lRDM §aVocê venceu o Evento, mais um dia de §6§lPRO §afoi adicionado em sua conta.");
						}
					}, 60L);
					Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
						public void run() {
							killer.sendMessage("§4§lRDM §aVocê venceu o Evento, mais um dia de §6§lPRO §afoi adicionado em sua conta.");
						}
					}, 80L);
					Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
						public void run() {
							
							RDMApi.playing.clear();
							WarpAPI.setWarp(p, "Spawn");
							RDMApi.status = RDMStatus.FINALIZADO;
						}
					}, 120L);
					return;
				}
			}
		}
	}
	
	@EventHandler
	public void onQuit(PlayerKickEvent e) {
		Player p = e.getPlayer();
		if(RDMApi.isPlaying(p)) {
			RDMApi.delPlayer(p);
		}
		if(RDMApi.isPlaying(e.getPlayer())) {
			RDMApi.delPlayer(e.getPlayer());
			if(p == RDMApi.getPlayerOne()) {
				RDMApi.player1 = null;
				RDMApi.proximaRodada();
			}
			if(p == RDMApi.getPlayerTwo()) {
				RDMApi.player2 = null;
				RDMApi.proximaRodada();
			}
			for(Player killer : RDMApi.playing) {
				if(RDMApi.ChecarGanhador(killer)) {
					RDMApi.status = RDMStatus.FINALIZANDO;
					Bukkit.broadcastMessage(" ");
					Bukkit.broadcastMessage("§4§lRDM §c"+killer.getName()+" §fvenceu o Evento RDM!!");
					Bukkit.broadcastMessage(" ");
					boolean melhor = false;
					if(GroupAPI.getGroup(p)=="Youtuber" || GroupAPI.getGroup(p)=="Youtuber+") {
						melhor = true;
					}
					if(melhor) {
						killer.sendMessage("§4§lRDM §eSeu Grupo é melhor que o Grupo §6§lPRO§e, seu prêmio será 5k de coins");
						Status.addCoins(p, 5000);
						
					}else {
						if(VIPAPI.isVip(killer)) {
							VIPAPI.applyVIP(killer,1);
						}else {
							GroupAPI.setGroupTemp(killer, "Pro", ((long)86400 + 1000L + System.currentTimeMillis()));
						}
						NameTagAPI.setupTag(killer);
					}
					ScoreBoarding.setScoreBoard(killer);
					Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
						public void run() {
							killer.sendMessage("§4§lRDM §aVocê venceu o Evento, mais um dia de §6§lPRO §afoi adicionado em sua conta.");
						}
					}, 20L);
					Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
						public void run() {
							killer.sendMessage("§4§lRDM §aVocê venceu o Evento, mais um dia de §6§lPRO §afoi adicionado em sua conta.");
						}
					}, 40L);
					Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
						public void run() {
							killer.sendMessage("§4§lRDM §aVocê venceu o Evento, mais um dia de §6§lPRO §afoi adicionado em sua conta.");
						}
					}, 60L);
					Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
						public void run() {
							killer.sendMessage("§4§lRDM §aVocê venceu o Evento, mais um dia de §6§lPRO §afoi adicionado em sua conta.");
						}
					}, 80L);
					Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
						public void run() {
							
							RDMApi.playing.clear();
							WarpAPI.setWarp(p, "Spawn");
							RDMApi.status = RDMStatus.FINALIZADO;
						}
					}, 120L);
					return;
				}
			}
				
		}
	}
	
	@EventHandler
	  public void onMe2(PlayerCommandPreprocessEvent event)
	  {
	    Player p = event.getPlayer();
	    if ((RDMApi.isPlaying(p)) && (
	      (!event.getMessage().toLowerCase().startsWith("/spawn"))))
	    {
	      event.setCancelled(true);
	      p.sendMessage("§4§lRDM §cVocê está no RDM, use §4/spawn §cpara executar comandos");
	    }
	  }
	
}
