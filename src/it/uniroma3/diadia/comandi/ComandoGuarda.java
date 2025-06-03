package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoGuarda extends  AbstractComando {
    private static final String NOME = "guarda";
    /**
     * Stampa le informazioni della stanza corrente e lo stato della partita
     * per il giocatore
     * 
     * 
     *
     * @param la stanza corrente ed informazioni del giocatore
     */
	@Override
	public void esegui(Partita partita) {
		io.mostraMessaggio(partita.getGiocatore().getBorsa().toString());
		io.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
		io.mostraMessaggio("Numero cfu: " + partita.getGiocatore().getCfu());
	
	}


	@Override
	public String getNome() {
		// TODO Auto-generated method stub
		return NOME;
	}
	@Override
	public void setIO(IO io) {
		this.io = io;
	}

	
	
}
