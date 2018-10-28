package api;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import main.Main;

public class TempoReiniciar {
	
	public static Integer Tempo;
	public static int Iniciando = 301;
	
	public TempoReiniciar() {
		Tempo = Integer.valueOf(Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.plugin, new Runnable() {
				
			@SuppressWarnings("deprecation")
			public void run() {
				Iniciando -= 1;
				if (Iniciando == 300) {
					mandarAvisos();
				}
				if (Iniciando == 240) {
					mandarAvisos();
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
					for(Player player : Bukkit.getOnlinePlayers())
					{
						player.kickPlayer("§cServidor Reiniciando...");
					}
						CancelarTempo();
						Iniciando = 301;
						Bukkit.getServer().shutdown();
					}
				}

			
		}, 0L, 20L));
	}
	private void mandarAvisos() {
		Bukkit.broadcastMessage("§d[Server] Servidor Reiniciando em "+FormatoTempo(Iniciando));
		
	}
	public static void CancelarTempo() {
		if (Tempo != null) {
			Bukkit.getScheduler().cancelTask(Tempo);
			Tempo = null;
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

}
