package connect4;
import java.awt.event.*;
import javax.swing.*;
/**
 *
 * Author: Kalp Shah
 * Date: August 25, 2020
 * This program runs the solo game mode for Connect 4 
 */
public class SoloGame extends Game implements ItemListener, ActionListener, Runnable {
    
    //Create a new instance of the Connect4 class
    Connect4 main = new  Connect4();
    //Create a new boolean variable that represents if the game is over 
    boolean isGameOver = false;
    //Create a new integer variable that represents the number of pieces left on the board
    int intPiecesLeft = 42;
    //Create two integers represetented the clicked buttons from the player 
    //Temp1 for the first button clicked
    int intTemp1 = -1;
    //Temp2 for the second button clicked
    int intTemp2 = -1;
    
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
            int intCommand = Integer.parseInt(strCommand);
            //If the first button has not been clicked yet
            if (intTemp1 == -1) {
                //Set the integer to the integer that represents the button clicked 
                intTemp1 = intCommand;
            //Else if the second button has not been clicked yet
            } else if (intTemp2 == -1) {
                //Set the integer to the integer that represents the button clicked 
                intTemp2 = intCommand;
                //Set a temporary icon to the icon of the first button clicked
                Icon temp = super.boxes[intTemp1 / 7][intTemp1 % 7].getIcon();
                //Set the icon of the first button clicked to the icon of the second button clicked
                super.boxes[intTemp1 / 7][intTemp1 % 7].setIcon(super.boxes[intTemp2 / 7][intTemp2 % 7].getIcon());
                //Set the icon of the second button to the temporary icon 
                super.boxes[intTemp2 / 7][intTemp2 % 7].setIcon(temp);
                //Set both of the temporary button numbers back to -1 to represent no button pressed
                intTemp1 = -1;
                intTemp2 = -1;
                //Add 1 to the number of moves made
                movesMade++;
                //Drop all of the floating pieces using the drop pieces method 
                DropPieces();
                //Check for a win using the 
                CheckWin();
            }
        //Catch any exception if the conversion was not possible 
        } catch (Exception e) {}
    }
    
    /**
     * This method checks if there are any connections of 4 on the board 
     */
    public void CheckWin() {
        //Remove all of the pieces that are in a horizontal connection 
        CheckWinHorizontal(0, 0);
        //Remove all of the pieces that are in a vertical connection 
        CheckWinVertical(0, 0);
        //Remove all of the pieces that are in a diagonal connection 
        CheckWinDiagonal1(0, 0);
        //Remove all of the pieces that are in a backwards diagonal connection 
        CheckWinDiagonal2(0, 0);
        //Drop all of the pieces again 
        DropPieces();
        
        //Check if less than 6 pieces remain on the board and if the game is not over yet 
        if (intPiecesLeft <= 6 && !isGameOver) {
            //End the game 
            isGameOver = true;
            //Run the EndGame method and pass in the number of moves made by the player
            EndGame(movesMade);
        }
    }
    
    /**
     * 
     * @param intX is the index of the row being examined
     * @param intY is the index of the column being examined
     */
    public void CheckWinHorizontal(int intX, int intY) {
        //Base condition which will ensure that the recursion will stop before the method begins to check values outside of the board 
        //Ex. [intX] = [6] is a row that is out of bounds 
        if (intX < 6) {
            //Same kind of condition for the columns 
            if (intY < 4) {
                //If the initial value and the three values that come after in the vertical are all the same and are not blank 
                if (super.boxes[intX][intY].getIcon() == super.boxes[intX][intY + 1].getIcon()
                        && super.boxes[intX][intY].getIcon() == super.boxes[intX][intY + 2].getIcon()
                        && super.boxes[intX][intY].getIcon() == super.boxes[intX][intY + 3].getIcon()
                        && super.boxes[intX][intY].getIcon() != super.blank) {
                    //Set new intX2 integer value to intX
                    int intX2 = intX;
                    //Set new intY2 integer value to intY + 1
                    int intY2 = intY + 1;
                    //While Y2 is less than the total number of columns (this will allow us to check for connections beyond 4)
                    while (intY2 < 7) {
                        //Check if the box to the right of the initial box with the connection is the same icon
                        if (super.boxes[intX2][intY2].getIcon().equals(super.boxes[intX][intY].getIcon())) {
                            //Set the icon of that box to blank
                            super.boxes[intX2][intY2].setIcon(super.blank);
                            //Decrease the number of pieces by 1
                            intPiecesLeft -= 1;
                            //Add 1 to intY2
                            intY2 += 1;
                        } else {
                            //Else set intY2 to a value that is out of bounds so that it stops checking 
                            intY2 = 7;
                        }
                    }
                    //Set the inital box with the connection to blank as well
                    super.boxes[intX][intY].setIcon(super.blank);
                    //Decrease the number of pieces by 1
                    intPiecesLeft -= 1;
                }
                //Call this funciton again and increase the initial column number by 1 
                CheckWinHorizontal(intX, ++intY);
            //Else if the column number is too great, increase the row number, reset the column number to 0, call the function again
            } else {
                CheckWinHorizontal(++intX, 0);
            }
        }
    }
    
    /**
     * 
     * @param intX is the index of the row being examined
     * @param intY is the index of the column being examined
     */
    public void CheckWinVertical(int intX, int intY) {
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
                    //Set new intX2 integer value to intX + 1
                    int intX2 = intX + 1;
                    //Set new intY2 integer value to intY
                    int intY2 = intY;
                    //While X2 is less than the total number of rows (this will allow us to check for connections beyond 4)
                    while (intX2 < 6) {
                        //Check if the box to the below of the initial box with the connection is the same icon
                        if (super.boxes[intX2][intY2].getIcon().equals(super.boxes[intX][intY].getIcon())) {
                            //Set the icon of that box to blank
                            super.boxes[intX2][intY2].setIcon(super.blank);
                            //Decrease the number of pieces by 1
                            intPiecesLeft -= 1;
                            //Add 1 to intX2
                            intX2 += 1;
                        } else {
                            //Else set intX2 to a value that is out of bounds so that it stops checking 
                            intX2 = 6;
                        }
                    }
                    //Set the inital box with the connection to blank as well
                    super.boxes[intX][intY].setIcon(super.blank);
                    //Decrease the number of pieces by 1
                    intPiecesLeft -= 1;
                }
                //Call this funciton again and increase the initial column number by 1 
                CheckWinVertical(intX, ++intY);
            //Else if the column number is too great, increase the row number, reset the column number to 0, call the function again
            } else {
                CheckWinVertical(++intX, 0);
            }
        }
    }
    
    /**
     * 
     * @param intX is the index of the row being examined
     * @param intY is the index of the column being examined
     */
    public void CheckWinDiagonal1(int intX, int intY) {
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
                    //Set new intX2 integer value to intX + 1
                    int intX2 = intX + 1;
                    //Set new intY2 integer value to intY + 1
                    int intY2 = intY + 1;
                    //While the row number or the column number does not exceed the maximum size
                    //(This will allow us to check for connections beyond 4)
                    while (intX2 < 6 && intY2 < 7) {
                        //Check if the box to the below and to the right of the initial box with the connection is the same icon
                        if (super.boxes[intX2][intY2].getIcon().equals(super.boxes[intX][intY].getIcon())) {
                            //Set the icon of that box to blank
                            super.boxes[intX2][intY2].setIcon(super.blank);
                            //Decrease the number of pieces by 1
                            intPiecesLeft -= 1;
                            //Add 1 to intX2
                            intX2 += 1;
                            //Add 1 to intY2
                            intY2 += 1;
                        } else {
                            //Else set intX2 and intY2 to values that are out of bounds so that it stops checking 
                            intX2 = 6;
                            intY2 = 7;
                        }
                    }
                    //Set the inital box with the connection to blank as well
                    super.boxes[intX][intY].setIcon(super.blank);
                    //Decrease the number of pieces by 1
                    intPiecesLeft -= 1;
                }
                //Call this funciton again and increase the initial column number by 1 
                CheckWinDiagonal1(intX, ++intY);
            } else {
            //Else if the column number is too great, increase the row number, reset the column number to 0, call the function again
                CheckWinDiagonal1(++intX, 0);
            }
        }
    }
    
    /**
     * 
     * @param intX is the index of the row being examined
     * @param intY is the index of the column being examined
     */
    public void CheckWinDiagonal2(int intX, int intY) {
        //Base condition which will ensure that the recursion will stop before the method begins to check values outside of the board 
        //Ex. [intX + 3] = [3 + 3] = [6] is a row that is out of bounds 
        if (intX < 3) {
            //Same kind condition for the columns 
            if (intY < 4) {
                //If the initial value and the three values that come after in the diagonal are all the same and are not blank 
                if (super.boxes[intX][6 - intY].getIcon() == super.boxes[intX + 1][6 - (intY + 1)].getIcon()
                        && super.boxes[intX][6 - intY].getIcon() == super.boxes[intX + 2][6 - (intY + 2)].getIcon()
                        && super.boxes[intX][6 - intY].getIcon() == super.boxes[intX + 3][6 - (intY + 3)].getIcon()
                        && super.boxes[intX][6 - intY].getIcon() != super.blank) {
                    //Set new intX2 integer value to intX + 1
                    int intX2 = intX + 1;
                    //Set new intY2 integer value to intY + 1
                    int intY2 = intY + 1;
                    //While the row number or the column number does not exceed the maximum size
                    //(This will allow us to check for connections beyond 4)
                    while (intX2 < 6 && intY2 < 7) {
                        //Check if the box to the below and to the right of the initial box with the connection is the same icon
                        if (super.boxes[intX2][6 - intY2].getIcon().equals(super.boxes[intX][6 - intY].getIcon())) {
                            //Set the icon of that box to blank
                            super.boxes[intX2][6 - intY2].setIcon(super.blank);
                            //Decrease the number of pieces by 1
                            intPiecesLeft -= 1;
                            //Add 1 to intX2
                            intX2 += 1;
                            //Add 1 to intY2
                            intY2 += 1;
                        } else {
                            //Else set intX2 and intY2 to values that are out of bounds so that it stops checking 
                            intX2 = 6;
                            intY2 = 7;
                        }
                    }
                    //Set the inital box with the connection to blank as well
                    super.boxes[intX][intY].setIcon(super.blank);
                    //Decrease the number of pieces by 1
                    intPiecesLeft -= 1;
                } 
                //Call this funciton again and increase the initial column number by 1 
                CheckWinDiagonal2(intX, ++intY);
            } else {
            //Else if the column number is too great, increase the row number, reset the column number to 0, call the function again
                CheckWinDiagonal2(++intX, 0);
            }
        }
    }
    
    /**
     * This linear searching algorithm method finds and drops all of the pieces that are floating on the board 
     */
    public void DropPieces() {
        //Create a boolean that will represent whether or not the CheckWin method needs to be called again
        boolean repeat = false;
        //For each row on the board starting from the bottom up 
        for (int k = 6; k >= 0; k--) {
            //For each column on the board starting from the right to the left
            for (int i = 5; i >= 1; i--) {
                //If the piece icon is blank and the piece above it is not blank
                if (super.boxes[i][k].getIcon().equals(super.blank) && !super.boxes[i - 1][k].getIcon().equals(super.blank)) {
                    //Set the icon of the button below to the icon of the piece above
                    super.boxes[i][k].setIcon(super.boxes[i - 1][k].getIcon());
                    //Set the icon of the piece above to blank
                    super.boxes[i - 1][k].setIcon(super.blank);
                    //Set repeat to true since pieces have shifted on the board
                    repeat = true;
                }
            }
        } 
        //If rerpeat is true, run the check win method again to get rid of any new connections that may have been made
        if (repeat) CheckWin();
    }
    
    /**
     * 
     * @param intO is the number of moves the player made 
     */
    public void EndGame(int intO) {
        //If the number of moves is less than the players current high score or if their high score hasn't been set yet (==0)
        if (intO < main.player1.intSolitaireHS || main.player1.intSolitaireHS == 0) {
            //Set the players high score to the score they just achieved
            main.player1.intSolitaireHS = intO;
            //Pop up a message saying that they acheived a new high score
            JOptionPane.showMessageDialog(this, "New High Score!\nYour score is now " + main.player1.intSolitaireHS + ".");
        } else {
            //Else show a message showing what score they achieved and their current high score is
            JOptionPane.showMessageDialog(this, "Your score was " + intO + ".\nYour current high score is "
                    + main.player1.intSolitaireHS + ".");
        }
        //Score the player statistics using the SaveDataBase method
        main.SaveDataBase();
        //Create a new welcome window
        WelcomeWindow welcomeWindow = new WelcomeWindow();
        welcomeWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        welcomeWindow.setVisible(true);
        //Dispose of this window 
        this.dispose();
    }
    
    /**
     * 
     * @param strName is the title of this window 
     */
    public SoloGame(String strName) {
        //Use the parent constructor and pass through the name that was recieved in order to set the title of the generated frame 
        super(strName);
        
        //For loop that loops through each row of boxes/buttons in the game 
        for (int x = 0; x < 6; x++) {
            //For loop that loops through each column of boxes/buttons in the game 
            for (int y = 0; y < 7; y++) {
                //Add an action listener to each box that listens to this program
                super.boxes[x][y].addActionListener(this);
                //Generate a random number
                double intRandom = Math.random();
                //If the number is greater than or equal to 0.5
                if (intRandom >= 0.5) {
                    //Set the icon of that box to red
                    super.boxes[x][y].setIcon(super.red);
                //Else if the number is less than 0.5
                } else {
                    //Set the icon of the box to yellow
                    super.boxes[x][y].setIcon(super.yellow);
                }
            }
        }
    }
}
