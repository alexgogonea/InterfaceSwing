import java.io.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MappaScoperte implements Serializable {

    private Map<String, Esperimento> mappa;
    public MappaScoperte() {
        mappa = new HashMap();
    }

    public void aggiungiScoperta(String key, Esperimento scoperta) {
        mappa.put(key, scoperta);
    }

    public void removeScoperta(String key) {
        mappa.remove(key);
    }

    public Collection<String> elencoChiavi() {
        return mappa.keySet();
    }

    public Collection<Esperimento> elencoEsperimenti() {
        return mappa.values();
    }

    // AGGIUNGI QUESTO METODO - per ottenere un esperimento dalla chiave
    public Esperimento getEsperimento(String chiave) {
        return mappa.get(chiave);
    }

    public void salvaSuFile(String nomeFile) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(nomeFile))) {
            out.writeObject(this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public MappaScoperte caricaDaFile(String nomeFile) throws IOException {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(nomeFile));
            return (MappaScoperte) in.readObject();
        } catch (IOException |ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}