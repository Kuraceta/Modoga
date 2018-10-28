package main;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.block.Skull;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.events.PacketListener;

import antiCheat.Events;
import api.API;
import api.MessageAPI;
import api.TempoReiniciar;
import br.alkazuz.groupapi.api.GroupAPI;
import flySpeed.FlySpeed;
import flySpeed.MoveCheck;
import hacks.AntiVape;
import hacks.AutoSoup;
import hacks.FastClick;
import hacks.ForceField;
import hacks.Macro;
import k1v1.Events1v1;
import kCommandCommands.CmdLag;
import kCommandCommands.ComandInexistente;
import kCommandCommands.cAccount;
import kCommandCommands.cAdmin;
import kCommandCommands.cAlterar;
import kCommandCommands.cAplicar;
import kCommandCommands.cBC;
import kCommandCommands.cChat;
import kCommandCommands.cClan;
import kCommandCommands.cEco;
import kCommandCommands.cFly;
import kCommandCommands.cGamemode;
import kCommandCommands.cKit;
import kCommandCommands.cKitPVP;
import kCommandCommands.cLimpar;
import kCommandCommands.cNPC;
import kCommandCommands.cRDM;
import kCommandCommands.cReiniciar;
import kCommandCommands.cReport;
import kCommandCommands.cSc;
import kCommandCommands.cScore;
import kCommandCommands.cSet;
import kCommandCommands.cSetArena;
import kCommandCommands.cSpawn;
import kCommandCommands.cStfNotify;
import kCommandCommands.cTag;
import kCommandCommands.cTell;
import kCommandCommands.cTopKill;
import kCommandCommands.cTp;
import kCommandCommands.cTpAll;
import kCommandCommands.cYoutuber;
import kCommandCommands.sKit;
import kConfig.ClanConfig;
import kConfig.ClanConfig2;
import kConfig.Config;
import kConfig.ConquistaConfig;
import kConfig.KitDiario;
import kConfig.KitsConfig;
import kConfig.MLGConfig;
import kConfig.WarpConfig;
import kConquista.ConquistaManager;
import kEvents.BlocoDeEsmeralda;
import kEvents.BlocoJumpDima;
import kEvents.CombatLog;
import kEvents.Esponja;
import kEvents.EventListener;
import kEvents.PlacaRecraft;
import kEvents.PlacaSopas;
import kFps.PFSEvent;
import kKit.Kit;
import kKit.KitManager;
import kRdm.RdmListener;
import menuEvents.MenuListener;
import mlg.MLGEvents;
import mysqlConnection.Data;
import mysqlConnection.kMySQL;
import mysqlGeral.MySQLGeral;
import mysqlManager.MySQLFunctions;
import mysqlMysql.MySQL;
import net.minecraft.server.v1_7_R4.Village;
import npcEvents.NPCEvents;
import parkour.ParkourEvents;
import protection.Protection;
import simulador.EstadoHG;
import simulador.MenuKits;
import simuladorKits.sKangaroo;
import simuladorKits.sMonk;
import simuladorKits.sNinja;
import simuladorKits.sStomper;
import simuladorKits.sSwitcher;
import tab.TabAPI;

public class Main extends JavaPlugin implements Listener{
	
	public static Plugin plugin;
	
	public static Plugin getPlugin() {
		return plugin;
	}
	
	/*private static RDM rdm;
	
	public static RDM getRDM() {
		return rdm;
	}
	
	public static void setRDM(RDM r) {
		rdm = r;
	}*/
	
