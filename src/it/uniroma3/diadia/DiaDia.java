package it.uniroma3.diadia;

import java.util.Scanner;

import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Labirinto.LabirintoBuilder;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.FabbricaDiComandiFisarmonica;
import it.uniroma3.diadia.personaggi.Mago;
import it.uniroma3.diadia.personaggi.Cane;

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
	private IO io;

	/*
	 * Inizializza il gioco, ricevendo la console di input ed output ed inizializza
	 * la partita
	 * 
	 * @param la console di interazione con l'utente
	 */

	public DiaDia(Labirinto labirinto, IO io) {
		this.io = io;
		this.partita = new Partita(labirinto);
	}

	/*
	 * Stampa il messaggio di benvenuto e riceve le istruzioni dell'utente
	 * 
	 * 
	 */
	public void gioca() throws Exception {
		String istruzione;

		this.io.mostraMessaggio(MESSAGGIO_BENVENUTO);
		do
			istruzione = this.io.leggiRiga();
		while (!processaIstruzione(istruzione));
	}

	/**
	 * Processa una istruzione
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false
	 *         altrimenti
	 * @throws Exception
	 */
	private boolean processaIstruzione(String istruzione) throws Exception {
		Comando comandoDaEseguire;
		FabbricaDiComandiFisarmonica factory = new FabbricaDiComandiFisarmonica(io);
		comandoDaEseguire = factory.costruisciComando(istruzione);
		comandoDaEseguire.esegui(this.partita);
		if (this.partita.vinta())
			io.mostraMessaggio("Hai vinto!");
		if (!this.partita.giocatoreIsVivo())
			io.mostraMessaggio("Hai finito i CFU...");
		return this.partita.isFinita();
	}

	public static void main(String[] argc) throws Exception {
		IO io = new IOConsole();
		LabirintoBuilder builder = Labirinto.newBuilder("Labirinto.txt");
		Labirinto labirinto = builder.getLabirinto();
		DiaDia gioco = new DiaDia(labirinto, io);
		try {
			gioco.gioca();
		}finally {
			io.chiudi();
		}
	}
}


