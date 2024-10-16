package State;

import Entities.Soldier;
import Settings.AudioSettings;
import Settings.UISettings;
import game.Game;

import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import static AssetsManager.AssetsLoad.gate;

/*! \class public class LevelOneState extends State
    \brief Primul nivel din joc

    Primul state al jocului, aici se verifica existenta entitatilor din primul nivel si actiunile lor.
 */
public class LevelOneState extends State {
    Game game;/*!< Variabila ce primeste referinta la Game.*/
    UISettings ioDraw;/*!< Setarile care deseneaza punctele de viata si scorul.*/
    boolean OpenGate = true;/*!< Variabila determina daca se poate face trecerea intre nivele.*/

    /*! \fn   public LevelOneState(Game game,  UISettings ioDraw)
       \brief Constructorul clasei LevelOneState
       \param game Referinta la clasa Game.
       \param ioDraw Referinta la setarile UI.
    */
    public LevelOneState(Game game, UISettings ioDraw) {
        super(game);
        this.game = game;
        this.ioDraw = ioDraw;
        this.loadMap("/maps/matrixMap1.txt");
        SetState();

    }

    /*! \fn   public void SetState()
      \brief Seteaza entitatile din primul nivel.

   */
    public void SetState() {
        this.loadMap("/maps/matrixMap1.txt");
        game.soldati[0] = new Soldier(this.game, 805, 305);
        game.soldati[1] = new Soldier(this.game, 350, 200);
        game.soldati[2] = new Soldier(this.game, 1000, 720);
        game.soldati[3] = new Soldier(this.game, 70, 700);
        game.soldati[4] = new Soldier(this.game, 700, 720);
        game.player.setValues();
        game.mapLoader.SetGate(true);
    }

    /*! \fn   public void Update()
     \brief Actualizeaza elementele.

    Se verifica daca nivelul trebuie reinitializat, verifica daca se
    poate fac trecerea intre nivele,daca trebuie sa se treaca in state de pauza  si
     daca ntitatile nu sunt nule, pentru a putea functiona.
  */
    @Override
    public void Update() {

        if (Game.Restart) {
            SetState();
            Game.Restart = false;
            game.Score = 0;
        }

        OpenGate = true;
        for (int i = 0; i < game.soldati.length; i++) {
            if (game.soldati[i] != null) {
                OpenGate = false;
            }
        }
        if (OpenGate) {
            game.mapLoader.SetGate(false);
        }

        if (game.Pause) {
            State.SetState(game.pause);
        }

        game.player.update();

        for (int i = 0; i < game.soldati.length; i++) {
            if (game.soldati[i] != null) {
                game.soldati[i].update();
            }
        }


        if (game.player.projectile != null) {
            if (game.player.projectile.alive) { //  && monster[i].dying==false ;
                game.player.projectile.update();

            }
        }

    }

    @Override
    public void SetStateValues() {

    }

    /*! \fn    public void Draw(Graphics2D g2)
         \brief Deseneaza elementele.

        \param g2 Instrumentul de desenare.
*/
    @Override
    public void Draw(Graphics2D g2) {

        g2.setColor(Color.YELLOW);
        game.mapLoader.draw(g2, 1);
        if (!OpenGate) {
            g2.drawImage(gate, 1245, 340, 22, 176, null);
        }

        for (int i = 0; i < game.soldati.length; i++) {
            if (game.soldati[i] != null) {
                game.soldati[i].draw(g2);
            }
        }

        if (game.player.projectile != null) {
            if (game.player.projectile.alive) { //  && monster[i].dying==false ;
                game.player.projectile.draw(g2);
            }
        }

        game.player.draw(g2);
        this.ioDraw.draw(g2);

    }

    /*! \fn   public void loadMap(String path)
           \brief Incarca matricea de coliziune.

            \param path Calea catre fisierul cu matricea
  */
    public void loadMap(String path) {
        try {
            int col = 0, row = 0;
            InputStream is = getClass().getResourceAsStream(path);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            while (row < game.maxScreenRow && col < game.maxScreenCol) {
                String line = br.readLine();

                while (col < game.maxScreenCol) {
                    String[] numbers = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    game.mapTile[row][col] = num;
                    col++;
                }
                if (col == game.maxScreenCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();

        } catch (Exception e) {
            System.out.println("Eroare pe citire din matrice");
        }
    }
}

