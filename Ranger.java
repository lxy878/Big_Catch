import java.util.ArrayList;
import java.util.Random;

public class Ranger extends Thread {
	public boolean working = true;
	public boolean fishhole = false;
	
	public ArrayList<Fishman> waitingList = new ArrayList<>();
	
	public Ranger(String name){
		setName(name);
	}
	
	public void run(){
		while(working){
			if(!waitingList.isEmpty()){
				if(!fishhole){
					// lock fish hole
					fishhole = true;
					pickFishman();
				}
			}else{
				msg("no fisherman comes to fish");
				try {
					sleep(500);
				} catch (InterruptedException e) {
					System.out.println(e.getMessage());
				}
			}
		}
		msg("is going home");
	}
	
	public void pickFishman(){
			// pick a fishman
			int i_fm = (new Random()).nextInt(waitingList.size());
			Fishman picked_fm = waitingList.get(i_fm);
			// wake up fishman
			if(!picked_fm.isInterrupted()){
				msg("picked "+picked_fm.getName());
				waitingList.remove(picked_fm);
				picked_fm.interrupt();
			}
	}
	
	public void msg (String m) {
		long current_time = System.currentTimeMillis() - BigCatchManager.time;
		System.out.println( "[" + current_time + "]"  + getName() + ": "+ m);
	}
}
