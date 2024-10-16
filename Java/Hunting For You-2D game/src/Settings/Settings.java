package Settings;

import java.util.ArrayList;
import java.util.List;

/*! \class public class Settings
    \brief Notifica celelalte componenente pentru a actualiza setarile.

    Implementeaza sablonul de proiectare Observer, notifica setarile AudioSettings si UISettings.
 */
public class Settings {
    private List<Observer> observers = new ArrayList<Observer>();/*!< Lista de Observer.*/
    private boolean AudioOn = true; /*<! Verifica daca sunetul e activat.*/
    private boolean ScoreOn = true;/*<! Verifica daca desenarea scorului e activata.*/
    private boolean LifeOn = true;/*<! Verifica daca desenarea barei de viata e activata..*/

    /*! \fn  public boolean getAudio()
        \brief Returneaza AudioOn
     */

    public boolean getAudio() {
        return AudioOn;
    }

    /*! \fn  public boolean getScore()
        \brief Returneaza ScoreOn
     */
    public boolean getScore() {
        return ScoreOn;
    }

    /*! \fn  public boolean getLife()
        \brief Returneaza LifeOn
     */
    public boolean getLife() {
        return LifeOn;
    }

    /*! \fn  public void setState(boolean Audio,boolean Score,boolean Life)
       \brief Seteaza variabilele setarilor si actualizeaza starea celorlalti observeri.
        \param Audio Setarea pt audio.
        \param Score Setarea pentru desenarea scorului.
        \param Life Setarea pentru desenarea punctelor de viata.
    */
    public void setState(boolean Audio, boolean Score, boolean Life) {

        this.AudioOn = Audio;
        this.ScoreOn = Score;
        this.LifeOn = Life;

        notifyAllObservers();
    }

    /*! \fn public void attach(Observer observer)
        \brief Adauga un nou observer in lista.
     */
    public void attach(Observer observer) {
        observers.add(observer);
    }

    /*! \fn public void notifyAllObservers()
        \brief Notifica ceilalalti observatori sa isi schimbe starea.
     */
    public void notifyAllObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }
}