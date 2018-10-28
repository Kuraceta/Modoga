package simulador;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class MenuKits implements Listener
{
    public void guiHG(final Player p) {
        final Inventory inv = Bukkit.getServer().createInventory((InventoryHolder)p, 36, "§aKits");
        final ItemStack vidro = new ItemStack(Material.STAINED_GLASS_PANE);
        final ItemMeta metav = vidro.getItemMeta();
        metav.setDisplayName("");
        vidro.setItemMeta(metav);
        inv.setItem(0, vidro);
        inv.setItem(1, vidro);
        inv.setItem(2, vidro);
        inv.setItem(3, vidro);
        inv.setItem(4, vidro);
        inv.setItem(5, vidro);
        inv.setItem(6, vidro);
        inv.setItem(7, vidro);
        inv.setItem(8, vidro);
        inv.setItem(9, vidro);
        inv.setItem(10, vidro);
        inv.setItem(11, vidro);
        inv.setItem(12, vidro);
        inv.setItem(13, vidro);
        inv.setItem(14, vidro);
        inv.setItem(15, vidro);
        inv.setItem(16, vidro);
        inv.setItem(17, vidro);
        inv.setItem(18, vidro);
        inv.setItem(19, vidro);
        inv.setItem(20, vidro);
        inv.setItem(21, vidro);
        inv.setItem(22, vidro);
        inv.setItem(23, vidro);
        inv.setItem(24, vidro);
        inv.setItem(10, vidro);
        inv.setItem(25, vidro);
        inv.setItem(26, vidro);
        inv.setItem(27, vidro);
        inv.setItem(28, vidro);
        inv.setItem(29, vidro);
        inv.setItem(30, vidro);
        inv.setItem(31, vidro);
        inv.setItem(32, vidro);
        inv.setItem(33, vidro);
        inv.setItem(34, vidro);
        inv.setItem(35, vidro);
        final ItemStack Star = new ItemStack(Material.IRON_BOOTS);
        final ItemMeta metaStar = Star.getItemMeta();
        metaStar.setDisplayName("§6Kit Stomper");
        Star.setItemMeta(metaStar);
        inv.setItem(20, Star);
        final ItemStack Star2 = new ItemStack(Material.SNOW_BALL);
        final ItemMeta metaStar2 = Star2.getItemMeta();
        metaStar2.setDisplayName("§6Kit Switcher");
        Star2.setItemMeta(metaStar2);
        inv.setItem(24, Star2);
        final ItemStack Star3 = new ItemStack(Material.WOOD_SWORD);
        final ItemMeta metaStar3 = Star3.getItemMeta();
        metaStar3.setDisplayName("§aKITS");
        Star3.setItemMeta(metaStar3);
        inv.setItem(4, Star3);
        final ItemStack kanga = new ItemStack(Material.FIREWORK);
        final ItemMeta metaskanga = kanga.getItemMeta();
        metaskanga.setDisplayName("§6Kit Kangaroo");
        kanga.setItemMeta(metaskanga);
        inv.setItem(22, kanga);
        final ItemStack stomp = new ItemStack(Material.WOOL, 1, (short)15);
        final ItemMeta metastomp = stomp.getItemMeta();
        metastomp.setDisplayName("§6Kit Ninja");
        stomp.setItemMeta(metastomp);
        inv.setItem(21, stomp);
        final ItemStack tank = new ItemStack(Material.BLAZE_ROD);
        final ItemMeta metatank = tank.getItemMeta();
        metatank.setDisplayName("§6Kit Monk");
        tank.setItemMeta(metatank);
        inv.setItem(23, tank);
        p.openInventory(inv);
    }
    
    @EventHandler
    public void onPlayerInteract(final PlayerInteractEvent e) {
        final Player p = e.getPlayer();
        if (!EventosAPI.playerPlayingHG(p)) {
            return;
        }
        if (p.getItemInHand().getType() == Material.FEATHER) {
            guiHG(p);
        }
    }
    
    @SuppressWarnings("deprecation")
	@EventHandler
    public void onPlayerCLickInventry(final InventoryClickEvent e) {
        final Player p = (Player)e.getWhoClicked();
        if (e.getInventory().getTitle().equalsIgnoreCase("§aKits") && e.getCurrentItem() != null && e.getCurrentItem().getTypeId() != 0) {
            
            if (e.getCurrentItem().getType() == Material.FIREWORK) {
                p.closeInventory();
                if (!KitAPI.kits.contains(p.getName())) {
                    if (!EventosAPI.playerPlayingHG(p)) {
                        return;
                    }
                    p.sendMessage("§aVocê pegou o Kit §fKangaroo");
                    p.playSound(p.getLocation(), Sound.LEVEL_UP, 4.0f, 4.0f);
                    KitAPI.kits.add(p.getName());
                    KitAPI.kangaroo.add(p.getName());
                    p.getInventory().clear();
                }else {
                    p.sendMessage("§cVoce ja contem um kit!");
                }
                e.setCancelled(true);
            }
            if (e.getCurrentItem().getType() == Material.WOOL) {
                p.closeInventory();
                if (!KitAPI.kits.contains(p.getName())) {
                    if (!EventosAPI.playerPlayingHG(p)) {
                        return;
                    }
                    p.sendMessage("§aVocê pegou o Kit §fNinja");
                    p.playSound(p.getLocation(), Sound.LEVEL_UP, 4.0f, 4.0f);
                    KitAPI.kits.add(p.getName());
                    KitAPI.ninja.add(p.getName());
                    p.getInventory().clear();
            }
            else {
                p.sendMessage("§cVoce ja contem um kit!");
            }
                e.setCancelled(true);
            }
            if (e.getCurrentItem().getType() == Material.IRON_BOOTS) {
                p.closeInventory();
                if (!KitAPI.kits.contains(p.getName())) {
                    if (!EventosAPI.playerPlayingHG(p)) {
                        return;
                    }
                    p.playSound(p.getLocation(), Sound.LEVEL_UP, 4.0f, 4.0f);
                    KitAPI.kits.add(p.getName());
                    KitAPI.stomper.add(p.getName());
                    p.sendMessage("§aVocê pegou o Kit §fStomper");
                    p.getInventory().clear();
            }
            else {
                p.sendMessage("§cVoce já selecionou um kit!");
            }
                e.setCancelled(true);
            }
            if (e.getCurrentItem().getType() == Material.WOOD_AXE) {
                e.setCancelled(true);
            }
            if (e.getCurrentItem().getType() == Material.BLAZE_ROD) {
                p.closeInventory();
                if (!KitAPI.kits.contains(p.getName())) {
                    if (!EventosAPI.playerPlayingHG(p)) {
                        return;
                    }
                    p.sendMessage("§aVocê pegou o kit §fMonk");
                    p.playSound(p.getLocation(), Sound.LEVEL_UP, 4.0f, 4.0f);
                    KitAPI.kits.add(p.getName());
                    KitAPI.monk.add(p.getName());
                    p.getInventory().clear();
                    final ItemStack grappl = new ItemStack(Material.BLAZE_ROD);
                    final ItemMeta grap = grappl.getItemMeta();
                    grap.setDisplayName("§6Monk");
                    grappl.setItemMeta(grap);
                }
                
            else {
                p.sendMessage("§cVoce ja contem um kit!");
            }
                e.setCancelled(true);
            }
            if (e.getCurrentItem().getType() == Material.SNOW_BALL) {
                p.closeInventory();
                if (!KitAPI.kits.contains(p.getName())) {
                    if (!EventosAPI.playerPlayingHG(p)) {
                        return;
                    }
                    p.sendMessage("§aVocê pegou o kit §fSwitcher");
                    p.playSound(p.getLocation(), Sound.LEVEL_UP, 4.0f, 4.0f);
                    KitAPI.kits.add(p.getName());
                    KitAPI.switcher.add(p.getName());
                    p.getInventory().clear();
                    p.getInventory().setHelmet(new ItemStack(Material.REDSTONE_BLOCK));
                }
            else {
                p.sendMessage("§cVoce ja contem um kit!");
            }
                e.setCancelled(true);
            }
            e.setCancelled(true);
        }
    }
    
    @EventHandler
    public void ahoraqmorre(final PlayerRespawnEvent e) {
    	if(KitAPI.grappler.contains(e.getPlayer().getName())){
        KitAPI.grappler.remove(e.getPlayer().getName());
    	}
    	if(KitAPI.grappler.contains(e.getPlayer().getName())){
        KitAPI.kangaroo.remove(e.getPlayer().getName());
    	}
    	if(KitAPI.grappler.contains(e.getPlayer().getName())){
        KitAPI.monk.remove(e.getPlayer().getName());
    	}
    	if(KitAPI.grappler.contains(e.getPlayer().getName())){
        KitAPI.ninja.remove(e.getPlayer().getName());
    	}
    	if(KitAPI.grappler.contains(e.getPlayer().getName())){
        KitAPI.stomper.remove(e.getPlayer().getName());
    	}
    	if(KitAPI.grappler.contains(e.getPlayer().getName())){
        KitAPI.switcher.remove(e.getPlayer().getName());
    	}
    	if(KitAPI.grappler.contains(e.getPlayer().getName())){
        KitAPI.thor.remove(e.getPlayer().getName());
    	}
    }
    
    @EventHandler
    public void ahoraqmorre1(final PlayerDeathEvent e) {
    	if(KitAPI.grappler.contains(e.getEntity().getName())){
            KitAPI.grappler.remove(e.getEntity().getName());
        	}
        	if(KitAPI.grappler.contains(e.getEntity().getName())){
            KitAPI.kangaroo.remove(e.getEntity().getName());
        	}
        	if(KitAPI.grappler.contains(e.getEntity().getName())){
            KitAPI.monk.remove(e.getEntity().getName());
        	}
        	if(KitAPI.grappler.contains(e.getEntity().getName())){
            KitAPI.ninja.remove(e.getEntity().getName());
        	}
        	if(KitAPI.grappler.contains(e.getEntity().getName())){
            KitAPI.stomper.remove(e.getEntity().getName());
        	}
        	if(KitAPI.grappler.contains(e.getEntity().getName())){
            KitAPI.switcher.remove(e.getEntity().getName());
        	}
        	if(KitAPI.grappler.contains(e.getEntity().getName())){
            KitAPI.thor.remove(e.getEntity().getName());
        	}
    }

}
