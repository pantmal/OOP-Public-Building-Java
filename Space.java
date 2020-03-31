
public class Space {

protected	int N; //capacity is used by every child
		
	
	public Space(int fN) {N=fN;} //Constructor function
	
	 //every function is inherited by the mother class
	//because later on instead of using the children directly i will pass their addresses at pointers to the mother class	
	//Note: Elevator object is an exception to the above because it has to use other classes as arguments in its methods
	void enter (Visitor v){} //used by Office, Entrance, Floor and Building 	 
	void enter (Escort e) {} //used by Office, Entrance, Floor and Building
	Visitor exit(){ Visitor v=null; return v;} //used by Office
	Visitor exit(int a, int b, int c){Visitor v=null; return v;}//used by Entrance
	Escort exit(int a, int b, int c, int d){Escort e=null; return e;}//used by Entrance
	Visitor exit(int a, int b, boolean l){Visitor v=null; return v;}//used by Floor
	Escort exit(int a, int b, double c) {Escort e=null; return e;}//used by Floor
	Visitor exitlimit() {Visitor v=null; return v;} //used by Office
	Escort exit(int a){Escort e=null; return e;}//used by Office
	void exit(Visitor vt){}//used by Building
	void exit(Escort et){}//used by Building
	void make_served(){}//used by Floor and Office
	int get_ofcount(){int v=0; return v;}//used by Office
	int findparent(int p){int v=0; return v;}//used by Office
	int gethead(int p){int v=0; return v;}//used by Entrance
	boolean checknext(int a, int b) {boolean v=false; return v;}//used by Entrance
	boolean checknext(int a) {boolean v=false; return v;}//used by Entrance
	int getpos() {int v=0; return v;}//used by Entrance
	boolean getoff(int a){boolean v=false; return v;}//used by Entrance
	void print_prio(int a, int b, int c){}//used by Entrance
	int get_encount(){int v=0; return v;}//used by Entrance
	int getno(){int v=0; return v;}//used by Floor
	int get_flcount(){int v=0; return v;}//used by Floor
	void proceed(){}//used by Building
	void operate(){}//used by Building
	int getbcount(){int v=0; return v;}//used by Building
		
	//note: whenever arguments of type double are used it's because i had to overload some functions and couldn't come up with an other solution. :/ 
	
}
