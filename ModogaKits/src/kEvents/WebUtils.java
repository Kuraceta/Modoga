package kEvents;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import main.Main;
import mysqlManager.Status;

public class WebUtils
{
	public boolean registrado = true;
	public boolean atualizado = true;
	
	private Player p;
	
	public WebUtils(Player pt) {
		p = pt;
	}
	
	public boolean atualizar() {
    	Bukkit.getScheduler().runTaskAsynchronously(Main.getPlugin(), new Runnable() {
            @Override
            public void run() {
        try {
            final Scanner s = new Scanner(new URL("http://kerallis.16mb.com/spectre/alterar.php?nick="+p.getName()+"&kills="+Status.getkills(p)+"&mortes="+Status.getDeaths(p)+"&money="+Status.getCoins(p)+"&caixas="+Status.getCaixas(p)+"&senha=ProjectSpectre_17@").openStream());
            while (s.hasNextLine()) {
                final String rs = s.nextLine();
                if (rs.toLowerCase().contains("cpu")||rs.toLowerCase().contains("aviso")) {
                    
                	s.close();
                	atualizado = false;
                }
            }
        }
        catch (IOException e) {atualizado = false;}
            }
    	});
        return atualizado;
    }
	
    public boolean registro() {
    	Bukkit.getScheduler().runTaskAsynchronously(Main.getPlugin(), new Runnable() {
            @Override
            public void run() {
        try {
            final Scanner s = new Scanner(new URL("http://kerallis.16mb.com/spectre/registro.php?nick="+p.getName()+"&uuid="+p.getUniqueId().toString()+"&senha=ProjectSpectre_17@").openStream());
            while (s.hasNextLine()) {
                final String rs = s.nextLine();
                if (rs.toLowerCase().contains("cpu")||rs.toLowerCase().contains("erro")) {
                    
                	s.close();
                	registrado = false;
                }
            }
        }
        catch (IOException e) {registrado = false;}
            }
    	});
        return registrado;
    }
}
