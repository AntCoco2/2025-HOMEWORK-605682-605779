package it.uniroma3.diadia;

import java.io.*;
import java.util.*;

import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.FormatoFileNonValidoException;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.ambienti.StanzaBloccata;
import it.uniroma3.diadia.ambienti.StanzaBuia;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.*;

public class CaricatoreLabirinto {

	/* prefisso di una singola riga di testo contenente tutti i nomi delle stanze */
	private static final String STANZE_MARKER = "Stanze:";

	/* prefisso di una singola riga contenente il nome della stanza iniziale */
	private static final String STANZA_INIZIALE_MARKER = "Inizio:";

	/* prefisso della riga contenente il nome stanza vincente */
	private static final String STANZA_VINCENTE_MARKER = "Vincente:";

	/*
	 * prefisso della riga contenente le specifiche degli attrezzi da collocare nel
	 * formato <nomeAttrezzo> <peso> <nomeStanza>
	 */
	private static final String ATTREZZI_MARKER = "Attrezzi:";

	/*
	 * prefisso della riga contenente le specifiche dei collegamenti tra stanza nel
	 * formato <nomeStanzaDa> <direzione> <nomeStanzaA>
	 */
	private static final String USCITE_MARKER = "Uscite:";

	private static final String MAGHI_MARKER = "Maghi:";

	private static final String CANI_MARKER = "Cani:";

	private static final String STREGHE_MARKER = "Streghe:";

	private static final String BUIA_MARKER = "Buia:";

	private static final String BLOCCATA_MARKER = "Bloccata:";

	/*
	 * Esempio di un possibile file di specifica di un labirinto (vedi
	 * POO-26-eccezioni-file.pdf)
	 * 
	 * Stanze: biblioteca, N10, N11 Inizio: N10 Vincente: N11 Attrezzi: martello 10
	 * biblioteca, pinza 2 N10 Uscite: biblioteca nord N10, biblioteca sud N11
	 * 
	 */
	private LineNumberReader reader;

	private Map<String, Stanza> nome2stanza;

	private Stanza stanzaIniziale;
	private Stanza stanzaVincente;

	public CaricatoreLabirinto(String nomeFile) throws FileNotFoundException {
		this.nome2stanza = new HashMap<String, Stanza>();
		this.reader = new LineNumberReader(new FileReader(nomeFile));
	}

	public void carica() throws FormatoFileNonValidoException {
		try {
			this.leggiECreaStanze();
			this.leggiEImpostaBloccata();
			this.leggiEImpostaBuia();
			this.leggiInizialeEvincente();
			this.leggiECollocaAttrezzi();
			this.leggiEImpostaUscite();
			this.leggiEImpostaMaghi();
			this.leggiEImpostaStreghe();
			this.leggiEImpostaCani();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}

	}

	private String leggiRigaCheCominciaPer(String marker) throws FormatoFileNonValidoException {
		try {
			String riga = this.reader.readLine();
			check(riga != null, "era attesa una riga non null");
			check(riga.startsWith(marker), "era attesa una riga che cominciasse per " + marker);
			return riga.substring(marker.length()).trim();
		} catch (IOException e) {
			throw new FormatoFileNonValidoException(e.getMessage());
		}
	}

	private void leggiECreaStanze() throws FormatoFileNonValidoException {
		String nomiStanze = this.leggiRigaCheCominciaPer(STANZE_MARKER);
		for (String nomeStanza : separaStringheAlleVirgole(nomiStanze)) {
			Stanza stanza = new Stanza(nomeStanza);
			this.nome2stanza.put(nomeStanza, stanza);
		}
	}

	private List<String> separaStringheAlleVirgole(String string) {
		List<String> result = new LinkedList<>();
		try (Scanner scannerDiParole = new Scanner(string)) {
			scannerDiParole.useDelimiter(",");
			while (scannerDiParole.hasNext()) {
				result.add(scannerDiParole.next().trim());
			}
		}
		return result;
	}

	private void leggiInizialeEvincente() throws FormatoFileNonValidoException {
		String nomeStanzaIniziale = null;
		nomeStanzaIniziale = this.leggiRigaCheCominciaPer(STANZA_INIZIALE_MARKER);
		check(this.isStanzaValida(nomeStanzaIniziale), nomeStanzaIniziale + " non definita");
		String nomeStanzaVincente = this.leggiRigaCheCominciaPer(STANZA_VINCENTE_MARKER);
		check(this.isStanzaValida(nomeStanzaVincente), nomeStanzaVincente + " non definita");
		this.stanzaIniziale = this.nome2stanza.get(nomeStanzaIniziale);
		this.stanzaVincente = this.nome2stanza.get(nomeStanzaVincente);
	}

