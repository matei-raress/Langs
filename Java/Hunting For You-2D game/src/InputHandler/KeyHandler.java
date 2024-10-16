package InputHandler;

import game.Game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/*! \class public class KeyHandler implements KeyListener
    \brief Gestioneaza input-ul de la tastatura.

 */
public class KeyHandler implements KeyListener {

    public boolean restart;/*!< Variabila folosita pentru a reincepe jocul cand player-ul a pierdut.*/
    public boolean upPress, downPress, leftPress, rightPress, spacePress, num0Pressed, num1Pressed, num2Pressed, num3Pressed;
    /*!< Toate variabilele reprezinta apasarea tastei din nume.*/

    /*! \fn public void keyPressed(KeyEvent k)
        \brief Observa input-ul tastaturii si seteaza variabila corespunzatoare.
        \param k Evenimentul cand o tasta a fost apasata.
     */

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent k) {

        int code = k.getKeyCode();

        if (code == KeyEvent.VK_SPACE) {
            spacePress = true;
        }
        if (code == KeyEvent.VK_W) {
            upPress = true;
        }
        if (code == KeyEvent.VK_S) {
            downPress = true;
        }
        if (code == KeyEvent.VK_A) {
            leftPress = true;
        }
        if (code == KeyEvent.VK_D) {
            rightPress = true;
        }
        if (code == KeyEvent.VK_ESCAPE || code == 80) {

            if (!Game.Pause) {
                Game.Pause = true;
            } else {
                Game.Pause = false;
            }
        }
        switch (code) {
            case 96:
                num0Pressed = true;
                break;
            case 97:
                num1Pressed = true;
                break;
            case 98:
                num2Pressed = true;
                break;
            case 99:
                num3Pressed = true;
                break;
        }
        if (code == KeyEvent.VK_ENTER) {
            restart = true;
        }
    }

    /*! \fn public void keyReleased(KeyEvent k)
            \brief Observa daca s-a eliberat tasta apasata si seteaza variabila corespunzatoare.
            \param k Evenimentul cand o tasta a fost eliberata.
         */
    @Override
    public void keyReleased(KeyEvent k) {

        int code = k.getKeyCode();
        if (code == KeyEvent.VK_SPACE) {
            spacePress = false;
        }
        if (code == KeyEvent.VK_W) {
            upPress = false;
        }
        if (code == KeyEvent.VK_S) {
            downPress = false;
        }
        if (code == KeyEvent.VK_A) {
            leftPress = false;
        }
        if (code == KeyEvent.VK_D) {
            rightPress = false;
        }
        switch (code) {
            case 96:
                num0Pressed = false;
                break;
            case 97:
                num1Pressed = false;
                break;
            case 98:
                num2Pressed = false;
                break;
            case 99:
                num3Pressed = false;
                break;
        }
        if (code == KeyEvent.VK_ENTER) {
            restart = false;
        }
    }


}
