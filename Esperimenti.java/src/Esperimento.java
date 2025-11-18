import java.io.Serializable;
//classe padre Esperimento
public abstract class Esperimento implements Serializable {
    protected String nome;
    protected double energia;

    //costruttore che lancia DatiEsperimentoNonValidiException se il nome è vuoto o se l'energia è minore di 0
    public Esperimento(String nome, double energia ) throws DatiEsperimentoNonValidiException {
        this.nome = nome;
        this.energia = energia;
        if(nome == ""){
            throw new DatiEsperimentoNonValidiException();
        }
        if(energia <= 0){
            throw new DatiEsperimentoNonValidiException();
        }
    }

    //firma del metodo descrizione
    public abstract String descrizione();
}
