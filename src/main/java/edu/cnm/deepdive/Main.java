package edu.cnm.deepdive;

import edu.cnm.deepdive.controller.Player;
import java.io.IOException;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Main {

  private static final String DEFAULT_POOL = "ABCDEF";
  private static final int DEFAULT_LENGTH = 4;
  public static final String BUNDLE_BASE_NAME = "strings";
  public static final String BAD_NUMBER_FORMAT_KEY = "bad_number_format";
  public static final String UNAVAILABLE_SERVICE_MESSAGE_KEY = "unavailable_service_message";
  public static final String WHITESPACE_PATTERN = "\\s+";
  public static final String MISSING_BUNDLE_FORMAT_KEY = "missing_bundle_format";

  @SuppressWarnings("ConstantConditions")
  public static void main(String[] args) {
      ResourceBundle bundle = null;
    try {
      bundle = ResourceBundle.getBundle(BUNDLE_BASE_NAME);
      String pool = (args.length > 0) ? args[0].replaceAll(WHITESPACE_PATTERN, "") : DEFAULT_POOL;
      int length = (args.length > 1) ? Integer.parseInt(args[1]): DEFAULT_LENGTH;
      Scanner scanner = new Scanner(System.in);
      Player player = new Player(scanner, System.out, bundle);
      player.play(pool, length);
    } catch (MissingResourceException e) {
      System.out.printf(bundle.getString(MISSING_BUNDLE_FORMAT_KEY), BUNDLE_BASE_NAME);
      e.printStackTrace();
    }catch (NumberFormatException e) {
      System.out.printf(bundle.getString(BAD_NUMBER_FORMAT_KEY), args[1]);
    } catch (IOException e) {
      System.out.println(bundle.getString(UNAVAILABLE_SERVICE_MESSAGE_KEY));
      e.printStackTrace();
    }
  }

}
