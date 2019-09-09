
public class CustomerAssociate extends Thread {
	public boolean working = true;
	public boolean isBusy = false;
	
	public CustomerAssociate(String name){
		setName(name);
	}
	
	public void run(){
		while(working){
			if(!BigCatchManager.isEmpty_queue()){ // pick a fishman from the queue
				serve();
			}else{ // otherwise, sleep 
				msg("no fisherman is in the queue");
				try {
					sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		terminate();
	}
	
	// serving one fishman
	public void serve(){
		Fishman fm = BigCatchManager.dequeue();
		// prevent no fishman
		if(fm == null) return;
		
		isBusy = true;
		// tell picked fishman that which associate serves him
		BigCatchManager.serveTo(this, fm);
		msg("is serving "+ fm.getName());
		// release the fishman from busy waiting
		BigCatchManager.help_ca(fm);
		// lock current associate 
		while(isBusy){
			try {
				sleep(500);
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	public void terminate(){
		int i_ca = BigCatchManager.getAssociates().indexOf(this);
		// join to previous associate[i-1] and wait to terminate
		if(i_ca != 0){
			CustomerAssociate previous = BigCatchManager.getAssociates().get(i_ca-1);
			try{
				if(previous.isAlive()){
					BigCatchManager.off_ca(i_ca-1);
					previous.join();
				}
			}catch(InterruptedException e){
				System.out.println(Thread.currentThread().getName()+ " join failed");
			}
		}
		
		msg("is going home");
	}
	
	public void msg (String m) {
		long current_time = System.currentTimeMillis() - BigCatchManager.time;
		System.out.println( "[" + current_time + "]"  + getName() + ": "+ m);
	}
}
