package menu;

import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import api.RankAPI;
import br.alkazuz.groupapi.api.GroupAPI;
import kConquista.Conquista;
import kConquista.ConquistaAPI;
import kConquista.ConquistaManager;
import main.Methods;
import mysqlManager.Status;
import tab.TituloAPI;

public class MenusAPI {
	
    public static ItemStack createItemMenu(Material material, String nome, String[] habilidade){
		
		ItemStack item = new ItemStack(material);
		ItemMeta kitem = item.getItemMeta();
		kitem.setDisplayName(nome);
		kitem.setLore(Arrays.asList(habilidade));
		item.setItemMeta(kitem);
		
		return item;
	}
    
    public static ItemStack createItemMenu(Material material,int quantidade, String nome, String[] habilidade){
		
		ItemStack item = new ItemStack(material,quantidade);
		ItemMeta kitem = item.getItemMeta();
		kitem.setDisplayName(nome);
		kitem.setLore(Arrays.asList(habilidade));
		item.setItemMeta(kitem);
		
		return item;
	}
    
    public static ItemStack createItemMenu(Material material, String nome, String[] habilidade,short data){
		
		ItemStack item = new ItemStack(material,1,data);
		ItemMeta kitem = item.getItemMeta();
		kitem.setDisplayName(nome);
		kitem.setLore(Arrays.asList(habilidade));
		item.setItemMeta(kitem);
		
		return item;
	}
    
    @SuppressWarnings("deprecation")
	public static ItemStack getRandomGlass() {
        int r = new Random().nextInt(15);
        while (r == 5 || r == 8) {
        	r = new Random().nextInt(15);
		}
        final ItemStack item = new ItemStack(160, 1, (short)r);
        return item;
    }
    
    public static String money(int i){
		if(i < 0) {
			return "§c"+NumberFormat.getCurrencyInstance().format(i);
		}
		return NumberFormat.getCurrencyInstance().format(i);
	}
    
    public static void rankMenu(Player p) {
    	Inventory inv = Bukkit.createInventory(p, 27,"§aRanks");
    	for (int i = 0; i < 27; i++) {
    		inv.setItem(i, getRandomGlass());	
		}
    	inv.setItem(10, createItemMenu(Material.SUGAR, "§f§l⚊ UNRANKED", new String[] {"","§fDe §e0 §fà §e49 §fKills"}));
    	inv.setItem(11, createItemMenu(Material.NETHER_BRICK, "§a§l☰ PRIMARY", new String[] {"","§fDe §e50 §fà §e100 §fKills"}));
    	inv.setItem(12, createItemMenu(Material.IRON_INGOT, "§e§l☲ EXPERT", new String[] {"","§fDe §e101 §fà §e150 §fKills"}));
    	inv.setItem(13, createItemMenu(Material.GOLD_INGOT, "§1☲ ADVANCED", new String[] {"","§fDe §e151 §fà §e200 §fKills"}));
    	inv.setItem(14, createItemMenu(Material.DIAMOND, "§c§l☳ KING", new String[] {"","§fDe §e201 §fà §e250 §fKills"}));
    	inv.setItem(15, createItemMenu(Material.EMERALD, "§3§l☴ MITO", new String[] {"","§fDe §e251 §fà §e300 §fKills"}));
    	inv.setItem(16, createItemMenu(Material.NETHER_STAR, "§4§l☵ LENDA", new String[] {"","§fMais de §e500 §fKills"}));
    	p.openInventory(inv);
    }
    
    public static void lojaCaixas(Player p) {
    	Inventory inv = Bukkit.createInventory(p, 27,"§aLoja de Caixas");
    	for (int i = 0; i < 27; i++) {
    		inv.setItem(i, getRandomGlass());
		}
    	ItemStack caixa1 = createItemMenu(Material.CHEST, "§e§lCaixa §7x1", new String[] {"§fClique para comprar §c§lUMA §fcaixa"," ","§fPreço do produto: §e"+money(1500)});
    	ItemStack caixa5 = createItemMenu(Material.CHEST,5, "§e§lCaixa §7x5", new String[] {"§fClique para comprar §c§lCINCO §fcaixas"," ","§fPreço do produto: §e"+money(1500 * 5)});
    	ItemStack caixa10 = createItemMenu(Material.CHEST,10, "§e§lCaixa §7x10", new String[] {"§fClique para comprar §c§lDEZ §fcaixas"," ","§fPreço do produto: §e"+money(1500 * 10)});
    	
    	inv.setItem(4, createItemMenu(Material.INK_SACK, "§a§lSTATUS", new String[] {" ","§fAtualmente você possui §e"+money(Status.getCoins(p))},(short)10));
    	inv.setItem(12, caixa1);
    	inv.setItem(13, caixa5);
    	inv.setItem(14, caixa10);
    	
    	p.openInventory(inv);
    	
    }
    
