package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestIOSimulator {

	@Test
	void testVittoria() {
		String[] comandi = { "prendi osso", "posa osso", "vai nord" };
		IOSimulator io = new IOSimulator(comandi);
		DiaDia dia = new DiaDia(io);

		dia.gioca();
		String[] output = io.getMessaggi();
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
void testPrendiOsso() {
	String[] comandi = { "prendi osso", "posa osso", "vai nord" };
	IOSimulator io = new IOSimulator(comandi);
	DiaDia dia = new DiaDia(io);

	dia.gioca();
	String[] output = io.getMessaggi();
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
