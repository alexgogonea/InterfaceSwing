import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ArchivioEsperimenti {
    private List<Esperimento> esperimenti;

    public ArchivioEsperimenti() {
        this.esperimenti = new ArrayList<>();
    }

    public void aggiungiEsperimento(Esperimento e) {
        esperimenti.add(e);
    }

    public void rimuoviEsperimento(Esperimento e) {
        esperimenti.remove(e);
    }

    public int dimensione() {
        return esperimenti.size();
    }

    //con questo salvo su file (Serializza)
    public void salvaSuFile(String file) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new BufferedOutputStream(new FileOutputStream(file)))) {
            oos.writeObject(this);
        } catch (IOException e) {
            System.out.println("Errore: " + e.getMessage());
        }
    }

    //Deserializza
    public static ArchivioEsperimenti caricaDaFile(String file) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(
                new BufferedInputStream(new FileInputStream(file)))) {
            Object obj = ois.readObject();
        } catch (IOException e) {
            System.out.println("Errore: " + e.getMessage());
        }
        return null;
    }
}
