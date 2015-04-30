/**
  Nick Forand
  CS 110
  Deck Class
  Representation of a Deck of cards.  
  Initialized to a standard 52 card deck. 
 
 */

import java.util.Random;
import java.util.ArrayList;

public class Deck 
{
    
   //Number of cards in standard deck
   
   final int CARDS_IN_DECK = 52;

   /** The collection of Cards */
   private ArrayList<Card> deck;
   
   /**
    * Constructs a regular 52-card deck.  Initially, the cards
    * are in a sorted order.  The shuffle() method can be called to
    * randomize the order.  
    */
   public Deck()
   {
            freshDeck();
   }
   /**
     Constructor
     Constructs a new deck from an existing one
   */

   public Deck(Deck d)
   {
      int length = d.cardsRemaining();
      deck = new ArrayList<Card>();
      for(int i=0;i<length;i++)
         deck.add(d.getCard(i));

   }
   /**
      Constructor
      Constructs a deck out of another deck using specific indexes
   */
   public Deck(Deck d, int start, int end)
   {
      //set cards in deck to length of indexes
      final int CARDS_IN_DECK = end-start;
      Deck original = new Deck(d);
      int length = original.cardsRemaining();
      deck = new ArrayList<Card>();
      
      for(int i=0;i<start && i<=length;i++)
         original.dealCard();
      for(int i=0;i<CARDS_IN_DECK;i++)
         deck.add(original.dealCard());
   }


   /**
    * Create a new collection of 52 cards, in sorted order
    */
   public void freshDeck()
   {
      deck = new ArrayList<Card>();
      System.out.println(deck.size());

      for (int r = Card.ACE; r<=Card.KING;r++)
      {
         for (int s=Card.SPADES;s<=Card.CLUBS;s++)
         {
           deck.add(new Card(r,s));
         }
      }
      
     
   
   }
   /** 
     * Remove and return the top Card on the Deck
     * @return A reference to a Card that was top on the Deck
     */
   public Card dealCard()
   {
      Card c = deck.remove(0);  //  remove it (returns removed object)
      return c;
   }
   /** 
     * Return current number of Cards in Deck
     * @return number of Cards in Deck
     */

   public int cardsRemaining()
   {  
      return deck.size();
   }
   /** 
     * Randomize the order of Cards in Deck
     */

   public void shuffle()
   {
      int randNum;
      Card temp;
      Random r = new Random();
      for (int i = 0; i < deck.size(); i++)
      {
         randNum = r.nextInt(deck.size());
         temp = deck.get(i);
         deck.set(i,deck.get(randNum));
         deck.set(randNum,temp);
      }      
   }
   /** 
     * Determine if Deck is empty
     * @return true if there are no more cards, false otherwise
     */
   
   public boolean isEmpty()
   {
      return (deck.size() == 0);
   }

     public static Card highCard(Card...cards)
   {
   
      Card high = cards[0];
      for (int i=1;i<cards.length;i++)
      {
         if (cards[i].getRank() > high.getRank())
         {
         
            high = cards[i];
         }
      }
      return high;
   
   }
   /**
    getCard method
    @return c the card object
    */

   public Card getCard(int i)
   {
      Card c = deck.get(i);
      return c;
   }
   /**
    addCard method
    @param card to be added to dexk
    adds card to deck
    */

   public void addCard(Card c)
   {
      deck.add(c);
   }
}
