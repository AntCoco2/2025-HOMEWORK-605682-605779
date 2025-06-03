package it.uniroma3.diadia.ambienti;
import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Labirinto.LabirintoBuilder;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class TestLabirinto {
	
	private Labirinto labirinto;
	private Stanza stanza;
	private Attrezzo lanterna;
	
	@BeforeEach
	void setup() throws FileNotFoundException, FormatoFileNonValidoException {
		LabirintoBuilder builder = Labirinto.newBuilder("Labirinto2.txt");
		Labirinto labirinto = builder.getLabirinto();
	    stanza = new Stanza("aulaN10");
	    lanterna =new Attrezzo("lanterna",3);
	}

	@Test
	void TestStanzaInizio() {
		assertEquals("Atrio", labirinto.getStanzaCorrente().getNome());
	}
	
	@Test
	void TestStanzaVincente() {
		assertEquals("Biblioteca", labirinto.getStanzaVincente().getNome());
	}
	
	@Test
	void TestAttrezzoAulaN10() {
		stanza.addAttrezzo(lanterna);
		assertTrue(stanza.hasAttrezzo("lanterna"));
	}
	
	@Test
	void TestStanzaAdiacenteAtrio() {
		assertNotNull(labirinto.getStanzaCorrente().getStanzaAdiacente(Direzione.NORD));
	}
}
