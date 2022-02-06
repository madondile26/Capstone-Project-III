package task24Package;
import java.text.DecimalFormat;

/**
 * This is a Project class containing all the relevant attributes, constructor, and
 * methods that make a suitable class.
 * 
 * @author Xhasa Madondile
 * @version 1.00, Jan 27-2022
 * 
 * 
 */
public class Project {
	private String projectName;
	private int projectNumber;
	private String typeOfBuilding;
	private String physicalAddress;
	private int EFR_Number;
	private double totalCharged;
	private double totalPaid;
	private String projectDeadline;
	
	/**
	 * Constructor method that reference each of the attributes of the Project class.
	 * 
	 * @param projectName name of the project
	 * @param projectNumber number of the project
	 * @param typeOfBuildingString type of building being designed
	 * @param physicalAddress physical address of the building
	 * @param EFR_Number EFR number
	 * @param totalCharged total amount that is being charged
	 * @param totalPaid total amount that is paid
	 * @param projectDeadline deadline for the project
	 * 
	 * 
	 */
	public Project(String projectName,int projectNumber, String typeOfBuildingString, String physicalAddress,
			int EFR_Number, double totalCharged, double totalPaid, String projectDeadline) {
		this.projectNumber = projectNumber;
		this.projectName = projectName;
		this.typeOfBuilding = typeOfBuildingString;
		this.physicalAddress = physicalAddress;
		this.EFR_Number = EFR_Number;
		this.totalCharged = totalCharged;
		this.totalPaid = totalPaid;
		this.projectDeadline = projectDeadline;
	}
	
	//The methods below are used to obtain the new attributes of each Project object.
	
	/**
	 * 
	 * @param projectName2 second project name
	 * @param projectNumber2 second project number
	 * @param typeOfBuilding2 second type of building
	 * @param physicalAddress2 second physical address
	 * @param EFR_Number2 second EFR number
	 * @param totalCharged2 second total amount charged
	 * @param totalPaid2 second total amount paid
	 * @param projectDeadline2 second project dead line
	 *   
	 * 
	 */
	public Project(String projectName2, String projectNumber2, String typeOfBuilding2, String physicalAddress2,
			String EFR_Number2, String totalCharged2, String totalPaid2, String projectDeadline2) {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Set the projectName attribute so that it can be modified inside the Project 
	 * class.
	 * 
	 * @param projectName name of the project
	 */
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	
	/**
	 * Set the projectNumber attribute so that it can be modified inside the Project 
	 * class.
	 * 
	 * @param projectNumber number of the project
	 */
	public void setProjectNumber(int projectNumber) {
		this.projectNumber = projectNumber;
	}
	
	/**
	 * Set the typeOfBuilding attribute so that it can be modified inside the Project 
	 * class.
	 * 
	 * @param typeOfBuilding type of building being designed
	 */
	public void setTypeOfBuilding(String typeOfBuilding) {
		this.typeOfBuilding = typeOfBuilding;
	}
	
	/**
	 * Set the physicalAddress attribute so that it can be modified inside the Project 
	 * class.
	 * 
	 * @param physicalAddess physical address of the project
	 */
	public void setPhysicalAddress(String physicalAddess) {
		this.physicalAddress = physicalAddess;
	}
	
	/**
	 * Set the EFR_Number attribute so that it can be modified inside the Project 
	 * class.
	 * 
	 * @param EFR_Number EFR number of the project
	 */
	public void setEFR_Number(int EFR_Number) {
		this.EFR_Number = EFR_Number;
	}
	
	/**
	 * Set the totalCharged attribute so that it can be modified inside the Project 
	 * class.
	 * 
	 * @param totalCharged total amount being charged
	 */
	public void setTotalCharged(double totalCharged) {
		this.totalCharged = totalCharged;
	}
	
	/**
	 * Set the totalPaid attribute so that it can be modified inside the Project 
	 * class.
	 * 
	 * @param totalPaid total amount paid
	 */
	public void setTotalpaid(double totalPaid) {
		this.totalPaid = totalPaid;
	}
	
	/**
	 * Set the projectDeadline attribute so that it can be modified inside the Project 
	 * class.
	 * 
	 * @param projectDeadline deadline of the project.
	 */
	public void setProjectDeadline(String projectDeadline) {
		this.projectDeadline = projectDeadline;
	}
	
	/**
	 * Get the projectName attribute so that it can be accessible outside the Project
	 * class.
	 * 
	 * @return returning the String value of projectName
	 */
	public String getProjectName() {
		return projectName;
	}
	
	/**
	 * Get the projectNumber attribute so that it can be accessible outside the Project
	 * class.
	 * 
	 * @return returning the int value of projectNumber
	 */
	public int getProjectNumber() {
		return projectNumber;
	}
	
	/**
	 * Get the typeOfBuilding attribute so that it can be accessible outside the Project
	 * class.
	 * 
	 * @return returning the String value of typeOfBuilding
	 */
	public String getTypeOfBuilding() {
		return typeOfBuilding;	
	}
	
	/**
	 * Get the physicalAddress attribute so that it can be accessible outside the Project
	 * class.
	 * 
	 * @return returning the String value of physicalAddress
	 */
	public String getPhysicalAddress() {
		return physicalAddress;
	}
	
	/**
	 * Get the EFR_Number attribute so that it can be accessible outside the Project
	 * class.
	 * 
	 * @return returning the int value of EFR_Number
	 */	
	public int getEFR_Number() {
		return EFR_Number;
	}
	
	/**
	 * Get the totalCharged so that it can be accessible outside the Project
	 * class.
	 * 
	 * @return returning the double value of totalCharged
	 */
	public double getTotalCharged() {
		return totalCharged;
	}	
	
	/**
	 * Get the totalPaid attribute so that it can be accessible outside the Project
	 * class.
	 * 
	 * @return returning the double value of totalPaid
	 */
	public double getTotalPaid() {
		return totalPaid;
	}
	
	/**
	 * Get the projectDeadline attribute so that it can be accessible outside the Project
	 * class.
	 * 
	 * @return returning the String value of projectDeadline
	 */
	public String getProjectDeadline() {
		return projectDeadline;
	}	
	
	//The DecimalFormat class will be used to convert the totalCharged and the totalPaid attributes to correct currency figures.
	DecimalFormat df = new DecimalFormat("$0,000.00");
	
	/**
	 * Each class is displayed together with its attributes using this toString method.
	 */
	public String toString() {
		String output = "Project Name: " + projectName;
		output += "\nProject Number: " + projectNumber;
		output += "\nBuilding Type: " + typeOfBuilding;
		output += "\nPhysical Address: " + physicalAddress;
		output += "\nEFR Number: " + EFR_Number;
		output += "\nTotal Fee Charged: " + df.format(totalCharged) ;
		output += "\nTotal Amount Paid: " + df.format(totalPaid);
		output += "\nDeadline For The Project: " + projectDeadline;
		
		return output;
	}
}
