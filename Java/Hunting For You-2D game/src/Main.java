import game.Game;

import java.io.IOException;

/*! \class public class Main
    \brief De aici porneste jocul
 */
public class Main {
    public static void main(String[] args) throws IOException {
        Game game = new Game();
        game.startGameThread();
    }
}
