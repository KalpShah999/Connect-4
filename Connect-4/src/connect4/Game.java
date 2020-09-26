package connect4;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 *
 * Author: Kalp Shah
 * Date: August 25, 2020
 * This program is the abstract Game class that will extend to all of the game modes 
 */
public abstract class Game extends JFrame implements ItemListener, ActionListener, Runnable {
    
    //Create a new JPanel that everything will be held in 
    JPanel panel = new JPanel();
    //Create a double array of boxes that will hold all of the buttons used in the game 
    JButton[][] boxes = new JButton[6][7];
    //New ImageIcon that represents an empty counter token 
    ImageIcon blank = new ImageIcon("blank.png");
    //New ImageIcon that represents a red counter token 
    ImageIcon red = new ImageIcon("red.png");
    //New ImageIcon that represents a yellow counter token 
    ImageIcon yellow = new ImageIcon("yellow.png");
    //New Integer variable that reprents the number of moves made 
    Integer movesMade;
    
    /**
     * 
     * @param strTitle is the title of the window that is being constructed 
     */
    public Game (String strTitle) {
        //Set the title of the window to the String that has been passed into the method 
        super(strTitle);
        //Set the size of the window 
        setSize (1080,780);
        //Set the default close operation for the game window 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Set the layout of the window a new Flow layout so that everything is neatly structed and centered on the window 
        FlowLayout layout = new FlowLayout();
        setLayout(layout);
        //Set a new integer name variable to 0 
        int name = 0;
        //Create a new string variable that is the String version of name 
        String newname;

        //New gridlayout for the window that is the size of the of the classic game board
        GridLayout layout1 = new GridLayout(6, 7, 10, 10);
        //Set the panel layout to the grid layout 
        panel.setLayout(layout1);
        //For each row in the boxes variable
        for (int x = 0; x < 6; x++) {
            //For each column in the boxes variable
            for (int y = 0; y < 7; y++) {
                //Set newname to the string converted version of name 
                newname = Integer.toString(name);
                //Add a new Jbutton to the location within boxes with a name newname 
                boxes[x][y] = new JButton(newname);
                //Add 1 to the name integer 
                name++;
                //Set the buttons icon to blank to represent a blank spot on the board 
                boxes[x][y].setIcon(blank);
                //Add the button to the panel grid 
                panel.add(boxes[x][y]);
            }
        } 
        
        //Add the entire panel to the window 
        add (panel);
        //Set the number of moves made to 0
        movesMade = 0;
        
        //Set the window visibility to true 
        setVisible(true);
    }
    
    //Abstract methods for each of the child classes 
    abstract void CheckWin();
    abstract void EndGame(int intO);

    //Exceptions that will caught 
    public void itemStateChanged(ItemEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void run() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
