package smith.seaport;

import java.util.Scanner;

/**
 * The class Person represents a instance of a person.
 * <p>
 * <b>Not Implemented yet</b>
 * <p>
 * A person has a skill that can be used to complete a {@link Job} when a job of it's parent has the
 * skill requirement that matches.
 *
 * @author Hunter Smith
 */
public class Person extends Thing {

  private String skill;

  /**
   * Instantiates a new Person.
   *
   * @param sc the scanner providing variable info.
   */
  public Person(Scanner sc) {
    super(sc);
    if (sc.hasNext()) {
      skill = sc.next();
    }
  }

  /**
   * Has skill boolean. Returns true if skill is present.
   * <p>
   * <b>Note: partial matches allowed</b>
   *
   * @param skill the skill(string) whose presence is being tested.
   * @return True if person has skill.
   */
  Boolean hasSkill(String skill) {
    return this.skill.toLowerCase().contains(skill.toLowerCase()); //allow partial matches
  }

  /**
   * Returns a formatted representation of a person.
   *
   * @return person as string.
   */
  @Override
  public String toString() {
    String spacer = "    ";
    return String.format("Person: %s%s%s", super.toString(), spacer, skill);
  }
}