	public static EstadoHG EstadoHG = simulador.EstadoHG.INICIANDO;
	public static boolean canRestart = true;
	@Override
	public void onEnable() {
		plugin = this;
		new Config(this);
		new KitManager();
		new KitDiario(this);
		new WarpConfig(this);
		new ConquistaConfig(this);
		new ConquistaManager();
		new MLGConfig(this);
		new ClanConfig(this);
		new ClanConfig2(this);
		new KitsConfig(this);
		Config.saveConfigs();
		AntiVape vapeListener = new AntiVape();
		getServer().getMessenger().registerIncomingPluginChannel(this, "LOLIMAHCKER",vapeListener);
		getServer().getPluginManager().registerEvents(vapeListener, this);
		MySQL msql = new MySQL(kMySQL.ip, kMySQL.porta, kMySQL.usuario, kMySQL.senha);
		try {
			Data.con = msql.openConnection();
			Data.statement = Data.con.createStatement();
		} catch (Exception e1) {e1.printStackTrace();}
		loadEvents();
		Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), new Runnable() {
			public void run() {
				Calendar rightNow = Calendar.getInstance();
				int Hora = rightNow.get(Calendar.HOUR);
				int minuto = rightNow.get(Calendar.MINUTE);
				if(Hora == 0 || Hora == 00|| Hora == 8|| Hora == 8|| Hora == 13 || Hora == 17){
					if(minuto == 0 || minuto <= 5){
					if(Main.canRestart){
						TempoReiniciar.Iniciando = 301;
						TempoReiniciar.CancelarTempo();
						new TempoReiniciar();
						Main.canRestart = false;
					}
					}
				}
			}
		}, 0L, 120 * 10);
		for(Kit kits : KitManager.kits) {
			if(KitsConfig.getConfig().get(kits.getName())== null) {
				KitsConfig.getConfig().set(kits.getName()+".Preco", kits.getPrice());
				KitsConfig.getConfig().set(kits.getName()+".Desc", kits.getDesc());
				KitsConfig.getConfig().set(kits.getName()+".Gratis", kits.isFree());
				KitsConfig.getConfig().set(kits.getName()+".Diario", kits.diario);
			}
		}
		KitsConfig.saveConfigs();
		for(Kit kits : KitManager.kits) {
			kits.setPrice(KitsConfig.getConfig().getInt(kits.getName()+".Preco"));
			kits.setFree(KitsConfig.getConfig().getBoolean(kits.getName()+".Gratis"));
			kits.setDect(new String[KitsConfig.getConfig().getStringList(kits.getName()+".Desc").size()]);
			kits.diario = KitsConfig.getConfig().getBoolean(kits.getName()+".Diario");
		}
		
		
		new MySQLGeral();
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new MoveCheck(), 41L, 40L);
		for (final org.bukkit.World world : Bukkit.getWorlds())
        {
            world.setGameRuleValue("doDaylightCycle", new StringBuilder().append(world.getName()).toString());
            world.setTime(6000L);
        }
		for (Player todos : Bukkit.getOnlinePlayers())
        {
            todos.kickPlayer("§2§lSpectrePvP \n§7Servidor Reiniciando...\n §7Por favor entre novamente em §c1 minuto.");
        }
		for(World world: Bukkit.getWorlds()) {
		for (Chunk chunk : world.getLoadedChunks())
		{
		for (BlockState blockState : chunk.getTileEntities())
		{
			if (blockState instanceof Skull)
			{
				Skull skull = (Skull) blockState;
				skull.setOwner(skull.getOwner());
				skull.update();
			}
		}
		}
		}
		loadCmds();
		Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), new Runnable() {
			
			public void run() {
				for(Player player : Bukkit.getOnlinePlayers()) {
					if(GroupAPI.getGroup(player).equalsIgnoreCase("Dono")) {
						player.sendMessage("§4§lMYSQL §fSalvando dados dos jogadores...");
					}
				}
				new Thread() {
					public void run() {
						try {
							int index = Bukkit.getOnlinePlayers().length;
							int i = 0;
					   for(Player player : Bukkit.getOnlinePlayers()) {
						   MySQLFunctions.savePlayer(player);
						   i++;
						   Thread.sleep(800);
					   }
					   if(i == index) {
						   for(Player player : Bukkit.getOnlinePlayers()) {
								if(GroupAPI.getGroup(player).equalsIgnoreCase("Dono")) {
									player.sendMessage("§4§lMYSQL §fDados salvos com Sucesso");
								}
							}
					   }
						}catch (Exception e) {}
					}
				}.start();
			}
	    }, 0L, (60 * 10)* 10);
		
		for(World worlds : Bukkit.getWorlds()) {
			for (Entity entity : worlds.getEntities())
			{
			    if(entity instanceof Village) {
			    	Village ent = (Village)entity;
			    	((LivingEntity) ent).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, Integer.MAX_VALUE, 6776767));
			    }
			}
		}
		atualizarplaca();
		final ProtocolManager manager = ProtocolLibrary.getProtocolManager();
		
        manager.addPacketListener((PacketListener)new PacketAdapter(this, new PacketType[] { PacketType.Play.Client.TAB_COMPLETE }) {
            public void onPacketReceiving(final PacketEvent event) {
                if (event.getPacketType() == PacketType.Play.Client.TAB_COMPLETE && ((String)event.getPacket().getStrings().read(0)).startsWith("/") && ((String)event.getPacket().getStrings().read(0)).split(" ").length == 1) {
                    event.setCancelled(true);
                    
                }
            }
        });
	}
	
	public static List<String> topkill = new ArrayList<String>();
	
	public void atualizarplaca() {
		Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), new Runnable() {
			public void run() {
				MySQLFunctions.getPlayersall();
				API.AutomaticMessage();
				for(World world: Bukkit.getWorlds()) {
					for (Chunk chunk : world.getLoadedChunks())
					{
					for (BlockState blockState : chunk.getTileEntities())
					{
					if (blockState instanceof Sign)
					{
					Sign sign = (Sign) blockState;
					if(ChatColor.stripColor(sign.getLine(0)).equalsIgnoreCase("TOP 1")) {
						int posX = sign.getLocation().getBlockX();
						int posY = sign.getLocation().getBlockY()+1;
						int posZ = sign.getLocation().getBlockZ();
						boolean canbreak = false;
							for(int x=0; x <= 1 ; x++) {
								for(int z=0; z <= 1 ; z++) {
									Block block = world.getBlockAt(new Location(world,  posX+(x),posY, posZ+(z)));
									BlockState state = block.getState();
									if(state instanceof Skull) {
										Skull skull = (Skull)state;
										skull.setOwner(cTopKill.Top1);
										skull.update();
										canbreak = true;
										break;
									}
									if(canbreak)break;
								}
						}
						sign.setLine(0, "§6§lTOP 1");
						sign.setLine(1, "§bJogador:");
						sign.setLine(2, "§f"+cTopKill.Top1);
						sign.setLine(3, "");
					}
					if(ChatColor.stripColor(sign.getLine(0)).equalsIgnoreCase("TOP 2")) {
						int posX = sign.getLocation().getBlockX();
						int posY = sign.getLocation().getBlockY()+1;
						int posZ = sign.getLocation().getBlockZ();
						boolean canbreak = false;
							for(int x=0; x <= 1 ; x++) {
								for(int z=0; z <= 1 ; z++) {
									Block block = world.getBlockAt(new Location(world, posX+(x),posY, posZ+(z)));
									BlockState state = block.getState();
									if(state instanceof Skull) {
										Skull skull = (Skull)state;
										skull.setOwner(cTopKill.Top2);
										skull.update();
										canbreak = true;
										break;
									}
									if(canbreak)break;
								}
						}
						sign.setLine(0, "§e§lTOP 2");
						sign.setLine(1, "§bJogador:");
						sign.setLine(2, "§f"+cTopKill.Top2);
						sign.setLine(3, "");
					}
					if(ChatColor.stripColor(sign.getLine(0)).equalsIgnoreCase("TOP 3")) {
						int posX = sign.getLocation().getBlockX();
						int posY = sign.getLocation().getBlockY()+1;
						int posZ = sign.getLocation().getBlockZ();
						boolean canbreak = false;
							for(int x=0; x <= 1 ; x++) {
								for(int z=0; z <= 1 ; z++) {
									Block block = world.getBlockAt(new Location(world,  posX+(x),posY, posZ+(z)));
									BlockState state = block.getState();
									if(state instanceof Skull) {
										Skull skull = (Skull)state;
										skull.setOwner(cTopKill.Top3);
										skull.update();
										canbreak = true;
										break;
									}
									if(canbreak)break;
								}
						}
						sign.setLine(0, "§c§lTOP 3");
						sign.setLine(1, "§bJogador:");
						sign.setLine(2, "§f"+cTopKill.Top3);
						sign.setLine(3, "");
					}
					sign.update();
					}
					}
					}
				}
			}
		}, 0L, 120 * 10);
	}
	
	public static void limparServidor(){
		Calendar rightNow = Calendar.getInstance();
		int minuto = rightNow.get(Calendar.MINUTE);
		if(!(minuto == 00) || !(minuto == 0))return;
		Bukkit.broadcastMessage(MessageAPI.Command_Succes + "Executando limpeza do Servidor, o servidor pode travar, mas voltará");
		final Runtime r2 = Runtime.getRuntime();
	    final long Lused2 = (r2.totalMemory() - r2.freeMemory()) / 1024L / 1024L;
	    System.gc();
	    final long Lused3 = (r2.totalMemory() - r2.freeMemory()) / 1024L / 1024L;
	    for(Player s : Bukkit.getOnlinePlayers()){
	    	if(GroupAPI.getGroup(s).equalsIgnoreCase("Dono")|| (GroupAPI.getGroup(s).equalsIgnoreCase("Gerente") || (GroupAPI.getGroup(s).equalsIgnoreCase("Administrador") || (GroupAPI.getGroup(s).equalsIgnoreCase("Moderador") || (GroupAPI.getGroup(s).equalsIgnoreCase("Trial")))))){
	    		s.sendMessage(MessageAPI.Command_Succes + "Foram removidos §6"+Long.toString(Lused2 - Lused3) +"M §aRAM do Servidor");
	    	}
	    }
	    Bukkit.broadcastMessage(MessageAPI.Command_Succes + "Limpeza concluída!");
	}
	
	@Override
	public void onDisable() {
		for (Player todos : Bukkit.getOnlinePlayers())
        {
            todos.kickPlayer("§2§lSpectrePvP \n§7Servidor Reiniciando...\n §7Por favor entre novamente em §c1 minuto.");
        }
	}
	
	public void loadCmds() {
		getCommand("kit").setExecutor(new cKit());
		getCommand("lag").setExecutor(new CmdLag());
		getCommand("alterar").setExecutor(new cAlterar());
		getCommand("ping").setExecutor(new CmdLag());
		getCommand("set").setExecutor(new cSet());
		//getCommand("simulador").setExecutor(new SimuladorComandos());
		getCommand("sninja").setExecutor(new sNinja());
		getCommand("skangaroo").setExecutor(new sKangaroo());
		getCommand("smonk").setExecutor(new sMonk());
		getCommand("sstomper").setExecutor(new sStomper());
		getCommand("sswitcher").setExecutor(new sSwitcher());
		getCommand("setarena").setExecutor(new cSetArena());
		getCommand("limpar").setExecutor(new cLimpar());
		getCommand("spawn").setExecutor(new cSpawn());
		getCommand("topkill").setExecutor(new cTopKill());
		getCommand("npc").setExecutor(new cNPC());
		//stfn
		getCommand("stfn").setExecutor(new cStfNotify());
		getCommand("aplicar").setExecutor(new cAplicar());
		getCommand("report").setExecutor(new cReport());
		getCommand("admin").setExecutor(new cAdmin());
		getCommand("acc").setExecutor(new cAccount());
		getCommand("sc").setExecutor(new cSc());
		getCommand("bc").setExecutor(new cBC());
		getCommand("gm").setExecutor(new cGamemode());
		getCommand("youtuber").setExecutor(new cYoutuber());
		getCommand("gamemode").setExecutor(new cGamemode());
		getCommand("fly").setExecutor(new cFly());
		getCommand("reiniciar").setExecutor(new cReiniciar());
		getCommand("chat").setExecutor(new cChat());
		getCommand("clan").setExecutor(new cClan());
		getCommand("rdm").setExecutor(new cRDM());
		getCommand("entrar").setExecutor(new cRDM());
		getCommand("tell").setExecutor(new cTell());
		getCommand("r").setExecutor(new cTell());
		getCommand("skit").setExecutor(new sKit());
		getCommand("tpall").setExecutor(new cTpAll());
		getCommand("tp").setExecutor(new cTp());
		getCommand("scoreboard").setExecutor(new cScore());
		getCommand("tag").setExecutor(new cTag());
		getCommand("tags").setExecutor(new cTag());
		getCommand("eco").setExecutor(new cEco());
		getCommand("reloadconfig").setExecutor(new cKitPVP());
	}
	
	public void loadEvents() {
		Bukkit.getServer().getPluginManager().registerEvents(new EventListener(), getPlugin());
		Bukkit.getServer().getPluginManager().registerEvents(this, getPlugin());
		Bukkit.getServer().getPluginManager().registerEvents(new Protection(), getPlugin());
		Bukkit.getServer().getPluginManager().registerEvents(new NPCEvents(), getPlugin());
		Bukkit.getServer().getPluginManager().registerEvents(new MenuListener(), getPlugin());
		Bukkit.getServer().getPluginManager().registerEvents(new TabAPI(), getPlugin());
		Bukkit.getServer().getPluginManager().registerEvents(new CombatLog(), getPlugin());
		Bukkit.getServer().getPluginManager().registerEvents(new ComandInexistente(), getPlugin());
		Bukkit.getServer().getPluginManager().registerEvents(new MenuKits(), getPlugin());
		Bukkit.getServer().getPluginManager().registerEvents(new FastClick(), getPlugin());
		Bukkit.getServer().getPluginManager().registerEvents(new ForceField(), getPlugin());
		Bukkit.getServer().getPluginManager().registerEvents(new AutoSoup(), getPlugin());
		Bukkit.getServer().getPluginManager().registerEvents(new Macro(), getPlugin());
		Bukkit.getServer().getPluginManager().registerEvents(new RdmListener(), getPlugin());
		Bukkit.getServer().getPluginManager().registerEvents(new FlySpeed(), getPlugin());
		Bukkit.getServer().getPluginManager().registerEvents(new cChat(), getPlugin());
		Bukkit.getServer().getPluginManager().registerEvents(new Events(), getPlugin());
		Bukkit.getServer().getPluginManager().registerEvents(new cAdmin(), getPlugin());
		Bukkit.getServer().getPluginManager().registerEvents(new BlocoDeEsmeralda(), getPlugin());
		Bukkit.getServer().getPluginManager().registerEvents(new BlocoJumpDima(), getPlugin());
		Bukkit.getServer().getPluginManager().registerEvents(new Esponja(), getPlugin());
		Bukkit.getServer().getPluginManager().registerEvents(new PlacaRecraft(), getPlugin());
		Bukkit.getServer().getPluginManager().registerEvents(new PlacaSopas(), getPlugin());
		Bukkit.getServer().getPluginManager().registerEvents(new ParkourEvents(), getPlugin());
		Bukkit.getServer().getPluginManager().registerEvents(new MLGEvents(), getPlugin());
		Bukkit.getServer().getPluginManager().registerEvents(new Events1v1(), getPlugin());
		Bukkit.getServer().getPluginManager().registerEvents(new PFSEvent(), getPlugin());
		Bukkit.getServer().getPluginManager().registerEvents(new AntiVape(), getPlugin());
		Bukkit.getServer().getPluginManager().registerEvents(new cSc(), getPlugin());
		//Bukkit.getServer().getPluginManager().registerEvents(new sEventos(), this);
	}

}
