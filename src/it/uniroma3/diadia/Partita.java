package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Giocatore;

/**
 * Questa classe modella una partita del gioco
 *
 * @author  docente di POO
 * @see Stanza
 * @version versione.A
 */

public class Partita {

	static final private int CFU_INIZIALI = 20;
	public boolean finita;
	public int cfu;
	public Labirinto labirinto;
	public Giocatore giocatore;
	/*
	 * Costruttore di partita
	 * crea la partita del giocatore
	 * 
	 * 
	 */
	public Partita(){
		
		this.labirinto = new Labirinto();
		this.labirinto.init();
		this.finita = false;
		this.cfu = CFU_INIZIALI;
		this.giocatore = new Giocatore(this.cfu);
	}

    /**
	 * Imposta la stanza corrente del labirinto
     *
	 * @param la stanza corrente
	 */
	public void setStanzaCorrente(Stanza stanzaCorrente) {
 		 this.labirinto.setStanzaCorrente(stanzaCorrente);
 	}
   
    /**
	 * Ritorna a DiaDia la stanza vincente 
     *
	 * @return la stanza vincente
	 */
	public Stanza getStanzaVincente() {
		return this.labirinto.stanzaVincente;
	}
    /**
	 * Ritorna a Diadia la stanza corrente
     *
	 * @return la stanza corrente
	 */
	public Stanza getStanzaCorrente() {
	 	return this.labirinto.stanzaCorrente;
	}
	
	/**
	 * Restituisce vero se e solo se la partita e' stata vinta
     *
	 * @return vero se partita vinta
	 */
	public boolean vinta() {
		return this.getStanzaCorrente()== this.getStanzaVincente();
	}

	/**
	 * Restituisce vero se e solo se la partita e' finita
     *
	 * @return vero se partita finita
	 */
	public boolean isFinita() {
		return finita || vinta() || (cfu == 0);
	}

	/**
	 * Imposta la partita come finita
	 *
	 */
	public void setFinita() {
		this.finita = true;
	}
    /**
	 * Ritorna a DiaDia i cfu del giocatore
     *
	 * @return i cfu del giocatore
	 */
    public int getCfu() {
		return this.giocatore.cfu;
	}
    /**
	 * Imposta i cfu del giocatore
     *
	 * @param i cfu del giocatore
	 */
	public void setCfu(int cfu) {
		this.giocatore.setCfu(cfu);
	}
	public Giocatore getGiocatore() {
		return this.giocatore;
	}
	/**
	 * Controlla se i cfu del giocatore sono esauriti
	 *
	 * @return true se non sono esauriti
	 */
	public boolean giocatoreIsVivo() {
		return this.getGiocatore().getCfu() != 0;
	}
}
