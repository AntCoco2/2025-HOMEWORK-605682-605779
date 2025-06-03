package it.uniroma3.diadia.ambienti;


import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBuiaProtected extends StanzaProtected {
	private String oggettoNecessario;
	

	public StanzaBuiaProtected(String nome, String oggettoNecessario) {
		super(nome);
		this.oggettoNecessario = oggettoNecessario;
		
	}
	@Override
	public String getDescrizione() {
		if(this.hasAttrezzo(oggettoNecessario)) {
		return this.toString();
	}else {
		return "qui c'è buio pesto";
		
	}
	
	
	

}
	@Override
	public String toString() {
		StringBuilder risultato = new StringBuilder();
		risultato.append(super.nome);
		risultato.append("\nUscite: ");
		for (String direzione : this.direzioni)
			if (direzione != null)
				risultato.append(" " + direzione);
		risultato.append("\nAttrezzi nella stanza: ");
		for (Attrezzo attrezzo : this.attrezzi) {
			if (attrezzo != null) {
				risultato.append(attrezzo.toString() + " ");
			}
		}
		return risultato.toString();

	}
}