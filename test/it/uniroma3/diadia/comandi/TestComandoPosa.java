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
import it.uniroma3.diadia.giocatore.Borsa;
import it.uniroma3.diadia.giocatore.Giocatore;


class TestComandoPosa {
	private IOConsole io;
	private Attrezzo attrezzo;
	private Partita partita;
	private Stanza stanza;
	private ComandoPosa comando;
	private Labirinto labirinto;
	private Giocatore giocatore;
	private Borsa borsa;
	@BeforeEach
	void setUp() throws FileNotFoundException, FormatoFileNonValidoException {
		io = new IOConsole();
		attrezzo = new Attrezzo("osso",2);
		LabirintoBuilder builder = Labirinto.newBuilder("Labirinto3.txt");
		Labirinto labirinto = builder.getLabirinto();
		partita = new Partita(labirinto);
		comando = new ComandoPosa();
		borsa = partita.getGiocatore().getBorsa();
		comando.setIO(io);
	}
	
	@Test
	void TestGetParametro() {
		comando.setParametro("osso");
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
	    borsa.addAttrezzo(attrezzo);
	    comando.setParametro("osso");
	    comando.esegui(partita);
	    
	    // Controlla che l'attrezzo sia presente nella stanza
	    Attrezzo attrezzoPosato = partita.getStanzaCorrente().getAttrezzo("osso");
	    
	    assertNotNull(attrezzoPosato); // Verifica che l'attrezzo esista
	    assertEquals(attrezzo.getNome(), attrezzoPosato.getNome()); // Controlla il nome dell'attrezzo
	    assertEquals(attrezzo.getPeso(), attrezzoPosato.getPeso()); // Controlla il peso dell'attrezzo
	}

	
	
	
	

}
