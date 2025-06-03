package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoFine extends AbstractComando {
	
	private final static String NOME = "fine"; 
	private final static String MESSAGGIO_FIN = "Grazie di aver giocato!";
	
	
	
	/**
	 * Comando "Fine".
	 */
	

	@Override
	public void esegui(Partita partita) {
		partita.setFinita();
		io.mostraMessaggio(MESSAGGIO_FIN);
	
		
	}

	

	@Override
	public String getNome() {
		return NOME;
	}
	@Override
	public void setIO(IO io) {
		this.io = io;
	}
	

}
