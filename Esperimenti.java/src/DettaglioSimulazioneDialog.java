import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

public class DettaglioSimulazioneDialog extends JDialog {
    private EsperimentoSimulazione esperimento;
    private CERNGUI parent;
    private boolean isPreferito;

    private JTextField txtNome;
    private JTextField txtEnergia;
    private JTextField txtAnnoSimulazione;
    private JButton btnSalva;
    private JButton btnAnnulla;

    public DettaglioSimulazioneDialog(CERNGUI parent, EsperimentoSimulazione esperimento, boolean isPreferito) {
        super(parent, "Dettaglio Esperimento Simulazione", true);
        this.parent = parent;
        this.esperimento = esperimento;
        this.isPreferito = isPreferito;

        inizializzaUI();
        caricaDati();
        setLocationRelativeTo(parent);
        setVisible(true);
    }

    private void inizializzaUI() {
        setLayout(new BorderLayout());
        setSize(400, 300);

        // Pannello centrale con i campi
        JPanel panelCampi = new JPanel(new GridLayout(3, 2, 10, 10));
        panelCampi.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Creazione etichette e campi
        JLabel lblNome = new JLabel("Nome Esperimento:");
        txtNome = new JTextField();

        JLabel lblEnergia = new JLabel("Energia (TeV):");
        txtEnergia = new JTextField();

        JLabel lblAnno = new JLabel("Anno Simulazione:");
        txtAnnoSimulazione = new JTextField();

        // Aggiunta componenti al pannello
        panelCampi.add(lblNome);
        panelCampi.add(txtNome);
        panelCampi.add(lblEnergia);
        panelCampi.add(txtEnergia);
        panelCampi.add(lblAnno);
        panelCampi.add(txtAnnoSimulazione);

        // Pannello pulsanti
        JPanel panelPulsanti = new JPanel(new FlowLayout());
        btnSalva = new JButton("Salva Modifiche");
        btnAnnulla = new JButton("Annulla");

        btnSalva.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvaModifiche();
            }
        });

        btnAnnulla.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Chiude la finestra
            }
        });

        panelPulsanti.add(btnSalva);
        panelPulsanti.add(btnAnnulla);

        // Aggiunta dei pannelli alla finestra
        add(panelCampi, BorderLayout.CENTER);
        add(panelPulsanti, BorderLayout.SOUTH);

        // Messaggio se è un preferito
        if (isPreferito) {
            JLabel lblPreferito = new JLabel(" ESPERIMENTO PREFERITO");
            lblPreferito.setHorizontalAlignment(SwingConstants.CENTER);
            lblPreferito.setForeground(Color.BLUE);
            add(lblPreferito, BorderLayout.NORTH);
        }
    }

    private void caricaDati() {
        // Inserisce i dati attuali dell'esperimento nei campi
        txtNome.setText(esperimento.nome);
        txtEnergia.setText(String.valueOf(esperimento.energia));
        txtAnnoSimulazione.setText(String.valueOf(esperimento.annoSimulazione));
    }

    private void salvaModifiche() {
        try {
            // Validazione dati
            String nome = txtNome.getText().trim();
            if (nome.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Il nome non può essere vuoto!");
                return;
            }

            double energia = Double.parseDouble(txtEnergia.getText());
            if (energia <= 0) {
                JOptionPane.showMessageDialog(this, "L'energia deve essere maggiore di 0!");
                return;
            }

            int anno = Integer.parseInt(txtAnnoSimulazione.getText());
            int annoCorrente = Calendar.getInstance().get(Calendar.YEAR);
            if (anno < 1950 || anno > annoCorrente + 10) {
                JOptionPane.showMessageDialog(this,
                        "L'anno di simulazione non è realistico!\n" +
                                "Deve essere tra il 1950 e " + (annoCorrente + 10));
                return;
            }

            // Aggiorna l'oggetto esperimento
            // Per ora mostriamo un messaggio di conferma
            JOptionPane.showMessageDialog(this,
                    "Modifiche salvate con successo!\n" +
                            "Nome: " + nome + "\n" +
                            "Energia: " + energia + " TeV\n" +
                            "Anno Simulazione: " + anno);

            dispose(); // Chiude la finestra

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "Formato numerico non valido!\n" +
                            "Energia deve essere un numero (es: 13.6)\n" +
                            "Anno deve essere un numero intero");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Errore nel salvataggio: " + e.getMessage());
        }
    }
}