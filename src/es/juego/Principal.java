package es.juego;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Principal extends JPanel {
    //Se crean objetos de las clases que serán la referencia en esta clase 
    Ball ball = new Ball(this);
    Raqueta racquet = new Raqueta(this);
    Portales portal = new Portales(this);
    Linea linea = new Linea(this);
    Moneda moneda = new Moneda(this);

    public Principal() {
        /**
         * Métodos que sobreescriben los métodos de las clases keyEvent
         * y keyListener
         */
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {

                racquet.keyReleased(e);

            }

            @Override
            public void keyPressed(KeyEvent e) {

                racquet.keyPressed(e);
                ball.keyPressed(e);
            }

        });
        setFocusable(true);
    }
    /**
     * el main de la clase Principal captura la excepción interruptedexception
    *que va a producir el thread.sleep que será lo que modificará la velocidad
    * de la bola
    */
    public static void main(String[] args) throws InterruptedException {
        //Aqui se crea una nueva ventana que va a sacar el texto Portal Pong
        JFrame frame = new JFrame("Portal Pong");
       
        //Creamos y añadimos game que será el objeto donde irán todos los paints
        //de todas las clases
        Principal game = new Principal();
        frame.add(game);
        //modificamos la ventana
        frame.setSize(300, 400);
        //le decimos que sea visible
        frame.setVisible(true);
        //le ponemos una opción para cerrar la ventana
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //bucle infinito que ejecuta los moves y los paint y 
        //le hace sleep a la bola
        while (true) {
            game.move();
            game.repaint();
            Thread.sleep(6);
        }
    }
    //aquí se llaman los move de las clases, se captura la excepción
    //InterruptedException debido a que también se produce en el método move de 
    //ball
    private void move() throws InterruptedException  {
        ball.move();
        racquet.move();

    }
    //Sobreescribe el método paint de graphics para poder pintar.
    @Override
    public void paint(Graphics g) {

        super.paint(g);
        //Se modifica el color del fondo
        int fila = 0;
        for (int rojo = 0; rojo <= 400; rojo++) {
            Color col = Color.LIGHT_GRAY;
            g.setColor(col);
            g.drawLine(0, fila, 300, fila);
            fila++;
        }
        //Aqui acaba el color del fondo
        Graphics2D g2d = (Graphics2D) g;
        //el antialiasing quita los pixeles de los paints
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        ball.paint(g2d);
        racquet.paint(g2d);
        portal.paint(g2d);
        linea.paint(g2d);
        moneda.paint(g2d);

        //si speed mayor de 0 escribirá en color Pink y en fuente Roma_Baseline
        //el speed en tamaño 120,200
        if (ball.getScore() > 0) {
            g2d.setColor(Color.PINK);
            g2d.setFont(new Font("Verdana", Font.ROMAN_BASELINE, 100));
            g2d.drawString(String.valueOf(ball.getScore()), 120, 200);
        }//si es igual que 0 escribirá Ya en color Pink,en fuente roman_baseline
        //en tamaño 70,200
            else if (ball.getScore() == 0) {
            g2d.setColor(Color.PINK);
            g2d.setFont(new Font("Verdana", Font.ROMAN_BASELINE, 100));
            g2d.drawString(String.valueOf("YA"), 70, 200);
        }

    }
    //el método gameOver te dice el texto YOU LOSE y aborta todos los procesos
    public void gameOver() {

        JOptionPane.showMessageDialog(this, "YOU LOSE!", "Game Over", JOptionPane.YES_NO_OPTION);
        System.exit(ABORT);
    }
     //el método gameWon te dice el texto YOU WIN y aborta todos los procesos
    public void gameWon() {

        JOptionPane.showMessageDialog(this, "¡¡YOU WIN!!", "Stage 1 Complete", JOptionPane.INFORMATION_MESSAGE);
        System.exit(ABORT);
    }

}
