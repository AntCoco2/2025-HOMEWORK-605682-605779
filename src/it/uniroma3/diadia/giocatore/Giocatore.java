package it.uniroma3.diadia.giocatore;
/**
* Nome: Giocatore
* Una classe che modella un giocatore della partita. Ogni giocatore ha una
* dei cfu ed una borsa
* 
*
* @author Coco Antonio (Matricola 605682), Villa Patrizio (Matricola 605779)
* @see Borsa
* @version versione.A
*/

public class Giocatore {
	public int cfu;
	private Borsa borsa;
	
    /**
     * Crea un giocatore
     * 
     *
     * @param i cfu del giocatore
     */
	public Giocatore(int cfu) {
		this.cfu = cfu;
		this.borsa = new Borsa();
		
	}
    /** 
     * Metodo che ritorna alla classe partita i Cfu del giocatore.
     *
     * @return una variabile int
     */
	public int getCfu() {
		return this.cfu;
	}
    /** 
     * Metodo che imposta i Cfu del giocatore.
     *
     * @param i cfu del giocatore
     */
	public void setCfu(int cfu) {
		this.cfu = cfu;		
	}
    /** 
     * Metodo che ritorna alla classe partita il riferimento alla borsa del giocatore.
     *
     * @return riferimento a borsa
     */	
	public Borsa getBorsa() {
		return this.borsa;
	}
}


