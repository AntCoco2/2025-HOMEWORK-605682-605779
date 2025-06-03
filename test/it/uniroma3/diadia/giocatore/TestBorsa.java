package it.uniroma3.diadia.giocatore;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.SortedSet;
import java.util.stream.Collectors;

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
		spada = new Attrezzo("spada", 4);
		arco = new Attrezzo("arco", 1);
		mattone = new Attrezzo("mattone", 5);
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
		assertFalse(borsa.addAttrezzo(new Attrezzo("Bicicletta", 20)));
	}

	@Test
	void TestRimuoviOggetto() {
		borsa.addAttrezzo(arco);
		assertNotNull(borsa.removeAttrezzo("arco"));
		assertTrue(borsa.isEmpty());
	}
	
	@Test
	void TestOrdinaOggetto() {
		assertTrue(this.borsa.isEmpty());
		assertTrue(this.borsa.addAttrezzo(spada));
		assertTrue(this.borsa.hasAttrezzo("spada"));
		assertTrue(this.borsa.addAttrezzo(mattone));
		assertTrue(this.borsa.hasAttrezzo("mattone"));
		assertTrue(this.borsa.addAttrezzo(arco));
		assertTrue(this.borsa.hasAttrezzo("arco"));
		List <Attrezzo> ver =this.borsa.getContenutoOrdinatoPerPeso();
		assertEquals("arco", ver.get(0).getNome());
		assertEquals("spada", ver.get(1).getNome());
		assertEquals("mattone", ver.get(2).getNome());
		
	}
	@Test
	void TestOrdinaPerNome() {
		assertTrue(this.borsa.isEmpty());
		assertTrue(this.borsa.addAttrezzo(spada));
		assertTrue(this.borsa.hasAttrezzo("spada"));
		assertTrue(this.borsa.addAttrezzo(mattone));
		assertTrue(this.borsa.hasAttrezzo("mattone"));
		assertTrue(this.borsa.addAttrezzo(arco));
		assertTrue(this.borsa.hasAttrezzo("arco"));
		SortedSet <Attrezzo> ver = this.borsa.getContenutoOrdinatoPerNome();
		List<String> attesi = Arrays.asList("arco", "mattone", "spada"); // Ordine alfabetico
		List<String> ottenuti = ver.stream().map(Attrezzo::getNome).collect(Collectors.toList());
		assertEquals(attesi, ottenuti);
		
		
	}
	@Test 
	void testSetOrdinatoPerPeso() {
		assertTrue(this.borsa.isEmpty());
		assertTrue(this.borsa.addAttrezzo(spada));
		assertTrue(this.borsa.hasAttrezzo("spada"));
		assertTrue(this.borsa.addAttrezzo(mattone));
		assertTrue(this.borsa.hasAttrezzo("mattone"));
		assertTrue(this.borsa.addAttrezzo(arco));
		assertTrue(this.borsa.hasAttrezzo("arco"));
		SortedSet <Attrezzo> ver = this.borsa.getSortedSetOrdinatoPerPeso();
		List<String> attesi = Arrays.asList("arco", "spada", "mattone"); // Ordine peso
		List<String> ottenuti = ver.stream().map(Attrezzo::getNome).collect(Collectors.toList());
		assertEquals(attesi, ottenuti);
		
		
	}
}

