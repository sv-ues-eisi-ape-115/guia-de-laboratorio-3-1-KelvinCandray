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
 * @author (nombre del estudiante)
 */
public class VistaMaestroDetalle extends JFrame {

    private final List<Producto>             productos = new ArrayList<>();
    private final DefaultListModel<Producto> modelo    = new DefaultListModel<>();
    private JList<Producto>  lstProductos;

    // Campos del detalle
    private JTextField       txtNombre, txtPrecio, txtStock;
    private JComboBox<String> cboCat;
    private JTextArea        txDesc;

    public VistaMaestroDetalle() {
        super("R04 — Vista Maestro-Detalle: JSplitPane");
        cargarDatos();
        construirUI();
        setSize(860, 560);
        setMinimumSize(new Dimension(700, 480));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void cargarDatos() {
        Object[][] d = {
            {"Laptop Dell",    "Electrónica",899.99,12,"Laptop i7 16GB RAM"},
            {"Monitor LG",     "Electrónica",249.50,30,"Monitor 24\" IPS 75Hz"},
            {"Teclado Mec.",   "Accesorios", 89.00, 50,"Switches Blue RGB"},
            {"Silla Ergon.",   "Hogar",      320.00, 8,"Soporte lumbar"},
            {"Audífonos Sony", "Electrónica",299.00,20,"Noise Cancelling"},
            {"Mochila",        "Accesorios",  75.00,40,"Para laptop 15.6\""},
            {"Lámpara LED",    "Hogar",        45.00,60,"Luz ajustable"},
            {"Mouse Logitech", "Accesorios", 109.00,35,"Inalámbrico"},
        };
        for (Object[] row : d) {
            Producto p = new Producto((String)row[0],(String)row[1],
                (Double)row[2],(Integer)row[3],(String)row[4]);
            productos.add(p);
            modelo.addElement(p);
        }
    }

    private void construirUI() {
        JPanel root = new JPanel(new BorderLayout(8, 8));
        root.setBorder(BorderFactory.createEmptyBorder(10, 12, 10, 12));
        setContentPane(root);

        // JSplitPane con los parámetros exigidos por R04
        JSplitPane split = new JSplitPane(
            JSplitPane.HORIZONTAL_SPLIT,
            crearMaestro(),
            crearDetalle()
        );
        split.setDividerLocation(260);
        split.setResizeWeight(0.30);
        split.setOneTouchExpandable(true);
        split.setContinuousLayout(true);
        root.add(split, BorderLayout.CENTER);
    }

    // ── Panel Maestro (izquierda) ─────────────────────────────────
    private JPanel crearMaestro() {
        JPanel p = new JPanel(new BorderLayout(0, 6));
        // TODO R04: CompoundBorder con TitledBorder 'Productos'
        p.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(EtchedBorder.LOWERED),
                "Productos", TitledBorder.LEFT, TitledBorder.TOP,
                new Font("Segoe UI", Font.BOLD, 12),
                new Color(25, 118, 210)
            ),
            BorderFactory.createEmptyBorder(6, 8, 6, 8)
        ));

        // NORTH: campo de búsqueda con LineBorder azul
        JTextField txtBuscar = new JTextField();
        txtBuscar.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtBuscar.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(25, 118, 210), 1),
            BorderFactory.createEmptyBorder(4, 6, 4, 6)
        ));
        txtBuscar.setToolTipText("Escribe y presiona Enter para buscar");
        txtBuscar.addActionListener(e -> filtrar(txtBuscar.getText()));
        p.add(txtBuscar, BorderLayout.NORTH);

        // CENTER: JList dentro de JScrollPane
        lstProductos = new JList<>(modelo);
        lstProductos.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lstProductos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lstProductos.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting())
                mostrarDetalle(lstProductos.getSelectedValue());
        });
        p.add(new JScrollPane(lstProductos), BorderLayout.CENTER);
        return p;
    }

    // ── Panel Detalle (derecha) ───────────────────────────────────
    private JPanel crearDetalle() {
        JPanel outer = new JPanel(new BorderLayout(0, 8));
        // TODO R04: CompoundBorder con TitledBorder 'Detalle del Producto'
        outer.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(EtchedBorder.LOWERED),
                "Detalle del Producto", TitledBorder.LEFT, TitledBorder.TOP,
                new Font("Segoe UI", Font.BOLD, 12),
                new Color(25, 118, 210)
            ),
            BorderFactory.createEmptyBorder(6, 10, 6, 10)
        ));

        // Formulario GridBagLayout
        JPanel form = new JPanel(new GridBagLayout());
        form.setOpaque(false);
        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(5, 6, 5, 6);
        g.anchor = GridBagConstraints.WEST;

        // Nombre
        lbl(form,g,"Nombre:",0,0);
        g.gridx=1; g.gridy=0; g.gridwidth=2;
        g.fill=GridBagConstraints.HORIZONTAL; g.weightx=1;
        txtNombre=tf(); form.add(txtNombre,g); r(g);

        // Precio | Stock
        lbl(form,g,"Precio $:",0,1);
        g.gridx=1; g.gridy=1; g.fill=GridBagConstraints.HORIZONTAL; g.weightx=1;
        txtPrecio=tf(); form.add(txtPrecio,g); r(g);

        lbl(form,g,"Stock:",0,2);
        g.gridx=1; g.gridy=2; g.fill=GridBagConstraints.HORIZONTAL; g.weightx=1;
        txtStock=tf(); form.add(txtStock,g); r(g);

        // Categoría
        lbl(form,g,"Categoría:",0,3);
        g.gridx=1; g.gridy=3; g.gridwidth=2;
        g.fill=GridBagConstraints.HORIZONTAL; g.weightx=1;
        cboCat=new JComboBox<>(new String[]{"Electrónica","Hogar","Accesorios"});
        cboCat.setFont(new Font("Segoe UI",Font.PLAIN,13));
        form.add(cboCat,g); r(g);

        // Descripción JTextArea (filas 4-5, span 1-2)
        lbl(form,g,"Descripción:",0,4);
        g.gridx=1; g.gridy=4; g.gridwidth=2; g.gridheight=2;
        g.fill=GridBagConstraints.BOTH; g.weightx=1; g.weighty=1;
        txDesc=new JTextArea(4,16);
        txDesc.setLineWrap(true); txDesc.setWrapStyleWord(true);
        txDesc.setFont(new Font("Segoe UI",Font.PLAIN,13));
        form.add(new JScrollPane(txDesc),g);

        outer.add(form, BorderLayout.CENTER);

        // Botones SOUTH
        JPanel pBtns = new JPanel(new FlowLayout(FlowLayout.RIGHT, 6, 4));
        pBtns.setOpaque(false);
        JButton btnCancelar= boton("Cancelar",new Color(100,100,100));
        JButton btnGuardar = boton("Guardar", new Color(46,125,50));
        btnCancelar.addActionListener(e -> limpiar());
        pBtns.add(btnCancelar);
        pBtns.add(btnGuardar);
        outer.add(pBtns, BorderLayout.SOUTH);
        return outer;
    }

    private void mostrarDetalle(Producto p) {
        if (p==null) return;
        txtNombre.setText(p.getNombre());
        txtPrecio.setText(String.valueOf(p.getPrecio()));
        txtStock.setText(String.valueOf(p.getStock()));
        cboCat.setSelectedItem(p.getCategoria());
        txDesc.setText(p.getDescripcion());
    }

    private void filtrar(String q) {
        modelo.clear();
        String t = q.trim().toLowerCase();
        productos.stream()
            .filter(p -> t.isEmpty() || p.getNombre().toLowerCase().contains(t))
            .forEach(modelo::addElement);
    }

    private void limpiar() {
        txtNombre.setText(""); txtPrecio.setText(""); txtStock.setText("");
        txDesc.setText(""); lstProductos.clearSelection();
    }

    // helpers
    private void lbl(JPanel p,GridBagConstraints g,String t,int x,int y){
        g.gridx=x;g.gridy=y;g.fill=GridBagConstraints.NONE;g.weightx=0;g.gridwidth=1;
        JLabel l=new JLabel(t); l.setFont(new Font("Segoe UI",Font.PLAIN,13));
        p.add(l,g);
    }
    private JTextField tf(){JTextField t=new JTextField();t.setFont(new Font("Segoe UI",Font.PLAIN,13));return t;}
    private void r(GridBagConstraints g){g.gridwidth=1;g.gridheight=1;g.weightx=0;g.weighty=0;}
    private JButton boton(String label,Color color){
        JButton btn=new JButton(label);
        btn.setFont(new Font("Segoe UI",Font.PLAIN,13));
        btn.setBackground(color);btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);btn.setBorderPainted(false);btn.setOpaque(true);
        btn.setBorder(BorderFactory.createEmptyBorder(6,16,6,16));
        return btn;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VistaMaestroDetalle().setVisible(true));
    }
}
