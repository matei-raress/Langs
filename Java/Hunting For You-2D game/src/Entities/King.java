package Entities;

import Exeptions.ExceptionManager;
import game.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/*! \class public class King extends Entity
    \brief Clasa ce se ocupa de proprietatile/actiunile regelui.

     */
public class King extends Entity {
    BufferedImage[] kingUpWalk = new BufferedImage[2];/*!< Imaginile regelui cand se indreapta in sus.*/
    BufferedImage[] kingDownWalk = new BufferedImage[2];/*!< Imaginile regelui cand se indreapta in jos.*/
    BufferedImage[] kingAttackWalk = new BufferedImage[2];/*!< Imaginile regelui in momentul in care ataca.*/
    BufferedImage kingUpSheet;/*!< Sprite sheetul cu imaginile care vor fi decupate si puse in vectorul kingUpWalk.*/
    BufferedImage kingDownSheet;/*!< Sprite sheetul cu imaginile care vor fi decupate si puse in vectorul kingDownWalk.*/
    BufferedImage kingAttackSheet;/*!< Sprite sheetul cu imaginile care vor fi decupate si puse in vectorul kingAttackWalk.*/
    int shotAvailable = 0;/*!< Viteza cu care poate ataca regele.*/
    public Projectile projectile;/*!< Atacul de baza al regelui,Fire , asemanator cu cel al eroului.*/
    public Projectile[] bigProjectile = new Projectile[3];/*!< Atacul special al regelui, format din 3 proiectile BigFire.*/

    /*! \fn public King(Game g)
        \brief Initializeaza proprietatile entitatii King si se ocupa de incarcarea imaginilor respective.

        \param g Referinta la clasa Game
            */
    public King(Game g) {
        super(g);
        tileHeight = 32 * 3;
        tileWidth = 32 * 3;
        getImage();
        solidArea = new Rectangle(20, 15, 50, 70);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        setValues();
    }

    /*! \fn public void setValues()
    \brief Initializeaza punctul din care pleaca si proprietatile de baza: attack, viteza, puncte de viata, etc.


    \param g Referinta la clasa Game
        */
    public void setValues() {
        attack = 100;
        directionFaceRight = false;
        direction = "still";
        x = 1000;
        y = 380;
        speed = 3;
        maxLife = 100;
        life = maxLife;
        projectile = new Fire(this.game);
        bigProjectile[0] = new BigFire(this.game);
        bigProjectile[1] = new BigFire(this.game);
        bigProjectile[2] = new BigFire(this.game);
        alive = true;
    }

    /*! \fn  public void setAction()
        \brief Seteaza actiunea pe care o va face regele

        Initial regele sta pe loc dar dupa ce actionLock este iterat de cel putin 50 de ori acesta va primi o actiune
       determinata de un numar generat aleatoriu. In momentul in care coordonata x a eroului >500, regele va ignora celelalte
       actiuni si va primi actiunea de a ataca cu cele 3 proiectile de tip BigFire.

            */
    public void setAction() {
        actionLock++;
        if (game.player.x > 500) {
            direction = "big attack";
        } else if (actionLock > 50) {
            Random random = new Random();
            int i = random.nextInt(100) + 1;
            if (i > 60 && i <= 100) {

                direction = "attack";
            } else if (i > 30 && i <= 60) {
                direction = "up";
            } else if (i > 0 && i <= 30) {
                direction = "down";
            }
            actionLock = 0;
        }
    }

    /*!      \fn  public void update() {
            \brief Se ocupa de reactualizarea atributelor si actiunilor regelui

            Functia verifica daca regele are coliziune cu jucatorul, daca da, eroului i se vor scadea puncte de viata aferent
           atacului acestuia dar si cu . Totodata de aici se verifica daca eroul a primit actiunea de a ataca, in acest caz, se apeleaza
           functiile Projectile.set() sau Projectile.setBig() care instantiaza proiectilele.

                */
    public void update() {
        setAction();
        collisionOn = false;
        game.checkCollisionCheck.checkTilePlayer(this);
        boolean intersectsPlayer = game.checkCollisionCheck.checkPlayer(this);

        if (intersectsPlayer && !(game.player.invincible)) {
            game.player.life -= this.attack;
            game.player.invincible = true;
        }

        if (direction == "attack" && !projectile.alive && shotAvailable == 10) {
            projectile.set(x, y, "left", true, this);
            shotAvailable = 0;
        }

        for (int i = 0; i < bigProjectile.length; i++) {
            if (direction == "big attack" && !bigProjectile[i].alive) {
                bigProjectile[i].setBig(1000, 120 + i * 210, "left", true, this);

            }
        }
        if (shotAvailable < 10) {
            shotAvailable++;
        }
        if (!collisionOn) {
            switch (direction) {
                case "up" -> this.y -= speed;
                case "down" -> this.y += speed;
            }
        }
        spriteCounter++;
        if (spriteCounter > 6) {
            if (spriteNum != 2) {
                spriteNum++;
            } else {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }

    }

    /*! \fn   public void draw(Graphics2D g)
        \brief Deseneaza animatiile regelui.

        Variabila spriteCounter determina viteza cu care se vor schimba imaginile.

        \param g Instrumentul de desenare.
     */
    public void draw(Graphics2D g) {
        BufferedImage image = null;

        switch (direction) {
            case "attack":
                image = kingAttackWalk[spriteNum - 1];
            case "big attack":
                image = kingAttackWalk[spriteNum - 1];
                break;
            case "still":
                image = kingAttackWalk[0];
                break;
            case "up":
                image = kingUpWalk[spriteNum - 1];
                break;
            case "down":
                image = kingDownWalk[spriteNum - 1];
                break;
            // case "left":  image = soldierLeftWalk[spriteNum-1] ; break ;
        }

        g.drawImage(image, this.x, this.y, tileWidth, tileHeight, null);

        g.drawRect(x + solidArea.x, y + solidArea.y, this.solidArea.width, this.solidArea.height);
    }

    public void getImage() {
        ExceptionManager manager = new ExceptionManager();
        try {

            kingDownSheet = ImageIO.read(Soldier.class.getResource("/King/king_down.png"));
            kingUpSheet = ImageIO.read(Soldier.class.getResource("/King/king_up.png"));
            kingAttackSheet = ImageIO.read(Soldier.class.getResource("/King/king_attack.png"));
            for (int i = 0; i < 2; i++) {
                kingDownWalk[i] = kingDownSheet.getSubimage(0, i * 32, 32, 32);
                kingUpWalk[i] = kingUpSheet.getSubimage(0, i * 32, 32, 32);
                kingAttackWalk[i] = kingAttackSheet.getSubimage(0, i * 32, 32, 32);
            }
        } catch (IOException e) {
            manager.IOException(e);
        }
    }
}
