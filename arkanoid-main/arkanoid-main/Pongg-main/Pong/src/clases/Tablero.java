package clases;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Tablero extends JPanel {

    private static final long serialVersionUID = 1L;
    Balon balon = new Balon(800);
    Paleta p1 = new Paleta(350, 415);
    ArrayList<Rectangle2D.Double> bloques = new ArrayList<>();
    private int puntaje = 0;
    private boolean juegoEnCurso = true;
    private int contadorToques = 0;
    private int toquesParaAumentarVelocidad = 5;
    private int vidas = 3;
    private Image fondo;
    private Image corazon1;
    private Image corazonVacio;

    private static final int AnchoBloque = 50;
    private static final int AltoBloque = 20;
    private int nivelActual = 1;

    public Tablero() {
        try {
            fondo = ImageIO.read(new URL("file:///D:/arkanoid-main/arkanoid-main/Pong2-main/Pong2-main/imag/cancha.jpg"));
            corazon1 = ImageIO.read(new URL("file:///D:/arkanoid-main/arkanoid-main/Pong2-main/Pong2-main/imag/corazon.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        configurarNivel(nivelActual);
    }

    private void configurarNivel(int nivel) {
        bloques.clear();
        if (nivel == 1) {
            for (int i = 0; i < 5; i++) {
                int x = 100 + i * (AnchoBloque + 20);
                int y = 200;
                bloques.add(new Rectangle2D.Double(x, y, AnchoBloque, AltoBloque));
            }
        } else if (nivel == 2) {
            for (int i = 0; i < 5; i++) {
                int x = 100 + i * (AnchoBloque + 20);
                int y = 200;
                bloques.add(new Rectangle2D.Double(x, y, AnchoBloque, AltoBloque));
            }
            for (int i = 0; i < 5; i++) {
                int x = 100 + i * (AnchoBloque + 20);
                int y = 220;
                bloques.add(new Rectangle2D.Double(x, y, AnchoBloque, AltoBloque));
            }
        } else if (nivel == 3) {
            for (int i = 0; i < 10; i++) {
                int x = 100 + i * (AnchoBloque + 20);
                int y = 200;
                bloques.add(new Rectangle2D.Double(x, y, AnchoBloque, AltoBloque));
            }
            for (int i = 0; i < 10; i++) {
                int x = 100 + i * (AnchoBloque + 20);
                int y = 220;
                bloques.add(new Rectangle2D.Double(x, y, AnchoBloque, AltoBloque));
            }
        }
    }

    public void resetgame() {
        balon.reset();
        p1.resetpaleta();
        puntaje = 0;
        juegoEnCurso = true;
        contadorToques = 0;
        toquesParaAumentarVelocidad = 5;
        vidas = 3;
        nivelActual = 1;
        configurarNivel(nivelActual);
        
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        if (fondo != null) {
            g2.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
        }
        g2.setColor(Color.WHITE);
        dibujar(g2);
        actualizar();
    }

    public void dibujar(Graphics2D g) {
        for (Rectangle2D.Double bloque : bloques) {
            g.fill(bloque);
        }

        balon.dibujar(g);
        g.fill(p1.getPaleta());
        g.drawString("Puntaje: " + puntaje, 10, 20);
        for (int i = 0; i < vidas; i++) {
            g.drawImage(corazon1, 700 + i * 30, 10, this);
        }

        for (int i = vidas; i < 3; i++) {
            g.drawImage(corazonVacio, 700 + i * 30, 10, this);
        }
    }

    public void actualizar() {
        balon.mover(getBounds(), colision(p1.getPaleta()));
        p1.mover_raqueta(getBounds());

        ArrayList<Rectangle2D.Double> bloquesParaEliminar = new ArrayList<>();
        for (Rectangle2D.Double bloque : bloques) {
            if (colision(bloque)) {
                bloquesParaEliminar.add(bloque);
                balon.invertirDireccionVertical();
                puntaje++;
            }
        }

        for (Rectangle2D.Double bloque : bloquesParaEliminar) {
            bloques.remove(bloque);
        }

        if (bloques.isEmpty()) {
         
            nivelActual++;
            if (nivelActual <= 3) {
                configurarNivel(nivelActual);
            } else {
            
                JOptionPane.showMessageDialog(this, "¡Has completado todos los niveles!\nPuntaje final: " + puntaje, "¡Felicidades!", JOptionPane.INFORMATION_MESSAGE);
                resetgame();
                nivelActual = 1;
            }
        }

        if (contadorToques >= toquesParaAumentarVelocidad) {
            balon.aumentarVelocidad();
            contadorToques = 0;
        }

        if (balon.getBalon().getMaxY() >= getHeight()) {
            juegoEnCurso = false;
            vidas = vidas - 1;
            balon = new Balon(800);

            if (vidas == 0) {
                JOptionPane.showMessageDialog(this, "Juego perdido\nPuntaje: " + puntaje, "Game Over", JOptionPane.INFORMATION_MESSAGE);
                resetgame();
            }
        }
    }

    public void iterar() {
        while (juegoEnCurso) {
            try {
                repaint();
                Thread.sleep(6);
            } catch (InterruptedException ex) {
                Logger.getLogger(Tablero.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private boolean colision(Rectangle2D r) {
        return balon.getBalon().intersects(r);
    }
}
