
public class CustomerAssociate extends Thread {
	public boolean working = true;
	public boolean isBusy = true;
	
	public CustomerAssociate(String name){
		setName(name);
	}
	
	public void run(){
		
	}
	
	public void msg (String m) {
		long current_time = System.currentTimeMillis() - BigCatchManager.time;
		System.out.println( "[" + current_time + "]"  + getName() + ": "+ m);
	}
}
