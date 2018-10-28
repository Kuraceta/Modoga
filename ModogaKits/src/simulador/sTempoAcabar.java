package simulador;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import api.API;
import api.WarpAPI;
import main.Main;
import protection.Protection;
import score.ScoreBoarding;

public class sTempoAcabar {
	public static Integer Tempo;
	public static int Iniciando = 901;
	
	public static void CancelarTempo() {
		if (Tempo != null) {
			Bukkit.getScheduler().cancelTask(Tempo);
			Tempo = null;
		}
	}
	
	@SuppressWarnings("deprecation")
	public static void mandarBroadcast(String Menssagem) {
		for (Player Jogadores : Bukkit.getOnlinePlayers()) {
			if(EventosAPI.playerPlayingHG(Jogadores)){
				Jogadores.sendMessage(Menssagem);
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	private void mandarAvisos() {
		for (Player Jogadores : Bukkit.getOnlinePlayers()) {
			if(EventosAPI.playerPlayingHG(Jogadores)){
			Jogadores.playSound(Jogadores.getLocation(), Sound.CLICK, 10.0F, 10.0F);
			Jogadores.sendMessage(ChatColor.GOLD + "Partida termina em §e"+FormatoTempo(Iniciando));
			Jogadores.sendMessage("§7Restam ainda §e"+EventosAPI.playersHG.size()+" §7Jogadores");
			}
		}
	}
	
	public static String FormatoTempo(int Tempo) {
		int Minuto = Tempo/60, Segundo = Tempo%60;
		
		String MinutoTexto = null;
		String SegundoTexto = null;
		
		if (Minuto > 10) {
			MinutoTexto = " " + Minuto + " minuto(s)";
		} else {
			MinutoTexto = " " + Minuto + " minuto(s)";
		}
		if (Segundo > 10) {
			SegundoTexto = " " + Segundo + " segundo(s)";
		} else {
			SegundoTexto = " " + Segundo + " segundo(s)";
		}
		if (Minuto == 0) {
			MinutoTexto = "";
		}
		if (Segundo == 0) {
			SegundoTexto = "";
		}
		
		return MinutoTexto + SegundoTexto;
	}
	
	public static String FormatoTempo2(int Tempo) {
		int Minuto = Tempo/60, Segundo = Tempo%60;
		
		String MinutoTexto = null;
		String SegundoTexto = null;
		
		if (Minuto > 10) {
			MinutoTexto = " " + Minuto + " min";
		} else {
			MinutoTexto = " " + Minuto + " min";
		}
		if (Segundo > 10) {
			SegundoTexto = " " + Segundo + " seg";
		} else {
			SegundoTexto = " " + Segundo + " seg";
		}
		if (Minuto == 0) {
			MinutoTexto = "";
		}
		if (Segundo == 0) {
			SegundoTexto = "";
		}
		
		return MinutoTexto + SegundoTexto;
	}
	
	public sTempoAcabar() {
		if (Main.EstadoHG == EstadoHG.ANDAMENTO) {
			
			Tempo = Integer.valueOf(Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), new Runnable() {
				@SuppressWarnings("deprecation")
				public void run() {
					Iniciando -= 1;
					if (Iniciando == 900) {
						mandarAvisos();
						for(Player p : Bukkit.getOnlinePlayers()){
							if(!EventosAPI.playerPlayingHG(p))continue;
							p.getInventory().setItem(8, API.createItem(p, Material.COMPASS, "§eBussola", new String[] {""}, 1, (short)0));
						}
					}
					if (Iniciando == 840) {
						mandarAvisos();
					}
					if (Iniciando == 780) {
						mandarAvisos();
					}
					if (Iniciando == 720) {
						mandarAvisos();
					}
					if (Iniciando == 660) {
						mandarAvisos();
					}
					if (Iniciando == 600) {
						mandarAvisos();
					}
					if (Iniciando == 540) {
						mandarAvisos();
					}
					if (Iniciando == 480) {
						mandarAvisos();
					}
					if (Iniciando == 420) {
						mandarAvisos();
					}
					if (Iniciando == 360) {
						mandarAvisos();
					}
					if (Iniciando == 300) {
						mandarAvisos();
					}
					if (Iniciando == 240) {
						mandarAvisos();
						for(Player players : Bukkit.getOnlinePlayers()){
							ScoreBoarding.setScoreBoard(players);
							if(!EventosAPI.playerPlayingHG(players))continue;
							players.sendMessage("§6§l! §eTodos os Jogador foram Teleportados para o Spawn, pois a partida está no Fim!");
							WarpAPI.setWarp(players, "HGSpawn");
							Protection.setImortal(players, true);
							Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
								public void run() {
									players.sendMessage("§c§l!§7 §fPrepare-se para Lutar §c5"); 
									players.playSound(players.getLocation(), Sound.CLICK, 10.0F, 10.0F);
								}
							}, 20L);
							Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
								public void run() {
									players.sendMessage("§4§l!§7 §fPrepare-se para Lutar §c4"); 
									players.playSound(players.getLocation(), Sound.CLICK, 10.0F, 10.0F);
								}
							}, 40L);
							Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
								public void run() {
									players.sendMessage("§4§l!§7 §fPrepare-se para Lutar §c3"); 
									players.playSound(players.getLocation(), Sound.CLICK, 10.0F, 10.0F);
								}
							}, 60L);
							Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
								public void run() {
									players.sendMessage("§4§l!§7 §fPrepare-se para Lutar §c2"); 
									players.playSound(players.getLocation(), Sound.CLICK, 10.0F, 10.0F);
								}
							}, 80L);
							Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
								public void run() {
									players.sendMessage("§4§l!§7 §fPrepare-se para Lutar §c1"); 
									players.playSound(players.getLocation(), Sound.CLICK, 10.0F, 10.0F);
								}
							}, 100L);
							Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
								public void run() {
									players.sendMessage("§4§l!§7 §fLute!!!"); 
									Protection.setImortal(players, false);
									players.playSound(players.getLocation(), Sound.NOTE_PLING, 10.0F, 10.0F);
								}
							}, 120L);
						}
					}
					if (Iniciando == 180) {
						mandarAvisos();
					}
					if (Iniciando == 120) {
						mandarAvisos();
					}
					if (Iniciando == 60) {
						mandarAvisos();
					}
					if (Iniciando == 30) {
						mandarAvisos();
					}
					if (Iniciando == 15) {
						mandarAvisos();
					}
					if (Iniciando == 5) {
						mandarAvisos();
					}
					if (Iniciando == 4) {
						mandarAvisos();
					}
					if (Iniciando == 3) {
						mandarAvisos();
					}
					if (Iniciando == 2) {
						mandarAvisos();
					}
					if (Iniciando == 1) {
						mandarAvisos();
					}
					if (Iniciando == 0) {
						for(Player players : Bukkit.getOnlinePlayers()){
							if(!EventosAPI.playerPlayingHG(players))continue;
							players.sendMessage("§cO tempo da partida acabou e não houve vencedores");
							WarpAPI.setWarp(players, "Spawn");
							players.getInventory().clear();
						}
						EventosAPI.playersHG.clear();
							for(Block block :sEventos.blocksPlaced){
								block.setType(Material.AIR);
							}
							for(Player players : Bukkit.getOnlinePlayers()){
								if(KitAPI.kits.contains(players.getName())){
							        KitAPI.kits.remove(players.getName());
							    	}
								if(KitAPI.grappler.contains(players.getName())){
							        KitAPI.grappler.remove(players.getName());
							    	}
							    	if(KitAPI.grappler.contains(players.getName())){
							        KitAPI.kangaroo.remove(players.getName());
							    	}
							    	if(KitAPI.grappler.contains(players.getName())){
							        KitAPI.monk.remove(players.getName());
							    	}
							    	if(KitAPI.grappler.contains(players.getName())){
							        KitAPI.ninja.remove(players.getName());
							    	}
							    	if(KitAPI.grappler.contains(players.getName())){
							        KitAPI.stomper.remove(players.getName());
							    	}
							    	if(KitAPI.grappler.contains(players.getName())){
							        KitAPI.switcher.remove(players.getName());
							    	}
							    	if(KitAPI.grappler.contains(players.getName())){
							        KitAPI.thor.remove(players.getName());
							    	}
							}
				                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), (Runnable)new Runnable() {
					                @Override
					                public void run() {
					                	Bukkit.broadcastMessage("§2§lSpectrePvP §fPartida de §eHG §finiciando digite §a/simulador§f para entrar");
										sTempoIniciar.Iniciando=121;
										sTempoIniciar.CancelarTempo();
										
										Main.EstadoHG = simulador.EstadoHG.INICIANDO;
										sTempoAcabar.Iniciando=901;
										sTempoAcabar.CancelarTempo();
										sTempoInvencible.CancelarTempo();
										sTempoInvencible.Iniciando= 121;
										
										new sTempoIniciar();
					                }
					            }, 400L);
						
					}
				}
			}, 0L, 20L));
		}
	}

}
