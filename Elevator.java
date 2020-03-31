
public class Elevator extends Space {

	private	int lpos; //the use of these variables will be explained in the function they are used
	private	int elcount; //how many people are in an elevator
	private	int fc; //how many people finished
	private	Visitor[] elevisit; //array that stores all the visitors in an elevator
		
	
		
	public Elevator(int fNl){ //Constructor function
			super(fNl);
		
			System.out.println("A lift has been created!");
			lpos=0;
			elcount=0;
			fc=0;
			
			elevisit= new Visitor[fNl];
			for(int j=0;j<fNl;j++){
				elevisit[j]=null;	
			}
			
		}
		
		
	public	Visitor exit(int n, double e){ //exit function removes the visitor from the n cell 
			Visitor temp;
			temp=elevisit[n];
			elevisit[n]=null;
			
			return temp;
		
		
		}
	
	public	Escort exit(int n, int e){ //overloaded for escorts
		Escort etemp;
		etemp=(Escort)elevisit[n];
		elevisit[n]=null;
		
		return etemp;
	
	
	}
		
		
	public	int empty_all(Floor FGfbe){ //empty all function takes the visitors who finished and places them in the groundfloor
			
			fc=0;
			
			for(int i=0;i<N;i++){
				if(elevisit[i]==null){ //if we stumbled upon an empty position run the loop again
					continue;
				}
				if(elevisit[i].getserved()==1 && elevisit[i].is_esc ==0 ){ //if the visitor who finished is not an escort, call Groundfloor::enter for visitors
					
					Visitor temp = exit(i,0.0);
					elcount--;
					FGfbe.enter(temp,i,0,1);
					fc++;
					continue;
				}
				if(elevisit[i].getserved()==1 && elevisit[i].is_esc ==1 ){ //if the visitor who finished is an escort, call Groundfloor::enter for escorts
					
					Escort etemp = exit(i,0);
					elcount--;
					FGfbe.enter(etemp,i,0,1);
					fc++;
					continue;
				}
			}
			
			return fc; //empty all has to return how many finished
			
			
		}
		
	public	void StopDown(Space SFbe[]){  //stop down function that goes through every floor and takes those that finished
			
			for(int f=0;f<4;f++){ //give served values to those that are in the offices
				SFbe[f].make_served();
			}
			
			boolean bound=false; //bound's use is explained below
			for(int f=3;f>=0;f--){ //since we are stopping down, the loop begins from the last floor
				for(int i=0;i<N;i++){ //for Nl visitors (elevator can't take more than that) (N is used because it is inherited from space class)
					
					if (SFbe[f].getno()== f+1 ){ //just making sure we are on the right floor
						if(elcount == N-1){ //if the elevator has room for one more, remove a visitor without an escort
							
							bound=true; //elevator is on its bounds 
							
							int j=0; 
							for(j=0;j<N;j++){ //find an empty position
								if(elevisit[j]==null){
									break;
								}
							}
							
							Visitor temp=SFbe[f].exit(1,0,bound); //find a served visitor
				
							if(temp!=null){ //if he exists place him in the empty position
								enter(temp,j);
								elcount++;
								continue;
							}
						}	
						
						if(elcount<N && elcount != N-1){ //if there is room for more
							
							bound=false; //elevator is not on its bounds
							
							int j1=0; 
							for(j1=0;j1<N;j1++){ //find an empty position
								if(elevisit[j1]==null){
									break;
								}
							}
							
							Visitor temp1=SFbe[f].exit(0,0,bound); //find a served visitor
							
							if(temp1!=null){ //if he exists place him in the empty position
								enter(temp1,j1);
								elcount++;
								
								if(temp1.has_esc==1){ //if he has an escort, remove his escort
									Escort etemp=SFbe[f].exit(0,0,0.0); //call Floor::exit for escorts
									int k=0; 
									for(k=0;k<N;k++){ //find an empty position
										if(elevisit[k]==null){
										break;
										}
									}
									if (etemp!=null){
										enter(etemp,k);
										elcount++;
									}
									
								}
								continue;
							}
						}
					}
				}
			}
			
	}
			
		
	public	void StopUp(Space SFbe[],int Nfi){  //stop up function that goes through every floor and places the visitors who want to enter it 
			
		
			for(int f=0;f<4;f++){ //for every floor
	
				for(int i=0; i<N; i++){ //for Nl people (N is used because it is inherited from space class)
			
					if (elcount == 0){ //if there is no one on the elevator this process is skipped 
						break;
					}
					if (elevisit[i]==null){  //if we stumbled upon an empty position run the loop again
						continue;
					}
					if ( elevisit[i].getfloor()== f+1 ){ //if this is the floor the visitor wants to go to
						if(SFbe[f].get_flcount()==Nfi-1) { //if the floor has room for one more, place a visitor without an escort
							int pfi=f+1;
							if(elevisit[i].has_esc == 0 && elevisit[i].is_esc==0){  
								if(SFbe[f].get_flcount()<Nfi){ //if he can fit in the floor
									Visitor temp = exit(i,0.0);   //remove him from the elevator
									elcount--;	
						
									System.out.printf("Entering floor number:  " + pfi);
									temp.printprio();
									SFbe[f].enter(temp); //and place him on the floor
									
									continue;
								}
	
							}
							else {
								System.out.printf("Sorry, floor number " + pfi +" is full. ");
								elevisit[i].printprio();
								continue;
							}
						}
						int pf=f+1;
						if(SFbe[f].get_flcount()<Nfi){ //if he can fit in the floor
							if(elevisit[i].is_esc==0) { //if the visitor isn't an escort, call Floor::enter for visitors
								Visitor temp = exit(i,0.0);   //remove him from the elevator
								elcount--;	
				
								System.out.printf("Entering floor number:  " + pf);
								temp.printprio();
								SFbe[f].enter(temp); //and place him on the floor
							
							
								continue; 
							}
							if(elevisit[i].is_esc==1) { //if the visitor is an escort, call Floor::enter for escorts
								Escort etemp = exit(i,0);   //remove him from the elevator
								elcount--;	
						
								System.out.printf("Entering floor number:  " + pf);
								etemp.printprio();
								SFbe[f].enter((Escort)etemp); //and place him on the floor
									
								
								continue;
							
							}
						}
						else{ //if he can't fit print the desired messages
							if(elevisit[i].is_esc==0){ 
								System.out.printf("Sorry, floor number " + pf +" is full. ");
								elevisit[i].printprio();
							}
							if(elevisit[i].is_esc==1) { //if he is an escort we must print the fact that he is an escort as well
								System.out.printf("Sorry, floor number " + pf +" is full. ");
								Escort etemp=(Escort)elevisit[i];
								etemp.printprio();
							}
						}
					
					}
				}
			}
			
		
		}
		
