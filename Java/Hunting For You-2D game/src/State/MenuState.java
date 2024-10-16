package State;

import Exeptions.ExceptionManager;
import InputHandler.MouseHandler;
import Settings.Settings;
import game.Game;
import game.Window;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/*! \class public class MenuState extends State
    \brief Gestioneazae elementele meniului.

    Aici se face verificare butoanelor apasate pentru a intra in submeniuri.
 */
public class MenuState extends State {
    Game game;/*!< Variabila care va primi referinta la clasa Game.*/
    BufferedImage fundal;/*!< Imaginea de fundal.*/
    BufferedImage titlu;/*!< Imaginea titlului.*/
    BufferedImage[] buttons = new BufferedImage[6];/*!< Imaginea butoanelor.*/
    Font font;/*!< Fontul si marimea scrisului din meniu.*/
    boolean[] b = new boolean[3];/*!< Variabile ce ajuta la setarile jocului.*/
    public boolean settingsExtracted = true;/*!< Determina daca setarile au fost extrase din baza de date.*/
    MouseHandler mouse;/*!< Variabila ce primeste referinta la MouseHandler.*/
    public boolean helpDraw = false;/*!< Determina daca se deseneaza meniul help.*/
    public boolean playDraw = false;/*!< Determina daca se deseneaza meniul play.*/
    public boolean leaderDraw = false;/*!< Determina daca se deseneaza meniul leaderboard.*/
    public boolean settingsDraw = false;/*!< Determina daca se deseneaza meniul de setari.*/
    public boolean settingsChanged = false;/*!< Determina daca s-au schimbat setarile.*/
    public boolean readFromDatabase = true;/*!< Determina daca s-au extras primele 10 locuri din baza de date.*/
    public boolean AudioChanged = false;/*!< Determina daca s-a schimbat setarea de audio.*/
    BufferedImage text;/*!< Imaginea pentru textul cu instructiuni.*/
    BufferedImage instructions;/*!< Imaginea pentru textul cu instructiuni.*/
    BufferedImage backButton, backButtonOver, newGame, newGameOver, loadGame, loadGameOver, leader, leaderOver, settingsButton, settingsButtonOver, yes, no, yesOver, noOver;
    /*!< Imaginile pentru butoane si pentru butoane over.*/
    Settings settings;/*!< Variabila ce primeste referinta la Settings.*/
    int audio = 1, score = 1, life = 1;/*!< Determina ce setari sunt active.*/
    int[] a = new int[3];/*!< Ajuta la extragerea setarilor in baza de date.*/

    /*! \fn  public MenuState(Game game, MouseHandler mouseHandler, Settings settings)
        \brief Constructorul clasei MenuState.

        \param game Referinta la clasa Game.
        \param mouseHandler Referinta la mouse handler..
        \param settings Referinta la setari.
      */
    public MenuState(Game game, MouseHandler mouseHandler, Settings settings) {
        super(game);
        this.game = game;
        this.mouse = mouseHandler;
        this.settings = settings;

        b[0] = true;
        b[1] = true;
        b[2] = true;

        System.out.println("" + b[0] + b[1] + b[2] + "");
        GetImages();
    }

