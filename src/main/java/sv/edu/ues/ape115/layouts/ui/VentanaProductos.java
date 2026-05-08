package sv.edu.ues.ape115.layouts.ui;

import sv.edu.ues.ape115.layouts.model.Producto;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Ejemplo paso a paso — G0301 Layout Managers y Bordes.
 *
 * Sigue los pasos de la guía (sección 2) para completar cada método.
 *
 * Layouts que debes usar:
 *   - BorderLayout  → construirUI() (estructura exterior del JFrame)
 *   - FlowLayout    → crearNorth()  y  crearSouth()
 *   - BoxLayout     → crearWest()   (panel de categorías)
 *   - GridBagLayout → crearFormulario() (campos del formulario)
 *   - GridLayout    → crearDetalle()    (pestaña Datos del JTabbedPane)
 *   - JSplitPane    → crearCentro()
 *   - JTabbedPane   → crearDetalle()
 *
 * Bordes que debes usar:
 *   - LineBorder    → crearNorth()
 *   - CompoundBorder (TitledBorder + EmptyBorder) → crearWest(), crearFormulario(), crearDetalle()
 *   - MatteBorder   → crearSouth()
 *
 * @author (escribe tu nombre aquí)
 */
public class VentanaProductos extends JFrame {

    // ── Datos de la aplicación ────────────────────────────────────
    private final List<Producto>             productos = new ArrayList<>();
    private final DefaultListModel<Producto> modelo    = new DefaultListModel<>();

    // ── Componentes del formulario (izquierda del JSplitPane) ─────
    private JList<Producto>  lstProductos;
    private JTextField       txtNombre;
    private JTextField       txtPrecio;
    private JTextField       txtStock;
    private JComboBox<String> cboCat;

    // ── Componentes del detalle (derecha del JSplitPane) ──────────
    private JLabel   lblDNombre;
    private JLabel   lblDPrecio;
    private JLabel   lblDStock;
    private JLabel   lblDCat;
    private JTextArea txDesc;

