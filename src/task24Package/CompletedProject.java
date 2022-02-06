package task24Package;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

/**
 * Date: Jan 27-2022
 * <p>
 * This is an application that keeps track of the many projects worked 
 * on by the structural engineering firm called "Poised".
 * 
 * @author Xhasa Madondile
 * @version 1.00
 * 
 * 
 */
public class CompletedProject {

	private static Scanner input;
	
	/**
	 * Main method of this application
	 * 
	 * @param args array of string arguments
	 * @throws IOException general class of exceptions produced 
	 *  by failed or interrupted I/O operations  
	 */
	public static void main(String[] args) throws IOException{
		
		try {			
			input = new Scanner(System.in); //Create a scanner that will store all the user inputs.
			File file = new File("MainProjects.txt"); //Create a File object.
			FileWriter fw = new FileWriter("MainProjects.txt", true); //Create a FileWriter class that writes a list of existing projects.
			FileWriter fw2 = new FileWriter("CompletedProjects.txt", true); //Create a FileWriter class that writes a list of finalised projects.
			Date thisDate = new Date(); //Date class to reference the current date
			SimpleDateFormat dateForm = new SimpleDateFormat("yyyy-MM-dd HH:mm a"); //Date format class.
			
			//Create ArrayLists that store lists of entered project objects.
			
			List<Project> projectList = new ArrayList<Project>(); 
			List<Project> projectList2 = new ArrayList<Project>(); //Create an ArrayList that stores a list containing projects that are going to be renamed using the building type + customer surname.
			List<Person> architectList = new ArrayList<Person>(); 
			List<Person> contractorList = new ArrayList<Person>(); 
			List<Person> customerList = new ArrayList<Person>(); 
			
			//Declare and initialize all the listIterators that are going to be used throughout the course of the program.
			
			ListIterator<Project> li1 = null; 
			ListIterator<Person> li2 = null;
			ListIterator<Person> li3 = null;
			ListIterator<Person> li4 = null;
			
			//Declare and initialize all variables that will be needed throughout the course of the program.
			
			int Option;
			int size;
			boolean again = true;
			String projectName = "";
			int projectNumber = 0;
			int projNumber = 0;
			String buildingType = "";
			String physicalAddress = "";
			int EFR_Number = 0;
			double totalCharged = 0;
			double totalPaid = 0;
			String name3 = "";
			String surname3 = "";
			String physicalAddress3 = "";
			String deadLine = dateForm.format(thisDate);
			String dueDate = "";
			String telephoneNumber3 = "";
			String emailAddress3 = "";
			
			System.out.println(deadLine); // Display the date and time at which the user enters the program.
			
			while (again) {
				try {
					do { //Menu options.
						System.out.println("\nPlease select your choice of operation out of the following options:\n");
						System.out.println("1.INSERT a project.");
						System.out.println("2.DISPLAY inserted projects.");
						System.out.println("3.SEARCH for a project.");
						System.out.println("4.UPDATE a project.");
						System.out.println("5.FINALISE a project.");
						System.out.println("6.DISPLAY projects that are past the due date.");
						System.out.println("7.EXIT THE PROGRAM...");
						System.out.print("Enter your choice: ");
						Option = input.nextInt();
						input.nextLine();
						
						switch (Option) {
							case 1: 
								do {
									System.out.print("\nHow many projects would you like to add? ");
									size = input.nextInt();
									input.nextLine(); //Enter the number of projects that are within the given range.
									while (size < 1 || size > 5) { //Display the below error message if the user enters a project size that is out of range. 
										System.out.println("\nERROR...!!!The size you've just entered is not within the specified range....");									
										System.out.print("Please enter a project size that is greater than 1 but less than 5: ");
										size = input.nextInt();
										input.nextLine();
									}
								} while (size < 1 || size > 5);
								
								//Declare and initialize all the arrays of objects that are going to be used throughout the course of the program.
								
								Project[] projectArray = new Project[size];
								Project[] projectArray2 = new Project[size];
								Person[] architectArray = new Person[size];
								Person[] contractorArray = new Person[size];
								Person[] customerArray = new Person[size];
								
								for (int i = 0; i < size; i++) {
									System.out.println("\nEnter the details for project # " + (i + 1) + ":\n");
									
									//Prompt the user to enter all the details of the project.
									System.out.print("What is the name of project " + (i + 1) + "? ");
									projectName = input.nextLine();
									System.out.print("What is the number of project " + (i + 1) + "? ");
									projectNumber = input.nextInt();
									input.nextLine();
									System.out.print("What type of building is being designed for project " + (i + 1) +"? ");
									buildingType = input.nextLine();
									
									while (true) { //Input validation while loop to ensure that the user enters only "House", "Apartment block", or "Store" as a valid building type.
										if (buildingType.equalsIgnoreCase("House") || buildingType.equalsIgnoreCase("Apartment") || buildingType.equalsIgnoreCase("Store")) {
											break;
										}
										System.out.println("\nERROR!!!...The building type you've just entered is invalid...");
										System.out.print("Please enter either 'House', 'Apartment', or 'Store': ");
										buildingType = input.nextLine();
										System.out.println();
									}
								
									System.out.print("Enter the physical address for project " + (i + 1) + ": ");
									physicalAddress = input.nextLine();
									System.out.print("What is the EFR number for project " + (i + 1) +"? ");
									EFR_Number = input.nextInt();
									input.nextLine();
									System.out.print("What is the total amount being charged for project " + (i + 1) + "? ");
									totalCharged = input.nextDouble();
									input.nextLine();
									System.out.print("What is the total amount paid up to date for project " + (i + 1) + "? ");
									totalPaid = input.nextDouble();
									input.nextLine();
									System.out.print("What is the deadline for project " + (i + 1) + "? ");
									dueDate = input.nextLine();
									
									while (validDate(dueDate) == false) {
										dueDate = input.nextLine();									
										//Input validation while loop to ensure that the user enters a date that corresponds to the required date format.
										while (dueDate.matches("[0-9]{2}[-]{1}[0-9]{2}[-]{1}[0-9]{4}") == false) {
											System.out.println("\nERROR!!!...The characters of the date you've just entered are invalid...");
											System.out.print("Please enter characters that corresponds with the required date format: ");
											dueDate = input.nextLine();		
										}
									}
										
									//Creating an array of objects for the Project class to reference the details about the project.
									projectArray[i] = new Project(projectName, projectNumber, buildingType, physicalAddress, EFR_Number, totalCharged, totalPaid, dueDate);
									projectList = Arrays.asList(projectArray); //Convert the projectArray into an ArrayList of project objects.		
									
									System.out.println("\nEnter the details of the architect # " + (i + 1) +":\n");
									
									//Prompt the user to enter all the details of the architect.
									System.out.print("What is the name of architect " +  (i + 1) + "? ");
									String name1 = input.nextLine();
									System.out.print("What is architect " +  (i + 1) + "'s surname? ");
									String surname1 = input.nextLine();
									System.out.print("What is architect " +  (i + 1) + "'s telephone number? ");
									String telephoneNumber1 = input.nextLine();
									
									for (int j = 0; j < telephoneNumber1.length(); j++) {
										while (true) { //Input validation while loop to ensure that the user enters only 10 digits for the architect's telephone number.
											if (telephoneNumber1.length() == 10 && Character.isDigit(telephoneNumber1.charAt(j))) {
												break;
											}
											System.out.println("\nERROR!!!...There should be 10 digits entered in order for the phone number to be in a correct format...");
											System.out.print("Please enter the architect's phone number again and make sure that it's 10 digits long: ");
											telephoneNumber1 = input.nextLine();
											System.out.println();
										}
									}
									
									System.out.print("What is architect " +  (i + 1)  + "'s email address? ");
									String emailAddress1 = input.nextLine();
									System.out.print("What is architect " +  (i + 1) + "'s physical address? ");
									String physicalAddress1 = input.nextLine();
									
									//Creating an array objects for the Person class to reference the details about the architect.
									architectArray[i] = new Person(name1, surname1, telephoneNumber1, emailAddress1, physicalAddress1);
									architectList = Arrays.asList(architectArray); //Convert the architectArray into an ArrayList of architect objects.	
									
									System.out.println("\nEnter the details of contractor # " +  (i + 1) + ":\n");
									
									//Prompt the user to enter all the details of the contractor.
									System.out.print("What is the name of contractor " +  (i + 1) + "? ");
									String name2 = input.nextLine();
									System.out.print("What is contractor " +  (i + 1) + "'s surname? ");
									String surname2 = input.nextLine();
									System.out.print("What is contractor " +  (i + 1) + "'s telephone number? ");
									String telephoneNumber2 = input.nextLine();

									for (int j = 0; j < telephoneNumber2.length(); j++) {
										while (true) { //Input validation while loop to ensure that the user enters only 10 digits for the contractor's telephone number.
											if (telephoneNumber2.length() == 10 && Character.isDigit(telephoneNumber2.charAt(j))) {
												break;
											}
											System.out.println("\nERROR!!!...There should be 10 digits entered in order for the cellphone number to be in a correct format...");
											System.out.print("Please enter the contractor's cellphone number again and make sure that it is 10 digits long: ");
											telephoneNumber2 = input.nextLine();
											System.out.println();
										}
									}									
									
									System.out.print("What is contractor " +  (i + 1) + "'s email address? ");
									String emailAddress2 = input.nextLine();
									System.out.print("What is contractor " +  (i + 1) + "'s physical address? ");
									String physicalAddress2 = input.nextLine();
									
									//Creating an array of objects for the Person class to reference the details about the contractor.
									contractorArray[i] = new Person(name2, surname2, telephoneNumber2, emailAddress2, physicalAddress2);
									contractorList = Arrays.asList(contractorArray);  //Convert the contractorArray into an Arraylist of contractor objects.		
									
									System.out.println("\nEnter the details of customer # " +  (i + 1) + ":\n");
									
									//Prompt the user to enter all the details of the customer.
									System.out.print("What is the name of customer " +  (i + 1) + "? ");
									name3 = input.nextLine();
									System.out.print("What is customer " +  (i + 1) + "'s surname? ");
									surname3 = input.nextLine();
									System.out.print("What is customer " +  (i + 1) + "'s telephone number? ");
									telephoneNumber3 = input.nextLine();
									
									for (int j = 0; j < telephoneNumber3.length(); j++) {
										while (true) { //Input validation while loop to ensure that the user enters only 10 digits for the customer's telephone number.
											if (telephoneNumber3.length() == 10 && Character.isDigit(telephoneNumber3.charAt(j))) {
												break;
											}
											System.out.println("\nERROR!!!...The should be 10 digits entered in order for the cellphone number to be in a correct format...");
											System.out.print("Please enter the customer's cellphone number again and make sure that it is 10 digits long: ");
											telephoneNumber3 = input.nextLine();
											System.out.println();
										}
									}
									
									System.out.print("What is customer " +  (i + 1) + "'s email address? ");
									emailAddress3 = input.nextLine();
									System.out.print("What is customer " +  (i + 1) + "'s physical address? ");
									physicalAddress3 = input.nextLine();
									
									//Creating an array of objects for the Person class to reference the details of the customer.
									customerArray[i] = new Person(name3, surname3, telephoneNumber3, emailAddress3, physicalAddress3);									
									customerList = Arrays.asList(customerArray); //Convert the customerArray into an ArrayList of customer objects.	
									
									if (projectName.contains("")) { //If the project name is not provided.
										
										//Replace the instance of the empty("") string with the building type followed by the customer's surname.
										String newProjectName = projectName.replaceAll("", projectArray[i].getTypeOfBuilding() + " " + customerArray[i].getSurname()); 
										
										//Creating an array of objects for the Project class to reference the project names renamed using the building type followed by the customer's surname.
										projectArray2[i] = new Project(newProjectName, projectNumber, buildingType, physicalAddress, EFR_Number, totalCharged, totalPaid, dueDate);
										projectList2 = Arrays.asList(projectArray2); //Convert the project2Array into an ArrayList of project2 objects.		
									} 
								}
							
							//Call the projectDetails function while passing the f1 object along with all the other necessary arguments.
							projectDetails(fw, projectList, projectList2, architectList, contractorList, customerList,
									projectName, projectArray, projectArray2, architectArray, contractorArray,
									customerArray, size);			
							break;
							
							case 2:
								li1 = projectList.listIterator();
								li2 = architectList.listIterator();
								li3 = contractorList.listIterator();
								li4 = customerList.listIterator();
								
								ArrayList<String> myArrayList = readFromFile("MainProjects.txt"); //Call the ArrayList function while passing the file name as argument.
										
								for (String str : myArrayList) { //Display all the inserted projects while reading a list of already existing projects from the text file.
									System.out.println(str);
								}
								System.out.println("                                       POISED STRUCTURAL ENGINEERING MAIN");
								System.out.println("                                       ----------------------------------");
								System.out.println("\nNEW PROJECTS HAVE BEEN ADDED SUCCESSFULLY...!!!");
								System.out.println("Date And Time: " + deadLine);
								System.out.println("----------------------------------------------------------------------------------------------------------------");
								
								//Display all the inserted projects along with their respective architects, contractors, and customers on the output screen by using the listIterator.
								for (int j = 0; j < projectList.size(); j++) {							
									while (li1.hasNext()) {
										System.out.println("PROJECT MANAGEMENT SYSTEM # " + (j + 1) +":");
										System.out.println("----------------------------------------------------------------------------------------------------------------");
										Project p = (Project) li1.next();
										System.out.println("PROJECT DETAILS # " + (j + 1) + ":");
										System.out.println("--------------------");
										System.out.println(p); 
										System.out.println("----------------------------------------------------------------------------------------------------------------");
										Person p1 = (Person) li2.next();										
										System.out.println("ARCHITECT DETAILS # " + (j + 1) + ":");
										System.out.println("----------------------");
										System.out.println(p1);
										System.out.println("----------------------------------------------------------------------------------------------------------------");									
										Person p2 = (Person) li3.next();										
										System.out.println("CONTRACTOR DETAILS # " + (j + 1) + ":");
										System.out.println("-----------------------");
										System.out.println(p2);
										System.out.println("----------------------------------------------------------------------------------------------------------------");								
										Person p3 = (Person) li4.next();
										System.out.println("CUSTOMER DETAILS # " + (j + 1) + ":");
										System.out.println("---------------------");
										System.out.println(p3);
										System.out.println("----------------------------------------------------------------------------------------------------------------");
										j++;
									}	
									
									//Display this message if the projects are successfully displayed.
									System.out.println("Projects Displayed Successfully...!!!");
								}
								System.out.println("----------------------------------------------------------------------------------------------------------------");
							break;
							
							case 3:
								if (file.canRead()) { //If the file exists, execute the blocks of code below.
									boolean found = false;
									
									System.out.print("\nEnter the Project Number to SEARCH: ");
									projNumber = input.nextInt(); //This project number will be compared to the project number entered by the user initially.
									
									System.out.println("----------------------------------------------------------------------------------------------------------------");
									li1 = projectList.listIterator();
									li2 = architectList.listIterator();
									li3 = contractorList.listIterator();
									li4 = customerList.listIterator();
									
									while (li1.hasNext()) {
										Project p = (Project) li1.next();
										if (p.getProjectNumber() == projNumber) { //Search for the project using the project number entered by the user.
											System.out.println("PROJECT MANAGEMENT SYSTEM:");
											System.out.println("----------------------------------------------------------------------------------------------------------------");
											System.out.println("PROJECT DETAILS:");
											System.out.println("----------------");
											System.out.println(p);
											System.out.println("----------------------------------------------------------------------------------------------------------------");
											Person p1 = (Person) li2.next();
											System.out.println("ARCHITECT DETAILS:");
											System.out.println("------------------");
											System.out.println(p1);
											System.out.println("----------------------------------------------------------------------------------------------------------------");
											Person p2 = (Person) li3.next();
											System.out.println("CONTRACTOR DETAILS:");
											System.out.println("-------------------");
											System.out.println(p2);
											System.out.println("----------------------------------------------------------------------------------------------------------------");
											Person p3 = (Person) li4.next();
											System.out.println("CUSTOMER DETAILS:");
											System.out.println("-----------------");
											System.out.println(p3);
											
											found = true;
										}
									}
									if (!found) { //Display this message if the record cannot be found.
										System.out.println("Record Not Found...");
									}
									System.out.println("----------------------------------------------------------------------------------------------------------------");
								}else { //Display this message if the file does not exit.
									System.out.println("File Not Found...");
								}
								
							break;
							
							case 4:
								if (file.canRead()) { //If the file is readable, execute the blocks of code below.
									boolean found = false;
									
									System.out.print("\nEnter the Project Number to UPDATE: ");
									projNumber = input.nextInt(); //This project number will be compared to the project number entered by the user initially.
									input.nextLine();
									li1 = projectList.listIterator();
									
									while (li1.hasNext()) {
										Project p = (Project) li1.next();
										if (p.getProjectNumber() == projNumber) { //If the project number entered to update is equivalent the project number entered by the user initially, proceed by updating all the project information.
											System.out.print("\nEnter the new Project Name: ");
											projectName = input.nextLine();
											System.out.print("Enter the new Project Number: ");
											projectNumber = input.nextInt();
											input.nextLine();
											System.out.print("Enter the new Building Type: ");
											buildingType = input.nextLine();
											
											while (true) { //Input validation while loop to ensure that the user enters either "House", "Apartment block", or "Store" as a valid building type.
												if (buildingType.equalsIgnoreCase("House") || buildingType.equalsIgnoreCase("Apartment") || buildingType.equalsIgnoreCase("Store")) {
													break;
												}
												System.out.println("\nERROR!!!...The building type you've just entered is invalid...");
												System.out.print("Please enter either 'House', 'Apartment', or 'Store': ");
												buildingType = input.nextLine();
												System.out.println();
											}
										
											System.out.print("Enter the new Physical Address: ");
											physicalAddress = input.nextLine();
											System.out.print("Enter the new EFR Number: ");
											EFR_Number = input.nextInt();
											input.nextLine();
											System.out.print("Enter the new Total Amount Charged: ");
											totalCharged = input.nextDouble();
											input.nextLine();
											System.out.print("Enter the new Total Amount Paid up to date: ");
											totalPaid = input.nextDouble();
											input.nextLine();
											System.out.print("Enter the new Deadline for the project: ");
											dueDate = input.nextLine();
											
											while (validDate(dueDate) == false) {
												dueDate = input.nextLine();
												//Input validation while loop to ensure that the user enters a date that corresponds to the required date format.
												while (dueDate.matches("[0-9]{2}[-]{1}[0-9]{2}[-]{1}[0-9]{4}") == false) {
													System.out.println("\nERROR!!!...The characters of the date you've just entered are invalid...");
													System.out.print("Please enter characters that corresponds with the required date format: ");
													dueDate = input.nextLine();		
												}
											}
											
											li1.set(new Project(projectName, projectNumber, buildingType, physicalAddress, EFR_Number, totalCharged, totalPaid, dueDate));
											
											//Call this the updateToFile function while passing as argument all the updated information that is to be written to the text file.
											updateToFile(p, projNumber, projectName, projectNumber, buildingType, 
													physicalAddress3, EFR_Number, totalCharged, totalPaid, dueDate, deadLine);
											
											found = true;			
										}
										System.out.println("----------------------------------------------------------------------------------------------------------------");
										if (found) { //Display this message if the record got updated successfully.
											System.out.println("Record Updated Successfully...!!!");
										}else if(!found) { //Display this message if the record cannot be found.
											System.out.println("Record Not Found...");
										}
										System.out.println("----------------------------------------------------------------------------------------------------------------");
									}
								}else { //Display this message if the file cannot be found.
									System.out.println("File Not Found...");
								}				
							break;
							
							case 5:
								if (file.canRead()) {
									boolean found = false;
									String answer = "";
									
									System.out.print("\nEnter the project number to FINALISE: ");
									projNumber = input.nextInt(); //This project number will be compared to the project number entered by the user initially.
									input.nextLine();

									li1 = projectList.listIterator();
									li2 = architectList.listIterator();
									li3 = contractorList.listIterator();
									li4 = customerList.listIterator();
									
									while (li1.hasNext()) {
										Project p = (Project)li1.next();										
										if (p.getProjectNumber() == projNumber) {
											System.out.print("\nAre you sure this is the project you want to finalise? ");
											answer = input.nextLine(); //Prompt the user for the certainty of their project finalization.
											
											while (answer.equalsIgnoreCase("Yes") == false) {
												System.out.println("\nERROR!!!...Your response to the question is not valid...");
												System.out.print("Please enter 'Yes' to signify that you want this project finalised: ");
												answer = input.nextLine(); //Ensure that the user enters only 'Yes' in order to be able to proceed with the project  
												}	
											
											if (dueDate.compareTo(deadLine) < 0) {
												System.out.println("----------------------------------------------------------------------------------------------------------------");
												System.out.println("PROJECT MANAGEMENT SYSTEM:");
												System.out.println("----------------------------------------------------------------------------------------------------------------");
												System.out.println("PROJECT DETAILS:");
												System.out.println("----------------");
												System.out.println(p);
												System.out.println("----------------------------------------------------------------------------------------------------------------");
												Person p1 = (Person) li2.next();
												System.out.println("ARCHITECT DETAILS:");
												System.out.println("------------------");
												System.out.println(p1);
												System.out.println("----------------------------------------------------------------------------------------------------------------");
												Person p2 = (Person) li3.next();
												System.out.println("CONTRACTOR DETAILS:");
												System.out.println("-------------------");
												System.out.println(p2);
												System.out.println("----------------------------------------------------------------------------------------------------------------");
												Person p3 = (Person) li4.next();
												System.out.println("CUSTOMER DETAILS:");
												System.out.println("-----------------");
												System.out.println(p3);	
												System.out.println("----------------------------------------------------------------------------------------------------------------");
												System.out.println("CUSTOMER INVOICE:");
												System.out.println("-----------------");
												
												//This function is called to display the invoice of the amount owed by every customer who has paid less than the total price of the House, Apartment Block, or Store.
												customerInvoicefunction(totalCharged, totalPaid, physicalAddress3, dueDate,
														telephoneNumber3, emailAddress3);
												
												Person customer = new Person(name3, surname3, telephoneNumber3, emailAddress3, physicalAddress3);
												Project projectX = new Project(projectName, projectNumber, buildingType, physicalAddress, EFR_Number, totalCharged, totalPaid, dueDate);;
												
												if (projectName.contains("")) { //If the project name is not provided.
													
													//Replace the instance of the empty("") string with the building type followed by the customer's surname.
													String newProjectName = projectName.replaceAll("", projectX.getTypeOfBuilding() + " " + customer.getSurname());                                           
													projectX = new Project(newProjectName, projectNumber, buildingType, physicalAddress3, EFR_Number, totalCharged, totalPaid, dueDate);
												}
												finalisedProjects(fw2, deadLine, projectX, projectName , p, p1, p2, p3);
												
											}else if(dueDate.compareTo(deadLine) > 0) { //Display this message if the record is older than the current date
												System.out.println("----------------------------------------------------------------------------------------------------------------");
												System.out.println("Record Past Due Date..."); 
												System.out.println("----------------------------------------------------------------------------------------------------------------");
												break;
											}
											found = true;
										}
										System.out.println("----------------------------------------------------------------------------------------------------------------");
										if (found) { //Display this message if the record is found and finalised successfully.
											System.out.println("RECORD FINALISED SUCCESSFULLY...!!!");
											System.out.println("Date and time: " + deadLine);
										}else if(found == false){ 
											System.out.println("Record Not Found..."); //Display this message if the record cannot be found.
										}				
										System.out.println("----------------------------------------------------------------------------------------------------------------");
										
									}
								}else { //Display this message if the file cannot be found.
									System.out.println("File Not Found...");
								}
							break;
							
							case 6:
								li1 = projectList.listIterator();
								li2 = architectList.listIterator();
								li3 = contractorList.listIterator();
								li4 = customerList.listIterator();
								
								System.out.println("                                       POISED STRUCTURAL ENGINEERING MAIN");
								System.out.println("                                       ----------------------------------");
								System.out.println("\nNEW PROJECTS THAT ARE PAST THE DUE DATE HAVE BEEN ADDED SUCCESSFULLY...!!!");
								System.out.println("Date And Time: " + deadLine);
								System.out.println("----------------------------------------------------------------------------------------------------------------");
								//Display all the inserted projects along with their respective architects, contractors, and customers on the output screen by using the listIterator.
								for (int j = 0; j < projectList.size(); j++) {							
									while (li1.hasNext()) {
										if (dueDate.compareTo(deadLine) > 0) {
											System.out.println("PROJECT MANAGEMENT SYSTEM # " + (j + 1) +":");
											System.out.println("----------------------------------------------------------------------------------------------------------------");
											Project p = (Project) li1.next();
											System.out.println("PROJECT DETAILS # " + (j + 1) + ":");
											System.out.println("--------------------");
											System.out.println(p); 
											System.out.println("----------------------------------------------------------------------------------------------------------------");
											Person p1 = (Person) li2.next();										
											System.out.println("ARCHITECT DETAILS # " + (j + 1) + ":");
											System.out.println("----------------------");
											System.out.println(p1);
											System.out.println("----------------------------------------------------------------------------------------------------------------");									
											Person p2 = (Person) li3.next();										
											System.out.println("CONTRACTOR DETAILS # " + (j + 1) + ":");
											System.out.println("-----------------------");
											System.out.println(p2);
											System.out.println("----------------------------------------------------------------------------------------------------------------");								
											Person p3 = (Person) li4.next();
											System.out.println("CUSTOMER DETAILS # " + (j + 1) + ":");
											System.out.println("---------------------");
											System.out.println(p3);
											System.out.println("----------------------------------------------------------------------------------------------------------------");
											j++;
										}	
									}	
								}
								System.out.println("Records Past Due Date Displayed Successfully...!!!");
								System.out.println("----------------------------------------------------------------------------------------------------------------");
							break;
								
							case 7: //Provides the only point of exit from the program.
								System.out.println("--------------------------------------YOU'VE JUST EXITED THE PROGRAM...!!!--------------------------------------");
								System.exit(0);
						}
					} while (Option != 7);					

					again = false;
					
				} catch (InputMismatchException e) { //Display an error message if the user enters a value that does not correspond to the required data type.
					System.out.println("\nAN ERROR OCCURED!!!...The input you've just entered does");
					System.out.println("not satisfy/validate the variable type declared...");
					input.next();
				}
			}
			
		} catch (FileNotFoundException e) { //Display an error message if the file is not accessible.
			System.out.println("ERROR!!!...File not found...");
		}
	}
	
