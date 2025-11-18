import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.io.IOException;

public class CERNGUI extends JFrame {

    private ArchivioEsperimenti archivio;
    private MappaScoperte scoperte;
    private DefaultListModel<String> listaEsperimentiModel;
    private DefaultListModel<String> listaPreferitiModel;
    private JList<String> listaEsperimenti;
    private JList<String> listaPreferiti;

    public CERNGUI() {
        super("CERN Laboratory - Gestione Esperimenti");
        inizializzaDati();
        inizializzaUI();
        caricaDatiIniziali();
    }

    private void inizializzaDati() {
        archivio = new ArchivioEsperimenti();
        scoperte = new MappaScoperte();
    }

    private void inizializzaUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Creazione dei modelli per le liste
        listaEsperimentiModel = new DefaultListModel<>();
        listaPreferitiModel = new DefaultListModel<>();

        // Creazione delle liste
        listaEsperimenti = new JList<>(listaEsperimentiModel);
        listaPreferiti = new JList<>(listaPreferitiModel);

        // Pannello principale diviso in due
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                creaPannelloEsperimenti(), creaPannelloPreferiti());
        splitPane.setResizeWeight(0.5);

        add(splitPane, BorderLayout.CENTER);
        add(creaPannelloControlli(), BorderLayout.SOUTH);

        setSize(900, 600);
        setLocationRelativeTo(null); // Centra la finestra
    }

    private JPanel creaPannelloEsperimenti() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Archivio Esperimenti"));

        listaEsperimenti.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaEsperimenti.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int index = listaEsperimenti.getSelectedIndex();
                    if (index != -1) {
                        Esperimento exp = archivio.getEsperimento(index);
                        mostraDettaglioEsperimento(exp, false);
                    }
                }
            }
        });

        panel.add(new JScrollPane(listaEsperimenti), BorderLayout.CENTER);
        return panel;
    }

    private JPanel creaPannelloPreferiti() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Scoperte Preferite"));

        listaPreferiti.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaPreferiti.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int index = listaPreferiti.getSelectedIndex();
                    if (index != -1) {
                        String chiave = listaPreferitiModel.getElementAt(index);
                        Esperimento exp = scoperte.getEsperimento(chiave);
                        if (exp != null) {
                            mostraDettaglioEsperimento(exp, true);
                        }
                    }
                }
            }
        });

        panel.add(new JScrollPane(listaPreferiti), BorderLayout.CENTER);
        return panel;
    }

    private JPanel creaPannelloControlli() {
        JPanel panel = new JPanel(new FlowLayout());

        JButton btnCarica = new JButton("Carica Dati");
        JButton btnSalva = new JButton("Salva Dati");
        JButton btnAggiungi = new JButton("Nuovo Esperimento");
        JButton btnAggiungiPreferito = new JButton("Aggiungi ai Preferiti");
        JButton btnRimuoviPreferito = new JButton("Rimuovi dai Preferiti");
        JButton btnMappa = new JButton("Mostra Mappa");

        btnCarica.addActionListener(e -> caricaDati());
        btnSalva.addActionListener(e -> salvaDati());
        btnAggiungi.addActionListener(e -> nuovoEsperimento());
        btnAggiungiPreferito.addActionListener(e -> aggiungiAiPreferiti());
        btnRimuoviPreferito.addActionListener(e -> rimuoviDaiPreferiti());
        btnMappa.addActionListener(e -> mostraMappa());

        panel.add(btnCarica);
        panel.add(btnSalva);
        panel.add(btnAggiungi);
        panel.add(btnAggiungiPreferito);
        panel.add(btnRimuoviPreferito);
        panel.add(btnMappa);

        return panel;
    }

    private void caricaDatiIniziali() {
        try {
            // Esperimenti di esempio
            EsperimentoCollisione collisione1 = new EsperimentoCollisione("LHC Run 1", 8.0, 1000000);
            EsperimentoCollisione collisione2 = new EsperimentoCollisione("LHC Run 2", 13.0, 2500000);
            EsperimentoRilevamento rilevamento1 = new EsperimentoRilevamento("CMS", 13.6, "Bosone di Higgs");
            EsperimentoRilevamento rilevamento2 = new EsperimentoRilevamento("ATLAS", 13.6, "Quark Top");
            EsperimentoSimulazione simulazione1 = new EsperimentoSimulazione("Big Bang Sim", 14.0, 2023);
            EsperimentoSimulazione simulazione2 = new EsperimentoSimulazione("Early Universe", 10.0, 2024);

            archivio.aggiungiEsperimento(collisione1);
            archivio.aggiungiEsperimento(collisione2);
            archivio.aggiungiEsperimento(rilevamento1);
            archivio.aggiungiEsperimento(rilevamento2);
            archivio.aggiungiEsperimento(simulazione1);
            archivio.aggiungiEsperimento(simulazione2);

            scoperte.aggiungiScoperta("Bosone di Higgs", rilevamento1);
            scoperte.aggiungiScoperta("Antimateria", collisione1);
            scoperte.aggiungiScoperta("Quark Top", rilevamento2);

            aggiornaListe();

        } catch (DatiEsperimentoNonValidiException e) {
            JOptionPane.showMessageDialog(this, "Errore nel caricamento dati: " + e.getMessage());
        }
    }

    private void aggiornaListe() {
        listaEsperimentiModel.clear();
        listaPreferitiModel.clear();

        for (int i = 0; i < archivio.dimensione(); i++) {
            Esperimento exp = archivio.getEsperimento(i);
            listaEsperimentiModel.addElement(exp.descrizione());
        }

        for (String chiave : scoperte.elencoChiavi()) {
            listaPreferitiModel.addElement(chiave);
        }
    }

    private void mostraDettaglioEsperimento(Esperimento esperimento, boolean isPreferito) {
        if (esperimento instanceof EsperimentoCollisione) {
            new DettaglioCollisioneDialog(this, (EsperimentoCollisione) esperimento, isPreferito);
        } else if (esperimento instanceof EsperimentoRilevamento) {
            new DettaglioRilevamentoDialog(this, (EsperimentoRilevamento) esperimento, isPreferito);
        } else if (esperimento instanceof EsperimentoSimulazione) {
            new DettaglioSimulazioneDialog(this, (EsperimentoSimulazione) esperimento, isPreferito);
        }
    }

    private void caricaDati() {
        JOptionPane.showMessageDialog(this, "Funzione Carica Dati - Da implementare");
    }

    private void salvaDati() {
        try {
            archivio.salvaSuFile("archivio.cern");
            scoperte.salvaSuFile("scoperte.cern");
            JOptionPane.showMessageDialog(this, "Dati salvati con successo!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Errore nel salvataggio: " + e.getMessage());
        }
    }

    private void nuovoEsperimento() {
        JOptionPane.showMessageDialog(this, "Funzione Nuovo Esperimento - Da implementare");
    }

    private void aggiungiAiPreferiti() {
        int index = listaEsperimenti.getSelectedIndex();
        if (index != -1) {
            Esperimento exp = archivio.getEsperimento(index);
            String nomeScoperta = JOptionPane.showInputDialog(this, "Inserisci nome della scoperta:");
            if (nomeScoperta != null && !nomeScoperta.trim().isEmpty()) {
                scoperte.aggiungiScoperta(nomeScoperta, exp);
                aggiornaListe();
                JOptionPane.showMessageDialog(this, "Esperimento aggiunto ai preferiti!");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleziona un esperimento dalla lista prima di aggiungerlo ai preferiti");
        }
    }

    private void rimuoviDaiPreferiti() {
        int index = listaPreferiti.getSelectedIndex();
        if (index != -1) {
            String chiave = listaPreferitiModel.getElementAt(index);
            scoperte.removeScoperta(chiave);
            aggiornaListe();
            JOptionPane.showMessageDialog(this, "Scoperta rimossa dai preferiti!");
        } else {
            JOptionPane.showMessageDialog(this, "Seleziona una scoperta dalla lista preferiti");
        }
    }

    private void mostraMappa() {
        SwingUtilities.invokeLater(MappaViewer::new);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CERNGUI().setVisible(true));
    }
}
