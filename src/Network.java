import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

public class Network {

	public ArrayList<User> USERI = new ArrayList<>();
	
    private static final Network INSTANCE = new Network();
 
    private Network() {}
 
    public static Network getInstance() {
        return INSTANCE;
    }
    
    /**
     * VERIFY , by id , if a user is in the users list
     * @param ID
     * @return
     */
    public int verify(int ID){
    	for (int i = 0; i < USERI.size(); i++) {
			if (USERI.get(i).ID == ID){
				return 0;
			}
		}
    	return 1;
    }
    
    /**
     * <p>
     * add - adding user in users list by name and id
     * </p>
     * @param c
     * @param nume
     */
    public void add(int c , String nume){
    	USERI.add(new User(c, nume));
    }
    
    /**
     * <p>
     * printNetwork - method - foreach
     * </p>
     */
    public void printNetwork() {
    	for (User i : USERI) {
			System.out.println(i);
		}
    }
    
    /**
     * <p>
     * BUBBLE SORT method - sorting ascending users 
     * </p>
     * @param prieteni
     */
    public void bubbleSort(ArrayList<User> prieteni) {
		 for (int i = 0; i < prieteni.size() - 1; i++) {
			 for (int j = 0; j < prieteni.size() - i - 1; j++) {
				if (prieteni.get(j).ID > prieteni.get(j+1).ID) {
					User a1 = prieteni.get(j);
					User a2 = prieteni.get(j+1);
					
					prieteni.set(j, a2);
					prieteni.set(j+1, a1);
				}
			 }
		 }
	 }
   
    /**
     * <p>
     * getName method -returns a user's name by ID
     * </p>
     * @param ID
     * @return
     */
    public String getName(int ID){
    	for (int i = 0; i < USERI.size(); i++) {
			if(USERI.get(i).ID == ID){
				return USERI.get(i).getNume();
			}
		}
    	return "nu avem";
    }
    
    /**
     * <p>
     * getUser method  
     * </p>
     * 
     * <p>
     * returns a specify user by givinig two parameters: name and id
     * </p>
     * @param nume
     * @param id
     * @return
     */
    public User getUser(String nume , int id){
    	for (int i = 0; i < USERI.size(); i++) {
			if(USERI.get(i).getNume().equals(nume) && USERI.get(i).ID == id){
				return USERI.get(i);
			}
		}
    	return new User(0,"0");
    }
    
    /**
     * <p>
     * getUser method 
     * </p>
     * 
     * <p>
     * returns a specific user by giving it's id
     * </p>
     * @param id
     * @return
     */
    public User getUser(int id) {
    	for (int i = 0; i < USERI.size(); i++) {
			if(USERI.get(i).ID == id){
				return USERI.get(i);
			}
		}
    	return new User(0,"0");
    }
    
    /**
     * <p>
     * DFS method
     * </p>
     * 
     * <p>
     * traversing entier adjacency list ;
     * it helps us to find all communities (connex components)
     * </p>
     * @param visited
     * @param start
     * @param community
     */
    public void DFS(boolean[] visited, User start, ArrayList<Integer> community) {
    	int index = USERI.indexOf(start);
    	visited[index] = true;
    	community.add(start.getID());
    	
    	for (int i = 0; i < start.prieteni.size(); i++) {
    		int position = USERI.indexOf(start.prieteni.get(i));
    		if (visited[position] == false)
    			DFS(visited, USERI.get(position), community);
    	}
    }
    
    /**
     * <p>
     * getMax method
     * </p>
     * 
     * <p>
     * returns max value from an integer vector
     * </p>
     * @param v
     * @return
     */
    public int getMax(int[] v) {
    	int max = 0;
    	for (int i : v) {
			if (i > max) {
				max = i;
			}
		}
    	return max;
    }
    
    /**
     * <p>
     * BFS method
     * </p>
     * 
     * <p>
     * BFS traversal algorithm ;
     * it helps us to find the strongest community 
     * </p>
     * @param distances
     * @param fullCommunity
     */
    public void BFS(int[] distances, ArrayList<User> fullCommunity) {
    	boolean[] visited = new boolean[USERI.size()];
    	int diametru = 0;
	    int nrUtilizatori = fullCommunity.size();
    	for (User user : fullCommunity) {	

    		Queue<User> coada = new LinkedList<User>();
	    	coada.add(user);
	    	int index = USERI.indexOf(user);
	    	visited[index] = true;
	    	distances[index] = 0;
	    	while (!coada.isEmpty()) {
	    		User temp = coada.remove();
	    		for (int i = 0; i < temp.prieteni.size(); i++) {
	    			int position = USERI.indexOf(temp.prieteni.get(i));
	    			if(!visited[position]) {
	    				coada.add(USERI.get(position));
	    				visited[position] = true;
	    				distances[position] = distances[USERI.indexOf(temp)] + 1;
	    			}
	    		}
	    	}
	    	
	    	int max = getMax(distances);
	    	if (max > diametru) {
	    		diametru = max;
	    	}
	    	
	    	visited = new boolean[USERI.size()];
	    }
    	
    	if (nrUtilizatori == 1) {
    		System.out.println(0);
    	}
    	else {
    		System.out.println((nrUtilizatori - diametru) * 100 / (nrUtilizatori - 1));
    	}
 
    }
}
