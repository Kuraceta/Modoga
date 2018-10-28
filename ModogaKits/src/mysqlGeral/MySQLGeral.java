package mysqlGeral;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import mysqlConnection.Data;
import mysqlLite.SQLite;

public class MySQLGeral {
	
	private static SQLite lite;
	private static Connection con;
	private static Statement state;
	
	public MySQLGeral() {
		lite= new SQLite("dados.db");
		try {
			con = lite.openConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			state = getConnection().createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		CriarTabela();
	}
	
	public static Statement getStatement() {
		return state;
	}
	
	public static SQLite getSQL() {
		return lite;
	}
	
	public static Connection getConnection() {
		return con;
	}
	
	public static void CriarTabela()
	  {
	    try
	    {
	      PreparedStatement ps = getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS data (UUID VARCHAR(100), NICK VARCHAR(100), Grupo VARCHAR(100), Lenght VARCHAR(100), AcertosM INT(100), Erros INT(100))");
	      ps.executeUpdate();
	    }
	    catch (Exception ex)
	    {
	      ex.printStackTrace();
	    }
	  }
	
	public static String getGroup(OfflinePlayer p) {
		try
	    {
			ResultSet res = Data.statement.executeQuery("SELECT * FROM data WHERE UUID='" + p.getUniqueId().toString() + "'");
			res.next();return res.getString("Grupo");
	    }
	    catch (Exception ex)
	    {
	      ex.printStackTrace();
	    }
	    return "Membro";
	}
	
	public static void setGroup(OfflinePlayer p , String i) {
		try {
			Data.statement.executeUpdate("UPDATE data SET Grupo='"+i+"' WHERE UUID='"+p.getUniqueId().toString()+"'");
		}catch (Exception e) {}
	}
	
	public static int getAcertos(OfflinePlayer p) {
		try
	    {
			ResultSet res = Data.statement.executeQuery("SELECT * FROM data WHERE UUID='" + p.getUniqueId().toString() + "'");
			res.next();return res.getInt("Erros");
	    }
	    catch (Exception ex)
	    {
	      ex.printStackTrace();
	    }
	    return 0;
	}
	
	public static void addAcertos(OfflinePlayer p , int i) {
		try {
			Data.statement.executeUpdate("UPDATE KitPvP SET Erros='"+(getAcertos(p)+i)+"' WHERE UUID='"+p.getUniqueId().toString()+"'");
		}catch (Exception e) {}
	}
	
	public static Long getLenght(OfflinePlayer p) {
		try
	    {
			ResultSet res = Data.statement.executeQuery("SELECT * FROM data WHERE UUID='" + p.getUniqueId().toString() + "'");
			res.next();
			return Long.valueOf(res.getString("Lenght"));
	    }
	    catch (Exception ex)
	    {
	      ex.printStackTrace();
	    }
	    return (long)-1;
	}
	
	public static void setLenght(OfflinePlayer p , long lenght) {
		try {
			Data.statement.executeUpdate("UPDATE KitPvP SET Lenght='"+(String.valueOf(lenght))+"' WHERE UUID='"+p.getUniqueId().toString()+"'");
		}catch (Exception e) {}
	}
	
	public static int getErros(OfflinePlayer p) {
		try
	    {
			ResultSet res = Data.statement.executeQuery("SELECT * FROM data WHERE UUID='" + p.getUniqueId().toString() + "'");
			res.next();return res.getInt("AcertosM");
	    }
	    catch (Exception ex)
	    {
	      ex.printStackTrace();
	    }
	    return 0;
	}
	
	public static void addErros(OfflinePlayer p , int i) {
		try {
			Data.statement.executeUpdate("UPDATE KitPvP SET Vitoria='"+(getAcertos(p)+i)+"' WHERE UUID='"+p.getUniqueId().toString()+"'");
		}catch (Exception e) {}
	}
	
	public static boolean VerificarRegistro(UUID p)
	  {
		  try
		    {
		      PreparedStatement ps = con.prepareStatement("SELECT * FROM data WHERE UUID= ?");
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
	
	public static void Registro(Player p)
	  {
		 new Thread() {
			 @Override
			 public void run() {
	            	try
	            	{
	            		getStatement().executeUpdate("INSERT INTO `data` (`UUID`,`NICK`, `Grupo`, `Lenght`, `AcertosM`, `Erros`) VALUES ('" + p.getUniqueId().toString()+"','"+p.getName()+"', 'Membro', '-1', '0', '0')");
	            	}
	            	catch (Exception ex)
	            	{
	            		ex.printStackTrace();
	            	}
	            }
		  	}.start();
	  }
	
}
