package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public abstract class AbstractComando implements Comando {
	protected IO io;
	protected String parametro;
	private static String NOME ="AbstractComando";
	
public abstract void esegui(Partita partita);
	
	@Override
	public void setIO(IO io) {
		this.io = io;
	}
	
	@Override
	public String getNome() {
		return NOME;
	}
	
	@Override
	public String getParametro() {
		return this.parametro;
	}
	
	@Override
	public void setParametro(String parametro) {
		this.parametro = parametro;
	}
	

}
