package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Giocatore;

class TestStanzaBuia {
	Partita partita;
	Labirinto labirinto;
	Stanza stanza;
	StanzaBuia stanzab;
	Attrezzo lanterna;
	@BeforeEach
	void setUp() {
		partita = new Partita();
		lanterna = new Attrezzo("lanterna", 7);
		stanzab = new StanzaBuia("stanzab", "lanterna" );
		
		
	}
	
	@Test
	void getDescrizione() {
		String descrizione = stanzab.getDescrizione();
		assertEquals("qui c'è buio pesto", descrizione);
		
	}
	@Test
	void getDescrizioneWithAttrezzo(){
		stanzab.addAttrezzo(lanterna);
		String descrizione = stanzab.getDescrizione();
		String attesa = "stanzab\nUscite: \nAttrezzi nella stanza: lanterna (7kg) ";
		assertEquals(attesa, descrizione);
		
	}
	

}
