package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoAiuto extends AbstractComando {
	static final private String[] elencoComandi = { "vai", "aiuto", "fine", "prendi", "posa", "guarda", "regala", "interagisci", "saluta" };
	
	static final private String NOME = "aiuto";
	
	
	/**
	 * Stampa informazioni di aiuto.
	 */

	@Override
	public void esegui(Partita partita) {
		for (int i = 0; i < elencoComandi.length; i++)
			io.mostraMessaggio(elencoComandi[i] + " ");
		io.mostraMessaggio("\n");
		
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
