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
		s1.impostaStanzaAdiacente("nord", s2);
		assertEquals(s2, s1.getStanzaAdiacente("nord"));
		
	}
	@Test
	void stanzaAdiacenteSame() {
		s1.impostaStanzaAdiacente("est", s3);
		s2.impostaStanzaAdiacente("est", s3);
		assertSame(s2.getStanzaAdiacente("est"), s1.getStanzaAdiacente("est"));
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
		assertFalse(s1.hasAttrezzo("quaderno");
}
