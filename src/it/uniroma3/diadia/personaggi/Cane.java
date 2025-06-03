package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Cane extends AbstractPersonaggio {
	private final String MSG_BAU = "Ora ti mordo!";
	private final String CIBO = "osso";

	public Cane(String nome, String presentazione) {
		super(nome, presentazione);
	}

	@Override
	public String agisci(Partita partita) {
		String msg;
		partita.getGiocatore().setCfu(partita.getGiocatore().getCfu() - 1);
		msg = MSG_BAU;
		return msg;
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		String msg;
		if(partita.getGiocatore().getBorsa().hasAttrezzo(attrezzo.getNome())) {
			if(partita.getGiocatore().getBorsa().getAttrezzo(attrezzo.getNome()).nomeUguale(CIBO)){
				msg = "Mi hai dato il mio cibo preferito!";
				partita.getGiocatore().getBorsa().removeAttrezzo(attrezzo.getNome());
				partita.getStanzaCorrente().addAttrezzo(new Attrezzo("giacca", 4));
				return msg;
				
			}else {
				msg = "NON MI PIACE!";
				partita.getGiocatore().setCfu(partita.getGiocatore().getCfu() - 1);
				return msg;
			}
		}else {
			msg = "Non lo hai!";
			return msg;
		}
	}

}
