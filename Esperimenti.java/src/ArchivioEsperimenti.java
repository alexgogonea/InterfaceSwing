import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class ArchivioEsperimenti implements Serializable {
    private List<Esperimento> lista;

    public ArchivioEsperimenti() {
        lista = new ArrayList();
    }

    public void aggiungiEsperimento(Esperimento esperimento) {
        lista.add(esperimento);
    }

    public void rimuoviEsperimento(int index) {
        lista.remove(index);
    }

    public int dimensione() {
        return lista.size();
    }

    public Iterator<Esperimento> iterator() {
        return lista.iterator();
    }

    public Esperimento getEsperimento(int index) {
        if (index >= 0 && index < lista.size()) {
            return lista.get(index);
        }
        return null;
    }

    public List<Esperimento> getListaEsperimenti() {
        return new ArrayList<>(lista);
    }

    public void salvaSuFile(String nomeFile) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(nomeFile))) {
            out.writeObject(this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ArchivioEsperimenti caricaDaFile(String nomeFile) throws IOException {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(nomeFile));
            return (ArchivioEsperimenti) in.readObject();
        } catch (IOException |ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    //ricercaEsperimentoDaEnergia: ritorna una lista di esperimenti che hanno una data energia ± 10%
    public ArchivioEsperimenti ricercaEsperimentoDaEnergia(double energiaTarget) {
        ArchivioEsperimenti risultato = new ArchivioEsperimenti();
        double tolleranza = energiaTarget * 0.10; // ±10%
        double minEnergia = energiaTarget - tolleranza;
        double maxEnergia = energiaTarget + tolleranza;

        for (Esperimento esperimento : lista) {
            if (esperimento.energia >= minEnergia && esperimento.energia <= maxEnergia) {
                risultato.aggiungiEsperimento(esperimento);
            }
        }
        return risultato;
    }
}