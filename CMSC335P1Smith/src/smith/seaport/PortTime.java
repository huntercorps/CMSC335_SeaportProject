package smith.seaport;

import java.util.Scanner;

/**
 * The class PortTime represents a instance of a time.
 * <b>Note: Class not yet fully implemented</b>
 * It is used to track the arrival and dock time a {@link Ship}s.
 */
public class PortTime {

  @SuppressWarnings({"unused", "FieldCanBeLocal"})
  private int time; //TODO implement functionality

  /**
   * Instantiates a new Port time.
   *
   * @param sc the scanner providing variable info.
   */
  public PortTime(Scanner sc) {
    if (sc.hasNextInt()) {
      time = sc.nextInt();
    }
  }

}
