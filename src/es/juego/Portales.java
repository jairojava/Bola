package es.juego;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Portales {
    /*
    * Variables que definen los parámetros del objeto Portales.
     */
    private static final int a = 250;
    private static final int b = 50;
    private static final int Y = 200;
    private static final int WIDTH = 30;
    private static final int HEIGHT = 10;
    private int x = 0;
    
    private Principal game;

    public Portales(Principal game) {
        this.game = game;
    }
    //método que modifica el color y dibuja los portales
    public void paint(Graphics2D g) {
        g.setColor(Color.ORANGE);
        g.fillOval(x, Y, WIDTH, HEIGHT);
        g.fillOval(a, b, WIDTH, HEIGHT);
    }
  
    public Rectangle getBounds() {
        return new Rectangle(x, Y, WIDTH, HEIGHT);
    }

}
