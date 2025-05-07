package it.uniroma3.diadia.giocatore;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.giocatore.Giocatore;

class TestGiocatore {
	Giocatore giocatore;
	
	@BeforeEach
	void setUp() {
		this.giocatore = new Giocatore(20);
	}
	@Test
	void getCfu() {
		assertEquals(20, giocatore.getCfu());
	}
	@Test 
	void setCfu() {
	     giocatore.setCfu(35);
	     assertEquals(35, giocatore.getCfu());
	}
	
	

}
