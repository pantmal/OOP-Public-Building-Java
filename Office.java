import java.util.Random;

public class Office extends Space {

	
	private	int oc;	//oc member is the number of the office
	private	int ofcount; //how many people are in an office
	private	Visitor[] offvisit; //array that stores all the visitors in an office
		
	public Office(int fNo,int foc){//Constructor function
			super(fNo); 
			this.oc=foc;
		
			System.out.println("Office number " + this.oc + " has been created");
			
			offvisit= new Visitor[fNo];
			for(int j=0;j<fNo;j++){
				offvisit[j]=null;
			}
			
			ofcount=0;
			
		}
	
	
	public void enter(Visitor fvarr){ //Office enter takes a visitor and places him in the first empty cell it finds
		
		int i=0;
		for(i=0;i<N;i++){
			if(offvisit[i]==null){
				break;
			}
		}
		
		offvisit[i]=fvarr;
		ofcount++;
			
	}
	
	public void enter(Escort fvarr){ //Office enter takes a visitor and places him in the first empty cell it finds
		
		int i=0;
		for(i=0;i<N;i++){
			if(offvisit[i]==null){
				break;
			}
		}
		
		offvisit[i]=fvarr;
		ofcount++;
		
	}
	
	
	Visitor exit(){ //Office exit removes the first visitor (who was served) from the array  
		
		Visitor temp=null;
		int i=0;
		for(i=0;i<N;i++){
			if(offvisit[i]!=null && offvisit[i].getserved()==1 ){
				temp=offvisit[i];
				offvisit[i]=null;
				ofcount--;
				break;
			}
		}
		
		
		return temp;	

	}
	
	Visitor exitlimit(){ //Exitlimit is variation because in some cases we have to remove a visitor without an escort
		
		Visitor temp=null;
		int i=0;
		for(i=0;i<N;i++){
			if(offvisit[i]!=null && offvisit[i].getserved()==1 && offvisit[i].get_esc()==0 && offvisit[i].get_isesc()==0 ){
				temp=offvisit[i];
				offvisit[i]=null;
				ofcount--;
				break;
			}
		}
		
		
		return temp;	

	}
	
	
	
	Escort exit(int e){ //Overloaded for escorts 
		
		Escort temp=null;
		int i=0;
		for(i=0;i<N;i++){
			if(offvisit[i]!=null && offvisit[i].getserved()==1 && offvisit[i].is_esc==1 ){
				temp=(Escort)offvisit[i];
				offvisit[i]=null;
				ofcount--;
				break;
			}
		}
		
		return temp;	

	}
	
	void make_served(){ //this function sets a visitor's served value to either 0 or 1
		

		
		int n1=0;
		for(int i=0;i<N;i++){
			if(offvisit[i]==null) {
				continue;
			}
			if(offvisit[i]!=null){
				if (offvisit[i].getserved()!=1){ //if the visitor is already served don't reset his value
					if(offvisit[i].has_esc==0){ //if he doesn't have an escort simply set his value
					Random rand = new Random();
					int n = rand.nextInt(2);
					offvisit[i].setserved(n);
					}
					if(offvisit[i].is_esc==0 && offvisit[i].has_esc==1){ //if he has an escort keep the value that rand() produced so it will be passed to the escort
						Random rand = new Random();
						 n1 =rand.nextInt(2);
						offvisit[i].setserved(n1);
					
					}
					if(offvisit[i].is_esc==1){ //if we have an escort make him take the value of the visitor he follows
				
						Escort etemp = (Escort)offvisit[i];
						etemp.setserved(n1);
						
					}
					
				}
			}
		}
		
		

	}	
	
	
	int get_ofcount(){ //getting how many people are in an office
		return ofcount;
	}
	
	int findparent(int prio) {//findparent is used because in some cases we need to check the priority of the visitor who is followed by an escort, more on that later..
		
		int bingo=0;
		int i;
		for(i=0;i<N;i++){
			if(offvisit[i]!=null && offvisit[i].getprio()==prio ){
				bingo=1;
				break;
			}
		}
		return bingo;
	}
	
}
