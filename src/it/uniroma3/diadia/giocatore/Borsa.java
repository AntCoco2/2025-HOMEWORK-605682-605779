package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Nome: Borsa
 * Una classe che modella una borsa della giocatore. Ogni giocatore può prendere o
 * posare oggetti dalla borsa o dalla stanza
 * 
 *
 * @author Coco Antonio (Matricola 605682), Villa Patrizio (Matricola 605779)
 * @see Attrezzo
 * @version versione.A
 */

public class Borsa {
	public final static int DEFAULT_PESO_MAX_BORSA = 10;
	private Attrezzo[] attrezzi;
	private int numeroAttrezzi;
	private int pesoMax;

	public Borsa() {
		this(DEFAULT_PESO_MAX_BORSA);
	}
    /**
     * Crea una borsa
     *
     * @param il peso massimo della borsa
     */
	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;
		this.attrezzi = new Attrezzo[10]; // speriamo bastino...
		this.numeroAttrezzi = 0;
	}
    /** 
     * Restituisce vero se l'attrezzo è stato aggiunto correttamente
     * 
	 * @param Attrezzo da aggiungere
	 * @return vero se attrezzo è stato aggiunto
	 */
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (this.getPeso() + attrezzo.getPeso() > this.getPesoMax() && attrezzo!=null)
			return false;
		if (this.numeroAttrezzi == 10)
			return false;
		this.attrezzi[this.numeroAttrezzi] = attrezzo;
		this.numeroAttrezzi++;
		return true;
	}
    /** 
     * Restituisce il peso massimo della Borsa
     *
	 * @return peso massimo
	 */
	public int getPesoMax() {
		return pesoMax;
	}
    /** 
     * Restituisce l'indirizzo dell'Attrezzo
     *
	 * @param Nome dell'attrezzo
	 * @return indirizzo dell'attrezzo
     *
     */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		Attrezzo a = null;
		for (int i = 0; i < this.numeroAttrezzi; i++)
			if (this.attrezzi[i].getNome().equals(nomeAttrezzo))
				a = attrezzi[i];
		return a;
	}
    /** 
     * Ritorna il peso totale degli Attrezzi nella borsa
     *
	 * @return peso attrezzi
	 */
	public int getPeso() {
		int peso = 0;
		for (int i = 0; i < this.numeroAttrezzi; i++)
			if(attrezzi[i]!=null) {
			peso += this.attrezzi[i].getPeso();
		
	}return peso;
	}
    /** 
     * Ritorna true se la borsa non ha elementi
     *
	 * @return boolean a seconda se la borsa è vuota o meno
	 */
	public boolean isEmpty() {
		return this.numeroAttrezzi == 0;
	}
    /**
     * Ci dice se nella borsa c'è un attrezzo che cerchiamo
	 * 
     * @return true se l'attrezzo cercato sta nella borsa
	 */
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.getAttrezzo(nomeAttrezzo) != null;
	}
    /** 
     * Rimuove un attrezzo dalla borsa
	 * 
     * @param nome dell'attrezzo da rimuovere 
	 * @return Indirizzo dell'attrezzo rimosso
     */
	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
		Attrezzo a = null;
		for(int i = 0; i<attrezzi.length;i++) {
			if(attrezzi[i]!=null) {
				if(attrezzi[i].getNome().equals(nomeAttrezzo)) {
					a = attrezzi[i];
					for(int j = i; j<attrezzi.length-1;j++) {
						attrezzi[j] = attrezzi[j+1];
					}
					attrezzi[attrezzi.length-1] = null;
				}
			}
		}
		
		return a;
	}
/*Stampa il contenuto della borsa, indicando il peso portato e gli oggetti portati ed il loro peso*/
	public String toString() {
		StringBuilder s = new StringBuilder();
		if (!this.isEmpty()) {
			s.append("Contenuto borsa (" + this.getPeso() + "kg/" + this.getPesoMax() + "kg): ");
			for (int i = 0; i < this.numeroAttrezzi; i++) {
				if(attrezzi[i]!=null) {
					s.append(attrezzi[i].toString() + " ");
				}
			}
				
		} else
			s.append("Borsa vuota");
		return s.toString();
	}
    /**
     * Dice se la borsa è piena, se lo è ritorna true
     * altrimenti false
     *
     * @return se la borsa è piena o no
     */
	public boolean isFull() {
		return this.numeroAttrezzi == 10;
	}
}