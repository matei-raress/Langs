package Entities;

import Exeptions.ExceptionManager;
import game.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/*! \class public class BigFire extends Projectile
    \brief  Extinde clasa Projectile si incarca imaginile proiectilelor mari de foc folosite de rege.

 */
public class BigFire extends Projectile {
    BufferedImage[] fireballLeft = new BufferedImage[2];/*!< Imaginile proiectilelor indreptate spre stanga.*/
    Game g;/*!< Variabila de tip Game ce retine referinta la Game.*/

    /*! \fn BigFire(Game g)
        \brief Constructorul clasei BigFire
            Acesta initializeaza toate proprietatile unei entitati de tip BigFire cum ar fi coordonatele,
           viteza, durata vietii, statusul vietii, zona solida, imaginile, etc.
        \param g Referinta la clasa Game.
  */
    BigFire(Game g) {
        super(g);
        this.x = 1;
        this.y = 400;
        this.g = g;
        speed = 10;
        maxLife = 100;
        life = maxLife;
        attack = 50;
        alive = false;
        direction = "left";
        solidArea = new Rectangle(0, 10, 100, 200);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        GetImages();
    }

    /*! \fn draw(Graphics2D g2)
           \brief Functia de desenare a imaginilor proiectilului.

            \param g2 Instrumentul pentru desenarea imaginilor.
          */
    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        switch (direction) {
            case "attack":
                image = fireballLeft[spriteNum];
                break;
            case "still":
                image = fireballLeft[spriteNum];
                break;
            case "up":
                image = fireballLeft[spriteNum];
                break;
            case "down":
                image = fireballLeft[spriteNum];
                break;
            case "right":
                image = fireballLeft[spriteNum];
                break;
            case "left":
                image = fireballLeft[spriteNum];
                break;
        }
        g2.drawImage(image, this.x + 5, this.y + 10, 200, 200, null);
      //  g2.drawRect(x + solidArea.x, y + solidArea.y, this.solidArea.width, this.solidArea.height);
    }

    /*! \fn GetImages()
       \brief Functia de incarcare a imaginilor proiectilului.

      */
    public void GetImages() {
        ExceptionManager a = new ExceptionManager();
        try {
            fireballLeft[0] = ImageIO.read(Fire.class.getResource("/Entities/fireball.png"));
            fireballLeft[1] = ImageIO.read(Fire.class.getResource("/Entities/fireball.png"));
        } catch (IOException e) {
            a.IOException(e);
        }
    }
}
