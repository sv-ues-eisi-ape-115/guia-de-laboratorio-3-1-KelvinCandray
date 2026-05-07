package sv.edu.ues.ape115.layouts.ui;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

/**
 * Requerimiento R01 — Vitrina de Layouts.
 *
 * Ventana con GridLayout(2,3) donde cada celda demuestra
 * un Layout Manager distinto con TitledBorder de color propio.
 *
 * TODO: Completar los detalles de cada celda según R01.
 *
 * @author (nombre del estudiante)
 */
public class VitrinaLayouts extends JFrame {

    // CardLayout y su contenedor (celda 6)
    private final CardLayout   cardDemo = new CardLayout();
    private final JPanel       pnlCard  = new JPanel(cardDemo);

    public VitrinaLayouts() {
        super("R01 — Vitrina: Los 6 Layout Managers");
        construirUI();
        setSize(960, 640);
        setMinimumSize(new Dimension(740, 500));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void construirUI() {
        JPanel root = new JPanel(new BorderLayout(8, 8));
        root.setBorder(BorderFactory.createEmptyBorder(10, 12, 10, 12));
        setContentPane(root);

        root.add(crearNorth(),   BorderLayout.NORTH);
        root.add(crearGrilla(),  BorderLayout.CENTER);
        root.add(crearSouth(),   BorderLayout.SOUTH);
    }

    // NORTH ────────────────────────────────────────────────────────
    private JPanel crearNorth() {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.CENTER));
        p.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(25, 118, 210)));

        JLabel titulo = new JLabel("Vitrina — Los 6 Layout Managers de Java Swing");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 16));
        titulo.setForeground(new Color(25, 118, 210));

        JLabel sub = new JLabel("  (Redimensiona la ventana para ver cómo se adapta cada uno)");
        sub.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        sub.setForeground(new Color(100, 100, 120));

        p.add(titulo);
        p.add(sub);
        return p;
    }

    // CENTER — GridLayout 2×3 con las seis celdas ──────────────────
    private JPanel crearGrilla() {
        JPanel grilla = new JPanel(new GridLayout(2, 3, 10, 10));

        grilla.add(crearCeldaBorderLayout());   // celda 1
        grilla.add(crearCeldaFlowLayout());     // celda 2
        grilla.add(crearCeldaGridLayout());     // celda 3
        grilla.add(crearCeldaGridBagLayout());  // celda 4
        grilla.add(crearCeldaBoxLayout());      // celda 5
        grilla.add(crearCeldaCardLayout());     // celda 6

        return grilla;
    }

    // ── Celda 1: BorderLayout ─────────────────────────────────────
    private JPanel crearCeldaBorderLayout() {
        JPanel cell = new JPanel(new BorderLayout(2, 2));
        cell.setBorder(titledBorde("BorderLayout", new Color(25, 118, 210)));

        // TODO R01: JLabel descriptivo en CENTER + JButton en las 4 regiones cardinales
        cell.add(new JButton("NORTH"),  BorderLayout.NORTH);
        cell.add(new JButton("SOUTH"),  BorderLayout.SOUTH);
        cell.add(new JButton("EAST"),   BorderLayout.EAST);
        cell.add(new JButton("WEST"),   BorderLayout.WEST);
        JLabel c = new JLabel("CENTER", SwingConstants.CENTER);
        c.setFont(new Font("Segoe UI", Font.BOLD, 13));
        c.setForeground(new Color(25, 118, 210));
        cell.add(c, BorderLayout.CENTER);
        return cell;
    }

    // ── Celda 2: FlowLayout ───────────────────────────────────────
    private JPanel crearCeldaFlowLayout() {
        JPanel cell = new JPanel(new FlowLayout(FlowLayout.LEFT, 4, 4));
        cell.setBorder(titledBorde("FlowLayout", new Color(46, 125, 50)));

        // TODO R01: 8 JButton pequeños que se reorganicen al redimensionar
        for (int i = 1; i <= 8; i++) {
            JButton btn = new JButton("B" + i);
            btn.setPreferredSize(new Dimension(50, 28));
            cell.add(btn);
        }
        return cell;
    }

    // ── Celda 3: GridLayout ───────────────────────────────────────
    private JPanel crearCeldaGridLayout() {
        JPanel cell = new JPanel(new GridLayout(3, 3, 3, 3));
        cell.setBorder(titledBorde("GridLayout", new Color(198, 40, 40)));

        // TODO R01: 9 JButton como teclado numérico
        String[] keys = {"7", "8", "9", "4", "5", "6", "1", "2", "3"};
        for (String k : keys) cell.add(new JButton(k));
        return cell;
    }

    // ── Celda 4: GridBagLayout ────────────────────────────────────
    private JPanel crearCeldaGridBagLayout() {
        JPanel cell = new JPanel(new GridBagLayout());
        cell.setBorder(titledBorde("GridBagLayout", new Color(100, 50, 150)));

        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(3, 4, 3, 4);
        g.anchor = GridBagConstraints.WEST;

        // TODO R01: miniFormulario de 3 filas (etiqueta + campo)
        String[] lbls = {"Nombre:", "Email:", "Ciudad:"};
        for (int i = 0; i < lbls.length; i++) {
            g.gridx = 0; g.gridy = i;
            g.fill = GridBagConstraints.NONE; g.weightx = 0;
            cell.add(new JLabel(lbls[i]), g);
            g.gridx = 1;
            g.fill = GridBagConstraints.HORIZONTAL; g.weightx = 1;
            cell.add(new JTextField(10), g);
        }
        return cell;
    }

    // ── Celda 5: BoxLayout(Y_AXIS) ────────────────────────────────
    private JPanel crearCeldaBoxLayout() {
        JPanel cell = new JPanel();
        cell.setLayout(new BoxLayout(cell, BoxLayout.Y_AXIS));
        cell.setBorder(titledBorde("BoxLayout Y_AXIS", new Color(230, 101, 0)));

        // TODO R01: 5 JLabel apilados con Box.createVerticalStrut entre ellos
        String[] items = {"Inicio", "Productos", "Clientes", "Reportes", "Configuración"};
        for (String item : items) {
            JLabel lbl = new JLabel("▸  " + item);
            lbl.setFont(new Font("Segoe UI", Font.PLAIN, 12));
            lbl.setAlignmentX(Component.LEFT_ALIGNMENT);
            cell.add(lbl);
            cell.add(Box.createVerticalStrut(5));
        }
        cell.add(Box.createVerticalGlue());
        return cell;
    }

    // ── Celda 6: CardLayout ───────────────────────────────────────
    private JPanel crearCeldaCardLayout() {
        JPanel cell = new JPanel(new BorderLayout(0, 4));
        cell.setBorder(titledBorde("CardLayout", new Color(0, 121, 107)));

        // Dos tarjetas
        JPanel cardA = new JPanel(new FlowLayout(FlowLayout.CENTER));
        cardA.setBackground(new Color(232, 245, 233));
        cardA.add(new JLabel("🟢  Tarjeta A — Activa"));

        JPanel cardB = new JPanel(new FlowLayout(FlowLayout.CENTER));
        cardB.setBackground(new Color(227, 242, 253));
        cardB.add(new JLabel("🔵  Tarjeta B — Siguiente"));

        pnlCard.add(cardA, "A");
        pnlCard.add(cardB, "B");
        cell.add(pnlCard, BorderLayout.CENTER);

        // TODO R01: botones Anterior y Siguiente que naveguen el CardLayout
        JPanel botones = new JPanel(new FlowLayout(FlowLayout.CENTER, 4, 0));
        JButton btnAnt = new JButton("◀ Anterior");
        JButton btnSig = new JButton("Siguiente ▶");
        btnAnt.addActionListener(e -> cardDemo.previous(pnlCard));
        btnSig.addActionListener(e -> cardDemo.next(pnlCard));
        botones.add(btnAnt);
        botones.add(btnSig);
        cell.add(botones, BorderLayout.SOUTH);

        return cell;
    }

    // SOUTH ────────────────────────────────────────────────────────
    private JPanel crearSouth() {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 6));
        p.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(200, 210, 230)));
        JButton btnSalir = new JButton("Salir");
        btnSalir.addActionListener(e -> dispose());
        p.add(btnSalir);
        return p;
    }

    // ── Helper: TitledBorder de color envuelto en CompoundBorder ──
    private Border titledBorde(String titulo, Color color) {
        return BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(color, 2),
                titulo,
                TitledBorder.LEFT, TitledBorder.TOP,
                new Font("Segoe UI", Font.BOLD, 11),
                color
            ),
            BorderFactory.createEmptyBorder(4, 6, 4, 6)
        );
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VitrinaLayouts().setVisible(true));
    }
}
