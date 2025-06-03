package it.uniroma3.diadia.ambienti;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import it.uniroma3.diadia.CaricatoreLabirinto;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.Cane;
import it.uniroma3.diadia.personaggi.Mago;
import it.uniroma3.diadia.personaggi.Strega;

/**
 * Nome: Labirinto Una classe che modella un labirinto di una partita. Un
 * labirinto ha una stanza da cui il giocatore inizia e una stanza dove se il
 * giocatore arriva vince
 * 
 *
 * @author Coco Antonio (Matricola 605682), Villa Patrizio (Matricola 605779)
 * @see Attrezzo
 * @version versione.A
 */

public class Labirinto {
	private Stanza stanzaIniziale;
	private Stanza stanzaVincente;

	private Labirinto(String nomeFile) throws FileNotFoundException, FormatoFileNonValidoException {
		CaricatoreLabirinto c = new CaricatoreLabirinto(nomeFile);
		c.carica();
		this.stanzaIniziale = c.getStanzaIniziale();
		this.stanzaVincente = c.getStanzaVincente();
	}

	public static LabirintoBuilder newBuilder(String labirinto)
			throws FileNotFoundException, FormatoFileNonValidoException {
		return new LabirintoBuilder(labirinto);
	}

	public Stanza getStanzaVincente() {
		return stanzaVincente;
	}

	public void setStanzaVincente(Stanza stanzaVincente) {
		this.stanzaVincente = stanzaVincente;
	}

	public void setStanzaCorrente(Stanza stanzaCorrente) {
		this.stanzaIniziale = stanzaCorrente;
	}

	public Stanza getStanzaCorrente() {
		return this.stanzaIniziale;
	}

	public static class LabirintoBuilder {

		private Labirinto labirinto;
		private Stanza ultimaStanzaAggiunta;
		private Map<String, Stanza> stanze;

		public LabirintoBuilder(String labirinto) throws FileNotFoundException, FormatoFileNonValidoException {
			this.labirinto = new Labirinto(labirinto);
			this.stanze = new HashMap<>();
		}

		public Map<String, Stanza> getStanzeAdiacenti() {
			return stanze;
		}

		public Labirinto getLabirinto() {
			return this.labirinto;
		}

		public LabirintoBuilder addStanzaIniziale(String stanzaIniziale) {
			Stanza i = new Stanza(stanzaIniziale);
			this.labirinto.setStanzaCorrente(i);
			this.UltimaStanzaAggiuntaEAggiorna(i);
			return this;
		}

		public LabirintoBuilder addStanzaVincente(String stanzaVincente) {
			Stanza s = new Stanza(stanzaVincente);
			this.labirinto.setStanzaVincente(s);
			this.UltimaStanzaAggiuntaEAggiorna(s);
			return this;
		}

		public LabirintoBuilder addStanza(String nomeStanza) {
			Stanza s = new Stanza(nomeStanza);
			this.UltimaStanzaAggiuntaEAggiorna(s);
			return this;
		}

		public void UltimaStanzaAggiuntaEAggiorna(Stanza nomeStanza) {
			this.ultimaStanzaAggiunta = nomeStanza;
			this.stanze.put(nomeStanza.getNome(), nomeStanza);
		}

		public LabirintoBuilder addMago(String nome, String presentazione, Attrezzo attrezzo) {
			Mago daAggiungere = new Mago(nome, presentazione, attrezzo);
			if (this.ultimaStanzaAggiunta == null)
				return this;
			this.ultimaStanzaAggiunta.setPersonaggio(daAggiungere);
			return this;
		}

		public LabirintoBuilder addCane(String nome, String presentazione) {
			Cane daAggiungere = new Cane(nome, presentazione);
			if (this.ultimaStanzaAggiunta == null)
				return this;
			this.ultimaStanzaAggiunta.setPersonaggio(daAggiungere);
			return this;
		}

		public LabirintoBuilder addStrega(String nome, String presentazione) {
			Strega daAggiungere = new Strega(nome, presentazione);
			if (this.ultimaStanzaAggiunta == null)
				return this;
			this.ultimaStanzaAggiunta.setPersonaggio(daAggiungere);
			return this;
		}

		public LabirintoBuilder addAttrezzo(String attrezzo, int peso) {
			Attrezzo daAggiungere = new Attrezzo(attrezzo, peso);
			if (this.ultimaStanzaAggiunta == null)
				return this;
			this.ultimaStanzaAggiunta.addAttrezzo(daAggiungere);
			return this;
		}

		public LabirintoBuilder addAdiacenza(String stanzaCorrente, String stanzaAdiecente, Direzione direzione) {
			Stanza corrente = this.stanze.get(stanzaCorrente);
			Stanza adiacente = this.stanze.get(stanzaAdiecente);
			corrente.impostaStanzaAdiacente(direzione, adiacente);
			return this;
		}

		public LabirintoBuilder addStanzaMagica(String nome) {
			Stanza stanza = new StanzaMagica(nome);
			this.UltimaStanzaAggiuntaEAggiorna(stanza);
			return this;
		}

		public LabirintoBuilder addStanzaBuia(String nome, String attrezzoPerVedere) {
			Stanza stanza = new StanzaBuia(nome, attrezzoPerVedere);
			this.UltimaStanzaAggiuntaEAggiorna(stanza);
			return this;
		}

		public LabirintoBuilder addStanzaBloccata(String nome, String attrezzoSbloccante, String direzioneBloccata) {
			Stanza stanza = new StanzaBloccata(nome, Direzione.valueOf(direzioneBloccata), attrezzoSbloccante);
			this.UltimaStanzaAggiuntaEAggiorna(stanza);
			return this;
		}
		
		public Labirinto getLabirinto1() {
			return this.labirinto;
		}

	}
}
