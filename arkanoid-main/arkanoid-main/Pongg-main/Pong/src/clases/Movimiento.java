package clases;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Movimiento extends KeyAdapter{
	static boolean left, right,a,d,u;
	
	public void keyPressed(KeyEvent e)
	{
	int id = e.getKeyCode();
	
	
	if(id == KeyEvent.VK_A)
	{
		a = true;
	}
	if(id == KeyEvent.VK_D)
	{
		d = true;
	}
	if(id == KeyEvent.VK_LEFT)
	{
		left = true;
	}
	if(id == KeyEvent.VK_RIGHT)
	{
		right = true;
	}
	}
	public void keyReleased(KeyEvent e)
	{
	int id = e.getKeyCode();
	
	if(id == KeyEvent.VK_A)
	{
		a = false;
	}
	if(id == KeyEvent.VK_D)
	{
		d = false;
	}
	if(id == KeyEvent.VK_LEFT)
	{
		left = false;
	}
	if(id == KeyEvent.VK_RIGHT)
	{
		right = false;
	}
	}

}
