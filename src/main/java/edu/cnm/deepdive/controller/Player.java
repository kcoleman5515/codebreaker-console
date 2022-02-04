package edu.cnm.deepdive.controller;

import edu.cnm.deepdive.model.Game;
import edu.cnm.deepdive.model.Guess;
import edu.cnm.deepdive.service.GameRepository;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.Reader;

public class Player {

  private final GameRepository repository;
  private final InputStream input;
  private final PrintStream output;

  public Player(InputStream input, PrintStream output) {
    this.input = input;
    this.output = output;
    repository = new GameRepository();
  }

  public void play(String pool, int length) throws IOException {
    try (
        Reader reader = new InputStreamReader(input);
        BufferedReader buffer = new BufferedReader(reader);
    ) {
      Game game = repository.startGame(pool, length);
      do {
        String input = getGuess(game, buffer);
        Guess guess = repository.submitGuess(game, input);
        displayOutcome(guess);
      } while (!game.isSolved());
      // TODO: Display ending summary.
    }
  }

  private String getGuess(Game game, BufferedReader buffer) throws IOException {
    output.printf("Pool: \"%s\"; length: %d.%nEnterguess: ", game.getPool(), game.getLength());
    String input = buffer.readLine().trim();
    //TODO: Validate input using the pool and length.
    return input;
  }

  private void displayOutcome(Guess guess) {
    output.printf("Exact matches: %d; near matches: %d.%n",
        guess.getExactMatches(), guess.getNearMatches());
    // TODO: Include appropriate indicator if solved.
  }
}
