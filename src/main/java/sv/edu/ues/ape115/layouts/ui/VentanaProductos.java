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
 * @author (Kelvin Jair Zacarias Candray)
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
        JPanel root = new JPanel(new BorderLayout(6,6));
        root.setBorder(BorderFactory.createEmptyBorder(10,12,10,12));
        setContentPane(root);

        root.add(crearNorth(), BorderLayout.NORTH);
        root.add(crearWest(), BorderLayout.WEST);
        root.add(crearCentro() ,BorderLayout.CENTER);
        root.add(crearSouth(), BorderLayout.SOUTH);
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
        JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT,12,6));
        // LineBOrder : LInea simple de 2 px
        p.setBorder(BorderFactory.createLineBorder(new Color(25,118,210),2));
        JLabel lbl = new JLabel(" Gestión de Productos – APE 115 G0301");
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 17));
        lbl.setForeground(new Color(25, 118, 210));

        JTextField txtBuscar = new JTextField(18);
        txtBuscar.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtBuscar.setToolTipText("Escribe y presiona Enter para buscar");
        txtBuscar.addActionListener(e -> filtrar(txtBuscar.getText()));

// PASO 4 (Guía §2.5): Panel NORTH – FlowLayout + LineBorder
        p.add(lbl);
        p.add(Box.createHorizontalStrut(30));
        p.add(new JLabel("Buscar: "));
        p.add(txtBuscar);

        return p; // reemplazar
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

        p.add(Box.createVerticalGlue()); // Empuja los botones hacia arriba
        return p;
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
        JSplitPane sp = new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT,
                crearFormulario(),
                crearDetalle()
        );
        sp.setDividerLocation(340);
        sp.setResizeWeight(0.40);
        sp.setOneTouchExpandable(true);
        sp.setContinuousLayout(true);
        return sp; // reemplazar
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

        JPanel p = new JPanel(new GridBagLayout());
        p.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(
                        BorderFactory.createEtchedBorder(EtchedBorder.LOWERED),
                        "Datos del Producto",
                        TitledBorder.LEFT,TitledBorder.TOP,
                        new Font("Segoe UI", Font.BOLD,12),
                        new Color(25,118,210)
                ),
                BorderFactory.createEmptyBorder(8,10,8,10)
        ));
        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(5, 6, 5, 6);
        g.anchor = GridBagConstraints.WEST;

// --- Fila 0: Nombre (campo abarca col 1-3)
        g.gridx = 0; g.gridy = 0; g.fill = GridBagConstraints.NONE; g.weightx = 0;
        p.add(new JLabel("Nombre *:"), g);
        g.gridx = 1; g.gridwidth = 3;
        g.fill = GridBagConstraints.HORIZONTAL; g.weightx = 1;
        p.add(txtNombre = new JTextField(20), g);
        g.gridwidth = 1; g.weightx = 0; // SIEMPRE resetear

// --- Fila 1: Precio (col 1) | Stock (col 2-3)
        g.gridx = 0; g.gridy = 1; g.fill = GridBagConstraints.NONE;
        p.add(new JLabel("Precio * ($):"), g);
        g.gridx = 1; g.fill = GridBagConstraints.HORIZONTAL; g.weightx = 1;
        p.add(txtPrecio = new JTextField(8), g);
        g.gridx = 2; g.fill = GridBagConstraints.NONE; g.weightx = 0;
        p.add(new JLabel("Stock *:"), g);
        g.gridx = 3; g.fill = GridBagConstraints.HORIZONTAL; g.weightx = 0.5;
        p.add(txtStock = new JTextField(5), g);
        g.weightx = 0;

// --- Fila 2: Categoría (JComboBox, span 3 cols)
        g.gridx = 0; g.gridy = 2; g.fill = GridBagConstraints.NONE;
        p.add(new JLabel("Categoría *:"), g);
        g.gridx = 1; g.gridwidth = 3; g.fill = GridBagConstraints.HORIZONTAL;
        cboCat = new JComboBox<>(new String[]{"Electrónica", "Hogar", "Accesorios"});
        cboCat.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        p.add(cboCat, g);
        g.gridwidth = 1;

// --- Fila 3: JList con scroll (span 4 cols, crece vertical)
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
         // reemplazar
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
        JTabbedPane tabs = new JTabbedPane(JTabbedPane.TOP);

