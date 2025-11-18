package week4;

public class project {

	public static void main(String[] args) {
		
		// used [] to create an array
		int[] ages = {3, 9, 23, 64, 2, 8, 28, 93};
		// ages.length - 1 gives us the index of the last element
		int result = ages[ages.length - 1] - ages[0];
		// result is 90
		System.out.println("Result: " + result);
		
		// added my own values
		int[] ages2 = {4, 10, 16, 25, 31, 53, 19, 47, 62, 86};
		// ages.length - 1 gives us the index of the last element
		int result2 = ages2[ages2.length - 1] - ages[0];
		System.out.println("Result: " + result2);
	
	
	for (int i = 0; i < ages2.length; i++) {
	    System.out.println("ages2[" + i + "] = " + ages2[i]);
	    //this loop works for arrays of any length showing that it works dynamically
	}
	    
    
    String[] names = {"Sam", "Tommy", "Tim", "Sally", "Buck", "Bob" };  
    //String array of names (6 elements, 5 indexes)
    
    int totalLetters = 0;
    // making a variable that starts at 0 for the loop
     
    for (int l = 0; l < names.length; l++) {
    	totalLetters += names[l].length();
    	// names.length gives us 6, so the loop will run from l=0 to l=5
    	// names{l} gives the current name
    	// .length gives the number of letters in the name
    	// +- adds that to totalLetters
    }
    // Trying out String Builder
    // Start with an empty String so we have a starting point to build out the results
    StringBuilder allNames = new StringBuilder();

    // Loop through array
    for (int i = 0; i < names.length; i++) {
        allNames.append(names[i]); // adds the current name to the builder

        // Add a space until the last element is listed
        if (i < names.length - 1) {
            allNames.append(" ");
        }
    }
    
    System.out.println("Concatenated names: " + allNames.toString());
    //Change the StringBuilder back to String
    
    
    double average = (double) totalLetters / names.length;
    
    System.out.println("Average letters per name: " + average);
    
    // Specify Index within the class
    
    // First Element
    String first = names[0];
    
    // Last element
    String last = names[5];
    
    System.out.println("First element: " + first);
    System.out.println("Last element: " + last);
    
 // New int array with the same length as names
    int[] nameLengths = new int[names.length];

    // Fill nameLengths with the length of each name
    for (int i = 0; i < names.length; i++) {
        nameLengths[i] = names[i].length();
    }

    // Print nameLengths to check
    System.out.println("Contents of nameLengths:");
    for (int i = 0; i < nameLengths.length; i++) {
        System.out.println("nameLengths[" + i + "] = " + nameLengths[i]);
    }
    // Making a quick loop for our previous code
    // Calculate sum of all elements in nameLengths
    int sum = 0;
    for (int i = 0; i < nameLengths.length; i++) {
        sum += nameLengths[i];
    }

    // Print result
    System.out.println("Sum of all elements in nameLengths: " + sum);
    
    
    
    String tripleHello = multiplyString("Hello", 3);
    System.out.println(tripleHello);
    
    // Create fullName method here to keep information in the main method
    String fullName = createFullName("Hunter", "Teixeira");
    System.out.println("Full name: " + fullName);
    
    
    //
    int[] testArray = {20, 30, 25, 40}; 
    boolean result1 = isSumGreaterThan100(testArray);
    System.out.println("Is the sum greater than 100? " + result1);
    
    
    //created double values and kept them in main. Method is stored below
    double[] values = {10.5, 20.0, 30.5, 40.0};
    double avg = calculateAverage(values);
    System.out.println("Average: " + avg);
    
    
    double[] numbers1 = {10.0, 20.0, 30.0};
    double[] numbers2 = {5.0, 15.0, 25.0};

    boolean result3 = isFirstAverageGreater(numbers1, numbers2);
    System.out.println("Is the first average greater? " + result3);
    
    
    boolean buy = willBuyDrink(true, 12.00);
    System.out.println("Will buy drink? " + buy);

    boolean buy2 = willBuyDrink(false, 20.00);
    System.out.println("Will buy drink? " + buy2);
    
    
        double total = totalRigCost(999.99, 599.99, 349.99);
        System.out.println("Total cost of rig: $" + total);
}
    
	

	

	//Create a new method for multiplyString
	public static String multiplyString(String str, int num) {
		String result = ""; //Starting string to build results off of
		for (int n = 0; n < num; n++) {
			result += str;
		}
		return result; //stores built string into tripleHello
	}
	//Created the method here for the full name
	 public static String createFullName(String firstName, String lastName) {
	        return firstName + " " + lastName;
	

	    }
	 //Created method that gives us a boolean of sum > 100
	 public static boolean isSumGreaterThan100(int[] numbers) {
		    int sum = 0;

		    // Loop through array and calculate sum
		    for (int i = 0; i < numbers.length; i++) {
		        sum += numbers[i];
		    }

		    // Return true if sum > 100, otherwise false
		    return sum > 100;
		}
	 
	 
	 public static double calculateAverage(double[] numbers) {
		    double sum = 0;

		    // Loop through array and add up all values
		    for (int i = 0; i < numbers.length; i++) {
		        sum += numbers[i];
		    }

		    // Divide sum by the number of elements
		    return sum / numbers.length;
		}
	 
	 public static boolean isFirstAverageGreater(double[] array1, double[] array2) {
		    double sum1 = 0;
		    double sum2 = 0;

		    // Sum of first array
		    for (int i = 0; i < array1.length; i++) {
		        sum1 += array1[i];
		    }

		    // Sum of second array
		    for (int j = 0; j < array2.length; j++) {
		        sum2 += array2[j];
		    }

		    // Calculate averages question 11
		    double avg1 = sum1 / array1.length;
		    double avg2 = sum2 / array2.length;

		    // Return true if avg1 > avg2
		    return avg1 > avg2; 
		    
	 }
	 
	 
	 public static boolean willBuyDrink(boolean isHotOutside, double moneyInPocket) {
		    return isHotOutside && moneyInPocket > 10.50;
		}
	 // I enjoy playing guitar so I made a method that gives me the total cost of a guitar rig based on the price of a guitar, an amp, and a speaker cabinet
	 public static double totalRigCost(double guitarPrice, double ampPrice, double cabinetPrice) {
		    return guitarPrice + ampPrice + cabinetPrice;
		}
	 
	 
	 
	 
	 
	 
}
	

	

 
	
	




 




	

