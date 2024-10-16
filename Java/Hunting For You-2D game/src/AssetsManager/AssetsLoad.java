package AssetsManager;

import Exeptions.ExceptionManager;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/*! \class public class AssetsLoad
    \brief Clasa incarca fiecare elementel grafic necesar hartilor si a eroului.

 */
public class AssetsLoad {
    public static BufferedImage rightPlayerSheet = null; /*!< Sprite sheet-ul animatiilor din dreapta.*/
    public static BufferedImage[] rightPlayerStay = new BufferedImage[7];/*!< Imaginile in momentul cand eroul sta nemiscat cu privirea in partea dreapta.*/
    public static BufferedImage[] rightPlayerWalk = new BufferedImage[7];/*!< Imaginile in momentul cand eroul se misca in partea dreapta.*/
    public static BufferedImage[] rightPlayerAttack = new BufferedImage[7];/*!< Imaginile im momentul in care eroul ataca cu privirea in partea dreapta.*/
    public static BufferedImage leftPlayerSheet = null;/*!< Sprite sheet-ul animatiilor din dreapta.*/
    public static BufferedImage[] leftPlayerStay = new BufferedImage[7];/*!< Imaginile in momentul cand eroul sta nemiscat cu privirea in partea stanga.*/
    public static BufferedImage[] leftPlayerWalk = new BufferedImage[7];/*!< Imaginile in momentul cand eroul se misca in partea stanga.*/
    public static BufferedImage[] leftPlayerAttack = new BufferedImage[7];/*!< Imaginile im momentul in care eroul ataca cu privirea in partea stanga.*/
    public static BufferedImage transparent = null;/*!< O imagine goala pentru a marca tile-urile prin care pot trece toate entitatile.*/
    public static BufferedImage block = null;/*!< O imagine desenata pentru a marca tile-urile prin care nu poate trece nimic.*/
    public static BufferedImage fundalul1 = null;/*!< Imaginea de fundal pentru primul nivel.*/
    public static BufferedImage fundalul2 = null;/*!< Imaginea de fundal pentru a doua harta.*/
    public static BufferedImage gate = null;/*!< O imagine care marcheaza poarta care face trecerea din primul nivel in al doilea.*/

    /*! \fn public static void Init()
        \brief Functia initializaza referintele catre elementele grafice utilizate.

     */
    public static void Init() {
        ExceptionManager a = new ExceptionManager();
        try {
            rightPlayerSheet = ImageIO.read(AssetsLoad.class.getResource("/player/Ranger right.png"));
            leftPlayerSheet = ImageIO.read(AssetsLoad.class.getResource("/player/Ranger left.png"));
            for (int i = 0; i < 7; i++) {
                rightPlayerStay[i] = rightPlayerSheet.getSubimage((i + 1) * 48, 0, 48, 32);
                rightPlayerWalk[i] = rightPlayerSheet.getSubimage((i + 1) * 48, 48, 48, 32);
                rightPlayerAttack[i] = rightPlayerSheet.getSubimage((i + 1) * 48, 160, 48, 32);
                leftPlayerStay[i] = leftPlayerSheet.getSubimage((i + 1) * 48, 0, 48, 32);
                leftPlayerWalk[i] = leftPlayerSheet.getSubimage((i + 1) * 48, 48, 48, 32);
                leftPlayerAttack[i] = leftPlayerSheet.getSubimage((i + 1) * 47, 160, 48, 32);
            }
            transparent = ImageIO.read(AssetsLoad.class.getResource("/tiles/Transparent block.png"));
            block = ImageIO.read(AssetsLoad.class.getResource("/tiles/random.png"));
            fundalul1 = ImageIO.read(AssetsLoad.class.getResourceAsStream("/maps/mapa_1_1280x704.png"));
            fundalul2 = ImageIO.read(AssetsLoad.class.getResourceAsStream("/maps/castel.1376x800.png"));
            gate = ImageIO.read(AssetsLoad.class.getResourceAsStream("/maps/gate.png"));
        } catch (IOException e) {
            a.IOException(e);
        }
    }
}
