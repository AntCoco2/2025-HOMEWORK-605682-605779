package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoSaluta extends AbstractComando {
	private IO io;
	private static final String NOME = "saluta";
	private static final String SAL = "Hai salutato il personaggio nella stanza!";
	private static final String SALG = "Hai già salutato il personaggio nella stanza!";
	private static final String SALN = "Non c'è nessuno!";





	@Override
	public String getNome() {
		return NOME;
	}

	
	
	@Override
	public void esegui (Partita partita) {
		if(partita.getStanzaCorrente().hasPersonaggio()) {
			if (partita.getStanzaCorrente().getPersonaggio().haSalutato()) {
				io.mostraMessaggio(SALG);
			} else {
				io.mostraMessaggio(SAL);
				io.mostraMessaggio(partita.getStanzaCorrente().getPersonaggio().saluta());
			}
		}else {
			io.mostraMessaggio(SALN);
		}
		
	}
	@Override
	public void setIO(IO io) {
		this.io = io;
	}

}
