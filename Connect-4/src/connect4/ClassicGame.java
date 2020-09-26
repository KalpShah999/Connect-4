package connect4;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
/**
 *
 * Author: Kalp Shah
 * Date: August 25, 2020
 * This program runs the main classic game mode for Connect 4 
 */
public class ClassicGame extends Game implements ItemListener, ActionListener, Runnable {
    
    //Create a new Connect4 instance in order to be able to acces the information of the logged in players 1 and 2 
    Connect4 main = new  Connect4();
    //Create a new boolean variable that represents if the game is over 
    boolean isGameOver = false;
    //To make the game easier to manage, this ArrayList is to be considered backwards. The first outer ArrayList represents the 
    //columns rather than the rows, and the inner ArrayList represents the rows rather than the columns. This is because when
    //placing pieces on the board, we will be looking for which column the player has placed in and then determing the row afterwards 
    ArrayList<ArrayList<Integer>> piecesList = new ArrayList<ArrayList<Integer>>();

    /**
     * 
     * @param event is the event that is being passed to this script 
     */
    public void actionPerformed (ActionEvent event) {
        //Store the Action event command as a string in a new String variable called strCommand
        String strCommand = event.getActionCommand();
        
        //Try function that tries to convert the strCommand into an Integer 
        try {
            //Convert the strCommand into an Integer and get the remainder of it when it is divided by 7
            //This will allow us to determine which column it is in 
            int intCommand = Integer.parseInt(strCommand) % 7;
            //Check the piecesList to make sure that the entire column is not empty yet 
            if (piecesList.get(intCommand).size() < 7) {
                //If the number of moves is even then player 2 has just gone and it is player 1's turn 
                if (super.movesMade % 2 == 0) {
                    //Set the first avaible box in that column to red to represent a player 1 counter
                    super.boxes[5 - piecesList.get(intCommand).size()][intCommand].setIcon(super.red);
                    //Add a 0 to that column within the piecesList to increase its size and show that it contains a new counter
                    piecesList.get(intCommand).add(0);
                //Else if the number of moves is off then player 1 has just gone and it is player 2's turn 
                } else if (super.movesMade % 2 == 1) {
                    //Set the first avaible box in that column to yellow to represent a player 2 counter
                    super.boxes[5 - piecesList.get(intCommand).size()][intCommand].setIcon(super.yellow);
                    //Add a 0 to that column within the piecesList to increase its size and show that it contains a new counter
                    piecesList.get(intCommand).add(0);
                } 
                //Increase the number of moves made by 1
                super.movesMade++;
                //Check if anybody has won 
                CheckWin();
            }
        //Catch any exception if the conversion was not possible 
        } catch (Exception e) {}
    }
    
    /**
     * This method checks if any of the players have connected 4 on the board 
     */
    public void CheckWin() {
        //If there is a horizontal connection somewhere on the board, set the game over to true 
        if (CheckWinHorizontal(0, 0)) isGameOver = true;
        //If there is a vertical connection somewhere on the board, set the game over to true 
        if (CheckWinVertical(0, 0)) isGameOver = true;
        //If there is a diagonal connection somewhere on the board, set the game over to true 
        if (CheckWinDiagonal1(0,0)) isGameOver = true;
        //If there is a backwards diagonal connection somewhere on the board, set the game over to true 
        if (CheckWinDiagonal2(0,0)) isGameOver = true;
        
        //if the game over is true or the number of moves exceeds or is equal to 42, the total number of pieces that can be places
        //on the board, call the EndGame function 
        if (isGameOver || super.movesMade >= 42) {
            //Call the EndGame function and pass through moves made 
            EndGame(movesMade);
        }
    }
    
    /**
     * This method checks if there was a horizontal connection of 4 on the board 
     * @param intX is the row element number that is being checked on the board 
     * @param intY is the column element number that is being checked on the board 
     * @return true if there was a connection of 4 on the board, else return false 
     */
    public boolean CheckWinHorizontal(int intX, int intY) {
        //Base condition which will ensure that the recursion will stop before the method begins to check values outside of the board 
        //Ex. [intX] = [6] is a row that is out of bounds 
        if (intX < 6) {
            //Same kind of condition for the columns. Ex. [y + 3] = [4 + 3] = [7] is a column that does not exist 
            if (intY < 4) {
                //If the initial value and the three values that come after in the horizontal are all the same and are not blank 
                if (super.boxes[intX][intY].getIcon() == super.boxes[intX][intY + 1].getIcon()
                        && super.boxes[intX][intY].getIcon() == super.boxes[intX][intY + 2].getIcon()
                        && super.boxes[intX][intY].getIcon() == super.boxes[intX][intY + 3].getIcon()
                        && super.boxes[intX][intY].getIcon() != super.blank) {
                    //Return true, to tell the CheckWin method that someone won 
                    return true;
                //Else call this funciton again and increase the initial column number by 1 
                } else {
                    return CheckWinHorizontal(intX, ++intY);
                }
            //Else if the column number is too great, increase the row number, reset the column number to 0, call the function again
            } else {
                return CheckWinHorizontal(++intX, 0);
            }
        }
        //If nothing is found, return false 
        return false;
    }
    
