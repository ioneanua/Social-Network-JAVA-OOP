import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * <p>
 * This execution entry point class handles parsing and executing commands from the input
 * file.
 * </p>
 */
public class Main {

	/**
	 * <p>
	 * Execution entry point.
	 *</p>
	 *
	 * @param args the name of the file containing commands to be executed
	 */
	public static void main(String[] args) {
		
		if (args.length != 1) {
			System.err.println("Usage: java -cp <classpath> Main <input file>");
			System.exit(1);
		}
		
		Network useri = Network.getInstance();
		FileParser fPars = new FileParser(args[0]);
		
		fPars.open();
		List<String> comenzi = fPars.parseNextLine();
	
		while (comenzi != null) {
			// ADD
			if (comenzi.get(0).equals("add")){
				int c = Integer.parseInt(comenzi.get(1));
				if (useri.verify(c)==0){
					System.out.println("DUPLICATE");
				} else {
					useri.add(c, comenzi.get(2));
					System.out.println("OK");
				}
			} // END ADD 
			
			// FRIEND
			else if (comenzi.get(0).equals("friend")) {
				int ok1 = 0, ok2 = 0;
				String prieten1 = useri.getName(Integer.parseInt(comenzi.get(1)));
				int id1 = Integer.parseInt(comenzi.get(1));
				String prieten2 = useri.getName(Integer.parseInt(comenzi.get(2)));
				int id2 = Integer.parseInt(comenzi.get(2));
				
				for (User user : useri.USERI) {
					if (user.getID() == id1) ok1 = 1;
					if (user.getID() == id2) ok2 = 1;
				}
				
				
				if (useri.getUser(id1).verifyPrieten(id2) == true) {
					System.out.println("OK");
				} else {
					if (ok1 == 1 && ok2 == 1) {
						useri.getUser(prieten1, id1).prieteni.add(useri.getUser(prieten2, id2));
						useri.getUser(prieten2 ,id2).prieteni.add(useri.getUser(prieten1, id1));
						System.out.println("OK");
					} else {
						System.out.println("INEXISTENT");
						comenzi = fPars.parseNextLine();
						continue;
					}
				}
				
			} // END FRIEND
			
			// UNFRIEND
			else if (comenzi.get(0).equals("unfriend")){
				int id1 = Integer.parseInt(comenzi.get(1));
				int id2 = Integer.parseInt(comenzi.get(2));
				String prieten1 = useri.getName(id1);
				String prieten2 = useri.getName(id2);
				
				
				if (useri.getUser(prieten1, id1).verifyPrieten(id2) == true) {
					useri.getUser(prieten1, id1).prieteni.remove(useri.getUser(prieten2 ,id2));
		
				}
				else {
					System.out.println("INEXISTENT");
					comenzi = fPars.parseNextLine();
					continue;
				}
				
				if (useri.getUser(prieten2, id2).verifyPrieten(id1) == true) {
					useri.getUser(prieten2, id2).prieteni.remove(useri.getUser(prieten1 ,id1));
					
					System.out.println("OK");
				}
				else {
					System.out.println("INEXISTENT");
					comenzi = fPars.parseNextLine();
					continue;
				}
				
			} //END UNFRIEND
			
			//PRINT
			else if (comenzi.get(0).equals("print")){
				useri.bubbleSort(useri.USERI);
				for (User user : useri.USERI) {
					useri.bubbleSort(user.prieteni);
				}
				
				//NETWORK
				if (comenzi.get(1).equals("network")){
					useri.printNetwork();	
				}//END NETWORK 
				
				// USER
				else if (comenzi.get(1).equals("user")){
					if (useri.verify(Integer.parseInt(comenzi.get(2))) == 0){
						System.out.println(useri.getUser(Integer.parseInt(comenzi.get(2))));
					} else {
						System.out.println("INEXISTENT");
						comenzi = fPars.parseNextLine();
						continue;
					}
					
				} //END USER
				
				// STRENGTH
				else if (comenzi.get(1).equals("strength")){
					int ok = 0;
					User aux = new User();
					for (User u : useri.USERI) {
						if(u.getID() == Integer.parseInt(comenzi.get(2))) {
							aux = u;
							ok = 1;
						}
					}
					if (ok == 1) {
						boolean visited[] = new boolean[useri.USERI.size()];
						ArrayList<Integer> community = new ArrayList<Integer>();
						useri.DFS(visited, aux, community);
						ArrayList<User> fullCommunity = new ArrayList<User>();
						for (Integer i : community) {
							fullCommunity.add(useri.getUser(i));
						}
						int[] distances = new int[useri.USERI.size()];
						useri.BFS(distances, fullCommunity);
					} else {
						System.out.println("INEXISTENT");
						comenzi = fPars.parseNextLine();
						continue;
					}
				} //END STRENGTH
				
				// COMMUNITIES
				else if (comenzi.get(1).equals("communities")){
					boolean[] visited = new boolean[useri.USERI.size()];
			    	ArrayList<Integer> community = new ArrayList<Integer>();
			    	
			    	for (User u : useri.USERI) {
			    		if (visited[useri.USERI.indexOf(u)] == false) {
			    			community.clear();
			    			useri.DFS(visited, u, community);
			    			String s = new String();
			    			for (int i = 0; i < community.size(); i++) {
			    	    		s += community.get(i);
			    	    		s += " ";
			    	    	}
			    			System.out.println(community.size() + ": " + s);
			    		}
			    	}
				} //END COMMUNITIES
			
			} //END PRINT
			
			//REMOVE
			else if (comenzi.get(0).equals("remove")){
				
				if (useri.verify(Integer.parseInt(comenzi.get(1) )) == 1 ){
					System.out.println("INEXISTENT");
					comenzi = fPars.parseNextLine();
					continue;
				} else {
					for (Iterator<User> it = useri.USERI.iterator(); it.hasNext () ; ){
						User user = it.next();
						if (user.getID() == Integer.parseInt(comenzi.get(1))) {
							it.remove();
							for (int i = 0; i < useri.USERI.size(); i++) {
								for (int j = 0; j < useri.USERI.get(i).prieteni.size(); j++) {
									if (useri.USERI.get(i).prieteni.get(j) == user) 
										useri.USERI.get(i).prieteni.remove(j);
								}
							}
							System.out.println("OK");
						}
					}
					
				}
				
			} //END REMOVE
			
			comenzi = fPars.parseNextLine();	
		}
		fPars.close();
		
	}
}