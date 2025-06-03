package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBloccata extends Stanza {
	private Direzione direzionebloccata;
	private String attrezzochefapa;

	public StanzaBloccata(String nome, Direzione direzione, String attrezzochefapa) {
		super(nome);
		this.direzionebloccata = direzione;
		this.attrezzochefapa = attrezzochefapa;
	}

	@Override
	public Stanza getStanzaAdiacente(Direzione dir) {
		Stanza stanza = null;
		if(dir != direzionebloccata) {
			stanza = super.getStanzaAdiacente(dir);
		}else if(dir == direzionebloccata) {
			if(this.hasAttrezzo(attrezzochefapa)) {
				stanza = super.getStanzaAdiacente(dir);
			}else {
				stanza = this;
			}
		}
		return stanza;
	}

	@Override 
	public String getDescrizione() {
		return toString();
	}

	@Override
	public String toString() {
		StringBuilder risultato = new StringBuilder();
		risultato.append(this.nome);
		risultato.append("\nUscite: ");
		for (Direzione direzione : this.stanzeAdiacenti.keySet()) {
			if (direzione != null) {
				risultato.append(" " + direzione);
			}
		}
		for(Direzione direzione :this.stanzeAdiacenti.keySet()) {
			if (direzione == direzionebloccata) {
				risultato.append("\nDirezione bloccata " + direzione);
			}
		}
		risultato.append("\nAttrezzi nella stanza: ");
		for (Attrezzo attrezzo : this.attrezzi.values()) {
			if (attrezzo != null) {
				risultato.append(attrezzo.toString() + " ");
			}
		}
		risultato.append("\nL'attrezzo che sblocca la direzione " + this.direzionebloccata + " è " + this.attrezzochefapa);
		return risultato.toString();
	}
}




