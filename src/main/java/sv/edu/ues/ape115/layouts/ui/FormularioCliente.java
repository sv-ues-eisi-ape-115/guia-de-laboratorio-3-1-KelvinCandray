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
 * @author (escribe tu nombre aquí)
 */
public class FormularioCliente extends JFrame {

    // ── Pestaña Datos Personales ──────────────────────────────────
    // TODO: declarar JTextField txtNombre, txtApellido, txtFechaNac
    // TODO: declarar JComboBox<String> cboGenero con opciones Masculino/Femenino/Otro

    // ── Pestaña Contacto ─────────────────────────────────────────
    // TODO: declarar JTextField txtEmail, txtTelefono
    // TODO: declarar JComboBox<String> cboCiudad con ciudades de El Salvador
    // TODO: declarar JTextArea txDireccion

    public FormularioCliente() {
        super("R02 — Formulario de Registro de Cliente");
        // TODO: llamar construirUI()
        // TODO: setSize(620, 500)
        // TODO: setMinimumSize(new Dimension(500, 420))
        // TODO: setDefaultCloseOperation(EXIT_ON_CLOSE)
        // TODO: setLocationRelativeTo(null)
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
        return new JPanel(); // reemplazar
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
        return new JPanel(); // reemplazar
    }

    private void validarYRegistrar() {
        // TODO: Verificar que txtNombre, txtApellido y txtEmail no estén vacíos (RN-R02.1)
        //       Si falta alguno: JOptionPane.showMessageDialog(..., WARNING_MESSAGE)
        //       Si todo OK: JOptionPane.INFORMATION_MESSAGE con nombre registrado + limpiar()
    }

    private void limpiar() {
        // TODO: Limpiar todos los JTextField (RN-R02.4)
        // TODO: Limpiar txDireccion
        // TODO: Resetear JComboBox a índice 0
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
