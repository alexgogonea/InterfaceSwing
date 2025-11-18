//creazione classe derivata da Esperimento

public class EsperimentoCollisione extends Esperimento {
    public int numeroCollisioni;
    //costruttore prendendo attributi nome ed energia dalla classe padre
    public EsperimentoCollisione(String nome, double energia, int numeroCollisioni) throws DatiEsperimentoNonValidiException {
        super( nome,  energia);
        this.numeroCollisioni = numeroCollisioni;

    }

    //implementazione metodo descrizione
    public String descrizione(){
        StringBuilder stringa = new StringBuilder();
        stringa.append("Nome: ");
        stringa.append(nome);
        stringa.append(", Energia: ");
        stringa.append(energia);
        stringa.append("TeV");
        stringa.append(", Collisioni: ");
        stringa.append(numeroCollisioni);
        return stringa.toString();
    }
}
