//classe con la quale se non metto dei dati richiesti mi lancia un'eccezione
public class DatiEsperimentoNonValidiException extends Exception {
    public DatiEsperimentoNonValidiException(String messaggio) {
        super(messaggio);
    }
}