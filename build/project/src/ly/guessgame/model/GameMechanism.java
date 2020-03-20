package ly.guessgame.model;

import java.util.Random;


public class GameMechanism {

	static private int RandomNumbers[] = new int[10];//for the sake of valueofguess,you can make a getter and setter i guess
	static private int Guess;
	static private int numGuesses=0;
	static private int Counter=0;

	public void GeneratedNumbers() {
		for(int i=0; i<=9;i++) {
			RandomNumbers[i]= new Random().nextInt(10)+1;
		}
	}
	public static int[] getRandomNumbers() {
		return RandomNumbers;
	}
	public static void setRandomNumbers(int[] randomNumbers) {
		RandomNumbers = randomNumbers;
	}
	/*I didn't make a single comment thus far but here's the first, finally your program works as expected, you had a 
	 * problem with multiple new windows coming after pressing the button, you solved that by making the below method
	 * static which also made me make everything else static,but that's good and I should pay more attention to this
	 * because I'm only calculating once why would I need ten different objects,having this method and the associated 
	 * variables static should be my standard. p.s. : don't make you window object static, makes problems leave it
	 * there local at display(), I have now dealt with the exceptions, not fancy dealing but makes up with no 
	 * crashing, I'll see if I can build on from this, and then after I've acquired the necessary level of dealing with
	 * swing GUI I should go and make my Age Calculator GUI, then maybe go ahead and learn more about GUI and window 
	 * builder and most importantly JavaFX, and then Maven and other stuff and make my code more professional by making
	 * more responsible comments and lastly JavaDOC.
	 * another notice, I hope I don't forget how this program works since it has a lot of in and out between each
	 * class.*/
	static public void CheckUserNumbers(String EnteredValue) {
		try {
			Guess = Integer.parseInt(EnteredValue);
			if(Guess == RandomNumbers[numGuesses]) {
				Counter++;
			}
			numGuesses++;
		}catch (NumberFormatException |  ArrayIndexOutOfBoundsException e) {

		}
	}

	public int getNumGuesses() {
		return numGuesses;
	}
	public int getCounter() {
		return Counter;
	}
	public static void setNumGuesses(int numGuesses) {
		GameMechanism.numGuesses = numGuesses;
	}
	public static void setCounter(int counter) {
		Counter = counter;
	}
		
}
