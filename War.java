/*CS110
  Homework 10
  Nick Forand
  war
*/
import java.util.ArrayList;
public class War
{
   //initalize decks for each player
   Deck p1Deck,p2Deck;
   //initalize playing card for each player
   Card p1Card, p2Card,p1Card2,p2Card2;
   //initialize winnings piles
	ArrayList<Card> p1Pile = new ArrayList<Card>();
	ArrayList<Card> p2Pile = new ArrayList<Card>();
   //create middle array
	ArrayList<Card> middlePile = new ArrayList<Card>();
   //initalize variables for GUI
   //gamestate int default to ongoing play mode
	int state=0;
	//int to identify winner of the hand
	int winner;
	//initalize string to hold messages to be displayed
	String text = "";

   /**
      Constructor
      
   */
   public War()
   {
   //create a deck
   Deck d = new Deck();
   //shuffle
   d.shuffle();
   //give each player half the deck
   p1Deck = new Deck(d,0,26);
	p2Deck = new Deck(d,26,52);
   text = "The game is about to begin!";
   }
   
   /**
      getPlayerCard method
      @param int p ---the player whose card is being examined
      @p1Card/p2Card ---the card of the requested player
      
   */

   public Card getPlayerCard(int p)
	{
		switch(p)
		{
			case 1:
				return p1Card;
			case 2:
				return p2Card;
			default:
				return null;
      }
    }
    /**
      getCardImage method
      @param int p ---the player whose card an image is needed
      @p1Card/p2Card ---the card of the requested player
      
   */

    public String getCardImage(int p)
	{
		switch(p)
		{
			case 1:
				return p1Card.getImageName();
			case 2:
				return p2Card.getImageName();
			default:
				return "cards/back.jpg";
		}
	}
   /**
      getCardsLeft method
      @param int p ---the player whose deck is being examined
      @return # of cards left
      
   */

   public int getCardsLeft(int p)
	{
		switch(p)
		{
			case 1:
				return p1Deck.cardsRemaining();
			case 2:
				return p2Deck.cardsRemaining();
			default:
				return 0; 
		}
	}
   /**
      getState method
      @return state----the state of the game
      
   */

	public int getState()
	{
		return state;
	}
   /**
      getText method
      @return text---the text to be displayed on the GUI
      
   */

	public String getText()
	{
		return text;
	}
   /**
      hasCardsLeft method
      @param int p ---the player whose deck is being examined
      @return # of cards left
      
   */

	public boolean hasCardsLeft(int p)
	{
		switch(p)
		{
			case 1:
				return !p1Deck.isEmpty();
			case 2:
				return !p2Deck.isEmpty();
			default:
				return false; 
		}
	}

	/**
      playHand method
      @param int p ---the player whose deck is being examined
      @return # of cards left
      
   */

	public void playHand()
	{
	   //check if player 1's deck is empyty
		if(!p1Deck.isEmpty())
		{
			//play top card and second card(burn car)
			p1Card = p1Deck.dealCard();
         p1Card2 = p1Deck.dealCard();
		}
		//if cards are empty, check winning pile
		else if(!p1Pile.isEmpty())
		{
			//shuffle deck and play top card
			int length = p1Pile.size();
			for(int i=0;i<length;i++)
				p1Deck.addCard(p1Pile.remove(0));
			p1Deck.shuffle();
			p1Card = p1Deck.dealCard();
         p1Card2 =p1Deck.dealCard();
		}
		//if p1 is out of cards ---p2 wins
		else
		{ 
			p2Win();
			
		}

		//repeat for player 2
		//check if p2 has cards
		if(!p2Deck.isEmpty())
		{
			//play top card
			p2Card = p2Deck.dealCard();
         p2Card2 = p2Deck.dealCard();
		}
		//if p2 is out of cards, check if he has any winnings
		else if(!p2Pile.isEmpty())
		{
			//shuffle p2 deck and play top card
			int length = p2Pile.size();
			for(int i=0;i<length;i++)
				p2Deck.addCard(p2Pile.remove(0));
			p2Deck.shuffle();
			p2Card = p2Deck.dealCard();
         p2Card2 = p2Deck.dealCard();
		}
		//else
		else
		{ 
			//p1 wins
			p1Win();
		}


		//once both cards have been played, compare them
      
		if(p1Card.getRank()>p2Card.getRank())
		{
			//p1 wins. designate 1 to mean p1 won 
			
			winner=1;
		}
		
		else if(p2Card.getRank()>p1Card.getRank())
		{
			//p2 wins. designate 2 to mean p2 won
			winner=2;
		}
		//else there is a tie----war
		else
		{
		//use 0 to designate war
			winner=0;
		}
		
		text = "Player 1 drew "+p1Card.toString()
		+"\nPlayer 2 drew "+p2Card.toString(); 
		//add all the cards to the center
		middlePile.add(p1Card);
      middlePile.add(p1Card2);
		middlePile.add(p2Card);
      middlePile.add(p2Card2);
		//give winnings/start war
		int length = middlePile.size();
		switch(winner)
		{
			case 1:
				text+=", Player 1 wins the hand.";
				for (int i=0;i<length;i++) 
				{
					p1Pile.add(middlePile.remove(0));
				}
				break;
			case 2:
				text+=", Player 2 wins the hand.";
				for (int i=0;i<length;i++) 
				{
					p2Pile.add(middlePile.remove(0));
				}
				break;
			default:
				text+=", war!";
				middlePile.add(p1Deck.dealCard());
				middlePile.add(p2Deck.dealCard());
		}

	}
   /**
      p1win method
      method sets state to 1 to designate p1 won
      
   */

	private void p1Win()
	{
		state = 1;
	}
   /**
      p2win method
      method sets state to 2 to designate p2 won
      
   */

	private void p2Win()
	{
		state = 2;
	}
    /**
      getDeckSize method
      @param p---int of the player
      @return text---the text to be displayed on the GUI
      
   */

   public int getDeckSize(int p)
	{
		switch(p)
		{
			case 1:
				return p1Deck.cardsRemaining();
			case 2:
				return p2Deck.cardsRemaining();
			default:
				return 0; 
		}
	}

		

}