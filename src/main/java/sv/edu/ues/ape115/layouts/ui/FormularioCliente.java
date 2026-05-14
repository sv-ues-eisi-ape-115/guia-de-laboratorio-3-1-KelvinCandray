package sv.edu.ues.ape115.layouts.ui;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

/**
 * Requerimiento R02 — Formulario de Cliente con GridBagLayout y JTabbedPane.
 *
 * Estructura requerida:
 *
 *  JFrame  BorderLayout
 *  ├── CENTER → JTabbedPane
 *  │    ├── Pestaña "Datos Personales"  (JPanel GridBagLayout + CompoundBorder)
 *  │    │    ├── Fila 0: "Nombre *:"      → JTextField (span 3 cols)
 *  │    │    ├── Fila 1: "Apellido *:"    → JTextField (span 3 cols)
 *  │    │    └── Fila 2: "Fecha nac.:"    → JTextField (col 1-2)
 *  │    │               "Género:"         → JComboBox  (col 3)
 *  │    └── Pestaña "Contacto"            (JPanel GridBagLayout + CompoundBorder)
 *  │         ├── Fila 0: "Email *:"       → JTextField (span 3 cols)
 *  │         ├── Fila 1: "Teléfono:"      → JTextField (col 1-2)
 *  │         │           "Ciudad:"        → JComboBox  (col 3)
 *  │         └── Filas 2-3: "Dirección:"  → JTextArea  (span 3 cols, 2 filas)
 *  │                          en JScrollPane
 *  └── SOUTH → JPanel FlowLayout(RIGHT)   botones Cancelar / Registrar
 *
 * Reglas de negocio:
 *   RN-R02.1 Campos * obligatorios; Registrar muestra JOptionPane.WARNING si falta alguno.
 *   RN-R02.2 JTextArea de Dirección en JScrollPane.
 *   RN-R02.3 Insets homogéneos Insets(5, 6, 5, 6) en todas las filas.
 *   RN-R02.4 Botón Cancelar limpia todos los campos.
 *
 * @author (Kelvin Jair Zacarias Candray)
 */
public class FormularioCliente extends JFrame {

    // ── Pestaña Datos Personales ──────────────────────────────────
    // TODO: declarar JTextField txtNombre, txtApellido, txtFechaNac
    // TODO: declarar JComboBox<String> cboGenero con opciones Masculino/Femenino/Otro

    private JTextField txtNombre, txtApellido, txtFechaNac;
    private JComboBox<String> cboGenero;

    // ── Pestaña Contacto ─────────────────────────────────────────
    // TODO: declarar JTextField txtEmail, txtTelefono
    // TODO: declarar JComboBox<String> cboCiudad con ciudades de El Salvador
    // TODO: declarar JTextArea txDireccion

    private JTextField txtEmail, txtTelefono;
    private JComboBox<String> cboCiudad;
    private JTextArea txDireccion;

