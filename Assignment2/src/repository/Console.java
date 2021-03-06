package repository;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

/**
 * Class Console creates a menu for a car repository. 
 * @author Fernando Herrera and Leah Weiland
 */
public class Console {	
  
  /**
   * @ObjectInputStream used to read from a file
   */
  private static ObjectInputStream inFile;

  /**
	 * variable to keep track of how many users there are in the system
	 */
	static int userCounter = 0;

  /**
   * Class Console creates and displays an interactive menu for the user.
   * @param args
   * @throws Exception
   */ 
  public static void main (String[] args) throws Exception {   	 
    
    /**
     * Opens a file for reading.
     */
    openInFile();
    
    /**
     * Reads a file.
     */
    readFile();
    
    /**
     * Closes file.
     */
    closeInFile();

    /**
     * @Scanner reads car data from file "dealership.txt"
     */    
    Scanner in = new Scanner(System.in);
   
    //experiment
    Vehicle vehicle = new Vehicle ();
    
    /**
     * new object of type User
     */
    User user = new User();
    
    /**
     * new object of type Input for employee input
     */
    Input employee = new Input();
        
    /**
     * new object of type Car       
     */
    Car car = new Car ();
    
    /**
     * new object of type Truck
     */
    Truck truck = new Truck ();
    
    /**
     * new object of type Motorcycle
     */
    Motorcycle bike = new Motorcycle ();
        
    /**
     * new object of type Input
     */
    Input input = new Input ();        
        
    /**
     *  integer variable to hold user input
     */
    int choice;
    
    /**
     *  integer variable to hold user vehicle choice
     */
    int option;
        
    do {
      printMenu ();
        
      choice = userChoice (in);
        
      switch (choice) {
        case '1':
          do {
            option = vehicleChoice (in);
            switch (option) {
              case '1': 
                input.addCarRecord(in);
                break;
              case '2':
                input.addTruckRecord (in);
                break;
              case '3':
                input.addBikeRecord (in); 
                break;
              case '4':
                break;
              default : 
                System.out.printf("Not a valid option%n", option);
            }
          } while (option != '4');
          break;
        case '2':  
          input.deleteRecord(in);
          break;
        case '3':  
          car.printCar();
          truck.printTruck();
          bike.printMotorcycle();
          break;
        case '4':  
          do {
            option = vehicleChoice (in);
            switch (option) {
              case '1': 
                input.priceRange(in, option);
                break;
              case '2':
                input.priceRange(in, option);
                break;
              case '3':
                input.priceRange(in, option); 
                break;
              case '4':
                break;
              default : 
                System.out.printf("Not a valid option%n", option);
            }
          } while (option != '4');
          break;
        case '5':  
          do {
            option = addUserChoice(in);
            switch(option){
            case '1':              
              input.addEmployeeUserRecord(in);
              break;
            case '2':              
              input.addCustomerUserRecord(in);
              break;
            case '3':
              break;
            default :
              System.out.printf(" Not a valid option%n", option);
            }       	  
          } while (option != '3');
          break;
//**********************************CASE6  PART 1 START****************************************
        case '6':  
        		
          break;
        case '7':
        		System.out.print("Employees:\n");
        		user.printUserArrayHeader();
        		user.printUserEmployeeArray();
        		System.out.print("\nCustomers:\n");
        		user.printUserCustomerHeader();
        		user.printUserCustomerArray();
          break;
        case '8':
          break;
        case '9':
          vehicle.saveData();
          break;
        default: 
          System.out.printf("Not a valid option%n", choice);
      }
    } while (choice != '9');
  }
    
  /**
   * Prints a menu out to the console.
   */   
  public static void printMenu () {
    System.out.print("\n");
    System.out.print("Choose one of the following:\n" 
                     + "\t1. Add a new vehicle to the database.\n"
                     + "\t2. Delete a vehicle from a database (by VIN)\n"
                     + "\t3. Show all existing vehicles in the database.\n"
                     + "\t4. Show a list of vehicles within a given price range\n"
                     + "\t5. Add a new user to the database.\n"
                     + "\t6. Update user info (by ID)\n" 
                     + "\t7. Show list of users.\n"
                     + "\t8. Sell a vehicle\n"
                     + "\t9. Exit program.\n");
  }

  /**
   * Returns integer value read by Scanner
   * @param in reusing Scanner in object
   * @return user input read by Scanner, an integer value between 1 and 6
   */
  public static int userChoice(Scanner in) {

    System.out.print("Your choice: ");

    char command = in.next().charAt(0);

    return command;
  }
  
