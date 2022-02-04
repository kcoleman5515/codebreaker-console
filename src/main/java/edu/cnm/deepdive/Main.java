package edu.cnm.deepdive;

import edu.cnm.deepdive.controller.Player;
import java.io.IOException;

public class Main {

  public static void main(String[] args) throws IOException {
    // TODO: Read command-line arguments for game difficulty.
    Player player = new Player(System.in, System.out);
    player.play("ABCDEF", 3);
  }

}
