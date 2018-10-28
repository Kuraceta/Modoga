package menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.WeakHashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import api.MessageAPI;
import kConfig.KitDiario;
import kKit.Kit;
import kKit.KitManager;
import utils.BoxScroller;
import utils.RDItem;

public class KitDiarioMenu {

	public ArrayList<Kit> kits = new ArrayList<>();
	public List<ItemStack> itens_list = new ArrayList<ItemStack>();
	public static WeakHashMap<Player, BoxScroller> open = new WeakHashMap<Player, BoxScroller>();
	public WeakHashMap<ItemStack, RDItem> itens_alt = new WeakHashMap<ItemStack, RDItem>();
	public Player p;
	
	public KitDiarioMenu(Player p) {
		this.p = p;
		for(Kit kits: KitManager.kits) {
			if(!p.hasPermission(kits.getPerm())&&kits.diario&&!kits.isFree()) {
				this.kits.add(kits);
				itens_list.add(KitMenu.createItemMenu(kits));
				itens_alt.put(KitMenu.createItemMenu(kits), new RDItem(KitMenu.createItemMenu(kits)));
			}
		}
	}
	
	public boolean todosOsKits() {
		int i = 0;
		for(Kit kits : kits) {
			if(p.hasPermission(kits.getPerm())) {
				i++;
			}
		}
		return i == kits.size();
	}
	
	@SuppressWarnings("deprecation")
	public static ItemStack getRandomGlass() {
        int r = new Random().nextInt(15);
        if (r == 8) {
            r = 9;
        }
        final ItemStack item = new ItemStack(160, 1, (short)r);
        return item;
    }
	
	public void newScroll() {
		if(todosOsKits()) {
			p.sendMessage(MessageAPI.Command_Error+"Você já possui todos os Kits Diários");
			return;
		}
		long banTime = (long)KitDiario.getConfig().getLong(p.getUniqueId().toString()+".Tempo");
		if(banTime > System.currentTimeMillis()) {
			p.sendMessage(MessageAPI.Command_Error+"Você abriu essa caixa em menos de 24 horas");
			return;
		}
        final Inventory inv = Bukkit.createInventory(p, 27, "§aKit diário");
        final int[] slots_roda = { 9, 10, 11, 12, 13, 14, 15, 16, 17 };
        for (int i = 0; i < 27; ++i) {
            boolean b = false;
            int[] array;
            for (int length = (array = slots_roda).length, j = 0; j < length; ++j) {
                final int a = array[j];
                if (a == i) {
                    b = true;
                }
            }
            if (!b) {
                inv.setItem(i, getRandomGlass());
            }
        }
        final BoxScroller bs = new BoxScroller(p, itens_list, inv,new int[] { 9, 10, 11, 12, 13, 14, 15, 16, 17 });
        KitDiario.addTempo(p);
        open.put(p, bs);
        p.openInventory(inv);
        final Thread th = new Thread(bs);
        th.start();
    }
	
}
