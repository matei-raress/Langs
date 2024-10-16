package game;

import AssetsManager.AssetsLoad;
import AssetsManager.Map;
import Database.Database;
import Entities.King;
import Entities.Player;
import Entities.Soldier;
import InputHandler.KeyHandler;
import InputHandler.MouseHandler;
import Settings.AudioSettings;
import Settings.UISettings;
import Settings.Settings;
import State.*;
import collision.CollisionCheck;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/*! \class  public class Game extends JPanel implements Runnable
    \brief Clasa in care sunt declarate obiectele principale.

   Clasa instantiaza componentele principale ale jocului pentru a putea fi folosite mai departe
   in cod prin referinta.
 */
public class Game extends JPanel implements Runnable {
    public final int tileSize = 32; /*<! Dimensiunea unui tile.*/
    public final int maxScreenCol = 43;/*<! Numar maxim de coloane a matricei de coliziune.*/
    public final int maxScreenRow = 25;/*<! NUmar maxim de linii a matricei de coliziune.*/
    final int screenWidth = tileSize * maxScreenCol;/*<! Latimea ferestrei de joc.*/
    final int screenHeight = tileSize * maxScreenRow;/*<! Inaltimea ferestrei de joc.*/
    Window fereastra;/*<! Fereastra in care se desfasoara jocul.*/
    public Thread gameThread;/*<! Firul principal de executie.*/
    KeyHandler keyHandler = new KeyHandler();/*<! Instrumentul care urmareste inputul tastaturii.*/
    MouseHandler mouseHandler = new MouseHandler(this);/*<! Instrumentul care urmareste inputul mouse-ului.*/
    public Player player = new Player(this, keyHandler, mouseHandler);/*<! Variabila jucatorului principal.*/
    public Soldier[] soldati = new Soldier[5];/*<! Vector de soldati instatiati in primul nivel.*/
    public King king;/*<! Regele instantiat in al doilea nivel.*/
    public int Score = 0;/*<! Scorul jucatorului.*/
    public int mapTile[][] = new int[maxScreenRow][maxScreenCol];/*<! Harta de coliziune.*/
    public Map mapLoader = new Map(this);/*<! Obiectul care se ocupa de desenarea si incarcarea matricei de coliziunea.*/
    public CollisionCheck checkCollisionCheck = new CollisionCheck(this);/*<! Obiectul care verifica coliziunea entitatilor.*/
    public MenuState menuState;/*<! Starea de meniu.*/
    public State levelOneState;/*<! Starea primului nivel.*/
    public State levelTwoState;/*<! Starea celui de-al doilea nivel.*/
    public GameOverState gameOver;/*<! Starea pierderii unui joc.*/
    public WinState winState;/*<! Starea castigului unui joc.*/
    static public boolean Pause = false;/*<! Variabila care determina daca se trece sau nu in starea de pauza.*/
    public PauseState pause;/*<! Starea de pauza.*/
    public boolean updateState = false;/*<! Variabila care observa daca o trecere dintr-o stare in alta a fost facuta.*/
    public Database database = new Database(); /*<! Obiectul referinta la baza de date.*/
    public static boolean Restart = false;/*<! Variabila care observa daca s-a inceput un joc nou.*/
    Settings settings = new Settings();/*<! Obiectul care seteaza setarile.*/
    AudioSettings audioSettings = new AudioSettings(settings);/*<! Obiectul care se ocupa de setarile audio.*/
    UISettings UISettings = new UISettings(settings, this);/*<! Obiectul care se ocupa de setarile video.*/
    boolean soundPlayed = true;/*<! Variabila care observa daca s-a schimbat setarea de sunet.*/

    /*! \fn public Game()
          \brief Constructorul clasei Game

          Aceasta functie se ocupa de initializarea tuturor referintelor folosite in cod.
     */
    public Game() {
        settings.attach(audioSettings);
        settings.attach(UISettings);
        menuState = new MenuState(this, this.mouseHandler, settings);
        levelOneState = new LevelOneState(this, UISettings);
        levelTwoState = new LevelTwoState(this, UISettings);
        pause = new PauseState(this, mouseHandler);
        gameOver = new GameOverState(this, keyHandler);
        winState = new WinState(this, mouseHandler);

        AssetsLoad.Init();
        this.setPreferredSize(new Dimension(1376, 800)); //43x25
        //this.setPreferredSize(new Dimension(screenWidth, screenHeight));    /// In aceeasi maniera trebuiesc setate proprietatile pentru acest obiect
        this.setBackground(Color.white);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.addMouseListener(mouseHandler);
        this.addMouseMotionListener(mouseHandler);
        this.setFocusable(true);
        fereastra = Window.getInstance("Game", screenWidth, screenHeight);
        fereastra.Init(this);
        State.SetState(menuState);

    }

    /* \fn public void startGameThread()
        \brief Porneste firul principal de executie.
        */
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    /* \fn public void run()
        \brief aici se desfareoasa loop-ul threadului unde se sincronizeaza executia codului la 60 FPS si se apeleaza update si draw.
     */
    public void run() {
        long oldTime = System.nanoTime();
        long currentTime;
        final int FPS = 60;
        final double timeFrame = 1000000000 / FPS;

        try {
            while (gameThread.getState() != Thread.State.WAITING) { //gameThread != null
                currentTime = System.nanoTime();
                if ((currentTime - oldTime) > timeFrame) {


                    update();

                    repaint();
                    oldTime = currentTime;

                }
            }
        } catch (NullPointerException e) {

        }


    }

    /* \fn public void update()
       \brief Actualizeaza starea jocului si porneste audio daca setarea ii permite.

       Porneste sunetul daca setarea pentru sunet e activa si seteaza starile jocului, daca se apasa
       tastele numpad0, se trece in starea menu, pentru numpad1 se trece in nivelul 1, pentru numpad2
       se trece in nivelul 2.
     */
    public void update() {
        System.out.println(this.Score);

        if (State.GetState() != null) {
            State.GetState().Update();
        }

        pause.setPrevious(State.GetPreviousState());

        if (keyHandler.num0Pressed) {
            State.SetState(menuState);
        } else if (keyHandler.num1Pressed) {
            State.SetState(levelOneState);
        } else if (keyHandler.num2Pressed) {
            State.SetState(levelTwoState);
            Game.Restart = true;
        }
        this.requestFocusInWindow();

        if (soundPlayed) {
            audioSettings.playAudio();
            soundPlayed = false;
        } else if (menuState.AudioChanged) {
            audioSettings.playAudio();
            menuState.AudioChanged = false;
        }

    }

    /*! \fn public void paintComponent(Graphics g)
        \brief Deseneaza starea curenta si trimite referinta instrumentului de desen catre stare.

     */
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        if (State.GetState() != null) {

            State.GetState().Draw(g2);
        }

        g2.dispose();

    }

}
