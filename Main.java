import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	
	public static int num_fm = 6;
	public static int num_ca = 2;
	public static int vacation_cost = 250;
	
	public static void main(String[] args) {
		// set values by arguments
		if(args.length >= 3){
			num_fm = Integer.parseInt(args[0]);
			num_ca = Integer.parseInt(args[1]);
			vacation_cost = Integer.parseInt(args[2]);
		}
		// set values by keyboard
		Scanner kb = new Scanner(System.in);
		System.out.print("Enter \"m\" to set variables or any keys to not set: ");
		
		String s = kb.nextLine();
		if(s.toLowerCase().equals("m")){
			System.out.print("Enter number of fishmen: ");
			num_fm = Integer.parseInt(kb.nextLine());
			System.out.print("Enter number of associates: ");
			num_ca = Integer.parseInt(kb.nextLine());
			System.out.print("Enter min vacation cost: ");
			vacation_cost = Integer.parseInt(kb.nextLine());
		}
		kb.close();
		
		ArrayList<Fishman> fm_list = new ArrayList<>();
		ArrayList<CustomerAssociate> ca_list = new ArrayList<>();
		Ranger ranger = new Ranger("Ranger");
		
		// declare threads and add to lists
		for(int i=0; i<num_fm; i++){
			Fishman fm = new Fishman("Fisherman["+i+"]");
			fm_list.add(fm);
		}
		
		for(int i=0; i<num_ca; i++){
			CustomerAssociate ca = new CustomerAssociate("Assoicate["+i+"]");
			ca_list.add(ca);
		}
		
		// set lists of threads and values to manager
		BigCatchManager.setAll(fm_list, ca_list, ranger, vacation_cost);
		
		// execute threads
		startAll(fm_list, ca_list, ranger);
	}
	
	// start all threads
	public static void startAll(ArrayList<Fishman> fm_list, ArrayList<CustomerAssociate> ca_list, Ranger r){
		for(Fishman fm : fm_list)	fm.start();
		for(CustomerAssociate ca : ca_list)	ca.start();
		r.start();
	}

}
