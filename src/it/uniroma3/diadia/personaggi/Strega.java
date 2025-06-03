package it.uniroma3.diadia.personaggi;
import it.uniroma3.diadia.ambienti.*;

import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.Iterator;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Strega extends AbstractPersonaggio {
	
	private Map<Direzione, Stanza> stanzeadiacenti;
	private boolean haSalutato;
	private final String MSG_NON = "Non mi hai salutato, ora verrai punito!";
	private final String MSG_PRE = "Grazie per avermi salutato, ora ti premio!";
	private Attrezzo attrezzo;
	

	public Strega(String nome, String presentazione) {
		super(nome, presentazione);
	}

	@Override
	public String agisci(Partita partita) {
		String msg;
		if(!haSalutato) {
			List <Stanza> stanze = new ArrayList<>(stanzeadiacenti.values());
			int min = stanze.get(0).getNumeroAttrezzi();
			Stanza stanzmin = stanze.get(0);
			Iterator<Stanza> it = stanze.iterator();
			for(Stanza s = it.next(); it.hasNext(); s = it.next() ) {
				if(s.getNumeroAttrezzi() < min) {
					min = s.getNumeroAttrezzi();
					stanzmin = s;
					
				}
				
			}
			partita.setStanzaCorrente(stanzmin);
			msg = MSG_NON;
			return msg;
			
			
		}else {
			List <Stanza> stanze = new ArrayList<>(stanzeadiacenti.values());
			int max = stanze.get(0).getNumeroAttrezzi();
			Stanza stanzmax = stanze.get(0);
			Iterator<Stanza> it = stanze.iterator();
			for(Stanza s = it.next(); it.hasNext(); s = it.next()) {
				if(s.getNumeroAttrezzi() > max) {
					max = s.getNumeroAttrezzi();
					stanzmax = s;
				}
			}
			partita.setStanzaCorrente(stanzmax);
			msg = MSG_PRE;
			return msg;
			
			
		}
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		String msg;
		if(partita.getGiocatore().getBorsa().hasAttrezzo(attrezzo.getNome())) {
			partita.getGiocatore().getBorsa().removeAttrezzo(attrezzo.getNome());
			msg = "Muahahaha fa ridere ahahahah";
			return msg;
		}else {
			msg = "Ma non lo hai!";
			return msg;
		}
		
	}
	
	public void setStanzeAdiacenti(Map <Direzione, Stanza> stanzeadiacenti) {
		this.stanzeadiacenti = stanzeadiacenti;
	}

}
