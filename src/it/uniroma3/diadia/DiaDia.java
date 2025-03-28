package it.uniroma3.diadia;

import java.util.Scanner;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il letodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author docente di POO (da un'idea di Michael Kolling and David J. Barnes)
 * 
 * @version versione.A
 */

public class DiaDia {

	static final private String MESSAGGIO_BENVENUTO = ""
			+ "Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n"
			+ "Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"
			+ "I locali sono popolati da strani personaggi, " + "alcuni amici, altri... chissa!\n"
			+ "Ci sono attrezzi che potrebbero servirti nell'impresa:\n"
			+ "puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n"
			+ "o regalarli se pensi che possano ingraziarti qualcuno.\n\n"
			+ "Per conoscere le istruzioni usa il comando 'aiuto'.";

	static final private String[] elencoComandi = { "vai", "aiuto", "fine", "prendi", "posa" };

	private Partita partita;
	private String nomeAttrezzo;
	private Attrezzo daLevare;
	private Attrezzo daAggiungere;
	private IOConsole console;
	
	
/*
 * Inizializza il gioco, ricevendo la console di input ed output
 * ed inizializza la partita
 * 
 * @param la console di interazione con l'utente
 */

	public DiaDia(IOConsole console) {
		this.console = console;
		this.partita = new Partita();
	}
/*
 * Stampa il messaggio di benvenuto e 
 * riceve le istruzioni dell'utente
 * 
 * 
 */
	public void gioca() {
		String istruzione;

		this.console.mostraMessaggio(MESSAGGIO_BENVENUTO);
		do
			istruzione = this.console.leggiRiga();
		while (!processaIstruzione(istruzione));
	}

	/**
	 * Processa una istruzione
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false
	 *         altrimenti
	 */
	private boolean processaIstruzione(String istruzione) {
		Comando comandoDaEseguire = new Comando(istruzione);
        if(comandoDaEseguire.getNome() == null) {
            return false;
        }
		if (comandoDaEseguire.getNome().equals("fine")) {
			this.fine();
			return true;
		} else if (comandoDaEseguire.getNome().equals("vai"))
			this.vai(comandoDaEseguire.getParametro());
		else if (comandoDaEseguire.getNome().equals("aiuto"))
			this.aiuto();
		else if (comandoDaEseguire.getNome().equals("prendi"))
			this.prendi(comandoDaEseguire.getParametro());
		else if(comandoDaEseguire.getNome().equals("posa"))
			this.posa(comandoDaEseguire.getParametro());
		else
			console.mostraMessaggio("Comando sconosciuto");
		if (this.partita.vinta()) {
			console.mostraMessaggio("Hai vinto!");
			return true;
		} else
			return false;
	}

	// implementazioni dei comandi dell'utente:
    /**  
     *Prende un oggetto dalla stanza e poi lo mette nella borsa
     *  stampa poi il contenuto della borsa dopo l'azione. Se l'oggetto 
     *  non c'è stampa i messaggi di errore, stessa cosa per la stanza vuota e la borsa
     *  piena.
     *
     *  @param il nome dell'attrezzo da prendere
     */
	private void prendi(String nomeAttrezzo) {
		if (partita.giocatore.getBorsa().isFull()) {
			console.mostraMessaggio("La borsa è piena!");
		} else if (partita.labirinto.getStanzaCorrente().isEmpty()) {
			console.mostraMessaggio("La stanza è vuota!");

		}else {
			if(partita.labirinto.getStanzaCorrente().hasAttrezzo(nomeAttrezzo)) {
				daLevare = partita.labirinto.getStanzaCorrente().getAttrezzo(nomeAttrezzo);		
				partita.labirinto.getStanzaCorrente().removeAttrezzo(daLevare);
				if(partita.giocatore.getBorsa().addAttrezzo(daLevare)) {
					console.mostraMessaggio("Oggetto aggiunto alla borsa!");
					console.mostraMessaggio(partita.giocatore.getBorsa().toString());
				}else {
					console.mostraMessaggio("Oggetto non aggiunto!");
				}
			}
			else {
				console.mostraMessaggio("La stanza non ha l'oggetto che vuoi!");
			}
			
		}
	}
    /**  
     *  Prende un oggetto dalla borsa e poi lo mette nella stanza.
     *  Stampa poi il contenuto della borsa dopo l'azione. Se l'oggetto 
     *  non c'è stampa i messaggi di errore, stessa cosa per la stanza piena e la borsa
     *  vuota.
     *
     *  @param il nome dell'attrezzo da posare
     */
	private void posa(String nomeAttrezzo) {
		if (partita.giocatore.getBorsa().isEmpty()) {
			console.mostraMessaggio("La borsa è vuota");
		} else if (partita.giocatore.getBorsa().hasAttrezzo(nomeAttrezzo)) {
			daAggiungere = partita.giocatore.getBorsa().getAttrezzo(nomeAttrezzo);
			partita.giocatore.getBorsa().removeAttrezzo(nomeAttrezzo);
			if(partita.getStanzaCorrente().addAttrezzo(daAggiungere)) {
				console.mostraMessaggio("Oggetto posato");
				console.mostraMessaggio(partita.giocatore.getBorsa().toString());
			}
			
		} else if (partita.giocatore.getBorsa().hasAttrezzo(nomeAttrezzo)==false) {
			console.mostraMessaggio("La borsa non ha questo oggetto");
		}
	}

	/**
	 * Stampa informazioni di aiuto.
	 */
	private void aiuto() {
		for (int i = 0; i < elencoComandi.length; i++)
			console.mostraMessaggio(elencoComandi[i] + " ");
		console.mostraMessaggio("\n");
	}

	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra e ne stampa il
	 * nome, altrimenti stampa un messaggio di errore
	 */
	private void vai(String direzione) {
		if (direzione == null)
			console.mostraMessaggio("Dove vuoi andare ?");
		Stanza prossimaStanza = null;
		prossimaStanza = this.partita.getStanzaCorrente().getStanzaAdiacente(direzione);
		if (prossimaStanza == null)
			console.mostraMessaggio("Direzione inesistente");
		else {
			this.partita.setStanzaCorrente(prossimaStanza);
			int cfu = this.partita.getCfu();
			this.partita.setCfu(cfu--);
		}
		console.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
	}

	/**
	 * Comando "Fine".
	 */
	private void fine() {
		console.mostraMessaggio("Grazie di aver giocato!"); // si desidera smettere
	}

	public static void main(String[] argc) {
		IOConsole console = new IOConsole();
		DiaDia gioco = new DiaDia(console);
		gioco.gioca();
		
	}
}