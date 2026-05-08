package sv.edu.ues.ape115.layouts.ui;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

/**
 * Requerimiento R05 — Dashboard Integrado: todos los layouts.
 *
 * Estructura requerida (TODOS los layouts del laboratorio en una sola ventana):
 *
 *  JFrame  BorderLayout(8,8)
 *  ├── NORTH  → JPanel FlowLayout(LEFT)   título + 2 tarjetas de estadística
 *  │            Borde: MatteBorder(0,0,2,0) azul  ← RN-R05.1
 *  │            Las tarjetas tienen LineBorder     ← RN-R05.1
 *  ├── WEST   → JPanel BoxLayout(Y_AXIS)  4 botones de módulo  ancho 170px
 *  │            Borde: CompoundBorder TitledBorder("Módulos")  ← RN-R05.1
 *  ├── CENTER → JSplitPane(resizeWeight=0.70)                  ← RN-R05.4
 *  │    ├── Izquierda: JTabbedPane  3 pestañas                 ← RN-R05.2
 *  │    │    ├── "Resumen"   → JPanel GridLayout(2,3)  6 tarjetas EtchedBorder  ← RN-R05.3
 *  │    │    ├── "Productos" → JTable en JScrollPane
 *  │    │    └── "Actividad" → JTextArea en JScrollPane
 *  │    └── Derecha: JPanel GridBagLayout  formulario rápido 3 campos + botón
 *  │                 Borde: CompoundBorder TitledBorder         ← RN-R05.5
 *  └── SOUTH  → JPanel FlowLayout(RIGHT)  estado + 2 botones
 *               Borde: MatteBorder(1,0,0,0)                    ← RN-R05.1
 *
 * Reglas de negocio:
 *   RN-R05.1 Cada región del BorderLayout tiene un borde distinto.
 *   RN-R05.2 JTabbedPane con 3 pestañas con contenido real (no vacías).
 *   RN-R05.3 Pestaña Resumen: GridLayout(2,3) con 6 tarjetas + EtchedBorder.
 *   RN-R05.4 JSplitPane: setResizeWeight(0.70).
 *   RN-R05.5 Todos los paneles de sección usan CompoundBorder.
 *   setMinimumSize(900, 550) obligatorio.
 *
 * @author (escribe tu nombre aquí)
 */
public class DashboardIntegrado extends JFrame {

    public DashboardIntegrado() {
        super("R05 — Dashboard Integrado — Todos los Layouts");
        // TODO: construirUI()
        // TODO: setSize(1100, 680)
        // TODO: setMinimumSize(new Dimension(900, 550))   ← obligatorio, lo verifica test T06.5
        // TODO: setDefaultCloseOperation(EXIT_ON_CLOSE)
        // TODO: setLocationRelativeTo(null)
    }

    private void construirUI() {
        // TODO: JPanel root = new JPanel(new BorderLayout(8, 8))
        // TODO: root.setBorder(EmptyBorder(8,10,8,10))
        // TODO: setContentPane(root)
        // TODO: root.add(crearNorth(),  BorderLayout.NORTH)
        // TODO: root.add(crearWest(),   BorderLayout.WEST)
        // TODO: root.add(crearCentro(), BorderLayout.CENTER)
        // TODO: root.add(crearSouth(),  BorderLayout.SOUTH)
    }

    private JPanel crearNorth() {
        // TODO: FlowLayout(LEFT, 16, 8)
        // TODO: MatteBorder(0, 0, 2, 0, azul)   ← RN-R05.1
        // TODO: JLabel título
        // TODO: Box.createHorizontalStrut(20)
        // TODO: tarjetaStat("Total Productos", "8",  azul)
        // TODO: tarjetaStat("Total Clientes",  "142", verde)
        // TODO: return panel
        return new JPanel(); // reemplazar
    }

    /** Tarjeta pequeña con valor estadístico y LineBorder de color. */
    private JPanel tarjetaStat(String etiqueta, String valor, Color color) {
        // TODO: new JPanel(new BorderLayout(4, 0))
        // TODO: CompoundBorder: LineBorder(color, 2) + EmptyBorder(6,14,6,14)
        // TODO: JLabel valor  → fuente Segoe UI Bold 22, color dado
        // TODO: JLabel etiqueta → fuente Segoe UI Plain 11, gris
        // TODO: return tarjeta
        return new JPanel(); // reemplazar
    }

