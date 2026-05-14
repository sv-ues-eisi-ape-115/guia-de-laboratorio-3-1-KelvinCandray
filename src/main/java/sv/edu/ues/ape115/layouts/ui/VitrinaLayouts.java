package sv.edu.ues.ape115.layouts.ui;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

/**
 * Requerimiento R01 — Vitrina de los 6 Layout Managers.
 *
 * Estructura requerida (leer guía sección 3, R01):
 *
 *  JFrame
 *  └── BorderLayout
 *      ├── NORTH  → FlowLayout(CENTER)  título + JLabel instrucción
 *      ├── CENTER → GridLayout(2, 3)    seis celdas, una por Layout Manager
 *      │    ├── Celda 1 BorderLayout  (TitledBorder azul)
 *      │    ├── Celda 2 FlowLayout    (TitledBorder verde)
 *      │    ├── Celda 3 GridLayout(3,3)(TitledBorder rojo)
 *      │    ├── Celda 4 GridBagLayout (TitledBorder violeta)
 *      │    ├── Celda 5 BoxLayout(Y)   (TitledBorder naranja)
 *      │    └── Celda 6 CardLayout     (TitledBorder verde-azulado)
 *      └── SOUTH  → FlowLayout(RIGHT)  botón Salir
 *
 * Reglas de negocio:
 *   RN-R01.1 Cada celda tiene TitledBorder con el nombre del layout.
 *   RN-R01.2 La celda 6 (CardLayout) tiene botones Anterior / Siguiente funcionales.
 *   RN-R01.3 La ventana es redimensionable; los layouts deben adaptarse.
 *   RN-R01.4 Cada celda usa un color diferente en su TitledBorder.
 *
 * @author (escribe tu nombre aquí)
 */
public class VitrinaLayouts extends JFrame {

    // TODO: declarar campo  private final CardLayout cardDemo = new CardLayout();
    // TODO: declarar campo  private final JPanel pnlCard = new JPanel(cardDemo);
    //
    // IMPORTANTE: el campo cardDemo DEBE ser de tipo CardLayout (lo verifica el test T02.3)

    private final CardLayout cardDemo = new CardLayout();
    private final JPanel pnlCard = new JPanel(cardDemo);

    public VitrinaLayouts() {
        super("R01 — Vitrina: Los 6 Layout Managers de Swing");
        // TODO: construir la UI (ver métodos de abajo)
        // TODO: setSize(960, 640)
        // TODO: setMinimumSize(new Dimension(740, 500))
        // TODO: setDefaultCloseOperation(EXIT_ON_CLOSE)
        // TODO: setLocationRelativeTo(null)

        setLayout(new BorderLayout());
        add(crearNorth(), BorderLayout.NORTH);
        add(crearGrilla(), BorderLayout.CENTER);
        add(crearSouth(), BorderLayout.SOUTH);

        setSize(960, 640);
        setMinimumSize(new Dimension(740, 500));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    // ── NORTH ─────────────────────────────────────────────────────
    private JPanel crearNorth() {
        // TODO: FlowLayout(CENTER)
        // TODO: MatteBorder inferior de 2px azul
        // TODO: JLabel título con fuente Segoe UI Bold 16, color azul
        // TODO: JLabel subtítulo con instrucción para redimensionar
        // TODO: return panel

        JPanel p = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        p.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLUE));

        JLabel lblTitulo = new JLabel("Laboratorio APE115 — Gestión de Layouts");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblTitulo.setForeground(Color.BLUE);

        JLabel lblSub = new JLabel("|  Redimensione la ventana para observar el comportamiento.");
        lblSub.setFont(new Font("Segoe UI", Font.ITALIC, 13));

