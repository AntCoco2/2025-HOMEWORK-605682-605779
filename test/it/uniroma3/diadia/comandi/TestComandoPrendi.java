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
import it.uniroma3.diadia.attrezzi.Attrezzo;


public class TestComandoPrendi {
	
	private Partita partita;
	private Stanza stanza;
	private Attrezzo attrezzo;
	private IOConsole io;
	private ComandoPrendi comando;
	private Labirinto labirinto;
	
	
	
	
	@BeforeEach
	void setUp() throws FileNotFoundException, FormatoFileNonValidoException {
		LabirintoBuilder builder = Labirinto.newBuilder("Labirinto3.txt");
		Labirinto labirinto = builder.getLabirinto();
		partita = new Partita(labirinto);
		stanza = new Stanza("atrio");
		attrezzo= new Attrezzo("osso",2);
		io = new IOConsole();
		comando = new ComandoPrendi();;
		partita.getStanzaCorrente().addAttrezzo(attrezzo);
		comando.setIO(io);
		comando.setParametro("osso");
		
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
		assertEquals("prendi" , comando.getNome());
	}
	
	
	@Test
	void TestEseguiComando() {
		
		comando.esegui(partita);
		assertEquals(attrezzo,partita.getGiocatore().getBorsa().getAttrezzo("osso"));
	}

}
