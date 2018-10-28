package utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import api.MessageAPI;
import kKit.KitAPI;
import kKit.KitManager;
import menu.KitDiarioMenu;
import menu.MenusAPI;
import mysqlManager.Status;

public class BoxScroller implements Runnable
{
    private List<ItemStack> itens;
    private Player p;
    private int[] slots_roda;
    private boolean end;
    private Inventory gui;
    private int index;
    private int max;
    
    public BoxScroller(final Player p, final List<ItemStack> itens, final Inventory gui, int[] slots) {
        this.itens = new ArrayList<ItemStack>();
        slots_roda = slots;
        end = false;
        index = 0;
        max = 0;
        this.p = p;
        this.gui = gui;
        this.itens = itens;
        setup();
    }
    
    public int getMax() {
        return max;
    }
    
    public void setup() {
    	for(int d : slots_roda) {
    		gui.setItem(d, itens.get(new Random().nextInt(itens.size())));
    	}
    }
    
    public int getIndex() {
        return index;
    }
    
    public boolean isEnd() {
        return end;
    }
    
    public void setEnd(final boolean end) {
        this.end = end;
    }
    
    public Inventory getGui() {
        return gui;
    }
    
    @Override
    public void run() {
        max = 90;
        int atual = 0;
        int add = 0;
        while (!end) {
            if (p == null) {
                end = true;
                KitDiarioMenu.open.remove(p);
            }
            ++index;
            ++atual;
            add += 2;
            if (atual >= max) {
                rodar();
                gui.setItem(9, new ItemStack(Material.AIR));
                gui.setItem(10, new ItemStack(Material.AIR));
                gui.setItem(11, new ItemStack(Material.AIR));
                gui.setItem(12, new ItemStack(Material.AIR));
                gui.setItem(14, new ItemStack(Material.AIR));
                gui.setItem(15, new ItemStack(Material.AIR));
                gui.setItem(16, new ItemStack(Material.AIR));
                gui.setItem(17, new ItemStack(Material.AIR));
                sleep(3000);
                KitDiarioMenu.open.remove(p);
                p.closeInventory();
                final ItemStack i = gui.getItem(13);
                end = true;
                if(i.getType() == Material.EXP_BOTTLE && gui.getTitle().equalsIgnoreCase("§aAbrindo caixa...")) {
                	String dinheiro = ChatColor.stripColor(i.getItemMeta().getDisplayName()).split("Money - ")[1];
                	int t = Integer.parseInt(dinheiro);
                	p.sendMessage(MessageAPI.Command_Succes+"Você recebeu §e"+MenusAPI.money(t));
                	Status.addCoins(p, t);
                }else {
                p.sendMessage(MessageAPI.Command_Succes+"Você ganhou o Kit §e"+ChatColor.stripColor(i.getItemMeta().getDisplayName()).split("Kit ")[1]);
                KitAPI.darKit(p, KitManager.getKit(ChatColor.stripColor(i.getItemMeta().getDisplayName()).split("Kit ")[1]));
                }

            }
            else {
                rodar();
                sleep(55 + add);
                p.playSound(p.getLocation(), Sound.LEVEL_UP, 10.0f, 20.0f);
            }
        }
    }
    
    private void sleep(final int s) {
        try {
            Thread.sleep(s);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    private void rodar() {
        gui.setItem(slots_roda[8], gui.getItem(slots_roda[7]));
        gui.setItem(slots_roda[7], gui.getItem(slots_roda[6]));
        gui.setItem(slots_roda[6], gui.getItem(slots_roda[5]));
        gui.setItem(slots_roda[5], gui.getItem(slots_roda[4]));
        gui.setItem(slots_roda[4], gui.getItem(slots_roda[3]));
        gui.setItem(slots_roda[3], gui.getItem(slots_roda[2]));
        gui.setItem(slots_roda[2], gui.getItem(slots_roda[1]));
        gui.setItem(slots_roda[1], gui.getItem(slots_roda[0]));
        gui.setItem(slots_roda[0], (ItemStack)itens.get(new Random().nextInt(itens.size())));
    }
}