        p.add(lblTitulo);
        p.add(lblSub);
        return p;
    }

    // ── CENTER: GridLayout(2,3) ────────────────────────────────────
    private JPanel crearGrilla() {
        // TODO: new JPanel(new GridLayout(2, 3, 10, 10))
        // TODO: grilla.add(celda1_BorderLayout())
        // TODO: grilla.add(celda2_FlowLayout())
        // TODO: grilla.add(celda3_GridLayout())
        // TODO: grilla.add(celda4_GridBagLayout())
        // TODO: grilla.add(celda5_BoxLayout())
        // TODO: grilla.add(celda6_CardLayout())
        // TODO: return grilla

        JPanel grilla = new JPanel(new GridLayout(2, 3, 10, 10));
        grilla.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        grilla.add(celda1_BorderLayout());
        grilla.add(celda2_FlowLayout());
        grilla.add(celda3_GridLayout());
        grilla.add(celda4_GridBagLayout());
        grilla.add(celda5_BoxLayout());
        grilla.add(celda6_CardLayout());

        return grilla;
    }

    // ── Celda 1: BorderLayout ─────────────────────────────────────
    private JPanel celda1_BorderLayout() {
        // TODO: new JPanel(new BorderLayout(2, 2))
        // TODO: TitledBorder con nombre "BorderLayout" y color azul
        // TODO: JButton "NORTH"  → BorderLayout.NORTH
        // TODO: JButton "SOUTH"  → BorderLayout.SOUTH
        // TODO: JButton "EAST"   → BorderLayout.EAST
        // TODO: JButton "WEST"   → BorderLayout.WEST
        // TODO: JLabel  "CENTER" → BorderLayout.CENTER (centrado, negrita, azul)
        // TODO: return celda

        JPanel p = new JPanel(new BorderLayout(2, 2));
        p.setBorder(titledBorde("BorderLayout", Color.BLUE));

        p.add(new JButton("NORTH"), BorderLayout.NORTH);
        p.add(new JButton("SOUTH"), BorderLayout.SOUTH);
        p.add(new JButton("EAST"), BorderLayout.EAST);
        p.add(new JButton("WEST"), BorderLayout.WEST);

        JLabel lblCenter = new JLabel("CENTER", SwingConstants.CENTER);
        lblCenter.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblCenter.setForeground(Color.BLUE);
        p.add(lblCenter, BorderLayout.CENTER);

        return p;
    }

    // ── Celda 2: FlowLayout ───────────────────────────────────────
    private JPanel celda2_FlowLayout() {
        // TODO: new JPanel(new FlowLayout(FlowLayout.LEFT, 4, 4))
        // TODO: TitledBorder "FlowLayout" en verde
        // TODO: 8 JButton pequeños (preferredSize 50×28 cada uno)
        //       Los botones fluyen y se reorganizan al redimensionar
        // TODO: return celda

        JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT, 4, 4));
        p.setBorder(titledBorde("FlowLayout", new Color(46, 125, 50))); // Verde

        for (int i = 1; i <= 8; i++) {
            JButton btn = new JButton("B" + i);
            btn.setPreferredSize(new Dimension(50, 28));
            p.add(btn);
        }

        return p;
    }

    // ── Celda 3: GridLayout(3,3) ──────────────────────────────────
    private JPanel celda3_GridLayout() {
        // TODO: new JPanel(new GridLayout(3, 3, 3, 3))
        // TODO: TitledBorder "GridLayout" en rojo
        // TODO: 9 JButton con las cifras "7","8","9","4","5","6","1","2","3"
        // TODO: return celda

        JPanel p = new JPanel(new GridLayout(3, 3, 3, 3));
        p.setBorder(titledBorde("GridLayout", Color.RED));

        String[] num = {"7", "8", "9", "4", "5", "6", "1", "2", "3"};
        for (String n : num) {
            p.add(new JButton(n));
        }

        return p;
    }

    // ── Celda 4: GridBagLayout ────────────────────────────────────
    private JPanel celda4_GridBagLayout() {
        // TODO: new JPanel(new GridBagLayout())
        // TODO: TitledBorder "GridBagLayout" en violeta/morado
        // TODO: GridBagConstraints g; insets=Insets(3,4,3,4); anchor=WEST
        // TODO: Para cada etiqueta en {"Nombre:","Email:","Ciudad:"}:
        //         gridx=0, fill=NONE → add JLabel
        //         gridx=1, fill=HORIZONTAL, weightx=1 → add JTextField(10)
        // TODO: return celda

        JPanel p = new JPanel(new GridBagLayout());
        p.setBorder(titledBorde("GridBagLayout", new Color(103, 58, 183))); // Violeta

        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(3, 4, 3, 4);
        g.anchor = GridBagConstraints.WEST;

        String[] etiquetas = {"Nombre:", "Email:", "Ciudad:"};
        for (int i = 0; i < etiquetas.length; i++) {
            g.gridy = i;
            g.gridx = 0; g.fill = GridBagConstraints.NONE; g.weightx = 0;
            p.add(new JLabel(etiquetas[i]), g);

            g.gridx = 1; g.fill = GridBagConstraints.HORIZONTAL; g.weightx = 1.0;
            p.add(new JTextField(10), g);
        }

        return p;
    }

    // ── Celda 5: BoxLayout(Y_AXIS) ────────────────────────────────
    private JPanel celda5_BoxLayout() {
        // TODO: new JPanel(); setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS))
        // TODO: TitledBorder "BoxLayout Y_AXIS" en naranja
        // TODO: Para cada item en {"Inicio","Productos","Clientes","Reportes","Configuración"}:
        //         JLabel "▸  " + item; setAlignmentX(LEFT)
        //         panel.add(lbl); panel.add(Box.createVerticalStrut(5))
        // TODO: panel.add(Box.createVerticalGlue())
        // TODO: return celda
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setBorder(titledBorde("BoxLayout Y_AXIS", Color.ORANGE));

        String[] menu = {"Inicio", "Productos", "Clientes", "Reportes", "Configuración"};
        for (String item : menu) {
            JLabel lbl = new JLabel("▸ " + item);
            lbl.setAlignmentX(Component.LEFT_ALIGNMENT);
            p.add(lbl);
            p.add(Box.createVerticalStrut(5));
        }
        p.add(Box.createVerticalGlue());

        return p;
    }

    // ── Celda 6: CardLayout ───────────────────────────────────────
    private JPanel celda6_CardLayout() {
        // TODO: new JPanel(new BorderLayout(0, 4))
        // TODO: TitledBorder "CardLayout" en verde-azulado (ej: Color(0,121,107))
        //
        // TODO: Crear JPanel cardA con fondo verde claro y JLabel "Tarjeta A"
        // TODO: Crear JPanel cardB con fondo azul claro y JLabel "Tarjeta B"
        // TODO: pnlCard.add(cardA, "A"); pnlCard.add(cardB, "B")
        // TODO: celda.add(pnlCard, BorderLayout.CENTER)
        //
        // TODO: JPanel botones con FlowLayout(CENTER, 4, 0)
        //         JButton "◀ Anterior" → cardDemo.previous(pnlCard)
        //         JButton "Siguiente ▶" → cardDemo.next(pnlCard)
        // TODO: celda.add(botones, BorderLayout.SOUTH)
        // TODO: return celda

        JPanel p = new JPanel(new BorderLayout(0, 4));
        p.setBorder(titledBorde("CardLayout", new Color(0, 121, 107))); // Verde-azulado

        JPanel cardA = new JPanel(new GridBagLayout());
        cardA.setBackground(new Color(232, 245, 233));
        cardA.add(new JLabel("Tarjeta A"));

        JPanel cardB = new JPanel(new GridBagLayout());
        cardB.setBackground(new Color(227, 242, 253));
        cardB.add(new JLabel("Tarjeta B"));

        pnlCard.add(cardA, "A");
        pnlCard.add(cardB, "B");
        p.add(pnlCard, BorderLayout.CENTER);

        JPanel botones = new JPanel(new FlowLayout(FlowLayout.CENTER, 4, 0));
        JButton btnAnt = new JButton("◀ Anterior");
        btnAnt.addActionListener(e -> cardDemo.previous(pnlCard));

        JButton btnSig = new JButton("Siguiente ▶");
        btnSig.addActionListener(e -> cardDemo.next(pnlCard));

        botones.add(btnAnt);
        botones.add(btnSig);
        p.add(botones, BorderLayout.SOUTH);

        return p;
    }

    // ── SOUTH ──────────────────────────────────────────────────────
    private JPanel crearSouth() {
        // TODO: FlowLayout(FlowLayout.RIGHT, 8, 6)
        // TODO: MatteBorder(1, 0, 0, 0, Color(200,210,230))
        // TODO: JButton "Salir" → dispose()
        // TODO: return panel

        JPanel p = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 6));
        p.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(200, 210, 230)));

        JButton btnSalir = new JButton("Salir");
        btnSalir.addActionListener(e -> dispose());
        p.add(btnSalir);

        return p;
    }

    // ── Helper: TitledBorder envuelto en CompoundBorder ───────────
    // TODO: Implementar este método
    //   return BorderFactory.createCompoundBorder(
    //       BorderFactory.createTitledBorder(
    //           BorderFactory.createLineBorder(color, 2),
    //           titulo, TitledBorder.LEFT, TitledBorder.TOP,
    //           new Font("Segoe UI", Font.BOLD, 11), color),
    //       BorderFactory.createEmptyBorder(4, 6, 4, 6));
    private Border titledBorde(String titulo, Color color) {
        return BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(
                        BorderFactory.createLineBorder(color, 2),
                        titulo, TitledBorder.LEFT, TitledBorder.TOP,
                        new Font("Segoe UI", Font.BOLD, 11), color),
                BorderFactory.createEmptyBorder(4, 6, 4, 6));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VitrinaLayouts().setVisible(true));
    }
}
