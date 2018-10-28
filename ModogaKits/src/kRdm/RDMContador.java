package kRdm;

import org.bukkit.Bukkit;

import main.Main;
import protection.Protection;

public class RDMContador {
	
	public static Integer Tempo;
	public static int Iniciando = 301;
	
	public RDMContador() {
		Tempo = Integer.valueOf(Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.plugin, new Runnable() {
			public void run() {
				Iniciando -= 1;
				if(RDMApi.status == RDMStatus.RODANDO) {
					CancelarTempo();
					return;
				}
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
					if(RDMApi.playing.size()< 10) {
						Bukkit.broadcastMessage("§4§lRDM §fO Evento não pode ser iniciado porque não há Jogadores Suficientes");
						RDMContador.Tempo = 121;
					}else {
						CancelarTempo();
						Iniciando = 301;
						RDMApi.iniciar();
						Bukkit.broadcastMessage(" ");
						Bukkit.broadcastMessage("§4§lRDM §fEvento RDM foi iniciado");
						Bukkit.broadcastMessage("§4§lRDM §fHá §c"+RDMApi.playing.size()+" §fjogadores Participando");
						Bukkit.broadcastMessage("§4§lRDM §c"+RDMApi.getPlayerOne().getName()+" §fvs §c"+RDMApi.getPlayerTwo().getName());
						Bukkit.broadcastMessage(" ");
						Protection.setImortal(RDMApi.getPlayerTwo(), false);
						Protection.setImortal(RDMApi.getPlayerOne(), false);
					}
				}
			}
		}, 0L, 20L));
	}
	private void mandarAvisos() {
		Bukkit.broadcastMessage(" ");
		Bukkit.broadcastMessage("§4§lRDM §fEvento RDM iniciando em §e"+FormatoTempo(Iniciando));
		Bukkit.broadcastMessage("§4§lRDM §fHá §c"+RDMApi.playing.size()+" §fjogadores Participando");
		Bukkit.broadcastMessage("§4§lRDM §fUse §4/entrar §fpara participar");
		Bukkit.broadcastMessage(" ");
		
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
