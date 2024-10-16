package AssetsManager;

import game.Game;

import java.awt.*;

import static AssetsManager.AssetsLoad.*;

/*! \class public class Map
    \brief Clasa initializeaza matricea de coliziune cu tile-uri corespunzatoare fiecarei coliziune.

 */
public class Map {
    Game game; /*!< Variabila ce copie referinta la clasa Game.*/
    public Tile[] tile = new Tile[4]; /*!< Vector ce contine tipurile de Tile-uri .*/

    /*! \fn public Map(Game gm)
        \brief Constructorul cu parametri al clasei Map.

        Retine referinta la clasa Game si initializeaza Tile-urile folosite in coliziuni prin matricea getTile().

        \param gm Referinta la Game.
     */
    public Map(Game gm) {
        this.game = gm;
        getTile();
    }

    /*! \fn public void getTile()
        \brief Instantiaza elementele vectorului de Tile si le initializeaza valorile.

        Tile-urile cu indexul -> 0: nu au coliziune cu nici o entitate.
                              -> 1: au coliziune cu soldati, eroul si regele.
                              -> 2: au coliziune cu toate entitatile.
                              -> 3: au coliziune cu toate entitatile si isi poate schimba proprietatea daca au fost
                                    invinsi toti soldatii.
            */
    public void getTile() {
        tile[0] = new Tile();
        tile[0].image = transparent;
        tile[1] = new Tile();
        tile[1].image = block;
        tile[1].collisionTile = true;
        tile[2] = new Tile();
        tile[2].image = block;
        tile[2].collisionProjectile = true;
        tile[2].collisionTile = true;
        tile[3] = new Tile();
        tile[3].image = block;
        tile[3].collisionTile = true;

    }

    /*! \fn public void SetGate(boolean a)
            \brief Seteaza coliziunea tileurilor cu index 3 .

            /param coliz Coliziunea tile-ului.
            */
    public void SetGate(boolean coliz) {
        tile[3].collisionTile = coliz;
    }

    /*! \fn public void draw(Graphics graphics, int nr_mapa)
         \brief Deseneaza fundalul nivelelor in functie de ce matrice e incarcata in mapTile.

         /param graphics Instrumentul de desenare.
         /param nr_mapa Numarul nuvelului care trebuie desenat: 1, pentru primul nivel, 2 pentru al doilea nivel.
         */
    public void draw(Graphics graphics, int nr_mapa) {
        int col = 0, row = 0, x = 0, y = 0;

        while (row < game.maxScreenRow && col < game.maxScreenCol) {
            int tileNum = game.mapTile[row][col];

            if (tileNum == 2) {
                graphics.drawImage(tile[tileNum].image, x, y, game.tileSize / 2, game.tileSize, null);
            } else {
                graphics.drawImage(tile[tileNum].image, x, y, game.tileSize, game.tileSize, null);
            }

            col++;
            x += game.tileSize;

            if (col == game.maxScreenCol) {
                col = 0;
                x = 0;
                row++;
                y += game.tileSize;
            }
        }
        if (nr_mapa == 1) {
            graphics.drawImage(fundalul1, 0, 0, 1376, 800, null);
        } else if (nr_mapa == 2) {
            graphics.drawImage(fundalul2, 0, 0, 1376, 800, null);
        }
    }
}
