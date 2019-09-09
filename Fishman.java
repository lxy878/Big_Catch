
public class Fishman extends Thread {
	// private
	private double vacation_cost = 0;
	// max fish as 200 pounds
	private int maxCatch = Integer.MIN_VALUE;
	private int caughtFish = Integer.MIN_VALUE;
	
	// public 
	// customer associate who serves current fishman
	public CustomerAssociate ca = null;
	public boolean needs_help = false;
	// prevent fishman[0] to terminate if it is not the last one to finish
	public boolean waiting = true;
	
	public Fishman(String name){
		setName(name);
	}
	
	public void run(){
		while(!((vacation_cost >= BigCatchManager.getVactionCost()) && (maxCatch == 200))){
			// fishing
			while(caughtFish<10){
				wait_fishing();
				caughtFish = fishing();
				// fish is smaller than 10
				if(caughtFish != 0){
					// release fishhole to other fishmen
					BigCatchManager.release_fishhole();
				}else{
					// one more time if no fish
					oneMore();
				}
			}
			// on a boat
			takeBoatTrip();
			// on a shop
			onShop();
		}
		gohome();
	}
	
	// wait for fishing
	public void wait_fishing(){
		msg("waiting for fishing");
		// add a waiting list to fishing
		BigCatchManager.add_fishList(this);;
		try {
			// sleeping a long time
			sleep(1000*1000);
		} catch (InterruptedException e) {
			msg(e.getMessage());
		}
	}
	
	// start fishing and return fish size
	public int fishing(){
		msg("starts fishing");
		// fishing
		int fish = BigCatchManager.randomFish();
		//record max size fish caught
		if(fish>maxCatch) maxCatch = fish;
		msg("caught "+fish+" pounds fish");
		return fish;
	}
	
	// fish one more time if no fish
	public void oneMore(){
		msg("tries one more time");
		// save default priority
		int default_value = getPriority();
		// set priority to max
		setPriority(10);
		caughtFish = fishing();
		// reset priority to default
		setPriority(default_value);
		// release fishhole 
		BigCatchManager.release_fishhole();
		msg("rested");
		yield();
	}
	
	// boat trip
	public void takeBoatTrip(){
		msg("is taking a boat Trip");
		try {
			sleep(3*1000);
		} catch (InterruptedException e) {
			System.out.println(getName()+ " boat trip is interrupted");
		}
	}
	
	// enter the queue and wait to give help
	public void onShop(){
		msg("is walking into the shop");
		BigCatchManager.enqueue(this);
		needs_help = true;
		msg("is standing into the queue");
		// busy wait
		while(needs_help){
			try {
				sleep(500);
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
			}
		}
		// cash out or mount
		servedBy();
		msg("is leaving the shop");
	}
	
	// cash out fish or mount max fish
	public void servedBy(){
		// mount max fish 
		if(caughtFish == 200){
			msg("mounted "+ maxCatch + " pounds fish");
		}else{
			// otherwise, cash out the fish and save money for vacation
			double cash = caughtFish*0.75;
			msg("cashed out "+caughtFish + " pounds fish for $" +cash );
			vacation_cost +=caughtFish*0.75;
		}
		// reset caught fish size
		caughtFish = 0;
		// release customer associate who serves current fishman
		BigCatchManager.release_ca(ca);
	}
	
	// terminate
	public void gohome(){
		// record num of fishmen who caught max fish and saved enough money
		BigCatchManager.addCounter();
		int index = BigCatchManager.getFishman().indexOf(this);
		if(index == 0){	// the first fishman
			// the first fishman is not the last one to terminate
			if(BigCatchManager.leftFishmanCounter != BigCatchManager.getFishman().size()){
				// wait for the last fishman to terminate
				while(waiting){
					try {
						sleep(500);
					} catch (InterruptedException e) {
						System.out.println(e.getMessage());
					}
				}
			}
			// call ranger and associates to terminate
			msg("calls the assoicate to go home");
			BigCatchManager.off_ca(BigCatchManager.getAssociates().size()-1);
			msg("calls the ranger to go home");
			BigCatchManager.off_ranger();
		}else{ // no the first fishman
			Fishman previous = BigCatchManager.getFishman().get(index-1);
			try {
				// current fishman is the last one to terminate
				if(BigCatchManager.leftFishmanCounter == BigCatchManager.getFishman().size()){
					// unlock the first fishman
					BigCatchManager.off_fm(0);
				}
				msg("joins to "+ previous.getName());
				// join to previous fishman
				previous.join();
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
			}
		}
		msg("is going home");
	}
	
	public void msg (String m) {
		long current_time = System.currentTimeMillis() - BigCatchManager.time;
		System.out.println( "[" + current_time + "]"  + getName() + ": "+ m);
	}
}