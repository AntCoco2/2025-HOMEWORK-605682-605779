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
		StanzaBl = new StanzaBloccata("StanzaBl", Direzione.NORD, "pass");
		pass = new Attrezzo("pass", 1);
		atrio = new Stanza("atrio");
		StanzaBl.impostaStanzaAdiacente(Direzione.NORD, atrio);
	}
	
	@Test
	void testStanzaBloccata() {
		 Stanza prova = StanzaBl.getStanzaAdiacente(Direzione.NORD);
		assertEquals(prova, StanzaBl);
		
	}
	@Test
	void testStanzaSbloccata(){
		StanzaBl.addAttrezzo(pass);
		Stanza prova = StanzaBl.getStanzaAdiacente(Direzione.NORD);
		assertEquals(prova, atrio);
		
	}

}
