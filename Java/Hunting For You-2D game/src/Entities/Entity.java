package Entities;

import game.Game;

import java.awt.*;

/*! \class public class Entity
    \brief Clasa ce introduce conceptul de entitate

        Clasa are ca scop atasarea unor atribute specifice unei entitati dintr-un joc, cum ar fi: coordonate
       viteza, durata de viata, atacul, coliziunea, etc.

     */
public class Entity {

    Game game;/*!< Variabila de tip Game ce primeste referinta la clasa Game .*/
    public int x;/*!< Coordonata x a entitatii.*/
    public int y;/*!< Coordonata y a entitatii.*/
    public int speed;/*!< Viteza cu care se deplaseaza entitatea.*/
    int tileWidth, tileHeight;/*!< Inaltimea si latiema unui tile, folosit ca referinta pentru desenare.*/

    public String direction;/*!< Directia in care se va deplaseaza entitatea.*/
    public boolean directionFaceRight = true;/*!< Determina daca entitatea are privirea indreptata spre dreapta sau spre stanga.*/
    public int spriteCounter = 0;/*!< Aceasta variabila determina viteza cu care imaginile entitatii se schimba.*/
    public int spriteNum = 1;/*!< Un index care determina ce imagine din vectorul de imagini al entitatii se va desena.*/
    public Rectangle solidArea;/*!< Zona solida a entitatii. Aceasta ajuta in determinarea coliziunilor cu celelalte entitati.*/
    public int solidAreaDefaultX;/*!< Coordonata de baza x a zonei solide.*/
    public int solidAreaDefaultY;/*!< Coordonata de baza y a zonei solide.*/
    public boolean collisionOn = false;/*!< Daca coliziunea e adevarata, entitatea nu se poate misca.*/
    public boolean collisionUp = false;/*!< Determina daca coliziunea se intampla cand entitatea se indreapta in sus.*/
    public boolean collisionDown = false;/*!< Determina daca coliziunea se intampla cand entitatea se indreapta in jos.*/
    public boolean collisionRight = false;/*!< Determina daca coliziunea se intampla cand entitatea se indreapta inspre dreapta.*/
    public boolean collisionLeft = false;/*!< Determina daca coliziunea se intampla cand entitatea se indreapta inspre stanga.*/
    public boolean collisionProjectile = false;/*!< Determina daca proiectilul are voie sa treaca sau nu prin entitate/tile.*/
    public int actionLock = 0;/*!< Viteaza cu care isi pot schimba starea entitatile.*/

    public boolean alive;/*!< Cand entitatea e instantiata, alive devine true, cand alive e false, entitatea devine null.*/
    public int attack;/*!< Cate puncte de viata poate lua atacul entitatii.*/
    public int maxLife;/*!< Maximul de puncte de viata pe care il poate avea entitatea.*/
    public int life;/*!< Punctele de viata curente ale jucatorului.*/
    public Projectile projectile;/*!< Entitatile pot detine proiectile care le determina atacul.*/

    /* \fn Entity(Game g)
       \brief Constructorul clase, initializeaza variabila game cu referinta la clasa Game.

        \param g Referinta la game.
    */
    Entity(Game g) {
        this.game = g;
    }

    /*! \fn public void update()
       \brief Actualizeaza variabilele entitatii in loop-ul update din clasa Game .

    */
    public void update() {
    }

    /*! \fn public void draw(Graphics2D g2)
        \brief Deseneaza pe harta imaginile entitatii.

        \param g2 Referinta la instrumentul de desenat.
     */
    public void draw(Graphics2D g2) {
    }

    /*! \fn public int getTileHeight()
        \brief Returneaza inaltimea unui tile ;

            */
    public int getTileHeight() {
        return tileHeight;
    }


}
