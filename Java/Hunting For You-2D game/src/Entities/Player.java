package Entities;

import InputHandler.KeyHandler;
import InputHandler.MouseHandler;
import State.State;
import game.Game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static AssetsManager.AssetsLoad.*;

    /*! \class public class Player extends Entity
        \brief Clasa se ocupa de comportamentul eroului.

            Clasa Player se ocupa de controlul player-ului si de interactiunile cu acesta.
         */

public class Player extends Entity {
    KeyHandler key;/*!< Variabila primeste referinta la KeyHandler-ul jocului care gestioneaza miscarea player-ului si directia sa de atac.*/
    MouseHandler mouse;/*!< Variabila primeste referinta la MouseHandler-ul jocului care gestioneaza daca player-ului ataca.*/
    int tileWidth;/*!< Inaltimea imaginilor desenelor.*/
    int tileHeight;/*!< Latimea imaginilor desenelor.*/
    public boolean invincible = false;/*!< Variabila defineste daca player-ul poate fi ranit.*/
    public int invincibleCounter = 0;/*!< Durata pentru care player-ul nu poate fi atacat.*/
    String directionShoot;/*!< Directia in care va trage proiectilul.*/
    int shotAvailable = 0;/*!< Contorul care declara daca player-ul poate sa traga.*/
    int soldatiAttack;/*!< O variabila ce retine atacul unui soldat.*/
    boolean dash=false ;
    /*! \fn public Player(Game g, KeyHandler keyH,MouseHandler mouseh)
        \brief Constructorul clasei PLayer

        Initializeaza toate atributele de inceput ale playerului

        \param g Referinta la clasa Game
        \param keyH Referinta la variabila KeyHandler din clasa Game
        \param mouseH Referinta la variabila MouseHandler din clasa Game
         */
    public Player(Game g, KeyHandler keyH, MouseHandler mouseh) {
        super(g);
        Soldier a = new Soldier(this.game, 0, 0);
        soldatiAttack = a.attack;
        tileWidth = 48 * 2;
        tileHeight = 32 * 2;
        this.key = keyH;
        this.mouse = mouseh;
        setValues();
        solidArea = new Rectangle(32, 20, 25, 45);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        direction = "right";
        directionShoot = "right";
    }

    /*! \fn public void setValues()
        \brief Seteaza punctul de inceput al playerului.

        Seteaza coordonatele, punctele de viata maxima, viteza si atacul.

            */
    public void setValues() {
        x = 1;
        y = 400;
        speed = 6;
        maxLife = 100;
        life = maxLife;
        projectile = new Arrow(this.game);
    }

