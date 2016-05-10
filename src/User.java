import java.util.ArrayList;

/**
 * <p>
 * User from Social Network
 * </p>
 * @author ProBook
 *
 */

public class User {
	
	public int ID;
	public String Nume;
	public ArrayList<User> prieteni = new ArrayList<>();
	
	/**
	 * Constructor without parameters
	 */
	public User(){}
	
	/**
	 * <p>
	 * Constructor with parameters - creates a new User
	 * </p>
	 * @param ID
	 * @param Nume
	 */
	public User(int ID , String Nume){
		this.Nume = Nume;
		this.ID = ID;
	}
	
	/**
	 * <p>
	 * setNume - setter method
	 * </p>
	 * @param nume
	 */
	public void setNume(String nume){
		this.Nume=nume;
	}
	
	/**
	 * <p>
	 * detID - setter method
	 * </p>
	 * @param id
	 */
	public void setID(int id){
		this.ID=id;
	}
	
	/**
	 * <p>
	 * getNume - getter method
	 *</p>
	 * @return
	 */
	public String getNume(){
		return Nume;
	}
	
	/**
	 * <p>
	 * getID - getter method
	 * </p>
	 * @return
	 */
	public int getID(){
		return ID;
	}
	
	/**
	 * <p>
	 * printPrieteni - method for printing a user's friends
	 * </p>
	 */
	 public void printPrieteni(){
	    for (User i : prieteni) {
			System.out.println(i);
		}
	  }
	   
	 /**
	  * <p>
	  * Verify if the user with specify id is in the 
	  * specify user's friends
	  * </p>
	  * @param ID
	  * @return
	  */
	 public boolean verifyPrieten(int ID){
	    	for (int i = 0; i < prieteni.size(); i++) {
				if (prieteni.get(i).ID == ID){
					return true;
				}
			}
	    	return false;
	 }
	
	 
	 /**
	  * <p>
	  * Override the string method and 
	  * returns id and name from a user and his list of friends
	  * </p>
	  */
	 @Override
	public String toString() {
		String listString = "";
		for (int i = 0; i < prieteni.size(); i++) {
			listString += prieteni.get(i).getID();
			listString += " ";
		}
		return (this.getID() +" "+this.getNume()+": " + listString);
	}
}