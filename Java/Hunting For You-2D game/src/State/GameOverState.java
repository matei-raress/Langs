
package State;

import Entities.Player;
import InputHandler.KeyHandler;
import game.Game;

import java.awt.*;
import java.io.IOException;

/*! \class public class GameOverState extends State
    \brief State-ul de joc pierdut

    In momentul in care viata jucatorului scade sub 0, starea jocului trece in GameOver.
 */

public class GameOverState extends State {
    Game game;/*!< Variabila ce va primi referinta la clasa Game.*/
    Font font;/*!< Marimea si fontul pentru scrierea mesajului de pierdere.*/
    Font font2;/*!< Marimea si fontul pentru scrierea mesajului de restart.*/
    public State prev;/*!< Starea din care s-a pierdut jocul.*/
    KeyHandler keyHandler;/*!< Variabila care observa daca s-a apasat tasta Enter.*/

    /*! \fn   public GameOverState(Game game,KeyHandler keys)
        \brief Constructorul clasei GameOverState.
        \param game Referinta la clasa Game.
        \param keys Referinta la handlerul tastaturii.
     */
    public GameOverState(Game game, KeyHandler keys) {
        super(game);
        this.game = game;
        this.keyHandler = keys;

        font = new Font("bold", Font.BOLD, 90);
        font2 = new Font("bold", Font.BOLD, 30);
    }

    /*! \fn    public void Update()
       \brief In momentul cand s-a intrat in starea GameOver si se apasa Enter se trece la ultimul checkpoint.
    */
    @Override
    public void Update() {

        if (keyHandler.restart) {
            State.SetState(State.GetPreviousState());
            game.player.setValues();
            Game.Restart = true;
        }
    }

    /*! \fn   public void Draw(Graphics2D g)
          \brief Desenarea imaginii de GameOver.
          \param g Instrumentul de desenare.
       */
    @Override
    public void Draw(Graphics2D g) {

        game.setBackground(Color.BLACK);
        g.setFont(font);
        g.setColor(Color.red);
        g.drawString("YOU LOST", 450, 300);
        g.setFont(font2);
        g.setColor(Color.yellow);
        g.drawString("Score " + game.Score, 550, 400);
        g.setFont(font2);
        g.setColor(Color.red);
        g.drawString("Press Enter to start from the last checkpoint", 370, 500);

    }

    @Override
    public void SetStateValues() {

    }

}


