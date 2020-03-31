import java.util.Random;

public class ErgasiaJava {
	
	public static void main(String []args){
		
		
		int mN,mNf,mNg,mNo,mNl,K,L; //the command line arguments 
		

	    	mN = Integer.parseInt(args[0]);	
	        mNf = Integer.parseInt(args[1]);
	        mNg = Integer.parseInt(args[2]);	
	        mNo = Integer.parseInt(args[3]);
			mNl = Integer.parseInt(args[4]);	
			K = Integer.parseInt(args[5]);	
			L = Integer.parseInt(args[6]);		
	   
		
		Visitor[] varr= new Visitor[K]; //varr array will store all the visitors
		
		Random rand1 = new Random();
		Random rand2 = new Random();
		
		int kf=0,ko=0;
		int create_esc=0;//create_esc is the variable that will determine whether an escort will be created or not
		for(int i=0; i<K;i++){ //create the visitors
			if (create_esc==0) { //if we don't have to creat an escort we create a regular visitor
				int f_pr=rand1.nextInt(4)+1;//random office and floor preferences 
				int o_pr=rand2.nextInt(10)+1;
				varr[i]=new Visitor(o_pr,f_pr,0);
				if (varr[i].get_esc()==1) { //when a visitor is created get_esc variable gets a random boolean value  
					create_esc=1; //if it's 1 this means he must have an escort, so the next visitor who will be created will be an escort
					kf=f_pr; //kf and ko are temporal variables that will be used in the creation of the escort, since he must have the same preference as the visitor he follows
					ko=o_pr;
					varr[i].set_esc(1);//making sure the get_esc variable is correct
				}else { //if he doesn't have an escort then the next visitor to be created will be a regular one
					varr[i].set_esc(0);
				}
				if(i==K-1 ) { //if this is the last visitor make sure he won't have an escort (because he can't have one)
					varr[i].set_esc(0);
					create_esc=0;
				}
				continue;//the following if-statement has to be executed in the next loop
			}
			if(create_esc==1) { //previous visitor made the create_esc value to 1, so we have to create an escort
				varr[i]=new Escort(ko,kf);//escort is a class that inherits from visitor, he has the same floor and office preferences with the visitor he follows
				create_esc=0;//a visitor may have one escort at a time, so the next visitor must be a regular one
				continue;
			}
			
			
		}
	
		
		Building Dimosio4Ever =new Building(mN,mNf,mNg,mNo,mNl,K,L); //constructing the building
		Space Sb=Dimosio4Ever; //pointer to the mother 
		
		for(int a=0; a<K;a++ ){ //placing the visitors in the building
			
			if(Sb.getbcount()>=mN){ //if he can't fit print the desired message
				 
				System.out.println("Please, come tomorrow. here");
				continue;
			}
			
			if(Sb.getbcount()==mN-1) { //if a building capacity is at its limits(only room for one more) then place a visitor but only one without an escort
				if(varr[a].has_esc == 0 && varr[a].is_esc==0){ //in my implementation a visitor with an escort must always be at the same space. so if there's only room for one the pair won't enter
					Sb.enter(varr[a]); //however if we have a visitor without an escort there's no need for him to wait
				}
				else {
					System.out.println("Please, come tomorrow."); 
					continue;
				}
			}
			
			if(varr[a].get_isesc()==0) { //if he is not an escort call Building::enter but for visitors
				
			Sb.enter(varr[a]);
	
			}
			
			if(varr[a].get_isesc()==1) { //otherwise call Building::enter for escorts
				Sb.enter((Escort)varr[a]);
				
			}
			
			
		}
		
		Sb.proceed(); //proceed function is used to place the visitors in the Ground Floor
		
		for(int lc=0;lc<L;lc++){ //calling operate function for L times
			
			Sb.operate();
		}	
		
		
	}
	

}

