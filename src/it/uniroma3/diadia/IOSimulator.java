package it.uniroma3.diadia;

import java.util.ArrayList;
import java.util.List;

public class IOSimulator implements IO {

	private List<String> righeLette;
	private int indiceRigheLette;

	public List<String> getMessaggiProdotti() {
		return messaggiProdotti;
	}

	public void setMessaggiProdotti(List<String> messaggiProdotti) {
		this.messaggiProdotti = messaggiProdotti;
	}

	
	private List<String> messaggiProdotti;
	private int indiceMessaggiProdotti;
	private int indiceMessaggiMostrati;

	public IOSimulator(List<String> righeDaLeggere) {
		this.righeLette = righeDaLeggere;
		this.indiceRigheLette = 0;
		this.indiceMessaggiMostrati = 0;
		this.messaggiProdotti = new ArrayList<String>();
	}

	@Override
	public String leggiRiga() {
	    if (indiceRigheLette >= righeLette.size()) {
	        return null;  
	    }
	    String riga = righeLette.get(indiceRigheLette);
	    indiceRigheLette++;
	    return riga;
	}


	@Override
	public void mostraMessaggio(String msg) {
		this.messaggiProdotti.add(this.indiceMessaggiProdotti, msg);
		this.indiceMessaggiProdotti++;
	}

	public String nextMessaggio() {
		String next = this.messaggiProdotti.get(indiceMessaggiMostrati);
		this.indiceMessaggiMostrati++;
		return next;
	}

	public boolean hasNextMessaggio() {
		return this.indiceMessaggiMostrati < this.indiceMessaggiProdotti;
	}

	@Override
	public void chiudi() {
		// TODO Auto-generated method stub
		
	}

}