	private void leggiECollocaAttrezzi() throws FormatoFileNonValidoException {
		String specificheAttrezzi = this.leggiRigaCheCominciaPer(ATTREZZI_MARKER);

		for (String specificaAttrezzo : separaStringheAlleVirgole(specificheAttrezzi)) {
			String nomeAttrezzo = null;
			String pesoAttrezzo = null;
			String nomeStanza = null;
			try (Scanner scannerLinea = new Scanner(specificaAttrezzo)) {
				check(scannerLinea.hasNext(), msgTerminazionePrecoce("il nome di un attrezzo."));
				nomeAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(), msgTerminazionePrecoce("il peso dell'attrezzo " + nomeAttrezzo + "."));
				pesoAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(), msgTerminazionePrecoce(
						"il nome della stanza in cui collocare l'attrezzo " + nomeAttrezzo + "."));
				nomeStanza = scannerLinea.next();
			}
			posaAttrezzo(nomeAttrezzo, pesoAttrezzo, nomeStanza);
		}
	}

	private void posaAttrezzo(String nomeAttrezzo, String pesoAttrezzo, String nomeStanza)
			throws FormatoFileNonValidoException {
		int peso;
		try {
			peso = Integer.parseInt(pesoAttrezzo);
			Attrezzo attrezzo = new Attrezzo(nomeAttrezzo, peso);
			check(isStanzaValida(nomeStanza),
					"Attrezzo " + nomeAttrezzo + " non collocabile: stanza " + nomeStanza + " inesistente");
			this.nome2stanza.get(nomeStanza).addAttrezzo(attrezzo);
		} catch (NumberFormatException e) {
			check(false, "Peso attrezzo " + nomeAttrezzo + " non valido");
		}
	}

	private boolean isStanzaValida(String nomeStanza) {
		return this.nome2stanza.containsKey(nomeStanza);
	}

	private void leggiEImpostaUscite() throws FormatoFileNonValidoException {
		String specificheUscite = this.leggiRigaCheCominciaPer(USCITE_MARKER);
		for(String specifiche : separaStringheAlleVirgole(specificheUscite)) {
			try (Scanner scannerDiLinea = new Scanner(specifiche)) 	{	
				while (scannerDiLinea.hasNext()) {
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("le uscite di una stanza."));
					String stanzaPartenza = scannerDiLinea.next();
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la direzione di una uscita della stanza "+stanzaPartenza));
					String dir = scannerDiLinea.next();
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la destinazione di una uscita della stanza "+stanzaPartenza+" nella direzione "+dir));
					String stanzaDestinazione = scannerDiLinea.next();

					impostaUscita(stanzaPartenza, dir, stanzaDestinazione);
				}
			}
		} 
	}

	private String msgTerminazionePrecoce(String msg) {
		return "Terminazione precoce del file prima di leggere " + msg;
	}

	private void impostaUscita(String stanzaDa, String dir, String nomeA) throws FormatoFileNonValidoException {
		check(isStanzaValida(stanzaDa), "Stanza di partenza sconosciuta " + stanzaDa);
		check(isStanzaValida(nomeA), "Stanza di destinazione sconosciuta " + nomeA);
		Stanza partenzaDa = this.nome2stanza.get(stanzaDa);
		Stanza arrivoA = this.nome2stanza.get(nomeA);
		partenzaDa.impostaStanzaAdiacente(Direzione.valueOf(dir.toUpperCase()), arrivoA);
	}

	private void impostaMago(String stanza, String nome, String presentazione, String attrezzo)
			throws FormatoFileNonValidoException {
		check(isStanzaValida(stanza), "Stanza di partenza " + stanza);
		Stanza stanzaS = this.nome2stanza.get(stanza);
		Mago mago = new Mago(nome, presentazione, new Attrezzo(attrezzo, 4));
		stanzaS.setPersonaggio(mago);
	}

	final private void check(boolean condizioneCheDeveEsseraVera, String messaggioErrore)
			throws FormatoFileNonValidoException {
		if (!condizioneCheDeveEsseraVera)
			throw new FormatoFileNonValidoException(
					"Formato file non valido [" + this.reader.getLineNumber() + "] " + messaggioErrore);
	}

	private void leggiEImpostaMaghi() throws FormatoFileNonValidoException {
		String specificaMaghi = this.leggiRigaCheCominciaPer(MAGHI_MARKER);
		try (Scanner scannerDiLinea = new Scanner(specificaMaghi)) {
			while (scannerDiLinea.hasNext()) {

				check(scannerDiLinea.hasNext(), msgTerminazionePrecoce("nome della stanza per il mago atteso"));
				String nomeStanza = scannerDiLinea.next();

				check(scannerDiLinea.hasNext(),
						msgTerminazionePrecoce("nome del mago atteso per la stanza " + nomeStanza));
				String nomeMago = scannerDiLinea.next();

				check(scannerDiLinea.hasNext(),
						msgTerminazionePrecoce("presentazione del mago attesa per " + nomeMago));
				String presentazioneDelMago = scannerDiLinea.findInLine("\"([^\"]+)\"");;

				check(scannerDiLinea.hasNext(), msgTerminazionePrecoce("attrezzo del mago atteso per " + nomeMago));
				String attrezzoDelMago = scannerDiLinea.next();

				impostaMago(nomeStanza, nomeMago, presentazioneDelMago, attrezzoDelMago);
			}
		}
	}

	private void leggiEImpostaStreghe() throws FormatoFileNonValidoException {
		String specificaStreghe = this.leggiRigaCheCominciaPer(STREGHE_MARKER);
		try (Scanner scannerDiLinea = new Scanner(specificaStreghe)) {
			while (scannerDiLinea.hasNext()) {

				check(scannerDiLinea.hasNext(), msgTerminazionePrecoce("nome della stanza per la strega attesa"));
				String nomeStanza = scannerDiLinea.next();

				check(scannerDiLinea.hasNext(),
						msgTerminazionePrecoce("nome della strega atteso per la stanza " + nomeStanza));
				String nomeStrega = scannerDiLinea.next();

				check(scannerDiLinea.hasNext(),
						msgTerminazionePrecoce("presentazione della strega attesa per " + nomeStrega));
				String presentazioneDellaStrega = scannerDiLinea.findInLine("\"([^\"]+)\"");;

				impostaStrega(nomeStanza, nomeStrega, presentazioneDellaStrega);
			}
		}
	}

	private void impostaStrega(String stanza, String nome, String presentazione) throws FormatoFileNonValidoException {
		check(isStanzaValida(stanza), "Stanza di partenza " + stanza);
		Stanza stanzaS = this.nome2stanza.get(stanza);
		Strega strega = new Strega(nome, presentazione);
		Map <Direzione, Stanza> stanzaAdiacenti = stanzaS.getStanzeAdiacentiMappa();
		strega.setStanzeAdiacenti(stanzaAdiacenti);
		stanzaS.setPersonaggio(strega);
	}

	private void leggiEImpostaCani() throws FormatoFileNonValidoException {
		String specificaCani = this.leggiRigaCheCominciaPer(CANI_MARKER);
		try (Scanner scannerDiLinea = new Scanner(specificaCani)) {
			while (scannerDiLinea.hasNext()) {
				check(scannerDiLinea.hasNext(), msgTerminazionePrecoce("nome della stanza per il cane atteso"));
				String nomeStanza = scannerDiLinea.next();

				check(scannerDiLinea.hasNext(),
						msgTerminazionePrecoce("nome del cane atteso nella stanza " + nomeStanza));
				String nomeCane = scannerDiLinea.next();

				check(scannerDiLinea.hasNext(),
						msgTerminazionePrecoce("presentazione del cane attesa per " + nomeCane));
				String presentazioneDelCane = scannerDiLinea.findInLine("\"([^\"]+)\"");;

				impostaCane(nomeStanza, nomeCane, presentazioneDelCane);
			}
		}
	}

	private void impostaCane(String stanza, String nome, String presentazione) throws FormatoFileNonValidoException {
		check(isStanzaValida(stanza), "Stanza  " + stanza);
		Stanza stanzaS = this.nome2stanza.get(stanza);
		Cane cane = new Cane(nome, presentazione);
		stanzaS.setPersonaggio(cane);
	}

	private void leggiEImpostaBuia() throws FormatoFileNonValidoException {
		String specificaBuia = this.leggiRigaCheCominciaPer(BUIA_MARKER);
		try (Scanner scannerDiLinea = new Scanner(specificaBuia)) {
			check(scannerDiLinea.hasNext(), msgTerminazionePrecoce("nome della stanza per la stanza buia atteso"));
			String nomeStanza = scannerDiLinea.next();

			check(scannerDiLinea.hasNext(),
					msgTerminazionePrecoce("attrezzo necessario per rendere visibile la stanza buia " + nomeStanza));
			String nomeAttrezzo = scannerDiLinea.next();

			StanzaBuia stanza = new StanzaBuia(nomeStanza, nomeAttrezzo);
			this.nome2stanza.put(nomeStanza, stanza);
		}
	}

	private void leggiEImpostaBloccata() throws FormatoFileNonValidoException {

		String specificaBloccata = this.leggiRigaCheCominciaPer(BLOCCATA_MARKER);
		try (Scanner scannerDiLinea = new Scanner(specificaBloccata)) {

			check(scannerDiLinea.hasNext(), msgTerminazionePrecoce("Il nome della stanza bloccata atteso"));
			String nomeStanza = scannerDiLinea.next();

			check(scannerDiLinea.hasNext(),
					msgTerminazionePrecoce("L'attrezzo necessario per sbloccare la stanza atteso per " + nomeStanza));
			String nomeAttrezzo = scannerDiLinea.next();

			check(scannerDiLinea.hasNext(), msgTerminazionePrecoce("La direzione bloccata attesa per " + nomeStanza));
			String direzione = scannerDiLinea.next();

			StanzaBloccata stanza = new StanzaBloccata(nomeStanza, Direzione.valueOf(direzione.toUpperCase()),
					nomeAttrezzo);
			this.nome2stanza.put(nomeStanza, stanza);
		}
	}

	public Stanza getStanzaIniziale() {
		return this.stanzaIniziale;
	}

	public Stanza getStanzaVincente() {
		return this.stanzaVincente;
	}
}