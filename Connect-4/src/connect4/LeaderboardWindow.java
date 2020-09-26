package connect4;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
/**
 *
 * Author: Kalp Shah
 * Date: August 25, 2020
 * This is the leader board window for the Connect 4 Game 
 */
public class LeaderboardWindow extends JFrame implements ItemListener, ActionListener, Runnable {
    
    //Create a new instance of the Connect4 class
    Connect4 main = new Connect4();
    
    //New JLabel that represents the title on the window
    JLabel lblTitle = new JLabel();
    //New JLabel that represents the text area on the leaderboard window 
    JTextArea txtAScores = new JTextArea();
    //New JButton with text classic mode for the classic mode scores
    JButton btnClassicScores = new JButton("Classic Mode");
    //New JButton with text reverse mode for the reverse mode scores
    JButton btnReverseScores = new JButton("Reverse Mode");
    //New JButton with text solo mode for the solo mode scores
    JButton btnSoloScores = new JButton("Solo Mode");
    //New JButton for when the player wants to leave the screen and go back to the home page 
    JButton btnBack = new JButton("Back");
    
    /**
     * 
     * @param event is the event that is being passed to this script 
     */
    public void actionPerformed (ActionEvent event) {
        //Store the Action event command as a string in a new String variable called strCommand
        String command = event.getActionCommand();
        
        //If the command is equal to classic mode; if the classic mode button was pressed 
        if (command.equals("Classic Mode")) {
            //Set all of the button to enabled except for the classic button that was just pressed to show that it is active 
            btnClassicScores.setEnabled(false);
            btnReverseScores.setEnabled(true);
            btnSoloScores.setEnabled(true);
            //Run the change text method and pass through 0 to let it know which text to output
            ChangeText(0);
            //If the number of players is less than 10
            if (main.playerList.size() < 10) {
                //Set the title to the top, however many number of players, players
                lblTitle.setText("Classic Game Mode - Top " + main.playerList.size() + " Players");
            //Else if the number of players is greater than 10
            } else {
                //Set the title to top 10 players
                lblTitle.setText("Classic Game Mode - Top 10 Players");
            }
        //Else if the command is equal to reverse mode; if the reverse mode button was pressed 
        } else if (command.equals("Reverse Mode")) {
            //Set all of the button to enabled except for the reverse button that was just pressed to show that it is active 
            btnClassicScores.setEnabled(true);
            btnReverseScores.setEnabled(false);
            btnSoloScores.setEnabled(true);
            //Run the change text method and pass through 0 to let it know which text to output
            ChangeText(1);
            //If the number of players is less than 10
            if (main.playerList.size() < 10) {
                //Set the title to the top, however many number of players, players
                lblTitle.setText("Reverse Game Mode - Top " + main.playerList.size() + " Players");
            //Else if the number of players is greater than 10
            } else {
                //Set the title to top 10 players
                lblTitle.setText("Reverse Game Mode - Top 10 Players");
            }
        //Else if the command is equal to solo mode; if the solo mode button was pressed 
        } else if (command.equals("Solo Mode")) {
            //Set all of the button to enabled except for the solo button that was just pressed to show that it is active 
            btnClassicScores.setEnabled(true);
            btnReverseScores.setEnabled(true);
            btnSoloScores.setEnabled(false);
            //Run the change text method and pass through 0 to let it know which text to output
            ChangeText(2);
            //If the number of players is less than 10
            if (main.playerList.size() < 10) {
                //Set the title to the top, however many number of players, players
                lblTitle.setText("Solo Game Mode - Top " + main.playerList.size() + " Players");
            //Else if the number of players is greater than 10
            } else {
                //Set the title to top 10 players
                lblTitle.setText("Solo Game Mode - Top 10 Players");
            }
        //Else if the command is equal to back; the back button was pressed 
        } else if (command.equals("Back")) {
            //Create a new welcome window
            WelcomeWindow welcomeWindow = new WelcomeWindow();
            welcomeWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            welcomeWindow.setVisible(true);
            //Close this window 
            this.dispose();
        }
    }
    
