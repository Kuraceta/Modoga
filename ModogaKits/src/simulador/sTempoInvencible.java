package simulador;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import main.Main;
import protection.Protection;


public class sTempoInvencible {
	
	public static Integer Tempo;
	public static int Iniciando = 121;
	
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
			mandarBroadcast(ChatColor.GREEN + "Invencibilidade acaba em §e"+FormatoTempo(Iniciando));
		for (Player Jogadores : Bukkit.getOnlinePlayers()) {
			if(EventosAPI.playerPlayingHG(Jogadores)){
			Jogadores.playSound(Jogadores.getLocation(), Sound.CLICK, 10.0F, 10.0F);
			}
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
	public sTempoInvencible() {
		if (Main.EstadoHG == EstadoHG.INICIANDO) {
			Tempo = Integer.valueOf(Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), new Runnable() {
					
				@SuppressWarnings("deprecation")
				public void run() {
					Iniciando -= 1;
					if (Iniciando == 120) {
						for(Player p :Bukkit.getOnlinePlayers()){
							if(EventosAPI.playerPlayingHG(p)){
								if(KitAPI.kangaroo.contains(p.getName())){
									final ItemStack grappl = new ItemStack(Material.FIREWORK);
				                    final ItemMeta grap = grappl.getItemMeta();
				                    grap.setDisplayName("§eKangaroo");
				                    grappl.setItemMeta(grap);
				                    p.getInventory().addItem(new ItemStack[] { grappl });
								}
								if(KitAPI.monk.contains(p.getName())){
									final ItemStack grappl = new ItemStack(Material.BLAZE_ROD);
				                    final ItemMeta grap = grappl.getItemMeta();
				                    grap.setDisplayName("§6Monk");
				                    grappl.setItemMeta(grap);
				                    p.getInventory().addItem(new ItemStack[] { grappl });
								}
								if(KitAPI.switcher.contains(p.getName())){
									final ItemStack grappl = new ItemStack(Material.SNOW_BALL);
				                    final ItemMeta grap = grappl.getItemMeta();
				                    grap.setDisplayName("§5Switcher SnowBall");
				                    grappl.setItemMeta(grap);
				                    grappl.setAmount(6);
				                    p.getInventory().addItem(new ItemStack[] { grappl });
								}
							}
						}
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
							if(EventosAPI.playerPlayingHG(players)){
								Protection.setImortal(players, false);
								players.sendMessage("§cInvencibilidade acabou, boa sorte à todos");
							}
						}
						Main.EstadoHG = EstadoHG.ANDAMENTO;
						CancelarTempo();
						Iniciando = 121;
						new sTempoAcabar();
					}
				}
			}, 0L, 20L));
		}
	}

}
