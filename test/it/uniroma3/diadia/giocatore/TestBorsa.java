package it.uniroma3.diadia.giocatore;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class TestBorsa {
	
	private Borsa borsa;
	private Attrezzo spada;
	private Attrezzo arco;
	private Attrezzo mattone;

	@BeforeEach
	void setup() {
		borsa = new Borsa();
		spada = new Attrezzo("spada", 5);
		arco = new Attrezzo("arco", 3);
		mattone = new Attrezzo("mattone", 12);
	}
	
	@Test
	void TestAggiungiOggetto() {
		borsa.addAttrezzo(spada);
		assertEquals(spada,borsa.getAttrezzo("spada"));
	}
	
	@Test
	void TestOttenimentoCorretto() {
		assertTrue(borsa.addAttrezzo(spada));
	}
	
	@Test
	void TestOggettoNonTrovato() {
		borsa.addAttrezzo(spada);
		assertFalse(borsa.hasAttrezzo("arco"));
	}
	
	@Test
	void TestOggettoTrovato() {
		borsa.addAttrezzo(arco);
		assertTrue(borsa.hasAttrezzo("arco"));
	}
	
	@Test
	void TestAttrezzoRimosso() {
		borsa.addAttrezzo(arco);
		assertNotNull(borsa.removeAttrezzo("arco"));
	}
	
	@Test
	void TestAttrezzoNonRimosso() {
		borsa.addAttrezzo(spada);
		assertNull(borsa.removeAttrezzo("arco"));
	}
	
	
	@Test
	void TestToStringCorretto() {
		borsa.addAttrezzo(arco);
		assertEquals("Contenuto borsa (3kg/10kg): arco (3kg) ", borsa.toString());
	}
	
	@Test
	void TestToStringNull() {
		assertNotNull(borsa.toString());
	}
	
	@Test
	void TestToStringBorsaVuota() {
		assertEquals("Borsa vuota", borsa.toString());
	}
	
	@Test
	void TestBorsaVuota() {
		assertTrue(borsa.isEmpty());
	}
	@Test
	void TestBorsaNonVuota() {
		borsa.addAttrezzo(arco);
		assertFalse(borsa.isEmpty());
	}
	@Test
	void TestOggettoTroppoPesante() {
		assertFalse(borsa.addAttrezzo(mattone));
	}
	@Test
	void TestRimuoviOggetto() {
		borsa.addAttrezzo(arco);
		assertNotNull(borsa.removeAttrezzo("arco"));
		assertTrue(borsa.isEmpty());
	}
}

