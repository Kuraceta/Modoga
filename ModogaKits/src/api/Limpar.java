package api;

import org.bukkit.Bukkit;

import main.Main;

public class Limpar {
	
	public static Integer Tempo;
	public static int Iniciando = 0;
	
	public Limpar() {
		Tempo = Integer.valueOf(Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.plugin, new Runnable() {
			public void run() {
				Iniciando += 1;
				if (Iniciando >= 50) {
					Main.limparServidor();
					Iniciando = 0;
				}
				}

		}, 0L, 20L * 60L));
	}
	public static void CancelarTempo() {
		if (Tempo != null) {
			Bukkit.getScheduler().cancelTask(Tempo);
			Tempo = null;
		}
	}

}
