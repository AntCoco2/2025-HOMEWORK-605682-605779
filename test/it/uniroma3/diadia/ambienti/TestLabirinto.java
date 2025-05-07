package it.uniroma3.diadia.ambienti;
import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class TestLabirinto {
	
	private Labirinto labirinto;
	private Stanza stanza;
	private Attrezzo lanterna;
	
	@BeforeEach
	void setup() {
	    labirinto = new Labirinto();
	    stanza = new Stanza("aulaN10");
	    lanterna =new Attrezzo("lanterna",3);
		labirinto.init();
	}

	@Test
	void TestStanzaInizio() {
		assertEquals("Atrio", labirinto.stanzaCorrente.getNome());
	}
	
	@Test
	void TestStanzaVincente() {
		assertEquals("Biblioteca", labirinto.stanzaVincente.getNome());
	}
	
	@Test
	void TestAttrezzoAulaN10() {
		stanza.addAttrezzo(lanterna);
		assertTrue(stanza.hasAttrezzo("lanterna"));
	}
	
	@Test
	void TestStanzaAdiacenteAtrio() {
		assertNotNull(labirinto.stanzaCorrente.getStanzaAdiacente("nord"));
	}
}
