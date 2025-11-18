
public class EsperimentoRilevamento extends Esperimento {
    public String tipoParticella ;
    public EsperimentoRilevamento(String nome, double energia, String tipoParticella) throws DatiEsperimentoNonValidiException {
        super( nome,  energia);
        this.tipoParticella  = tipoParticella ;

    }

    public String descrizione(){
        StringBuilder stringa = new StringBuilder();
        stringa.append("Nome: ");
        stringa.append(nome);
        stringa.append(", Energia: ");
        stringa.append(energia);
        stringa.append("TeV");
        stringa.append(", Particella: ");
        stringa.append(tipoParticella );
        return stringa.toString();
    }
}
