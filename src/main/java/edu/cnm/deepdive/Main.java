package edu.cnm.deepdive;

import edu.cnm.deepdive.controller.Player;
import java.io.IOException;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) throws IOException {
    // TODO: Read command-line arguments for game difficulty.
    Scanner scanner = new Scanner(System.in);
    ResourceBundle bundle = ResourceBundle.getBundle("strings");
    Player player = new Player(scanner, System.out, bundle);
    player.play("ABCDEF", 3);
  }

}
