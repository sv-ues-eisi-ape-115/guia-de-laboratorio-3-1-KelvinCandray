package sv.edu.ues.ape115.layouts.ui;

import sv.edu.ues.ape115.layouts.model.Producto;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Ejemplo completo — G0301 Layout Managers y Bordes.
 *
 * Layouts demostrados:
 *   BorderLayout  — estructura exterior del JFrame (raíz)
 *   FlowLayout    — paneles NORTH y SOUTH
 *   BoxLayout     — panel WEST (categorías)
 *   GridBagLayout — formulario de captura (izquierda del JSplitPane)
 *   GridLayout    — pestaña Datos del panel de detalle
 *   JSplitPane    — divide formulario / detalle
 *   JTabbedPane   — organiza el panel de detalle en pestañas
 *
 * Bordes demostrados:
 *   LineBorder    — barra de título (NORTH)
 *   CompoundBorder (TitledBorder + EmptyBorder) — todas las secciones
 *   MatteBorder   — separador del SOUTH
 *
 * @author APE 115 — UES — G0301
 */
public class VentanaProductos extends JFrame {

    // ── Datos ─────────────────────────────────────────────────────
    private final List<Producto>         productos = new ArrayList<>();
    private final DefaultListModel<Producto> modelo = new DefaultListModel<>();

    // ── Formulario (izquierda del JSplitPane) ─────────────────────
    private JList<Producto>  lstProductos;
    private JTextField       txtNombre, txtPrecio, txtStock;
    private JComboBox<String> cboCat;

    // ── Detalle (derecha del JSplitPane) ──────────────────────────
    private JLabel   lblDNombre, lblDPrecio, lblDStock, lblDCat;
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

    // ── Construcción de la UI ─────────────────────────────────────
    private void construirUI() {
        // ── Layout raíz: BorderLayout con gaps de 6 px ────────────
        JPanel root = new JPanel(new BorderLayout(6, 6));
        root.setBorder(BorderFactory.createEmptyBorder(10, 12, 10, 12));
        setContentPane(root);

        root.add(crearNorth(),  BorderLayout.NORTH);
        root.add(crearWest(),   BorderLayout.WEST);
        root.add(crearCentro(), BorderLayout.CENTER);
        root.add(crearSouth(),  BorderLayout.SOUTH);
    }

