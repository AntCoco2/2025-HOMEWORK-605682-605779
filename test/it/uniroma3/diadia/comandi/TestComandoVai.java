package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;


class TestComandoVai {
	private Partita partita;
	private String direzione;
	private Stanza atrio;
	private Stanza aulaN11;
	private Comando comando;
	private IOConsole io;
	
	
	
	@BeforeEach
	public void setUp () {
		io = new IOConsole();
		direzione = "est";
		partita = new Partita();
		atrio = new Stanza("atrio");
		aulaN11 = new Stanza("aulaN11");
		comando = new ComandoVai();
		atrio = partita.getStanzaCorrente();
		atrio.impostaStanzaAdiacente(direzione, aulaN11);
		comando.setParametro(direzione);
		comando.setIO(io);
		

	}
	
	@Test
	void TestVaiStanzaAdiacente() {
		comando.esegui(partita);
		assertEquals("aulaN11",partita.getStanzaCorrente().getNome());
	}
	
	@Test
	void TestCfu() {
		comando.esegui(partita);
		assertEquals(19,partita.getGiocatore().getCfu());

	}
	
	@Test
	void TestgetNome() {
		assertEquals("vai" , comando.getNome());
	}
	
	@Test
	void TestgetParametro() {
		assertEquals(direzione, comando.getParametro());
	}
	

}