    /*! \fn  public void update() {
         \brief Se ocupa de reactualizarea atributelor si actiunilor playerului

         Functia verifica daca jucatorul are coliziune cu entitatile si cu harta, daca exita coliziuni cu entitatile, eroului i
         se vor scadea puncte de viata aferent atacului entitatii. Totodata de se verifica inputul tastaturii pentru a verifica in
         ce directie merge jucatorul. Aici se mai gestioneaza invincibilitatea, trecea eroului din harta 1 in harta 2 si trecerea in starea
         de game oveer daca viata player-ului scade sub 0.

             */
    public void update() {
        if (life <= 0) {
            State.SetState(game.gameOver);
        }

        if (key.downPress == true || key.leftPress == true || key.rightPress == true || key.upPress == true || mouse.mousePress) {
            if (key.upPress == true) {
                direction = "up";
                directionShoot = "up";
            } else if (key.downPress == true) {
                direction = "down";
                directionShoot = "down";
            } else if (key.rightPress == true) {
                direction = "right";
                directionFaceRight = true;
                directionShoot = "right";
            } else if (key.leftPress == true) {
                direction = "left";
                directionFaceRight = false;
                directionShoot = "left";
            } else if (mouse.mousePress == true) {
                direction = "attack";
            }

            CollisionStatus();

            if(key.spacePress){
                dash=true ;
            }
            if (!collisionOn) {
                switch (direction) {
                    case "up" -> y -= speed;
                    case "down" -> y += speed;
                    case "left" -> x -= speed;
                    case "right" -> x += speed;
                }
                if(dash){
                    switch (direction) {
                        case "up" -> y -= speed +10;
                        case "down" -> y += speed +10;
                        case "left" -> x -= speed +10;
                        case "right" -> x += speed+10;
                    }
                    dash=false;
                }
            }

            spriteCounter++;
            if (spriteCounter > 3) {
                if (spriteNum != 7) {
                    spriteNum++;
                } else {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }

        } else {
            direction = "still";
            spriteCounter++;
            if (spriteCounter > 10) {
                if (spriteNum == 7) {
                    spriteNum = 1;
                } else {
                    spriteNum++;
                }
                spriteCounter = 0;
            }
        }
        if (mouse.mousePress && !projectile.alive && shotAvailable == 10) {
            projectile.set(x, y, directionShoot, true, this);
            shotAvailable = 0;
        }
        if (shotAvailable < 10) {
            shotAvailable++;
        }

        if ((this.x + this.solidArea.x + this.tileWidth) > 1352 && (this.y + this.solidArea.y + this.tileHeight) < 480 && (this.y + this.solidArea.y + this.tileHeight) > 356 && (State.GetState() == game.levelOneState)) {
            State.SetState(game.levelTwoState);
            this.x = 1;
            this.y = 400;
            game.updateState = true;
        }

        invincibleStatus();

    }

    /* \fn     public void CollisionStatus()
        \brief Verifica coliziunile playerului.

        Daca exita coliziune cu alte entitati, i se vor scadea puncte de viata player-ului iar daca
       va avea coliziune cu mapa de coliziune aceasta nu se va mai putea misca.
    */
    public void CollisionStatus() {
        collisionOn = false;
        game.checkCollisionCheck.checkTilePlayer(this);

        int soldatiIndex = game.checkCollisionCheck.checkEntityArray(this, game.soldati);
        damagePlayer(soldatiIndex, soldatiAttack);

        if (State.GetState() == game.levelTwoState) {

            if (game.king != null) {
                int kingIndex = game.checkCollisionCheck.checkEntity(this, game.king);
                damagePlayer(kingIndex, game.king.attack);
            }
        }
    }

    /* \fn public void invincibleStatus() {
        \brief Determina timpul in care eroul nu poate fi ranit.
     */
    public void invincibleStatus() {
        if (invincible == true) {
            invincibleCounter++;
            if (invincibleCounter > 30) {
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }

    /*! \fn public void damagePlayer(int i, int damage)
       \brief Scade punctele de viata a jucatorului cu o conditia sa exista coliziuni.

       \param i Variabila care afirma coliziunea cu alte entitati.
       \param damage Daunele scazute din viata jucatorului.

         */
    public void damagePlayer(int i, int damage) {
        if (i != -1) {
            if (!invincible) {
                life -= damage;
                invincible = true;
            }
        }
    }

    /*! \fn  public void damageSoldier(int index, int damage)
         \brief Scade punctele de viata a sodlatilor cu o conditia sa existe coliziuni.

         \param i Variabila care afirma coliziunea cu alte entitati.
         \param damage Daunele scazute din viata soldatului.
           */
    public void damageSoldier(int index, int damage) {
        if (index != -1) {
            game.soldati[index].life -= damage;
            if (game.soldati[index].life <= 0) {
                game.soldati[index] = null;
                game.Score += 1;
            }
        }
    }

    /*! \fn   public void damageKing(int damage)
        \brief Scade punctele de viata a regelui

        Daca viata regelui scade sub 0, State-ul trece in win state.

         \param damage Daunele scazute din viata regelui.
               */
    public void damageKing(int damage) {

        game.king.life -= damage;
        if (game.king.life <= 0) {
            game.king.alive = false;
            game.king = null;
            game.winState.gameScoreUpdate=true;
            State.SetState(game.winState);

        }
    }

    /*! \fn   public void draw(Graphics2D g)
           \brief Deseneaza animatiile jucatorului.

           Variabila spriteCounter determina viteza cu care se vor schimba imaginile.

           \param g Instrumentul de desenare.
        */
    public void draw(Graphics2D g) {
        BufferedImage image = null;
        if (directionFaceRight) {
            switch (direction) {
                case "attack":
                    image = rightPlayerAttack[spriteNum - 1];
                    break;
                case "still":
                    image = rightPlayerStay[spriteNum - 1];
                    break;
                case "up":
                    image = rightPlayerWalk[spriteNum - 1];
                    break;
                case "down":
                    image = rightPlayerWalk[spriteNum - 1];
                    break;
                case "right":
                    image = rightPlayerWalk[spriteNum - 1];
                    break;
            }
        } else {
            switch (direction) {
                case "attack":
                    image = leftPlayerAttack[spriteNum - 1];
                    break;
                case "still":
                    image = leftPlayerStay[spriteNum - 1];
                    break;
                case "up":
                    image = leftPlayerWalk[spriteNum - 1];
                    break;
                case "down":
                    image = leftPlayerWalk[spriteNum - 1];
                    break;
                case "left":
                    image = leftPlayerWalk[spriteNum - 1];
                    break;
            }
        }

        if (invincible == true) {
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        }
        g.drawImage(image, x, y, tileWidth, tileHeight, null);
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        g.drawRect(x + solidArea.x, y + solidArea.y, this.solidArea.width, this.solidArea.height);
    }

    /*! \fn public int getTileHeight()
        \brief Returneaza inaltimea imaginilor jucatorului.
      */
    public int getTileHeight() {
        return tileHeight;
    }
}
