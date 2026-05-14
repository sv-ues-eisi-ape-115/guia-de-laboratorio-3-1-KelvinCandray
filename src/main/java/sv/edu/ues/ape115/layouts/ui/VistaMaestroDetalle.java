package sv.edu.ues.ape115.layouts.ui;

import sv.edu.ues.ape115.layouts.model.Producto;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Requerimiento R04 — Vista Maestro-Detalle con JSplitPane.
 *
 * Estructura requerida:
 *
 *  JFrame  BorderLayout
 *  └── CENTER → JSplitPane(HORIZONTAL_SPLIT, divider=260, resize=0.30, oneTouch=true)
 *       ├── Izquierda (Maestro)  JPanel BorderLayout  CompoundBorder "Productos"
 *       │    ├── NORTH → JTextField búsqueda (LineBorder azul)
 *       │    └── CENTER → JList<Producto> en JScrollPane (8 productos)
 *       └── Derecha (Detalle)    JPanel BorderLayout  CompoundBorder "Detalle del Producto"
 *            ├── CENTER → JPanel GridBagLayout
 *            │    ├── Fila 0: "Nombre:"     → JTextField  (span 2)
 *            │    ├── Fila 1: "Precio $:"   → JTextField
 *            │    ├── Fila 2: "Stock:"      → JTextField
 *            │    ├── Fila 3: "Categoría:"  → JComboBox  (span 2)
 *            │    └── Filas 4-5: "Descripción:" → JTextArea en JScrollPane (span 2 × 2 filas)
 *            └── SOUTH → FlowLayout(RIGHT)  Cancelar / Guardar
 *
 * Reglas de negocio:
 *   RN-R04.1 Seleccionar un producto en la JList carga todos sus campos en el detalle.
 *   RN-R04.2 Presionar Enter en el campo de búsqueda filtra la JList por nombre.
 *   RN-R04.3 JList y JTextArea en JScrollPane.
 *   RN-R04.4 JSplitPane: setResizeWeight(0.30).
 *
 * @author (escribe tu nombre aquí)
 */
public class VistaMaestroDetalle extends JFrame {

    private final List<Producto>             productos = new ArrayList<>();
    private final DefaultListModel<Producto> modelo    = new DefaultListModel<>();

    // TODO: declarar JList<Producto> lstProductos

    // TODO: declarar campos del formulario de detalle:
    //   JTextField txtNombre, txtPrecio, txtStock
    //   JComboBox<String> cboCat
    //   JTextArea txDesc

    private JList<Producto> lstProductos;

    private JTextField txtNombre, txtPrecio, txtStock;
    private JComboBox<String> cboCat;
    private JTextArea txDesc;

    public VistaMaestroDetalle() {
        super("R04 — Vista Maestro-Detalle: JSplitPane");
        cargarDatos();
        // TODO: construirUI()
        // TODO: setSize(860, 560)
        // TODO: setMinimumSize(new Dimension(700, 480))
        // TODO: setDefaultCloseOperation(EXIT_ON_CLOSE)
        // TODO: setLocationRelativeTo(null)
        construirUI();

        setSize(860, 560);
        setMinimumSize(new Dimension(700, 480));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void cargarDatos() {
        // TODO: Agregar 8 productos a la lista y al modelo
        //   Ejemplo: productos.add(new Producto("Laptop Dell","Electrónica",899.99,12,"desc"))
        //            modelo.addElement(prod)

        String[] nombres = {"Laptop Dell", "Smartphone S23", "Monitor 4K", "Mouse Gamer",
                "Teclado Mecánico", "Audífonos Sony", "Silla Oficina", "Escritorio L"};
        String[] cats = {"Electrónica", "Electrónica", "Accesorios", "Accesorios",
                "Accesorios", "Audio", "Hogar", "Hogar"};

        for (int i = 0; i < nombres.length; i++) {
            Producto p = new Producto(nombres[i], cats[i], (i + 1) * 100.50, 10 + i, "Descripción de " + nombres[i]);
            productos.add(p);
            modelo.addElement(p);
        }
    }

    private void construirUI() {
        // TODO: JPanel root con BorderLayout(8,8) + EmptyBorder(10,12,10,12)
        // TODO: setContentPane(root)
        // TODO: JSplitPane split = new JSplitPane(HORIZONTAL_SPLIT, crearMaestro(), crearDetalle())
        // TODO: split.setDividerLocation(260)
        // TODO: split.setResizeWeight(0.30)       ← RN-R04.4
        // TODO: split.setOneTouchExpandable(true)  ← requerido por test T05.3
        // TODO: root.add(split, BorderLayout.CENTER)

        JPanel root = new JPanel(new BorderLayout(8, 8));
        root.setBorder(BorderFactory.createEmptyBorder(10, 12, 10, 12));
        setContentPane(root);

        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, crearMaestro(), crearDetalle());
        split.setDividerLocation(260);
        split.setResizeWeight(0.30);
        split.setOneTouchExpandable(true);
        root.add(split, BorderLayout.CENTER);
    }

