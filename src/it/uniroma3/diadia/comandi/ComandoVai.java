package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Stanza;

public class ComandoVai extends AbstractComando {
    private static final String NOME = "vai";
    private Direzione direzione;
    
    /**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra e ne stampa il
	 * nome, altrimenti stampa un messaggio di errore
	 */
    
    public ComandoVai(Direzione direzione) {
    	this.direzione = direzione;
    }

    @Override
    public void esegui(Partita partita) {
        Stanza stanzaCorrente = partita.getStanzaCorrente();
        Stanza prossimaStanza = null;

        if (this.direzione == null) {
        	 io.mostraMessaggio("Dove vuoi andare? Devi specificare una direzione");
            return;
        }

        prossimaStanza = stanzaCorrente.getStanzaAdiacente(this.direzione);

        if (prossimaStanza == null) {
        	 io.mostraMessaggio("Direzione inesistente");
            return;
        }

        partita.setStanzaCorrente(prossimaStanza);
        io.mostraMessaggio(partita.getStanzaCorrente().getNome());
        partita.getGiocatore().setCfu(partita.getGiocatore().getCfu() - 1);
    }

    

	@Override
	public String getNome() {
		// TODO Auto-generated method stub
		return NOME;
	}
	@Override
	public void setIO(IO io) {
		this.io = io;
	}

	
}

