package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class TestStanzaMagica {
	
	private StanzaMagica stanza;
	private Attrezzo attrezzo;
	
	@BeforeEach
	void setUp() {
		stanza = new StanzaMagica("atrio");
		attrezzo = new Attrezzo("penna", 2);
		
	}
	
	
	
	@Test
	void testNotNull() {
		assertNotNull(stanza);
	}
	
	@Test
	void testAggiornaContatore() {
		assertNotNull(stanza);
		assertEquals(0, stanza.getNumeroAttrezzi());
		assertTrue(stanza.addAttrezzo(attrezzo));
		assertTrue(stanza.hasAttrezzo("penna"));
		assertEquals(1, stanza.getNumeroAttrezzi());
	}
	
	@Test
	void testModificaAttrezzo() {
		assertNotNull(stanza);
		assertEquals(0, stanza.getContatore());
		stanza.setContatorePosatiFull();
		assertEquals(3, stanza.getContatore());
		assertTrue(stanza.addAttrezzo(attrezzo));
		assertEquals("annep", stanza.getAttrezzo("annep").getNome());
		assertEquals(4, stanza.getAttrezzo("annep").getPeso());
		
	}
}
