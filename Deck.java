package cardGame;

import java.util.*; // Import List, ArrayList, and Collections

//The Deck class represents a full deck of 52 playing cards
public class Deck {

 // List of Card objects (the deck)
 private List<Card> cards;

 // Constructor builds all 52 cards when a Deck is created
 public Deck() {
     cards = new ArrayList<>();

     // Define the 4 suits
     String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};

     // Define card names from 2 to Ace
     String[] faceNames = {"Two", "Three", "Four", "Five", "Six", "Seven",
                           "Eight", "Nine", "Ten", "Jack", "Queen", "King", "Ace"};

     // Nested loop: for every suit, create one of each value
     for (String suit : suits) {
         for (int i = 0; i < faceNames.length; i++) {
             int value = i + 2; // Because Two = 2, Ace = 14
             String name = faceNames[i] + " of " + suit;
             cards.add(new Card(value, name)); // Add a new Card to the deck
         }
     }
 }

 // Shuffles the deck randomly using Collections.shuffle()
 public void shuffle() {
     Collections.shuffle(cards);
     System.out.println("Deck has been shuffled!\n");
 }

 // Removes and returns the top card (index 0)
 public Card draw() {
     return cards.remove(0);
 }

 // Helper to check how many cards are left
 public int size() {
     return cards.size();
 }
}
