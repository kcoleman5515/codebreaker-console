package edu.cnm.deepdive;

import edu.cnm.deepdive.model.Game;
import edu.cnm.deepdive.service.GameRepository;
import java.io.IOException;

public class Main {

  public static void main(String[] args) throws IOException {
    // TODO: Read command-line arguments for game difficulty.
    // TODO: Request (POST) start of new game from service.
    GameRepository repository = new GameRepository();
    Game game = repository.startGame("ABCD", 3);
    System.out.println(game);
    // TODO: While the game is not completed:
    //    - Get user's guess.
    //    - Send guess (POST) to service.
    //    - Display matches (from service response) to user.
    // TODO: Display statistics (# of guesses, time to love) to user.
  }

}
