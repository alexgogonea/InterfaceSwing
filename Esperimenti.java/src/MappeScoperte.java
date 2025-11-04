import java.io.*;
import java.util.*;

public class MappeScoperte implements Serializable {

    private Map<String, Esperimento> scoperte = new HashMap<>();

    public void aggiungiScoperta(String nome, Esperimento esperimento) {
        scoperte.put(nome, esperimento);
    }

    public void rimuoviScoperta(String nome) {
        scoperte.remove(nome);
    }

    public Set<String> elencoChiavi() {
        return scoperte.keySet();
    }

    public Collection<Esperimento> elencoEsperimenti() {
        return scoperte.values();
    }

    public Esperimento getEsperimento(String nome) {
        return scoperte.get(nome);
    }

    public void salvaSuFile(String nomeFile) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(nomeFile))) {
            out.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static MappeScoperte caricaDaFile(String nomeFile) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(nomeFile))) {
            return (MappeScoperte) in.readObject();
        } catch (Exception e) {
            return new MappeScoperte();
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Mappa scoperte:\n");
        for (Map.Entry<String, Esperimento> entry : scoperte.entrySet()) {
            sb.append("- ").append(entry.getKey())
                    .append(" → ").append(entry.getValue().descrizione()).append("\n");
        }
        return sb.toString();
    }

    public Map<String, Esperimento> getMappa() {
        return scoperte;
    }

}
