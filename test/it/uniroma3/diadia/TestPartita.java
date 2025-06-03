package it.uniroma3.diadia;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.FormatoFileNonValidoException;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Labirinto.LabirintoBuilder;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Giocatore;

class TestPartita {
	Partita test;
	Stanza s1;	
	String nome;
	Stanza biblioteca;
	Stanza atrio;
	Giocatore giocatore;
	Labirinto labirinto;
	@BeforeEach
	void setUp() throws FileNotFoundException, FormatoFileNonValidoException {
		LabirintoBuilder builder = Labirinto.newBuilder("Labirinto2.txt");
		Labirinto labirinto = builder.getLabirinto();
		test = new Partita(labirinto);		
		biblioteca = test.getStanzaVincente();
		atrio = test.getStanzaCorrente();
		giocatore = new Giocatore(Configuratore.getCFU());
		
	}
	
	@Test
	void setStanzaCorrente() {
		test.setStanzaCorrente(s1 = new Stanza("s1"));
		assertEquals(s1, test.getStanzaCorrente());
		
		
	}
	@Test
	void getStanzaVincente() {
		assertEquals(biblioteca, test.getStanzaVincente());
	}
	@Test
	void getStanzaCorrente() {
		assertEquals(atrio, test.getStanzaCorrente());
	}
	@Test
	void notFinita() {
		assertTrue(false == test.isFinita());
	}
	@Test
	void isFinita() {
		test.setFinita();
		assertTrue(true == test.isFinita());
	}
	@Test
	void isnotVinta() {
		assertTrue(false == test.vinta());
	}
	@Test
	void isVinta() {
		test.setStanzaCorrente(biblioteca);
		assertTrue(true == test.vinta());
	}
	@Test
	void getCfu() {
		assertEquals(20, test.getCfu());
	}
	@Test
	void setCfu() {
		test.setCfu(30);
		assertEquals(30, test.getCfu());
	}
	

}