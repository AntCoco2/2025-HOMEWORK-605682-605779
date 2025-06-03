package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoRegala extends AbstractComando {
	private final  String NOME = "regala";
	

	@Override
	public void esegui(Partita partita) {
		if(partita.getStanzaCorrente().hasPersonaggio()) {
		Attrezzo attrezzo = partita.getGiocatore().getBorsa().getAttrezzo(parametro);
		if(attrezzo == null) {
			io.mostraMessaggio("Non hai questo strumento!");
			return;
		}
		io.mostraMessaggio(partita.getStanzaCorrente().getPersonaggio().riceviRegalo(attrezzo, partita));
		
		
		
	}else {
		io.mostraMessaggio("Non c'è nessuno!");
	}
	}

	

	@Override
	public String getNome() {
		// TODO Auto-generated method stub
		return NOME;
	}
	
	@Override
	public void setIO(IO io) {
		this.io = io;
	}

	

	

}
