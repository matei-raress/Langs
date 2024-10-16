package Entities;

import game.Game;

import java.awt.*;

/*! \class public class Projectile extends Entity
    \brief Clasa ce se ocupa de comportamentul proiectilelor.

    Toate proiectilele au acelasi comportament, difera doar proprietatile.

     */
public class Projectile extends Entity {
    Entity user;/*!< Variabila ce declara utilizatorul proiectilului.*/

    /* \fn Projectile(Game g)
        \brief Constructorul clasei
     */
    Projectile(Game g) {
        super(g);
    }

    /*! \fn public void set(int x, int y, String direction, boolean alive, Entity user)
        \brief Ajuta la instantierea unui proiectil care poate merge in directii diferite.
        \param x Coordonata x a proiectilului.
        \param y Coordonata y a proiectilului.
        \param direction Directia in care merge proiectilul.
        \param alive Durata de viata a proiectilului.
        \param user Cel care foloseste proiectilul.
     */

    public void set(int x, int y, String direction, boolean alive, Entity user) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        switch (direction) {
            case "up":
            case "down":
                solidArea = new Rectangle(0, 0, 32, 64);
                this.x = x + 20;
                break;
            case "right":
            case "left":
                solidArea = new Rectangle(0, 0, 64, 32);
                break;
        }
        this.alive = alive;
        this.user = user;
        this.life = this.maxLife;
    }

    /*! \fn public void set(int x, int y, String direction, boolean alive, Entity user)
        \brief Ajuta la instantierea unui proiectil mare.
        \param x Coordonata x a proiectilului.
        \param y Coordonata y a proiectilului.
        \param direction Directia in care merge proiectilul.
        \param alive Durata de viata a proiectilului.
        \param user Cel care foloseste proiectilul.
     */
    public void setBig(int x, int y, String direction, boolean alive, Entity user) {
        this.x = x;
        this.y = y;
        this.direction = direction;

        this.alive = alive;
        this.user = user;
        this.life = this.maxLife;
    }

    /*! \fn public void update()
        \brief Urmareste comportamentul proiectilului si coliziunile acestuia

        Daca obiectul are o coliziune atunci devine null si scade puncte din viata entitatii cu care
       face coliziunea in functie de utilizatorul sau. Totodata aici se determina si durata de viata
       a proiectilului, adica cat timp apare pe ecran din momentul in care a fost instantiat.
     */
    public void update() {
        collisionProjectile = false;
        game.checkCollisionCheck.checkTile(this);
        if (this.collisionProjectile) {
            this.alive = false;
        }


        if (user == game.player) {
            int soldierIndex = game.checkCollisionCheck.checkEntityArray(this, game.soldati);
            int KingIndex = game.checkCollisionCheck.checkEntity(this, game.king);
            if (soldierIndex != -1) {
                game.player.damageSoldier(soldierIndex, this.attack);
                this.alive = false;
            }
            if (KingIndex != -1) {
                game.player.damageKing(this.attack);
                this.alive = false;
            }
        } else if (user != game.player) {
            int intersects = game.checkCollisionCheck.checkEntity(this, game.player);
            if (intersects != -1) {
                game.player.damagePlayer(intersects, this.attack);
                this.alive = false;
            }
        }
        switch (direction) {
            case "up" -> this.y -= speed;
            case "down" -> this.y += speed;
            case "left" -> this.x -= speed;
            case "right" -> this.x += speed;
        }

        life = life - 2;
        if (life <= 0) {
            alive = false;
        }

        spriteCounter++;
        if (spriteCounter > 3) {
            if (spriteNum != 1) {
                spriteNum++;
            } else {
                spriteNum = 0;
            }
            spriteCounter = 0;
        }
    }

}
