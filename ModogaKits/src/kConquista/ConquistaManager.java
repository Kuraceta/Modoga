package kConquista;

import java.util.ArrayList;

import kConquistaConquistas.CaixasI;
import kConquistaConquistas.CaixasII;
import kConquistaConquistas.CaixasIII;
import kConquistaConquistas.InicianteI;
import kConquistaConquistas.InicianteII;
import kConquistaConquistas.InicianteIII;
import kConquistaConquistas.MLGI;
import kConquistaConquistas.MLGII;
import kConquistaConquistas.MLGIII;
import kConquistaConquistas.MestreI;
import kConquistaConquistas.MestreII;
import kConquistaConquistas.MestreIII;
import kConquistaConquistas.NoobI;
import kConquistaConquistas.NoobII;
import kConquistaConquistas.NoobIII;
import kConquistaConquistas.RicoI;
import kConquistaConquistas.RicoII;
import kConquistaConquistas.RicoIII;

public class ConquistaManager {
	
	public static ArrayList<Conquista> conquistas = new ArrayList<Conquista>();
	
	public ConquistaManager(){
		conquistas.add(new CaixasI());
		conquistas.add(new CaixasII());
		conquistas.add(new CaixasIII());
		conquistas.add(new InicianteI());
		conquistas.add(new InicianteII());
		conquistas.add(new InicianteIII());
		conquistas.add(new MestreI());
		conquistas.add(new MestreII());
		conquistas.add(new MestreIII());
		conquistas.add(new MLGI());
		conquistas.add(new MLGII());
		conquistas.add(new MLGIII());
		conquistas.add(new NoobI());
		conquistas.add(new NoobII());
		conquistas.add(new NoobIII());
		conquistas.add(new RicoI());
		conquistas.add(new RicoII());
		conquistas.add(new RicoIII());
	}
	
	public Conquista getConquistaByName(String name){
		for(Conquista cqt : conquistas){
			if(cqt.getName().equalsIgnoreCase(name)){
				return cqt;
			}
		}
		return null;
	}

}