    /**
     * 
     * @param intGameMode is an integer that represents the game mode text that needs to be outputted
     */
    public void ChangeText(int intGameMode) {
        //Set a new variable integer that will represent the number of players to output on the leaderboard, intX, to 10
        int intX = 10;
        //Create a new blank string that will represent the leaderboard scores
        String strMessage = "";
        //If the number of players is less than 10, set intX to the size of the list 
        if (main.playerList.size() < 10) {
            //Set intX to the size of the playerList 
            intX = main.playerList.size();
        }
        //Sort the players based on the game mode stats that have been requested 
        SortPlayers(intGameMode);
        //For each player whose score needs to be outputted, represented by intX, run through this foor loop 
        for (int i = 0; i < intX; i++) {
            //Get the player that is located at the sorted playerList 
            Player p = main.playerList.get(i);
            //If the game requested equals 0, meaning that it was classic game mode
            if (intGameMode == 0) {
                //Append the strMessage with that players classic game mode scores 
                strMessage += (i + 1) + ". " + p.GetName() + " - Score: " + p.GetCStats() + "\n";
            //Else if the game requested equals 1, meaning that it was reverse game mode
            } else if (intGameMode == 1) {
                //Append the strMessage with that players reverse game mode scores 
                strMessage += (i + 1) + ". " + p.GetName() + " - Score: " + p.GetRStats() + "\n";
            //Else if the game requested equals 2, meaning that it was solo game mode
            } else if (intGameMode == 2) {
                //Append the strMessage with that players solo game mode scores 
                strMessage += (i + 1) + ". " + p.GetName() + " - Score: " + p.intSolitaireHS + "\n";
            }
        }
        //Output the strMessage to the textfield on the window 
        txtAScores.setText(strMessage);
    }
    
    /**
     * 
     * @param intX is an integer that represents the format that the playerList needs to be sorted with 
     */
    public void SortPlayers(int intX) {
        //Switch statement that checks intX
        switch (intX) {
            //If intX equals 0, meaning the classic game mode, then bubble sort the playerList based on their Classic game statistics
            case 0:
                //For loop that repeats for the number of players in the playerList 
                for (int i = 0; i < main.playerList.size(); i++) {
                    //For each player in the playerrList that needs to be examined except the last 
                    for (int k = 0; k < main.playerList.size() - 1; k++) {
                        //Check if the score of the player next in the list if less than current players score (we want decending)
                        if (main.playerList.get(k).GetCStats() < main.playerList.get(k + 1).GetCStats()) {
                            //Store the player as a temporary player
                            Player temp = main.playerList.get(k);
                            //Set the player at k to the player at k + 1
                            main.playerList.set(k, main.playerList.get(k + 1));
                            //Set the player at k + 1 to the temporary player
                            main.playerList.set(k + 1, temp);
                        }
                    }
                } 
                //Break from the switch statement
                break;
            //Else if intX equals 1, meaning the reverse game mode, use selection sort the playerList based on the reverse game stats
            case 1:
                //For loop that repeats for each player in the playerList except the last one 
                for (int i = main.playerList.size() - 1; i >= 0; i--) {
                    //Creating an integer that represents the current largest number in the window that is being examined 
                    int intNumberToMove = i;
                    //For each value in the window that is being examined
                    for (int k = 0; k < i; k++) {
                        //If the value is smaller than the smallest value so far in the exmained list 
                        if (main.playerList.get(k).GetRStats() <= main.playerList.get(intNumberToMove).GetRStats()) {
                            //Set this new player index to the smallest value 
                            intNumberToMove = k;
                        }
                    }
                    //Set the player that has the smallest score to a temporary player
                    Player temp = main.playerList.get(intNumberToMove);
                    //Set the player that had the smallest to the player that was at the end of the examined window 
                    main.playerList.set(intNumberToMove, main.playerList.get(i));
                    //Set the last player in the examined window to the temporary player
                    main.playerList.set(i, temp);
                }
                //Break from the switch statement
                break;
            //Else if intX equals 2, meaning the solo game mode, use insertion sort to sort the playerList based on their solo stats
            case 2:
                //Creates an arraylist that can be sorted 
                ArrayList<Player> newList = new ArrayList<Player>();
                //Transfers the first data point from the playerList to the newList
                newList.add(main.playerList.get(0));
                //For loop that runs through the rest of the elements in the playerList
                for (int i = 0; i <  main.playerList.size(); i++) {
                    //Integer that represents the insertion location 
                    int intK = 0;
                    //While the score for the playerList player that is greater than the score for the newList player,
                    //increase the insertion location by 1
                    while (main.playerList.get(i).intSolitaireHS > newList.get(intK).intSolitaireHS && intK < newList.size() - 1) {
                        //Increase intK by 1
                        intK++;
                    }
                    //Add the array player to the specified insertion location 
                    newList.add(intK, main.playerList.get(i));
                }
                //Transfer all of the sorted players form the newList back into the playersList 
                for (int i = 0; i < main.playerList.size(); i++) {
                    main.playerList.set(i, newList.get(i));
                }
                //For loop that will move all of those that have a score of zero to the end 
                for (int i = main.playerList.size() - 1; i >= 0; i--) {
                    if (main.playerList.get(i).intSolitaireHS == 0) {
                        //Store that player as a temporary player
                        Player temp = main.playerList.get(i);
                        //Remove the player from the list, shifting everything down one 
                        main.playerList.remove(i);
                        //Add the player back to the end 
                        main.playerList.add(temp); 
                    }
                }
                //Break from the switch statement
                break;
            //Default case that will do nothing
            default:
                //Break from the switch statement
                break;
        }
    }
    
