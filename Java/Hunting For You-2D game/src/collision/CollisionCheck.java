package collision;

import Entities.Entity;
import game.Game;

/*! \class public class CollisionCheck
    \brief Se ocupa de toate coliziunile dintre entitati si dintre entitati si tile-uri.
*/
public class CollisionCheck {
    Game game; /*!< Variabila ce retine referinta la game.*/

    /*! \fn public CollisionCheck
    \brief Initializea o copie cu referinta la Game.

    \param gm Referinta la game.
*/
    public CollisionCheck(Game gm) {
        this.game = gm;
    }

    /*! \fn  public void checkTilePlayer(Entity entity){
        \brief  Verifica coliziunea eroului cu matricea hartii.

        Eroul are o metoda separata de coliziune deoarece el este capabil sa fie desenat deasupra anumitor structuri
       pentru a da impresia de plasare in spatiu.

        \param entity Referinta la player.
    */
    public void checkTilePlayer(Entity entity) {
        int entityLeftX = entity.x + entity.solidArea.x;
        int entityRightX = entity.x + entity.solidArea.x + entity.solidArea.width;
        int entityTopY = entity.y + entity.solidArea.y;
        int entityBottomY = entity.y + entity.solidArea.y + entity.solidArea.height;


        int entityLeftCol = entityLeftX / game.tileSize;
        int entityRightCol = entityRightX / game.tileSize;
        int entityTopRow = entityTopY / game.tileSize;
        int entityBottomRow = entityBottomY / game.tileSize;

        int tileNum1, tileNum2;


        int leftbound = entity.x + entity.solidArea.x - entity.speed;
        switch (entity.direction) {
            case "up":
                entityTopRow = (entityTopY - entity.speed) / game.tileSize;
                tileNum1 = game.mapTile[entityTopRow + 1][entityLeftCol];
                tileNum2 = game.mapTile[entityTopRow + 1][entityRightCol - 1];

                if ((game.mapLoader.tile[tileNum1].collisionTile == true) || (game.mapLoader.tile[tileNum2].collisionTile == true) || entity.y < 2) {

                    entity.collisionUp = true;
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomY + entity.speed) / game.tileSize;
                tileNum1 = game.mapTile[entityBottomRow][entityLeftCol];
                tileNum2 = game.mapTile[entityBottomRow][entityRightCol - 1];
                int bottombound = entity.y + entity.speed + entity.getTileHeight() + 7;

                if ((game.mapLoader.tile[tileNum1].collisionTile == true) || (game.mapLoader.tile[tileNum2].collisionTile == true) || (bottombound > 800)) {
                    entity.collisionDown = true;
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftX - 10 - entity.speed) / game.tileSize;
                tileNum1 = game.mapTile[entityTopRow + 1][entityLeftCol];
                tileNum2 = game.mapTile[entityBottomRow][entityLeftCol];


                if ((game.mapLoader.tile[tileNum1].collisionTile == true) || (game.mapLoader.tile[tileNum2].collisionTile == true) || (leftbound < 4)) {
                    entity.collisionLeft = true;
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightX - 10 + entity.speed) / game.tileSize;
                tileNum1 = game.mapTile[entityTopRow + 1][entityRightCol];
                tileNum2 = game.mapTile[entityBottomRow][entityRightCol];
                if ((game.mapLoader.tile[tileNum1].collisionTile == true) || (game.mapLoader.tile[tileNum2].collisionTile == true)) {
                    entity.collisionRight = true;
                    entity.collisionOn = true;
                }
                break;

        }
    }

