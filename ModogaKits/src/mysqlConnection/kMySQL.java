package mysqlConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.Bukkit;

import kConfig.Config;

public class kMySQL
{
  public static String ip = Config.getConfig().getString("MySQL.host");
  public static String porta = Config.getConfig().getString("MySQL.Port");
  public static String database = Config.getConfig().getString("MySQL.Database");
  public static String usuario = Config.getConfig().getString("MySQL.User");
  public static String senha = Config.getConfig().getString("MySQL.Password");
  public static Connection con;
  
  public static boolean j·Conectado()
  {
    return con != null;
  }
  
  public static void conectar()
  {
    if (!j·Conectado()) {
      try
      {
        con = DriverManager.getConnection("jdbc:mysql://" + ip + ":" + porta + "/" + database + "?autoReconnect=true", usuario, senha);
        Bukkit.getConsoleSender().sendMessage("ß7A conex„o com o ßdMySQLß7 foi aceita.");
        Bukkit.getConsoleSender().sendMessage("ß7O Servidor est· conectado ao mysql.");
      }
      catch (SQLException e)
      {
    	  Bukkit.getConsoleSender().sendMessage("ß7A conex„o com o ßdMySQLß7 foi negada.");
      }
    }
  }
  
  public static void Desconectar()
  {
    try
    {
      con.close();
      System.out.print("ßcDesligar conex„o com o MySQL: ßcßlSucesso!");
    }
    catch (SQLException e)
    {
      System.out.print("ßcDesligar conex„o com o MySQL: ßcßlProblema!");
    }
  }
  
  public static PreparedStatement getStatement(String sql)
  {
    if (j·Conectado()) {
      try
      {
        return con.prepareStatement(sql);
      }
      catch (SQLException e)
      {
        e.printStackTrace();
      }
    }
    return null;
  }
  
  public static ResultSet getResult(String sql)
  {
    if (j·Conectado()) {
      try
      {
        PreparedStatement ps = getStatement(sql);
        return ps.executeQuery();
      }
      catch (SQLException e)
      {
        e.printStackTrace();
      }
    }
    return null;
  }
}
