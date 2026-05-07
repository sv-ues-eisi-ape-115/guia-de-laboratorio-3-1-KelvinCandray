package sv.edu.ues.ape115.layouts.app;

import sv.edu.ues.ape115.layouts.ui.VentanaProductos;
import javax.swing.SwingUtilities;

/**
 * Punto de entrada — G0301 Layout Managers y Bordes.
 * Toda UI debe crearse en el Event Dispatch Thread (EDT).
 */
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaProductos().setVisible(true));
    }
}
