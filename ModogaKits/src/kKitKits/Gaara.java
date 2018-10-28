package kKitKits;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import api.API;
import api.MessageAPI;
import kCooldown.CooldownAPI;
import kKit.Kit;
import kKit.KitAPI;
import main.Main;

public class Gaara extends Kit
  implements Listener
{
	
	public Gaara() {
		//super("Gaara", new String[]{"","§7Crie um capsula de areia no jogador"}, new ItemStack(Material.SANDSTONE), "kit.Gaara",KitType.AVANCED);
		super("Gaara", 4000, false, new ItemStack(Material.SANDSTONE), true, "kit.gaara", false,  new String[]{""," §e§l- §7Crie um capsula de areia no jogador"});
	}
	
  private List<UUID> blocos = new ArrayList<UUID>();
  
  @EventHandler
  private void blocosGaara(EntityChangeBlockEvent evento)
  {
    if (this.blocos.contains(evento.getEntity().getUniqueId())) {
      evento.setCancelled(true);
    }
  }
  
  @EventHandler
  private void eventoGaara(final PlayerInteractEntityEvent e)
  {
    final Player p = e.getPlayer();
    if (((e.getRightClicked() instanceof Player)) && (KitAPI.getKitName(p) == "Gaara") && (p.getItemInHand().getType() == Material.SANDSTONE)) {
    	if(CooldownAPI.Cooldown.containsKey(p.getName())){
    		e.setCancelled(true);
			 p.sendMessage(MessageAPI.Command_Error+"Você está com um Cooldown de §e" + CooldownAPI.Cooldown(p) + "s");
			 return;
    	}
      if ((Gladiator.fighting.containsKey(p.getName())) || (Gladiator.noExecut.contains(p)))
      {
        p.sendMessage("§cEste kit nao é permitido no Gladiador");
      }
      else
      {
        CooldownAPI.addCooldown(p, 15);
        API.darEfeito(p, PotionEffectType.SPEED, 10, 1);
        new BukkitRunnable()
        {
          Location[] oldLoc = null;
          Location[] loc = new Location[20];
          int contador = -1;
          
          public void run()
          {
            if ((e.getPlayer() != null) && (e.getRightClicked() != null) && (e.getPlayer().isOnline()) && (((Player)e.getRightClicked()).isOnline()))
            {
              if (++this.contador != 3)
              {
                if (this.oldLoc != null)
                {
                  Location[] oldLoc;
                  int length = (oldLoc = this.oldLoc).length;
                  for (int i = 0; i < length; i++)
                  {
                    Location old = oldLoc[i];
                    old.getBlock().setType(Material.AIR);
                  }
                }
                int x = e.getRightClicked().getLocation().getBlockX();
                int y = e.getRightClicked().getLocation().getBlockY();
                int z = e.getRightClicked().getLocation().getBlockZ();
                e.getRightClicked().teleport(new Location(e.getRightClicked().getWorld(), x + 0.5D, y + 4 + this.contador, z + 0.5D));
                this.loc[0] = e.getRightClicked().getLocation().add(0.0D, -1.0D, 0.0D);
                this.loc[1] = e.getRightClicked().getLocation().add(0.0D, -1.0D, 0.0D);
                this.loc[2] = e.getRightClicked().getLocation().add(1.0D, 1.0D, 1.0D);
                this.loc[3] = e.getRightClicked().getLocation().add(-1.0D, 1.0D, -1.0D);
                this.loc[4] = e.getRightClicked().getLocation().add(1.0D, 1.0D, -1.0D);
                this.loc[5] = e.getRightClicked().getLocation().add(-1.0D, 1.0D, 1.0D);
                this.loc[6] = e.getRightClicked().getLocation().add(0.0D, 0.0D, -1.0D);
                this.loc[7] = e.getRightClicked().getLocation().add(-1.0D, 0.0D, 0.0D);
                this.loc[8] = e.getRightClicked().getLocation().add(1.0D, 0.0D, 0.0D);
                this.loc[9] = e.getRightClicked().getLocation().add(0.0D, 0.0D, 1.0D);
                this.loc[10] = e.getRightClicked().getLocation().add(0.0D, 3.0D, 0.0D);
                this.loc[11] = e.getRightClicked().getLocation().add(0.0D, 2.0D, -1.0D);
                this.loc[12] = e.getRightClicked().getLocation().add(-1.0D, 2.0D, 0.0D);
                this.loc[13] = e.getRightClicked().getLocation().add(1.0D, 2.0D, 0.0D);
                this.loc[14] = e.getRightClicked().getLocation().add(0.0D, 2.0D, 1.0D);
                this.loc[15] = e.getRightClicked().getLocation().add(0.0D, 1.0D, -1.0D);
                this.loc[16] = e.getRightClicked().getLocation().add(-1.0D, 1.0D, 0.0D);
                this.loc[17] = e.getRightClicked().getLocation().add(1.0D, 1.0D, 0.0D);
                this.loc[18] = e.getRightClicked().getLocation().add(0.0D, 1.0D, 1.0D);
                this.loc[19] = e.getRightClicked().getLocation().add(0.0D, 2.0D, 0.0D);
                Location[] loc;
                int length2 = (loc = this.loc).length;
                for (int j = 0; j < length2; j++)
                {
                  Location locais = loc[j];
                  locais.getBlock().setType(Material.SANDSTONE);
                }
                this.oldLoc = this.loc;
              }
              else
              {
                this.loc[0] = e.getRightClicked().getLocation().add(0.0D, -1.0D, 0.0D);
                this.loc[1] = e.getRightClicked().getLocation().add(0.0D, -1.0D, 0.0D);
                this.loc[2] = e.getRightClicked().getLocation().add(1.0D, 1.0D, 1.0D);
                this.loc[3] = e.getRightClicked().getLocation().add(-1.0D, 1.0D, -1.0D);
                this.loc[4] = e.getRightClicked().getLocation().add(1.0D, 1.0D, -1.0D);
                this.loc[5] = e.getRightClicked().getLocation().add(-1.0D, 1.0D, 1.0D);
                this.loc[6] = e.getRightClicked().getLocation().add(0.0D, 0.0D, -1.0D);
                this.loc[7] = e.getRightClicked().getLocation().add(-1.0D, 0.0D, 0.0D);
                this.loc[8] = e.getRightClicked().getLocation().add(1.0D, 0.0D, 0.0D);
                this.loc[9] = e.getRightClicked().getLocation().add(0.0D, 0.0D, 1.0D);
                this.loc[10] = e.getRightClicked().getLocation().add(0.0D, 3.0D, 0.0D);
                this.loc[11] = e.getRightClicked().getLocation().add(0.0D, 2.0D, -1.0D);
                this.loc[12] = e.getRightClicked().getLocation().add(-1.0D, 2.0D, 0.0D);
                this.loc[13] = e.getRightClicked().getLocation().add(1.0D, 2.0D, 0.0D);
                this.loc[14] = e.getRightClicked().getLocation().add(0.0D, 2.0D, 1.0D);
                this.loc[15] = e.getRightClicked().getLocation().add(0.0D, 1.0D, -1.0D);
                this.loc[16] = e.getRightClicked().getLocation().add(-1.0D, 1.0D, 0.0D);
                this.loc[17] = e.getRightClicked().getLocation().add(1.0D, 1.0D, 0.0D);
                this.loc[18] = e.getRightClicked().getLocation().add(0.0D, 1.0D, 1.0D);
                this.loc[19] = e.getRightClicked().getLocation().add(0.0D, 2.0D, 0.0D);
                Location[] oldLoc2;
                int length3 = (oldLoc2 = this.oldLoc).length;
                for (int k = 0; k < length3; k++)
                {
                  Location old = oldLoc2[k];
                  old.getBlock().setType(Material.AIR);
                }
                Location[] loc2;
                int length4 = (loc2 = this.loc).length;
                for (int l = 0; l < length4; l++)
                {
                  Location locais2 = loc2[l];
                  
                  @SuppressWarnings("deprecation")
				FallingBlock bloco = locais2.getBlock().getWorld().spawnFallingBlock(locais2, Material.SAND, (byte)0);
                  Gaara.this.blocos.add(bloco.getUniqueId());
                }
                e.getRightClicked().setFallDistance(-10.0F);
                e.getRightClicked().getWorld().createExplosion(this.loc[10], 1.0F, false);
                cancel();
              }
            }
            else
            {
              Location[] loc3;
              int length5 = (loc3 = this.loc).length;
              for (int n = 0; n < length5; n++)
              {
                Location locais2 = loc3[n];
                locais2.getBlock().setType(Material.AIR);
              }
              if (this.oldLoc != null)
              {
                Location[] oldLoc3;
                int length6 = (oldLoc3 = this.oldLoc).length;
                for (int n2 = 0; n2 < length6; n2++)
                {
                  Location old = oldLoc3[n2];
                  old.getBlock().setType(Material.AIR);
                }
              }
              cancel();
            }
          }
        }.runTaskTimer(Main.plugin, 0L, 8L);
      }
    }
  }
  
  @EventHandler
  public void DropKit(PlayerDropItemEvent e)
  {
    Player p = e.getPlayer();
    if (KitAPI.getKitName(p) == "Gaara") {
      if (e.getItemDrop().getItemStack().getType() == Material.SANDSTONE) {
        e.setCancelled(true);
      }
    }
  }
}
