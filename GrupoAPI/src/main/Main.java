package main;

import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import api.GroupAPI;
import api.NameTagAPI;
import apiDaTab.TituloAPI;
import comandos.cGroup;
import comandos.cGroupos;
import comandos.cTempGroup;
import config.Config;
import config.GroupConfig;
import mysqlConnection.Data;
import mysqlConnection.kMySQL;
import mysqlManager.MySQLFunctions;
import mysqlMysql.MySQL;

public class Main extends JavaPlugin implements Listener{
	
	private static Plugin plugin;
	
	@Override
	public void onEnable() {
		plugin = this;
		new Config(this);
		new GroupConfig(this);
		MySQL msql = new MySQL(kMySQL.ip, kMySQL.porta, kMySQL.usuario, kMySQL.senha);
		try {
			Data.con = msql.openConnection();
			Data.statement = Data.con.createStatement();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		MySQLFunctions.CriarTabela();
		getCommand("grupo").setExecutor(new cGroup());
		getCommand("tempgrupo").setExecutor(new cTempGroup());
		getCommand("grupos").setExecutor(new cGroupos());
		PluginManager pm = Bukkit.getServer().getPluginManager();
		GroupAPI.loadGroups();
		pm.registerEvents(this, this);
		for (final org.bukkit.World world : Bukkit.getWorlds())
        {
            world.setGameRuleValue("doDaylightCycle", new StringBuilder().append(world.getName()).toString());
            world.setTime(4000L);
        }
	}

	public static Plugin getPlugin() {
		return plugin;
	}
	
	@EventHandler
	public void onJin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		if(!MySQLFunctions.VerificarRegistro(e.getPlayer().getUniqueId())) {
			MySQLFunctions.Registro(e.getPlayer());
		}
		if(GroupAPI.inTempGroup(p)) {
			long banTime = (long)MySQLFunctions.getLenght(p);
			if (banTime < System.currentTimeMillis())
		      {
				String grupo = GroupAPI.getGroup(p);
				GroupAPI.setGroup(p, "Membro");
				p.sendMessage(" ");
				p.sendMessage("§cSeu Grupo "+GroupAPI.getColor(grupo)+grupo+" §cexpirou.");
				p.sendMessage(" ");
				TituloAPI.mandarTitulo(p, "§a§lGRUPO");
				TituloAPI.mandarSubTitulo(p, "§cSeu Grupo "+GroupAPI.getColor(grupo)+grupo+" §cexpirou.");
		      }
			}
		Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
			public void run() {
				NameTagAPI.setupTag(p);
			}
		}, 10L);
	}

}
