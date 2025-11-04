import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class InterfacciaCERN extends JFrame {

    private ArchivioEsperimenti archivio;
    private MappeScoperte scoperte;
    private DefaultListModel<Esperimento> modelloArchivio;
    private DefaultListModel<String> modelloScoperte;
    private JList<Esperimento> listaEsperimenti;
    private JList<String> listaScoperte;

    public InterfacciaCERN() {
        setTitle("Simulatore CERN");
        setSize(700, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        archivio = ArchivioEsperimenti.caricaDaFile("archivio.cern");
        scoperte = MappeScoperte.caricaDaFile("scoperte.cern");

        modelloArchivio = new DefaultListModel<>();
        modelloScoperte = new DefaultListModel<>();

        for (Esperimento e : archivio.getEsperimenti()) modelloArchivio.addElement(e);
        for (String nome : scoperte.elencoChiavi()) modelloScoperte.addElement(nome);

        listaEsperimenti = new JList<>(modelloArchivio);
        listaScoperte = new JList<>(modelloScoperte);

        add(new JScrollPane(listaEsperimenti), BorderLayout.CENTER);
        add(new JScrollPane(listaScoperte), BorderLayout.EAST);

        JPanel pannelloBottoni = new JPanel();
        JButton btnSalva = new JButton("Salva");
        JButton btnAggiungiPref = new JButton("Aggiungi ai preferiti");
        pannelloBottoni.add(btnSalva);
        pannelloBottoni.add(btnAggiungiPref);
        add(pannelloBottoni, BorderLayout.SOUTH);

        listaEsperimenti.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    Esperimento sel = listaEsperimenti.getSelectedValue();
                    JOptionPane.showMessageDialog(null, sel.descrizione(), "Dettagli Esperimento", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        btnAggiungiPref.addActionListener(e -> {
            Esperimento sel = listaEsperimenti.getSelectedValue();
            if (sel != null) {
                String nome = JOptionPane.showInputDialog("Nome scoperta:");
                if (nome != null && !nome.isEmpty()) {
                    scoperte.aggiungiScoperta(nome, sel);
                    modelloScoperte.addElement(nome);
                }
            }
        });

        btnSalva.addActionListener(e -> {
            archivio.salvaSuFile("archivio.cern");
            scoperte.salvaSuFile("scoperte.cern");
            JOptionPane.showMessageDialog(null, "Dati salvati con successo!");
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new InterfacciaCERN().setVisible(true));
    }
}
