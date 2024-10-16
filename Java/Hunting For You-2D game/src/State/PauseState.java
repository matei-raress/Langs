package State;

import InputHandler.MouseHandler;
import game.Game;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;


/*! \class public class PauseState extends State
    \brief Se ocupa de gestionarea elementelor in meniul de pauza.

    Pauza este activata de fiecare data cand player-ul se afla in joc, mai exact in nivelul 1 sau 2, si se apasa tasta
    Esc sau P pentru a activa variabila game.Pause.
 */
public class PauseState extends State{
    BufferedImage pauseScroll ;
    Font font,instructions ;
    public State prev ;
    MouseHandler mouse ;
    BufferedImage[] buttons=new BufferedImage[6] ;
    public boolean controlsDraw ;
    BufferedImage backButton ;
    BufferedImage backButtonOver ;

/*! \fn  public PauseState(Game game, MouseHandler mouseHandler)
      \brief Constructorul clasei PauseState.

     \param game Referinta la clasa Game.
     \param mouseHandler Referinta la MouseHandler.
   */
    public PauseState(Game game, MouseHandler mouseHandler)  {
        super(game);
        this.mouse=mouseHandler ;
        GetImage();
        font=new Font("font",Font.BOLD,90);

    }

    /*! \fn    public void Update()
      \brief Actualizarea meniului de pauza.
     Verifica inputul mouse-ului, daca este peste controls si se apasa click stanga se afiseaza controalele,
     daca este peste meniu se salveaza checkpoint-ul si se intra in starea de meniu.

   */
    @Override
    public void Update()  {

        if(!game.Pause){
            State.SetState(prev);
        }
        else if(mouse.mouseOverPlay && mouse.mousePress){
            State.SetState(prev) ;
            game.Pause=false ;
        }
        else if(mouse.mousePress && mouse.mouseOverPauseExit) {

                if(prev== game.levelOneState) {
                    game.database.insertSaveInDatabase(1);
                }
                else if(prev== game.levelTwoState){
                    game.database.insertSaveInDatabase(2);
                }
            State.SetState(game.menuState);
            game.Pause=false;
            game.menuState.playDraw=false ;
        }
        else if(mouse.mouseOverControls && mouse.mousePress){
            controlsDraw=true ;
        }

    }

    /*! \fn   public void Draw(Graphics2D g)
       \brief Desenarea imaginii de Pause
       Deseneaza imaginilie din state-ul precedent si meniul de pauza.
       \param g Instrumentul de desenare.
    */
    @Override
    public void Draw(Graphics2D g) {
        if(prev!=null){
        prev.Draw(g) ;}
        drawPause(g);

    }

    @Override
    public void SetStateValues()  {

    }

    /*! \fn  public void drawPause(Graphics2D g)
       \brief Desenarea meniului de pauza

       \param g Instrumentul de desenare.
    */
    public void drawPause(Graphics2D g){
        g.setFont(font);
        g.setColor(Color.BLACK);
        g.drawImage(pauseScroll,400,0,600,800,null);
        g.drawString("PAUSE",525,150 );

        if(!controlsDraw) {
            if (mouse.mouseOverPlay) {
                g.drawImage(buttons[3], 550, 250, 270, 100, null);
            } else {
                g.drawImage(buttons[0], 550, 250, 270, 100, null);
            }

            if (mouse.mouseOverPauseExit) {
                g.drawImage(buttons[1], 550, 500, 270, 100, null);
            } else {

                g.drawImage(buttons[4], 550, 500, 270, 100, null);
            }

            if (mouse.mouseOverControls) {
                g.drawImage(buttons[5], 550, 375, 270, 100, null);
            } else {
                g.drawImage(buttons[2], 550, 375, 270, 100, null);
            }
        }
        else{
            g.setFont(instructions);
            g.setColor(Color.BLACK);
            g.drawString("W : Walk up",440,250 );
            g.drawString("A : Walk left",440,290 );
            g.drawString("S : Walk down",440,330 );
            g.drawString("D : Walk right",440,370 );
            g.drawString("ESC/P : Pause",440,410 );
            g.drawString("LEFT CLICK : ATTACK",440,450 );
            g.drawString("SPACE : DASH",440,490 );

            if(mouse.mouseOverBack){
                g.drawImage(backButtonOver,550,550,270,100,null);
            }
            else{
                g.drawImage(backButton,550,550,270,100,null);
            }

            if(mouse.mouseOverBack && mouse.mousePress){
                controlsDraw =false ;}
        }
        }



    /*! \fn    public void setPrevious(State prev)
            \brief Seteaza starae trecuta.

            \param prev Stare trecuta.
         */
    public void setPrevious(State prev){
        this.prev=prev;
    }

    /*! \fn    public void GetImages()
         \brief Initializarea imaginilor din meniul de pauza.

      */
    public void GetImage(){
        try {
            pauseScroll = ImageIO.read(MenuState.class.getResource("/ui/UI.png"));
            buttons[0] = ImageIO.read(MenuState.class.getResource("/pause/play.png"));
            buttons[1] = ImageIO.read(MenuState.class.getResource("/meniu/exit button.png"));
            buttons[2] = ImageIO.read(MenuState.class.getResource("/pause/controls.png"));
            buttons[3] = ImageIO.read(MenuState.class.getResource("/pause/play_over.png"));
            buttons[4] = ImageIO.read(MenuState.class.getResource("/meniu/exit button over.png"));
            buttons[5] = ImageIO.read(MenuState.class.getResource("/pause/controls_over.png"));
            backButton=ImageIO.read(MenuState.class.getResource("/meniu/backButton.png")); ;
            backButtonOver=ImageIO.read(MenuState.class.getResource("/meniu/backButton_over.png")); ;
            font=new Font("font",Font.BOLD,90);
            instructions=new Font("",Font.PLAIN,30);


        }
        catch(Exception e){

        }
    }
}
