package Entities;

import AssetsManager.AssetsLoad;
import Exeptions.ExceptionManager;
import game.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/*! \class public class Arrow extends Projectile
    \brief  Extinde clasa Projectile si incarca imaginile sagetilor folosite de erou.

 */
public class Arrow extends Projectile {
    BufferedImage arrowRight; /*!< Imaginea sagetii cand se indreapta spre dreapta.*/
    BufferedImage arrowLeft;/*!< Imaginea sagetii cand se indrepta spre stanga.*/
    BufferedImage arrowUp;/*!< Imaginea sagetii cand se indrepta in sus.*/
    BufferedImage arrowDown;/*!< Imaginea sagetii cand se indrepta in jos.*/
    Game g;/*!< variabila Game in care se va pune referinta la clasa Game.*/

    /*! \fn Arrow(Game g)
       \brief Constructorul clasei Arrow.
            Acesta initializeaza toate proprietatile unei entitati de tip Arrow cum ar fi coordonatele
           viteza, durata vietii, statusul vietii, zona solida, imaginile, etc.
        \param g Referinta la clasa Game.
      */
    Arrow(Game g) {
        super(g);
        this.x = 1;
        this.y = 400;
        this.g = g;
        speed = 20;
        maxLife = 100;
        life = maxLife;
        attack = 20;
        alive = false;
        direction = "right";
        solidArea = new Rectangle(0, 0, 64, 32);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        GetImages();
    }

    /*! \fn draw(Graphics2D g2)
       \brief Functia de desenare a imaginilor.

        \param g2 Instrumentul pentru desenarea imaginilor proiectilului.
      */
    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        switch (direction) {
            case "up":
                image = arrowUp;
                g2.drawImage(image, this.x + 5, this.y + 10, 32, 64, null);
                break;
            case "down":
                image = arrowDown;
                g2.drawImage(image, this.x + 5, this.y + 10, 32, 64, null);
                break;
            case "right":
                image = arrowRight;
                g2.drawImage(image, this.x + 5, this.y + 10, 64, 32, null);
                break;
            case "left":
                image = arrowLeft;
                g2.drawImage(image, this.x + 5, this.y + 10, 64, 32, null);
                break;
        }
        //g2.drawRect(x + solidArea.x, y + solidArea.y, this.solidArea.width, this.solidArea.height);
    }

    /*! \fn GetImages()
       \brief Functia de incarcare a imaginilor proiectilului.

      */
    public void GetImages() {
        ExceptionManager a = new ExceptionManager();
        try {
            arrowRight = ImageIO.read(AssetsLoad.class.getResource("/player/arrow_right.png"));
            arrowLeft = ImageIO.read(AssetsLoad.class.getResource("/player/arrow_left.png"));
            arrowDown = ImageIO.read(AssetsLoad.class.getResource("/player/arrow_down.png"));
            arrowUp = ImageIO.read(AssetsLoad.class.getResource("/player/arrow_up.png"));
        } catch (IOException e) {
            a.IOException(e);
        }
    }
}
