package Entities;

import Exeptions.ExceptionManager;
import game.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/*! \class public class Soldier extends Entity
    \brief Se ocupa de comportamentul entitatii Soldier

   */
public class Soldier extends Entity {
    BufferedImage[] soldierRightWalk = new BufferedImage[3];/*!< Imaginile soldatului cand merge inspre dreapta.*/
    BufferedImage[] soldierLeftWalk = new BufferedImage[3];/*!< Imaginile soldatului cand merge inspre stanga.*/
    BufferedImage soldierWalkSheet;/*!< Sprite sheet-ul din care se decupeaza imaginile soldatului.*/
    public String prevCollision = "";/*!< Coliziunea precedenta folosita in determinarea miscarii urmatoare.*/
    public boolean follow = false;/*!< Variabila care determina daca sa urmareasca sau nu player-ul.*/

    /*! \fn public Soldier(Game g, int x, int y )
        \brief Constructorul clasei Soldier

        Initializeaza atributele soldatului cum ar fi: inaltimea imaginilor, latimea imaginilor,zona solida, etc.
        \param g Referinta la clasa Game.
        \param x Coordonata x, la care va fi instantiat.
        \param y Coordonata y la care va fi instantiat.
        */
    public Soldier(Game g, int x, int y) {
        super(g);
        tileHeight = 32 * 2;
        tileWidth = 32 * 2;
        getImage();
        solidArea = new Rectangle(25, 20, 30, 30);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        setValues(x, y);
    }

    /*! \fn public void setValues(int x, int y ) {
        \brief Initializeaza valorile folosite pentru reset.

        Initializeaza atributele entitatii soldat cum ar fi:atacul, directia, viteza, etc.
            */
    public void setValues(int x, int y) {
        attack = 5;
        directionFaceRight = false;
        direction = "still";
        this.x = x;//700
        this.y = y;//y=400
        speed = 3;
        maxLife = 100;
        life = maxLife;

    }

    /*!  \fn public void setAction()
        \brief Determmina actiunile soldatului.

         In momentul cand player-ul se apropie prea mult de soldat sau este atacat, se activeaza metoda
         setAction care umareste player-ul.
            */
    public void setAction() {
        actionLock++;

        int col = this.x / 32;
        int row = this.y / 32;
        if (col >= 0 && col < 43 && row >= 0 && row < 25) {
            if (game.mapTile[row - 1][col] == 1 && game.mapTile[row + 1][col] == 1 && game.mapTile[row][col + 1] == 1) {
                direction = "up";
                actionLock = -10;
            }
        }
        if (actionLock > 10) {
            actionLock = 0;

            int distance = game.player.x - this.x;

            if (collisionOn) {
                actionLock = 5;
                if (direction == "up" && (prevCollision == "right" || prevCollision == "left")) {
                    prevCollision = direction;
                    direction = "down";
                }
                if (direction == "down") {
                    prevCollision = direction;
                    direction = "up";
                }
                if (direction == "left") {
                    directionFaceRight = true;
                    prevCollision = direction;
                    direction = "right";
                } else {
                    directionFaceRight = false;
                    prevCollision = direction;
                    direction = "left";
                }
            } else {
                if (distance >= -25 && distance <= 25) {
                    if (game.player.y <= this.y) {
                        direction = "up";
                        directionFaceRight = true;
                    } else {
                        direction = "down";
                        directionFaceRight = true;
                    }
                }
                if (distance < -25) {
                    direction = "left";
                    directionFaceRight = false;
                } else if (distance > 25) {
                    direction = "right";
                    directionFaceRight = true;
                }
            }
        }
    }


    /*! \fn public void update()
        \brief  Actualizeaza atributele si starea soldatului.

        Determina daca metoda de urmarire e aplayerului sa se activeze sau nu, verifica coliziunile soldatului
        cu harta, coliziunile soldatului cu player-ul si vede daca poate ataca sau nu eroul.Deasemenea determina
        viteza animatiilor soldatului.
            */
    public void update() {
        if (follow) {
            setAction();
        }
        if ((game.player.x < this.x + game.tileSize * 6 && game.player.x > this.x - game.tileSize * 6) && (game.player.y < this.y + game.tileSize * 6 && game.player.y > this.y - game.tileSize * 6) || this.life != this.maxLife) {
            follow = true;
        }

        collisionOn = false;
        game.checkCollisionCheck.checkTile(this);
        boolean intersectsPlayer = game.checkCollisionCheck.checkPlayer(this);

        if (intersectsPlayer && !(game.player.invincible)) {
            game.player.life -= this.attack;
            game.player.invincible = true;
        }

        if (collisionOn == false) {
            switch (direction) {
                case "up":
                    this.y -= speed;
                    break;
                case "down":
                    this.y += speed;
                    break;
                case "left":
                    this.x -= speed;
                    break;
                case "right":
                    this.x += speed;
                    break;
            }
        }

        spriteCounter++;
        if (spriteCounter > 6) {
            if (spriteNum != 3) {
                spriteNum++;
            } else {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }

    }

    /*! \fn public void draw(Graphics2D g)
        \brief Deseneaza animatiile soldatului

        \param g Instrumentul de desenat.
     */
    public void draw(Graphics2D g) {
        BufferedImage image = null;

        //right images
        if (directionFaceRight) {
         /*  switch(spriteNum) {
                    case n: image = rightstay[n-1];break;
                } break ;*/
            switch (direction) {
                case "attack":
                    image = soldierRightWalk[spriteNum - 1];
                    break;
                case "still":
                    image = soldierRightWalk[1];
                    break;
                case "up":
                    image = soldierRightWalk[spriteNum - 1];
                    break;
                case "down":
                    image = soldierRightWalk[spriteNum - 1];
                    break;
                case "right":
                    image = soldierRightWalk[spriteNum - 1];
                    break;
            }
        }
        //left images
        else {
            switch (direction) {
                case "attack":
                    image = soldierLeftWalk[spriteNum - 1];
                    break;
                case "still":
                    image = soldierLeftWalk[1];
                    break;
                case "up":
                    image = soldierLeftWalk[spriteNum - 1];
                    break;
                case "down":
                    image = soldierLeftWalk[spriteNum - 1];
                    break;
                case "left":
                    image = soldierLeftWalk[spriteNum - 1];
                    break;
            }
        }


        g.drawImage(image, this.x, this.y, tileWidth, tileHeight, null);

     //   g.drawRect(x + solidArea.x, y + solidArea.y, this.solidArea.width, this.solidArea.height);
    }

    /*! \fn public void getImage()
        \brief Initializeaza imaginile soldatului si trateaza exceptiile.
     */
    public void getImage() {
        ExceptionManager a = new ExceptionManager();
        try {

            soldierWalkSheet = ImageIO.read(Soldier.class.getResource("/Entities/soldier1/soldier1.png"));
            for (int i = 0; i < 3; i++) {
                soldierLeftWalk[i] = soldierWalkSheet.getSubimage(i * 32, 0, 32, 32);
                soldierRightWalk[i] = soldierWalkSheet.getSubimage(i * 32, 32, 32, 32);
            }
        } catch (IOException e) {
            a.IOException(e);
        }
    }
}
