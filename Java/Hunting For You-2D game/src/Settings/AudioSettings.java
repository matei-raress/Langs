package Settings;

import Exeptions.ExceptionManager;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

/*! \fn public class AudioSettings extends Observer
    \brief Gestioneaza setarile sunetului.
 */
public class AudioSettings extends Observer {
    Clip clip;/*!< Variabila care ajuta la pornirea si oprirea sunetului.*/
    URL soundUrl;/*!< Primeste datele sunetului.*/
    boolean playAudio;/*<! Determina daca sunetul trebuie activat.*/

    /*! \fn public AudioSettings(Settings subject)
        \brief Constructorul clasei.

        \param subject Referinta care anunta daca setarea de audio a fost activata.
     */
    public AudioSettings(Settings subject) {
        this.subject = subject;
        this.subject.attach(this);
        soundUrl = getClass().getResource("/sound/backround.wav");
    }

    /*! \fn public void update()
        \brief Actualizeaza starea setarii audio.
     */
    @Override
    public void update() {
        playAudio = subject.getAudio();
    }

    /*! \fn   public boolean getAudio()
        \brief  Returneaza starea setarii audio.
     */
    public boolean getAudio() {
        return playAudio;
    }

    /*! \fn    public void playAudio()
       \brief  Porneste sau opreste sunetul, in functie de variabila playAudio.
    */
    public void playAudio() {

        if (playAudio) {

            this.setFile();
            this.play();
            this.loop();
        } else if (!playAudio) {


            this.stop();
        }
    }

    /*! \fn  public void setFile()
      \brief  Initializeaza sunetul.
   */
    public void setFile() {
        ExceptionManager a = new ExceptionManager();
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundUrl);
            clip = AudioSystem.getClip();
            clip.open(ais);


        } catch (Exception e) {
            a.UnknownException(e);
        }
    }

    /*! \fn  public void play()
         \brief  Porneste sunetul.
      */
    public void play() {
        clip.start();

    }

    /*! \fn public void loop()
           \brief  Pune sunetul intr-un loop.
        */
    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);

    }

    /*! \fn  public void stop()
            \brief Opreste sunetul.
         */
    public void stop() {
        try {
            clip.stop();
        } catch (NullPointerException e) {
        }
    }
}