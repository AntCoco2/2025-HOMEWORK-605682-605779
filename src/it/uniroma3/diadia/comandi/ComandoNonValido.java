package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoNonValido extends AbstractComando {
	private static final String NOME = "Comando non valido!";
	
	/**
	 * Stampa un messaggio se il giocatore inserisce un comando
	 * non valido
	 */

	@Override
	public void esegui(Partita partita) {
		io.mostraMessaggio(NOME);
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
