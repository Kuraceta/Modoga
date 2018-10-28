package kCommandCommands;

import java.util.HashMap;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import api.MessageAPI;
import br.alkazuz.groupapi.api.GroupAPI;

public class sKit implements Listener, CommandExecutor
{
    public HashMap<String, ItemStack[]> kit= new HashMap<String, ItemStack[]>();
    public HashMap<String, ItemStack[]> armadura= new HashMap<String, ItemStack[]>();
    
    
    public boolean isInt(final String s) {
        try {
            Integer.parseInt(s);
            return true;
        }
        catch (NumberFormatException ex) {
            return false;
        }
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String CommandLabel, final String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§bSomente jogadores podem executar este comando!");
            return true;
        }
        final Player p = (Player)sender;
        if(!GroupAPI.GroupCanExecute(GroupAPI.getGroup(p), cmd.getName())){
			p.sendMessage(MessageAPI.Command_Error+"Comando inexistente ou você não tem acesso");
			return true;
	    }
            if (args.length == 0) {
                p.sendMessage("§2§lSpectrePvP"+" §cUso correto: /skit criar|aplicar [nome]|[raio]");
                return true;
            }
            if (args[0].equalsIgnoreCase("criar")) {
                if (args.length == 1) {
                    p.sendMessage("§2§lSpectrePvP"+" §cUso correto: /skit criar [nome]");
                    return true;
                }
                final String name = args[1];
                this.kit.put(name, p.getInventory().getContents());
                this.armadura.put(name, p.getInventory().getArmorContents());
                p.sendMessage("§2§lSpectrePvP"+" §7Kit §f" + args[1] + " §7criado com sucesso!");
                return true;
            }
            else if (args[0].equalsIgnoreCase("aplicar")) {
                if (args.length <= 2) {
                    p.sendMessage("§2§lSpectrePvP"+" §cUso correto: /skit aplicar [kits] [distancia]");
                    return true;
                }
                final String name = args[1];
                if (!this.kit.containsKey(name) && !this.armadura.containsKey(name)) {
                    p.sendMessage("§2§lSpectrePvP"+" §cKit §e" + name + " §cnao encontrado!");
                    return true;
                }
                if (this.isInt(args[2])) {
                    final int numero = Integer.parseInt(args[2]);
                    for (final Entity ent : p.getNearbyEntities((double)numero, (double)numero, (double)numero)) {
                        if (!(ent instanceof Player)) {
                            continue;
                        }
                        final Player plr = (Player)ent;
                        plr.getInventory().setArmorContents((ItemStack[])this.armadura.get(name));
                        plr.getInventory().setContents((ItemStack[])this.kit.get(name));
                    }
                    p.sendMessage("§2§lSpectrePvP"+" §7Kit §e" + name + " §7aplicado para jogadores em um raio de §6" + numero + " §7blocos");
                    return true;
                }
        }
        return true;
    }

}
