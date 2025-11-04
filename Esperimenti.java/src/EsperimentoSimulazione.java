class EsperimentoSimulazione extends Esperimento {

    private int annoSimulazione;

    public EsperimentoSimulazione(String nome, double energia, int annoSimulazione)
            throws DatiEsperimentoNonValidiException {
        super(nome, energia);
        if (annoSimulazione < 0) {
            throw new DatiEsperimentoNonValidiException("Anno simulazione non valido");
        }
        this.annoSimulazione = annoSimulazione;
    }

    public int getAnnoSimulazione() {
        return annoSimulazione;
    }

    @Override
    public String descrizione() {
        return String.format("Nome: %s, Anno: %d, Energia: %.2f TeV",
                getNome(), annoSimulazione, getEnergia());
    }
}
