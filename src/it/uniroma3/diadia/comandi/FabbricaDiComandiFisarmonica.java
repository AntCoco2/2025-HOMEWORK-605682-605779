package it.uniroma3.diadia.comandi;

import java.util.Scanner;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.ambienti.Direzione;

public class FabbricaDiComandiFisarmonica implements FabbricaDiComandi {
	
	IO io;
	
	public FabbricaDiComandiFisarmonica(IO io) {
		this.io = io;
		
	}
	

    public Comando costruisciComando(String istruzione) throws Exception {
        Scanner scannerDiParole = new Scanner(istruzione);
        String nomeComando = null;
        String parametro = null;
        Comando comando = null;

        if (scannerDiParole.hasNext())
            nomeComando = scannerDiParole.next();

        if (scannerDiParole.hasNext())
        	parametro = scannerDiParole.next();

        if (nomeComando == null) {
        	comando = new ComandoNonValido();
        }

        else if (nomeComando.equals("vai") && parametro != null)
            comando = new ComandoVai(Direzione.valueOf(parametro.toUpperCase()));
        else if (nomeComando.equals("aiuto"))
            comando = new ComandoAiuto();
        else if (nomeComando.equals("fine"))
            comando = new ComandoFine();
        else if(nomeComando.equals("prendi"))
        	comando = new ComandoPrendi();
        else if (nomeComando.equals("posa"))
        	comando = new ComandoPosa();
        else if (nomeComando.equals("guarda")) {
        	comando = new ComandoGuarda();
        }else if (nomeComando.equals("regala")) {
        	comando = new ComandoRegala();
        }else if(nomeComando.equals("interagisci")) {
        	comando = new ComandoInteragisci();
        }else if(nomeComando.equals("saluta")) {
        	comando = new ComandoSaluta();
        }
        else 
        	comando = new ComandoNonValido();

        if (comando != null)
            comando.setParametro(parametro);
        
        comando.setIO(io);

        return comando;
    }
}
