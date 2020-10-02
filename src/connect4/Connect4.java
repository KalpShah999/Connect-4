package connect4;
import javax.swing.*;
import java.util.ArrayList;
import java.io.*;
/**
 *
 * Author: Kalp Shah
 * Date: August 25, 2020
 * This program starts the Connect 4 game 
 */
public class Connect4 implements GameManager {
    
    //These following variables have been declared static so that there is only once instance of these varibles that can be accesed 
    //from all of the scripts 
    //A player arraylist that stores all of the players that are registered to the game 
    public static ArrayList<Player> playerList = new ArrayList<Player>();
    //A player variable that stores the currently logged in player 1 
    public static Player player1;
    //A player variable that stores the currently logged in player 2 
    public static Player player2;
    
    /**
     * This method reads the external file and adds the information there into the playerList as players 
     */
    public void ReadDataBase() {
        //Try to open the file 
        try {
            //Clear the playerList to make sure that it is empty 
            playerList.clear();
            //Open a FileReader and get the PlayerDatabase file
            FileReader fr = new FileReader("PlayerDatabase.txt");
            //Create a buffered reader that reads the file from the file reader 
            BufferedReader br = new BufferedReader(fr);
            //Set the first line in the buffered reader as str1
            String str1 = br.readLine();
            //While loop that keeps running as long as the next line is not empty 
            while (str1 != null) {
                //7 integer variables that read the next 7 lines in the file and converts them into ints 
                int int1 = Integer.parseInt(br.readLine());
                int int2 = Integer.parseInt(br.readLine());
                int int3 = Integer.parseInt(br.readLine());
                int int4 = Integer.parseInt(br.readLine());
                int int5 = Integer.parseInt(br.readLine());
                int int6 = Integer.parseInt(br.readLine());
                int int7 = Integer.parseInt(br.readLine());
                //Creating a player object with all of the information from the external file and adding the player to the playerList
                playerList.add(new Player(str1, int1, int2, int3, int4, int5, int6, int7));
                //Update the str1 variable as the next line in the file 
                str1 = br.readLine();
            }
            //Close the buffered reader
            br.close();
            //Sort the players in terms of their username in the playerlist using the PlayerSort method 
            SortPlayers();
        //Catch the IOException if there is no file 
        } catch (IOException e) {}
    }
    
    /**
     * 
     * @param strUserName if the name of the player that we want to create and add to the external file 
     */
    public void AddDataBase (String strUserName) {
        //Add the new player to the playerlist with the username and all stats at 0
        playerList.add(new Player(strUserName, 0, 0, 0, 0, 0, 0, 0));
        //Save the playerlist to the external file using the SaveDataBase method 
        SaveDataBase();
    }
    
    /**
     * This method saves all of the data from the player list into the external file 
     */
    public void SaveDataBase() {
        //Try to open the external file 
        try {
            //Open a FileWriter and get the PlayerDatabase file
            FileWriter fw = new FileWriter("PlayerDatabase.txt");
            PrintWriter pw = new PrintWriter(fw);
            //Write a blank to reset the text file 
            pw.write("");
            //For loop that runs through for every player in the playerlist 
            for (int i = 0; i < playerList.size(); i++) {
                //Set the plaeyr from the playerlist to a player variable p
                Player p = playerList.get(i);
                //Append all of the information from the playerlist to the external file 
                pw.append(p.GetName() + "\n" + p.intWinsC + "\n" + p.intLosesC + "\n" + p.intTiesC + "\n" + p.intWinsR 
                        + "\n" + p.intLosesR + "\n" + p.intTiesR + "\n" + p.intSolitaireHS + "\n");
            }
            //Close the print writer
            pw.close();
        //Catch the IOException if there is no file 
        } catch (IOException e) {}
    }
    
    /**
     * This method sorts the players according to their name using Bubble Sort
     */
    public void SortPlayers() {
        //For loop that repeats for the number of players in the playerList 
        for (int i = 0; i < playerList.size(); i++) {
            //For each player in the playerrList that needs to be examined except the last 
            for (int k = 0; k < playerList.size() - 1; k++) {
                //Check if the name of the player next in the list if less than current players name (we want ascending)
                if (playerList.get(k).GetName().compareTo(playerList.get(k + 1).GetName()) > 0) {
                    //Store the player as a temporary player
                    Player temp = playerList.get(k);
                    //Set the player at k to the player at k + 1
                    playerList.set(k, playerList.get(k + 1));
                    //Set the player at k + 1 to the temporary player
                    playerList.set(k + 1, temp);
                }
            }
        } 
    }
    
    
    /**
     * Binary search method that finds the requested player from the playerList 
     * @param strName if the username of the player that we are looking for 
     * @param intMin is the minimum index of the window that we are examining 
     * @param intMax is the maximum index of the window that we are examining 
     * @return the player that has the username that is being looked for 
     */
    public Player FindPlayer (String strName, int intMin, int intMax) {
        //Create a mid value that represents the middle of the examined window
        int intMid = (intMin + intMax) / 2;
        
        //If the int min is less than or equal to the int max
        if (intMin <= intMax) {
            //Set the player at the mid points' username to be equal to a new string variable strUserName
            String strUserName = playerList.get(intMid).GetName();
            //if the found username is equal to the username that is being looked for 
            if (strUserName.equals(strName)) {
                //Return the player 
                return playerList.get(intMid);
            //Else if it is less than the target username 
            } else if (strUserName.compareTo(strName) < 0) {
                //Repeat this method but with the mid + 1 as the new minimum 
                return FindPlayer(strName, intMid + 1, intMax);
            //Else if it is greater than the target username 
            } else {
                //Repeat this method but with the mid - 1 as the new maximum
                return FindPlayer(strName, intMin, intMid - 1);
            }
        }
        //If the player is not found, return null
        return null;
    }
    
    public static void main (String[] arguments) {
        //Open a login winodow 
        LoginWindow loginWindow = new LoginWindow();
        loginWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginWindow.setVisible(true);
    }
}
