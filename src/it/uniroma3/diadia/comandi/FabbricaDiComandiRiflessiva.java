package it.uniroma3.diadia.comandi;

import java.util.Scanner;

import it.uniroma3.diadia.IOConsole;

public class FabbricaDiComandiRiflessiva implements FabbricaDiComandi  {
	
	private IOConsole io;
	
	private FabbricaDiComandiRiflessiva(IOConsole io) {
		this.io = io;
	}

	@Override
	public Comando costruisciComando (String istruzione) throws Exception {
		Scanner scannerDiParole = new Scanner(istruzione);
        String nomeComando = null;
        String parametro = null;
        Comando comando = null;
        if (scannerDiParole.hasNext())
            nomeComando = scannerDiParole.next();

        if (scannerDiParole.hasNext())
            parametro = scannerDiParole.next();

		StringBuilder nomeClasse
		= new StringBuilder("it.uniroma3.diadia.comandi.Comando");
		try {
		nomeClasse.append( Character.toUpperCase(nomeComando.charAt(0)) );
		nomeClasse.append( nomeComando.substring(1) ) ;
		comando = (Comando)Class.forName(nomeClasse.toString()).newInstance();
		comando.setParametro(parametro);
		
		comando.setIO(io);
		return comando;
	}catch(Exception e) {
		comando = new ComandoNonValido();
		io.mostraMessaggio("Comando non valido");
	}
		return comando;

}
}