    /*! \fn  public void checkTile(Entity entity)
        \brief  Verifica coliziunea entitatilor cu matricea hartii.

        Verifica pe matricea de coliziuni ce tile-uri au coliziune cu partea solida a entitatii si in functie de
      asta entitatea are dreptul sa se miste sau nu.

        \param entity Referinta la entitatea caruia i se verifica zona solida.
    */
    public void checkTile(Entity entity) {

        int entityLeftX = entity.x + entity.solidArea.x;
        int entityRightX = entity.x + entity.solidArea.x + entity.solidArea.width;
        int entityTopY = entity.y + entity.solidArea.y;
        int entityBottomY = entity.y + entity.solidArea.y + entity.solidArea.height;


        int entityLeftCol = entityLeftX / game.tileSize;
        int entityRightCol = entityRightX / game.tileSize;
        int entityTopRow = entityTopY / game.tileSize;
        int entityBottomRow = entityBottomY / game.tileSize;

        int tileNum1, tileNum2;


        int leftbound = entity.x + entity.solidArea.x - entity.speed;
        switch (entity.direction) {
            case "up":
                entityTopRow = (entityTopY - entity.speed) / game.tileSize;
                tileNum1 = game.mapTile[entityTopRow][entityLeftCol];
                tileNum2 = game.mapTile[entityTopRow][entityRightCol];

                if ((game.mapLoader.tile[tileNum1].collisionTile == true) || (game.mapLoader.tile[tileNum2].collisionTile == true) || entity.y < 2) {
                    entity.collisionUp = true;
                    entity.collisionOn = true;
                }
                if ((game.mapLoader.tile[tileNum1].collisionProjectile == true) || (game.mapLoader.tile[tileNum2].collisionProjectile == true) || entity.y < 2) {
                    entity.collisionProjectile = true;

                }

                break;
            case "down":
                if (entity.y + entity.speed > 730) {
                    entity.collisionProjectile = true;
                    break;
                }
                entityBottomRow = (entityBottomY + entity.speed) / game.tileSize;
                tileNum1 = game.mapTile[entityBottomRow][entityLeftCol];
                tileNum2 = game.mapTile[entityBottomRow][entityRightCol];
                int bottombound = entity.y + entity.speed + entity.getTileHeight();

                if ((game.mapLoader.tile[tileNum1].collisionTile == true) || (game.mapLoader.tile[tileNum2].collisionTile == true) || (bottombound > 800)) {
                    entity.collisionDown = true;
                    entity.collisionOn = true;
                }
                if ((game.mapLoader.tile[tileNum1].collisionProjectile == true) || (game.mapLoader.tile[tileNum2].collisionProjectile == true)) {
                    entity.collisionProjectile = true;

                }

                break;
            case "left":
                if (entity.x - entity.speed < 10) {
                    entity.collisionProjectile = true;
                    break;
                }
                entityLeftCol = (entityLeftX - 10 - entity.speed) / game.tileSize;
                tileNum1 = game.mapTile[entityTopRow][entityLeftCol];
                tileNum2 = game.mapTile[entityBottomRow][entityLeftCol];

                if ((game.mapLoader.tile[tileNum1].collisionTile == true) || (game.mapLoader.tile[tileNum2].collisionTile == true) || (leftbound < 4)) {
                    entity.collisionLeft = true;
                    entity.collisionOn = true;
                }
                if ((game.mapLoader.tile[tileNum1].collisionProjectile == true) || (game.mapLoader.tile[tileNum2].collisionProjectile == true) || (leftbound < 4)) {
                    entity.collisionProjectile = true;

                }
                break;
            case "right":
                entityRightCol = (entityRightX - 10 + entity.speed) / game.tileSize;
                tileNum1 = game.mapTile[entityTopRow][entityRightCol];
                tileNum2 = game.mapTile[entityBottomRow][entityRightCol];
                if ((game.mapLoader.tile[tileNum1].collisionTile == true) || (game.mapLoader.tile[tileNum2].collisionTile == true)) {
                    entity.collisionRight = true;
                    entity.collisionOn = true;
                }
                if ((game.mapLoader.tile[tileNum1].collisionProjectile == true) || (game.mapLoader.tile[tileNum2].collisionProjectile == true)) {
                    entity.collisionProjectile = true;

                }
                break;

        }
    }

