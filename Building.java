
public class Building extends Space {

	
	private	int Nf,Ng,No,Nl,K,L; //capacities 
	private	GroundFloor Gfb; //Building's groundfloor
	private	Floor Fg;   //Pointer to groundfloor's mother
	private	Elevator Eb; //Building's elevator
	private	Floor[] Fb = new Floor[4];  //A building has 4 Floors
	private	Space[] Sfb = new Space[4]; //pointer array to the mothers
	private	Visitor[] buildvisit; //array that stores all the visitors in a building
	
	private	int bcount; //how many people are in a building
	private	int fc; //fc is how many people finished their work in their offices. when we reach the exiting stage, we will run a loop for fc people.
		
	public Building(int fN, int fNf, int fNg, int fNo, int fNl, int fK, int fL ){ //Constructor function
			super(fN);
			Nf=fNf;
			Ng=fNg;
			No=fNo;
			Nl=fNl;
			K=fK;
			L=fL;
			
		System.out.println("A new building is ready for serving citizens!");
			
			Gfb= new GroundFloor(fNg);
			Eb= new Elevator(fNl);
			for(int i=0; i<4;i++){
				Fb[i]= new Floor(fNf,fNo,i+1);
			}
			for(int i=0; i<4;i++){
				Sfb[i]= Fb[i];
			}

			buildvisit=new Visitor[fN];
			for(int j=0;j<fN;j++){
				buildvisit[j]=null;
			}
			
			bcount=0;
			fc=0;
			
			Fg=Gfb;
			
			
		}
	
	public void proceed() { //proceed function places the visitors in the groundfloor
		 //if the counter is equal to the visitor number then we did all the necessary work for every visitor
		
			for(int i=0;i<bcount;i++){ //now for those that managed to fit, place them in the groundfloor 
				
				if(Fg.get_gcount()>=Ng) { //if he can't fit print the desired message
					System.out.println("Sorry, can't fit in the ground floor.");
					continue;
				}
				
				if(Fg.get_gcount()==Ng-1) { //if groundfloor has room for one more, place a visitor without an escort
					if(buildvisit[i].has_esc == 0 && buildvisit[i].is_esc==0){
						Fg.enter(buildvisit[i],i,bcount,0);
					}
					else {
						System.out.println("Sorry, can't fit in the ground floor.");
						continue;
					}
				}
				
				if(buildvisit[i].is_esc==0) { //if he is not an escort call GroundFloor::enter for visitors
					Fg.enter(buildvisit[i],i,bcount,0);
				}
				
				if(buildvisit[i].is_esc==1) { //if he is an escort call GroundFloor::enter for escorts
					Fg.enter((Escort)buildvisit[i],i,bcount,0);
				}
				
			}
			
				Fg.proceed(0); //place the visitors in the entrance now

		
	}
	
	public	void exit(Visitor fvarr){ //exit function prints the desired messages 
			
				System.out.printf("I cannot believe I finished! ");
				fvarr.printprio();
			
	}
	
	public	void exit(Escort fvarr){ //Overloaded for escorts
		
		System.out.printf("I cannot believe I finished! ");
		fvarr.printprio();
	
	}
		
	public	void operate(){ //operate is used to call elevator's operate and remove those that finished
			
		
			fc=Eb.operate(Fg,Sfb,Ng,Nf); //elevator operate will return(through empty_all function) how many finished 
			
			for(int i=0; i<fc;i++){ //removing those that finished
				
				Visitor temp=Fg.exit(1,0); //calling GroundFloor::exit for visitors
				if(temp==null) {
					continue;
				}
				
				exit(temp);
				
				Escort etemp=Fg.exit(1,1,1,0,0); //calling GroundFloor::exit for visitors
				if(etemp==null) {
					continue;
				}
				exit(etemp);
				
				
				
			}
			
		}
		
	public	void enter(Visitor fvarr){ //enter function to place the visitors inside the building and forbid from entering those who can't fit
		
				
				if(bcount<N){ //if he can fit put him in a array
					int i=0;
					for(i=0;i<N;i++) {
						if(buildvisit[i]==null) {
							break;
						}
					}
					buildvisit[i]=fvarr;
					bcount++;
				
				}
				
			
		}
	
	public	void enter(Escort evarr){ //Overloaded for escorts
		
		
		if(bcount<N){ //if he can fit put him in a array
			int i=0;
			for(i=0;i<N;i++) {
				if(buildvisit[i]==null) {
					break;
				}
			}
			buildvisit[i]=evarr;
			bcount++;
		
		}
		
		
	}
	
	public int getbcount() { //getting how many visitors are in a building
		return bcount;
	}
	
}