	/**
	 * Public method that validates the date entered by the user to ensure
	 * that it is in a correct format.
	 * 
	 * @param date string date entered by the user
	 * @return returning a boolean of true if the date is entered in a correct 
	 *  format or false if the date date is not entered in a correct format.
	 */
	public static boolean validDate(String date) {	
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy"); //Date format that is to be used.
	
		try {
			LocalDate newDate = LocalDate.parse(date, dtf);
			System.out.println("\nDUE DATE: " + newDate); //Display the valid date on screen.
			return true;
			
		} catch (DateTimeParseException e) { //Display this error message if the date entered by the user cannot be parsed.
			System.out.println("\nAN ERROR OCCURED!!!...The date you've just entered is Unparseable...");
			System.out.println("The date needs to be entered in a correct format for it to be parseable...");
			return false;	
		}
				
	}
	
	/**
	 * Public method that writes all the project details to the MainProject.txt text file.
	 * 
	 * @param fw fileWriter class
	 * @param projectList first set of projects stored in an ArrayList 
	 * @param projectList2 second set of projects stored in an ArrayList
	 * @param architectList collection of architects stored in an ArrayList
	 * @param contractorList collection of contractors stored in an ArrayList
	 * @param customerList collection of customers stored in an ArrayList
	 * @param projectName the name of the project
	 * @param projectArray array of Project type containing project details
	 * @param projectArray2 array of Project type with the project names renamed
	 * @param architectArray array of Person type containing architects' details
	 * @param contractorArray array of Person type containing contractors' details
	 * @param customerArray array of Person type containing customers' details
	 * @param size number of projects requested by the user
	 * @throws IOException general class of exceptions produced
	 *  by failed/interrupted I/O operation
	 *  
	 *    
	 */
	public static void projectDetails(FileWriter fw, List<Project> projectList, List<Project> projectList2,
			List<Person> architectList, List<Person> contractorList, List<Person> customerList, String projectName,
			Project[] projectArray, Project[] projectArray2, Person[] architectArray, Person[] contractorArray,
			Person[] customerArray, int size) throws IOException {	
		
		//Write to the MainProjects.txt text file in append mode.
		fw.write("                                          POISED STRUCTURAL ENGINEERING\r\n");
		fw.write("                                          -----------------------------\r\n");
		fw.write("\r\nNEW PROJECTS HAVE BEEN ADDED SUCCESSFULLY...!!!\r\n");
		fw.write("Number of projects: " + size + "\r\n");
		fw.write("----------------------------------------------------------------------------------------------------------------\r\n");
		for (int j = 0; j < projectArray.length; j++) {
			fw.write("PROJECT MANAGEMENT SYSTEM # " + (j + 1) + ":\r\n");
			fw.write("----------------------------------------------------------------------------------------------------------------\r\n");
			fw.write("PROJECT DETAILS # " + (j + 1) + ":\r\n");
			fw.write("--------------------\r\n");
			if (projectName.contains("") == false) { //Write to the file all the projects that do not have the empty("") string which signifies that a project name is not provided.
				for (Project p : projectList) {
					if (p == projectArray[j]) {
						fw.write(p + "\r\n");
					}	
				}
			}else if(projectName.contains("") == true) { //Name the project using the building type + surname of the customer if the project name is not provided when the information is captured.
				for (Project p2 : projectList2) {
					if (p2 == projectArray2[j]) {
						fw.write(p2 + "\r\n");
					}
				}
			}
			
			//Write to the file all the information captured about the architects.
			fw.write("----------------------------------------------------------------------------------------------------------------\r\n");
			fw.write("ARCHITECT DETAILS # " + (j + 1) + ":\r\n");
			fw.write("----------------------\r\n");
			for (Person a : architectList) {
				if (a == architectArray[j]) {
					fw.write(a + "\r\n");
				}
			}
			
			//Write to the file all the information captured about the contractors.
			fw.write("----------------------------------------------------------------------------------------------------------------\r\n");
			fw.write("CONTRACTOR DETAILS # " + (j + 1) + ":\r\n");
			fw.write("-----------------------\r\n");
			for (Person c : contractorList) {
				if (c == contractorArray[j]) {
					fw.write(c + "\r\n");
				}
			}
			
			//Write to the file all the information captured about the customers.
			fw.write("----------------------------------------------------------------------------------------------------------------\r\n");
			fw.write("CUSTOMER DETAILS # " + (j + 1) + ":\r\n");
			fw.write("---------------------\r\n");
			for (Person c : customerList) {
				if (c == customerArray[j]) {
					fw.write(c + "\r\n");
				}
			}
			fw.write("----------------------------------------------------------------------------------------------------------------\r\n");
		}
		fw.close(); //Close the file when finished.	
	}
	
