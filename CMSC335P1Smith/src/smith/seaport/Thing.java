package smith.seaport;

import java.util.Scanner;

/**
 * The class Thing represents a instance of a Thing. Things are not directly instantiated by this
 * program, but provide for polymorphism/inheritance in the code.
 * <p>
 * <b>Note: All Things posses a index name and parent(null allowed).</b>
 *
 * @author Hunter Smith
 */
public class Thing implements Comparable<Thing> {

  private int index;
  private String name;
  private int parent;

  /**
   * Instantiates a new Thing.
   * <p>
   * <b>method modified from project instructions code fragment.</b>
   *
   * @param sc the scanner providing variable info.
   */
  public Thing(Scanner sc) {
    if (sc.hasNext()) {
      name = sc.next();
    }
    if (sc.hasNextInt()) {
      index = sc.nextInt();
    }
    if (sc.hasNextInt()) {
      parent = sc.nextInt();
    }
  }

  /**
   * Gets index of Thing.
   *
   * @return the index of Thing
   */
  public int getIndex() {
    return index;
  }

  /**
   * Gets name of Thing.
   *
   * @return the name of Thing
   */
  public String getName() {
    return name;
  }

  /**
   * Gets parent of Thing.
   *
   * @return the parent of Thing
   */
  public int getParent() {
    return parent;
  }

  /**
   * Compares the index of two Things
   *
   * @param other Thing to compare to.
   * @return a positive number(greater), negative number(less) or 0(equal).
   */
  @Override
  public int compareTo(Thing other) {
    return index - other.getIndex();
  }

  /**
   * Returns a formatted representation of a Thing.
   *
   * @return Thing as String.
   */
  @Override
  public String toString() {
    return String.format("%s %d", name, index);
  }

}
