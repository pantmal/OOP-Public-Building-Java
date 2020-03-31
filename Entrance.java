
public class Entrance extends Space {

	private	Visitor[] entered; //array that stores all the visitors in an entrance
	private	int ecount; //how many people are in an entrance
		
	
	public	Entrance(int fNe){ //Constructor function
			super(fNe);	
			System.out.println("The Entrance has been created!");
			
			entered= new Visitor[fNe];
			int j=0;
			for(j=0;j<fNe;j++){
				entered[j]=null;
			}
			
			ecount=0;
			
		}
	
	public int gethead(int p) {//gethead is used to get whether a visitor about to exit the entrance is an escort or not
		
		int head=0;
		for(int i=0;i<N;i++) {
			if(i!=p) {
				continue;
			}
			if(entered[i]!=null && entered[i].is_esc==1) {
				head=1;
				break;
			}
			if(entered[i]!=null && entered[i].is_esc==0) {
				head=0;
				break;
			}
		}
		return head;
	}
	
	public boolean checknext(int a, int b) { //checknext function checks whether the next visitor we have has an escort so he won't enter the elevator (used when an elevator is on its limits)
		boolean bingo=false;
		int i=0;
		for(i=0;i<N;i++){
			if(entered[i]!=null) {
			if( entered[i].has_esc==1){
				
				bingo =true;
				break;
			}
			
			}
		}
		return bingo;
	}
	
	public boolean checknext(int a) { //variation used in groundfloor class
		boolean bingo=false;
		int i=0;
		for(i=0;i<N;i++){
			if(entered[i]!=null) {
			if(i!=a) {
				continue;
			}
			if( entered[i].has_esc==1 ){
	
				bingo =true;
				break;
			}
			}
		}
		return bingo;
	}
	
	public int getpos() { //getpos is used to get the first non-empty array position from the entered array
		int i=0;
		for(i=0;i<N-1;i++){
			if(entered[i]!=null){
				break;
			}
		}
		return i;
	}
	
		
 
	public	void enter(Visitor fvarr){  //Entrance enter takes a visitor and places him in the first empty cell it finds
			
			int i=0;
			for(i=0;i<N-1;i++){
				if(entered[i]==null){
					break;
				}
			}
			
			entered[i]=fvarr;
			ecount++;
		}
	
	public	void enter(Escort fvarr){  //Overloaded for escorts
		
		int i=0;
		for(i=0;i<N-1;i++){
			if(entered[i]==null){
				break;
			}
		}
		
		entered[i]=fvarr;
		ecount++;
	}
		
		
		public	Visitor exit(int f,int o, int a){  //Entrance exit removes the first visitor (who was served) from the array 
			 					 //Arguments f and o are used because the function behaves differently if we are exiting from a floor's entrance
			Visitor temp= null;
			if(f==0){ //exiting from groundfloor
			
				int i=0;
				for(i=0;i<N;i++){
					if(entered[i]!=null){
						if(i!=a) {
							continue;
						}
						temp=entered[i];
						entered[i]=null;
						
						ecount--;
						break;
						
					}
				}
				
				
				return temp;
			
			}
		
			
			if(f==1){  //exiting from floor
				if(a==1) {
					int k=0;
					for(k=0;k<super.N;k++){
					if(entered[k]!=null && entered[k].getoffice()==o && entered[k].has_esc==0 && entered[k].is_esc==0){ //making sure if the visitor wants to go to the desired office
						break;
					}
					}
					temp=entered[k];
					entered[k]=null;
					ecount--;
					return temp;
				}else {
				
				int k=0;
				for(k=0;k<super.N;k++){
					if(entered[k]!=null && entered[k].getoffice()==o){ //making sure if the visitor wants to go to the desired office
						break;
					}
				}
				temp=entered[k];
				entered[k]=null;
				ecount--;
				return temp;
				}
			}
			
			return temp;
		}
		
		public Escort exit(int f, int o, int a, int e) { //overloaded for escorts
			Escort temp= null;
			if(f==0){ //exiting from groundfloor
			
				int i=0;
				for(i=0;i<N;i++){
					if(i!=a) {
						continue;
					}
					if(entered[i]!=null && entered[i].is_esc==1){
						break;
					}
				}
				temp=(Escort)entered[i];
				entered[i]=null;
				
				ecount--;
				
				return temp;
			
			}
			
			if(f==1){  //exiting from floor
				
				
				int k=0;
				for(k=0;k<super.N;k++){
					if(entered[k]!=null && entered[k].is_esc==1 && entered[k].getoffice()==o){ //making sure if the visitor wants to go to the desired office
						break;
					}
				}
				temp=(Escort)entered[k];
				entered[k]=null;
				ecount--;
				return temp;
			
			}
			
			return temp;
		}
		
	public	boolean getoff(int i){ //this function is used to ensure the visitor wants to go to the office we are currently on
			boolean bingo=false;
			
			int k=0;
			for(k=0;k<N;k++){
				if(entered[k]!=null && entered[k].getoffice()==i){
					bingo=true;
					break;
				}
			}
		
			return bingo;
		}
		
	public	void print_prio(int n, int f, int i){ //function to print visitor's priority
			if (f==0){						  //again f and i arguments are used because it behaves differently if we are on the floor
				if (entered[n]!=null && entered[n].is_esc==0){
					System.out.printf("You are not allowed to enter! ");
					entered[n].printprio();
				}
				if (entered[n]!=null && entered[n].is_esc==1){
					System.out.printf("You are not allowed to enter! ");
					Escort etemp= (Escort)entered[n]; 
					etemp.printprio();
				}
			}
			if (f==1){
				if (entered[n]!=null && entered[n].is_esc==0){
					
					System.out.printf("Please wait for outside in office: " + i);
					entered[n].printprio();
				}
				if (entered[n]!=null && entered[n].is_esc==1){
					System.out.printf("You are not allowed to enter! ");
					Escort etemp= (Escort)entered[n]; 
					etemp.printprio();
				}
			}
		}
		
	public	int get_encount(){ //getting how many people are in an entrance
			return ecount;
		}
	
}
