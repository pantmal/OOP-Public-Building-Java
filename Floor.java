
public class Floor extends Space{

	private	int FNo; //office's capacity
    private	int fc; //fc member is floor's number
    private	int fcount; //how many people are in a floor
    private	Entrance Ef; //floor's entrance 
    private	Space Sef; //pointer to the mother
    private	Office[] Of = new Office[10];//every floor has 10 offices
    private	Space[] Sof = new Space [10]; //pointer array to the mothers


	
	public	Floor(int fNf, int fFNo, int ffc){ //Constructor function
			super(fNf);
			FNo=fFNo;
			fc=ffc;
			if(this.fc!=0){
				System.out.println("A Floor has been created with number:" + this.fc);
			}
			
			
			if(this.FNo!=0){ //don't create offices and entrance if office capacity is zero (only in groundfloor)
				Ef= new Entrance(fNf);
				Sef=Ef;	
				
				for(int i=0;i<10;i++){
					Of[i]= new Office(fFNo,i+1);
				}
				for(int i=0;i<10;i++){
					Sof[i]= Of[i];
				}
			}
			
			fcount=0;
			
		}
		
	public	int getno(){ //getting floor's number
			return this.fc;
		}
		
	
	public	void enter(Visitor fvarr){ //floor enter places a visitor in floor's entrance then proceeds to the entering stage of the office
			
			Sef.enter(fvarr);
			fcount++;
		
			for(int i=0;i<10;i++){ //for every office
				if(Sef.getoff(i+1)) {
					if(Sof[i].get_ofcount() == this.FNo-1 && fvarr.has_esc==1) { //if the office has room for one more place a visitor wihout an escort
						int po=i+1;
						System.out.printf("Please wait for outside in office: "+ po +" ");
						fvarr.printprio();
						break;
					}
				}
				if(Sof[i].get_ofcount() < this.FNo){ //if the visitor fits
					if(Sef.getoff(i+1)){ //and if this is the office he wants to go to
						
				
						Visitor temp = Sef.exit(1,i+1,0); //remove him from the entrance
				
						Sof[i].enter(temp); //and place him in the office
					
						int po=i+1;
						System.out.printf("Entering office number: " + po);
						temp.printprio();
				
						break; //only one can enter at a time so if one person went in no need to continue the loop
					}
				}
				else{ //print the desired messages if he couldn't fit
					int po=i+1;
					System.out.printf("Please wait for outside in office: "+ po +" ");
					fvarr.printprio(); 
					break;
				}
			}
			
		}
	
	public	void enter(Escort fvarr){ //Overloaded for escorts
		
		Sef.enter(fvarr);
		fcount++;
		
		
		for(int i=0;i<10;i++){ //for every office
			if(Sef.getoff(i+1)) {
				if(Sof[i].get_ofcount() == this.FNo-1 && Sof[i].findparent(fvarr.getprio())!=1) { //if the visitor he follows is not in the office don't enter
					int po=i+1;
					System.out.printf("Please wait for outside in office: "+ po +" ");
					fvarr.printprio();
					break;
				}
			}
			if(Sof[i].get_ofcount() < this.FNo){ //if the visitor fits
				if(Sef.getoff(i+1)){ //and if this is the office he wants to go to
					
			
					Escort temp = Sef.exit(1,i+1,0,1); //remove him from the entrance
			
					Sof[i].enter(temp); //and place him in the office
				
					int po=i+1;
					System.out.printf("Entering office number: " + po);
					temp.printprio();
			
					break; //only one can enter at a time so if one person went in no need to continue the loop
				}
			}
			else{ //print the desired messages if he couldn't fit
				int po=i+1;
				System.out.printf("Please wait for outside in office: "+ po +" ");
				fvarr.printprio(); 
				break;
			}
		}
		
	}
			
