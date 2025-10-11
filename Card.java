package cardGame;

//The Card class represents a single playing card
public class Card {

 // Each card has a numeric value (2–14, where 11=Jack, 12=Queen, 13=King, 14=Ace)
 private int value;

 // Each card also has a name, like "Ace of Hearts"
 private String name;

 // This Constructor runs when you create a new Card object
 public Card(int value, String name) {
     this.value = value;   // Assign the value argument to the field
     this.name = name;     // Assign the name argument to the field
 }

 // Getter returns the card's value
 public int getValue() {
     return value;
 }

 // Setter changes the card's value
 public void setValue(int value) {
     this.value = value;
 }

 // Getter for name
 public String getName() { 
     return name;
 }

 // Setter for name
 public void setName(String name) {
     this.name = name;
 }

 // Prints out information about the card
 public void describe() {
     System.out.println(name + " (value: " + value + ")");
 }


	

	}


