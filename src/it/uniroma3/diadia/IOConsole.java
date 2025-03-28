/**
* Classe che gestisce gli scanner ed i messaggi di stampa
* 
*
* @author Coco Antonio (Matricola 605682), Villa Patrizio (Matricola 605779)
* 
* 
* @version versione.A
*/



package it.uniroma3.diadia;
import java.util.Scanner;
public class IOConsole {
	/**
	 *Metodo che mostra un messaggio
	 * 
	 */
	public void mostraMessaggio(String msg) {
		 System.out.println(msg);
		}
	/**
	 * Metodo che legge un comando dall'utente
	 * 
	 * 
	 */
		 public String leggiRiga() {
		 Scanner scannerDiLinee = new Scanner(System.in);
		 String riga = scannerDiLinee.nextLine();
		 return riga;
		 }

}
