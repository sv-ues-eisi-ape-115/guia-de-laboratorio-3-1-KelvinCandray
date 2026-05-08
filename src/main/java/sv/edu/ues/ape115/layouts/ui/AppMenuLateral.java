package sv.edu.ues.ape115.layouts.ui;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

/**
 * Requerimiento R03 — Menú Lateral con BoxLayout y CardLayout.
 *
 * Estructura requerida:
 *
 *  JFrame  BorderLayout
 *  ├── WEST  → JPanel BoxLayout(Y_AXIS)   ancho fijo 180px
 *  │    ├── JLabel  nombre del sistema
 *  │    ├── JSeparator
 *  │    ├── JButton "Inicio"
 *  │    ├── JButton "Productos"
 *  │    ├── JButton "Clientes"
 *  │    ├── JButton "Reportes"
 *  │    ├── JButton "Configuración"
 *  │    ├── Box.createVerticalGlue()  ← pega el Salir al fondo
 *  │    ├── JSeparator
 *  │    └── JButton "Salir"
 *  └── CENTER → JPanel CardLayout  (5 vistas, una por módulo)
 *
 * Reglas de negocio:
 *   RN-R03.1 Clic en botón lateral → CardLayout muestra la vista correspondiente.
 *   RN-R03.2 El botón activo cambia de color para indicar la selección actual.
 *   RN-R03.3 El botón Salir siempre está pegado al fondo via createVerticalGlue().
 *   RN-R03.4 setMinimumSize(800, 500).
 *
 * @author (escribe tu nombre aquí)
 */
public class AppMenuLateral extends JFrame {

    // TODO: declarar  private final CardLayout card = new CardLayout();
    // TODO: declarar  private final JPanel pnlCentro = new JPanel(card);
    //
    // IMPORTANTE: el campo card DEBE ser de tipo CardLayout (lo verifica el test T04.3)

    private static final String[] MODULOS =
        {"Inicio", "Productos", "Clientes", "Reportes", "Configuración"};

    // TODO: declarar JButton btnActivo = null;   (para cambiar color del activo, RN-R03.2)

    public AppMenuLateral() {
        super("R03 — Menú Lateral: BoxLayout + CardLayout");
        // TODO: construirUI()
        // TODO: setSize(900, 580)
        // TODO: setMinimumSize(new Dimension(800, 500))   ← RN-R03.4
        // TODO: setDefaultCloseOperation(EXIT_ON_CLOSE)
        // TODO: setLocationRelativeTo(null)
    }

    private void construirUI() {
        // TODO: JPanel root = new JPanel(new BorderLayout(0,0))
        // TODO: setContentPane(root)
        // TODO: root.add(crearMenuWest(), BorderLayout.WEST)
        // TODO: root.add(crearCentro(),   BorderLayout.CENTER)
    }

    private JPanel crearMenuWest() {
        // TODO: new JPanel(); setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS))
        // TODO: setBackground(new Color(33, 37, 50))
        // TODO: setPreferredSize(new Dimension(180, 0))
        // TODO: CompoundBorder: MatteBorder(0,0,0,1, gris oscuro) + EmptyBorder(16,8,16,8)

        // TODO: Agregar JLabel con el nombre del sistema (color claro, alineado LEFT)
        // TODO: Agregar Box.createVerticalStrut(4) y JSeparator horizontal

        // TODO: Para cada módulo en MODULOS:
        //         JButton btn = botonNav(modulo)
        //         btn.addActionListener(e -> {
        //             card.show(pnlCentro, modulo);          // RN-R03.1
        //             if (btnActivo != null) btnActivo.setBackground(COLOR_INACTIVO);
        //             btn.setBackground(COLOR_ACTIVO);       // RN-R03.2
        //             btnActivo = btn;
        //         })
        //         panel.add(btn); panel.add(Box.createVerticalStrut(4))

        // TODO: panel.add(Box.createVerticalGlue())    ← RN-R03.3
        // TODO: Agregar JSeparator y JButton "Salir" (rojo oscuro) → dispose()

        // TODO: return panel
        return new JPanel(); // reemplazar
    }

    private JPanel crearCentro() {
        // TODO: Para cada módulo en MODULOS:
        //         pnlCentro.add(crearVistaModulo(modulo), modulo)
        // TODO: return pnlCentro
        return new JPanel(); // reemplazar
    }

    private JPanel crearVistaModulo(String nombre) {
        // TODO: new JPanel(new GridBagLayout())
        // TODO: CompoundBorder: TitledBorder(nombre, EtchedBorder, azul) + EmptyBorder(16,20,16,20)
        // TODO: GridBagConstraints; agregar:
        //         JLabel grande con el nombre del módulo (fila 0, span 2 cols)
        //         3 filas de JLabel + JTextField de ejemplo
        //         Fila final con relleno vertical (fill=BOTH, weighty=1)
        // TODO: return panel
        return new JPanel(); // reemplazar
    }

    // ── Helper: botón de navegación lateral ──────────────────────
    // NO modificar.
    private JButton botonNav(String label) {
        JButton btn = new JButton(label);
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        btn.setForeground(Color.WHITE);
        btn.setBackground(new Color(50, 55, 75));
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 36));
        btn.setAlignmentX(Component.LEFT_ALIGNMENT);
        btn.setFocusPainted(false); btn.setBorderPainted(false); btn.setOpaque(true);
        btn.setBorder(BorderFactory.createEmptyBorder(6, 12, 6, 12));
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return btn;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AppMenuLateral().setVisible(true));
    }
}