    /*! \fn   void DrawWin(Graphics2D g)
        \brief Actualizeaza afisarea meniului.

              Verifica pozitionarea mouse-ului si daca s-a dat click, in functie de asta, se afiseaza leaderboard
              setarile, controalele, meniul de play si iesirea din joc.
      */
    @Override
    public void Update() {

        if (mouse.mousePress && mouse.mouseOverStart) {
            playDraw = true;
        }
        //Close game
        else if (mouse.mousePress && mouse.mouseOverExit) {

            Window.window.dispose();
            game.gameThread = null;
        } else if (mouse.mouseOverLoad && mouse.mousePress) {
            int a = game.database.extractSave();
            Game.Restart = true;
            playDraw = false;
            if (a == 1) {
                State.SetState(game.levelOneState);
            } else if (a == 2) {
                State.SetState(game.levelTwoState);
            }


        } else if (mouse.mouseOverHelp && mouse.mousePress) {
            helpDraw = true;
        } else if (mouse.mouseOverLeader && mouse.mousePress) {
            leaderDraw = true;
        } else if (mouse.mouseOverSettings && mouse.mousePress) {
            settingsDraw = true;
        } else if (mouse.mouseOverNewGame && mouse.mousePress) {
            State.SetState(game.levelOneState);
            Game.Restart = true;
            playDraw = false;

        } else if (settingsDraw && mouse.mouseOverNoAudio && mouse.mousePress) {
            audio = 0;
            settingsChanged = true;
            settingsExtracted = true;
        } else if (settingsDraw && mouse.mouseOverNoScore && mouse.mousePress) {
            score = 0;
            settingsChanged = true;
            settingsExtracted = true;
        } else if (settingsDraw && mouse.mouseOverNoLife && mouse.mousePress) {
            life = 0;
            settingsChanged = true;
            settingsExtracted = true;
        } else if (settingsDraw && mouse.mouseOverYesAudio && mouse.mousePress) {
            audio = 1;
            settingsChanged = true;
            settingsExtracted = true;
        } else if (settingsDraw && mouse.mouseOverYesScore && mouse.mousePress) {
            score = 1;
            settingsChanged = true;
            settingsExtracted = true;
        } else if (settingsDraw && mouse.mouseOverYesLife && mouse.mousePress) {
            life = 1;
            settingsChanged = true;
            settingsExtracted = true;
        }

        if (settingsChanged) {
            game.database.insertSettingsInDatabase(audio, score, life);
            settingsChanged = false;
        }

        boolean a = b[0];
        if (settingsExtracted) {
            b = game.database.extractSettings();
            settingsExtracted = false;
        }

        if (b[0] != a) {
            AudioChanged = true;
        }


        settings.setState(b[0], b[1], b[2]);

    }

    /*! \fn   void DrawWin(Graphics2D g)
              \brief Desenarea meniului .

              \param g Instrumentul de desenare.
           */
    @Override
    public void Draw(Graphics2D g) {
        g.drawImage(fundal, 0, 0, 1376, 800, null);

        g.drawImage(titlu, 300, 50, 800, 200, null);

        if (mouse.mouseOverStart) {
            g.drawImage(buttons[3], 550, 350, 270, 100, null);
        } else {
            g.drawImage(buttons[0], 550, 350, 270, 100, null);
        }

        if (mouse.mouseOverExit) {
            g.drawImage(buttons[4], 550, 500, 270, 100, null);
        } else {
            g.drawImage(buttons[1], 550, 500, 270, 100, null);
        }
        if (mouse.mouseOverHelp) {
            g.drawImage(buttons[5], 550, 610, 70, 70, null);
        } else {
            g.drawImage(buttons[2], 550, 610, 70, 70, null);
        }
        if (mouse.mouseOverLeader) {
            g.drawImage(leader, 750, 610, 70, 70, null);
        } else {
            g.drawImage(leaderOver, 750, 610, 70, 70, null);
        }
        if (mouse.mouseOverSettings) {
            g.drawImage(settingsButton, 1200, 700, 70, 70, null);
        } else {
            g.drawImage(settingsButtonOver, 1200, 700, 70, 70, null);
        }


        if (playDraw) {
            PlayDraw(g);
            if (mouse.mouseOverBack && mouse.mousePress) {
                playDraw = false;
            }
        } else if (helpDraw) {
            DrawHelp(g);
            if (mouse.mouseOverBack && mouse.mousePress) {
                helpDraw = false;
            }
        } else if (leaderDraw) {
            LeaderDraw(g);
            if (mouse.mouseOverBack && mouse.mousePress) {
                leaderDraw = false;
            }
        } else if (settingsDraw) {
            SettingsDraw(g);
            if (mouse.mouseOverBack && mouse.mousePress) {
                settingsDraw = false;
            }
        }
    }

