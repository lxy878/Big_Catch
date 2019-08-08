import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BigCatchManager {
	private static int vaction_cost = 0;
	private static ArrayList<Fishman> fishmanList = null;
	private static Ranger ranger = null;
	private static ArrayList<CustomerAssociate> associateList = null;
	
	public static int leftFishmanCounter = 0;
	public static final long time = System.currentTimeMillis();
	
	public static final int fish[] = {0, 5, 10, 20, 30, 40, 50, 200};
	public static Queue<Fishman> waitingLine = new LinkedList<>();
	
	public static ArrayList<Fishman> wait_fm = new ArrayList<>();
	
	
	// synchronized functions
	
	// may be syn
	public static void add_waitList(Fishman fm){
		wait_fm.add(fm);
	}
	public static boolean isEmpty_waitList(){
		return wait_fm.isEmpty();
	}
	public static void unlock_fishhole(){
		ranger.fishhole = false;
	}
	//
	
	public static int size_waitList(){
		return wait_fm.size();
	}
	public static Fishman getFishman_waitList(int i){
		return wait_fm.get(i);
	}
	public static void remove_waitList(int i){
		wait_fm.remove(i);
	}
	//
	public static Fishman get_fm(int i){
		return fishmanList.get(i);
	}
	
	public static int get_vc(){
		return vaction_cost;
	}
	public static void setAll(ArrayList<Fishman> f_list, ArrayList<CustomerAssociate> ca_list, Ranger r, int v_cost){
		fishmanList = f_list;
		associateList = ca_list;
		ranger = r;
		vaction_cost = v_cost;
	}
}
