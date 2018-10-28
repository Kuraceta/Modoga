package simulador;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;

import api.WarpAPI;
import kConfig.WarpConfig;
import main.Main;
import mysqlManager.Status;
import score.ScoreBoarding;

public class EventosAPI {
	
	public static ArrayList<String> playersHG = new ArrayList<String>();
	
		public static void CheckarGanhador(Player p) {
			
			if (playersHG.size() == 1&&Main.EstadoHG!=EstadoHG.INICIANDO) {
				Bukkit.broadcastMessage("§e"+p.getName()+" §aGanhou a Partida do §eSimulador");
				
				p.sendMessage("§aParabéns você venceu a partida de HungerGames e ganhou §c200 coins");
				Status.addCoins(p, 200);
				
				sTempoIniciar.CancelarTempo();
				WarpAPI.setWarp(p, "Spawn");
				p.getInventory().clear();
				EventosAPI.playersHG.clear();
				for(Block block :sEventos.blocksPlaced){
					block.setType(Material.AIR);
				}
				ScoreBoarding.setScoreBoard(p);
				 World world =Bukkit.getWorld(WarpConfig.getConfig().getString("Warps.HGInicio.World"));
	                List<Entity> entList = world.getEntities();
	               
	                for(Entity current : entList){
	                    if (current instanceof Item){
	                        current.remove();
	                    }
	                }
	                for (final Chunk chunk : world.getLoadedChunks()) {
	                	world.unloadChunk(chunk);
                    }
		                	Bukkit.broadcastMessage("§2§lSpectrePvP §7Partida de §eHG §7iniciando digite §a/simulador§7 para entrar");
							sTempoIniciar.Iniciando=121;
							sTempoIniciar.CancelarTempo();
							
							Main.EstadoHG = simulador.EstadoHG.INICIANDO;
							sTempoAcabar.Iniciando=901;
							sTempoAcabar.CancelarTempo();
							sTempoInvencible.CancelarTempo();
							sTempoInvencible.Iniciando= 121;
			        KitAPI.kits.clear();
			        KitAPI.grappler.clear();
			        KitAPI.monk.clear();
			        KitAPI.kangaroo.clear();
			        KitAPI.stomper.clear();
			        KitAPI.ninja.clear();
			        KitAPI.switcher.clear();
			        KitAPI.viper.clear();
			        KitAPI.thor.clear();

			}
	}
	
	public static boolean playerPlayingHG(Player p){
		for(int i = 0;i<playersHG.size();i++){
			if(p.getName().equalsIgnoreCase(playersHG.get(i))){
				return true;
			}
		}
		return false;
	}
	

}