// --- Pestaña 1: GridLayout 4 x 2
        JPanel pDatos = new JPanel(new GridLayout(4, 2, 8, 8));
        pDatos.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        Font fL = new Font("Segoe UI", Font.BOLD, 13);
        Font fV = new Font("Segoe UI", Font.PLAIN, 13);

        pDatos.add(etqF("Nombre: ", fL, new Color(60, 80, 120)));
        pDatos.add(lblDNombre = new JLabel("-")); lblDNombre.setFont(fV);

        pDatos.add(etqF("Precio $: ", fL, new Color(60, 80, 120)));
        pDatos.add(lblDPrecio = new JLabel("-")); lblDPrecio.setFont(fV);

        pDatos.add(etqF("Stock: ", fL, new Color(60, 80, 120)));
        pDatos.add(lblDStock = new JLabel("-")); lblDStock.setFont(fV);

        pDatos.add(etqF("Categoría: ", fL, new Color(60, 80, 120)));
        pDatos.add(lblDCat = new JLabel("-")); lblDCat.setFont(fV);

        tabs.addTab("Datos", null, pDatos, "Datos generales del producto seleccionado");

// --- Pestaña 2: JTextArea en JScrollPane
        txDesc = new JTextArea();
        txDesc.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txDesc.setLineWrap(true);
        txDesc.setWrapStyleWord(true);
        txDesc.setEditable(false);
        txDesc.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        tabs.addTab("Descripción", null, new JScrollPane(txDesc), "Descripción completa del producto");

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
         // reemplazar
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

        JPanel p = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 6));

