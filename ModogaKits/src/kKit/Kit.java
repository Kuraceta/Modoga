package kKit;

import org.bukkit.inventory.ItemStack;

public class Kit {
	
	public String name;
	public int price;
	public boolean free;
	public ItemStack item;
	public boolean sendItem;
	public String perm;
	public String[] desc;
	public boolean diario;
	
	public Kit(String nome, int preco,boolean gratis,ItemStack itm,boolean send,String permissao,boolean dia,String[] desc) {
		name = nome;
	    price = preco;
		item = itm;
		sendItem = send;
		perm = permissao;
		free = gratis;
		this.desc = desc;
		diario = dia;
	}

	public String[] getDesc() {
		return desc;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public boolean isFree() {
		return free;
	}

	public void setFree(boolean free) {
		this.free = free;
	}

	public ItemStack getItem() {
		return item;
	}

	public void setItem(ItemStack item) {
		this.item = item;
	}

	public boolean isSendItem() {
		return sendItem;
	}

	public void setSendItem(boolean sendItem) {
		this.sendItem = sendItem;
	}

	public void setDect(String[] a) {
		desc = a;
	}
	
	public String getPerm() {
		return perm;
	}

	public void setPerm(String perm) {
		this.perm = perm;
	}

	
	
}
