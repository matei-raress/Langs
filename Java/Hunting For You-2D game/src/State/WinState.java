package State;

import Exeptions.ExceptionManager;
import InputHandler.MouseHandler;
import game.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/*! \class public class WinState extends State
    \brief State-ul de joc castigat

   In momentul in care viata regelui scade sub 0, starea jocului trece in Win.
            */

public class WinState extends State {
    Game game;/*!< Variabila ce va primi referinta la clasa Game.*/
    State prev;/*!< Starea din care s-a pierdut jocul.*/
    BufferedImage winScroll, menu, menuOver, girl, boy;/*!< Imagini pentru: fundalul de win, butonul de meniu,butonul de meniu cand cursorul eeste deasupra, sotie si vanator.*/
    Font fontWin, write;/*!< Marimea si fontul pentru scrierea mesajului de castig, si pentru informatii.*/
    public Boolean gameScoreUpdate = true;/*!< Determina daca s-a incarcat scorul.*/
    int Place;/*!< Locul pe care se pozitioneaza player-ul in leaderboard.*/
    MouseHandler mouse;/*!< Variabila ce va primi referinta la handler de mouse.*/

    /*! \fn public WinState(Game game, MouseHandler mouse)
       \brief Constructorul clasei WinState.
       \param game Referinta la clasa Game.
       \param keys Referinta la handlerul mouse-ului.
    */
    public WinState(Game game, MouseHandler mouse) {
        super(game);
        this.game = game;
        this.mouse = mouse;
        GetImages();
        fontWin = new Font("font", Font.BOLD, 90);
        write = new Font("font", Font.PLAIN, 30);

    }

    /*! \fn    public void Update()
       \brief In momentul cand s-a intrat in starea WinState si se apasa pe butonul menu se trece in state MenuState.
       Aici se pune scorul in baza de date si se extrage locul din leaderboard pt afisare.
    */
    @Override
    public void Update() {
        prev = State.GetPreviousState();

        if (gameScoreUpdate) {
            System.out.println("Win");
            game.Score += 100;
            game.Score = game.Score + game.player.life * 10;
            game.database.insertScoreInDatabase(game.Score);
            Place = game.database.extractPlace(game.Score);
            gameScoreUpdate = false;
        }
        if (mouse.mousePress && mouse.mouseOverMenu) {
            State.SetState(game.menuState);
        }
    }

    /*! \fn   public void Draw(Graphics2D g)
        \brief Desenarea imaginii de WinState.
        Deseneaza imaginilie din state-ul precedent si meniul de castig.
        \param g Instrumentul de desenare.
     */
    @Override
    public void Draw(Graphics2D g) {

        if (State.GetPreviousState() == game.levelTwoState) {
            if (prev != null) {
                prev.Draw(g);
            }
            DrawWin(g);
        }

    }

    @Override
    public void SetStateValues() {
    }

    /*! \fn   void DrawWin(Graphics2D g)
           \brief Desenarea meniului de castig

           \param g Instrumentul de desenare.
        */
    void DrawWin(Graphics2D g) {
        g.setFont(fontWin);
        g.setColor(Color.BLACK);
        g.drawImage(winScroll, 300, 0, 800, 800, null);
        g.drawImage(girl, 620, 345, 80, 80, null);
        g.drawImage(boy, 692, 350, 36, 72, null);
        g.drawString("YOU WON", 470, 300);
        String suf = "";
        switch (Place % 10) {
            case 1:
                suf = "st";
                break;
            case 2:
                suf = "nd";
                break;
            case 3:
                suf = "rd";
                break;
            default:
                suf = "th";
                break;
        }

        g.setFont(write);
        g.drawString("You are in the " + Place + "" + suf + " place with a score of " + game.Score + " points", 330, 500);

        if (mouse.mouseOverMenu) {
            g.drawImage(menuOver, 550, 550, 270, 100, null);
        } else {
            g.drawImage(menu, 550, 550, 270, 100, null);
        }

    }

    /*! \fn    public void GetImages()
         \brief Initializarea imaginilor din meniul de castig.

         \param g Instrumentul de desenare.
      */
    public void GetImages() {
        ExceptionManager a = new ExceptionManager();
        try {
            winScroll = ImageIO.read(WinState.class.getResource("/ui/UI.png"));
            menu = ImageIO.read(WinState.class.getResource("/meniu/meniu_button.png"));
            menuOver = ImageIO.read(WinState.class.getResource("/meniu/meniu_button_over.png"));
            girl = ImageIO.read(WinState.class.getResource("/meniu/win.png"));
            boy = ImageIO.read(WinState.class.getResource("/player/final.png"));
        } catch (IOException e) {
            a.IOException(e);
        }
    }
}
