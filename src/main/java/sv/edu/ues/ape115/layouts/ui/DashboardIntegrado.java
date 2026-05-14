package sv.edu.ues.ape115.layouts.ui;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
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
 * @author (Kelvin Jair Zacarias Candray)
 */
public class DashboardIntegrado extends JFrame {

    private final Color AZUL = new Color(25, 118, 210);
    private final Color VERDE = new Color(56, 142, 60);

    public DashboardIntegrado() {
        super("R05 — Dashboard Integrado — Todos los Layouts");
        // TODO: construirUI()
        // TODO: setSize(1100, 680)
        // TODO: setMinimumSize(new Dimension(900, 550))   ← obligatorio, lo verifica test T06.5
        // TODO: setDefaultCloseOperation(EXIT_ON_CLOSE)
        // TODO: setLocationRelativeTo(null)

        construirUI();
        setSize(1100, 680);
        setMinimumSize(new Dimension(900, 550)); // Obligatorio por T06.5
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void construirUI() {
        // TODO: JPanel root = new JPanel(new BorderLayout(8, 8))
        // TODO: root.setBorder(EmptyBorder(8,10,8,10))
        // TODO: setContentPane(root)
        // TODO: root.add(crearNorth(),  BorderLayout.NORTH)
        // TODO: root.add(crearWest(),   BorderLayout.WEST)
        // TODO: root.add(crearCentro(), BorderLayout.CENTER)
        // TODO: root.add(crearSouth(),  BorderLayout.SOUTH)

        JPanel root = new JPanel(new BorderLayout(8, 8));
        root.setBorder(BorderFactory.createEmptyBorder(8, 10, 8, 10));
        setContentPane(root);

        root.add(crearNorth(),  BorderLayout.NORTH);
        root.add(crearWest(),   BorderLayout.WEST);
        root.add(crearCentro(), BorderLayout.CENTER);
        root.add(crearSouth(),  BorderLayout.SOUTH);
    }

    private JPanel crearNorth() {
        // TODO: FlowLayout(LEFT, 16, 8)
        // TODO: MatteBorder(0, 0, 2, 0, azul)   ← RN-R05.1
        // TODO: JLabel título
        // TODO: Box.createHorizontalStrut(20)
        // TODO: tarjetaStat("Total Productos", "8",  azul)
        // TODO: tarjetaStat("Total Clientes",  "142", verde)
        // TODO: return panel

        JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT, 16, 8));
        // RN-R05.1: Borde inferior azul
        p.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, AZUL));

        JLabel lblTitulo = new JLabel("PANEL DE CONTROL");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 18));

        p.add(lblTitulo);
        p.add(Box.createHorizontalStrut(20));
        p.add(tarjetaStat("Total Productos", "8", AZUL));
        p.add(tarjetaStat("Total Clientes", "142", VERDE));
        return p;
    }

    /** Tarjeta pequeña con valor estadístico y LineBorder de color. */
    private JPanel tarjetaStat(String etiqueta, String valor, Color color) {
        // TODO: new JPanel(new BorderLayout(4, 0))
        // TODO: CompoundBorder: LineBorder(color, 2) + EmptyBorder(6,14,6,14)
        // TODO: JLabel valor  → fuente Segoe UI Bold 22, color dado
        // TODO: JLabel etiqueta → fuente Segoe UI Plain 11, gris
        // TODO: return tarjeta
        JPanel tarjeta = new JPanel(new BorderLayout(4, 0));

        tarjeta.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(color, 2),
                BorderFactory.createEmptyBorder(6, 14, 6, 14)
        ));

        JLabel lblValor = new JLabel(valor);
        lblValor.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblValor.setForeground(color);

        JLabel lblEtiq = new JLabel(etiqueta);
        lblEtiq.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        lblEtiq.setForeground(Color.GRAY);

        tarjeta.add(lblValor, BorderLayout.CENTER);
        tarjeta.add(lblEtiq, BorderLayout.SOUTH);

        return tarjeta;

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

        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setBackground(new Color(245, 247, 252));
        p.setPreferredSize(new Dimension(170, 0));

        p.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(
                        BorderFactory.createEtchedBorder(), "Módulos",
                        TitledBorder.LEFT, TitledBorder.TOP,
                        new Font("Segoe UI", Font.BOLD, 12), AZUL),
                BorderFactory.createEmptyBorder(8, 8, 8, 8)
        ));

        String[] items = {"📦 Productos", "👤 Clientes", "📈 Reportes", "⚙ Config"};
        for (String item : items) {
            JButton btn = new JButton(item);
            btn.setAlignmentX(Component.LEFT_ALIGNMENT);
            btn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 36));
            btn.setFocusPainted(false);
            btn.setContentAreaFilled(false);
            btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            p.add(btn);
            p.add(Box.createVerticalStrut(6));
        }

        p.add(Box.createVerticalGlue());
        return p;
    }

    private JSplitPane crearCentro() {
        // TODO: new JSplitPane(HORIZONTAL_SPLIT, crearTabbedPane(), crearFormularioRapido())
        // TODO: setDividerLocation(520)
        // TODO: setResizeWeight(0.70)   ← RN-R05.4, lo verifica test T06.2
        // TODO: setOneTouchExpandable(true)
        // TODO: return split

        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, crearTabbedPane(), crearFormularioRapido());
        split.setDividerLocation(520);
        split.setResizeWeight(0.70); // RN-R05.4 y T06.2
        split.setOneTouchExpandable(true);
        return split;
    }

    private JTabbedPane crearTabbedPane() {
        // TODO: new JTabbedPane(JTabbedPane.TOP)
        // TODO: addTab("Resumen",   null, crearPestanaResumen(),   "Estadísticas rápidas")
        // TODO: addTab("Productos", null, crearPestanaProductos(), "Tabla de productos")
        // TODO: addTab("Actividad", null, crearPestanaActividad(), "Registro de actividad")
        //       ← 3 pestañas requeridas por test T06.3
        // TODO: return tabs

        JTabbedPane tabs = new JTabbedPane(JTabbedPane.TOP);
        tabs.addTab("Resumen", null, crearPestanaResumen(), "Estadísticas rápidas");
        tabs.addTab("Productos", null, crearPestanaProductos(), "Tabla de productos");
        tabs.addTab("Actividad", null, crearPestanaActividad(), "Registro de actividad");
        return tabs;
    }

    private JPanel crearPestanaResumen() {
        // TODO: GridLayout(2, 3, 8, 8)   ← RN-R05.3
        // TODO: EmptyBorder(10,10,10,10)
        // TODO: 6 tarjetas con EtchedBorder(LOWERED)  ← RN-R05.3
        //       Datos: "Ventas Hoy"/$1,240 | "Stock Bajo"/3 | "Clientes"/142
        //              "Pedidos"/7         | "Devoluciones"/2| "Ganancia Mes"/$18,420
        //       Cada tarjeta: BorderLayout; valor en CENTER (negrita azul); etiqueta en SOUTH
        // TODO: return panel

        // RN-R05.3: GridLayout 2x3
        JPanel p = new JPanel(new GridLayout(2, 3, 8, 8));
        p.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        String[][] datos = {
                {"Ventas Hoy", "$1,240"}, {"Stock Bajo", "3"}, {"Clientes", "142"},
                {"Pedidos", "7"}, {"Devoluciones", "2"}, {"Ganancia Mes", "$18,420"}
        };

        for (String[] d : datos) {
            JPanel card = new JPanel(new BorderLayout());
            // RN-R05.3: EtchedBorder requerido
            card.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));

            JLabel v = new JLabel(d[1], SwingConstants.CENTER);
            v.setFont(new Font("Segoe UI", Font.BOLD, 16));
            v.setForeground(AZUL);

            JLabel e = new JLabel(d[0], SwingConstants.CENTER);
            e.setFont(new Font("Segoe UI", Font.PLAIN, 11));

            card.add(v, BorderLayout.CENTER);
            card.add(e, BorderLayout.SOUTH);
            p.add(card);
        }
        return p;
    }

    private JPanel crearPestanaProductos() {
        // TODO: BorderLayout + EmptyBorder
        // TODO: JTable con columnas: Nombre, Categoría, Precio, Stock
        //       4 filas de datos de ejemplo en JScrollPane
        // TODO: return panel

        JPanel p = new JPanel(new BorderLayout());
        p.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        String[] cols = {"Nombre", "Categoría", "Precio", "Stock"};
        Object[][] data = {
                {"Laptop Dell", "Electrónica", "$1200", "15"},
                {"Silla Oficina", "Hogar", "$220", "12"},
                {"Mouse RGB", "Accesorios", "$60", "50"},
                {"Monitor 4K", "Electrónica", "$450", "8"}
        };

        JTable tabla = new JTable(new DefaultTableModel(data, cols));
        p.add(new JScrollPane(tabla), BorderLayout.CENTER);
        return p;
    }

    private JPanel crearPestanaActividad() {
        // TODO: BorderLayout + EmptyBorder
        // TODO: JTextArea no editable con 5 líneas de log de ejemplo
        //       en JScrollPane
        // TODO: return panel

        JPanel p = new JPanel(new BorderLayout());
        p.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        JTextArea txtLog = new JTextArea();
        txtLog.setEditable(false);
        txtLog.setText("[10:15] Usuario Admin inició sesión\n" +
                "[10:20] Se agregó producto: Mouse RGB\n" +
                "[11:05] Venta realizada #4052\n" +
                "[11:30] Stock actualizado: Silla Oficina\n" +
                "[12:00] Reporte mensual generado");

        p.add(new JScrollPane(txtLog), BorderLayout.CENTER);
        return p;
    }

    private JPanel crearFormularioRapido() {
        // TODO: new JPanel(new GridBagLayout())
        // TODO: CompoundBorder: TitledBorder("Agregar Producto Rápido") + EmptyBorder ← RN-R05.5
        // TODO: GridBagConstraints; 3 filas: Nombre, Precio $, Stock (etiqueta + JTextField)
        // TODO: Fila 3: JButton "Agregar" alineado a la derecha (anchor=EAST)
        // TODO: Fila 4: relleno vertical (fill=BOTH, weighty=1)
        // TODO: return panel

        JPanel p = new JPanel(new GridBagLayout());
        p.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Agregar Producto Rápido"),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(4, 4, 4, 4);
        g.fill = GridBagConstraints.HORIZONTAL;

        String[] labels = {"Nombre:", "Precio $:", "Stock:"};
        for (int i = 0; i < labels.length; i++) {
            g.gridy = i; g.gridx = 0; g.weightx = 0;
            p.add(new JLabel(labels[i]), g);
            g.gridx = 1; g.weightx = 1;
            p.add(new JTextField(10), g);
        }

        g.gridy = 3; g.gridx = 1; g.fill = GridBagConstraints.NONE; g.anchor = GridBagConstraints.EAST;
        p.add(boton("Agregar", AZUL), g);

        g.gridy = 4; g.weighty = 1; g.fill = GridBagConstraints.BOTH;
        p.add(new JLabel(""), g);

        return p;
    }

    private JPanel crearSouth() {
        // TODO: FlowLayout(RIGHT, 10, 6)
        // TODO: MatteBorder(1, 0, 0, 0, ...)   ← RN-R05.1
        // TODO: JLabel "Estado: Sistema activo" (gris)
        // TODO: Box.createHorizontalStrut(20)
        // TODO: JButton "Exportar"   (gris)
        // TODO: JButton "Actualizar" (azul)
        // TODO: return panel

        JPanel p = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 6));
        // RN-R05.1: MatteBorder superior (separador)
        p.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.LIGHT_GRAY));

        JLabel lblStatus = new JLabel("Estado: Sistema activo");
        lblStatus.setForeground(Color.GRAY);
        p.add(lblStatus);

        p.add(Box.createHorizontalStrut(20));
        p.add(boton("Exportar", Color.GRAY));
        p.add(boton("Actualizar", AZUL));

        return p;

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
