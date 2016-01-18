package es.juego;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Moneda{
    /*
    * Variables que definen los parámetros del objeto Moneda.
     */
    private static final int X = 30;
    private static final int WIDTH = 10;
    private static final int HEIGHT = 10;
    private int y = 40;
    private int xa = 0;
    private Principal game;

    public Moneda(Principal game) {
        this.game = game;
    }
    //método que modifica el color y dibuja la figura
    public void paint(Graphics2D g) {
        g.setColor(Color.BLUE);
        g.fillOval(X, y, WIDTH, HEIGHT);
    }

    public Rectangle getBounds() {
        return new Rectangle(X, y, WIDTH, HEIGHT);
    }
}
