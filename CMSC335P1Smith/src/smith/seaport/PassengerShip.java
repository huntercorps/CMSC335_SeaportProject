package smith.seaport;

import java.util.Scanner;

/**
 * The class PassengerShip represents a instance of a PassengerShip. Passenger Ships can have a
 * {@link SeaPort} or {@link Dock} as a parent.
 *
 * @author Hunter Smith
 * @see Ship
 */
public class PassengerShip extends Ship {
  @SuppressWarnings({"unused","FieldCanBeLocal"}) //TODO: fields not yet implemented
  private int numberOfOccupiedRooms, numberOfPassengers, numberOfRooms;

  /**
   * Instantiates a new Passenger Ship.
   * <p>
   * <b>method modified from project instructions code fragment.</b>
   *
   * @param sc the scanner providing variable info.
   */
  public PassengerShip(Scanner sc) {
    super(sc);

    if (sc.hasNextInt()) {
      numberOfPassengers = sc.nextInt();
    }
    if (sc.hasNextInt()) {
      numberOfRooms = sc.nextInt();
    }
    if (sc.hasNextInt()) {
      numberOfOccupiedRooms = sc.nextInt();
    }
  }

  /**
   * Returns a formatted representation of a PassengerShip and its Jobs.
   * <p>
   * <b>method modified from project instructions code fragment.</b>
   *
   * @return PassengerShip as String
   */
  @Override
  public String toString() {
    return String.format("%s %s", "Passenger ship:", super.toString());
  }
}
