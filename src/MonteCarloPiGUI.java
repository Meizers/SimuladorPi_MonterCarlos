import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class MonteCarloPiGUI extends JPanel {
    private static final int Tamanio = 600;
    private int totalPuntos = 0;
    private int puntosDentro = 0;
    private final Random rand = new Random();
    private final java.util.List<Point> puntos = new java.util.ArrayList<>();
    private final java.util.List<Boolean> dentroCirculo = new java.util.ArrayList<>();
    private JLabel piLabel;
    private JLabel totalesLabel;
    private JLabel dentroLabel;

    public MonteCarloPiGUI(JLabel piLabel,JLabel piLabel2,JLabel piLabel3) {
        this.setPreferredSize(new Dimension(Tamanio, Tamanio));
        this.piLabel = piLabel;
        this.totalesLabel = piLabel2;
        this.dentroLabel = piLabel3;
    }

    public void agregarPunto() {
        double x = rand.nextDouble() * 2 - 1;
        double y = rand.nextDouble() * 2 - 1;

        int screenX = (int) ((x + 1) / 2 * Tamanio);
        int screenY = (int) ((y + 1) / 2 * Tamanio);

        boolean dentro = x * x + y * y <= 1.0;
        if (dentro) puntosDentro++;

        puntos.add(new Point(screenX, screenY));
        dentroCirculo.add(dentro);
        totalPuntos++;

        double pi = 4.0 * puntosDentro / totalPuntos;
        piLabel.setText("Estimacion de π: " + String.format("%.6f", pi));
        totalesLabel.setText("Puntos totales: "+ String.format("%d", totalPuntos));
        dentroLabel.setText("Puntos dentro: " + String.format("%d", puntosDentro));

        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Dibuja el cuadrado (todo el panel) y el círculo
        g.setColor(Color.BLACK);
        g.drawOval(0, 0, Tamanio, Tamanio);

        // Dibuja los puntos
        for (int i = 0; i < puntos.size(); i++) {
            Point p = puntos.get(i);
            g.setColor(dentroCirculo.get(i) ? Color.BLUE : Color.RED);
            g.fillRect(p.x, p.y, 2, 2);
        }
    }
}