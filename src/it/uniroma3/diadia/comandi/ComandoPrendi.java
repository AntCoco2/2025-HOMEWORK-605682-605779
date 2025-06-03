package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPrendi extends AbstractComando {
	private static final String NOME = "prendi";

	// implementazioni dei comandi dell'utente:
	/**
	 * Prende un oggetto dalla stanza e poi lo mette nella borsa stampa poi il
	 * contenuto della borsa dopo l'azione. Se l'oggetto non c'è stampa i messaggi
	 * di errore, stessa cosa per la stanza vuota e la borsa piena.
	 *
	 * @param il nome dell'attrezzo da prendere
	 */

	@Override
	public void esegui(Partita partita) {
	    // Controlla se la stanza è vuota
	    if (partita.labirinto.getStanzaCorrente().isEmpty()) {
	        io.mostraMessaggio("La stanza è vuota!");
	    } 
	    // Controlla se la stanza contiene l'oggetto desiderato
	    else if (partita.labirinto.getStanzaCorrente().hasAttrezzo(parametro)) {
	        Attrezzo daLevare = partita.labirinto.getStanzaCorrente().getAttrezzo(parametro);
	        // Verifica se aggiungere l'attrezzo supererebbe il peso max della borsa
	        if (partita.giocatore.getBorsa().getPeso() + daLevare.getPeso() > partita.giocatore.getBorsa().getPesoMax()) {
	            io.mostraMessaggio("La borsa è troppo pesante!");
	        } else {
	            // Rimuovi l'attrezzo dalla stanza e prova ad aggiungerlo alla borsa
	            partita.labirinto.getStanzaCorrente().removeAttrezzo(daLevare);
	            if (partita.giocatore.getBorsa().addAttrezzo(daLevare)) {
	                io.mostraMessaggio("Oggetto aggiunto alla borsa!");
	                io.mostraMessaggio(partita.giocatore.getBorsa().toString());
	            } else {
	                io.mostraMessaggio("Oggetto non aggiunto!");
	            }
	        }
	    } 
	    // Se la stanza non contiene l'oggetto cercato
	    else {
	        io.mostraMessaggio("La stanza non ha l'oggetto che vuoi!");
	    }
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
