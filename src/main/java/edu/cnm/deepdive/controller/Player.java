package edu.cnm.deepdive.controller;

import edu.cnm.deepdive.model.Game;
import edu.cnm.deepdive.model.Guess;
import edu.cnm.deepdive.service.GameRepository;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Player {

  private static final String GUESS_PROMPT_FORMAT_KEY = "guess_prompt_format";
  private static final String GUESS_OUTCOME_FORMAT_KEY = "guess_outcome_format";
  private static final String SUMMARY_FORMAT_KEY = "summary_format";

  private final GameRepository repository;
  private final Scanner scanner;
  private final PrintStream output;
  private final ResourceBundle bundle;

  public Player(Scanner scanner, PrintStream output,
      ResourceBundle bundle) {
    this.scanner = scanner;
    this.output = output;
    this.bundle = bundle;
    repository = new GameRepository();
  }

  public void play(String pool, int length) throws IOException {
    Game game = repository.startGame(pool, length);
    Guess guess;
    do {
      String input = getGuess(game);
      guess = repository.submitGuess(game, input);
      displayOutcome(guess);
    } while (!game.isSolved());
    long endTime = guess.getCreated().getTime();
    // TODO: Explore starting time from first guess
    long elapsedTime = endTime - game.getCreated().getTime();
    long seconds = Math.round(elapsedTime / 1000.0);
    System.out.printf(bundle.getString(SUMMARY_FORMAT_KEY), game.getText(), game.getGuesses().size(), seconds);
  }

  private String getGuess(Game game) {
    output.printf(bundle.getString(GUESS_PROMPT_FORMAT_KEY), game.getPool(), game.getLength());
    String input = scanner.nextLine().trim();
    //TODO: Validate input using the pool and length.
    return input;
  }

  private void displayOutcome(Guess guess) {
    output.printf(bundle.getString(GUESS_OUTCOME_FORMAT_KEY),
        guess.getExactMatches(), guess.getNearMatches());
    // TODO: Include appropriate indicator if solved.
  }
}