	/**
	 * Public method that writes all the project details to the MainProject.txt text file.
	 * 
	 * @param fileName name of the file
	 * @return returning an ArrayList of string values to be read from the file.
	 */
	public static ArrayList<String> readFromFile(String fileName) {
		ArrayList<String> myArrayList = new ArrayList<String>();
		try {
			Scanner sc = new Scanner(new File(fileName)); //Create a File instance to reference a text file in Java.
			
			while (sc.hasNextLine()) { //Read every line of text add to the ArrayList.
				myArrayList.add(sc.nextLine());
			}
			sc.close(); //Close the file when finished.
		} catch (Exception e) {
			System.out.println("Error Openning File...!!!");
		}
		return myArrayList;
	}
	
	/**
	 * Public method that declare an initialize all variables that will be 
	 * used to make the invoice generated for each customer realistic.
	 * 
	 * @param totalCharged total amount charged
	 * @param totalPaid total amount paid
	 * @param physicalAddress3 physical address of the customer.
	 * @param dueDate deadline of the project
	 * @param telephoneNumber3 telephone number of the customer.
	 * @param emailAddress3 email address of the customer.
	 * 
	 * 
	 */	
	public static void customerInvoicefunction(double totalCharged, double totalPaid, String physicalAddress3,
			String dueDate, String telephoneNumber3, String emailAddress3) {
		if (totalCharged > totalPaid) { //This will calculate the total amount that the customer must still pay if the total payed is less than the total charged.
			
			//The DecimalFormat class will be used to convert the values entered by the user into correct currency figures.
			DecimalFormat df = new DecimalFormat("$0,000.00");
			
			String invoiceDate = "24/02/2021";
			String invoiceNumber = "I60000201228421";
			String paymentMethod = "Stop Order";
			String billingAccount = "BA1149983721";
			
			//Call the customerInvoice function.
			customerInvoice(totalCharged, totalPaid, dueDate, telephoneNumber3, emailAddress3, physicalAddress3,
					df, invoiceDate, invoiceNumber, paymentMethod, billingAccount);
		}
	}
	
