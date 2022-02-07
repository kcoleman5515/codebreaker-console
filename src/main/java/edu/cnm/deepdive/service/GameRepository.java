package edu.cnm.deepdive.service;

import edu.cnm.deepdive.model.Game;
import edu.cnm.deepdive.model.Guess;
import java.io.IOException;
import retrofit2.Call;
import retrofit2.Response;

public class GameRepository {

  private final CodebreakerServiceProxy proxy;

  public GameRepository() {
    proxy = CodebreakerServiceProxy.getInstance();
  }

  public Game startGame(String pool, int length) throws IOException, BadGameException {
    Game game = new Game();
    game.setPool(pool);
    game.setLength(length);
    Call<Game> call = proxy.startGame(game);
    Response<Game> response = call.execute();
    if (!response.isSuccessful()) {
      throw new BadGameException(response.message());
    }
    return response.body();
  }

  public Guess submitGuess(Game game, String text) throws IOException {
    Guess guess = new Guess();
    guess.setText(text);
    Call<Guess> call = proxy.submitGuess(game.getId(), guess);
    Response<Guess> response = call.execute();
    if (!response.isSuccessful()) {
      throw new BadGuessException(response.message());
    }
    Guess evaluatedGuess = response.body();
    game
        .getGuesses()
        .add(evaluatedGuess);
    //noinspection ConstantConditions
    if (evaluatedGuess.isSolution()) {
      game.setSolved(true);
      game.setText(guess.getText());
    }
    return evaluatedGuess;
  }

  public static class BadGameException extends IllegalArgumentException {

    public BadGameException(String message) {
      super(message);
    }
  }

  public static class BadGuessException extends IllegalArgumentException {

    public BadGuessException(String message) {
      super(message);
    }
  }
}
