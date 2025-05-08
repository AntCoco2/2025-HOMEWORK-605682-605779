package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class TestStanzaBloccata {
	StanzaBloccata StanzaBl;
	Attrezzo pass;
	Stanza atrio;
	
	@BeforeEach
	void setUp() {
		StanzaBl = new StanzaBloccata("StanzaBl", "nord", "pass");
		pass = new Attrezzo("pass", 1);
		atrio = new Stanza("atrio");
		StanzaBl.impostaStanzaAdiacente("nord", atrio);
	}
	
	@Test
	void testStanzaBloccata() {
		 Stanza prova = StanzaBl.getStanzaAdiacente("nord");
		assertEquals(prova, StanzaBl);
		
	}
	@Test
	void testStanzaSbloccata(){
		StanzaBl.addAttrezzo(pass);
		Stanza prova = StanzaBl.getStanzaAdiacente("nord");
		assertEquals(prova, atrio);
		
	}

}
