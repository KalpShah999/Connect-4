package connect4;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 *
 * Author: Kalp Shah
 * Date: August 25, 2020
 * This is the welcome window for the Connect 4 Game 
 */
public class WelcomeWindow extends JFrame implements ItemListener, ActionListener, Runnable {
    
    //Create a new instance of the Connect4 class
    Connect4 main = new Connect4();
    
    //New JLabel that represents the subtitle on the window
    JLabel lblSubTitle = new JLabel("Kalp Shah's");
    //New JLabel that represents the title on the window
    JLabel lblTitle = new JLabel("Connect 4");
    //New JButton with text classic mode for the classic mode game
    JButton btnClassic = new JButton("Classic Mode");
    //New JButton with text reverse mode for the reverse mode game
    JButton btnReverse = new JButton("Reverse Mode");
    //New JButton with text solo mode for the solo mode game
    JButton btnSolo = new JButton("Solo Mode");
    //New JButton with text logo for if the player wants to relogin into the game with different usernames perhaps
    JButton btnLogin = new JButton("Login");
    //New JButton with text view leader board for when the player wants to go the leader board window 
    JButton btnLeaderBoard = new JButton("View Leader Boards");
    //New JButton with text exit for when the player wishes to close the game
    JButton btnExit = new JButton("Exit");

    /**
     * 
     * @param event is the event that is being passed to this script 
     */
    public void actionPerformed (ActionEvent event) {
        //Store the Action event command as a string in a new String variable called strCommand
        String command = event.getActionCommand();
        
        //If the command is equal to classic mode; if the classic mode button was pressed 
        if (command.equals("Classic Mode")) {
            //Set a new string variable to the title of the window that is going to be created
            String strName = "Kalp Shah's Connect 4 - Classic Game Mode - " + main.player1.GetName() + " vs. " + main.player2.GetName();
            //Create a new classic game window with the title as strName
            ClassicGame classicGameWindow = new ClassicGame(strName);
            classicGameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            classicGameWindow.setVisible(true);
            //Dispose of this window
            this.dispose();
        //Else if the command is equal to reverse mode; if the reverse mode button was pressed 
        } else if (command.equals("Reverse Mode")) {
            //Set a new string variable to the title of the window that is going to be created
            String strName = "Kalp Shah's Connect 4 - Reverse Game Mode - " + main.player1.GetName() + " vs. " + main.player2.GetName();
            //Create a new reverse game window with the title as strName
            ReverseGame reverseGameWindow = new ReverseGame(strName);
            reverseGameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            reverseGameWindow.setVisible(true);
            //Dispose of this window
            this.dispose();
        //Else if the command is equal to solo mode; if the solo mode button was pressed
        } else if (command.equals("Solo Mode")) {
            //Set a new string variable to the title of the window that is going to be created
            String strName = "Kalp Shah's Connect 4 - Solo Game Mode - " + main.player1.GetName();
            //Create a new solo game window with the title as strName
            SoloGame soloGameWindow = new SoloGame(strName);
            soloGameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            soloGameWindow.setVisible(true);
            //Dispose of this window
            this.dispose();
        //Else if the command is equal to login; if the login button was pressed
        } else if (command.equals("Login")) {
            //Create a new login window 
            LoginWindow loginWindow = new LoginWindow();
            loginWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            loginWindow.setVisible(true);
            //Dispose of this window
            this.dispose();
        //Else if the command is equal to view leader boards; if the leader boards button was pressed
        } else if (command.equals("View Leader Boards")) {
            //Create a new leader boards window 
            LeaderboardWindow leaderboardWindow = new LeaderboardWindow();
            leaderboardWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            leaderboardWindow.setVisible(true);
            //Dispose of this window
            this.dispose();
        //Else if the command equals exit; the exit button was pressed 
        } else if (command.equals("Exit")) {
            //Close this window without opening another one 
            this.dispose();
        }
    }
    
    /**
     * Constructor class for the welcome window that sets the GUI for the window 
     */
    public WelcomeWindow (){
        //Set the window name
        super("Kalp Shah's Connect 4 - Home");
        //Set the window size
        setSize(780,780);
        //Set the default closing operation
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Set the layout to nothing so that everything can be adjusted 
        setLayout(null);
        
        //Set the allignment, font, size, and location for the subtitle label
        lblSubTitle.setHorizontalAlignment(JLabel.CENTER);
        lblSubTitle.setFont(new Font("Spectral", Font.ITALIC, 20));
        lblSubTitle.setSize(200, 100);
        lblSubTitle.setLocation(290, 150);
        
        //Set the allignment, font, size, and location for the title label
        lblTitle.setHorizontalAlignment(JLabel.CENTER);
        lblTitle.setFont(new Font("Spectral", Font.BOLD, 100));
        lblTitle.setForeground(new Color(240, 0, 240));
        lblTitle.setSize(500, 100);
        lblTitle.setLocation(140, 250);
        
        //Set font, size, and location for the classic button and then add an action listener
        btnClassic.addActionListener(this);
        btnClassic.setFont(new Font("Spectral", Font.PLAIN, 17));
        btnClassic.setSize(150, 50);
        btnClassic.setLocation(115, 500);
        //If two players are not logged in at the moment for the multiplayer mode
        if (main.player2 == null) {
            //Set this button to not enabled
            btnClassic.setEnabled(false);
        } 
        
        //Set font, size, and location for the reverse button and then add an action listener
        btnReverse.addActionListener(this);
        btnReverse.setFont(new Font("Spectral", Font.PLAIN, 17));
        btnReverse.setSize(150, 50);
        btnReverse.setLocation(315, 500);
        //If two players are not logged in at the moment for the multiplayer mode
        if (main.player2 == null) {
            //Set this button to not enabled
            btnReverse.setEnabled(false);
        }
        
        //Set font, size, and location for the solo button and then add an action listener
        btnSolo.addActionListener(this);
        btnSolo.setFont(new Font("Spectral", Font.PLAIN, 17));
        btnSolo.setSize(150, 50);
        btnSolo.setLocation(515, 500);
        
        //Set font, size, and location for the login button and then add an action listener
        btnLogin.addActionListener(this);
        btnLogin.setFont(new Font("Spectral", Font.PLAIN, 17));
        btnLogin.setSize(125, 50);
        btnLogin.setLocation(115, 600);
        
        //Set font, size, and location for the leader boards button and then add an action listener
        btnLeaderBoard.addActionListener(this);
        btnLeaderBoard.setFont(new Font("Spectral", Font.PLAIN, 17));
        btnLeaderBoard.setSize(200, 50);
        btnLeaderBoard.setLocation(290, 600);
        
        //Set font, size, and location for the exit button and then add an action listener
        btnExit.addActionListener(this);
        btnExit.setFont(new Font("Spectral", Font.PLAIN, 17));
        btnExit.setSize(125, 50);
        btnExit.setLocation(540, 600);
        
        //Add all of the labels and buttons to the window 
        add(lblTitle);
        add(lblSubTitle);
        add(btnClassic);
        add(btnReverse);
        add(btnSolo);
        add(btnLogin);
        add(btnLeaderBoard);
        add(btnExit);
        
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
