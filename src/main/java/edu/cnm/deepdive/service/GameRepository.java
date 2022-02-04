package edu.cnm.deepdive.service;

import edu.cnm.deepdive.model.Game;
import java.io.IOException;
import retrofit2.Call;
import retrofit2.Response;

public class GameRepository {

  private final CodebreakerServiceProxy proxy;

  public GameRepository() {
    proxy = CodebreakerServiceProxy.getInstance();
  }

  public Game startGame(String pool, int length) throws IOException {
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

  public static class BadGameException extends IllegalArgumentException {

    public BadGameException(String message) {
      super(message);
    }
  }

}
