package es.juego;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Linea {
    /*
    * Variables que definen los parámetros del objeto Linea.
    */
    private static final int X = 0;
    private static final int WIDTH = 290;
    private static final int HEIGHT = 10;
    private int y = 100;

    //variable y método que sirven para crear clases en el main de 
    //todas las clases
    private Principal game;

    public Linea(Principal game) {
        this.game = game;
    }
    //método que modifica el color y dibuja la figura
    public void paint(Graphics2D g) {
        g.setColor(Color.DARK_GRAY);
        g.fillRect(X, y, WIDTH, HEIGHT);
    }

    public Rectangle getBounds() {
        return new Rectangle(X, y, WIDTH, HEIGHT);
    }
    //devuelve la y - el alto
    public int getTopY() {
        return y - HEIGHT;
    }
}
