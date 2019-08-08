import java.util.ArrayList;
import java.util.Random;

public class Ranger extends Thread {
	public boolean working = true;
	public boolean fishhole = false;
	
	public Ranger(String name){
		setName(name);
	}
	
	public void run(){
		while(working){
			if(!BigCatchManager.isEmpty_waitList()){
				if(!fishhole){
					fishhole=true;
					pickFishman();
				}
			}else{
				try {
					sleep(500);
				} catch (InterruptedException e) {
					System.out.println(e.getMessage());
				}
			}
		}
	}
	
	public void pickFishman(){
		int num = BigCatchManager.size_waitList();
		int picked_fm = (new Random()).nextInt(num);
		Fishman fm = BigCatchManager.getFishman_waitList(picked_fm);
		if(!fm.isInterrupted()){
			BigCatchManager.remove_waitList(picked_fm);
			fm.interrupt();
		}
	}
	public void msg (String m) {
		long current_time = System.currentTimeMillis() - BigCatchManager.time;
		System.out.println( "[" + current_time + "]"  + getName() + ": "+ m);
	}
}
