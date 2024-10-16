package game;

import javax.swing.*;
import java.io.IOException;

/*! \class public class Window
    \brief Construieste ferreastra jocului.

    Implementeaza sablonul de proiectare Singleton.
 */
public class Window {
    private static Window single_instance = null;/*!< Variabila care verifica daca s-a mai instantiat o dta clasa Window.*/
    public String title;/*!< Titlul jocului.*/
    public int width;/*!< Latimea ferestrei.*/
    public int height;/*!< Inaltimea ferestrei.*/
    public static JFrame window;/*!< Fereastra jocului.*/

    /*! \fn private Window(String title, int Width, int Height)
        \brief Constructorul clasei Window.
        \param title Titlul ferestrei.
        \param Width latimea ferestrei.
        \param Height inaltimea ferestrei.
     */
    private Window(String title, int Width, int Height) {
        this.title = title;
        this.width = Width;
        this.height = Height;
        window = new JFrame(title);
    }

    /*! \fn public static Window getInstance(String title, int Width, int Height)
        \brief Returneaza o instanta a ferestrei daca nu a mai fost instantiata.
        \param title Titlul ferestrei.
        \param Width latimea ferestrei.
        \param Height inaltimea ferestrei.

     */
    public static Window getInstance(String title, int Width, int Height) {
        if (single_instance == null)
            single_instance = new Window(title, Width, Height);
        return single_instance;
    }
/*! \fn public void Init(Game game)
    \brief Functie folosita in constructor pentru a activa diferite proprietati ale ferestrei.
 */

    public void Init(Game game) {
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Hunting fo you ");
        window.add(game);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}
