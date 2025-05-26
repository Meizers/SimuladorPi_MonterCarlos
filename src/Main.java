import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Preguntar modo
            String[] opciones = {"Modo infinito", "Modo maximo"};
            int modo = JOptionPane.showOptionDialog(null,
                    "Seleccionr el modo de simulación",
                    "Modo de ejecución",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    opciones,
                    opciones[0]);

            int cantidadPorVez;
            int totalFijo;

            if (modo == 0) {
                totalFijo = 0; // Modo Infinito
                String input = JOptionPane.showInputDialog("Cantidad de Puntos por iteracion");
                cantidadPorVez = Math.max(1, Integer.parseInt(input));
            } else {
                cantidadPorVez = 1;
                if (modo == 1) { // Modo fijo
                    String input = JOptionPane.showInputDialog("Puntos Maximos a generar :");
                    totalFijo = Math.max(1, Integer.parseInt(input));
                } else {
                    totalFijo = 0;
                }
            }

            // Interfaz Grafica
            JFrame frame = new JFrame("Estimación de PI");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JLabel piLabel = new JLabel("Estimacion de π: ");
            piLabel.setFont(new Font("Arial", Font.BOLD, 16));
            JLabel totalesLabel = new JLabel("Puntos totales: ");
            totalesLabel.setFont(new Font("Arial", Font.BOLD, 16));
            JLabel dentroLabel = new JLabel("Puntos dentro: ");
            dentroLabel.setFont(new Font("Arial", Font.BOLD, 16));

            MonteCarloPiGUI panel = new MonteCarloPiGUI(piLabel, totalesLabel, dentroLabel);

            JPanel labelPanel = new JPanel(new GridLayout(3, 1));
            labelPanel.add(dentroLabel);
            labelPanel.add(totalesLabel);
            labelPanel.add(piLabel);

            frame.setLayout(new BorderLayout());
            frame.add(panel, BorderLayout.CENTER);
            frame.add(labelPanel, BorderLayout.SOUTH);
            frame.pack();
            frame.setVisible(true);

            if (modo == 0) { // infinito de a poco
                new Timer(1, e -> {
                    for (int i = 0; i < cantidadPorVez; i++) {
                        panel.agregarPunto();
                    }
                }).start();
            } else if (modo == 1) { // cantidad maxima
                new Thread(() -> {
                    for (int i = 0; i < totalFijo; i++) {
                        panel.agregarPunto();
                    }
                }).start();
            }
        });
    }
}