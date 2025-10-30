public abstract class Esperimento {

    private String nome;
    private double energia;

    //costruttore dove lancia eccezione se  i dati non sono validi
    public Esperimento(String nome, double energia) throws DatiEsperimentoNonValidiException {
        if (nome == null || nome.trim().isEmpty()) {
            throw new DatiEsperimentoNonValidiException("Devi inserire il nome");
        }
        if (energia <= 0) {
            throw new DatiEsperimentoNonValidiException("Energia deve essere >= 0");
        }
        this.nome = nome;
        this.energia = energia;
    }

    public String getNome() {
        return nome;
    }

    public double getEnergia() {
        return energia;
    }

    public abstract String descrizione();

    //toString usa descrizione() per stampare
    @Override
    public String toString() {
        return descrizione();
    }
}