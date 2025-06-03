package it.uniroma3.diadia.ambienti;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

/**
 * Classe Stanza - una stanza in un gioco di ruolo. Una stanza e' un luogo
 * fisico nel gioco. E' collegata ad altre stanze attraverso delle uscite. Ogni
 * uscita e' associata ad una direzione.
 * 
 * @author docente di POO
 * @see Attrezzo
 * @version versione.A
 */


public class Stanza {

	static final private int NUMERO_MASSIMO_DIREZIONI = 4;
	static final private int NUMERO_MASSIMO_ATTREZZI = 10;

	protected String nome;
	
	protected Map <String, Attrezzo> attrezzi;
	protected int numeroAttrezzi;
	protected Map <Direzione, Stanza> stanzeAdiacenti;
	protected int numeroStanzeAdiacenti;
	protected AbstractPersonaggio personaggio;


	/**
	 * Crea una stanza. Non ci sono stanze adiacenti, non ci sono attrezzi.
	 * 
	 * @param nome il nome della stanza
	 */
	public Stanza(String nome)  {
		this.nome = nome;
		this.numeroStanzeAdiacenti = 0;
		this.stanzeAdiacenti = new HashMap();
		this.attrezzi = new HashMap();
		this.numeroAttrezzi = 0;
	
	}

	/**
	 * Imposta una stanza adiacente.
	 *
	 * @param direzione direzione in cui sara' posta la stanza adiacente.
	 * @param stanza    stanza adiacente nella direzione indicata dal primo
	 *                  parametro.
	 */
	public void impostaStanzaAdiacente(Direzione direzione, Stanza stanza) {
		if(this.stanzeAdiacenti.containsKey(direzione)) {
			return;
		}else if(this.stanzeAdiacenti.containsValue(stanza)) {
			return;
		}else {
			this.stanzeAdiacenti.put(direzione, stanza);
			this.numeroStanzeAdiacenti++;
		}
	}

	/**
	 * Restituisce la stanza adiacente nella direzione specificata
	 * 
	 * @param direzione
	 */
	public Stanza getStanzaAdiacente(Direzione direzione) {
		Stanza s = null;
		if(this.stanzeAdiacenti.containsKey(direzione)) {
			s = this.stanzeAdiacenti.get(direzione);
		}
		return s;
	}

	/**
	 * Restituisce la nome della stanza.
	 * 
	 * @return il nome della stanza
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * Restituisce la descrizione della stanza.
	 * 
	 * @return la descrizione della stanza
	 */
	public String getDescrizione() {
		return this.toString();
	}

	/**
	 * Restituisce la collezione di attrezzi presenti nella stanza.
	 * 
	 * @return la collezione di attrezzi nella stanza.
	 */
	public Map <String, Attrezzo> getAttrezzi() {
		return this.attrezzi;
	}

	/**
	 * Mette un attrezzo nella stanza.
	 * 
	 * @param attrezzo l'attrezzo da mettere nella stanza.
	 * @return true se riesce ad aggiungere l'attrezzo, false atrimenti.
	 */
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (this.numeroAttrezzi < NUMERO_MASSIMO_ATTREZZI) {
			this.numeroAttrezzi++;
			 this.attrezzi.put(attrezzo.getNome(),attrezzo);
			 return true;
		} else {
			return false;
		}
	}

	/**
	 * Restituisce una rappresentazione stringa di questa stanza, stampadone la
	 * descrizione, le uscite e gli eventuali attrezzi contenuti
	 * 
	 * @return la rappresentazione stringa
	 */
	public String toString() {
		StringBuilder risultato = new StringBuilder();
		risultato.append(this.nome);
		risultato.append("\nUscite: ");
		for (Direzione direzione : this.stanzeAdiacenti.keySet())
			if (direzione != null)
				risultato.append(" " + direzione);
		risultato.append("\nAttrezzi nella stanza: ");
		for (Attrezzo attrezzo : this.attrezzi.values()) {
			if (attrezzo != null) {
				risultato.append(attrezzo.toString() + " ");
			}
		}
		if(this.getPersonaggio()!=null) {
			risultato.append("\nPersonaggio nella stanza:" + this.getPersonaggio().getNome());
		}else {
			risultato.append("\nNon ci sono personaggi nella stanza");
		}
		return risultato.toString();

	}

	/**
	 * Controlla se un attrezzo esiste nella stanza (uguaglianza sul nome).
	 * 
	 * @return true se l'attrezzo esiste nella stanza, false altrimenti.
	 */
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.containsKey(nomeAttrezzo);
	}

	/**
	 * Restituisce l'attrezzo nomeAttrezzo se presente nella stanza.
	 * 
	 * @param nomeAttrezzo
	 * @return l'attrezzo presente nella stanza. null se l'attrezzo non e' presente.
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		Attrezzo a = null;
		if(this.attrezzi.containsKey(nomeAttrezzo)) {
			a = this.attrezzi.get(nomeAttrezzo);
		}
		return a;
	}

	/**
	 * Rimuove un attrezzo dalla stanza (ricerca in base al nome).
	 * 
	 * @param nomeAttrezzo
	 * @return true se l'attrezzo e' stato rimosso, false altrimenti
	 */
	public boolean removeAttrezzo(Attrezzo attrezzo) {
	    if (this.attrezzi.containsValue(attrezzo)) {
	        String nomeAttrezzo = attrezzo.getNome(); 
	        this.attrezzi.remove(nomeAttrezzo); 
	        this.numeroAttrezzi--;
	        return true;
	    } else {
	        return false;
	    }
	}
	/*
	 * Da al giocatore le direzioni accessibili da una stanza
	 * 
	 * @return un array di direzioni dove puoi andare
	 */

	public Set <Direzione> getDirezioni(){
		return this.stanzeAdiacenti.keySet();
	}
    /**
	 * Dice se la stanza è vuota.
     *
	 * @return true se la stanza è vuota, false altrimenti
	 */
	public boolean isEmpty() {
		return this.numeroAttrezzi == 0;
	}
	public int getNumeroAttrezzi() {
		return this.numeroAttrezzi;
	}
	public int getStanzeAdiacenti() {
		return this.numeroStanzeAdiacenti;
	}
	
	@Override
	public int hashCode() {
		return this.getNome().hashCode()+this.numeroAttrezzi+this.numeroStanzeAdiacenti;
	}
	
	@Override
	public boolean equals(Object o) {
		Stanza that = (Stanza) o;
		return this.getNome().equals(that.getNome()) && this.numeroAttrezzi == that.getNumeroAttrezzi() && this.numeroStanzeAdiacenti == that.getStanzeAdiacenti();
	}
	
	public AbstractPersonaggio getPersonaggio() {
		return this.personaggio;
	}
	
	public void setPersonaggio(AbstractPersonaggio personaggio) {
		this.personaggio = personaggio;
	}
	public boolean hasPersonaggio() {
		return this.personaggio != null;
	}
	
	public Map <Direzione, Stanza> getStanzeAdiacentiMappa(){
		return this.stanzeAdiacenti;
	}

}