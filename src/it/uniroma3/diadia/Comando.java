package it.uniroma3.diadia;



import java.util.Scanner;

/**
 * Questa classe modella un comando.
 * Un comando consiste al piu' di due parole:
 * il nome del comando ed un parametro
 * su cui si applica il comando.
 * (Ad es. alla riga digitata dall'utente "vai nord"
 *  corrisponde un comando di nome "vai" e parametro "nord").
 *
 * @author  docente di POO
 * @version versione.A
 */

public class Comando {

    private String nome;
    private String parametro;
    /*
     * Costruttore di un comando 
     * 
     * @param l'istruzione che si vuole eseguire
     */

    public Comando(String istruzione) {
		Scanner scannerDiParole = new Scanner(istruzione);

		// prima parola: nome del comando
		if (scannerDiParole.hasNext())
			this.nome = scannerDiParole.next(); 

		// seconda parola: eventuale parametro
		if (scannerDiParole.hasNext())
			this.parametro = scannerDiParole.next();
    }
    /*
     * Ritorna il nome
     * 
     * @return il nome
     */

    public String getNome() {
        return this.nome;
    }
    /*
     * Ritona il parametro
     * 
     * @return il parametro
     */

    public String getParametro() {
        return this.parametro;
    }
   /*
    * Dice se il comando ricevuto è sconosciuto
    * 
    * @return true se lo è, false altrimenti
    */
    public boolean sconosciuto() {
        return (this.nome == null);
    }
}