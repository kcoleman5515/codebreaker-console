package edu.cnm.deepdive;

import edu.cnm.deepdive.controller.Player;
import java.io.IOException;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Main {

  private static final String DEFAULT_POOL = "ABCDEF";
  private static final int DEFAULT_LENGTH = 4;
  private static final String BUNDLE_BASE_NAME = "strings";
  private static final String BAD_NUMBER_FORMAT_KEY = "bad_number_format";
  private static final String UNAVAILABLE_SERVICE_MESSAGE_KEY = "unavailable_service_message";
  private static final String WHITESPACE_PATTERN = "\\s+";
  private static final String MISSING_BUNDLE_FORMAT_KEY = "missing_bundle_format";
  private static final String OUT_OF_RANGE_FORMAT_KEY = "out_of_range_format";
  public static final int MINIMUM_CODE_LENGTH = 1;
  public static final int MAXIMUM_CODE_LENGTH = 20;
  public static final String PLAY_AGAIN_MESSAGE_KEY = "play_again_message";
  public static final String NEGATIVE_RESPONSE_KEY = "negative_response";

  @SuppressWarnings("ConstantConditions")
  public static void main(String[] args) {
      ResourceBundle bundle = null;
      int length = 0;
    try {
      bundle = ResourceBundle.getBundle(BUNDLE_BASE_NAME);
      String pool = (args.length > 0) ? args[0].replaceAll(WHITESPACE_PATTERN, "") : DEFAULT_POOL;
       length = (args.length > 1) ? Integer.parseInt(args[1]): DEFAULT_LENGTH;
      if (length < MINIMUM_CODE_LENGTH || length > MAXIMUM_CODE_LENGTH) {
        throw new IllegalArgumentException();
      }
      Scanner scanner = new Scanner(System.in);
      Player player = new Player(scanner, System.out, bundle);
      do {
        player.play(pool, length);
      } while (playAgain(bundle, scanner));
    } catch (MissingResourceException e) {
      System.out.printf(bundle.getString(MISSING_BUNDLE_FORMAT_KEY), BUNDLE_BASE_NAME);
      e.printStackTrace();
    } catch (NumberFormatException e) {
      System.out.printf(bundle.getString(BAD_NUMBER_FORMAT_KEY), args[1]);
    } catch (IllegalArgumentException e) {
      System.out.printf(bundle.getString(OUT_OF_RANGE_FORMAT_KEY), length, MINIMUM_CODE_LENGTH, MAXIMUM_CODE_LENGTH);
    } catch (IOException e) {
      System.out.println(bundle.getString(UNAVAILABLE_SERVICE_MESSAGE_KEY));
      e.printStackTrace();
    }
  }

  private static boolean playAgain(ResourceBundle bundle, Scanner scanner) {
    boolean result;
    System.out.print(bundle.getString(PLAY_AGAIN_MESSAGE_KEY));
    String response = scanner
        .nextLine()
        .trim()
        .toLowerCase();
    return (response.isEmpty()
        || response.charAt(0) != bundle.getString(NEGATIVE_RESPONSE_KEY).charAt(0));
  }
}