    /**
     * This method checks if there was a vertical connection of 4 on the board 
     * @param intX is the row element number that is being checked on the board 
     * @param intY is the column element number that is being checked on the board 
     * @return true if there was a connection of 4 on the board, else return false 
     */
    public boolean CheckWinVertical(int intX, int intY) {
        //Base condition which will ensure that the recursion will stop before the method begins to check values outside of the board 
        //Ex. [intX + 3] = [3 + 3] = [6] is a row that is out of bounds 
        if (intX < 3) {
            //Same kind of condition for the columns 
            if (intY < 7) {
                //If the initial value and the three values that come after in the vertical are all the same and are not blank 
                if (super.boxes[intX][intY].getIcon() == super.boxes[intX + 1][intY].getIcon()
                        && super.boxes[intX][intY].getIcon() == super.boxes[intX + 2][intY].getIcon()
                        && super.boxes[intX][intY].getIcon() == super.boxes[intX + 3][intY].getIcon()
                        && super.boxes[intX][intY].getIcon() != super.blank) {
                    //Return true, to tell the CheckWin method that someone won 
                    return true;
                //Else call this funciton again and increase the initial column number by 1 
                } else {
                    return CheckWinVertical(intX, ++intY);
                }
            //Else if the column number is too great, increase the row number, reset the column number to 0, call the function again
            } else {
                return CheckWinVertical(++intX, 0);
            }
        }
        //If nothing is found, return false 
        return false;
    }
    
    /**
     * This method checks if there was a diagonal connection, going down and to the right, of 4 on the board 
     * @param intX is the row element number that is being checked on the board 
     * @param intY is the column element number that is being checked on the board 
     * @return true if there was a connection of 4 on the board, else return false 
     */
    public boolean CheckWinDiagonal1(int intX, int intY) {
        //Base condition which will ensure that the recursion will stop before the method begins to check values outside of the board 
        //Ex. [intX + 3] = [3 + 3] = [6] is a row that is out of bounds 
        if (intX < 3) {
            //Same kind condition for the columns 
            if (intY < 4) {
                //If the initial value and the three values that come after in the diagonal are all the same and are not blank 
                if (super.boxes[intX][intY].getIcon() == super.boxes[intX + 1][intY + 1].getIcon()
                        && super.boxes[intX][intY].getIcon() == super.boxes[intX + 2][intY + 2].getIcon()
                        && super.boxes[intX][intY].getIcon() == super.boxes[intX + 3][intY + 3].getIcon()
                        && super.boxes[intX][intY].getIcon() != super.blank) {
                    //Return true, to tell the CheckWin method that someone won 
                    return true;
                //Else call this funciton again and increase the initial column number by 1 
                } else {
                    return CheckWinDiagonal1(intX, ++intY);
                }
            } else {
            //Else if the column number is too great, increase the row number, reset the column number to 0, call the function again
                return CheckWinDiagonal1(++intX, 0);
            }
        }
        //If nothing is found, return false 
        return false;
    }
    
    /**
     * This method checks if there was a diagonal connection, going down and to the left, of 4 on the board 
     * @param intX is the row element number that is being checked on the board 
     * @param intY is the column element number that is being checked on the board 
     * @return true if there was a connection of 4 on the board, else return false 
     */
    public boolean CheckWinDiagonal2(int intX, int intY) {
        //Base condition which will ensure that the recursion will stop before the method begins to check values outside of the board 
        //Ex. [intX + 3] = [3 + 3] = [6] is a row that is out of bounds 
        if (intX < 3) {
            //Same kind of condition for the columns 
            if (intY < 4) {
                //If the initial value and the three values that come after in the diagonal are all the same and are not blank 
                if (super.boxes[intX][6 - intY].getIcon() == super.boxes[intX + 1][6 - (intY + 1)].getIcon()
                        && super.boxes[intX][6 - intY].getIcon() == super.boxes[intX + 2][6 - (intY + 2)].getIcon()
                        && super.boxes[intX][6 - intY].getIcon() == super.boxes[intX + 3][6 - (intY + 3)].getIcon()
                        && super.boxes[intX][6 - intY].getIcon() != super.blank) {
                    //Return true, to tell the CheckWin method that someone won 
                    return true;
                //Else call this funciton again and increase the initial column number by 1 
                } else {
                    return CheckWinDiagonal2(intX, ++intY);
                }
            //Else if the column number is too great, increase the row number, reset the column number to 0, call the function again
            } else {
                return CheckWinDiagonal2(++intX, 0);
            }
        }
        //If nothing is found, return false 
        return false;
    }
    
