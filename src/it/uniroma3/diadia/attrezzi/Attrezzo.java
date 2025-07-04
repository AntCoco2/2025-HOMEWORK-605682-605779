package it.uniroma3.diadia.attrezzi;

import java.util.Comparator;

import it.uniroma3.diadia.ambienti.Stanza;

/**
 * Una semplice classe che modella un attrezzo.
 * Gli attrezzi possono trovarsi all'interno delle stanze
 * del labirinto.
 * Ogni attrezzo ha un nome ed un peso.
 *
 * @author  docente di POO
 * @see Stanza
 * @version versione.A
 */
public class Attrezzo implements Comparable <Attrezzo> {

	private String nome;
	private int peso;

	/**
	 * Crea un attrezzo
	 * @param nome il nome che identifica l'attrezzo
	 * @param peso il peso dell'attrezzo
	 */
	public Attrezzo(String nome, int peso) {
		this.peso = peso;
		this.nome = nome;
	}

	/**
	 * Restituisce il nome identificatore dell'attrezzo
	 * @return il nome identificatore dell'attrezzo
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * Restituisce il peso dell'attrezzo
	 * @return il peso dell'attrezzo
	 */
	public int getPeso() {
		return this.peso;
	}

	/**
	 * Restituisce una rappresentazione stringa di questo attrezzo
	 * @return la rappresentazione stringa
	 */
	public String toString() {
		return this.getNome()+" ("+this.getPeso()+"kg)";
	}
	
	@Override
	public int hashCode() {
		return this.getNome().hashCode();
	}
	@Override
	public boolean equals(Object o) {
		if (getClass() != o.getClass())
			return false;
		Attrezzo that = (Attrezzo) o;
		return this.getNome().equals(that.getNome());
	}

	@Override
	public int compareTo(Attrezzo o) {
		return this.nome.compareTo(o.getNome());
	}
	
	public void setPeso(int peso) {
		this.peso = peso;
	}
	
	public boolean nomeUguale(String nomeattrezzo) {
		return this.nome.equals(nomeattrezzo);
	}

	

	

}