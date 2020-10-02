package connect4;
/**
 *
 * Author: Kalp Shah
 * Date: August 25, 2020
 * This is the Player class 
 */
public class Player {
    
    //Creating variables for all of the attributes for a player object
    private String strName;
    public int intWinsC;
    public int intLosesC;
    public int intTiesC;
    public int intWinsR;
    public int intLosesR;
    public int intTiesR;
    public int intSolitaireHS;
    
    /**
     * 
     * @param strN is the name of the player
     * @param intWinC is the number of wins the player has in the classic game mode
     * @param intLoseC is the number of losses the player has in the classic game mode
     * @param intTieC is the number of ties the player has in the solo game mode
     * @param intWinR is the number of wins the player has in the reverse game mode
     * @param intLoseR is the number of losses the player has in the reverse game mode
     * @param intTieR is the number of ties the player has in the solo game mode
     * @param intHS is the highest score that the player has in the solo game mode
     */
    public Player (String strN, int intWinC, int intLoseC, int intTieC, int intWinR, int intLoseR, int intTieR, int intHS) {
        //Set the variables to the variables that have been passed to the constructor
        strName = strN;
        intWinsC = intWinC;
        intLosesC = intLoseC;
        intTiesC = intTieC;
        intWinsR = intWinR;
        intLosesR = intLoseR;
        intTiesR = intTieR;
        intSolitaireHS = intHS;
    }
    
    /**
     * 
     * @return the name of the player 
     */
    public String GetName() {
        //Return the name of the player 
        return strName;
    }
    
    /**
     * 
     * @return the overall percentage of games that the player has won in the classic game mode
     */
    public Integer GetCStats() {
        //If the number of games played by the player is not 0
        if ((intWinsC + intLosesC + intTiesC) != 0) {
            //Return an integer that is the percentage of games won by the player
            return ((intWinsC * 100) / (intWinsC + intLosesC + intTiesC));
        }
        //Return 0
        return 0;
    }
    
    /**
     * 
     * @return the overall percentage of games that the player has won in the reverse game mode
     */
    public Integer GetRStats() {
        //If the number of games played by the player is not 0
        if ((intWinsR + intLosesR + intTiesR) != 0) {
            //Return an integer that is the percentage of games won by the player
            return ((intWinsR * 100) / (intWinsR + intLosesR + intTiesR));
        }
        //Return 0
        return 0;
    }
    
    /**
     * 
     * @param intScore is the score that the player has just received in the solo game mode
     * @return whether or not a change to the high score was made 
     */
    public boolean UpdateHS (int intScore) {
        //If the new score is greater than the players previous high score
        if (intScore > intSolitaireHS) {
            //Set the players high score to the new score achieved
            intSolitaireHS = intScore;
            //Return true
            return true;
        }
        //Return false
        return false;
    }
}