	public	void enter(Visitor fvarr,int i){ //enter function places a visitor in the i cell

				System.out.printf("Visitor in the lift. ");
				elevisit[i]=fvarr;
				elevisit[i].printprio();
			
		}
	
	public	void enter(Escort evarr,int i){ //Overloaded for escorts

		System.out.printf("Visitor in the lift. ");
		elevisit[i]=evarr;
		Escort etemp=(Escort)elevisit[i];
		etemp.printprio();
	
}
		
	public	int operate(Floor FGfbe,Space SFbe[],int Ngi,int Nfi){
			
		
				//Entering stage
				int skip=0;
				
				int spos=FGfbe.getpos(0); //getting the position of the first non-empty position
				for(lpos=spos;lpos<Ngi;lpos++){
					if(elcount<N) {
					if(elcount==N-1) { //if the elevator has room for one more..
						if(skip==1) { //if the visitor followed by an escort couldn't enter print the desired message for the escort
							FGfbe.print_prio(lpos);
							skip=0;
							continue;
						}
						if(FGfbe.checknext(lpos,0,0)==true) { //check if the next visitor has an escort
							
							FGfbe.print_prio(lpos); //if he does he won't enter
							skip=1; //make sure his visitor won't enter either
							continue;
						}
					}
						int j=0;
						for(j=0;j<N;j++){
							if(elevisit[j]==null){ //find an empty position
								break;
							}
						}
						
						if (FGfbe.gethead(lpos,0)==0) { //if the visitor we want to place is not an escort
							
						
						Visitor temp = FGfbe.exit(0,lpos); //remove him from the groundfloor
						
						if(temp!=null){ //if he exists
							FGfbe.dec_counter(); //decrement the counter of those who are on the groundfloor
							enter(temp,j); //and place him in the elevator (with Elevator::enter for visitors) 
						
							elcount++;
							
							continue; //continue statement is used in order to execute the following if-block in the next loop 
						}
						}
						
						if (FGfbe.gethead(lpos,0)==1) { //if the visitor we want to place is an escort
							
							
						Escort temp = FGfbe.exit(0,lpos,0,0,0); //remove him from the groundfloor
						
						if(temp!=null){ //if he exists
							FGfbe.dec_counter(); //decrement the counter of those who are on the groundfloor
							enter(temp,j); //and place him in the elevator (with Elevator::enter for escorts)
						
							elcount++;
						
							continue; //continue statement is used in order to execute the following if-block in the next loop 
						}
						}
					}
						if(elcount==N){ //if the counter is equal to the capacity this means we can't take any more visitors

							FGfbe.print_prio(lpos); //and print the desired messages
							

						}
						
						
					}
					
					
				
			//entering stage is over, we may call the remaining functions
			StopUp(SFbe,Nfi); 
			
			StopDown(SFbe);
			
			return empty_all(FGfbe);
			
			
		
	}
	
}
