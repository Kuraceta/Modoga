package kCommandCommands;

import java.lang.management.ManagementFactory;

import javax.management.Attribute;
import javax.management.AttributeList;
import javax.management.MBeanServer;
import javax.management.ObjectName;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;

import api.Lag;
import api.MessageAPI;

public class CmdLag implements CommandExecutor{
	
	public static int TickCount = 0;
    public static long[] Ticks = new long[63];
    public static long LastTick = 0L;
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		Player p = (Player)arg0;
		if(arg1.getName().equalsIgnoreCase("ping")) {
			if(arg3.length == 0) {
				p.sendMessage("§e§lPING §fseu ping é de §a"+((CraftPlayer) p).getHandle().ping+"ms");
				return true;
			}
			Player target = Bukkit.getPlayer(arg3[0]);
			if(target == null) {
				p.sendMessage(MessageAPI.Command_Error+"Jogador não encontrado");
				return true;
			}
			p.sendMessage("§e§lPING §6"+target.getName()+" §festá com um ping de §a"+((CraftPlayer)target).getHandle().ping+"ms");
			return true;
		}
		if(arg1.getName().equalsIgnoreCase("lag")){
		    int ping = ((CraftPlayer) p).getHandle().ping;
		    final long RAM_TOTAL = Runtime.getRuntime().totalMemory();
		    final long RAM_FREE = Runtime.getRuntime().freeMemory();
		    final long RAM_USED = RAM_TOTAL - RAM_FREE;
		    final double RAM_USED_PERCENTAGE = (RAM_USED * 100) / RAM_TOTAL;
		    double tps = Lag.getTPS();
		    double lag = Math.round((1.0D - tps / 20.0D) * 100.0D);
		    double usedCPU = 0.0;
			try {
				usedCPU = getProcessCpuLoad();
			} catch (Exception e) {
			}
			p.sendMessage("§7 ");
		    p.sendMessage("§7Coletando informações...");
		    p.sendMessage("§7Você está no Servidor§8: §2§lSpectrePvP§8, §7online aqui§8: §a"+Bukkit.getOnlinePlayers().length);
		    p.sendMessage("  §c- §7Uso de processamento§8: §a"+usedCPU+" %§8, §7lag§8: §a"+(usedCPU >= 90? "sim":"nao"));
		    p.sendMessage("  §c- §7Sobrecarregamento geral§8: §a"+RAM_USED_PERCENTAGE+" %§8, §7lag§8: §a"+(RAM_USED_PERCENTAGE >= 90? "sim":"nao"));
		    p.sendMessage("  §c- §7Seu ping é de§8: §a"+ping+" ms§8, §7lag§8: §a"+(ping >= 250? "sim":"nao"));
		    p.sendMessage("  §c- §7Servidor rodando em§8: §a"+Lag.getTPS()+" TPS§8, §7lag§8: §a"+lag+" %");
		    p.sendMessage(getString((RAM_USED_PERCENTAGE >= 90? "sim":"nao"),(ping >= 450? "sim":"nao"),(usedCPU >= 90? "sim":"nao")));
		    p.sendMessage("§7 ");
		    return true;
		}
		return false;
	}
	
	public static double getTPS() {
        return getTPS(100);
    }
    
    public static double getTPS(final int ticks) {
        if (TickCount < ticks) {
            return 20.0;
        }
        final int target = (TickCount - 1 - ticks) % Ticks.length;
        final long elapsed = System.currentTimeMillis() - Ticks[target];
        if (ticks / (elapsed / 1000.0) > 20.0) {
            return 20.0;
        }
        return ticks / (elapsed / 1000.0);
    }
    
    public static double getElapsed(final int tickID) {
        if (TickCount - tickID >= Ticks.length) {
            return (TickCount - tickID) * getTPS();
        }
        final long time = Ticks[tickID % Ticks.length];
        return System.currentTimeMillis() - time;
    }
    
    public void run() {
        Ticks[TickCount % Ticks.length] = System.currentTimeMillis();
        ++TickCount;
    }

	public String getString(String lagram,String lagping,String lagprocess){
		if(lagram.equalsIgnoreCase("nao")&&lagping.equalsIgnoreCase("nao")&&lagprocess.equalsIgnoreCase("nao")){
			return "§aO Servidor e sua Internet estão em excelente estado.";
		}
		if(lagram.equalsIgnoreCase("sim")&&lagping.equalsIgnoreCase("nao")&&lagprocess.equalsIgnoreCase("nao")){
			return "§2O Servidor e sua Internet estão em ótimo estado.";
		}
		if(lagram.equalsIgnoreCase("sim")&&lagping.equalsIgnoreCase("sim")&&lagprocess.equalsIgnoreCase("nao")){
			return "§6O Servidor e sua Internet estão em bom estado.";
		}
		if(lagram.equalsIgnoreCase("sim")&&lagping.equalsIgnoreCase("sim")&&lagprocess.equalsIgnoreCase("sim")){
			return "§cO Servidor e sua Internet estão em péssimo estado.";
		}
		if(lagram.equalsIgnoreCase("nao")&&lagping.equalsIgnoreCase("sim")&&lagprocess.equalsIgnoreCase("nao")){
			return "§cSua Internet não está em boas condições";
		}
		return "";
	}
	
	public static double getProcessCpuLoad() throws Exception {

	    MBeanServer mbs    = ManagementFactory.getPlatformMBeanServer();
	    ObjectName name    = ObjectName.getInstance("java.lang:type=OperatingSystem");
	    AttributeList list = mbs.getAttributes(name, new String[]{ "ProcessCpuLoad" });

	    if (list.isEmpty())     return Double.NaN;

	    Attribute att = (Attribute)list.get(0);
	    Double value  = (Double)att.getValue();

	    // usually takes a couple of seconds before we get real values
	    if (value == -1.0)      return Double.NaN;
	    // returns a percentage value with 1 decimal point precision
	    return ((int)(value * 1000) / 10.0);
	}
	
}
