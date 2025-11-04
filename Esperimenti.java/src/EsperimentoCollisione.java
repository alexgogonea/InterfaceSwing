class EsperimentoCollisione extends Esperimento {

    private int numeroCollisioni;

    public EsperimentoCollisione(String nome, double energia, int numeroCollisioni)
            throws DatiEsperimentoNonValidiException {
        super(nome, energia);
        if (numeroCollisioni < 0) {
            throw new DatiEsperimentoNonValidiException("Numero collisioni non valido");
        }
        this.numeroCollisioni = numeroCollisioni;
    }

    public int getNumeroCollisioni() {
        return numeroCollisioni;
    }

    @Override
    public String descrizione() {
        return String.format("Nome: %s, Energia: %.2f TeV, Collisioni: %d",
                getNome(), getEnergia(), numeroCollisioni);
    }
}
