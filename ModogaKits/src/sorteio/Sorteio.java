package sorteio;

import org.bukkit.Bukkit;

public class Sorteio {
	
	public String nome;
	
	public Sorteio(String name) {
		nome = name;
	}
	
	public void sortear() {
		Bukkit.broadcastMessage(" ");
		Bukkit.broadcastMessage("§fSorteio de um(a) §e§l"+nome+" §festá acontecendo");
		Bukkit.broadcastMessage(" ");
		Bukkit.broadcastMessage(" ");
		Bukkit.broadcastMessage(" ");
	}
	

}