    public static void caixasmenu(Player p){
    	Inventory inv = Bukkit.createInventory(p, 27,"§aCaixas");
    	ItemStack vidroverde = createItemMenu(Material.STAINED_GLASS_PANE, "§a-", new String[] {" "},(short)5);
    	
    	for (int i = 0; i < 8; i++) {
			inv.setItem(i, vidroverde);
		}
    	for (int i = 8; i < 27; i++) {
			inv.setItem(i, getRandomGlass());
		}
    	
    	inv.setItem(8, createItemMenu(Material.DIAMOND, "§a§lLoja de Caixas", new String[] {"§fCompre caixas clicando aqui"," ","§fCom caixas você pode ganhar","§fmoney e até mesmo Kits"}));
    	inv.setItem(13, createItemMenu(Material.CHEST, "§e§lAbrir Caixas", new String[] {"§fClique para abrir uma caixa","§fVocê possui §e"+Status.getCaixas(p)+" §fcaixa(s)"}));
    	p.openInventory(inv);
    }
    
    public static int getCon(Player p) {
    	int i = 0;
    	for(Conquista conq : ConquistaManager.conquistas) {
    		if(ConquistaAPI.hasConquista(p, conq.getName())) {
    			i++;
    		}
    	}
    	return i;
    }
    
    public static void OnpenConquista(Player p) {
   	 Inventory inv = Bukkit.createInventory(p, 54,"§aConquistas");
   	  for (int i = 0; i < 8; i++) {
		inv.setItem(i, getRandomGlass());
	  }
   	  ItemStack vidroverde = createItemMenu(Material.STAINED_GLASS_PANE, "§a-", new String[] {" "},(short)5);
   	  for (int i = 8; i < 18; i++) {
   		inv.setItem(i, vidroverde);
   	  }
   	  inv.setItem(4, createItemMenu(Material.INK_SACK, "§a§lConquistas", new String[] {"§fConquistas completadas §e"+getCon(p)+"/"+ConquistaManager.conquistas.size()},(short)10));
   	  int d = 18;
   	  for(Conquista con : ConquistaManager.conquistas) {
   		  if(ConquistaAPI.hasConquista(p, con.getName())) {
   			inv.addItem(createItemMenu(Material.getMaterial(159), "§a§l"+con.getName(), con.getDesc(),(short)5));
   		  }else {
   			inv.addItem(createItemMenu(Material.getMaterial(159), "§c§l"+con.getName(), con.getDesc(),(short)14));
   		  }
   		  d++;
   	  }
   	ItemStack vidropreto = createItemMenu(Material.STAINED_GLASS_PANE, "§a-", new String[] {" "},(short)15);
   	  for (int i = d; i < 54; i++) {
   		inv.setItem(i, vidropreto);
		
	}
   	  p.openInventory(inv);
    }
    
    public static void openInfo(Player p) {
    	if(GroupAPI.inTempGroup(p)) {
    		long banTime = (long)GroupAPI.getLenght(p);
    		if (banTime < System.currentTimeMillis())
    	      {
    			String grupo = GroupAPI.getGroup(p);
    			GroupAPI.setGroup(p, "Membro");
    			p.sendMessage(" ");
    			p.sendMessage("§cSeu Grupo "+GroupAPI.getColor(grupo)+grupo+" §cexpirou.");
    			p.sendMessage(" ");
    			TituloAPI.mandarTitulo(p, "§a§lGRUPO");
    			TituloAPI.mandarSubTitulo(p, "§cSeu Grupo "+GroupAPI.getColor(grupo)+grupo+" §cexpirou.");
    	      }
    		}
    	 Inventory inv = Bukkit.createInventory(p, 54,"§aInformações");
    	 
    	 inv.setItem(13, createItemMenu(Material.INK_SACK, "§a§lSTATUS", new String[] {" "},(short)10));
    	 inv.setItem(22, createItemMenu(Material.ANVIL, "§a§lGRUPO", new String[] {" ","§fGrupo: "+GroupAPI.getColor(GroupAPI.getGroup(p))+GroupAPI.getGroup(p),"§fExpira em: "+(GroupAPI.inTempGroup(p)?"§c"+Methods.formatExpira(GroupAPI.getLenght(p)):"§4§lNUNCA")}));
    	 inv.setItem(29, createItemMenu(Material.ARROW, "§a§lSTATUS", new String[] {" ","§c§lMortes","§7"+Status.getDeaths(p)}));
    	 inv.setItem(30, createItemMenu(Material.DIAMOND_SWORD, "§a§lSTATUS", new String[] {" ","§a§lKills","§7"+Status.getkills(p)}));
    	 inv.setItem(31, createItemMenu(Material.EXP_BOTTLE, "§a§lSTATUS", new String[] {" ","§e§lMoney","§7"+money(Status.getCoins(p))}));
    	 inv.setItem(32, createItemMenu(Material.SLIME_BALL, "§a§lSTATUS", new String[] {" ","§3§lRank","§7"+RankAPI.getRank(p)}));
    	 inv.setItem(33, createItemMenu(Material.BOOK, "§a§lSTATUS", new String[] {" ","§d§lLiga","§7"+RankAPI.getRankName(p)}));
    	 p.openInventory(inv);
    }

}
