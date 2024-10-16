package InputHandler;

import State.State;
import game.Game;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/*! \class public class MouseHandler implements MouseListener, MouseMotionListener
    \brief Clasa se ocupa de gestionarea input-ului de la mouse.

 */
public class MouseHandler implements MouseListener, MouseMotionListener {

    public boolean mousePress = false;/*!< Primeste true cand click dreapta e apasat, false cand click dreapta e eliberat.*/
    public boolean mouseOverStart, mouseOverExit, mouseOverPauseExit, mouseOverHelp, mouseOverControls, mouseOverBack, mouseOverPlay, mouseOverMenu, mouseOverNewGame, mouseOverLoad, mouseOverLeader, mouseOverSettings, mouseOverYesScore, mouseOverYesLife, mouseOverYesAudio, mouseOverNoScore, mouseOverNoLife, mouseOverNoAudio;
    /*!< Variabilele sunt setate pe true cand mouse-ul este deasupra butonului cu numele butonului corespunzator.*/
    public int x, y;/*!< Coordonatele x si y ale mouse-ului.*/
    Game game;/*!< Variabila care primeste referinta la Game.*/

    /*! \fn public MouseHandler(Game game)
        \brief Constructorul clasei
        \param game Referinta la clasa Game
     */
    public MouseHandler(Game game) {
        this.game = game;
    }

    /*! \fn public void mousePressed(MouseEvent mouseEvent)
        \brief Verifica daca click stanga e apasat, daca click-ul e apasat seteaza variabila mousePress pe true

        \param mouseEvent Evenimentul cand mouse-ul e controlat
     */
    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        int code = mouseEvent.getButton();
        if (code == MouseEvent.BUTTON1) {
            mousePress = true;
        }
    }

    /*! \fn public void mouseReleased(MouseEvent mouseEvent)
        \brief Verifica daca s-a eliberat click stanga e apasat, daca click-ul e eliberat seteaza variabila mousePress pe false.

        \param mouseEvent Evenimentul cand mouse-ul e controlat
     */
    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        int code = mouseEvent.getButton();
        if (code == MouseEvent.BUTTON1) {
            mousePress = false;
        }
    }

    /*! \fn public void mouseMoved(MouseEvent mouseEvent)
        \brief Verifica daca mouse-ul se afla deasupra butoanelor.

        Pentru a seta variabilele mouseOver se verifica starea jocului si pozitionarea mouse-ului.
        \param mouseEvent Evenimentul cand mouse-ul e controlat
     */
    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        x = mouseEvent.getX();
        y = mouseEvent.getY();
        mouseOverStart = false;
        mouseOverExit = false;
        mouseOverHelp = false;
        mouseOverBack = false;
        mouseOverPlay = false;
        mouseOverMenu = false;
        mouseOverControls = false;
        mouseOverPauseExit = false;
        mouseOverLoad = false;
        mouseOverNewGame = false;
        mouseOverLeader = false;
        mouseOverSettings = false;
        mouseOverYesScore = false;
        mouseOverYesLife = false;
        mouseOverYesAudio = false;
        mouseOverNoScore = false;
        mouseOverNoLife = false;
        mouseOverNoAudio = false;

        //OVER START
        if (!game.menuState.helpDraw && !game.menuState.playDraw && State.GetState() == game.menuState && x > 550 && x < 820 && y > 350 && y < 450) {
            mouseOverStart = true;
        }
        //OVER PLAY in pause
        if (!game.pause.controlsDraw && State.GetState() == game.pause && x > 550 && x < 820 && y > 250 && y < 350) {
            mouseOverPlay = true;
        }
        //OVER EXIT
        if (!game.menuState.helpDraw && !game.menuState.playDraw && State.GetState() == game.menuState && x > 550 && x < 820 && y > 500 && y < 600) {
            mouseOverExit = true;
        }
        //OVER  EXIT in pause
        if (!game.pause.controlsDraw && State.GetState() == game.pause && x > 550 && x < 820 && y > 500 && y < 600) {
            mouseOverPauseExit = true;
        }
        //OVER HELP
        if (!game.menuState.helpDraw && !game.menuState.playDraw && State.GetState() == game.menuState && x > 550 && x < 620 && y > 610 && y < 680) {
            mouseOverHelp = true;
        }
        //OVER CONTROLS in pause
        if (!game.pause.controlsDraw && State.GetState() == game.pause && x > 550 && x < 820 && y > 375 && y < 475) {
            mouseOverControls = true;
        }
        //OVER BACK   in menu
        if (State.GetState() == game.menuState && (game.menuState.helpDraw || game.menuState.playDraw || game.menuState.settingsDraw || game.menuState.leaderDraw) && x > 10 && x < 170 && y > 700 && y < 780) {
            mouseOverBack = true;
        }
        //OVER  BACK IN pause
        if (State.GetState() == game.pause && game.pause.controlsDraw && x > 550 && x < 820 && y > 550 && y < 650) {
            mouseOverBack = true;
        }

        //OVER Menu in pause
        if (State.GetState() == game.winState && x > 550 && x < 820 && y > 550 && y < 650) {
            mouseOverMenu = true;
        }

        //OVER New game in menu
        if (game.menuState.playDraw && State.GetState() == game.menuState && x > 550 && x < 820 && y > 250 && y < 350) {
            mouseOverNewGame = true;
        }

        //OVER load game in menu
        if (game.menuState.playDraw && State.GetState() == game.menuState && x > 550 && x < 820 && y > 375 && y < 475) {
            mouseOverLoad = true;
        }
        //over leader in menu
        if (!game.menuState.playDraw && !game.menuState.helpDraw && State.GetState() == game.menuState && x > 750 && x < 820 && y > 610 && y < 680) {
            mouseOverLeader = true;
        }
        //over settings
        if (!game.menuState.playDraw && !game.menuState.helpDraw && State.GetState() == game.menuState && x > 1200 && x < 1270 && y > 700 && y < 770) {
            mouseOverSettings = true;
        }
        //Over yes
        if (State.GetState() == game.menuState && game.menuState.settingsDraw && x > 900 && x < 970 && y > 250 && y < 320) {
            mouseOverYesLife = true;
        }
        if (State.GetState() == game.menuState && game.menuState.settingsDraw && x > 900 && x < 970 && y > 320 && y < 390) {
            mouseOverYesScore = true;
        }
        if (State.GetState() == game.menuState && game.menuState.settingsDraw && x > 900 && x < 970 && y > 390 && y < 460) {
            mouseOverYesAudio = true;
        }
        //over NO
        if (State.GetState() == game.menuState && game.menuState.settingsDraw && x > 970 && x < 1040 && y > 250 && y < 320) {
            mouseOverNoLife = true;
        }
        if (State.GetState() == game.menuState && game.menuState.settingsDraw && x > 970 && x < 1040 && y > 320 && y < 390) {
            mouseOverNoScore = true;
        }
        if (State.GetState() == game.menuState && game.menuState.settingsDraw && x > 970 && x < 1040 && y > 390 && y < 460) {
            mouseOverNoAudio = true;
        }

    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {
    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {
    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {
    }

}
