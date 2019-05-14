package smith.seaport;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The class SeaPort represents a instance of a SeaPort. All SeaPorts can posses lists of {@link
 * Dock}s, {@link Ship}-queue, {@link Ship}s, and {@link Person}s.
 * <p>
 * SeaPorts do not have a parent, the parent is null.
 *
 * @author Hunter Smith
 */
public class SeaPort extends Thing {

  private List<Dock> docks = new ArrayList<>();
  private List<Ship> queue = new ArrayList<>(); // the list of ships waiting to dock
  private List<Ship> ships = new ArrayList<>(); // a list of all the ships at this port
  private List<Person> persons = new ArrayList<>(); // people with skills at this port

  /**
   * Instantiates a new Seaport.
   *
   * @param sc the scanner providing variable info.
   */
  public SeaPort(Scanner sc) {
    super(sc);
  }

  /**
   * Gets {@link Dock}s present at port.
   *
   * @return the docks at the port
   */
  public List<Dock> getDocks() {
    return docks;
  }

  /**
   * Gets {@link Ship} in queue at port.
   *
   * @return the Ships in queue at port
   */
  public List<Ship> getQueue() {
    return queue;
  }

  /**
   * Gets {@link Ship} at port.
   *
   * @return the ships at the port
   */
  public List<Ship> getShips() {
    return ships;
  }

  /**
   * Gets {@link Person}s at port.
   *
   * @return the people assign to the port
   */
  public List<Person> getPersons() {
    return persons;
  }

  /**
   * Returns a formatted representation of a Seaport and {@link Thing}'s with the seaport as a
   * parent.
   * <p>
   * <b>method modified from project instructions code fragment.</b>
   *
   * @return Seaport and children as String
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("SeaPort: " + super.toString());
    String spacer = "\n >";
    String bullet = "---";

    docks.forEach(dock -> sb.append("\n\n  ").append(dock));

    sb.append("\n\n").append(bullet).append(" List of all ships in queue:");
    queue.forEach(ship -> sb.append(spacer).append(ship));

    sb.append("\n\n").append(bullet).append(" List of all ships:");
    ships.forEach(ship -> sb.append(spacer).append(ship));

    sb.append("\n\n").append(bullet).append(" List of all persons:");
    persons.forEach(person -> sb.append(spacer).append(person));

    return sb.toString();
  }

}