package mysqlManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import kCommandCommands.cTopKill;
import kLogs.LogManager;
import main.Main;
import mysqlConnection.Data;
import mysqlConnection.kMySQL;

public class MySQLFunctions {
	
	public static void setKills(Player p , int i) {
		try {
			Data.statement.executeUpdate("UPDATE kitpvp SET Kills='"+i+"' WHERE UUID='"+p.getUniqueId().toString()+"'");
		}catch (Exception e) {}
	}
	
	public static void setMoney(OfflinePlayer p , int i) {
		try {
			Data.statement.executeUpdate("UPDATE kitpvp SET Money='"+i+"' WHERE UUID='"+p.getUniqueId().toString()+"'");
		}catch (Exception e) {}
	}
	
	public static void setWins(OfflinePlayer p , int i) {
		try {
			Data.statement.executeUpdate("UPDATE kitpvp SET Vitoria='"+i+"' WHERE UUID='"+p.getUniqueId().toString()+"'");
		}catch (Exception e) {}
	}
	
	public static void addWins(Player p , int i) {
		try {
			Data.statement.executeUpdate("UPDATE kitpvp SET Vitoria='"+(getWins(p)+i)+"' WHERE UUID='"+p.getUniqueId().toString()+"'");
		}catch (Exception e) {}
	}
	
	public static void addLoser(Player p , int i) {
		try {
			Data.statement.executeUpdate("UPDATE kitpvp SET Derrotas='"+(getLoser(p)+i)+"' WHERE UUID='"+p.getUniqueId().toString()+"'");
		}catch (Exception e) {}
	}
	
	public static int getLoser(Player p) {
		try
	    {
			ResultSet res = Data.statement.executeQuery("SELECT * FROM kitpvp WHERE UUID='" + p.getUniqueId().toString() + "'");
			res.next();return res.getInt("Derrotas");
	    }
	    catch (Exception ex)
	    {
	      ex.printStackTrace();
	    }
	    return 0;
	}
	
	public static int getWins(Player p) {
		try
	    {
			ResultSet res = Data.statement.executeQuery("SELECT * FROM kitpvp WHERE UUID='" + p.getUniqueId().toString() + "'");
			res.next();return res.getInt("Vitoria");
	    }
	    catch (Exception ex)
	    {
	      ex.printStackTrace();
	    }
	    return 0;
	}
	
	public static void setWins(Player p , int i) {
		try {
			Data.statement.executeUpdate("UPDATE kitpvp SET Vitoria='"+i+"' WHERE UUID='"+p.getUniqueId().toString()+"'");
		}catch (Exception e) {}
	}
	
	public static void setKills(OfflinePlayer p , int i) {
		try {
			Data.statement.executeUpdate("UPDATE kitpvp SET Kills='"+i+"' WHERE UUID='"+p.getUniqueId().toString()+"'");
		}catch (Exception e) {}
	}
	
	public static void setDeath(OfflinePlayer p, int i) {
		try {
			Data.statement.executeUpdate("UPDATE kitpvp SET Deaths='"+i+"' WHERE UUID='"+p.getUniqueId().toString()+"'");
		}catch (Exception e) {}
	}
	
	public static void setDeath(Player p, int i) {
		try {
			Data.statement.executeUpdate("UPDATE kitpvp SET Deaths='"+i+"' WHERE UUID='"+p.getUniqueId().toString()+"'");
		}catch (Exception e) {}
	}
	
	public static int Caixas(Player p)
	  {
		try
	    {
			ResultSet res = Data.statement.executeQuery("SELECT * FROM kitpvp WHERE UUID='" + p.getUniqueId().toString() + "'");
			res.next();return res.getInt("Caixas");
	    }
	    catch (Exception ex)
	    {
	      ex.printStackTrace();
	    }
	    return 0;
	  }
	
	public static void loadPlayer(Player p){
	            Status.kills.put(p, Kills(p));
	            Status.coins.put(p, Money(p));
	            Status.deaths.put(p, Deaths(p));
	            Status.caixas.put(p, Caixas(p));
	            LogManager.createLogStatus("Carregado o Status de "+p.getName()+" Kills:"+Kills(p)+",Money:"+Money(p)+",Mortes:"+Deaths(p)+",Caixas:"+Caixas(p));
	  }
	public static void savePlayer(Player p){
	            	 try
	         	    {
	            		 Data.statement
							.executeUpdate("UPDATE kitpvp SET Kills='" + Status.getkills(p) + "', Money='" +Status.getCoins(p) +"', Caixas='"+Status.getCaixas(p)+"', Deaths='"
									+ Status.getDeaths(p) + "' WHERE UUID='" + p.getUniqueId().toString() + "'");
	         	    }
	         	    catch (Exception ex)
	         	    {
	         	      
	         	    } 
	  }
	
	  public static void CriarTabela()
	  {
	    try
	    {
	      PreparedStatement ps = kMySQL.getStatement("CREATE TABLE IF NOT EXISTS kitpvp (UUID VARCHAR(100), NICK VARCHAR(100), Money INT(100), Kills INT(100), Deaths INT(100), Vitoria INT(100), Derrotas INT(100), Caixas INT(100))");
	      ps.executeUpdate();
	    }
	    catch (Exception ex)
	    {
	      ex.printStackTrace();
	    }
	  }

	  
	  public static void Registro(UUID p,String nick)
	  {
	            	try
	            	{
	            		Data.statement
	            		.executeUpdate("INSERT INTO kitpvp (UUID,NICK, Money, Kills, Deaths, Vitoria, Derrotas, Caixas) VALUES ('" + p.toString()+"','"+nick+"', '0', '0', '0', '0', '0', '0')");
	            	}
	            	catch (Exception ex)
	            	{
	            		ex.printStackTrace();
	            	}
	  }
	  
