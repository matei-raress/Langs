package State;

import game.Game;

import java.awt.*;
import java.io.IOException;

/*! \class public abstract class State
    \brief Seteaza starea jocului.

    Implementeaza sablonul de proiectare State si se ocupa sa seteze starea trecuta si curenta.
 */
public abstract class State {
    private static State previousState = null; /*!< Referinta catre starea anterioara a jocului.*/
    private static State currentState = null; /*!< Referinta catre starea curenta a jocului*/
    protected Game game;/*!< Variabila care primeste referinta la clasa Game.*/

    /*! \fn  public State(Game game)
        \brief Constructorul clasei State.
        \param game Referinta la clasa Game.
 */
    public State(Game game) {
        this.game = game;
    }

    /*! \fn public static void SetState(State state)
      \brief Seteaza starea curenta si starea trecuta

      \param state Starea care va deveni starea curenta.
*/
    public static void SetState(State state) {
        previousState = currentState;
        currentState = state;
        System.out.println(" Previous                            " + previousState);
        System.out.println(" Curent                            " + currentState);

    }

    /*! \fn  public static State GetState()
        \brief Returneaza currentState

  */
    public static State GetState() {
        return currentState;
    }

    /*! \fn public static State GetPreviousState()
       \brief Returneaza previousState

 */
    public static State GetPreviousState() {
        return previousState;
    }

    /*! \fn  public abstract void Update()
       \brief Actualizeaza elementele folosite in stare.

 */
    public abstract void Update();

    /*! \fn   public abstract void Draw(Graphics2D g)
           \brief Deseneaza elementele folosite in stare.
           \param g Instrumentul de desenare
     */
    public abstract void Draw(Graphics2D g);

    /*! \fn    public abstract  void SetStateValues()
           \brief Initializeaza elementele de la inceputul starii..

     */
    public abstract void SetStateValues();


}
