package it.uniroma3.diadia;
 
public class IOSimulator implements IO {
 
    private static final int DEF_MAX_MESSAGES_AMOUNT = 20;
 
    private final String[] istruzioni;
    private final String[] messaggi;
    private int msgAmount = 0;
    private int curr = -1;
 
    public IOSimulator(String... istruzioni) {
        this(DEF_MAX_MESSAGES_AMOUNT, istruzioni);
    }
 
    public IOSimulator(int maxMsgs, String... istruzioni) {
        this.messaggi = new String[maxMsgs];
        this.istruzioni = istruzioni;
    }
 
    @Override
    public void mostraMessaggio(String messaggio) {
        if (msgAmount < messaggi.length) messaggi[msgAmount++] = messaggio;
    }
 
    @Override
    public String leggiRiga() {
        return ++curr < istruzioni.length ? istruzioni[curr] : null;
    }
 
    public String[] getMessaggi() {
        return messaggi;
    }
}