    // ─────────────────────────────────────────────────────────────
    public VentanaProductos() {
        super("G0301 — Layout Managers y Bordes en Java Swing");
        cargarDatos();
        construirUI();
        setSize(1000, 660);
        setMinimumSize(new Dimension(800, 540));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    // ─────────────────────────────────────────────────────────────
    // PASO 3 (Guía §2.4): Estructura principal BorderLayout
    // ─────────────────────────────────────────────────────────────
    private void construirUI() {
        // TODO: Crear JPanel root con BorderLayout(6,6)
        // TODO: Aplicar EmptyBorder(10,12,10,12) como padding exterior
        // TODO: setContentPane(root)
        // TODO: Agregar crearNorth()     → BorderLayout.NORTH
        // TODO: Agregar crearWest()      → BorderLayout.WEST
        // TODO: Agregar crearCentro()    → BorderLayout.CENTER
        // TODO: Agregar crearSouth()     → BorderLayout.SOUTH
    }

    // ─────────────────────────────────────────────────────────────
    // PASO 4 (Guía §2.5): Panel NORTH — FlowLayout + LineBorder
    // ─────────────────────────────────────────────────────────────
    private JPanel crearNorth() {
        // TODO: new JPanel(new FlowLayout(FlowLayout.LEFT, 12, 6))
        // TODO: setBorder(LineBorder azul institucional, grosor 2)
        // TODO: Crear JLabel con el título de la aplicación
        //       fuente Segoe UI Bold 17, color azul institucional
        // TODO: Crear JTextField txtBuscar columnas=18
        // TODO: addActionListener en txtBuscar → llamar filtrar(texto)
        // TODO: panel.add(lbl), add(separador), add("Buscar:"), add(txtBuscar)
        // TODO: return panel
        return new JPanel(); // reemplazar
    }

    // ─────────────────────────────────────────────────────────────
    // PASO 5 (Guía §2.6): Panel WEST — BoxLayout(Y_AXIS) + CompoundBorder
    // ─────────────────────────────────────────────────────────────
    private JPanel crearWest() {
        // TODO: new JPanel() + setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS))
        // TODO: setBorder CompoundBorder:
        //         exterior = TitledBorder("Categorías", EtchedBorder.LOWERED, azul)
        //         interior = EmptyBorder(6, 8, 6, 8)
        // TODO: setPreferredSize(new Dimension(165, 0))
        // TODO: for cada categoría en {"Todas","Electrónica","Hogar","Accesorios"}:
        //         JButton btn; setAlignmentX(LEFT); setMaximumSize(MAX, 34)
        //         addActionListener → filtrarCategoria(cat)
        //         panel.add(btn); panel.add(Box.createVerticalStrut(4))
        // TODO: panel.add(Box.createVerticalGlue())
        // TODO: return panel
        return new JPanel(); // reemplazar
    }

    // ─────────────────────────────────────────────────────────────
    // PASO 6 (Guía §2.7): Centro — JSplitPane
    // ─────────────────────────────────────────────────────────────
    private JSplitPane crearCentro() {
        // TODO: new JSplitPane(HORIZONTAL_SPLIT, crearFormulario(), crearDetalle())
        // TODO: setDividerLocation(340)
        // TODO: setResizeWeight(0.40)
        // TODO: setOneTouchExpandable(true)
        // TODO: return splitPane
        return new JSplitPane(); // reemplazar
    }

    // ─────────────────────────────────────────────────────────────
    // PASO 6 cont. — Formulario con GridBagLayout
    // ─────────────────────────────────────────────────────────────
    private JPanel crearFormulario() {
        // TODO: new JPanel(new GridBagLayout())
        // TODO: setBorder CompoundBorder:
        //         TitledBorder("Datos del Producto", EtchedBorder, azul)
        //         + EmptyBorder(8, 10, 8, 10)

        // TODO: GridBagConstraints g; insets=Insets(5,6,5,6); anchor=WEST

        // TODO — Fila 0: etiqueta "Nombre *:" + txtNombre(span 3 cols)
        //   g.gridx=0; g.gridy=0; fill=NONE; weightx=0  → add etiqueta
        //   g.gridx=1; gridwidth=3; fill=HORIZONTAL; weightx=1 → add txtNombre
        //   resetear: gridwidth=1; weightx=0

        // TODO — Fila 1: "Precio $*:" (col 0) + txtPrecio (col 1) +
        //                "Stock *:" (col 2) + txtStock (col 3)

        // TODO — Fila 2: "Categoría *:" + cboCat(span 3)

        // TODO — Fila 3: lstProductos en JScrollPane (span 4 cols, fill=BOTH, weighty=1)
        //   lstProductos = new JList<>(modelo)
        //   addListSelectionListener → cargarDetalle(selectedValue) si !isAdjusting

        // TODO: return panel
        return new JPanel(); // reemplazar
    }

    // ─────────────────────────────────────────────────────────────
    // PASO 7 (Guía §2.8): Detalle — JTabbedPane
    // ─────────────────────────────────────────────────────────────
    private JTabbedPane crearDetalle() {
        // TODO: new JTabbedPane(JTabbedPane.TOP)

        // TODO — Pestaña 1 "Datos":
        //   JPanel pDatos = new JPanel(new GridLayout(4, 2, 8, 8))
        //   pDatos.setBorder(EmptyBorder(12,12,12,12))
        //   Agregar 4 pares etiqueta/JLabel para: Nombre, Precio, Stock, Categoría
        //   tabs.addTab("Datos", null, pDatos, "Datos generales")

        // TODO — Pestaña 2 "Descripción":
        //   txDesc = new JTextArea(); setLineWrap(true); setWrapStyleWord(true); setEditable(false)
        //   tabs.addTab("Descripción", null, new JScrollPane(txDesc), "Descripción completa")

        // TODO — setBorder del JTabbedPane:
        //   CompoundBorder: TitledBorder("Detalle del Producto", EtchedBorder, azul)
        //                   + EmptyBorder(4, 8, 4, 8)

        // TODO: return tabs
        return new JTabbedPane(); // reemplazar
    }

    // ─────────────────────────────────────────────────────────────
    // PASO 8 (Guía §2.9): Panel SOUTH — FlowLayout(RIGHT) + MatteBorder
    // ─────────────────────────────────────────────────────────────
    private JPanel crearSouth() {
        // TODO: new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 6))
        // TODO: setBorder(MatteBorder(1,0,0,0, Color(200,210,230)))  ← solo borde superior
        // TODO: Agregar botones (usar crearBoton):
        //         "Limpiar"  → Color(100,100,100)
        //         "Eliminar" → Color(198,40,40)
        //         "+ Nuevo"  → Color(25,118,210)
        //         "Guardar"  → Color(46,125,50)
        // TODO: Conectar acciones de los botones (limpiar, eliminar, limpiar, guardar)
        // TODO: return panel
        return new JPanel(); // reemplazar
    }

    // ─────────────────────────────────────────────────────────────
    // PASO 9 (Guía §2.10): Datos de muestra y lógica
    // ─────────────────────────────────────────────────────────────
    private void cargarDatos() {
        // TODO: Agregar 8 productos a la lista usando new Producto(nombre, cat, precio, stock, desc)
        // TODO: Al final: productos.forEach(modelo::addElement)
    }

    private void cargarDetalle(Producto p) {
        // TODO: Si p es null, retornar
        // TODO: Actualizar los 4 JLabel del panel de detalle
        //       lblDNombre.setText(p.getNombre()), etc.
        // TODO: Actualizar también los campos del formulario
        //       txtNombre.setText(p.getNombre()), etc.
    }

    private void filtrar(String q) {
        // TODO: modelo.clear()
        // TODO: Para cada Producto en productos, agregar al modelo si
        //       q está vacío O el nombre/categoría contienen q (ignoreCase)
    }

    private void filtrarCategoria(String cat) {
        // TODO: modelo.clear()
        // TODO: Agregar los Productos cuya categoría coincida (o cat=="Todas")
    }

    private void guardar() {
        // TODO: Validar que txtNombre, txtPrecio, txtStock no estén vacíos
        //       Si falta alguno: JOptionPane.WARNING_MESSAGE
        // TODO: JOptionPane.INFORMATION_MESSAGE indicando éxito
        // TODO: limpiar()
    }

    private void eliminar() {
        // TODO: Obtener el producto seleccionado en lstProductos
        //       Si null: JOptionPane informativo
        // TODO: JOptionPane.showConfirmDialog para confirmar
        //       Si YES: productos.remove(sel), modelo.removeElement(sel), limpiar()
    }

    private void limpiar() {
        // TODO: Limpiar txtNombre, txtPrecio, txtStock
        // TODO: cboCat.setSelectedIndex(0)
        // TODO: lstProductos.clearSelection()
        // TODO: Resetear los 4 JLabel del detalle a "—"
        // TODO: txDesc.setText("")
        // TODO: txtNombre.requestFocus()
    }

    // ─────────────────────────────────────────────────────────────
    // Helper: botón con estilo unificado del laboratorio
    // NO modificar este método.
    // ─────────────────────────────────────────────────────────────
    private JButton crearBoton(String label, Color color) {
        JButton btn = new JButton(label);
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setOpaque(true);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setBorder(BorderFactory.createEmptyBorder(7, 18, 7, 18));
        return btn;
    }
}