    // ══════════════════════════════════════════════════════════════
    // NORTH — FlowLayout(LEFT) + LineBorder
    // ══════════════════════════════════════════════════════════════
    private JPanel crearNorth() {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT, 12, 6));

        // LineBorder: línea simple de 2 px en el color institucional
        p.setBorder(BorderFactory.createLineBorder(new Color(25, 118, 210), 2));

        JLabel lbl = new JLabel("📦  Gestión de Productos — APE 115 G0301");
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 17));
        lbl.setForeground(new Color(25, 118, 210));

        JTextField txtBuscar = new JTextField(18);
        txtBuscar.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtBuscar.setToolTipText("Escribe y presiona Enter para buscar");
        txtBuscar.addActionListener(e -> filtrar(txtBuscar.getText()));

        p.add(lbl);
        p.add(Box.createHorizontalStrut(30));
        p.add(new JLabel("Buscar: "));
        p.add(txtBuscar);
        return p;
    }

    // ══════════════════════════════════════════════════════════════
    // WEST — BoxLayout(Y_AXIS) + CompoundBorder
    // ══════════════════════════════════════════════════════════════
    private JPanel crearWest() {
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));

        // CompoundBorder = TitledBorder (exterior) + EmptyBorder (padding interior)
        p.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(EtchedBorder.LOWERED),
                "Categorías",
                TitledBorder.LEFT, TitledBorder.TOP,
                new Font("Segoe UI", Font.BOLD, 12),
                new Color(25, 118, 210)
            ),
            BorderFactory.createEmptyBorder(6, 8, 6, 8)
        ));
        p.setPreferredSize(new Dimension(165, 0));

        for (String cat : new String[]{"Todas", "Electrónica", "Hogar", "Accesorios"}) {
            JButton btn = new JButton(cat);
            btn.setAlignmentX(Component.LEFT_ALIGNMENT);
            btn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 34));
            btn.setFont(new Font("Segoe UI", Font.PLAIN, 13));
            btn.setFocusPainted(false);
            btn.addActionListener(e -> filtrarCategoria(cat));
            p.add(btn);
            p.add(Box.createVerticalStrut(4));
        }
        p.add(Box.createVerticalGlue()); // empuja los botones hacia arriba
        return p;
    }

    // ══════════════════════════════════════════════════════════════
    // CENTER — JSplitPane ( formulario | detalle-tabs )
    // ══════════════════════════════════════════════════════════════
    private JSplitPane crearCentro() {
        JSplitPane sp = new JSplitPane(
            JSplitPane.HORIZONTAL_SPLIT,
            crearFormulario(),
            crearDetalle()
        );
        sp.setDividerLocation(340);
        sp.setResizeWeight(0.40);           // 40 % para el formulario
        sp.setOneTouchExpandable(true);     // botones ◀▶
        sp.setContinuousLayout(true);
        return sp;
    }

    // ── Formulario con GridBagLayout ──────────────────────────────
    private JPanel crearFormulario() {
        JPanel p = new JPanel(new GridBagLayout());
        p.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(EtchedBorder.LOWERED),
                "Datos del Producto",
                TitledBorder.LEFT, TitledBorder.TOP,
                new Font("Segoe UI", Font.BOLD, 12),
                new Color(25, 118, 210)
            ),
            BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));

        GridBagConstraints g = new GridBagConstraints();
        g.insets  = new Insets(5, 6, 5, 6);
        g.anchor  = GridBagConstraints.WEST;

        // ── Fila 0: Nombre (campo abarca col 1-3) ─────────────────
        g.gridx = 0; g.gridy = 0; g.fill = GridBagConstraints.NONE; g.weightx = 0;
        p.add(etq("Nombre *:"), g);
        g.gridx = 1; g.gridwidth = 3;
        g.fill = GridBagConstraints.HORIZONTAL; g.weightx = 1;
        p.add(txtNombre = new JTextField(20), g);
        g.gridwidth = 1; g.weightx = 0;   // SIEMPRE resetear

        // ── Fila 1: Precio (col 1) | Stock (col 2-3) ──────────────
        g.gridx = 0; g.gridy = 1; g.fill = GridBagConstraints.NONE;
        p.add(etq("Precio $*:"), g);
        g.gridx = 1; g.fill = GridBagConstraints.HORIZONTAL; g.weightx = 1;
        p.add(txtPrecio = new JTextField(8), g);
        g.gridx = 2; g.fill = GridBagConstraints.NONE; g.weightx = 0;
        p.add(etq("Stock *:"), g);
        g.gridx = 3; g.fill = GridBagConstraints.HORIZONTAL; g.weightx = 0.5;
        p.add(txtStock = new JTextField(5), g);
        g.weightx = 0;

        // ── Fila 2: Categoría (JComboBox, span 3 cols) ────────────
        g.gridx = 0; g.gridy = 2; g.fill = GridBagConstraints.NONE;
        p.add(etq("Categoría *:"), g);
        g.gridx = 1; g.gridwidth = 3; g.fill = GridBagConstraints.HORIZONTAL;
        cboCat = new JComboBox<>(new String[]{"Electrónica", "Hogar", "Accesorios"});
        cboCat.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        p.add(cboCat, g);
        g.gridwidth = 1;

        // ── Fila 3: JList con scroll (span 4 cols, crece vertical) ─
        g.gridx = 0; g.gridy = 3; g.gridwidth = 4;
        g.fill = GridBagConstraints.BOTH; g.weighty = 1;
        lstProductos = new JList<>(modelo);
        lstProductos.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lstProductos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lstProductos.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) cargarDetalle(lstProductos.getSelectedValue());
        });

        JScrollPane scroll = new JScrollPane(lstProductos);
        scroll.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(200, 210, 230), 1),
                "Lista de Productos",
                TitledBorder.LEFT, TitledBorder.TOP,
                new Font("Segoe UI", Font.BOLD, 11),
                new Color(100, 120, 160)
            ),
            BorderFactory.createEmptyBorder(2, 4, 2, 4)
        ));
        p.add(scroll, g);
        return p;
    }

    // ── JTabbedPane para el detalle ───────────────────────────────
    private JTabbedPane crearDetalle() {
        JTabbedPane tabs = new JTabbedPane(JTabbedPane.TOP);

        // ── Pestaña 1: GridLayout 4 × 2 ──────────────────────────
        JPanel pDatos = new JPanel(new GridLayout(4, 2, 8, 8));
        pDatos.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        Font fL = new Font("Segoe UI", Font.BOLD, 13);
        Font fV = new Font("Segoe UI", Font.PLAIN, 13);

        pDatos.add(etqF("Nombre:",    fL, new Color(60, 80, 120)));
        pDatos.add(lblDNombre = new JLabel("—")); lblDNombre.setFont(fV);
        pDatos.add(etqF("Precio $:",  fL, new Color(60, 80, 120)));
        pDatos.add(lblDPrecio = new JLabel("—")); lblDPrecio.setFont(fV);
        pDatos.add(etqF("Stock:",     fL, new Color(60, 80, 120)));
        pDatos.add(lblDStock  = new JLabel("—")); lblDStock.setFont(fV);
        pDatos.add(etqF("Categoría:", fL, new Color(60, 80, 120)));
        pDatos.add(lblDCat    = new JLabel("—")); lblDCat.setFont(fV);

        tabs.addTab("Datos",       null, pDatos,
                    "Datos generales del producto seleccionado");

        // ── Pestaña 2: JTextArea en JScrollPane ───────────────────
        txDesc = new JTextArea();
        txDesc.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txDesc.setLineWrap(true);
        txDesc.setWrapStyleWord(true);
        txDesc.setEditable(false);
        txDesc.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        tabs.addTab("Descripción", null, new JScrollPane(txDesc),
                    "Descripción completa del producto");

        // CompoundBorder en el JTabbedPane
        tabs.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(EtchedBorder.LOWERED),
                "Detalle del Producto",
                TitledBorder.LEFT, TitledBorder.TOP,
                new Font("Segoe UI", Font.BOLD, 12),
                new Color(25, 118, 210)
            ),
            BorderFactory.createEmptyBorder(4, 8, 4, 8)
        ));
        return tabs;
    }

    // ══════════════════════════════════════════════════════════════
    // SOUTH — FlowLayout(RIGHT) + MatteBorder
    // ══════════════════════════════════════════════════════════════
    private JPanel crearSouth() {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 6));

        // MatteBorder: línea solo en el borde superior (separador visual)
        p.setBorder(BorderFactory.createMatteBorder(
            1, 0, 0, 0, new Color(200, 210, 230)));

        JButton btnLimpiar  = crearBoton("Limpiar",  new Color(100, 100, 100));
        JButton btnEliminar = crearBoton("Eliminar", new Color(198,  40,  40));
        JButton btnNuevo    = crearBoton("+ Nuevo",  new Color( 25, 118, 210));
        JButton btnGuardar  = crearBoton("Guardar",  new Color( 46, 125,  50));

        btnLimpiar.addActionListener(e  -> limpiar());
        btnEliminar.addActionListener(e -> eliminar());
        btnNuevo.addActionListener(e    -> limpiar());
        btnGuardar.addActionListener(e  -> guardar());

        p.add(btnLimpiar);
        p.add(btnEliminar);
        p.add(btnNuevo);
        p.add(btnGuardar);
        return p;
    }

    // ══════════════════════════════════════════════════════════════
    // Lógica de la interfaz
    // ══════════════════════════════════════════════════════════════

    private void cargarDatos() {
        productos.add(new Producto("Laptop Dell",    "Electrónica", 899.99, 12,
            "Laptop Intel Core i7, 16 GB RAM, 512 GB SSD, pantalla 15.6\" FHD."));
        productos.add(new Producto("Monitor LG 24\"","Electrónica", 249.50, 30,
            "Monitor IPS 24 pulgadas, 1920×1080, 75 Hz, HDMI y VGA."));
        productos.add(new Producto("Teclado Mec.",   "Accesorios",   89.00, 50,
            "Teclado mecánico switches Blue, retroiluminación RGB, layout español."));
        productos.add(new Producto("Silla Ergon.",   "Hogar",        320.00,  8,
            "Silla de oficina con soporte lumbar ajustable y ruedas de silicona."));
        productos.add(new Producto("Audífonos Sony", "Electrónica", 299.00, 20,
            "Audífonos inalámbricos con cancelación de ruido, 30 h de batería."));
        productos.add(new Producto("Mochila",        "Accesorios",   75.00, 40,
            "Mochila para laptop 15.6\", compartimiento acolchado, puerto USB."));
        productos.add(new Producto("Lámpara LED",    "Hogar",         45.00, 60,
            "Lámpara LED de escritorio con brillo y temperatura ajustables."));
        productos.add(new Producto("Mouse Logitech", "Accesorios",  109.00, 35,
            "Mouse ergonómico inalámbrico, 7 botones, rueda adaptable."));
        productos.forEach(modelo::addElement);
    }

    private void cargarDetalle(Producto p) {
        if (p == null) return;
        lblDNombre.setText(p.getNombre());
        lblDPrecio.setText(String.format("$ %.2f", p.getPrecio()));
        lblDStock.setText(String.valueOf(p.getStock()));
        lblDCat.setText(p.getCategoria());
        txDesc.setText(p.getDescripcion());

        txtNombre.setText(p.getNombre());
        txtPrecio.setText(String.valueOf(p.getPrecio()));
        txtStock.setText(String.valueOf(p.getStock()));
        cboCat.setSelectedItem(p.getCategoria());
    }

    private void filtrar(String q) {
        modelo.clear();
        String t = q.trim().toLowerCase();
        productos.stream()
            .filter(p -> t.isEmpty()
                      || p.getNombre().toLowerCase().contains(t)
                      || p.getCategoria().toLowerCase().contains(t))
            .forEach(modelo::addElement);
    }

    private void filtrarCategoria(String cat) {
        modelo.clear();
        productos.stream()
            .filter(p -> "Todas".equals(cat) || p.getCategoria().equals(cat))
            .forEach(modelo::addElement);
    }

    private void guardar() {
        if (txtNombre.getText().trim().isEmpty()
         || txtPrecio.getText().trim().isEmpty()
         || txtStock.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Los campos marcados con * son obligatorios.",
                "Campos requeridos", JOptionPane.WARNING_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(this,
            "Producto guardado: " + txtNombre.getText(),
            "Guardado", JOptionPane.INFORMATION_MESSAGE);
        limpiar();
    }

    private void eliminar() {
        Producto sel = lstProductos.getSelectedValue();
        if (sel == null) {
            JOptionPane.showMessageDialog(this, "Selecciona un producto.",
                "Sin selección", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        if (JOptionPane.showConfirmDialog(this,
                "¿Eliminar \"" + sel.getNombre() + "\"?",
                "Confirmar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            productos.remove(sel);
            modelo.removeElement(sel);
            limpiar();
        }
    }

    private void limpiar() {
        txtNombre.setText(""); txtPrecio.setText(""); txtStock.setText("");
        cboCat.setSelectedIndex(0);
        lstProductos.clearSelection();
        lblDNombre.setText("—"); lblDPrecio.setText("—");
        lblDStock.setText("—");  lblDCat.setText("—");
        txDesc.setText("");
        txtNombre.requestFocus();
    }

    // ══════════════════════════════════════════════════════════════
    // Helpers de construcción
    // ══════════════════════════════════════════════════════════════

    /** Botón con estilo unificado del laboratorio. */
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

    /** JLabel de etiqueta de formulario. */
    private JLabel etq(String t) {
        JLabel l = new JLabel(t);
        l.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        return l;
    }

    /** JLabel con fuente y color personalizados. */
    private JLabel etqF(String t, Font f, Color c) {
        JLabel l = new JLabel(t);
        l.setFont(f);
        l.setForeground(c);
        return l;
    }
}