	public	Visitor exit(int a, int b, boolean l){ //floor exit function removes the first visitor that was served and also proceeds to enter those that wait in the entrance 
		
		Visitor oftemp=null;
		Visitor retemp=null;
		
		if(l==true) { //if the elevator is at limits remove a visitor without an escort
			for(int i=0;i<10;i++){ //for every floor
				if(Sof[i].get_ofcount()!=0){ //if there is at least one person inside
					
					if((retemp=Sof[i].exitlimit())!=null){ //if we managed to find someone
						
						oftemp=retemp; //place him temporarily in a variable
						fcount--;
						for(int j=0;j<Sef.get_encount();j++){ //now check for every person in the entrance if he can go in the office
							if(Sef.getoff(i+1)) {
								if(Sof[i].get_ofcount() == this.FNo-1 && Sef.checknext(0,0)) {
							
									Sef.print_prio(j,1,i+1);
									break;
								}
							}
							if(Sof[i].get_ofcount()<FNo){ // if office count is less than its capacity 
						
								if(Sef.getoff(i+1)){ //if we find someone that wants this office
									Visitor temp; 
									temp=Sef.exit(1,i+1,a); //remove him from the entrance
									Sof[i].enter(temp); //and place him in that office
									int po=i+1;
									System.out.printf("Entering office number: "+ po);
									temp.printprio();
								}
							}
							else{ //for those that couldn't enter yet again..
								Sef.print_prio(j,1,i+1); 
								
							}
						}
					break; //we only return one visitor at a time, so if we found somebody break the loop
					}
					
				}	
			}
			
		}
		else{  
		for(int i=0;i<10;i++){ //for every floor
			if(Sof[i].get_ofcount()!=0){ //if there is at least one person inside
				
				if((retemp=Sof[i].exit())!=null){ //if we managed to find someone
					
					oftemp=retemp; //place him temporarily in a variable
					fcount--;
					for(int j=0;j<Sef.get_encount();j++){ //now check for every person in the entrance if he can go in the office
						if(Sef.getoff(i+1)) {
							if(Sof[i].get_ofcount() == this.FNo-1 && Sef.checknext(0,0)) {
						
								Sef.print_prio(j,1,i+1);
								break;
							}
						}
						if(Sof[i].get_ofcount()<FNo){ // if office count is less than its capacity 
					
							if(Sef.getoff(i+1)){ //if we find someone that wants this office
								Visitor temp; 
								temp=Sef.exit(1,i+1,a); //remove him from the entrance
								Sof[i].enter(temp); //and place him in that office
								int po=i+1;
								System.out.printf("Entering office number: "+ po);
								temp.printprio();
							}
						}
						else{ //for those that couldn't enter yet again..
							Sef.print_prio(j,1,i+1); 
							
						}
					}
				break; //we only return one visitor at a time, so if we found somebody break the loop
				}
				
			}	
		}
		}
		return oftemp; //return the visitor 
		
	}
	
	public	Escort exit(int a, int b,double e){ //Overloaded for escorts
		
		Escort oftemp=null;
		Escort retemp=null;
		for(int i=0;i<10;i++){ //for every floor
			if(Sof[i].get_ofcount()!=0){ //if there is at least one person inside
				
				if((retemp=Sof[i].exit(0))!=null){ //if we managed to find someone
				
					oftemp=retemp; //place him temporarily in a variable
					fcount--;
					for(int j=0;j<Sef.get_encount();j++){ //now check for every person in the entrance if he can go in the office
						if(Sef.getoff(i+1)) {
							
						if(Sof[i].get_ofcount()<FNo){ // if office count is less than its capacity 
					
							if(Sef.getoff(i+1)){ //if we find someone that wants this office
								Escort temp; 
								temp=Sef.exit(1,i+1,0,a); //remove him from the entrance
								Sof[i].enter(temp); //and place him in that office
								int po=i+1;
								System.out.printf("Entering office number: "+ po);
								temp.printprio();
							}
						}
						else{ //for those that couldn't enter yet again..
							Sef.print_prio(j,1,i+1); 
							
						}
						}
					}
				break; //we only return one visitor at a time, so if we found somebody break the loop
				}
				
			}	
		}
		
		return oftemp; //return the visitor 
		
	}
			
	public	int get_flcount(){ //getting how many people are in a floor
			return this.fcount;
		}
	
		
		
	public	void make_served(){ //this function will go at every office that has at least one person inside and give him a served value(either 0 or 1)
		
			for (int i=0; i<10; i++){
				if(Sof[i].get_ofcount()!=0){
					Sof[i].make_served();			
				}
			}
			
		}
		
	//GroundFloor has to inherit those functions
		int gethead(int a, int b) {int x=0; return x;}
		boolean checknext(int pos, int g, int gg) {boolean b=false; return b;}
		int getpos(int g) {int x=0; return x;}
		void print_prio(int a){} 
		int get_gcount(){int x=0; return x;}
		void enter(Visitor v, int a, int b, int c){}
		void enter(Escort e, int a, int b, int c){}
		Visitor exit(int a, int p){Visitor v=null; return v;}
		Escort exit(int a, int p, int e, int g, int gg){Escort v=null; return v;}
		void wait(Visitor v, int a, int p){}
		void dec_counter(){}
		void proceed(int g) {}
	
}
