package State;

import AssetsManager.AssetsLoad;
import Entities.King;
import Exeptions.ExceptionManager;
import Settings.AudioSettings;
import Settings.UISettings;
import game.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/*! \class public class LevelTwoState extends State
    \brief Al doilea nivel din joc

    Aici se verifica existenta entitatilor din primul nivel si actiunile lor.
 */
public class LevelTwoState extends State {
    Game game;/*!< Variabila ce primeste referinta la Game.*/
    UISettings ioDraw;/*!< Setarile care deseneaza punctele de viata si scorul.*/
    BufferedImage trap;/*!< Imaginea capcanei.*/


    /*! \fn public LevelTwoState(Game game, UISettings ioDraw)
       \brief Constructorul clasei LevelOneState
       \param game Referinta la clasa Game.
       \param ioDraw Referinta la setarile UI.
    */
    public LevelTwoState(Game game, UISettings ioDraw) {
        super(game);
        this.game = game;
        this.ioDraw = ioDraw;
    }

    /*! \fn   public void Update()
     \brief Actualizeaza elementele.

    Se verifica daca nivelul trebuie reinitializat, daca entitatile nu sunt nule, pentru a putea functiona.
    Se verifica cliziunea cu capcanele
  */
    @Override
    public void Update() {
        if (game.updateState) {
            this.loadMap("/maps/matrixmap2.txt");
            game.updateState = false;
            SetStateValues();
        }

        if (Game.Restart) {
            this.loadMap("/maps/matrixmap2.txt");
            SetStateValues();
            game.player.setValues();
            game.Score = 0;
            game.Restart = false;

        }

        if (game.Pause) {
            State.SetState(game.pause);
        }

        game.player.update();
        if (game.player.projectile != null) {
            if (game.player.projectile.alive) { //  && monster[i].dying==false ;
                game.player.projectile.update();
            }
        }

        for (int i = 0; i < game.soldati.length; i++) {
            if (game.soldati[i] != null) {
                game.soldati[i] = null;
            }
        }


        if (game.king != null) {
            game.king.update();
            if (game.king.projectile != null) {
                if (game.king.projectile.alive == true && game.king.alive == true) { //  && monster[i].dying==false ;
                    game.king.projectile.update();
                }
            }
            for (int i = 0; i < game.king.bigProjectile.length; i++) {
                if (game.king.bigProjectile[i].alive == true && game.king.alive == true) { //  && monster[i].dying==false ;
                    game.king.bigProjectile[i].update();
                }
            }
        }
        if (game.player.x > 236 && game.player.x < 300 && game.player.y > 41 && game.player.y < 581) {
            game.player.damagePlayer(1, 5);
        }
        if (game.player.x > 421 && game.player.x < 485 && game.player.y > 101 && game.player.y < 761) {
            game.player.damagePlayer(1, 5);
        }
    }

    /*! \fn    public void Draw(Graphics2D g2)
         \brief Deseneaza elementele.

        \param g2 Instrumentul de desenare.
*/
    @Override
    public void Draw(Graphics2D g2) {

        g2.setColor(Color.YELLOW);


        game.mapLoader.draw(g2, 2);
        for (int i = 0; i < 9; i++) {
            g2.drawImage(trap, 300, 105 + i * 60, 64, 64, null);
        }
        for (int i = 0; i < 11; i++) {
            g2.drawImage(trap, 485, 165 + i * 60, 64, 64, null);
        }
        //enemies
        if (State.GetState() == game.levelOneState) {
            for (int i = 0; i < game.soldati.length; i++) {
                if (game.soldati[i] != null) {
                    game.soldati[i].draw(g2);
                }
            }
        }

        //projectile
        if (game.player.projectile != null) {
            if (game.player.projectile.alive) { //  && monster[i].dying==false ;
                game.player.projectile.draw(g2);
            }
        }


        //player
        game.player.draw(g2);
        //UI
        ioDraw.draw(g2);
        //king
        if (game.king != null) {
            if (game.king.projectile != null) {
                if (game.king.projectile.alive == true) { //  && monster[i].dying==false ;
                    game.king.projectile.draw(g2);
                }
            }
            for (int i = 0; i < game.king.bigProjectile.length; i++) {
                if (game.king.bigProjectile[i] != null) {
                    if (game.king.bigProjectile[i].alive == true) { //  && monster[i].dying==false ;
                        game.king.bigProjectile[i].draw(g2);
                    }
                }
            }
            game.king.draw(g2);
        }


    }

    @Override
    public void SetStateValues() {
        game.king = new King(this.game);
        ExceptionManager a = new ExceptionManager();
        try {
            trap = ImageIO.read(AssetsLoad.class.getResource("/maps/trap.png"));
        } catch (IOException e) {
            a.IOException(e);
        }
    }


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

