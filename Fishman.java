import java.util.Random;

public class Fishman extends Thread {
	// private
	public double vacation_cost = 0;
	public int maxCatch = Integer.MIN_VALUE;
	public int caughtFish = Integer.MIN_VALUE;
	
	// public 
	public boolean needs_help = false;
	
	public Fishman(String name){
		setName(name);
	}
	
	public void run(){
		while(!((vacation_cost >= BigCatchManager.get_vc()) && (maxCatch == 200))){
			while(caughtFish < 10){
				wait_fishing();
				fishing();
				if(caughtFish == 0){
					int default_priority = getPriority();
					setPriority(10);
					setPriority(default_priority);
					BigCatchManager.unlock_fishhole();
					yield();
				}else{
					BigCatchManager.unlock_fishhole();
				}
			}
		}
	}
	public void fishing(){
		int num_fish = BigCatchManager.fish.length;
		int i_fish = (new Random()).nextInt(num_fish);
		caughtFish = BigCatchManager.fish[i_fish];
		if(caughtFish>maxCatch){
			maxCatch = caughtFish;
		}
		msg("caught "+caughtFish+" pounds fish");
	}
	public void wait_fishing(){
		BigCatchManager.add_waitList(this);
		msg("waiting for fishing");
		try {
			sleep(100*1000);
		} catch (InterruptedException e) {
			msg(e.getMessage());
		}
	}
	
	public void msg (String m) {
		long current_time = System.currentTimeMillis() - BigCatchManager.time;
		System.out.println( "[" + current_time + "]"  + getName() + ": "+ m);
	}
}