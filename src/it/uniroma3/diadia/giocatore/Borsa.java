package it.uniroma3.diadia.giocatore;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import it.uniroma3.diadia.Configuratore;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.attrezzi.ComparatorePerPeso;

/**
 * Nome: Borsa Una classe che modella una borsa della giocatore. Ogni giocatore
 * può prendere o posare oggetti dalla borsa o dalla stanza
 * 
 *
 * @author Coco Antonio (Matricola 605682), Villa Patrizio (Matricola 605779)
 * @see Attrezzo
 * @version versione.A
 */

public class Borsa {
	public final static int DEFAULT_PESO_MAX_BORSA = Configuratore.getPesoMax();
	private Map<String, Attrezzo> attrezzi;
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
		this.attrezzi = new HashMap();
		;
	}

	/**
	 * Restituisce vero se l'attrezzo è stato aggiunto correttamente
	 * 
	 * @param Attrezzo da aggiungere
	 * @return vero se attrezzo è stato aggiunto
	 */
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if(attrezzi == null) {
			return false;
		}
		if (this.getPeso() + attrezzo.getPeso() > this.getPesoMax())
			return false;
		else {
			this.attrezzi.put(attrezzo.getNome(), attrezzo);
			return true;

		}
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
		if (this.attrezzi.containsKey(nomeAttrezzo)) {
			a = this.attrezzi.get(nomeAttrezzo);

		}
		return a;
	}

	/**
	 * public Attrezzo getAttrezzo(String nomeAttrezzo){ Attrezzi a = null (if
	 * this.attrezzi.contains(nomeAttrezzo.equals(a1.getNome()){ a =
	 */

	/**
	 * Ritorna il peso totale degli Attrezzi nella borsa
	 *
	 * @return peso attrezzi
	 */
	public int getPeso() {
		int peso = 0;
		for (Attrezzo attrezzo : this.attrezzi.values()) {
			peso += attrezzo.getPeso();
		}
		return peso;
	}

	/**
	 * Ritorna true se la borsa non ha elementi
	 *
	 * @return boolean a seconda se la borsa è vuota o meno
	 */
	public boolean isEmpty() {
		return this.attrezzi.isEmpty();
	}

	/**
	 * Ci dice se nella borsa c'è un attrezzo che cerchiamo
	 * 
	 * @return true se l'attrezzo cercato sta nella borsa
	 */
	public boolean hasAttrezzo(String nomeAttrezzo) {
		if(this.attrezzi.containsKey(nomeAttrezzo)) {
			return true;
		}else {
			return false;
		}
	}

	/**
	 * Rimuove un attrezzo dalla borsa
	 * 
	 * @param nome dell'attrezzo da rimuovere
	 * @return Indirizzo dell'attrezzo rimosso
	 */
	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
		Attrezzo a = null;
		if(this.attrezzi.containsKey(nomeAttrezzo)) {
			a = this.attrezzi.get(nomeAttrezzo);
			this.attrezzi.remove(nomeAttrezzo);
		}

		return a;
	}
	
	public List <Attrezzo> getContenutoOrdinatoPerPeso(){
		List <Attrezzo> ret = new LinkedList<>(this.attrezzi.values());
		ComparatorePerPeso comp = new ComparatorePerPeso();
		Collections.sort(ret, comp);
		return ret;
	}
	
	public SortedSet <Attrezzo> getContenutoOrdinatoPerNome(){
		SortedSet Ord = new TreeSet<>(this.attrezzi.values());
		return Ord;
		
	}
	public Map<Integer,Set<Attrezzo>> getContenutoRaggruppatoPerPeso(){
		Map <Integer, Set<Attrezzo>> rag = new HashMap();
		for (Attrezzo attrezzo : this.attrezzi.values()) { 
	        int peso = attrezzo.getPeso();
	        
	        
	        rag.putIfAbsent(peso, new HashSet<Attrezzo>());

	        
	        rag.get(peso).add(attrezzo);
	    }

	    return rag;
		
		
	}
	public SortedSet<Attrezzo> getSortedSetOrdinatoPerPeso(){
		SortedSet ret = new TreeSet<Attrezzo>(new ComparatorePerPeso());
		ret.addAll(this.attrezzi.values());
		return ret;
	}

	/*
	 * Stampa il contenuto della borsa, indicando il peso portato e gli oggetti
	 * portati ed il loro peso
	 */
	public String toString() {
		StringBuilder s = new StringBuilder();
		if (!this.isEmpty()) {
			s.append("Contenuto borsa (" + this.getPeso() + "kg/" + this.getPesoMax() + "kg): ");
			s.append("\n");
			s.append(this.getContenutoOrdinatoPerNome().toString());
			s.append("\n");
			s.append(this.getContenutoOrdinatoPerPeso().toString());
			s.append("\n");
			s.append(this.getContenutoRaggruppatoPerPeso().toString());
			s.append("\n");

		} else
			s.append("Borsa vuota");
		return s.toString();
	}

}