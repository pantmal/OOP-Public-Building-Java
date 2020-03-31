import java.util.Random;

public class Visitor {
		
	protected int o_pr; //the office that the visitor wants to go to
	protected int f_pr; //the floor that the visitor wants to go to
	protected int prio; //his priority
	protected int served; //variable that determines whether the visitor was served or not
	protected int has_esc; //variable that determines whether the visitor has an escort
	protected int is_esc; //variable that determines whether the visitor is an escort

	public Visitor(int fo_pr, int ff_pr, int e){ //Constructor function
		this.o_pr=fo_pr;
		this.f_pr=ff_pr;
		
		if(e==0) { //e variable is set to 0 whenever a regular visitor is created
		Random rande = new Random(); //we use rand() to determine whether the visitor will have an escort or not
		has_esc=rande.nextInt(2); 
		is_esc=0;
		
		}
		if(e==1) { //if we create an escort e is set to 1
			has_esc=0;
			is_esc=1;
			
		}
		
		this.served=0;
	}
	
	public void set_esc(int i) { //setter used when has_esc has to receive a value 
		this.has_esc=i;
	}
	
	public int get_esc() { //getter that returns whether the visitor has an escort
		return this.has_esc;
	}
	
	public int get_isesc() { //getter that returns whether the visitor is an escort
		return this.is_esc;
	}
	
	public int setserved(int n){ //this function will be used later when a visitor has reached his office in order to get a served value
		return this.served=n;
	}
	
	public int getserved(){ //getter that returns whether the visitor is served or not
		return this.served;
	}
	
	public int getfloor(){ //getter that returns visitor's floor
		return this.f_pr;
	}
	
	public int getoffice(){ //getter that returns visitor's office
		return this.o_pr;
	}
	
	public void setprio(int i){ //this function is used when a visitor reaches the groundfloor. this is where his priority is set
		this.prio=i;
	}
	
	public int getprio(){ //getter that returns visitor's priority
		return this.prio;
	}
	
	public void printprio(){  //function that prints visitor's priority
		System.out.println(" priority is :" + this.prio );
	}
	
	
}
