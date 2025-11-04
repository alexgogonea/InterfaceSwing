import java.io.IOException;
import java.util.Map;


public class Main {
    public static void main(String[] args) {
        ArchivioEsperimenti archivio = new ArchivioEsperimenti();
        MappeScoperte scoperte = new MappeScoperte();

        try {
            // Creazione esperimenti
            EsperimentoCollisione e1 = new EsperimentoCollisione("Collisione LHC", 13.6, 1000);
            EsperimentoCollisione e2 = new EsperimentoCollisione("Collisione ATLAS", 14.0, 1200);

            EsperimentoRilevamento e3 = new EsperimentoRilevamento("Rilevamento Muone", 7.0, "muone");
            EsperimentoRilevamento e4 = new EsperimentoRilevamento("Rilevamento Fotone", 8.0, "fotone");

            EsperimentoSimulazione e5 = new EsperimentoSimulazione("Simulazione Bosone", 10.0, 2022);
            EsperimentoSimulazione e6 = new EsperimentoSimulazione("Simulazione Quark", 9.5, 2023);

            // Aggiunta all'archivio
            archivio.aggiungiEsperimento(e1);
            archivio.aggiungiEsperimento(e2);
            archivio.aggiungiEsperimento(e3);
            archivio.aggiungiEsperimento(e4);
            archivio.aggiungiEsperimento(e5);
            archivio.aggiungiEsperimento(e6);

            // Salvataggio su file
            archivio.salvaSuFile("archivio.cern");

            // Caricamento da file
            ArchivioEsperimenti archivioCaricato = new ArchivioEsperimenti();
            archivioCaricato.caricaDaFile("archivio.cern");

            // Aggiunta scoperte note
            scoperte.aggiungiScoperta("Bosone di Higgs", e5);
            scoperte.aggiungiScoperta("Antimateria", e1);

            // Stampa contenuto archivio
            System.out.println("Contenuto Archivio Esperimenti");
            for (Esperimento e : archivioCaricato.ricercaEsperimentoDaEnergia(10)) {
                System.out.println(e.descrizione());
            }

            // Stampa scoperte in modo corretto
            System.out.println("\nScoperte Note");
            for (Map.Entry<String, Esperimento> entry : scoperte.getMappa().entrySet()) {
                String particella = entry.getKey();
                Esperimento esperimento = entry.getValue();
                System.out.println(particella + " - " + esperimento.descrizione());
            }

        } catch (DatiEsperimentoNonValidiException ex) {
            System.err.println("Errore dati esperimento: " + ex.getMessage());
        }


    }
}