// MatteBorder: línea solo en el borde superior (separador visual)
        p.setBorder(BorderFactory.createMatteBorder(
                1, 0, 0, 0, new Color(200, 210, 230)));

        JButton btnLimpiar = crearBoton("Limpiar", new Color(100, 100, 100));
        JButton btnEliminar = crearBoton("Eliminar", new Color(198, 40, 40));
        JButton btnNuevo = crearBoton("+ Nuevo", new Color(25, 118, 210));
        JButton btnGuardar = crearBoton("Guardar", new Color(46, 125, 50));

        btnLimpiar.addActionListener(e -> limpiar());
        btnEliminar.addActionListener(e -> eliminar());
        btnNuevo.addActionListener(e -> limpiar());
        btnGuardar.addActionListener(e -> guardar());

        p.add(btnLimpiar);
        p.add(btnEliminar);
        p.add(btnNuevo);
        p.add(btnGuardar);

        return p;
        // reemplazar
    }

    // ─────────────────────────────────────────────────────────────
    // PASO 9 (Guía §2.10): Datos de muestra y lógica
    // ─────────────────────────────────────────────────────────────
    private void cargarDatos() {
        // TODO: Agregar 8 productos a la lista usando new Producto(nombre, cat, precio, stock, desc)
        // TODO: Al final: productos.forEach(modelo::addElement)

            // Agregamos productos de muestra
            productos.add(new Producto("Laptop Dell XPS", "Electrónica", 1200.00, 15, "Laptop de alto rendimiento con pantalla 4K."));
            productos.add(new Producto("Smartphone Samsung S23", "Electrónica", 850.00, 25, "Cámara de 200MP y procesador Snapdragon."));
            productos.add(new Producto("Cafetera Espresso", "Hogar", 150.00, 10, "Cafetera de 15 bares con espumador de leche."));
            productos.add(new Producto("Aspiradora Robot", "Hogar", 300.00, 5, "Navegación láser y compatible con Alexa."));
            productos.add(new Producto("Audífonos Sony WH", "Electrónica", 350.00, 30, "Cancelación de ruido líder en la industria."));
            productos.add(new Producto("Silla Ergonómica", "Hogar", 220.00, 12, "Soporte lumbar ajustable y malla transpirable."));
            productos.add(new Producto("Mouse Gamer RGB", "Accesorios", 60.00, 50, "Sensor de 25,000 DPI y 8 botones programables."));
            productos.add(new Producto("Teclado Mecánico", "Accesorios", 110.00, 20, "Switches Cherry MX Red y retroiluminación."));

            // Llenar el modelo del JList
            productos.forEach(modelo::addElement);

    }

    private void cargarDetalle(Producto p) {
        // TODO: Si p es null, retornar
        // TODO: Actualizar los 4 JLabel del panel de detalle
        //       lblDNombre.setText(p.getNombre()), etc.
        // TODO: Actualizar también los campos del formulario
        //       txtNombre.setText(p.getNombre()), etc.
        if (p == null) return;

        // Actualizar JLabels del panel derecho
        lblDNombre.setText(p.getNombre());
        lblDPrecio.setText(String.format("$ %.2f", p.getPrecio()));
        lblDStock.setText(String.valueOf(p.getStock()));
        lblDCat.setText(p.getCategoria());
        txDesc.setText(p.getDescripcion());

        // Sincronizar campos del formulario (izquierda)
        txtNombre.setText(p.getNombre());
        txtPrecio.setText(String.valueOf(p.getPrecio()));
        txtStock.setText(String.valueOf(p.getStock()));
        cboCat.setSelectedItem(p.getCategoria());
    }

    private void filtrar(String q) {
        // TODO: modelo.clear()
        // TODO: Para cada Producto en productos, agregar al modelo si
        //       q está vacío O el nombre/categoría contienen q (ignoreCase)
        modelo.clear();
        String query = q.toLowerCase();
        for (Producto p : productos) {
            if (query.isEmpty() ||
                    p.getNombre().toLowerCase().contains(query) ||
                    p.getCategoria().toLowerCase().contains(query)) {
                modelo.addElement(p);
            }
        }
    }

    private void filtrarCategoria(String cat) {
        // TODO: modelo.clear()
        // TODO: Agregar los Productos cuya categoría coincida (o cat=="Todas")
        modelo.clear();
        for (Producto p : productos) {
            if (cat.equals("Todas") || p.getCategoria().equals(cat)) {
                modelo.addElement(p);
            }
        }
    }

    private void guardar() {
        // TODO: Validar que txtNombre, txtPrecio, txtStock no estén vacíos
        //       Si falta alguno: JOptionPane.WARNING_MESSAGE
        // TODO: JOptionPane.INFORMATION_MESSAGE indicando éxito
        // TODO: limpiar()
        if (txtNombre.getText().isEmpty() || txtPrecio.getText().isEmpty() || txtStock.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor complete todos los campos obligatorios (*)",
                    "Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Aquí iría la lógica para agregar a la lista o actualizar (DAO)
        JOptionPane.showMessageDialog(this, "Producto procesado exitosamente.",
                "Éxito", JOptionPane.INFORMATION_MESSAGE);
        limpiar();
    }

    private void eliminar() {
        // TODO: Obtener el producto seleccionado en lstProductos
        //       Si null: JOptionPane informativo
        // TODO: JOptionPane.showConfirmDialog para confirmar
        //       Si YES: productos.remove(sel), modelo.removeElement(sel), limpiar()
        Producto sel = lstProductos.getSelectedValue();
        if (sel == null) {
            JOptionPane.showMessageDialog(this, "Seleccione un producto de la lista para eliminar.",
                    "Aviso", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this,
                "¿Está seguro de eliminar: " + sel.getNombre() + "?",
                "Confirmar eliminación", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            productos.remove(sel);
            modelo.removeElement(sel);
            limpiar();
        }
    }

    private void limpiar() {
        // TODO: Limpiar txtNombre, txtPrecio, txtStock
        // TODO: cboCat.setSelectedIndex(0)
        // TODO: lstProductos.clearSelection()
        // TODO: Resetear los 4 JLabel del detalle a "—"
        // TODO: txDesc.setText("")
        // TODO: txtNombre.requestFocus()
        txtNombre.setText("");
        txtPrecio.setText("");
        txtStock.setText("");
        cboCat.setSelectedIndex(0);
        lstProductos.clearSelection();

        // Resetear etiquetas de detalle
        lblDNombre.setText("—");
        lblDPrecio.setText("—");
        lblDStock.setText("—");
        lblDCat.setText("—");
        txDesc.setText("");

        txtNombre.requestFocus();
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

    private JLabel etqF(String texto, Font fuente, Color color) {
        JLabel label = new JLabel(texto);
        label.setFont(fuente);
        label.setForeground(color);
        return label;
    }
}