	/**
	 * Public method that that calculates the amount that the customer must 
	 * still pay and prints the invoice to the output screen.
	 * 
	 * @param totalCharged total amount charged
	 * @param totalPaid total amount paid
	 * @param dueDate deadline of the project
	 * @param telephoneNumber3 telephone number of the customer
	 * @param emailAddress3 email address of the customer
	 * @param physicalAddress3 physical address of the customer
	 * @param df decimal format
	 * @param invoiceDate invoice date
	 * @param invoiceNumber invoice number
	 * @param paymentMethod payment method
	 * @param billingAccount billing account
	 * 
	 * 
	 */	
	public static void customerInvoice(double totalCharged, double totalPaid, String dueDate, String telephoneNumber3,
			String emailAddress3, String physicalAddress3, DecimalFormat df, String invoiceDate, String invoiceNumber,
			String paymentMethod, String billingAccount) {
		double balance = totalCharged - totalPaid; //Work out the amount due by subtracting the total amount paid from the total amount charged.
		
		//Display on screen the invoice of the customer.
		System.out.println("                                        Email Address          " + emailAddress3);
		System.out.println("                                        Telephone Number       " + telephoneNumber3);
		System.out.println("                                        Physical Address       " + physicalAddress3);
		System.out.println("                                        Invoice Date           " + invoiceDate);	
		System.out.println("                                        Invoice Number         " + invoiceNumber);
		System.out.println("                                        Billing Account        " + billingAccount);
		System.out.println("                                        Payment Method         " + paymentMethod);
		System.out.println("                                        Due Date               " + dueDate);
		System.out.println("\n-----------------------------------------------------------------------");
		System.out.println("Date                Description                         Charge		    ");
		System.out.println("-----------------------------------------------------------------------");
		System.out.println("02/02/2021          BALANCE BROUGHT FORWARD             " + df.format(totalCharged));
		System.out.println("17/02/2021          PAYMENT - THANK YOU                " + "-" + df.format(totalPaid));
		System.out.println("-----------------------------------------------------------------------");
		System.out.println("                    AMOUNT DUE:                         " + df.format(balance));
		System.out.println("-----------------------------------------------------------------------");
	}
	
