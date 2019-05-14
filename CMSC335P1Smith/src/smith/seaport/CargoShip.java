package smith.seaport;

import java.util.Scanner;

/**
 * The class CargoShip represents a instance of a CargoShip. Cargo Ships
 * can have a {@link Dock} or {@link SeaPort} as a parent.
 *
 * @author Hunter Smith
 */
public class CargoShip extends Ship {

  @SuppressWarnings({"unused","FieldCanBeLocal"})
  private double cargoValue, cargoVolume, cargoWeight; //TODO not used for anything yet

  /**
   * Instantiates a new Cargo Ship.
   * <p>
   * <b>method modified from project instructions code fragment.</b>
   *
   * @param sc the scanner providing variable info.
   */
  public CargoShip(Scanner sc) {
    super(sc);

    if (sc.hasNextDouble()) {
      cargoWeight = sc.nextDouble();
    }
    if (sc.hasNextDouble()) {
      cargoVolume = sc.nextDouble();
    }
    if (sc.hasNextDouble()) {
      cargoValue = sc.nextDouble();
    }
  }

  /**
   * Returns a formatted representation of a CargoShip and its Jobs.
   * <p>
   * <b>method modified from project instructions code fragment.</b>
   *
   * @return CargoShip as String
   */
  @Override
  public String toString() {
    return String.format("%s %s", "Cargo ship:", super.toString());
  }
}
