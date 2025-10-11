package cardGame;

import java.util.*; // Import List and ArrayList

//The Player class represents one player in the game
public class Player {

 private List<Card> hand;  // Cards currently held by the player
 private int score;        // Player’s score (starts at 0)
 private String name;      // Player’s name

 // Constructor initializes fields when a Player is created
 public Player(String name) {
     this.name = name;         // Set the name
     this.score = 0;           // Start score at 0
     this.hand = new ArrayList<>(); // Create an empty hand
 }

 // Print out the players name, score, and their hand of cards
 public void describe() {
     System.out.println(name + " | Score: " + score);
     for (Card card : hand) {      // Loop through hand
         card.describe();          // Call describe() on each Card
     }
 }

 // Removes and returns the top card from the players hand
 public Card flip() {
     return hand.remove(0);
 }

 // Draws one card from a deck and adds it to this players hand
 public void draw(Deck deck) {
     hand.add(deck.draw());
 }

 // Adds one point to this players score
 public void incrementScore() {
     score++;
 }

 // Getters
 public int getScore() {
     return score;
 }

 public String getName() {
     return name;
 }
}
