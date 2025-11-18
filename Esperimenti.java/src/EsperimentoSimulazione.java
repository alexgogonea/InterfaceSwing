
public class EsperimentoSimulazione extends Esperimento {
    public int annoSimulazione ;
    public EsperimentoSimulazione(String nome, double energia, int annoSimulazione) throws DatiEsperimentoNonValidiException {
        super( nome,  energia);
        this.annoSimulazione  = annoSimulazione ;

    }

    public String descrizione(){
        StringBuilder stringa = new StringBuilder();
        stringa.append("Nome: ");
        stringa.append(nome);
        stringa.append(", Anno di Simulazione: ");
        stringa.append(annoSimulazione );
        stringa.append(", Energia: ");
        stringa.append(energia);
        stringa.append("TeV");

        return stringa.toString();
    }
}
