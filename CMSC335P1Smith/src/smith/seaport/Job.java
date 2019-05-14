package smith.seaport;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The class Job represents a instance of a Job. Jobs typically have a {@link Ship} as a parent.
 * <p>
 * <b>Note: functionality not yet implemented</b>
 * A job can require zero or more skill to complete. When job requirements are met the job can be
 * completed after the duration.
 *
 * @author Hunter Smith
 */
public class Job extends Thing {

  @SuppressWarnings({"unused","FieldCanBeLocal"})
  private double duration; //TODO Implement duration usage
  private List<String> requirements = new ArrayList<>();

  /**
   * Instantiates a new Job.
   *
   * @param sc the scanner providing variable info.
   */
  public Job(Scanner sc) {
    super(sc);
    if (sc.hasNextDouble()) {
      duration = sc.nextDouble();
    }
    while (sc.hasNext()) {
      String line = sc.next();
      requirements.add(line);
    }
  }

  /**
   * Gets requirements of Job.
   *
   * @return the job requirements
   */
  @SuppressWarnings("unused")
  public List<String> getRequirements() {
    return requirements;
  }

  /**
   * Returns true if Requirements contains skill.
   *
   * @param skill the skill(string) whose presence is being tested.
   * @return True if requirements contains skill (partial matches allowed).
   */
  Boolean requirementsContainsSkill(String skill) {
    return requirements.stream().anyMatch(s -> s.toLowerCase().contains(skill.toLowerCase()));
  }

  /**
   * Returns a formatted representation of a Job and its requirements(skills needed) for
   * completion.
   *
   * @return Job with requirements as String
   */
  @Override
  public String toString() {
    String spacer = "   ";
    StringBuilder sb = new StringBuilder("Job: ").append(super.toString());
    for (String req : requirements) {
      sb.append(spacer).append(req);
    }
    return sb.toString();
  }

}