	  public static void Registro(Player p)
	  {
		            	try
		            	{
		            		Data.statement
		            		.executeUpdate("INSERT INTO kitpvp (UUID,NICK, Money, Kills, Deaths, Vitoria, Derrotas, Caixas) VALUES ('" + p.getUniqueId().toString()+"','"+p.getName()+"', '0', '0', '0', '0', '0', '0')");
		            	}
		            	catch (Exception ex)
		            	{
			      ex.printStackTrace();
			    }
	  }
	  
	  public static boolean VerificarRegistro(UUID p)
	  {
		  try
		    {
		      PreparedStatement ps = Data.con.prepareStatement("SELECT * FROM kitpvp WHERE UUID= ?");
		      ps.setString(1, p.toString());
		      ResultSet rs = ps.executeQuery();
		      boolean user = rs.next();
		      rs.close();
		      rs.close();
		      return user;
		    }
		    catch (Exception ex)
		    {ex.printStackTrace();}
		    return false;
	  }
	  
	  public static List<String> getPlayersall()
	  {
	    List<String> list = new LinkedList<String>();
	    List<String> list2 = new LinkedList<String>();
	    try
	    {
		 PreparedStatement ps = Data.con.prepareStatement("SELECT * FROM kitpvp ORDER BY Kills DESC LIMIT 15");
	      ResultSet rs = ps.executeQuery();
	      int index = 1;
	      while (rs.next()) {
	    	  if(list2.contains(rs.getString("NICK")))continue;
	    	  if(list.size() >= 10)break;
		      list.add("§f"+index+". §7Jogador: §f"+rs.getString("NICK") + " §7Kills: §3" +  rs.getInt("Kills"));
		      index ++;
		      list2.add(rs.getString("NICK"));
	      }
	    }
	    catch (SQLException localSQLException) {localSQLException.printStackTrace();}
	    if(list2.size()>=3) {
	    cTopKill.Top1 = list2.get(0);
	    cTopKill.Top2 = list2.get(1);
	    cTopKill.Top3 = list2.get(2);
	    }
	    Main.topkill = list;
	    return list;
	  }
	  
	  public static int Kills(OfflinePlayer p)
	  {
		  try
		    {
				ResultSet res = Data.statement.executeQuery("SELECT * FROM kitpvp WHERE UUID='" + p.getUniqueId().toString() + "'");
				res.next();return res.getInt("Kills");
		    }
		    catch (Exception ex)
		    {
		      ex.printStackTrace();
		    }
		    return 0;
	  }
	  
	  public static int Kills(Player p)
	  {
		  try
		    {
				ResultSet res = Data.statement.executeQuery("SELECT * FROM kitpvp WHERE UUID='" + p.getUniqueId().toString() + "'");
				res.next();return res.getInt("Kills");
		    }
		    catch (Exception ex)
		    {
		      ex.printStackTrace();
		    }
		    return 0;
	  }
	  
	  public static int Money(Player p)
	  {
		  try
		    {
				ResultSet res = Data.statement.executeQuery("SELECT * FROM kitpvp WHERE UUID='" + p.getUniqueId().toString() + "'");
				res.next();return res.getInt("Money");
		    }
		    catch (Exception ex)
		    {
		      ex.printStackTrace();
		    }
		    return 0;
	  }
	  
	  public static int Deaths(Player p)
	  {
		  try
		    {
				ResultSet res = Data.statement.executeQuery("SELECT * FROM kitpvp WHERE UUID='" + p.getUniqueId().toString() + "'");
				res.next();return res.getInt("Deaths");
		    }
		    catch (Exception ex)
		    {
		      ex.printStackTrace();
		    }
		    return 0;
	  }
	  
	  public static void addKills(Player p, int i)
	  {
		  try {
				Data.statement.executeUpdate("UPDATE kitpvp SET Kills='"+(Deaths(p)+i)+"' WHERE UUID='"+p.getUniqueId().toString()+"'");
			}catch (Exception e) {}
	  }
	  
	  public static void addMoney(Player p, int i)
	  {
		  try {
				Data.statement.executeUpdate("UPDATE kitpvp SET Money='"+(Money(p)+i)+"' WHERE UUID='"+p.getUniqueId().toString()+"'");
			}catch (Exception e) {}
	  }
	  
	  public static void addDeaths(Player p, int i)
	  {
		  try {
				Data.statement.executeUpdate("UPDATE kitpvp SET Deaths='"+(Deaths(p)+i)+"' WHERE UUID='"+p.getUniqueId().toString()+"'");
			}catch (Exception e) {}
	  }
	  
	  
	  public static void removeMoney(Player p, int i)
	  {
		  try {
				Data.statement.executeUpdate("UPDATE kitpvp SET Money='"+(Money(p)-i)+"' WHERE UUID='"+p.getUniqueId().toString()+"'");
			}catch (Exception e) {}
	  }
}