    /**
     * Constructor class for the leader board window that sets the GUI for the window 
     */
    public LeaderboardWindow () {
        //Set the window name
        super("Kalp Shah's Connect 4 - LeaderBoards");
        //Set the window size
        setSize(780,780);
        //Set the default closing operation
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Set the layout to nothing so that everything can be adjusted 
        setLayout(null);
        
        //Set the allignment, font, size, and location for the title label
        lblTitle.setHorizontalAlignment(JLabel.CENTER);
        lblTitle.setFont(new Font("Spectral", Font.BOLD, 20));
        lblTitle.setSize(500, 100);
        lblTitle.setLocation(140, 0);
        //If the number of players is less than 10
        if (main.playerList.size() < 10) {
            //Set the title to the top, however many number of players, players
            lblTitle.setText("Classic Game Mode - Top " + main.playerList.size() + " Players");
        //Else if the number of players is greater than 10
        } else {
            //Set the title to top 10 players
            lblTitle.setText("Classic Game Mode - Top 10 Players");
        }
        
        //Set the allignment, font, size, and location for the scores text area
        txtAScores.setAlignmentY(JTextArea.CENTER_ALIGNMENT);
        txtAScores.setFont(new Font("Spectral", Font.PLAIN, 20));
        txtAScores.setSize(500, 350);
        txtAScores.setLocation(140, 100);
        //Set the text area so that it is not editable 
        txtAScores.setEditable(false);
        //Set the text in the text area 
        ChangeText(0);
        
        //Set font, size, and location for the classic scores button and then add an action listener
        btnClassicScores.addActionListener(this);
        btnClassicScores.setFont(new Font("Spectral", Font.PLAIN, 17));
        btnClassicScores.setSize(150, 50);
        btnClassicScores.setLocation(115, 500);
        //Set the button to not enabled, as it is supposed to be pressed by default 
        btnClassicScores.setEnabled(false);
        
        //Set font, size, and location for the reverse scores button and then add an action listener
        btnReverseScores.addActionListener(this);
        btnReverseScores.setFont(new Font("Spectral", Font.PLAIN, 17));
        btnReverseScores.setSize(150, 50);
        btnReverseScores.setLocation(315, 500);
        
        //Set font, size, and location for the solo scores button and then add an action listener
        btnSoloScores.addActionListener(this);
        btnSoloScores.setFont(new Font("Spectral", Font.PLAIN, 17));
        btnSoloScores.setSize(150, 50);
        btnSoloScores.setLocation(515, 500);
        
        //Set font, size, and location for the back button and then add an action listener
        btnBack.addActionListener(this);
        btnBack.setFont(new Font("Spectral", Font.PLAIN, 17));
        btnBack.setSize(150, 50);
        btnBack.setLocation(315, 600);
        
        //Add all of the labels, text areas, and buttons to the window 
        add(lblTitle);
        add(txtAScores);
        add(btnClassicScores);
        add(btnReverseScores);
        add(btnSoloScores);
        add(btnBack);
        
        //Set the window to visible 
        setVisible(true);
    }

    //Exceptions that will caught 
    public void itemStateChanged(ItemEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void run() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