    private JPanel crearMaestro() {
        // TODO: new JPanel(new BorderLayout(0, 6))
        // TODO: CompoundBorder: TitledBorder("Productos", EtchedBorder, azul) + EmptyBorder
        //
        // NORTH: JTextField txtBuscar con LineBorder azul
        //   addActionListener → filtrar(txtBuscar.getText())  ← RN-R04.2
        //
        // CENTER: lstProductos = new JList<>(modelo) en JScrollPane  ← RN-R04.3
        //   addListSelectionListener → si !isAdjusting: cargarDetalle(selectedValue)  ← RN-R04.1
        //
        // TODO: return panel

        JPanel p = new JPanel(new BorderLayout(0, 6));
        p.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Productos",
                        TitledBorder.LEFT, TitledBorder.TOP, null, Color.BLUE),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));

        JTextField txtBuscar = new JTextField();
        txtBuscar.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        txtBuscar.setToolTipText("Presione Enter para buscar...");
        txtBuscar.addActionListener(e -> filtrar(txtBuscar.getText()));
        p.add(txtBuscar, BorderLayout.NORTH);

        lstProductos = new JList<>(modelo);
        lstProductos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lstProductos.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                cargarDetalle(lstProductos.getSelectedValue());
            }
        });

        p.add(new JScrollPane(lstProductos), BorderLayout.CENTER);
        return p;

    }

    private JPanel crearDetalle() {
        // TODO: new JPanel(new BorderLayout(0, 8))
        // TODO: CompoundBorder: TitledBorder("Detalle del Producto", EtchedBorder, azul) + EmptyBorder
        //
        // CENTER: JPanel con GridBagLayout
        //   Fila 0: "Nombre:"      + txtNombre  (span 2 cols, fill=HORIZONTAL)
        //   Fila 1: "Precio $:"    + txtPrecio
        //   Fila 2: "Stock:"       + txtStock
        //   Fila 3: "Categoría:"   + cboCat     (span 2 cols)
        //   Filas 4-5: "Descripción:" + new JScrollPane(txDesc)  ← RN-R04.3
        //              gridwidth=2, gridheight=2, fill=BOTH, weighty=1
        //
        // SOUTH: FlowLayout(RIGHT) con botones Cancelar / Guardar
        //
        // TODO: return panel

        JPanel p = new JPanel(new BorderLayout(0, 8));
        p.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Detalle del Producto",
                        TitledBorder.LEFT, TitledBorder.TOP, null, Color.BLUE),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        JPanel pnlForm = new JPanel(new GridBagLayout());
        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(4, 4, 4, 4);
        g.anchor = GridBagConstraints.WEST;

        txtNombre = new JTextField();
        txtPrecio = new JTextField();
        txtStock = new JTextField();
        cboCat = new JComboBox<>(new String[]{"Electrónica", "Accesorios", "Audio", "Hogar"});
        txDesc = new JTextArea(4, 20);
        txDesc.setLineWrap(true);
        txDesc.setWrapStyleWord(true);

        g.gridy = 0; g.gridx = 0; pnlForm.add(new JLabel("Nombre:"), g);
        g.gridx = 1; g.gridwidth = 2; g.fill = GridBagConstraints.HORIZONTAL; g.weightx = 1.0;
        pnlForm.add(txtNombre, g);

        g.gridy = 1; g.gridx = 0; g.gridwidth = 1; g.weightx = 0; pnlForm.add(new JLabel("Precio $:"), g);
        g.gridx = 1; pnlForm.add(txtPrecio, g);

        g.gridy = 2; g.gridx = 0; pnlForm.add(new JLabel("Stock:"), g);
        g.gridx = 1; pnlForm.add(txtStock, g);

        g.gridy = 3; g.gridx = 0; pnlForm.add(new JLabel("Categoría:"), g);
        g.gridx = 1; g.gridwidth = 2; pnlForm.add(cboCat, g);

        g.gridy = 4; g.gridx = 0; g.gridwidth = 1; pnlForm.add(new JLabel("Descripción:"), g);
        g.gridy = 4; g.gridx = 1; g.gridwidth = 2; g.gridheight = 2;
        g.fill = GridBagConstraints.BOTH; g.weighty = 1.0;
        pnlForm.add(new JScrollPane(txDesc), g);

        p.add(pnlForm, BorderLayout.CENTER);

        // Botones
        JPanel pnlBtns = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnLimpiar = new JButton("Cancelar");
        btnLimpiar.addActionListener(e -> limpiar());

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setBackground(new Color(25, 118, 210));
        btnGuardar.setForeground(Color.WHITE);

        pnlBtns.add(btnLimpiar);
        pnlBtns.add(btnGuardar);
        p.add(pnlBtns, BorderLayout.SOUTH);

        return p;
    }

    private void cargarDetalle(Producto p) {
        // TODO: Si p es null, retornar
        // TODO: Llenar txtNombre, txtPrecio, txtStock con datos del producto
        // TODO: cboCat.setSelectedItem(p.getCategoria())
        // TODO: txDesc.setText(p.getDescripcion())

        if (p == null) return;
        txtNombre.setText(p.getNombre());
        txtPrecio.setText(String.valueOf(p.getPrecio()));
        txtStock.setText(String.valueOf(p.getStock()));
        cboCat.setSelectedItem(p.getCategoria());
        txDesc.setText(p.getDescripcion());
    }

    private void filtrar(String q) {
        // TODO: modelo.clear()
        // TODO: Agregar productos cuyo nombre contenga q (ignoreCase), o q está vacío

        modelo.clear();
        String query = q.toLowerCase();
        for (Producto p : productos) {
            if (query.isEmpty() || p.getNombre().toLowerCase().contains(query)) {
                modelo.addElement(p);
            }
        }
    }

    private void limpiar() {
        // TODO: Limpiar todos los campos de texto
        // TODO: lstProductos.clearSelection()
        txtNombre.setText("");
        txtPrecio.setText("");
        txtStock.setText("");
        txDesc.setText("");
        cboCat.setSelectedIndex(0);
        lstProductos.clearSelection();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VistaMaestroDetalle().setVisible(true));
    }
}
