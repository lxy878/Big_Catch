import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class BigCatchManager {
	private static int vaction_cost = 0;
	private static ArrayList<Fishman> fishmanList = null;
	private static Ranger ranger = null;
	private static ArrayList<CustomerAssociate> associateList = null;
	private static Queue<Fishman> waitingLine = new LinkedList<>();
	
	public static int leftFishmanCounter = 0;
	public static final long time = System.currentTimeMillis();
	public static final int fish[] = {0, 5, 10, 20, 30, 40, 50, 200};
	
	/* synchronized functions*/
	
	public synchronized static void add_fishList(Fishman fm){
		ranger.waitingList.add(fm);
	}
	// record the number of fishmen waiting to leave
	public synchronized static void addCounter(){
		leftFishmanCounter++;
	}
	// unlock fish hole
	public synchronized static void release_fishhole(){
		ranger.fishhole = false;
	}
	// add a fishman into the waiting line for associates
	public synchronized static void enqueue(Fishman i_fm){
		waitingLine.add(i_fm);
	}
	// a fishman is out the waiting line
	public synchronized static Fishman dequeue(){
		return waitingLine.poll();
	}
	
	public synchronized static boolean isEmpty_queue(){
		return waitingLine.isEmpty();
	}
	
	/* common functions*/
	// fishman is leaving
	public static void off_fm(int i_fm){
		fishmanList.get(i_fm).waiting = false;
	}
	// a fishman is picked and released by an associate
	public static void help_ca (Fishman fm){
		fm.needs_help = false;
	}
	// set an associate to the picked fishman
	public static void serveTo(CustomerAssociate ca, Fishman fm){
		fm.ca = ca;
	}
	// release the associate from busy waiting
	public static void release_ca(CustomerAssociate ca){
		ca.isBusy = false;
	}
	// associate is leaving
	public static void off_ca(int i_ca){
		associateList.get(i_ca).working = false;
	}
	// ranger is leaving
	public static void off_ranger(){
		ranger.working = false;
	}
	
	// fishing
	public static int randomFish(){
		int num_fish = fish.length;
		int i_fish = (new Random()).nextInt(num_fish);
		return fish[i_fish];
	}
	/* getters and setters */
	public static void setAll(ArrayList<Fishman> f_list, ArrayList<CustomerAssociate> ca_list, Ranger r, int v_cost){
		fishmanList = f_list;
		associateList = ca_list;
		ranger = r;
		vaction_cost = v_cost;
	}
	
	public static ArrayList<CustomerAssociate> getAssociates(){
		return associateList;
	}
	public static ArrayList<Fishman> getFishman(){
		return fishmanList;
	}
	
	public static Ranger getRanger(){
		return ranger;
	}
	
	public static int getVactionCost(){
		return vaction_cost;
	}
}
