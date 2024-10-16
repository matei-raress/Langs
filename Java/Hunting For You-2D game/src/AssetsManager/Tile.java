package AssetsManager;

import java.awt.image.BufferedImage;

/*! \class public class Tile
    \brief Clasa ce consta in detinerea proprietailor variabilelor de tip Tile.

    Imaginile s-au folosit in prima etapa a jocului, pentru depanare.
 */
public class Tile {
    public BufferedImage image; /*!< Imaginea tile-ului.*/
    public boolean collisionTile = false; /*!< Coliziunea cu entitatile.*/
    public boolean collisionProjectile = false;/*!< Coliziunea cu proiectilele.*/
}