    private JPanel crearWest() {
        // TODO: new JPanel(); setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS))
        //       ← BoxLayout requerido por test T06.4
        // TODO: setBackground(new Color(245, 247, 252))
        // TODO: setPreferredSize(new Dimension(170, 0))
        // TODO: CompoundBorder: TitledBorder("Módulos", EtchedBorder, azul) + EmptyBorder
        //       ← RN-R05.5
        // TODO: Para cada item {"📦 Productos","👤 Clientes","📈 Reportes","⚙ Config"}:
        //         JButton; setAlignmentX(LEFT); setMaximumSize(MAX, 36); etc.
        // TODO: panel.add(Box.createVerticalGlue())
        // TODO: return panel
        return new JPanel(); // reemplazar
    }

    private JSplitPane crearCentro() {
        // TODO: new JSplitPane(HORIZONTAL_SPLIT, crearTabbedPane(), crearFormularioRapido())
        // TODO: setDividerLocation(520)
        // TODO: setResizeWeight(0.70)   ← RN-R05.4, lo verifica test T06.2
        // TODO: setOneTouchExpandable(true)
        // TODO: return split
        return new JSplitPane(); // reemplazar
    }

    private JTabbedPane crearTabbedPane() {
        // TODO: new JTabbedPane(JTabbedPane.TOP)
        // TODO: addTab("Resumen",   null, crearPestanaResumen(),   "Estadísticas rápidas")
        // TODO: addTab("Productos", null, crearPestanaProductos(), "Tabla de productos")
        // TODO: addTab("Actividad", null, crearPestanaActividad(), "Registro de actividad")
        //       ← 3 pestañas requeridas por test T06.3
        // TODO: return tabs
        return new JTabbedPane(); // reemplazar
    }

    private JPanel crearPestanaResumen() {
        // TODO: GridLayout(2, 3, 8, 8)   ← RN-R05.3
        // TODO: EmptyBorder(10,10,10,10)
        // TODO: 6 tarjetas con EtchedBorder(LOWERED)  ← RN-R05.3
        //       Datos: "Ventas Hoy"/$1,240 | "Stock Bajo"/3 | "Clientes"/142
        //              "Pedidos"/7         | "Devoluciones"/2| "Ganancia Mes"/$18,420
        //       Cada tarjeta: BorderLayout; valor en CENTER (negrita azul); etiqueta en SOUTH
        // TODO: return panel
        return new JPanel(); // reemplazar
    }

    private JPanel crearPestanaProductos() {
        // TODO: BorderLayout + EmptyBorder
        // TODO: JTable con columnas: Nombre, Categoría, Precio, Stock
        //       4 filas de datos de ejemplo en JScrollPane
        // TODO: return panel
        return new JPanel(); // reemplazar
    }

    private JPanel crearPestanaActividad() {
        // TODO: BorderLayout + EmptyBorder
        // TODO: JTextArea no editable con 5 líneas de log de ejemplo
        //       en JScrollPane
        // TODO: return panel
        return new JPanel(); // reemplazar
    }

    private JPanel crearFormularioRapido() {
        // TODO: new JPanel(new GridBagLayout())
        // TODO: CompoundBorder: TitledBorder("Agregar Producto Rápido") + EmptyBorder ← RN-R05.5
        // TODO: GridBagConstraints; 3 filas: Nombre, Precio $, Stock (etiqueta + JTextField)
        // TODO: Fila 3: JButton "Agregar" alineado a la derecha (anchor=EAST)
        // TODO: Fila 4: relleno vertical (fill=BOTH, weighty=1)
        // TODO: return panel
        return new JPanel(); // reemplazar
    }

    private JPanel crearSouth() {
        // TODO: FlowLayout(RIGHT, 10, 6)
        // TODO: MatteBorder(1, 0, 0, 0, ...)   ← RN-R05.1
        // TODO: JLabel "Estado: Sistema activo" (gris)
        // TODO: Box.createHorizontalStrut(20)
        // TODO: JButton "Exportar"   (gris)
        // TODO: JButton "Actualizar" (azul)
        // TODO: return panel
        return new JPanel(); // reemplazar
    }

    // ── Helper: botón estándar ────────────────────────────────────
    // NO modificar.
    private JButton boton(String label, Color color) {
        JButton btn = new JButton(label);
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        btn.setBackground(color); btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false); btn.setBorderPainted(false); btn.setOpaque(true);
        btn.setBorder(BorderFactory.createEmptyBorder(6, 16, 6, 16));
        return btn;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DashboardIntegrado().setVisible(true));
    }
}
