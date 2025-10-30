public class Main {

    public static void main(String[] args) {

        try {
            // Creazione di alcuni esperimenti
            EsperimentoCollisione collisione1 = new EsperimentoCollisione("Collisione_A", 13.0, 1000000);
            EsperimentoCollisione collisione2 = new EsperimentoCollisione("Collisione_B", 7.0, 500000);

            EsperimentoRilevamento rilevamento1 = new EsperimentoRilevamento("Rilevamento_Muoni", 13.0, "muone");
            EsperimentoSimulazione simulazione1 = new EsperimentoSimulazione("Simulazione_Higgs", 14.0, 2023);

            // Creazione dell'archivio esperimenti
            ArchivioEsperimenti archivio = new ArchivioEsperimenti();

            // Aggiunta degli esperimenti all'archivio
            archivio.aggiungiEsperimento(collisione1);
            archivio.aggiungiEsperimento(collisione2);
            archivio.aggiungiEsperimento(rilevamento1);
            archivio.aggiungiEsperimento(simulazione1);
;

        } catch (DatiEsperimentoNonValidiException e) {
            System.err.println("Errore nella creazione di un esperimento: " + e.getMessage());
        }

    }
}
