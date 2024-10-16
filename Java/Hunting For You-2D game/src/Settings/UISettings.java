package Settings;

import Exeptions.ExceptionManager;

import game.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/*! \fn public class AudioSettings extends Observer
    \brief Gestioneaza setarile interfetei cu jucatorul.
 */
public class UISettings extends Observer {
    Game game;/*!< Variabila ce primeste referinta la game.*/
    boolean paintLife = true, paintScore = true;/*!< Variabile care deccid daca sa se deseneze punctele de viata sau scorul.*/
    BufferedImage score;/*!< Imaginea scorului.*/
    Font messagefont;/*!< Culoarea mesajului desenat pe ecran.*/
    BufferedImage[] health = new BufferedImage[9];/*!< Imaginea punctelor de viata.*/
    Font scoreFont;/*!< Culoarea scoruluii desenat pe ecran.*/

    /*! \fn public UISettings(Settings subject, Game game)
       \brief Constructorul clasei.

       \param subject Referinta care anunta daca setarea de interfata cu jucatorul a fost activata.
       \param game Referinta la Game.
    */
    public UISettings(Settings subject, Game game) {
        this.game = game;
        this.subject = subject;
        this.subject.attach(this);
        GetImages();
        messagefont = new Font("font", Font.BOLD, 90);
        scoreFont = new Font("Plain", Font.PLAIN, 40);
    }

    /*! \fn public void update()
       \brief Actualizeaza starea setarii de desenare a UI.
    */
    @Override
    public void update() {
        paintLife = subject.getLife();
        paintScore = subject.getScore();
    }

    /*! \fn    public void playAudio()
         \brief  Porneste sau opreste desenare UI, in functie de variabilele paintLife si paintScore.
        \param g Instrumentul de desenare.
        */
    public void draw(Graphics2D g) {
        if (paintScore && paintLife) {
            DrawLife(g);
            DrawScore(g);
        } else if (paintLife) {
            DrawLife(g);
        } else if (paintScore) {
            DrawScore(g);
        }
    }


    /*! \fn  public void DrawScore(Graphics2D g)
         \brief Functie de desenare a scorului.
        \param g Instrumentul de desenare.
        */
    public void DrawScore(Graphics2D g) {
        g.drawImage(score, 900, 0, 200, 50, null);
        g.setFont(messagefont);
        g.setColor(Color.BLACK);

        g.setFont(scoreFont);
        g.setColor(Color.WHITE);
        String str = "" + game.Score + "";

        g.drawString(str, 1100, 42);
    }

    /*! \fn   public void DrawLife(Graphics2D g)
        \brief Functie de desenare a punctelor de viata.
       \param g Instrumentul de desenare.
       */
    public void DrawLife(Graphics2D g) {
        int i = 0;
        int max = game.player.maxLife;
        int life = game.player.life;

        if (life > 0) {
            if (life == max) g.drawImage(health[4], 0, 0, 200, 50, null);
            else if (life > max * 0.25 && life <= max * 0.5) g.drawImage(health[2], 0, 0, 200, 50, null);
            else if (life > max * 0.5 && life < max) {
                g.drawImage(health[3], 0, 0, 200, 50, null);
            } else if (life <= max * 0.25) {
                g.drawImage(health[1], 0, 0, 200, 50, null);
            }
        } else {
            g.drawImage(health[0], 0, 0, 200, 50, null);
        }

    }

    /*! \fn   public void GetImages()
            \brief Functie ce initializeaza variabiele imaginilor legate de scor si puncte de viata.

           */
    public void GetImages() {
        ExceptionManager a = new ExceptionManager();
        try {
            //TODO  un vect cu   score
            health[0] = ImageIO.read(UISettings.class.getResource("/ui/health empty 200x50.png"));
            health[1] = ImageIO.read(UISettings.class.getResource("/ui/health 25 200x50.png"));
            health[2] = ImageIO.read(UISettings.class.getResource("/ui/health half 200x50.png"));
            health[3] = ImageIO.read(UISettings.class.getResource("/ui/health 75 200x50.png"));
            health[4] = ImageIO.read(UISettings.class.getResource("/ui/health 200x50.png"));
            score = ImageIO.read(UISettings.class.getResource("/ui/score 170x50.png"));
        } catch (IOException e) {
            a.IOException(e);
        }
    }
}

