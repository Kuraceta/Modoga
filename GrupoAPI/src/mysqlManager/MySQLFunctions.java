package mysqlManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.UUID;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import mysqlConnection.Data;

public class MySQLFunctions {
	
	  public static void CriarTabela()
	  {
	    try
	    {
	      PreparedStatement ps = Data.con.prepareStatement("CREATE TABLE IF NOT EXISTS grupos (UUID VARCHAR(100), NICK VARCHAR(100), Grupo VARCHAR(100), Lenght VARCHAR(100))");
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
				ResultSet res = Data.con.createStatement().executeQuery("SELECT * FROM grupos WHERE UUID='" + p.getUniqueId().toString() + "'");
				res.next();
				return res.getString("Grupo");
		    }
		    catch (Exception ex)
		    {
		      ex.printStackTrace();
		    }
		    return "Membro";
		}
		
		public static void setGroup(OfflinePlayer p , String i) {
			try {
				Data.con.createStatement().executeUpdate("UPDATE grupos SET Grupo='"+i+"' WHERE UUID='"+p.getUniqueId().toString()+"'");
			}catch (Exception e) {};
		}
		
		public static void setGroup(OfflinePlayer p , String i,long lenght) {
			try {
				Data.con.createStatement().executeUpdate("UPDATE grupos SET Grupo='"+i+"', Lenght='"+lenght+"' WHERE UUID='"+p.getUniqueId().toString()+"'");
			}catch (Exception e) {}
		}
		
		public static Long getLenght(OfflinePlayer p) {
			try
		    {
				ResultSet res = Data.con.createStatement().executeQuery("SELECT * FROM grupos WHERE UUID='" + p.getUniqueId().toString() + "'");
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
				Data.con.createStatement().executeUpdate("UPDATE grupos SET Lenght='"+(String.valueOf(lenght))+"' WHERE UUID='"+p.getUniqueId().toString()+"'");
			}catch (Exception e) {}
		}
		
		public static boolean VerificarRegistro(UUID p)
		  {
			  try
			    {
			      PreparedStatement ps = Data.con.prepareStatement("SELECT * FROM grupos WHERE UUID= ?");
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
		            	try
		            	{
		            		Data.con.createStatement().executeUpdate("INSERT INTO `grupos` (`UUID`,`NICK`, `Grupo`, `Lenght`) VALUES ('" + p.getUniqueId().toString()+"','"+p.getName()+"', 'Membro', '-1')");
		            	}
		            	catch (Exception ex)
		            	{
		            		ex.printStackTrace();
		            	}
		  }
}
