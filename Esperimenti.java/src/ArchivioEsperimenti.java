import java.io.*;
import java.util.*;

public class ArchivioEsperimenti implements Serializable {

    private List<Esperimento> esperimenti = new ArrayList<>();

    public void aggiungiEsperimento(Esperimento e) {
        esperimenti.add(e);
    }

    public void rimuoviEsperimento(Esperimento e) {
        esperimenti.remove(e);
    }

    public List<Esperimento> getEsperimenti() {
        return esperimenti;
    }

    public int dimensione() {
        return esperimenti.size();
    }

    public List<Esperimento> ricercaEsperimentoDaEnergia(double energia) {
        List<Esperimento> risultati = new ArrayList<>();
        double min = energia * 0.9;
        double max = energia * 1.1;
        for (Esperimento e : esperimenti) {
            if (e.getEnergia() >= min && e.getEnergia() <= max) {
                risultati.add(e);
            }
        }
        return risultati;
    }

    public void salvaSuFile(String nomeFile) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(nomeFile))) {
            out.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArchivioEsperimenti caricaDaFile(String nomeFile) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(nomeFile))) {
            return (ArchivioEsperimenti) in.readObject();
        } catch (Exception e) {
            return new ArchivioEsperimenti();
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Archivio esperimenti:\n");
        for (Esperimento e : esperimenti) {
            sb.append("- ").append(e.descrizione()).append("\n");
        }
        return sb.toString();
    }
}
