package connect4;
/**
 *
 * Author: Kalp Shah
 * Date: August 25, 2020
 * This is the Game Manager interface that is to be implemented in the main Connect4 class
 */
public interface GameManager {
    
    //Methods that must be present in the classes that implement this interface 
    public void ReadDataBase();
    public void AddDataBase(String strUserName);
    public void SaveDataBase();
    public Player FindPlayer(String strName, int intMin, int intMax);
    
}
