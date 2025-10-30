class EsperimentoCollisione extends Esperimento {

    private int numeroCollisioni;
    private int numero;

    //Costruttore dove lancia eccezione se  i dati non sono validi
    public EsperimentoCollisione(String nome, double energia, int numeroCollisioni) throws DatiEsperimentoNonValidiException {
        super(nome, energia);
        if (numeroCollisioni < 0) {
            throw new DatiEsperimentoNonValidiException("Devi mettere un numero di collisioni maggiore di 0");
        }
        this.numeroCollisioni = numeroCollisioni;
    }

    public int getNumeroCollisioni(){
        return numeroCollisioni;
    }

    //Faccio Override perchè c'è un metodo abstract
    @Override
    //stampo
    public String descrizione() {
        return String.format("Nome: %s, Energia: %.2f TeV, Collisioni: %d", getNome(), getEnergia(), numeroCollisioni);
    }

}