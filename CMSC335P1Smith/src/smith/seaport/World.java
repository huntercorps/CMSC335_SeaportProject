package smith.seaport;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;


/**
 * The class World represents a instance of a SeaPort Program World, containing. a list of {@link
 * SeaPort}s.
 * <p>
 * The World class provides a method to parse {@link Thing}s from a text file and present them with
 * the toString method. In addition, the world also provides support for searching for Things in the
 * world.
 *
 * @author Hunter Smith
 */
public class World extends Thing {

  private List<SeaPort> ports = new ArrayList<>();
  @SuppressWarnings({"unused", "FieldCanBeLocal"}) //TODO: not implemented remove for implementation
  private PortTime time;


  /**
   * Instantiates a new World.
   *
   * @param sc the scanner providing the text file contents to process.
   */
  public World(Scanner sc) {
    super(sc);
    time = null; //TODO: not implemented

    while (sc.hasNextLine()) {
      String line = sc.nextLine();
      process(line);
    }
  }


  /**
   * Processes text file line, calling the appropriate add Method to create and and {@link Thing} to
   * {@link SeaPort}.
   * <p>
   * <b>Note:Ignores lines that are commented out("//text") or do not match a case.
   * <p>method modified from project instructions code fragment.
   * </b>
   *
   * @param st the current string in the file being processed.
   */
  private void process(String st) {
    Scanner sc = new Scanner(st);

    if (!sc.hasNext()) {
      return;
    }
    switch (sc.next()) {
      case "port":
        addPort(sc);
        break;
      case "dock":
        addDock(sc);
        break;
      case "ship":
        addShip(sc);
        break;
      case "cship":
        addCShip(sc);
        break;
      case "pship":
        addPShip(sc);
        break;
      case "person":
        addPerson(sc);
        break;
      case "job":
        addJob(sc);
        break;
      default:
        break;
    }
  }


  /**
   * Creates and adds a SeaPort to this world.
   */
  private void addPort(Scanner sc) {
    SeaPort port = new SeaPort(sc);
    ports.add(port);
  }


  /**
   * Creates and adds a Dock to the parent {@link SeaPort}
   */
  private void addDock(Scanner sc) {
    Dock dock = new Dock(sc);
    if (getSeaPortByIndex(dock.getParent()) != null) {
      SeaPort parentPort = getSeaPortByIndex(dock.getParent());
      parentPort.getDocks().add(dock);
    }
  }

  /**
   * Creates and adds a Ship to the parent by calling {@link #assignShip(Ship)}
   */
  private void addShip(Scanner sc) {
    Ship ship = new Ship(sc);
    assignShip(ship);
  }


  /**
   * Creates and adds a CargoShip to the parent by calling {@link #assignShip(Ship)}
   */
  private void addCShip(Scanner sc) {
    Ship cShip = new CargoShip(sc);
    assignShip(cShip);
  }


  /**
   * Creates and adds a PassengerShip to the parent by calling {@link #assignShip(Ship)}
   */
  private void addPShip(Scanner sc) {
    Ship pShip = new PassengerShip(sc);
    assignShip(pShip);
  }


  /**
   * Creates and adds a Person to the parent {@link SeaPort}
   */
  private void addPerson(Scanner sc) {
    Person person = new Person(sc);
    if (getSeaPortByIndex(person.getParent()) != null) {
      SeaPort port = getSeaPortByIndex(person.getParent());
      port.getPersons().add(person);
    }
  }


  /**
   * Creates and adds a Job to the parent {@link Ship}
   */
  private void addJob(Scanner sc) {
    Job job = new Job(sc);
    if (getShipByIndex(job.getParent()) != null) {
      Ship parentShip = getShipByIndex(job.getParent());
      parentShip.getJobs().add(job);
    }
  }


  /**
   * Gets ship by index value.
   * <p>
   * <b>method modified from project instructions code fragment.</b>
   *
   * @param x the index value used to get ship.
   * @return the ship by index value
   */
  private Ship getShipByIndex(int x) {
    return ports
        .stream()
        .flatMap(port -> port.getShips().stream())
        .filter(ship -> ship.getIndex() == x)
        .findFirst().orElse(null);
  }


