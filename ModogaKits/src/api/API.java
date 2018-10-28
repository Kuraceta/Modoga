package api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import kConfig.Config;

public class API {
	
	public static ArrayList<Player> Checando = new ArrayList<Player>();
	public static ArrayList<Player> admin = new ArrayList<Player>();
	
	public static ArrayList<Player> knock = new ArrayList<Player>();
	
	public static void sendItems(Player p){
		p.getInventory().clear();
		p.getInventory().setArmorContents(null);
		ItemStack Kits = new ItemStack(Material.CHEST);
		ItemMeta kKits = Kits.getItemMeta();
		kKits.setDisplayName("§e§lKits §7- (Clique e Selecione)");
		kKits.setLore(Arrays.asList("§fClique aqui", "§fpara selecionar um §eKit"));
		Kits.setItemMeta(kKits);
		
		ItemStack KitDiario = new ItemStack(Material.ENDER_CHEST);
		ItemMeta kKitDiario = KitDiario.getItemMeta();
		kKitDiario.setDisplayName("§c§lKitDiario §7- (Clique para Abrir)");
		kKitDiario.setLore(Arrays.asList("§fClique aqui", "§fPara abrir uma caixa diária"));
		KitDiario.setItemMeta(kKitDiario);
		
		ItemStack Warps = new ItemStack(Material.ENCHANTED_BOOK);
		ItemMeta kWarps = Warps.getItemMeta();
		kWarps.setDisplayName("§a§lWarps §7- (Clique e Selecione)");
		kWarps.setLore(Arrays.asList("§fClique aqui", "§fpara ser teleportado para uma Warp"));
		Warps.setItemMeta(kWarps);
		
		final ItemStack C1 = new ItemStack(Material.SKULL_ITEM, 1, (short)3);
        final SkullMeta sm = (SkullMeta)C1.getItemMeta();
        sm.setOwner(p.getName());
        sm.setDisplayName("§f§lInformações §7- (Clique para Ver)");
        C1.setItemMeta((ItemMeta)sm);
       
        
		ItemStack Dima = new ItemStack(Material.EXP_BOTTLE);
		ItemMeta kDima = Dima.getItemMeta();
		kDima.setDisplayName("§d§lRanks §7- (Clique)");
		kDima.setLore(Arrays.asList("§fClique aqui", "§fPara ver todos os Ranks"));
		Dima.setItemMeta(kDima);
		
		ItemStack LojaKit = new ItemStack(Material.EMERALD);
		ItemMeta kLojaKit = LojaKit.getItemMeta();
		kLojaKit.setDisplayName("§3§lLoja §7- (Clique para Visitar)");
		kLojaKit.setLore(Arrays.asList("§fClique aqui", "§fComprar Kits para você"));
		LojaKit.setItemMeta(kLojaKit);
		
        p.getInventory().setHeldItemSlot(4);
        p.getInventory().setItem(4, C1);
        p.getInventory().setItem(0, Kits);
        p.getInventory().setItem(5, Warps);
        long banTime = (long)kConfig.KitDiario.getConfig().getLong(p.getUniqueId().toString()+".Tempo");
		if(banTime < System.currentTimeMillis()) {
			p.getInventory().setItem(1, KitDiario);
		}
        p.getInventory().setItem(3, Dima);
        p.getInventory().setItem(8, LojaKit);
        p.updateInventory();
        p.spigot().setCollidesWithEntities(false);
	}
	
	 public static ItemStack createItem( Material material, int quantidade){
			
			ItemStack item = new ItemStack(material, quantidade);
			ItemMeta kitem = item.getItemMeta();
			item.setItemMeta(kitem);
			
			return item;
		}
	
    public static ItemStack createItem(Player p, Material material, String nome, String[] lore, int quantidade, short cor){
		
		ItemStack item = new ItemStack(material, quantidade, cor);
		ItemMeta kitem = item.getItemMeta();
		kitem.setDisplayName(nome);
		kitem.setLore(Arrays.asList(lore));
		item.setItemMeta(kitem);
		
		return item;
	}

	public static void darEfeito(final Player p, final PotionEffectType tipo, final int duracao, final int level) {
        p.addPotionEffect(new PotionEffect(tipo, 20 * duracao, level));
    }

	public static int getAmount(Player p, Material m) {
	     int amount = 0;
	     for (ItemStack item : p.getInventory().getContents()) {
	      if ((item != null) && (item.getType() == m) && (item.getAmount() > 0)) {
	       amount += item.getAmount();
	      }
	     }
	     return amount;
	 }

	public static void darArmorDima(Player p)
  {
	ItemStack capacete = new ItemStack(Material.DIAMOND_HELMET);
    ItemMeta capacetemeta = capacete.getItemMeta();
    capacetemeta.setDisplayName("§cCapacete");
    capacete.setItemMeta(capacetemeta);
    
    ItemStack peitoral = new ItemStack(Material.DIAMOND_CHESTPLATE);
    ItemMeta peitoralmeta = peitoral.getItemMeta();
    peitoralmeta.setDisplayName("§cPeitoral");
    peitoral.setItemMeta(peitoralmeta);
    
    ItemStack calça = new ItemStack(Material.DIAMOND_LEGGINGS);
    ItemMeta calçameta = calça.getItemMeta();
    calçameta.setDisplayName("§cCalça");
    calça.setItemMeta(calçameta);
    
    ItemStack bota = new ItemStack(Material.DIAMOND_BOOTS);
    ItemMeta  botameta = bota.getItemMeta();
    botameta.setDisplayName("§cBota");
    bota.setItemMeta(botameta);
    
    p.getInventory().setHelmet(capacete);
    p.getInventory().setChestplate(peitoral);
    p.getInventory().setLeggings(calça);
    p.getInventory().setBoots(bota);
  }

	public static void AutomaticMessage(){
    	Random rand = new Random();
    	ArrayList<String> msgs = (ArrayList<String>) Config.getConfig().getStringList("Mensagens");
    	String msg = msgs.get(rand.nextInt(msgs.size())).replace("&", "§");
    	String msgenvia = "§2§lSpectrePvP §e» "+msg;
    	Bukkit.broadcastMessage(msgenvia);
    	for(Player p: Bukkit.getOnlinePlayers()) {
    		p.sendMessage("§8 §8 §1 §3 §3 §7 §8 ");
    	}
    }
	
	public static void darSopas(Player p)
  {
    PlayerInventory inv = p.getInventory();
    ItemStack sopa = new ItemStack(Material.MUSHROOM_SOUP);
    for(int i = 0;i<36;i++){
    	ItemStack item = inv.getItem(i);
    	if(item == null){
    		p.getInventory().setItem(i, sopa);
    	}
    	
    }
  }

	
    
}
