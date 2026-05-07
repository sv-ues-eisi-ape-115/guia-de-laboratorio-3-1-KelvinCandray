package sv.edu.ues.ape115.layouts.ui;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

/**
 * Requerimiento R02 — Formulario de Registro con GridBagLayout y JTabbedPane.
 *
 * TODO: Completar los paneles de cada pestaña según R02.
 *
 * @author (nombre del estudiante)
 */
public class FormularioCliente extends JFrame {

    // ── Pestaña Datos Personales ──────────────────────────────────
    private JTextField    txtNombre, txtApellido, txtFechaNac;
    private JComboBox<String> cboGenero;

    // ── Pestaña Contacto ─────────────────────────────────────────
    private JTextField    txtEmail, txtTelefono;
    private JComboBox<String> cboCiudad;
    private JTextArea     txDireccion;

    public FormularioCliente() {
        super("R02 — Formulario de Registro de Cliente");
        construirUI();
        setSize(620, 500);
        setMinimumSize(new Dimension(500, 420));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void construirUI() {
        JPanel root = new JPanel(new BorderLayout(8, 8));
        root.setBorder(BorderFactory.createEmptyBorder(12, 16, 12, 16));
        setContentPane(root);

        // ── JTabbedPane ─────────────────────────────────────────
        JTabbedPane tabs = new JTabbedPane(JTabbedPane.TOP);
        tabs.addTab("Datos Personales", null, crearPestanaDatos(),
                    "Información personal del cliente");
        tabs.addTab("Contacto", null, crearPestanaContacto(),
                    "Información de contacto y dirección");
        root.add(tabs, BorderLayout.CENTER);

        // ── SOUTH: botones ───────────────────────────────────────
        JPanel pBtns = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 6));
        pBtns.setBorder(BorderFactory.createMatteBorder(
            1, 0, 0, 0, new Color(200, 210, 230)));
        JButton btnCancelar = boton("Cancelar", new Color(100, 100, 100));
        JButton btnRegistrar= boton("Registrar",new Color(46,  125,  50));
        btnCancelar.addActionListener(e -> limpiar());
        btnRegistrar.addActionListener(e -> validar());
        pBtns.add(btnCancelar);
        pBtns.add(btnRegistrar);
        root.add(pBtns, BorderLayout.SOUTH);
    }

    private JPanel crearPestanaDatos() {
        JPanel p = new JPanel(new GridBagLayout());
        p.setBorder(compound("Datos Personales"));
        GridBagConstraints g = gbc();

        // Nombre * (span 3 cols)
        addLbl(p, g, "Nombre *:", 0, 0);
        g.gridx=1; g.gridy=0; g.gridwidth=3;
        g.fill=GridBagConstraints.HORIZONTAL; g.weightx=1;
        txtNombre = tf(); p.add(txtNombre, g); resetGBC(g);

        // Apellido * (span 3 cols)
        addLbl(p, g, "Apellido *:", 0, 1);
        g.gridx=1; g.gridy=1; g.gridwidth=3;
        g.fill=GridBagConstraints.HORIZONTAL; g.weightx=1;
        txtApellido = tf(); p.add(txtApellido, g); resetGBC(g);

        // Fecha (col 1-2) | Género JComboBox (col 3)
        addLbl(p, g, "Fecha nac. (dd/MM/aaaa):", 0, 2);
        g.gridx=1; g.gridy=2; g.gridwidth=2;
        g.fill=GridBagConstraints.HORIZONTAL; g.weightx=0.6;
        txtFechaNac = tf(); p.add(txtFechaNac, g);
        g.gridx=3; g.gridwidth=1;
        g.fill=GridBagConstraints.HORIZONTAL; g.weightx=0.4;
        cboGenero = new JComboBox<>(new String[]{"Masculino","Femenino","Otro"});
        cboGenero.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        p.add(cboGenero, g); resetGBC(g);

        // Relleno
        spacer(p, g, 3);
        return p;
    }

    private JPanel crearPestanaContacto() {
        JPanel p = new JPanel(new GridBagLayout());
        p.setBorder(compound("Contacto"));
        GridBagConstraints g = gbc();

        // Email * (span 3)
        addLbl(p, g, "Email *:", 0, 0);
        g.gridx=1; g.gridy=0; g.gridwidth=3;
        g.fill=GridBagConstraints.HORIZONTAL; g.weightx=1;
        txtEmail = tf(); p.add(txtEmail, g); resetGBC(g);

        // Teléfono (col 1-2) | Ciudad (col 3)
        addLbl(p, g, "Teléfono:", 0, 1);
        g.gridx=1; g.gridy=1; g.gridwidth=2;
        g.fill=GridBagConstraints.HORIZONTAL; g.weightx=0.6;
        txtTelefono = tf(); p.add(txtTelefono, g);
        g.gridx=3; g.gridwidth=1;
        g.fill=GridBagConstraints.HORIZONTAL; g.weightx=0.4;
        cboCiudad = new JComboBox<>(
            new String[]{"San Salvador","Santa Ana","San Miguel","Sonsonate"});
        cboCiudad.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        p.add(cboCiudad, g); resetGBC(g);

        // Dirección (etiqueta fila 2, JTextArea filas 2-3 span 1-3)
        addLbl(p, g, "Dirección:", 0, 2);
        g.gridx=1; g.gridy=2; g.gridwidth=3; g.gridheight=2;
        g.fill=GridBagConstraints.BOTH; g.weightx=1; g.weighty=1;
        txDireccion = new JTextArea(3, 18);
        txDireccion.setLineWrap(true); txDireccion.setWrapStyleWord(true);
        txDireccion.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        p.add(new JScrollPane(txDireccion), g); resetGBC(g);

        return p;
    }

    private void validar() {
        // TODO R02: verificar campos * y mostrar JOptionPane.WARNING si falta alguno
        if (txtNombre.getText().trim().isEmpty()
         || txtApellido.getText().trim().isEmpty()
         || txtEmail.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Los campos marcados con * son obligatorios.",
                "Campos requeridos", JOptionPane.WARNING_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(this,
            "Cliente registrado: " + txtNombre.getText() + " " + txtApellido.getText(),
            "Registro exitoso", JOptionPane.INFORMATION_MESSAGE);
        limpiar();
    }

    private void limpiar() {
        for (JTextField tf : new JTextField[]{
                txtNombre, txtApellido, txtFechaNac, txtEmail, txtTelefono})
            if (tf != null) tf.setText("");
        if (txDireccion != null) txDireccion.setText("");
    }

    // ── Helpers ───────────────────────────────────────────────────
    private Border compound(String titulo) {
        return BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(EtchedBorder.LOWERED),
                titulo, TitledBorder.LEFT, TitledBorder.TOP,
                new Font("Segoe UI", Font.BOLD, 12),
                new Color(25, 118, 210)
            ),
            BorderFactory.createEmptyBorder(8, 10, 8, 10)
        );
    }

    private GridBagConstraints gbc() {
        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(5, 6, 5, 6);
        g.anchor = GridBagConstraints.WEST;
        return g;
    }

    private void resetGBC(GridBagConstraints g) {
        g.gridwidth=1; g.gridheight=1; g.weightx=0; g.weighty=0;
    }

    private void addLbl(JPanel p, GridBagConstraints g, String t, int x, int y) {
        g.gridx=x; g.gridy=y; g.fill=GridBagConstraints.NONE;
        g.weightx=0; g.gridwidth=1;
        JLabel l = new JLabel(t);
        l.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        p.add(l, g);
    }

    private void spacer(JPanel p, GridBagConstraints g, int row) {
        g.gridx=0; g.gridy=row; g.gridwidth=4;
        g.fill=GridBagConstraints.BOTH; g.weighty=1;
        p.add(new JPanel(), g); resetGBC(g);
    }

    private JTextField tf() {
        JTextField t = new JTextField();
        t.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        return t;
    }

    private JButton boton(String label, Color color) {
        JButton btn = new JButton(label);
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        btn.setBackground(color); btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false); btn.setBorderPainted(false); btn.setOpaque(true);
        btn.setBorder(BorderFactory.createEmptyBorder(7, 18, 7, 18));
        return btn;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FormularioCliente().setVisible(true));
    }
}
