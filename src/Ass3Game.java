//Nadav Menirav 330845678

/**
 * Ass3Game class, it is the Main class that is responsible for running the game.
 */
public class Ass3Game {
    /**
     * Main method that creates the game.
     * @param args Command-Line-Arguments
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.initialize();
        game.run();
    }
}