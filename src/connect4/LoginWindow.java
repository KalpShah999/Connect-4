package connect4;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 *
 * Author: Kalp Shah
 * Date: August 25, 2020
 * This program is the login window for the Connect 4 Game 
 */
public class LoginWindow extends JFrame implements ItemListener, ActionListener, Runnable {
    
    //Create a new instance of the Connect4 class
    Connect4 main = new Connect4();
    
    //New JButton for when the player has typed in their username and wants to login  
    JButton btnStartGame = new JButton("Login");
    //New JLabel that represents the subtitle on the window
    JLabel lblSubTitle = new JLabel();
    //New JLabel that represents the title on the window
    JLabel lblTitle = new JLabel();
    //New JLabel that will show where player 1 should type their username
    JLabel lblPlayer1 = new JLabel();
    //New JLabel that will show where player 2 should type their username
    JLabel lblPlayer2 = new JLabel();
    //New JTextField that will be where player 1 should type their username
    JTextField txtPlayer1 = new JTextField();
    //New JTextField that will be where player 2 should type their username
    JTextField txtPlayer2 = new JTextField();

    /**
     * 
     * @param event is the event that is being passed to this script 
     */
    public void actionPerformed (ActionEvent event) {
        //Store the Action event command as a string in a new String variable called strCommand
        String command = event.getActionCommand();

        //If the command is equal to login; if the login button was pressed 
        if (command.equals("Login")) { 
            //Read the database from the external file
            main.ReadDataBase();
            //Get the username of player 1 and store it as a string
            String strPlayer1 = txtPlayer1.getText();
            //If a username was actually entered; if the text field was not left blank
            if (!txtPlayer1.getText().equals("")) {
                //Find the player from the playerList
                main.player1 = main.FindPlayer(strPlayer1, 0, main.playerList.size() - 1);
                //If no player was found 
                if (main.player1 == null) {
                    //Add the player to the list using the AddDataBase method
                    main.AddDataBase(strPlayer1);
                    //Find the player again, which will always be found 
                    main.player1 = main.FindPlayer(strPlayer1, 0, main.playerList.size() - 1);
                }
                //Get the username of player 2 and store it as a string
                String strPlayer2 = txtPlayer2.getText();
                //If a username was actually entered; if the text field was not left blank
                if (!txtPlayer2.getText().equals("")) {
                    //Try to find the player in the playerList
                    main.player2 = main.FindPlayer(strPlayer2, 0, main.playerList.size() - 1);
                    //If the player was not found 
                    if (main.player2 == null) {
                        //Add the player to the playerlist using the AddDataBase method
                        main.AddDataBase(strPlayer2);
                    //Find the player again, which will always be found 
                        main.player2 = main.FindPlayer(strPlayer1, 0, main.playerList.size() - 1);
                    }
                }
                //Open a new welcome window
                WelcomeWindow welcomeWindow = new WelcomeWindow();
                welcomeWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                welcomeWindow.setVisible(true);
                //Close this window 
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Player 1 username. Please re-enter the information.");
                //Open a new login winodow 
                LoginWindow loginWindow = new LoginWindow();
                loginWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                loginWindow.setVisible(true);
                this.dispose();
            }
        }
    }
    
    /**
     * Constructor class for the login window that sets the GUI for the window 
     */
    public LoginWindow () {
        //Set the window name
        super("Kalp Shah's Connect 4 - Login");
        //Set the window size
        setSize(780,780);
        //Set the default closing operation
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Set the layout to nothing so that everything can be adjusted 
        setLayout(null);
        
        //Set the text, alignment, font, size, and location for subtitle label 
        lblSubTitle.setText("Kalp Shah's");
        lblSubTitle.setHorizontalAlignment(JLabel.CENTER);
        lblSubTitle.setFont(new Font("Spectral", Font.ITALIC, 20));
        lblSubTitle.setSize(200, 100);
        lblSubTitle.setLocation(290, 150);
        
        //Set the text, alignment, font, size, and location for title label 
        lblTitle.setText("Connect 4");
        lblTitle.setHorizontalAlignment(JLabel.CENTER);
        lblTitle.setFont(new Font("Spectral", Font.BOLD, 100));
        lblTitle.setForeground(new Color(0, 240, 240));
        lblTitle.setSize(500, 100);
        lblTitle.setLocation(140, 250);
        
        //Set the text, alignment, font, size, and location for player1 label 
        lblPlayer1.setText("Player 1 Username: "); 
        lblPlayer1.setHorizontalAlignment(JLabel.CENTER);
        lblPlayer1.setFont(new Font("Spectral", Font.PLAIN, 17));
        lblPlayer1.setSize(170, 30);
        lblPlayer1.setLocation(220, 400);
        
        //Set the alignment, font, size, and location for player1 textfield 
        txtPlayer1.setHorizontalAlignment(JLabel.LEFT);
        txtPlayer1.setFont(new Font("Spectral", Font.PLAIN, 15));
        txtPlayer1.setSize(200, 30);
        txtPlayer1.setLocation(390, 402);
        
        //Set the text, alignment, font, size, and location for player2 label 
        lblPlayer2.setText("Player 2 Username: "); 
        lblPlayer2.setHorizontalAlignment(JLabel.CENTER);
        lblPlayer2.setFont(new Font("Spectral", Font.PLAIN, 17));
        lblPlayer2.setSize(170, 30);
        lblPlayer2.setLocation(220, 450);
        
        //Set the alignment, font, size, and location for player2 textfield 
        txtPlayer2.setHorizontalAlignment(JLabel.LEFT);
        txtPlayer2.setFont(new Font("Spectral", Font.PLAIN, 15));
        txtPlayer2.setSize(200, 30);
        txtPlayer2.setLocation(390, 452);
        
        //Set font, size, and location for the login button and then add an action listener
        btnStartGame.addActionListener(this);
        btnStartGame.setFont(new Font("Spectral", Font.PLAIN, 17));
        btnStartGame.setSize(100, 50);
        btnStartGame.setLocation(340, 550);
        
        //Add all of the labels, text areas, and buttons to the window 
        add(lblTitle);
        add(lblSubTitle);
        add(lblPlayer1);
        add(txtPlayer1);
        add(lblPlayer2);
        add(txtPlayer2);
        add(btnStartGame);
        
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
