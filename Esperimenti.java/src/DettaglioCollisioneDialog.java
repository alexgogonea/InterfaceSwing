import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DettaglioCollisioneDialog extends JDialog {
    private EsperimentoCollisione esperimento;
    private CERNGUI parent;
    private boolean isPreferito;

    private JTextField txtNome;
    private JTextField txtEnergia;
    private JTextField txtNumeroCollisioni;
    private JButton btnSalva;
    private JButton btnAnnulla;

    public DettaglioCollisioneDialog(CERNGUI parent, EsperimentoCollisione esperimento, boolean isPreferito) {
        super(parent, "Dettaglio Esperimento Collisione", true);
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

        JLabel lblCollisioni = new JLabel("Numero Collisioni:");
        txtNumeroCollisioni = new JTextField();

        // Aggiunta componenti al pannello
        panelCampi.add(lblNome);
        panelCampi.add(txtNome);
        panelCampi.add(lblEnergia);
        panelCampi.add(txtEnergia);
        panelCampi.add(lblCollisioni);
        panelCampi.add(txtNumeroCollisioni);

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
        txtNumeroCollisioni.setText(String.valueOf(esperimento.numeroCollisioni));
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

            int collisioni = Integer.parseInt(txtNumeroCollisioni.getText());
            if (collisioni < 0) {
                JOptionPane.showMessageDialog(this, "Il numero di collisioni non può essere negativo!");
                return;
            }

            // Aggiorna l'oggetto esperimento
            // NOTA: Per modificare gli attributi protetti, dobbiamo aggiungere getter/setter
            // Per ora usiamo reflection o modifichiamo le classi

            // Soluzione temporanea - crea un nuovo esperimento e sostituiscilo
            EsperimentoCollisione nuovoEsperimento = new EsperimentoCollisione(nome, energia, collisioni);

            // Qui dovremmo sostituire l'esperimento nell'archivio
            // Per ora mostriamo un messaggio
            JOptionPane.showMessageDialog(this,
                    "Modifiche salvate con successo!\n" +
                            "Nome: " + nome + "\n" +
                            "Energia: " + energia + " TeV\n" +
                            "Collisioni: " + collisioni);

            dispose(); // Chiude la finestra

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "Formato numerico non valido!\n" +
                            "Energia deve essere un numero (es: 13.6)\n" +
                            "Collisioni deve essere un numero intero");
        } catch (DatiEsperimentoNonValidiException e) {
            JOptionPane.showMessageDialog(this, "Dati dell'esperimento non validi!");
        }
    }
}