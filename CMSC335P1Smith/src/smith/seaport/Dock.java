package smith.seaport;

import java.util.Scanner;

/**
 * The class Dock represents a instance of a Dock. Docks have a {@link SeaPort}
 * as a parent and can be the parent of a {@link Ship} (i.e., have a ship assigned to it).
 *
 * @author Hunter Smith
 */
public class Dock extends Thing {

  private Ship ship;

  /**
   * Instantiates a new Dock.
   *
   * @param sc the scanner providing variable info.
   */
  public Dock(Scanner sc) {
    super(sc);
  }

  /**
   * Assigns a ship to this Dock.
   *
   * @param ship the ship to assign.
   */
  public void setShip(Ship ship) {
    this.ship = ship;
  }

  /**
   * Returns a formatted representation of a Dock and {@link Ship} at the Dock if present.
   * <p>
   * <b>method modified from project instructions code fragment.</b>
   *
   * @return Dock and children as String
   */
  @Override
  public String toString() {
    String spacer = "    ";
    String result = "Dock: " + super.toString();
    return (ship != null) ? result + "\n" + spacer + "Ship: " + ship : result;
  }

}
