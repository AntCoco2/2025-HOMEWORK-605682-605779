package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;
/**
 * Nome: Labirinto
 * Una classe che modella un labirinto di una partita. Un labirinto ha una stanza
 *  da cui il giocatore inizia e una stanza dove se il giocatore arriva vince
 * 
 *
 * @author Coco Antonio (Matricola 605682), Villa Patrizio (Matricola 605779)
 * @see Attrezzo
 * @version versione.A
 */

public class Labirinto {
	public Stanza stanzaCorrente;    //stanza d'entrata
	public Stanza stanzaVincente;     //stanza d'uscita
	   /**
     * Crea tutte le stanze e le porte di collegamento
     */
    public void init() {

		/* crea gli attrezzi */
    	Attrezzo lanterna = new Attrezzo("lanterna",3);
		Attrezzo osso = new Attrezzo("osso",1);
    	
		/* crea stanze del labirinto */
		Stanza atrio = new Stanza("Atrio");
		Stanza aulaN11 = new Stanza("Aula N11");
		Stanza aulaN10 = new Stanza("Aula N10");
		Stanza laboratorio = new Stanza("Laboratorio Campus");
		Stanza biblioteca = new Stanza("Biblioteca");
		
		/* collega le stanze */
		atrio.impostaStanzaAdiacente("nord", biblioteca);
		atrio.impostaStanzaAdiacente("est", aulaN11);
		atrio.impostaStanzaAdiacente("sud", aulaN10);
		atrio.impostaStanzaAdiacente("ovest", laboratorio);
		aulaN11.impostaStanzaAdiacente("est", laboratorio);
		aulaN11.impostaStanzaAdiacente("ovest", atrio);
		aulaN10.impostaStanzaAdiacente("nord", atrio);
		aulaN10.impostaStanzaAdiacente("est", aulaN11);
		aulaN10.impostaStanzaAdiacente("ovest", laboratorio);
		laboratorio.impostaStanzaAdiacente("est", atrio);
		laboratorio.impostaStanzaAdiacente("ovest", aulaN11);
		biblioteca.impostaStanzaAdiacente("sud", atrio);

        /* pone gli attrezzi nelle stanze */
		aulaN10.addAttrezzo(lanterna);
		atrio.addAttrezzo(osso);

		// il gioco comincia nell'atrio
        stanzaCorrente = atrio;  
		stanzaVincente = biblioteca;
    }
    /*
     * Imposta la stanza corrente
     */
     public void setStanzaCorrente(Stanza stanzaCorrente) {
 		this.stanzaCorrente = stanzaCorrente;
 	}
     /*Ritorna alla partita la stanza corrente del giocatore
      * 
      * @return la stanza corrente del giocatore
      */
     public Stanza getStanzaCorrente() {
    	 return stanzaCorrente;
     }
     /*Ritorna la stanza vincente
      * 
      * @return la stanza vincente del gioco
      */
     public Stanza getStanzaVincente() {
    	 return stanzaVincente;
     }

}
