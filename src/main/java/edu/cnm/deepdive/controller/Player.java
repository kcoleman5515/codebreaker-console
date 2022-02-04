package edu.cnm.deepdive.controller;

import edu.cnm.deepdive.model.Game;
import edu.cnm.deepdive.service.GameRepository;
import java.io.InputStream;
import java.io.PrintStream;

public class Player {

  private final GameRepository repository;
  private final InputStream input;
  private final PrintStream output;

  public Player(InputStream input, PrintStream output) {
    this.input = input;
    this.output = output;
    repository = new GameRepository();
  }

  public void play(String pool, int length) {
    Game game = repository.startGame(pool, length);
    do {
      // TODO: Prompt user for next guess
      // TODO: Read guess from input.
      // TODO: Display outcome of guess (matches, etc.)
    } while (!game.isSolved());
    // TODO: Display ending summary.
  }
}
