package tab;

import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.spigotmc.ProtocolInjector;

import net.minecraft.server.v1_7_R4.ChatSerializer;
import net.minecraft.server.v1_7_R4.IChatBaseComponent;
import net.minecraft.server.v1_7_R4.PacketPlayOutChat;

public class TituloAPI {

	public static void mandarTitulo(Player player, String titulo) {
	    if (((CraftPlayer)player).getHandle().playerConnection.networkManager.getVersion() < 45) {
	    	return;
	    }
	    ((CraftPlayer)player).getHandle().playerConnection.sendPacket(new ProtocolInjector.PacketTitle(ProtocolInjector.PacketTitle.Action.TITLE, ChatSerializer.a("{\"text\": \"\"}").a(titulo)));
	}
	
	public static void mandarfull(Player player, String titulo, String titulo2) {
		mandarTitulo(player, titulo);
		mandarSubTitulo(player, titulo2);
	}
	
	public static void mandarSubTitulo(Player player, String titulo) {
	    if (((CraftPlayer)player).getHandle().playerConnection.networkManager.getVersion() < 45) {
	    	return;
	    }
	    ((CraftPlayer)player).getHandle().playerConnection.sendPacket(new ProtocolInjector.PacketTitle(ProtocolInjector.PacketTitle.Action.SUBTITLE, ChatSerializer.a("{\"text\": \"\"}").a(titulo)));
	}
	public static void mandarActionBar(Player player, String titulo) {
		  CraftPlayer p = (CraftPlayer) player;
		  if (p.getHandle().playerConnection.networkManager.getVersion() != 47) {
		   return;
		  }
		
		  IChatBaseComponent cbc = ChatSerializer.a("{\"text\": \"" + titulo + "\"}");
		  PacketPlayOutChat ppoc = new PacketPlayOutChat(cbc);
		  ((CraftPlayer) p).getHandle().playerConnection.sendPacket(ppoc);
		  ((CraftPlayer) p).getHandle().playerConnection.sendPacket(ppoc);
	} 
	public static void clear(Player player) {
	    if (((CraftPlayer)player).getHandle().playerConnection.networkManager.getVersion() < 45) {
	    	return;
	    }
	    ((CraftPlayer)player).getHandle().playerConnection.sendPacket(new ProtocolInjector.PacketTitle(ProtocolInjector.PacketTitle.Action.CLEAR));
	}

}
