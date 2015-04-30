/* Nick Forand
 CS 110
 Card Class
*/
import java.util.Scanner;
import javax.swing.JOptionPane;

public class Card
{
   //variables to hold rank and suit respectively
   private static int rank;
   private static int suit;
   //variable to hold imagename
   private String imageName;
   //suits
   public final static int SPADES = 1;
   public final static int CLUBS = 2;
   public final static int HEARTS = 3;
   public final static int DIAMONDS = 4;
   //ranks
   public final static int ACE = 1;
   public final static int TWO = 2;
   public final static int THREE = 3;
   public final static int FOUR = 4;
   public final static int FIVE = 5;
   public final static int SIX = 6;
   public final static int SEVEN = 7;
   public final static int EIGHT = 8;
   public final static int NINE = 9;
   public final static int TEN = 10;
   public final static int JACK = 11;
   public final static int QUEEN = 12;
   public final static int KING = 13;
   
   /**
      Constructor
      @param rank, the rank of the card
      @param suit, the suit of the card
   */

   public Card(int r, int s)
   {
      rank = r;
      suit= s;
      imageName = "cards/" + rank + suit + ".jpg";
   }
   /**
      getSuit method
      @return suit, the suit of the card
   */

    public int getSuit() 
    {
        return suit;
    }
    /**
      getRank method
      @return rank, the rank of the card
   */

    public int getRank()
    {
        return rank;
    }
    /** getRankAsString method
        @return rank as a string(ace,king...)
    */
    public static String getRankAsString()
    {
      switch (rank) 
      {
        case ACE:
            return "Ace";
        case TWO:
            return "Deuce";
        case THREE:
            return "Three";
        case FOUR:
            return "Four";
        case FIVE:
            return "Five";
        case SIX:
            return "Six";
        case SEVEN:
            return "Seven";
        case EIGHT:
            return "Eight";
        case NINE:
            return "Nine";
        case TEN:
            return "Ten";
        case JACK:
            return "Jack";
        case QUEEN:
            return "Queen";
        case KING:
            return "King";
        default:
             return null;
          
           
       }
     }
     /** getSuitAsString method
        @return suit as a string(diamonds,clubs...)
     */

     public static String getSuitAsString()
     {
        switch (suit)
        {
        case DIAMONDS:
            return "Diamonds";
        case CLUBS:
            return "Clubs";
        case HEARTS:
            return "Hearts";
        case SPADES:
            return "Spades";
        default:
            return null;
        }
     } 
     /** getSuitAsString method
         @return card as a string using methods getRankAsString and getSuitAsString
     */ 
     public String toString()
     {
         return getRankAsString() + "of" + getSuitAsString();
     }
     /** equals method
         @param Card object card
         @return boolean about state of equality of the two cards
     */
      public boolean equals(Card other)
      {
      int second = other.getRank();
      if (rank == second)
         return true;
      else
         return false;
      }
      /** getImageName method
         @return imageName, string holding the card's image name
     */ 
     public String getImageName()
     {
         return imageName;
     }
     
}
   
   