    /*! \fn   void DrawWin(Graphics2D g)
              \brief Desenarea meniului de setari.

              \param g Instrumentul de desenare.
           */
    public void SettingsDraw(Graphics2D g) {
        g.drawImage(fundal, 0, 0, 1376, 800, null);
        Font instructions = new Font("", Font.BOLD, 40);
        g.setFont(instructions);
        g.setColor(Color.BLACK);
        g.drawString("Show Player's Life :", 440, 320);
        g.drawString("Show Player's Score :", 440, 390);
        g.drawString("Enable Audio :", 440, 460);
        if (mouse.mouseOverYesLife) {
            g.drawImage(yes, 900, 250, 70, 70, null);
        } else {
            g.drawImage(yesOver, 900, 250, 70, 70, null);
        }
        if (mouse.mouseOverNoLife) {
            g.drawImage(no, 970, 250, 70, 70, null);
        } else {
            g.drawImage(noOver, 970, 250, 70, 70, null);
        }
        if (mouse.mouseOverYesScore) {
            g.drawImage(yes, 900, 320, 70, 70, null);
        } else {
            g.drawImage(yesOver, 900, 320, 70, 70, null);
        }
        if (mouse.mouseOverNoScore) {
            g.drawImage(no, 970, 320, 70, 70, null);
        } else {
            g.drawImage(noOver, 970, 320, 70, 70, null);
        }
        if (mouse.mouseOverYesAudio) {
            g.drawImage(yes, 900, 390, 70, 70, null);
        } else {
            g.drawImage(yesOver, 900, 390, 70, 70, null);
        }
        if (mouse.mouseOverNoAudio) {
            g.drawImage(no, 970, 390, 70, 70, null);
        } else {
            g.drawImage(noOver, 970, 390, 70, 70, null);
        }


        if (mouse.mouseOverBack) {
            g.drawImage(backButtonOver, 10, 700, 150, 80, null);
        } else {
            g.drawImage(backButton, 10, 700, 150, 80, null);
        }
    }

    /*! \fn   void DrawWin(Graphics2D g)
          \brief Desenarea leaderboard.

          \param g Instrumentul de desenare.
       */
    public void LeaderDraw(Graphics2D g) {
        g.drawImage(fundal, 0, 0, 1376, 800, null);


        if (readFromDatabase) {
            a = game.database.extractFirst10();
            readFromDatabase = false;
        }
        Font leaderboard = new Font("", Font.PLAIN, 90);
        g.setFont(leaderboard);
        g.setColor(Color.BLACK);
        g.drawString("Hall of Fame", 400, 100);
        Font instructions = new Font("", Font.PLAIN, 40);
        g.setFont(instructions);
        g.setColor(Color.BLACK);
        for (int i = 0; i < a.length; i++) {
            g.drawString("" + (i + 1) + ". " + a[i] + "", 540, 250 + i * 40);
        }

        if (mouse.mouseOverBack) {
            g.drawImage(backButtonOver, 10, 700, 150, 80, null);
        } else {
            g.drawImage(backButton, 10, 700, 150, 80, null);
        }
    }

    /*! \fn   void DrawWin(Graphics2D g)
              \brief Desenarea informatiilor legate de joc.

              \param g Instrumentul de desenare.
           */
    public void DrawHelp(Graphics2D g) {
        g.drawImage(fundal, 0, 0, 1376, 800, null);
        g.drawImage(instructions, 100, 10, 700, 150, null);
        g.drawImage(text, 100, 170, 600, 500, null);
        Font leaderboard = new Font("", Font.ITALIC, 71);
        g.setFont(leaderboard);
        g.setColor(Color.BLACK);
        g.drawString("Space: Dash", 700, 220);

        if (mouse.mouseOverBack) {
            g.drawImage(backButtonOver, 10, 700, 150, 80, null);
        } else {
            g.drawImage(backButton, 10, 700, 150, 80, null);
        }
    }