    public FormularioCliente() {
        super("R02 — Formulario de Registro de Cliente");
        // TODO: llamar construirUI()
        // TODO: setSize(620, 500)
        // TODO: setMinimumSize(new Dimension(500, 420))
        // TODO: setDefaultCloseOperation(EXIT_ON_CLOSE)
        // TODO: setLocationRelativeTo(null)

        construirUI();
        setSize(620, 500);
        setMinimumSize(new Dimension(500, 420));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void construirUI() {
        // TODO: JPanel root con BorderLayout(8,8)
        // TODO: root.setBorder(EmptyBorder(12,16,12,16))
        // TODO: setContentPane(root)

        // TODO: JTabbedPane tabs = new JTabbedPane(JTabbedPane.TOP)
        // TODO: tabs.addTab("Datos Personales", null, crearPestanaDatos(), "...")
        // TODO: tabs.addTab("Contacto",          null, crearPestanaContacto(), "...")
        // TODO: root.add(tabs, BorderLayout.CENTER)

        // TODO: JPanel pBtns = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 6))
        // TODO: pBtns.setBorder(MatteBorder(1,0,0,0, ...))
        // TODO: JButton btnCancelar → limpiar()
        // TODO: JButton btnRegistrar → validarYRegistrar()
        // TODO: root.add(pBtns, BorderLayout.SOUTH)

        JPanel root = new JPanel(new BorderLayout(8, 8));
        root.setBorder(BorderFactory.createEmptyBorder(12, 16, 12, 16));
        setContentPane(root);

        JTabbedPane tabs = new JTabbedPane(JTabbedPane.TOP);
        tabs.addTab("Datos Personales", null, crearPestanaDatos(), "Información básica del cliente");
        tabs.addTab("Contacto", null, crearPestanaContacto(), "Información de localización");
        root.add(tabs, BorderLayout.CENTER);

        JPanel pBtns = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 6));
        pBtns.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.LIGHT_GRAY));

        JButton btnCancelar = boton("Cancelar", new Color(117, 117, 117));
        btnCancelar.addActionListener(e -> limpiar());

        JButton btnRegistrar = boton("Registrar", new Color(25, 118, 210));
        btnRegistrar.addActionListener(e -> validarYRegistrar());

        pBtns.add(btnCancelar);
        pBtns.add(btnRegistrar);
        root.add(pBtns, BorderLayout.SOUTH);
    }

    private JPanel crearPestanaDatos() {
        // TODO: new JPanel(new GridBagLayout())
        // TODO: setBorder( CompoundBorder: TitledBorder("Datos Personales") + EmptyBorder )
        // TODO: GridBagConstraints g; insets=Insets(5,6,5,6); anchor=WEST

        // TODO — Fila 0: "Nombre *:" (col 0) + txtNombre (col 1-3 span=3, fill=HORIZONTAL)
        // TODO — Fila 1: "Apellido *:" + txtApellido (span=3)
        // TODO — Fila 2: "Fecha nac. (dd/MM/aaaa):" + txtFechaNac (col 1-2) + cboGenero (col 3)
        // TODO — Fila 3: panel vacío con fill=BOTH, weighty=1  (relleno vertical)

        // TODO: return panel

        JPanel p = new JPanel(new GridBagLayout());
        p.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Datos Personales"),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(5, 6, 5, 6); // RN-R02.3
        g.anchor = GridBagConstraints.WEST;

        txtNombre = new JTextField();
        txtApellido = new JTextField();
        txtFechaNac = new JTextField();
        cboGenero = new JComboBox<>(new String[]{"Masculino", "Femenino", "Otro"});

        g.gridy = 0; g.gridx = 0; p.add(new JLabel("Nombre *:"), g);
        g.gridx = 1; g.gridwidth = 3; g.fill = GridBagConstraints.HORIZONTAL; g.weightx = 1.0;
        p.add(txtNombre, g);

        g.gridy = 1; g.gridx = 0; g.gridwidth = 1; g.weightx = 0; p.add(new JLabel("Apellido *:"), g);
        g.gridx = 1; g.gridwidth = 3; g.fill = GridBagConstraints.HORIZONTAL; g.weightx = 1.0;
        p.add(txtApellido, g);

        g.gridy = 2; g.gridx = 0; g.gridwidth = 1; g.weightx = 0; p.add(new JLabel("Fecha nac.:"), g);
        g.gridx = 1; g.gridwidth = 1; p.add(txtFechaNac, g);
        g.gridx = 2; p.add(new JLabel("Género:"), g);
        g.gridx = 3; p.add(cboGenero, g);

        g.gridy = 3; g.weighty = 1.0; g.fill = GridBagConstraints.BOTH;
        p.add(new JLabel(""), g);

        return p;
    }

    private JPanel crearPestanaContacto() {
        // TODO: new JPanel(new GridBagLayout())
        // TODO: setBorder( CompoundBorder: TitledBorder("Contacto") + EmptyBorder )
        // TODO: GridBagConstraints g; insets=Insets(5,6,5,6); anchor=WEST

        // TODO — Fila 0: "Email *:" + txtEmail (span=3, fill=HORIZONTAL)
        // TODO — Fila 1: "Teléfono:" + txtTelefono (col 1-2) + cboCiudad (col 3)
        // TODO — Fila 2: "Dirección:" (col 0)
        // TODO — Filas 2-3: new JScrollPane(txDireccion) en col 1, span 3 cols × 2 filas
        //         txDireccion.setLineWrap(true); txDireccion.setWrapStyleWord(true)
        //         fill=BOTH, weightx=1, weighty=1

        // TODO: return panel

        JPanel p = new JPanel(new GridBagLayout());
        p.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Contacto"),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(5, 6, 5, 6); // RN-R02.3
        g.anchor = GridBagConstraints.WEST;

        txtEmail = new JTextField();
        txtTelefono = new JTextField();
        cboCiudad = new JComboBox<>(new String[]{"San Salvador", "Santa Ana", "San Miguel", "La Libertad", "Sonsonate"});
        txDireccion = new JTextArea(3, 20);
        txDireccion.setLineWrap(true);
        txDireccion.setWrapStyleWord(true);

        g.gridy = 0; g.gridx = 0; p.add(new JLabel("Email *:"), g);
        g.gridx = 1; g.gridwidth = 3; g.fill = GridBagConstraints.HORIZONTAL; g.weightx = 1.0;
        p.add(txtEmail, g);

        g.gridy = 1; g.gridx = 0; g.gridwidth = 1; g.weightx = 0; p.add(new JLabel("Teléfono:"), g);
        g.gridx = 1; p.add(txtTelefono, g);
        g.gridx = 2; p.add(new JLabel("Ciudad:"), g);
        g.gridx = 3; p.add(cboCiudad, g);

        g.gridy = 2; g.gridx = 0; p.add(new JLabel("Dirección:"), g);
        g.gridx = 1; g.gridwidth = 3; g.gridheight = 2;
        g.fill = GridBagConstraints.BOTH; g.weighty = 1.0;
        p.add(new JScrollPane(txDireccion), g); // RN-R02.2

        return p;
        // reemplazar
    }

    private void validarYRegistrar() {
        // TODO: Verificar que txtNombre, txtApellido y txtEmail no estén vacíos (RN-R02.1)
        //       Si falta alguno: JOptionPane.showMessageDialog(..., WARNING_MESSAGE)
        //       Si todo OK: JOptionPane.INFORMATION_MESSAGE con nombre registrado + limpiar()

        if (txtNombre.getText().trim().isEmpty() ||
                txtApellido.getText().trim().isEmpty() ||
                txtEmail.getText().trim().isEmpty()) {

            JOptionPane.showMessageDialog(this,
                    "Los campos marcados con (*) son obligatorios.",
                    "Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String mensaje = "Cliente registrado con éxito:\n" + txtNombre.getText() + " " + txtApellido.getText();
        JOptionPane.showMessageDialog(this, mensaje, "Éxito", JOptionPane.INFORMATION_MESSAGE);
        limpiar();
    }

    private void limpiar() {
        // TODO: Limpiar todos los JTextField (RN-R02.4)
        // TODO: Limpiar txDireccion
        // TODO: Resetear JComboBox a índice 0

        txtNombre.setText("");
        txtApellido.setText("");
        txtFechaNac.setText("");
        txtEmail.setText("");
        txtTelefono.setText("");
        txDireccion.setText("");
        cboGenero.setSelectedIndex(0);
        cboCiudad.setSelectedIndex(0);
        txtNombre.requestFocus();
    }

    // ── Helper: botón estándar del laboratorio ────────────────────
    // NO modificar.
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
