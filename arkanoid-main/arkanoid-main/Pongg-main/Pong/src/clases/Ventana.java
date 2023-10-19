
package clases;

import javax.swing.JFrame;

public class Ventana extends JFrame{
	private static final long serialVersionUID = 1L;
    private final int Ancho=800, Alto=500;
    private Tablero lamina;
    private Hilo hilo;
    public Ventana(){
        setTitle("Pong");
        setSize(Ancho, Alto);
        setLocationRelativeTo(null);
        setResizable(false);
        lamina = new Tablero();
        add(lamina);
        addKeyListener(new Movimiento());
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        hilo = new Hilo(lamina);
        hilo.start();
    }
    
}