  /**
   * Returns integer value read by Scanner
   * @param in reusing Scanner in object
   * @return user input read by Scanner, an integer value between 1 and 4
   */
  public static int vehicleChoice(Scanner in) {
  
    System.out.print("\nChoose Vehicle Type: \n"
                     + "\t1. Car\n"
                     + "\t2. Truck\n"
                     + "\t3. Motorcycle\n"
                     + "\t4. Back to Main Menu.\n"
                     + "Your choice: ");
    
    char selection = in.next().charAt(0);
    
    return selection;
  }
  
  /**
   * Returns integer value read by Scanner
   * @param in reusing Scanner in object
   * @return user input read by Scanner, an integer value between 1 and 3
   */
  public static int addUserChoice(Scanner in){
	  
	  System.out.print("\nChoose a user type to add to the database: \n"
			  		   + "\t1. Employee \n"
			  		   + "\t2. Customer \n"
			  		   + "\t3. Back to Main Menu. \n"
			  		   + "Your choice: ");
	  
	  char selection = in.next().charAt(0);
	 
	  return selection;
  }
  
  /**
   * Returns an integer value read by Scanner
   * @param in reusing Scanner in object
   * @return user input read by Scanner, an integer value between 1 and 3
   */
  public static int updateUser(Scanner in){
    
	  System.out.print("\nSelect which type of user to update: \n"
			           + "\t1. Employee \n"
			  		   + "\t2. Customer \n"
			           + "\t3. Back to main menu. \n"
			  		   + "Your choice: ");
	  
	  char selection = in.next().charAt(0);
	  return selection;
  }
  

  /**
   * This method opens a file "dealership.txt" for reading.
   */
  public static int updateEmployeeMenu(Scanner in){	
			
		    System.out.print("Select a field to update:\n "
		    				+"\t1. ID Number\n "
		    				+"\t2. First Name\n"
		    				+"\t3. Last Name\n"
		    				+"\t4. Salary\n"
		    				+"\t5. Bank Account Number\n"
		    				+"\t6. Return to main menu.\n"
		    				+"Your choice: ");    
		    char selection = in.next().charAt(0);
		    return selection;
  }
  
  public static int updateCustomerMenu(Scanner in){
	    
	    System.out.print("Select a field to update:\n "
	    				+"\t1. ID Number\n "
	    				+"\t2. First Name\n"
	    				+"\t3. Last Name\n"
	    				+"\t4. Drivers License\n"
	    				+"\t5. Phone Number\n"
	    				+"\t6. Return to main menu.\n"
	    				+"Your choice: ");
	    char selection = in.next().charAt(0);
	    return selection;
  }
  

  public static void openInFile() {
    try {
      inFile = new ObjectInputStream(new FileInputStream("dealership.txt")); 
      
    } catch (IOException e) {
      System.out.println("Try adding data then exit using menu option 9 to "
                         + "create file. " + e);
    }
  }
  
  /**
   * This method first checks if there is data to be read from a file. If so,
   * the data is read as Serialized objects until an End Of File is reached. 
   * The read data is then populated to Vehicle Array using a for-each loop. 
   */
  public static void readFile() {
    
    if (inFile == null) {
      return;
    }
    
    try {
      while (true) {
        Vehicle v = (Vehicle) inFile.readObject();
        v.addToVehicleArray(v);
      }
    } 
    catch (ClassNotFoundException classNotFound) {
      System.err.println("Invalid object type. " + classNotFound);
    } 
    catch (IOException e) {
      System.err.println("Error reading from file. " + e);
    }
  }
  
  /**
   * This method closes the open input file
   */
  public static void closeInFile() {
    try {
      if (inFile != null) {
        inFile.close();
      }
    } catch (IOException e) {
      System.err.println("Error closing file. Terminating." + e);
      System.exit(1);
    }
  }
  
  /**
   * Returns an integer value representing the added Users
   * @return userCounter
   */
  public static int incrementCounter(){
    ++userCounter;
	return userCounter;    
  }

public static int findUserCustomer(Scanner in, int id){
	for(Customer c: User.getUserArrayCustomer()){
	    if( id == c.getId() ){
	      System.out.printf("ID" + "  First" + "          Last" + "           DL Number" + "              Phone\n");	  
	      System.out.printf("% 2d  " +  "%-15.10s" +  "%-15.30s" + "%-15d" + "%15s\n",  c.getId(), c.getFirstName(), c.getLastName(), c.getDriverLicense(), c.getPhoneNumber() );
	      return c.getUserArrayCustomer().indexOf(c);
}
	    else
	    {
	    	System.out.print("The ID number was not found.");
	    }
	    
		}
	return -1;
 	}
}
