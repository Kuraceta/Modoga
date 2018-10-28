package simulador;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

import api.API;
import api.WarpAPI;
import main.Main;


public class sTempoIniciar {
	
	public static Integer Tempo;
	public static int Iniciando = 121;
	
	public static void CancelarTempo() {
		if (Tempo != null) {
			Bukkit.getScheduler().cancelTask(Tempo);
			Tempo = null;
		}
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
		//mandarBroadcast(ChatColor.DARK_RED + ChatColor.BOLD.toString() + "RDM: " + ChatColor.GRAY + "O Evento Começa em" + ChatColor.RED + FormatoTempo(Iniciando) + ChatColor.GRAY + ", Temos " + ChatColor.WHITE + "(" + PlayerAPI.Participando.size() + "/30)" + ChatColor.GRAY + " Jogadores!");
		mandarBroadcast(ChatColor.GOLD + "Partida começa em §c"+FormatoTempo(Iniciando));
		for (Player Jogadores : Bukkit.getOnlinePlayers()) {
			if(EventosAPI.playerPlayingHG(Jogadores)){
			Jogadores.playSound(Jogadores.getLocation(), Sound.CLICK, 10.0F, 10.0F);
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
	public sTempoIniciar() {
		if (Main.EstadoHG == EstadoHG.INICIANDO) {
			Tempo = Integer.valueOf(Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), new Runnable() {
					
				@SuppressWarnings("deprecation")
				public void run() {
					Iniciando -= 1;
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
					if (Iniciando <= 0) {
						if (EventosAPI.playersHG.size()<= 4) {
							for(Player players : Bukkit.getOnlinePlayers()){
								if(EventosAPI.playerPlayingHG(players)){
									players.sendMessage("§cO minigame não pode ser iniciado, pois não há Players suficientes!");
									Iniciando = 61;
									CancelarTempo();
									players.sendMessage("§cO tempo foi alterado para §e1 minuto(s)");
									new sTempoIniciar();
								}
							}
							return;
						} else {
							for(Player players : Bukkit.getOnlinePlayers()){
								if(EventosAPI.playerPlayingHG(players)){
									players.removePotionEffect(PotionEffectType.SPEED);
									players.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
									WarpAPI.setWarp(players, "HGInicio");
									players.getInventory().clear();
									for(Player p : Bukkit.getOnlinePlayers()){
										if(!EventosAPI.playerPlayingHG(p))continue;
										p.getInventory().setItem(8, API.createItem(p, Material.COMPASS, "§eBussola", new String[] {""}, 1, (short)0));
									}
								}
							}
							CancelarTempo();
							Iniciando = 121;
							new sTempoInvencible();
							Main.EstadoHG = EstadoHG.INVENCIVEL;
						}
					}
				}
			}, 0L, 20L));
		}
	}

}
