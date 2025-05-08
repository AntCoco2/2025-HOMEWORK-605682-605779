package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;


class TestComandoPosa {
	private IOConsole io;
	private Attrezzo attrezzo;
	private Partita partita;
	private Stanza stanza;
	private ComandoPosa comando;
	@BeforeEach
	void setUp() {
		io = new IOConsole();
		attrezzo = new Attrezzo("osso",2);
		partita = new Partita();
		stanza = new Stanza("aulaN11");
		comando = new ComandoPosa();
		partita.getGiocatore().getBorsa().addAttrezzo(attrezzo);
		partita.setStanzaCorrente(stanza);
		comando.setParametro("osso");
		comando.setIO(io);
	}
	
	@Test
	void TestGetParametro() {
		assertEquals("osso",comando.getParametro());
	}
	
	
	
	@Test
	void TestSetParametro() {
		comando.setParametro("lanterna");
		assertEquals("lanterna", comando.getParametro());
	}
	
	@Test
	void TestGetNome() {
		assertEquals("posa" , comando.getNome());
	}
	
	@Test
	void TestAttrezzoPosato() {
		comando.esegui(partita);
		assertEquals(attrezzo,partita.getStanzaCorrente().getAttrezzo("osso"));
	}
	
	
	
	

}