	/**
	 * Public method that writes the updated projects.
	 * 
	 * @param projectX updated project
	 * @param deadLine today's date
	 */	
	public static void writeToFile(Project projectX, String deadLine) {
		try {
			//Create a FileWriter Class that writes all the updated details in append mode.
			FileWriter fw2 = new FileWriter("UpdatedProjects.txt",true); //The updated project will be written to this text file.
			
			//Append to the text file a list of recently updated project information to the UpdatedProjects.txt text file. 	
			fw2.write("                                    POISED STRUCTURAL ENGINEERING UPDATE\r\n");
			fw2.write("                                    ------------------------------------\r\n");
			fw2.write("A NEW PROJECT HAS BEEN UPDATED SUCCESSFULLY...!!!\r\n");
			fw2.write("Date And Time: " + deadLine + "\r\n"); //Provide the date and the time at which the project has been finalized.
			fw2.write("----------------------------------------------------------------------------------------------------------------\r\n");
			fw2.write("PROJECT UPDATED USING THE SELECTED PROJECT NUMBER:\r\n");
			fw2.write("----------------------------------------------------------------------------------------------------------------\r\n");
			fw2.write("PROJECT DETAILS:\r\n");
			fw2.write("----------------\r\n");
			fw2.write(projectX + "\r\n");
			fw2.write("----------------------------------------------------------------------------------------------------------------\r\n");
			fw2.close(); //Close the file when finished.
		} catch (IOException e) { //This IOException will display on screen the text below if there is a problem while writing to the text file.
			System.out.println("ERROR WRITTING TO FILE...!!!");
		}
	}
	
