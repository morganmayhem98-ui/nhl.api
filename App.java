package cardGame;

//The App class runs the game using the other classes
public class App {
 public static void main(String[] args) {

     // 1️ Create a deck and two players
     Deck deck = new Deck();                  // Build a full 52-card deck
     Player player1 = new Player("Player 1"); // Make Player 1
     Player player2 = new Player("Player 2"); // Make Player 2

     // 2️ Shuffle the deck before dealing
     deck.shuffle();

     // 3️ Deal all 52 cards, alternating between players
     for (int i = 0; i < 52; i++) {
         if (i % 2 == 0) {
             player1.draw(deck); // Even numbers → Player 1
         } else {
             player2.draw(deck); // Odd numbers → Player 2
         }
     }

     System.out.println("=======================================");
     System.out.println("  LET THE GAMES BEGIN!  ");
     System.out.println("=======================================\n");

     // 4️ Play 26 rounds (each player flips one card per round)
     for (int i = 0; i < 26; i++) {

         System.out.println("----------  Round " + (i + 1) + " ----------");

         // Each player flips their top card
         Card card1 = player1.flip();
         Card card2 = player2.flip();

         // Show the cards
         System.out.print(player1.getName() + " plays: ");
         card1.describe();

         System.out.print(player2.getName() + " plays: ");
         card2.describe();

         // Compare card values
         if (card1.getValue() > card2.getValue()) {
             // Player 1 wins the round
             player1.incrementScore();
             System.out.println(player1.getName() + " wins this round!");
         } else if (card1.getValue() < card2.getValue()) {
             // Player 2 wins the round
             player2.incrementScore();
             System.out.println(player2.getName() + " wins this round!");
         } else {
             // Tie — no points
             System.out.println("It's a tie! No points awarded.");
         }

         // Show running score after each round
         System.out.println("Current Score - "
                 + player1.getName() + ": " + player1.getScore() + " | "
                 + player2.getName() + ": " + player2.getScore());
         System.out.println("---------------------------------------\n");
     }

     // 5️ After 26 rounds, show the final results
     System.out.println("=======================================");
     System.out.println(" GAME OVER ");
     System.out.println("=======================================\n");

     // Show final scores
     System.out.println("Final Scores:");
     System.out.println(player1.getName() + ": " + player1.getScore());
     System.out.println(player2.getName() + ": " + player2.getScore() + "\n");

     // Determine the overall winner
     if (player1.getScore() > player2.getScore()) {
         System.out.println(player1.getName() + " WINS THE GAME!");
     } else if (player2.getScore() > player1.getScore()) {
         System.out.println(player2.getName() + " WINS THE GAME!");
     } else {
         System.out.println("It's a DRAW! Both players are evenly matched.");
     }

     System.out.println("\n=======================================");
     System.out.println("Thanks for playing War!");
     System.out.println("=======================================");
 }
}