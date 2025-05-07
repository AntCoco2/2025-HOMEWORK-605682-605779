package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoNonValido implements Comando {
	private IO io;
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
	public void setParametro(String parametro) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getNome() {
		return NOME;
	}

	@Override
	public String getParametro() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setIO(IO io) {
		this.io = io;
	}
	

}
