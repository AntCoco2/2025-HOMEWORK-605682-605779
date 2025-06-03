package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Labirinto.LabirintoBuilder;

class TestIOSimulator {

	@Test
	void testVittoria() throws Exception {
		List<String> comandi = Arrays.asList("prendi osso", "posa osso", "vai nord");
		IOSimulator io = new IOSimulator(comandi);
		LabirintoBuilder builder = Labirinto.newBuilder("Labirinto5.txt");
		Labirinto labirinto = builder.getLabirinto();
		DiaDia dia = new DiaDia(labirinto, io);

		dia.gioca();
		List<String> output = io.getMessaggiProdotti();
		boolean trovato = false;
		for (String msg : output) {
			if ("Hai vinto!".equals(msg)) {
				trovato = true;
				break;
			}
		}
		assertTrue(trovato, "Hai vinto!");
	}

@Test
void testPrendiOsso() throws Exception {
	List<String> comandi = Arrays.asList( "prendi osso", "posa osso", "vai nord" );
	IOSimulator io = new IOSimulator(comandi);
	LabirintoBuilder builder = Labirinto.newBuilder("Labirinto5.txt");
	Labirinto labirinto = builder.getLabirinto();
	DiaDia dia = new DiaDia(labirinto, io);

	dia.gioca();
	List<String> output = io.getMessaggiProdotti();
	boolean trovato = false;
	for (String msg : output) {
		if ("Oggetto aggiunto alla borsa!".equals(msg)) {
			trovato = true;
			break;
		}
	}
	assertTrue(trovato, "Oggetto aggiunto alla borsa!");
	
}
}
