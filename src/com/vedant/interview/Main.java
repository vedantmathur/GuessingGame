package com.vedant.interview;

import java.util.Random;
import java.util.Scanner;

public class Main {

	public static Scanner scan = new Scanner(System.in);
	public static Random rand = new Random();
	public static int MAX_ALLOWED_GUESSES = 10;
	public static int MIN_ALLOWED_RANGE = 10;

	public static boolean rangeCorrect = false;
	public static boolean correctGuess = false;
	public static int minval = 0, maxval = 100, guessCount = 0;
	public static int[] UserGuesses = new int[1];

	public static void main(String[] args) {

		int gameSel = 0;

		System.out.println("Username: ");
		String userName = scan.nextLine();
		Boolean validResponse = false;
		do {
			System.out.println(
					"Would you like to play \n[1] The Guessing Game\n[2] The Reverse Guessing Game?\n[3] Exit");
			String playGame = scan.nextLine();
			if (isANumber(playGame)) {
				gameSel = Integer.parseInt(playGame);
			}

			if (gameSel == 1) {
				System.out.println("Welcome to Guessing Game, " + userName);
				GuessingGame(userName);
				validResponse = true;
			} else if (gameSel == 2) {
				System.out.println("Welcome to Reverse Guessing Game, " + userName);
				ReverseGuessingGame(userName);
				validResponse = true;
			} else if (gameSel == 3) {
				System.exit(0);

			}

			if (!validResponse) {
				System.out.println("Not a valid response!");
			}

		} while (!validResponse);

	}

	// The code for the Guessing Game
	public static void GuessingGame(String userName) {

		// Reset variables from previous games
		resetVars();
		int randomNumberGen = 50, guess = 51;

		System.out.println("Please input a range");

		rangeValidation();

		System.out.println("Your minimum value is " + minval + ", and your maximum value is " + maxval);

		randomNumberGen = rand.nextInt(maxval) + minval; // Random number
		// assignment,
		// default is 50
		System.out.println("I have created a random number between those values. Try to guess!" + randomNumberGen);

		do {
			System.out.println("Guess: ");
			String guessS = scan.nextLine();
			if (isANumber(guessS)) {
				guess = Integer.parseInt(guessS);
			}

			// Register Guess and add to array
			guessCount++;
			UserGuesses = addListNum(UserGuesses, guess);

			if (guess == randomNumberGen)
				correctGuess = true;
			else if (guess < randomNumberGen) {
				System.out.println("Too Low! Guess again");
			} else if (guess > randomNumberGen) {
				System.out.println("Too High! Guess again");
			}

			// End after MIN_ALLOWED_GUESSES guesses
			if (guessCount > MAX_ALLOWED_GUESSES) {
				break;
			}

		} while (!correctGuess);
		if (correctGuess) {
			System.out.println("Congrats, " + userName + ", you guessed my number in " + guessCount + " attempts");
		} else {
			System.out.println("Sorry Bob, you did not guess my number - it's" + randomNumberGen + "!");
		}

		System.out.println("Here's a list of your prior guesses: ");
		for (int i = 1 /* Set to 1 to ignore value 0 in array */; i < UserGuesses.length; i++) {
			System.out.print(UserGuesses[i]);

			// Commas are 1 less than the numbers
			if (i < UserGuesses.length - 1) {
				System.out.print(", ");
			}
		}

	}

	// The code for the Reverse Guessing Game
	public static void ReverseGuessingGame(String userName) {

		// Reset variables from previous games
		resetVars();
		boolean rangeCorrect = false;
		boolean correctGuess = false;
		int response = 0;

		System.out.println("Please think of a number for me to guess");
		System.out.println("Please input a range for me to guess from");

		rangeValidation();

		System.out.println("Your minimum value is " + minval + ", and your maximum value is " + maxval);

		do {
			int computerGuess = guess(minval, maxval);

			// Register guess
			guessCount++;
			UserGuesses = addListNum(UserGuesses, computerGuess);

			System.out.println("Is your number " + computerGuess + "? \t Enter 1 if my guess was too low, 2 if it's too high, and 3 if it's right on!");
			String responseString = scan.nextLine();
			if (isANumber(responseString)) {
				response = Integer.parseInt(responseString);
			}

			if (response == 3) {
				System.out.println("Yay! I won!");
				correctGuess = true;
			} else if ((maxval - minval) <= 2) {
				System.out.println("Assert: " + computerGuess);
				correctGuess = true;
			} else if (response == 2) {
				maxval = computerGuess;
			} else if (response == 1) {
				minval = computerGuess;
			} else {
				System.out.println("Incorrect entry");
			}

		} while (!correctGuess);
		System.out.print("It took me " + guessCount + " tries. Here is a list of my guesses: ");

		for (int i = 1 /* Set to 1 to ignore value 0 in array */; i < UserGuesses.length; i++) {
			System.out.print(UserGuesses[i]);

			// Commas are 1 less than the numbers
			if (i < UserGuesses.length - 1) {
				System.out.print(", ");
			}
		}
	}

	// This method makes sure the range is valid and doesn't complete until its
	// within parameters. If minval/maxval are not numbers, then its set to a
	// common value to trigger an error
	public static void rangeValidation() {
		do {

			// MIMIMUM default is 0
			System.out.println("Minimum Value: ");
			String minvalString = scan.nextLine();
			// Send to check if it's a number
			if (isANumber(minvalString)) {
				minval = Integer.parseInt(minvalString);
			} else {
				minval = 0;
			}

			// MAXIMUM default is 100
			System.out.println("Maximum Value: ");
			String maxvalString = scan.nextLine();
			// Send to check if it's a number
			if (isANumber(maxvalString)) {
				maxval = Integer.parseInt(maxvalString);
			} else {
				maxval = 0;
			}

			// RANGE IS VALIDATED
			if (minval < maxval && (minval + MIN_ALLOWED_RANGE) <= maxval) {
				rangeCorrect = true;
			} else {
				System.out.println("The minimum has to be less then the maximum, and at least " + MIN_ALLOWED_RANGE
						+ " numbers apart. Both values have to be integers.");
			}

		} while (!rangeCorrect);
	}

	// This method resets variables back to standard
	public static void resetVars() {
		rangeCorrect = false;
		correctGuess = false;
		minval = 0;
		maxval = 100;
		guessCount = 0;
		int[] UserGuesses = new int[1];
	}

	// This method adds a number to an array, a list of guesses
	public static int[] addListNum(int[] arr, int val) {

		int[] temp = new int[arr.length + 1];
		for (int i = 0; i < arr.length; i++) {
			temp[i] = arr[i];
		}
		temp[arr.length] = val;

		int[] arrdone = temp;

		return arrdone;
	}
	
	// This method makes sure the String inputed is indeed a number
	public static boolean isANumber(String inRange) {
		try {
			Integer.parseInt(inRange);
			return true;
		} catch (/* GOOGLED */ NumberFormatException e) {
			return false;
		}
	}

	// This method generates a guess for the computer to give for the reverse
	// guessing game
	public static int guess(int minval, int maxval) {
		int guessVal = (maxval + minval) / 2;

		return guessVal;

	}
}
