/*CS110
  Homework 10
  Nick Forand
  warGUI
*/
//imports
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.util.ArrayList;
public class WarGUI extends JFrame
{



	//initalize a game
	private War g;
	//initialize 2 panels
	private JPanel panel1;
	private JPanel panel2;
	//initialize labels for everything
	private JLabel p1Deck;
	private JLabel p2Deck;
	private JLabel p1Pile;
	private JLabel p2Pile;
	private JLabel text;
   private JLabel p1Count;
   private JLabel p2Count;
	//initalize button to progress play
	private JButton playButton;
	//initialize imageicons to hold images of cards
	private ImageIcon back;
	private ImageIcon p1Card;
	private ImageIcon p2Card;
	
	/**
      Constructor
      
   */
	public WarGUI()
	{
		g = new War();//start new game

		try
        {//apply back.jpg to be image for back of the cards
            back = new ImageIcon(ImageIO.read(new File("cards/back.jpg")));
        }
        catch (IOException e)
        {//exit gracefully if image not found
            System.err.println("Image not found.");
            
        }
        
      //set title of frame to game of war
		setTitle("The Game of War");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800,600);
        validate();
        //build panel
        Panels();
        //add panel to the frame content
        add(panel1);
        add(panel2);
        setVisible(true);
	}
   /**
      panels method
      creates panels in the frame
      
   */

	private void Panels()
	{
		//text area to be displayed
		text = new JLabel("Welcome to War!");
        //create labels for all the cards
        p1Deck = new JLabel();
        p2Deck = new JLabel();
        p1Pile = new JLabel();
        p2Pile = new JLabel();
        p1Count = new JLabel();
        p2Count = new JLabel();
        update();
		//create play button
		playButton = new JButton("Play");
		//play button listener
		playButton.addActionListener(new PlayButtonListener());
		//create the panels
        panel1 = new JPanel();
        panel2 = new JPanel();

        panel1.add(p1Count);
        panel1.add(p1Deck);
        panel1.add(p1Pile);
        panel1.add(p2Pile);
        panel1.add(p2Deck);
        panel1.add(p2Count);

		panel2.add(text);
		panel2.add(playButton);
	}

	//button listener
	private class PlayButtonListener implements ActionListener
    {
        // Called when the button is pushed.
        public void actionPerformed(ActionEvent e)
        {
        	playButton.setText("Next Hand");
            g.playHand();
            update();
            
            // If the game is over, determine winner
            if (g.getState() != 0)
            {
            	
	            if (g.getState() < 0)
	            {
	            	javax.swing.JOptionPane.showMessageDialog(null, "Player 1 Wins!");
	            }
	            else 
	            {
	            	javax.swing.JOptionPane.showMessageDialog(null, "Player 2 Wins!");
	            }
	            
	            updateText(); // update the text displayed
               // Disable the continue button
	            playButton.setEnabled(false);
            }
            

        }
    }
    /**
      update method
      updates everything after an event has occured
      
   */

    public void update()
    {
        updateCardImages();
        updateText();
        updateCount();
    }

	/**
      updateCardImages method
      updates the card images for the cards
      
   */

	public void updateCardImages()
    {
    	//if player has cards show the back of cards in their deck
        if (g.hasCardsLeft(1))
        {
            p1Deck.setIcon(back);
        }
        else
        {
            p1Deck.setIcon(null);
        }
        p1Deck.revalidate(); 
        
        // give top card correct image
        if (g.getPlayerCard(1) != null)
        {
            try
            {
                p1Pile.setIcon(new ImageIcon(ImageIO.read(new File(g.getCardImage(1)))));
            }
            catch(IOException e)
            {
                System.err.println("Card image not found.");
                  
            }
        }
        else
        {
            p1Pile.setIcon(null);
        }
        p1Pile.revalidate(); 
        
        // Repeat for player 2
        if (g.hasCardsLeft(2))
        {
            p2Deck.setIcon(back);
        }
        else
        {
            p2Deck.setIcon(null);
        }
        p2Deck.revalidate(); 
        
        // Set the player's top card on the pile to the correct image
        if (g.getPlayerCard(2) != null)
        {
            try
            {
                p2Pile.setIcon(new ImageIcon(ImageIO.read(new File(g.getCardImage(2)))));
            }
            catch(IOException e)
            {
                System.err.println("Card image not found.");
                   
            }
        }
        else
        {
            p2Pile.setIcon(null);
        }
        p2Pile.revalidate();
    }
	/**
      updateText method
      updates the text displayed to the user
      
   */
	public void updateText()
    {
    	String displayText = ""+g.getText()+ "\nPlayer 1 deck size: " + g.getDeckSize(1) + "\nPlayer 2 deck size: " + g.getDeckSize(2);
    			
    	try
    	{
    		text.setText(displayText);
    
    	}
    	catch (NullPointerException e)
    	{
    		System.out.println("An error has occured.");
    	}
    }
    /**
      updateCount method
      updates the count of cards in a deck
      
   */

    public void updateCount()
    {
        p1Count.setText(String.valueOf(g.getDeckSize(1)));
        p2Count.setText(String.valueOf(g.getDeckSize(2)));
    }
    //run it
	public static void main(String[] args) 
	{
	    WarGUI w = new WarGUI();
	}
}