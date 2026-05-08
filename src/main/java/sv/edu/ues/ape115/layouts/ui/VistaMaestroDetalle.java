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

    public VistaMaestroDetalle() {
        super("R04 — Vista Maestro-Detalle: JSplitPane");
        cargarDatos();
        // TODO: construirUI()
        // TODO: setSize(860, 560)
        // TODO: setMinimumSize(new Dimension(700, 480))
        // TODO: setDefaultCloseOperation(EXIT_ON_CLOSE)
        // TODO: setLocationRelativeTo(null)
    }

    private void cargarDatos() {
        // TODO: Agregar 8 productos a la lista y al modelo
        //   Ejemplo: productos.add(new Producto("Laptop Dell","Electrónica",899.99,12,"desc"))
        //            modelo.addElement(prod)
    }

    private void construirUI() {
        // TODO: JPanel root con BorderLayout(8,8) + EmptyBorder(10,12,10,12)
        // TODO: setContentPane(root)
        // TODO: JSplitPane split = new JSplitPane(HORIZONTAL_SPLIT, crearMaestro(), crearDetalle())
        // TODO: split.setDividerLocation(260)
        // TODO: split.setResizeWeight(0.30)       ← RN-R04.4
        // TODO: split.setOneTouchExpandable(true)  ← requerido por test T05.3
        // TODO: root.add(split, BorderLayout.CENTER)
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
        return new JPanel(); // reemplazar
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
        return new JPanel(); // reemplazar
    }

    private void cargarDetalle(Producto p) {
        // TODO: Si p es null, retornar
        // TODO: Llenar txtNombre, txtPrecio, txtStock con datos del producto
        // TODO: cboCat.setSelectedItem(p.getCategoria())
        // TODO: txDesc.setText(p.getDescripcion())
    }

    private void filtrar(String q) {
        // TODO: modelo.clear()
        // TODO: Agregar productos cuyo nombre contenga q (ignoreCase), o q está vacío
    }

    private void limpiar() {
        // TODO: Limpiar todos los campos de texto
        // TODO: lstProductos.clearSelection()
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VistaMaestroDetalle().setVisible(true));
    }
}