    /*!  \fn public int checkEntityArray(Entity entity, Entity[] target)
         \brief Verifica coliziunea dintre prima entitatea si un vector de entitati.

          Folosita pentru a verifica coliziunea dintre player si vectorul de solati, daca exista coliziuea
         se retuneaza un index prin care afirm ca viata player-ului va scadea.

        \param entity Entitatea care nu are voie sa se deplaseze.
        \param target Entitatea cu care se face coliziunea
    */
    public int checkEntityArray(Entity entity, Entity[] target) {
        int index = -1;
        for (int i = 0; i < target.length; i++) {
            if (target[i] != null) {
                entity.solidArea.x = entity.x + entity.solidArea.x;
                entity.solidArea.y = entity.y + entity.solidArea.y;
                target[i].solidArea.x = target[i].x + target[i].solidArea.x;
                target[i].solidArea.y = target[i].y + target[i].solidArea.y;

                switch (entity.direction) {
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        if (entity.solidArea.intersects(target[i].solidArea)) {
                            if (target[i].collisionOn == true) {
                                entity.collisionOn = true;
                                index = i;
                            }
                            break;
                        }
                    case "down":
                        entity.solidArea.y += entity.speed;
                        if (entity.solidArea.intersects(target[i].solidArea)) {
                            entity.collisionOn = true;
                            index = i;
                        }
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        if (entity.solidArea.intersects(target[i].solidArea)) {
                            entity.collisionOn = true;
                            index = i;
                        }
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        if (entity.solidArea.intersects(target[i].solidArea)) {
                            entity.collisionOn = true;
                            index = i;
                        }
                        break;
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                target[i].solidArea.x = target[i].solidAreaDefaultX;
                target[i].solidArea.y = target[i].solidAreaDefaultY;
            }
        }
        return index;
    }

    /*!  \fn public boolean checkPlayer(Entity entity)
         \brief Verifica coliziunea dintre 2 entitati.

             Folosita pentru a verifica coliziunea dintre rege si player.


         \param entity Entitatea cu care se face coliziunea
        */
    public boolean checkPlayer(Entity entity) {
        boolean intersectsPLayer = false;

        entity.solidArea.x = entity.x + entity.solidArea.x;
        entity.solidArea.y = entity.y + entity.solidArea.y;
        game.player.solidArea.x = game.player.x + game.player.solidArea.x;
        game.player.solidArea.y = game.player.y + game.player.solidArea.y;

        switch (entity.direction) {
            case "up":
                entity.solidArea.y -= entity.speed;
                break;
            case "down":
                entity.solidArea.y += entity.speed;
                break;
            case "left":
                entity.solidArea.x -= entity.speed;
                break;
            case "right":
                entity.solidArea.x += entity.speed;
                break;
        }
        if (entity.solidArea.intersects(game.player.solidArea)) {
            entity.collisionOn = true;
            intersectsPLayer = true;

        }
        entity.solidArea.x = entity.solidAreaDefaultX;
        entity.solidArea.y = entity.solidAreaDefaultY;
        game.player.solidArea.x = game.player.solidAreaDefaultX;
        game.player.solidArea.y = game.player.solidAreaDefaultY;
        return intersectsPLayer;
    }

    /*!  \fn public int checkEntity(Entity entity, Entity target)
              \brief Verifica coliziunea dintre 2 entitati.

              Folosita pentru a verifica coliziunea dintre player si rege.

            \param entity Entitatea care nu are voie sa se deplaseze.
            \param target Entitatea cu care se face coliziunea
        */
    public int checkEntity(Entity entity, Entity target) {
        int index = -1;

        if (target != null) {
            entity.solidArea.x = entity.x + entity.solidArea.x;
            entity.solidArea.y = entity.y + entity.solidArea.y;
            target.solidArea.x = target.x + target.solidArea.x;
            target.solidArea.y = target.y + target.solidArea.y;

            switch (entity.direction) {
                case "up":
                    entity.solidArea.y -= entity.speed;
                    if (entity.solidArea.intersects(target.solidArea)) {
                        if (target.collisionOn == true) {
                            entity.collisionOn = true;
                            index = 1;
                        }
                        break;
                    }
                case "down":
                    entity.solidArea.y += entity.speed;
                    if (entity.solidArea.intersects(target.solidArea)) {
                        entity.collisionOn = true;
                        index = 1;
                    }
                    break;
                case "left":
                    entity.solidArea.x -= entity.speed;
                    if (entity.solidArea.intersects(target.solidArea)) {
                        entity.collisionOn = true;
                        index = 1;
                    }
                    break;
                case "right":
                    entity.solidArea.x += entity.speed;
                    if (entity.solidArea.intersects(target.solidArea)) {
                        entity.collisionOn = true;
                        index = 1;
                    }
                    break;
            }
            entity.solidArea.x = entity.solidAreaDefaultX;
            entity.solidArea.y = entity.solidAreaDefaultY;
            target.solidArea.x = target.solidAreaDefaultX;
            target.solidArea.y = target.solidAreaDefaultY;
        }

        return index;
    }
}