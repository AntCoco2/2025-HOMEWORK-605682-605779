package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.FormatoFileNonValidoException;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.ambienti.Labirinto.LabirintoBuilder;

class TestComandoVai {
	private Partita partita;
	private String direzione;
	private Comando comando;
	private IOConsole io;
	private Labirinto labirinto;

	@BeforeEach
	public void setUp() throws FileNotFoundException, FormatoFileNonValidoException {
		io = new IOConsole();
		direzione = "est";
		LabirintoBuilder builder = Labirinto.newBuilder("Labirinto4.txt");
		Labirinto labirinto = builder.getLabirinto();
		partita = new Partita(labirinto);
		comando = new ComandoVai(Direzione.valueOf(direzione.toUpperCase()));
		comando.setIO(io);

	}

	@Test
	void TestVaiStanzaStanzaAdiecente() {
		comando.setParametro("sud");
		comando.esegui(partita);
		assertEquals("Atrio", partita.getStanzaCorrente().getNome());
	}

	@Test
	void TestCfu() {
		comando.setParametro("sud");
		comando.esegui(partita);
		assertEquals(19, partita.getGiocatore().getCfu());

	}

	@Test
	void TestgetNome() {
		assertEquals("vai", comando.getNome());
	}

	@Test
	void TestgetParametro() {
		comando.setParametro("nord");
		assertEquals("nord", comando.getParametro());
	}

}
