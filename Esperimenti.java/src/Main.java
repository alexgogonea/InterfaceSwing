import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            // Creazione archivio principale
            ArchivioEsperimenti archivio = new ArchivioEsperimenti();

            // Creazione mappa scoperte (preferiti)
            MappaScoperte scoperte = new MappaScoperte();

            // Creazione istanze per ogni sottoclasse (almeno 2 per tipo)

            // Esperimenti di Collisione
            EsperimentoCollisione collisione1 = new EsperimentoCollisione("LHC Run 1", 8.0, 1000000);
            EsperimentoCollisione collisione2 = new EsperimentoCollisione("LHC Run 2", 13.0, 2500000);

            // Esperimenti di Rilevamento
            EsperimentoRilevamento rilevamento1 = new EsperimentoRilevamento("CMS", 13.6, "Bosone di Higgs");
            EsperimentoRilevamento rilevamento2 = new EsperimentoRilevamento("ATLAS", 13.6, "Quark Top");

            // Esperimenti di Simulazione
            EsperimentoSimulazione simulazione1 = new EsperimentoSimulazione("Big Bang Sim", 14.0, 2023);
            EsperimentoSimulazione simulazione2 = new EsperimentoSimulazione("Early Universe", 10.0, 2024);

            // Aggiunta esperimenti all'archivio
            archivio.aggiungiEsperimento(collisione1);
            archivio.aggiungiEsperimento(collisione2);
            archivio.aggiungiEsperimento(rilevamento1);
            archivio.aggiungiEsperimento(rilevamento2);
            archivio.aggiungiEsperimento(simulazione1);
            archivio.aggiungiEsperimento(simulazione2);

            // Aggiunta scoperte note alla mappa preferiti
            scoperte.aggiungiScoperta("Bosone di Higgs", rilevamento1);
            scoperte.aggiungiScoperta("Antimateria", collisione1);
            scoperte.aggiungiScoperta("Quark Top", rilevamento2);

            // Salvataggio su file
            archivio.salvaSuFile("archivio.cern");
            scoperte.salvaSuFile("scoperte.cern");

            System.out.println("=== ARCHIVIO ESPERIMENTI ===");
            // Stampa archivio (simulata - poiché non c'è metodo per iterare)
            for (int i = 0; i < archivio.dimensione(); i++) {
                // Qui dovremmo avere un metodo per accedere agli elementi
                System.out.println("Esperimento " + (i + 1));
            }

            System.out.println("\n=== SCOPERTE PREFERITE ===");
            // Stampa scoperte
            for (String chiave : scoperte.elencoChiavi()) {
                System.out.println("Scoperta: " + chiave);
            }

            System.out.println("\nDati salvati correttamente nei file .cern");

        } catch (DatiEsperimentoNonValidiException e) {
            System.err.println("Errore: dati esperimento non validi");
        } catch (IOException e) {
            System.err.println("Errore di I/O: " + e.getMessage());
        }
    }
}