	/**
	 * Public method that updates a project using the project number.
	 * 
	 * @param projectX updated project
	 * @param projNumber project number entered by the user
	 * @param projectName updated project name
	 * @param projectNumber updated project number
	 * @param buildingType updated building type
	 * @param physicalAddress updated physical address
	 * @param EFR_Number updated EFR number
	 * @param totalCharged updated total amount charged
	 * @param totalPaid updated total amount paid
	 * @param dueDate updated due date for the project
	 * @param deadLine today's date
	 * 
	 * 
	 */	
	public static void updateToFile(Project projectX, int projNumber, String projectName, int projectNumber, String buildingType,
			String physicalAddress, int EFR_Number, double totalCharged, double totalPaid, String dueDate, String deadLine) {
		
		if (projectX.getProjectNumber() == projNumber) { //If the project number inputed by the user initially is equivalent to the number used to search, proceed by executing the blocks of code below.
			projectX = new Project(projectName, projectNumber, buildingType, physicalAddress, EFR_Number, totalCharged, totalPaid, dueDate); //Create a new object of the Project class using the new details entered by the user.                      
			writeToFile(projectX, deadLine); //Call the writeToFile function while passing the newly created projectX object as argument to write the new information to the CompletedProject2 text file.
		}	
	}	
	
	/**
	 * Public method that writes all the finalized details about 
	 * the project to the CompleteProject.txt text file.
	 * 
	 * @param fw3 third FileWriter object for this program
	 * @param deadLine today's date
	 * @param projectX project name using building type + surname of the customer
	 * @param projectName project name entered by the user
	 * @param p iterator object to iterate through the project details
	 * @param p1 iterator object to iterate through the architect's details
	 * @param p2 iterator object to iterate through the constructor's details
	 * @param p3 iterator object to iterate through the customer's details
	 * @throws IOException general class of exceptions produced
	 *  by failed/interrupted I/O operation
	 *  
	 *  
	 */
	
