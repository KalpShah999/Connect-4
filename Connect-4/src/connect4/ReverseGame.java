package connect4;
/**
 *
 * Author: Kalp Shah
 * Date: August 25, 2020
 * This program runs the main reverse game mode for Connect 4 
 */
public class ReverseGame extends ClassicGame {
    
    /**
     * 
     * @param intO is the number of moves made during the game 
     */
    public void EndGame(int intO) {
        //Increase the number that is being passed into the EndGame function by 1 in order to let the game know that the results
        //of the game need to be reversed (if the final move was made by player 1, then player 2 actually wins and vice versa)
        super.EndGame(intO + 1);
    }
    
    public ReverseGame(String strName) {
        //Use the parent constructor and pass through the name that was recieved in order to set the title of the generated frame 
        super(strName);
    }
}