    /**
     * 
     * @param intO the number of moves that have been made so far 
     */
    public void EndGame(int intO) {
        //Boolean that checks if the game being played is classic or not 
        boolean isClassic = true;
        //If the incoming value is not equal to the number of movesMade, we know that this is the reverse game mode calling 
        //this function as opposed to the Classic Game Mode, and thus, we make isClassic false 
        if (intO != movesMade) isClassic = false;
        //Use the modular funcstion on IntO to find out whether or not the lase move was made by player 1 or two 
        //This is the reason why this method does not directly check the final move from the super; it needs to find out which game 
        //was being played at the time 
        int intX = intO % 2;
        //If the game had actually ended on a tie 
        if (intO >= 42) {
            //Set intX to a tie
            intX = 2; 
        }
        //Switch statement that examines intX
        switch (intX) {
            //If the final move was by player 1 
            case 1:
                //If classic mode is being played 
                if (isClassic) {
                    //Add a win to player 1's account for classic mode
                    main.player1.intWinsC++;
                    //Add a loss to player 2's account for classic mode
                    main.player2.intLosesC++;
                //If reverse mode is being playing 
                } else {
                    //Add a win to player 1's account for reverse mode
                    main.player1.intWinsR++;
                    //Add a loss to player 2's account for reverse mode
                    main.player2.intLosesR++;
                }
                //Show a message saying that player 1 has won 
                JOptionPane.showMessageDialog(this, main.player1.GetName() + " has won the game!");
                break;
            //If the final move was by player 2
            case 0:
                //If classic mode is being played 
                if (isClassic) {
                    //Add a loss to player 1's account for classic mode
                    main.player1.intLosesC++;
                    //Add a win to player 2's account for classic mode
                    main.player2.intWinsC++;
                //If reverse mode is being playing 
                } else {
                    //Add a loss to player 1's account for reverse mode
                    main.player1.intLosesR++;
                    //Add a win to player 2's account for reverse mode
                    main.player2.intWinsR++;
                }
                //Show a message saying that player 2 has won 
                JOptionPane.showMessageDialog(this, main.player2.GetName() + " has won the game!");
                break;
            //If the game ended a tie 
            case 2:
                //If classic mode is being played 
                if (isClassic) {
                    //Add a tie to both player 1 and 2 for classic mode 
                    main.player1.intTiesC++;
                    main.player2.intTiesC++;
                //If reverse mode is being playing 
                } else {
                    //Add a tie to both player 1 and 2 for reverse mode 
                    main.player1.intTiesR++;
                    main.player2.intTiesR++;
                }
                //Show a message saying that the game has tied 
                JOptionPane.showMessageDialog(this, "The game has tied!");
                break;
            default: 
                break;
        }
        //Save all of the new stats 
        main.SaveDataBase();
        //Create a new WelcomeWindow
        WelcomeWindow welcomeWindow = new WelcomeWindow();
        welcomeWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        welcomeWindow.setVisible(true);
        //Close the currently open window 
        this.dispose();
    }
    
    /**
     * This method sets up the piecesList array so that it contains empty arrayLists, each representing an empty column on the board
     */
    public void SetArray () {
        //For loop that will run 7 times
        for (int i = 0; i < 7; i++) {
            //Add an empty ArrayList to the piecesList 
            piecesList.add(new ArrayList());
        } 
    }
    
    /**
     * This is the constructor method for the Classic Game
     * @param strName is the title of this window 
     */
    public ClassicGame(String strName) {
        //Use the parent constructor and pass through the name that was recieved in order to set the title of the generated frame 
        super(strName);
        
        //Set the array with the correct amount of empty ArrayLists in order to start the game 
        SetArray();
        
        //For loop that adds an ActionListener to each box in the game that listens to this program 
        for (int x = 0; x < 6; x++) {
            //For loop that runs through each column in the rows 
            for (int y = 0; y < 7; y++) {
                //Add an action listener to the box 
                super.boxes[x][y].addActionListener(this);
            }
        }
    }
}
