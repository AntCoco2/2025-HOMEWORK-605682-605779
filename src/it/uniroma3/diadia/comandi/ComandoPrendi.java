package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPrendi implements Comando {
	IO io;
	String oggetto;
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
		Attrezzo daLevare;
		if (partita.giocatore.getBorsa().isFull()) {
			io.mostraMessaggio("La borsa è piena!");
		} else if (partita.labirinto.getStanzaCorrente().isEmpty()) {
			io.mostraMessaggio("La stanza è vuota!");

		}else {
			if(partita.labirinto.getStanzaCorrente().hasAttrezzo(oggetto)) {
				daLevare = partita.labirinto.getStanzaCorrente().getAttrezzo(oggetto);		
				partita.labirinto.getStanzaCorrente().removeAttrezzo(daLevare);
				if(partita.giocatore.getBorsa().addAttrezzo(daLevare)) {
					io.mostraMessaggio("Oggetto aggiunto alla borsa!");
					io.mostraMessaggio(partita.giocatore.getBorsa().toString());
				}else {
					io.mostraMessaggio("Oggetto non aggiunto!");
				}
			}
			else {
				io.mostraMessaggio("La stanza non ha l'oggetto che vuoi!");
			}
			
		}
	}

	@Override
	public void setParametro(String parametro) {
		this.oggetto = parametro;
		
	}

	@Override
	public String getNome() {
		// TODO Auto-generated method stub
		return NOME;
	}

	@Override
	public String getParametro() {
		return oggetto;
	}

	@Override
	public void setIO(IO io) {
		this.io = io;
		
	}

}
