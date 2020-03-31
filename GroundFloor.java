
public class GroundFloor extends Floor {

	private	Entrance Eg; //groundfloor's entrance
	private	Space Seg; //pointer to the mother
	private	Visitor[]  groundvisit; //array that stores all the visitors in a ground floor 
	private	Visitor[]  finished; //array that stores all the visitors who finished their work from their offices
	private	int gcount; //how many people are in a groundfloor
		
	public GroundFloor(int fNg){  //Constructor function
			super(fNg,0,0);
			
			System.out.println("A Ground Floor has been created " );
			
			Eg=new Entrance(fNg);
			
			groundvisit= new Visitor[fNg];
			for(int j=0;j<fNg;j++){
				groundvisit[j]=null;
			}
			
			finished= new Visitor[fNg];
			for(int k=0;k<fNg;k++){
				finished[k]=null;
			}
			
			Seg=Eg;
			
			gcount=0;
			
		}
	
	public int gethead(int p, int g) { //getting whether the visitor is an escort or not
		return Seg.gethead(p);
	}
	
	public boolean checknext(int pos, int g, int gg) { //getting whether the visitor has an escort
		return Seg.checknext(pos);
	}
	
	public int getpos(int g) { //getting the first non-empty position
		return Seg.getpos();
	}
		
	public	void wait(Visitor fvarr ,int a, int prio){ //wait function gives a visitor his priority and places him inside groundfloor's entrance
			 
		if(fvarr.get_isesc()==0) { //if the visitor isn't an escort set his priority regularly
			
			fvarr.setprio(prio);

			 Seg.enter(fvarr);
		}
		if(fvarr.get_isesc()==1) {//if he is an escort get the priority of the visitor he follows
			Escort temp = (Escort)fvarr;
			 temp.setprio(prio);
			 Seg.enter(temp);
		}
		
		 System.out.println("Waiting for the lift.");
		 
		}

	public void proceed(int g) { //proceed functions places the visitors in the entrance
		
		int prio =0;
		for(int i=0;i<gcount;i++){ //call the wait function for those that fit in the groundfloor
			if(groundvisit[i]!= null && groundvisit[i].is_esc==0 ) { 
				prio++; //priority is incremented only if the visitor about to enter is a non-escort, otherwise he will get the priority of the previous one
			}
			wait(groundvisit[i],i,prio);
			
		}
		
}
		
		
	public	void enter(Visitor fvarr, int n, int b, int r ){ //enter function to place the visitors inside the entrance and forbid from entering those who can't fit
		
			if(r==0){   //if the visitor goes from the building to the groundfloor
				if(gcount<N){ //if he can fit put him in a array
					int i=0;
					for(i=0;i<N;i++) {
						if(groundvisit[i]==null) {
							break;
						}
					}
					groundvisit[i]=fvarr;
					gcount++;
					
				}
				
			}
			
			if(r==1){ //if the visitor is returning from the elevator to the groundfloor place him in a different array
					  
				int i=0; 
				for(i=0;i<N;i++){
					if(finished[i]==null){
						break;
					}
				}
				finished[i]=fvarr;
				
			}
			
		}
		
	public	void enter(Escort fvarr, int n, int b, int r ){ //Overloaded for escorts
		
		if(r==0){   //if the visitor goes from the building to the groundfloor
			if(gcount<N){ //if he can fit put him in a array
				int i=0;
				for(i=0;i<N;i++) {
					if(groundvisit[i]==null) {
						break;
					}
				}
				groundvisit[i]=fvarr;
				gcount++;
				
			}
			
		}
		if(r==1){ //if the visitor is returning from the elevator to the groundfloor place him in a different array
			  
			int i=0; 
			for(i=0;i<N;i++){
				if(finished[i]==null){
					break;
				}
			}
			finished[i]=fvarr;
			
		}
	}
			
	public	Visitor exit(int r, int p){ //exit function removes a visitor 
			
			Visitor temp=null;
			if (r==0){ //if the visitor goes from the groundfloor to the elevator remove him from the groundfloor's entrance
				return	Seg.exit(0,0,p);
				
			}

			if(r==1){  //if the visitor goes from the groundfloor to the building (when he has finished, that is) remove him from the finished array
				
				
				int i=0;
				for(i=0;i<N;i++){
					if(finished[i]!=null  ){
						if( finished[i].is_esc==0) {
							temp=finished[i];
							finished[i]=null;
						break;
						}
					}
				}
				
				
			
				return temp;
			}	
			return temp;
		
		}
	
	public Escort exit(int r, int p, int e, int g, int gg) { //overloaded for escorts
		Escort temp=null;
		if (r==0){ //if the visitor goes from the groundfloor to the elevator remove him from the groundfloor's entrance
			return	Seg.exit(0,0,p,0);
			
		}
		if(r==1){  //if the visitor goes from the groundfloor to the building (when he has finished, that is) remove him from the finished array
			
			
			int i=0;
			for(i=0;i<N;i++){
				if(finished[i]!=null ){
					if (finished[i].is_esc==1) {
						temp=(Escort)finished[i];
						finished[i]=null;
					break;
					}
				}
			}
			
			
		
			return temp;
		}	
		return temp;
	}
		
	public	void print_prio(int n){ //function to print visitor's priority 
			Seg.print_prio(n,0,0); //(this is used in the elevator's entering stage so the visitor won't have to leave the entrance only to enter it again)
		}
		
	public	int get_gcount(){ //getting how many people are in a groundfloor
			return this.gcount;
		}		
			
	public	void dec_counter(){ //function to decrement groundfloor's counter
			this.gcount--;
		}
	
}
