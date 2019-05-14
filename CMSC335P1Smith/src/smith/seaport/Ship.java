package smith.seaport;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The class Ship represents a instance of a Ship. Ships are not directly instantiated by this
 * program, but provide a parent class for {@link PassengerShip} and {@link CargoShip} which have a
 * "is-a" relationship with the Ship class.
 * <p>
 * All ships can posses a list of {@link Job}'s that can be completed. Ships can have a {@link
 * SeaPort} or {@link Dock} as a parent.
 *
 * @author Hunter Smith
 */
@SuppressWarnings({"unused", "FieldCanBeLocal"})
public class Ship extends Thing {

  private PortTime arrivalTime, dockTime; //TODO: PortTime variables are not yet implemented).
  private double draft, length, weight, width; //TODO: variables are not used).
  private List<Job> jobs = new ArrayList<>();


  /**
   * Instantiates a new Ship.
   * <p>
   * <b>method modified from project instructions code fragment.</b>
   *
   * @param sc the scanner providing variable info.
   */
  public Ship(Scanner sc) {
    super(sc);
    if (sc.hasNextDouble()) {
      weight = sc.nextDouble();
    }
    if (sc.hasNextDouble()) {
      length = sc.nextDouble();
    }
    if (sc.hasNextDouble()) {
      width = sc.nextDouble();
    }
    if (sc.hasNextDouble()) {
      draft = sc.nextDouble();
    }
  }

  /**
   * Gets the Ships {@link Job}s.
   *
   * @return the Ships jobs
   */
  public List<Job> getJobs() {
    return jobs;
  }

  /**
   * Returns a formatted representation of a Ship and its {@link Job}s. It doesn't declare itself as
   * a ship, that is delegated to subclasses
   * <p>
   * <b>method modified from project instructions code fragment.</b>
   *
   * @return Ship as String with Jobs
   */
  @Override
  public String toString() {
    String spacer = "       ";
    StringBuilder sb = new StringBuilder(super.toString());
//        StringBuilder sb = new StringBuilder(this.getClass().getSimpleName());
//        sb.append(": ").append(super.toString());
    if (jobs.size() == 0) {
      return sb.toString();
    }
    for (Job job : jobs) {
      sb.append("\n").append(spacer).append("- ").append(job);
    }
    return sb.toString();
  }
}
