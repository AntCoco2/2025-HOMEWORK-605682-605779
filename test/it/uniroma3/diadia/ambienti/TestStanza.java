package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
class TestStanza {
		public Stanza stanza(String nome) {
			return new Stanza(nome);
		}
		public Attrezzo attrezzo(String nome, int n){
			return new Attrezzo(nome, n);
		}
		Stanza s1 = new Stanza("atrio");
		Stanza s2 = new Stanza("biblioteca");
		Stanza s3 = new Stanza("bagno");
	@Test
	void stanzaAdiacenteNulla() {
		assertNull(stanza("n12").getStanzaAdiacente(null));
		
	}
	@Test
	void stanzaAdiecente() {
		s1.impostaStanzaAdiacente(Direzione.NORD, s2);
		assertEquals(s2, s1.getStanzaAdiacente(Direzione.NORD));
		
	}
	@Test
	void stanzaAdiacenteSame() {
		s1.impostaStanzaAdiacente(Direzione.EST, s3);
		s2.impostaStanzaAdiacente(Direzione.EST, s3);
		assertSame(s2.getStanzaAdiacente(Direzione.EST), s1.getStanzaAdiacente(Direzione.EST));
	}
	@Test
	void aggiungiAttrezzo() {
		assertTrue(s1.addAttrezzo(attrezzo("pala", 7)));		
	}
	@Test 
	void testHasAttrezzo(){
		s1.addAttrezzo(attrezzo("palla", 3));
		assertTrue(s1.hasAttrezzo("palla"));
	}
	@Test 
	void testGetAttrezzo() {
		Attrezzo palla = new Attrezzo("palla",3);
		s1.addAttrezzo(palla);
		assertEquals(palla, s1.getAttrezzo("palla"));
		
		
		
	}
	@Test
	void testGHasAttrezzoFalse(){
		assertFalse(s1.hasAttrezzo("quaderno"));
	
	
}
	@Test
	void testNonAggiungeDuplicati() {
		assertTrue(s1.attrezzi.isEmpty());
		assertTrue(s1.addAttrezzo(attrezzo("palla", 3)));
		assertTrue(s1.addAttrezzo(attrezzo("palla", 3)));
		assertEquals(1, s1.attrezzi.size());
		
	}
	@Test
	void stanzaEmpty() {
		assertTrue(s1.isEmpty());
	}
}
