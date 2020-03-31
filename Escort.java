
public class Escort extends Visitor {

private int prio; //his priority is the same as the visitor he follows
	
	public Escort(int eo, int ef) { //Constructor calls the constructor of visitor class to make the necessary initializations
		super(eo,ef,1);
		
	}
	
	public void setprio(int pro) { //setprio is overridden because it has to function a bit differently, more on that later..
		super.prio=pro;
		this.prio=super.prio;
	}
	
	public void printprio(){ //function that prints escort's priority
		
		System.out.println(" priority is :" + this.prio + " and I am escort");
	}
	
	public int setserved(int n){ //this function will be used later when a visitor has reached his office in order to get a served value
		return this.served=n;
	}
	
	
}
