package es.juego;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Raqueta {

    /*
    * Variables que definen los parámetros del objeto Raqueta.
     */
    private int Y = 330;
    private int WIDTH = 60;
    private int HEIGHT = 10;
    private int x = 0;
    private int xa = 0;
    private Principal game;
    //variable y método que sirven para crear clases en el main de 
    //todas las clases

    public Raqueta(Principal game) {
        this.game = game;
    }
    //si no se sale de los márgenes la x suma
    public void move() {
        if (x + xa > 0 && x + xa < game.getWidth() - WIDTH) {
            x = x + xa;
        }
    }
    //método que modifica el color y dibuja la figura
    public void paint(Graphics2D g) {
        g.setColor(Color.GRAY);
        g.fillRect(x, Y, WIDTH, HEIGHT);
    }
    //si no hay ninguna tecla presionada xa = 0,es decir se queda quieto
    public void keyReleased(KeyEvent e) {
        xa = 0;
    }
    //si presiona la izquierda xa = -1 si presiona a la derecha xa = 1
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            xa = -1;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            xa = 1;
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x, Y, WIDTH, HEIGHT);
    }

    public int getTopY() {
        return Y - HEIGHT;
    }
 
    public int getX() {
        return x;
    }
}
