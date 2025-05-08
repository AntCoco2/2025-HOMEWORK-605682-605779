package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPosa implements Comando {
private IO io;
private String nomeAttrezzo;
private static final String NOME = "posa";



/**
 * Prende un oggetto dalla borsa e poi lo mette nella stanza. Stampa poi il
 * contenuto della borsa dopo l'azione. Se l'oggetto non c'è stampa i messaggi
 * di errore, stessa cosa per la stanza piena e la borsa vuota.
 *
 * @param il nome dell'attrezzo da posare
 */

	@Override
	public void esegui(Partita partita) {
		Attrezzo daAggiungere;
		if (partita.giocatore.getBorsa().isEmpty()) {
			io.mostraMessaggio("La borsa è vuota");
		} else if (partita.giocatore.getBorsa().hasAttrezzo(nomeAttrezzo)) {
			daAggiungere = partita.giocatore.getBorsa().getAttrezzo(nomeAttrezzo);
			partita.giocatore.getBorsa().removeAttrezzo(nomeAttrezzo);
			if(partita.getStanzaCorrente().addAttrezzo(daAggiungere)) {
				io.mostraMessaggio("Oggetto posato");
				io.mostraMessaggio(partita.giocatore.getBorsa().toString());
			}
			
		} else if (partita.giocatore.getBorsa().hasAttrezzo(nomeAttrezzo)==false) {
			io.mostraMessaggio("La borsa non ha questo oggetto");
		}
		
	}

	@Override
	public void setParametro(String parametro) {
		this.nomeAttrezzo = parametro;
	}

	@Override
	public String getNome() {
		// TODO Auto-generated method stub
		return NOME;
	}

	@Override
	public String getParametro() {
		// TODO Auto-generated method stub
		return nomeAttrezzo;
	}

	@Override
	public void setIO(IO io) {
		this.io = io;
	}
	

}
