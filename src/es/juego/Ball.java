package es.juego;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.event.KeyEvent;

public class Ball {

    private static final int DIAMETER = 24;

    /**
     * Variables X e Y son ancho y largo respectivamente Variable xa e ya:
     * xa=-1,significa que se mueve a la izquierde, xa=1,se mueve a la derecha.
     * ya=-1 se mueve hacia arriba,ya=1, se mueve hacia abajo. hacia arriba
     */
    private int x = 18;
    private int y = 308;
    private int xa = 1;
    private int ya = 1;

    private boolean a = false;
    private int speed = 3;

    private Principal game;

    public Ball(Principal game) {
        this.game = game;
    }

    /**
     * move lanzará la excepción InterruptedException cuando el thread entre en
     * juego
     *
     */
    void move() throws InterruptedException {

        //basicamente es que a sera true cuando pulses espacio y speed sera -1
        //cuando el 3,2,1 ya acabe de aparecer.
        if (a == true && speed == -1) {

            //con x e y se mueve la bola
            x = x + xa;
            y = y + ya;

            //si x es 0 y xa es -1 es decir si x esta en el borde y esta 
            //hacia la izq hay que cambiarla de lado o se saldrá de los bordes
            if (x + xa < 0) {
                xa = 1;
            }
            //si el movimiento de la x a la derecha es mayor a la anchura
            //de la ventana - el diametro se mueve a la izq o se saldrá de los
            //márgenes
            if (x + xa > game.getWidth() - DIAMETER) {
                xa = -1;
            }
            //si y es 0 y ya es -1 es decir si y esta en el borde y esta 
            //hacia arriba hay que cambiarla de lado o se saldrá de los bordes
            if (y + ya < 0) {
                ya = 1;
            }
            //si el movimiento de la y hacia abajo es mayor a la altura
            //de la ventana - el diametro saltará el gameover
            if (y + ya > game.getHeight() - DIAMETER) {
                game.gameOver();
            }
            //si colisiona la bola con la raqueta, se obligará a la bola
            //a ir hacia arriba sin modificar la x
            if (collision()) {
                ya = -1;

            }
            //si colisiona la bola con el portal de abajo, se definirá unos
            //nuevos valores para que la bola aparezca por el otro portal
            if (collision2()) {
                x = 250;
                y = 50;

            }
            //si colisiona la bola con la linea de arriba, si ya = 1 significará
            //que la bola va hacia abajo por tanto si cambiará la dirección, si
            //es -1 al contrario.
            if (collision3()) {
                if (ya == 1) {
                    ya = -1;

                } else {
                    ya = 1;

                }

            }
            //si colisiona la bola con la "moneda" de arriba se habrá ganado el
            //juego, así pues se llama al método.
            if (collision4()) {
                game.gameWon();
            }
         
        }
        //si no ha pulsado al espacio y speed distinto a -1, entonces hará
        //el sleep haciendo que el contador 3,2,1 YA funcione
        else if (speed != -1) {
            Thread.sleep(1000);
            --speed;
        } 
        //si no es nada de lo anterior significará que habrá acabado el contador
        //y que aun no se ha pulsado espacio por tanto x es igual al método de 
        //la raqueta+18 debido a que el diametro de la bola es 24/2=12 por tanto
        //la mitad de la raqueta que empieza en 60 es 30 así pues hay que
        //restar 12 a 30 y nos da 18 que será siempre la diferencia para que
        //la bola quede en el centro
        else {

            x = game.racquet.getX() +18;
        }

    }
    //métodos que sirven para detectar las colisiones
    private boolean collision() {
        return game.racquet.getBounds().intersects(getBounds());
    }

    private boolean collision2() {
        return game.portal.getBounds().intersects(getBounds());
    }

    private boolean collision3() {
        return game.linea.getBounds().intersects(getBounds());
    }

    private boolean collision4() {
        return game.moneda.getBounds().intersects(getBounds());
    }

    //clase que modifica el color del objeto a rojo y dibuja un círculo con
    //esos parámetros
    public void paint(Graphics2D g) {
        g.setColor(Color.RED);
        g.fillOval(x, y, DIAMETER, DIAMETER);

    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, DIAMETER, DIAMETER);
    }
    //método que controla las entradas por teclado, si pulsa espacio a==true
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            a = true;
        }
    }

    
    public int getScore() {
        return speed;
    }

}