  /**
   * Gets sea port by index value.
   *
   * method modified from project instructions code fragment.
   *
   * @param x the index value used to get sea port.
   * @return the sea port by index value
   */
  private SeaPort getSeaPortByIndex(int x) {
    return ports
        .stream()
        .filter(port -> port.getIndex() == x)
        .findFirst().orElse(null);
  }


  /**
   * Gets dock by index value.
   * <p>
   * <b>method modified from project instructions code fragment.</b>
   *
   * @param x the index value used to get dock.
   * @return the dock by index value
   */
  private Dock getDockByIndex(int x) {
    return ports
        .stream()
        .flatMap(port -> port.getDocks().stream())
        .filter(dock -> dock.getIndex() == x)
        .findFirst().orElse(null);
  }


  /**
   * Assign ship to {@link SeaPort} or {@link Dock}.
   * <p>
   * <b>method modified from project instructions code fragment.</b>
   *
   * @param ship the ship to assign
   */
  private void assignShip(Ship ship) {
    Dock parentDock = getDockByIndex(ship.getParent());
    if (parentDock == null) {
      getSeaPortByIndex(ship.getParent()).getShips().add(ship);
      getSeaPortByIndex(ship.getParent()).getQueue().add(ship);
      return;
    }
    parentDock.setShip(ship);
    getSeaPortByIndex(parentDock.getParent()).getShips().add(ship);
  }


  /**
   * Searches for indices that match the input parameter. Partial matches allowed.
   *
   * @param index the index to search for.
   * @return the list of results containing matches and partial matches
   */
  List<Thing> searchForIndex(int index) {
    return collectAllThings()
        .stream().filter(thing -> String.valueOf(thing.getIndex()).contains(String.valueOf(index)))
        .collect(Collectors.toCollection(ArrayList::new));
  }


  /**
   * Search for names that match the input parameter. Partial matches allowed, and search is not
   * case sensitive.
   *
   * @param name the name
   * @return the list of results containing matches and partial matches
   */
  List<Thing> searchForName(String name) {
    return collectAllThings()
        .stream()
        .filter(thing -> thing.getName().toLowerCase().contains(name.toLowerCase())) //ignore case
        .collect(Collectors.toCollection(ArrayList::new));
  }


  /**
   * Search for skills that matches input parameter. Partial matches allowed , and search is not
   * case sensitive. Checks Jobs and Persons for Skill.
   *
   * @param skill the skill to search for.
   * @return the list of results containing matches and partial matches
   */
  List<Thing> searchForSkill(String skill) {
    List<Thing> results = new ArrayList<>();

    ports.forEach(port -> {
      //add all persons with skill to results
      results.addAll(port.getPersons().stream()
          .filter(person -> person.hasSkill(skill))
          .collect(Collectors.toList()));

      //add all job requirements that require skill to results
      results.addAll(port.getShips().stream()
          .flatMap(ship -> ship.getJobs().stream())
          .filter(job -> job.requirementsContainsSkill(skill))
          .collect(Collectors.toList()));
    });
    return results;
  }


  /**
   * Returns a list of all things that are children of ports in the world's port list. This helper
   * method is used to simplify searches.
   *
   * @return list of all the ports children.
   */
  private List<Thing> collectAllThings() {
    List<Thing> searchList = new ArrayList<>();
    ports.forEach(port -> {
      searchList.addAll(ports);
      searchList.addAll(port.getDocks());
      searchList.addAll(port.getShips());
      searchList.addAll(port.getPersons());
      searchList.addAll(port.getShips().stream()
          .flatMap(ship -> ship.getJobs().stream()).collect(Collectors.toList()));
    });
    return searchList;
  }


  /**
   * Returns a formatted representation of the world and its ports.
   *
   * @return World as String.
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder(">>>>>The world:");
    for (SeaPort port : ports) {
      sb.append("\n\n").append(port.toString());
    }
    return sb.toString();
  }
}
