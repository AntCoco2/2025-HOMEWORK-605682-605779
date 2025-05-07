package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBloccata extends Stanza {
	private String direzionebloccata;
	private String attrezzochefapa;

	public StanzaBloccata(String nome, String direzionebloccata, String attrezzochefapa) {
		super(nome);
		this.direzionebloccata = direzionebloccata;
		this.attrezzochefapa = attrezzochefapa;
	}

	@Override
	public Stanza getStanzaAdiacente(String dir) {
		if (this.hasAttrezzo(attrezzochefapa)) {
			return super.getStanzaAdiacente(dir);
		} else {
			return this;
		}
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
		for (String direzione : this.direzioni) {
			if (direzione != null) {
				risultato.append(" " + direzione);
				if (direzione.equals(direzionebloccata)) {
					risultato.append("\nDirezione bloccata" + direzione);
				}
			}
		}
		risultato.append("\nAttrezzi nella stanza: ");
		for (Attrezzo attrezzo : this.attrezzi) {
			if (attrezzo != null) {
				risultato.append(attrezzo.toString() + " ");
			}
		}
		risultato.append("L'attrezzo che sblocca la direzione " + this.direzionebloccata + "è " + this.attrezzochefapa);
		return risultato.toString();
	}
}




