Compilation command 1: javac Building.java Elevator.java Entrance.java ErgasiaJava.java Escort.java Floor.java GroundFloor.java Office.java Space.java Visitor.java
Compilation command 2: javac *.java
Run command: java ErgasiaJava N Nf Ng No Nl K L

Comments: The program works only for the visitors that managed to fit in the groundfloor.
Regarding the escorts: In my implentation an escort is randomly created after a regular visitor. He inherits from Visitor class, but some certain functions are overriden. 
An escort is always at the same space with the visitor he follows. This means if a visitor can enter a space but he has an escort he will be rejected. However if we have a visitor without an escort he can enter the desired space.
Also for escorts the programs prints the preference of the visitor they follow as well as the fact that they are escorts.
The rest of the program functions similarly to the last one. 