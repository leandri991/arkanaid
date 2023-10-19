
package clases;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Hilo extends Thread{
    Tablero lamina;
    public Hilo(Tablero lamina)
    {
        this.lamina = lamina;
    }
    @Override
    public void run()
    {
        while(true)
        {
            try {
                Thread.sleep(1);
            } catch (InterruptedException ex) {
                Logger.getLogger(Hilo.class.getName()).log(Level.SEVERE, null, ex);
            }
            lamina.repaint();
        }
    }
}