    /*! \fn   void DrawWin(Graphics2D g)
              \brief Desenarea meniului de incepere de joc.

              \param g Instrumentul de desenare.
           */
    public void PlayDraw(Graphics2D g) {
        g.drawImage(fundal, 0, 0, 1376, 800, null);
        if (mouse.mouseOverNewGame) {
            g.drawImage(newGame, 550, 250, 270, 100, null);
        } else {
            g.drawImage(newGameOver, 550, 250, 270, 100, null);
        }

        if (mouse.mouseOverLoad) {
            g.drawImage(loadGame, 550, 375, 270, 100, null);
        } else {
            g.drawImage(loadGameOver, 550, 375, 270, 100, null);
        }
        if (mouse.mouseOverBack) {
            g.drawImage(backButtonOver, 10, 700, 150, 80, null);
        } else {
            g.drawImage(backButton, 10, 700, 150, 80, null);
        }
    }

    /*! \fn    public void GetImages()
             \brief Initializarea imaginilor din meniul de castig.

             \param g Instrumentul de desenare.
          */
    public void GetImages() {
        ExceptionManager a = new ExceptionManager();
        try {
            buttons[0] = ImageIO.read(MenuState.class.getResource("/meniu/button start.png"));
            buttons[1] = ImageIO.read(MenuState.class.getResource("/meniu/exit button.png"));
            buttons[2] = ImageIO.read(MenuState.class.getResource("/meniu/help button.png"));
            buttons[3] = ImageIO.read(MenuState.class.getResource("/meniu/button start over.png"));
            buttons[4] = ImageIO.read(MenuState.class.getResource("/meniu/exit button over.png"));
            buttons[5] = ImageIO.read(MenuState.class.getResource("/meniu/help button over.png"));

            titlu = ImageIO.read(MenuState.class.getResource("/meniu/titlu.png"));
            text = ImageIO.read(MenuState.class.getResource("/meniu/text instructions.png"));
            instructions = ImageIO.read(MenuState.class.getResource("/meniu/instructions.png"));
            backButton = ImageIO.read(MenuState.class.getResource("/meniu/backButton.png"));
            backButtonOver = ImageIO.read(MenuState.class.getResource("/meniu/backButton_over.png"));
            newGame = ImageIO.read(MenuState.class.getResource("/meniu/new game.png"));
            newGameOver = ImageIO.read(MenuState.class.getResource("/meniu/new game over.png"));
            loadGame = ImageIO.read(MenuState.class.getResource("/meniu/load game.png"));
            loadGameOver = ImageIO.read(MenuState.class.getResource("/meniu/load game over.png"));
            leader = ImageIO.read(MenuState.class.getResource("/meniu/leaderboard.png"));
            leaderOver = ImageIO.read(MenuState.class.getResource("/meniu/leaderboard over.png"));
            font = new Font("HonMincho", Font.BOLD, 90);
            fundal = ImageIO.read(MenuState.class.getResource("/meniu/meniu_fundal.jpg"));
            settingsButton = ImageIO.read(MenuState.class.getResource("/meniu/settings.png"));
            settingsButtonOver = ImageIO.read(MenuState.class.getResource("/meniu/settings over.png"));
            yes = ImageIO.read(MenuState.class.getResource("/meniu/yes.png"));
            yesOver = ImageIO.read(MenuState.class.getResource("/meniu/yes over.png"));
            no = ImageIO.read(MenuState.class.getResource("/meniu/no.png"));
            noOver = ImageIO.read(MenuState.class.getResource("/meniu/no over.png"));
        } catch (IOException e) {
            a.IOException(e);
        }
    }

    @Override
    public void SetStateValues() {

    }
}
