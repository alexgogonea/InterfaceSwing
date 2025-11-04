class EsperimentoRilevamento extends Esperimento {

    private String tipoParticella;

    public EsperimentoRilevamento(String nome, double energia, String tipoParticella)
            throws DatiEsperimentoNonValidiException {
        super(nome, energia);
        if (tipoParticella == null || tipoParticella.trim().isEmpty()) {
            throw new DatiEsperimentoNonValidiException("Tipo particella non valido");
        }
        this.tipoParticella = tipoParticella;
    }

    public String getTipoParticella() {
        return tipoParticella;
    }

    @Override
    public String descrizione() {
        return String.format("Nome: %s, Energia: %.2f TeV, Particella: %s",
                getNome(), getEnergia(), tipoParticella);
    }
}
