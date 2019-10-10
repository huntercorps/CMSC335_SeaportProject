# CMSC335_SeaportProject
## Introduction - the SeaPort Project series, Project 4
For this set of projects for the course, we wish to simulate some of the aspects of a number of Sea Ports. Here are the classes and their instance variables we wish to define:
 SeaPortProgram extends JFrame
o variables used by the GUI interface o world: World
 Thing implement Comparable <Thing> o index: int
o name: String
o parent: int
 World extends Thing
o ports: ArrayList <SeaPort>
o time: PortTime  SeaPort extends Thing
o docks: ArrayList <Dock>
o que: ArrayList <Ship> // the list of ships waiting to dock
o ships: ArrayList <Ship> // a list of all the ships at this port
o persons: ArrayList <Person> // people with skills at this port
 Dock extends Thing o ship: Ship
 Ship extends Thing
o arrivalTime, dockTime: PortTime
o draft, length, weight, width: double o jobs: ArrayList <Job>
 PassengerShip extends Ship
o numberOfOccupiedRooms: int o numberOfPassengers: int
o numberOfRooms: int
 CargoShip extends Ship
o cargoValue: double
o cargoVolume: double
o cargoWeight: double  Person extends Thing
o skill: String
 Job extends Thing - optional till Projects 3 and 4
o duration: double
o requirements: ArrayList <String>
// should be some of the skills of the persons  PortTime
o time: int
Eventually, in Projects 3 and 4, you will be asked to show the progress of the jobs using JProgressBar's.
1
Here's a very quick overview of all projects:
1. Read a data file, create the internal data structure, create a GUI to display the structure, and let the user search the structure.
2. Sort the structure, use hash maps to create the structure more efficiently.
3. Create a thread for each job, cannot run until a ship has a dock, create a GUI to show the
progress of each job.
4. Simulate competing for resources (persons with particular skills) for each job.
### Project 4 General Objectives
Project 4 - Concurrency
 Resource pools
o Threads competing for multiple resources
 Blocking threads
 Extending the GUI interface to visualize the resource pools and progress of the various threads.
### Documentation Requirements:
You should start working on a documentation file before you do anything else with these projects, and fill in items as you go along. Leaving the documentation until the project is finished is not a good idea for any number of reasons.
The documentation should include the following (graded) elements:
 Cover page (including name, date, project, your class information)
 Design
o including a UML class diagram
o classes, variables and methods: what they mean and why they are there o tied to the requirements of the project
 User's Guide
o how would a user start and run your project
o any special features
o effective screen shots are welcome, but don't overdo this
 Test Plan
o do this BEFORE you code anything
o what do you EXPECT the project to do
o justification for various data files, for example
 Lessons Learned
o express yourself here
o a way to keep good memories of successes after hard work
2

### Project 4 Specific Goals:
Extend project 3 to include making jobs wait until people with the resources required by the job are available at the port.
Elaboration:
1. Reading Job specifications from a data file and adding the required resources to each Job instance.
2. Resource pools - SeaPort.ArrayList <Person> list of persons with particular skills at each port, treated as resource pools, along with supporting assignment to ships and jobs.
3. Job threads - using the resource pools and supporting the concept of blocking until required resources are available before proceeding.
4. The Job threads should be efficient:
1. If the ship is at a dock and all the people with required skills are available, the job should
start.
2. Otherwise, the Job should not hold any resources if it cannot progress.
3. Use synchronization to avoid race conditions.
4. Each Job thread should hold any required synchronization locks for a very short period.
5. When a job is over, all the resources used by the job (the people) should be released
back to the port.
6. When all the jobs of a ship are done, the ship should depart the dock and if there are
any ships in the port que, one of then should should be assigned to the free dock, and
that ships jobs can now try to progress.
7. NOTE: If a job can never progress because the port doesn't have enough skills among all
the persons at the port, the program should report this and cancel the job.
5. GUI showing:
o Resources in pools - how many people with skill are currently available
o Thread progress, resources acquired, and resources requests still outstanding
### Deliverables:
1. Java source code files
2. Data files used to test your program
3. Configuration files used
4. A well-written document including the following sections:
a. Design: including a UML class diagram showing the type of the class relationships
b. User's Guide: description of how to set up and run your application
c. Test Plan: sample input and expected results, and including test data and results, with
screen snapshots of some of your test cases
d. Optionally, Comments: design strengths and limitations, and suggestions for future
improvement and alternative approaches
e. Lessons Learned
f. Use one of the following formats: MS Word docx or PDF.
3

Your project is due by midnight, EST, on the day of the date posted in the class schedule. We do not recommend staying up all night working on your project - it is so very easy to really mess up a project at the last minute by working when one was overly tired.
Your instructor's policy on late projects applies to this project.
Submitted projects that show evidence of plagiarism will be handled in accordance with UMUC Policy 150.25 — Academic Dishonesty and Plagiarism.
### Format:
The documentation describing and reflecting on your design and approach should be written using Microsoft Word or PDF, and should be of reasonable length. The font size should be 12 point. The page margins should be one inch. The paragraphs should be double spaced. All figures, tables, equations, and references should be properly labeled and formatted using APA style.
### Coding Hints:
 Code format: (See Google Java Style guide for specifics (https://google.github.io/styleguide/javaguide.html))
o o o o o o o o
o Your program should have no warnings
o Use the following compiler flag to show all warnings:
javac -Xlint *.java
o More about setting up IDE's to show warnings
o Generics - your code should use generic declarations appropriately, and to eliminate all
warnings  Elegance:
o just the right amount of code
o effective use of existing classes in the JDK
o effective use of the class hierarchy, including features related to polymorphism.
 GUI notes:
o GUI should resize nicely
o DO NOT use the GUI editor/generators in an IDE (integrated development environment, such as Netbeans and Eclipse)
o Do use JPanel, JFrame, JTextArea, JTextField, JButton, JLabel, JScrollPane
 panels on panels gives even more control of the display during resizing
 JTable and/or JTree for Projects 2, 3 and 4
header comment block, including the following information in each source code file: file name
date
author
purpose
appropriate comments within the code appropriate variable and function names correct indentation
code submitted should have no compilation or run-time errors  Warnings:
 Errors: o
 Font using the following gives a nicer display for this program, setting for the JTextArea jta:
jta.setFont (new java.awt.Font ("Monospaced", 0, 12)); o GridLayout and BorderLayout - FlowLayout rarely resizes nicely
 GridBagLayoutforextremecontroloverthedisplays
 youmaywishtoexploreotherlayoutmanagers
o ActionListener, ActionEvent - responding to JButton events
 Starting with JDK 8, lambda expression make defining listeners MUCH simpler. See the example below, with jbr (read), jbd (display) and jbs (search) three different JButtons.
jcb is a JComboBox <String> and jtf is a JTextField.
jbr.addActionListener (e -> readFile());
jbd.addActionListener (e -> displayCave ());
jbs.addActionListener (e -> search ((String)(jcb.getSelectedItem()),
jtf.getText()));
o JFileChooser - select data file at run time
o JSplitPane - optional, but gives user even more control over display panels