	public static void finalisedProjects(FileWriter fw3, String deadLine, Project projectX, String projectName, Project p, Person p1,
			Person p2, Person p3)throws IOException {
		
		//Create ArrayLists for the finalized projects, architects, constructors, and customers.
		
		ArrayList<Project> finalProjects = new ArrayList<Project>();
		ArrayList<Person> finalArchitects = new ArrayList<Person>();
		ArrayList<Person> finalContractors = new ArrayList<Person>();
		ArrayList<Person> finalCustomers = new ArrayList<Person>();
		
		//Add objects to each ArrayList created.
		
		finalProjects.add(p);
		finalArchitects.add(p1);
		finalContractors.add(p2);
		finalCustomers.add(p3);	
		
		//Write to the CompletedProjects.txt text file in append mode.
		fw3.write("                                  POISED STRUCTURAL ENGINEERING FINALIZATION\r\n");
		fw3.write("                                  ------------------------------------------\r\n");
		fw3.write("\r\nA NEW PROJECT HAS BEEN FINALISED SUCCESSFULLY...!!!\r\n");
		fw3.write("Date and time: " + deadLine + "\r\n"); //Provide the date and the time at which the project has been finalized.
		fw3.write("----------------------------------------------------------------------------------------------------------------\r\n");
		fw3.write("PROJECT MANAGEMENT SYSTEM:\r\n");
		fw3.write("----------------------------------------------------------------------------------------------------------------\r\n");
		fw3.write("PROJECT DETAILS:\r\n"); //Append to the file the final information captured about the completed project details.
		fw3.write("----------------\r\n");
		if (projectName.equalsIgnoreCase("") == false) { //Write to the file the project name that is not the empty("") string which signifies that a project name is not provided.
			for (Project f1 : finalProjects) {
				fw3.write(f1 + "\r\n");
			}
		}else{ //Name the project using the building type + surname of the customer if the project name is not provided when the information is captured.
			fw3.write(projectX + "\r\n");
		}
	
		//Append to the file the final information captured about the architect's details.
		fw3.write("----------------------------------------------------------------------------------------------------------------\r\n");
		fw3.write("ARCHITECT DETAILS:\r\n");
		fw3.write("------------------\r\n");
		for (Person f2 : finalArchitects) {
			fw3.write(f2 + "\r\n");
		}
		
		//Append to the file the final information captured about the contractor's details.
		fw3.write("----------------------------------------------------------------------------------------------------------------\r\n");
		fw3.write("CONTRACTOR DETAILS:\r\n");
		fw3.write("-------------------\r\n");
		for (Person f3 : finalContractors) {
			fw3.write(f3 + "\r\n");
		}
		
		//Append to the file the final information captured about the customer's details.
		fw3.write("----------------------------------------------------------------------------------------------------------------\r\n");
		fw3.write("CUSTOMER DETAILS:\r\n");
		fw3.write("-----------------\r\n");
		for (Person f4 : finalCustomers) {
			fw3.write(f4 + "\r\n");
		}
		fw3.write("----------------------------------------------------------------------------------------------------------------\r\n");
		fw3.close(); //Close the file when finished.
	}
}