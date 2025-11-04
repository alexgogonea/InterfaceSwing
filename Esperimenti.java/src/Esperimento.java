import java.io.Serializable;

abstract class Esperimento implements Serializable {

    private String nome;
    private double energia;

    public Esperimento(String nome, double energia) throws DatiEsperimentoNonValidiException {
        if (nome == null || nome.trim().isEmpty()) {
            throw new DatiEsperimentoNonValidiException("Il nome non può essere vuoto");
        }
        if (energia <= 0) {
            throw new DatiEsperimentoNonValidiException("L’energia deve essere > 0");
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

    @Override
    public String toString() {
        return descrizione();
    }
}
