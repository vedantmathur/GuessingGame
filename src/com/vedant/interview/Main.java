package com.vedant.interview;

import java.util.Scanner;
import java.util.Random;

public class Main {

	public static Scanner scan = new Scanner(System.in);
	public static Random rand = new Random();

	public static void main(String[] args) {

		int gameSel = 0;

		System.out.println("Username: ");
		String userName = scan.nextLine();
		Boolean validResponse = false;
		do{
			System.out.println("Would you like to play The Guessing Game [1] or the Reverse Guessing game [2]?: ");
			String playGame = scan.nextLine();
			if (isANumber(playGame)){
				gameSel = Integer.parseInt(playGame);
			}

			if (gameSel == 1){
				System.out.println("Welcome to Guessing Game, " + userName);
				GuessingGame();
				validResponse = true;
			} else if (gameSel == 2){
				System.out.println("Welcome to Reverse Guessing Game, " + userName);
				//ReverseGuessingGame();
				validResponse = true;
			}
			
			if (!validResponse){
				System.out.println("Not a valid response!");
			}

		} while (!validResponse);


	}

	public static int[] addListNum(int[] arr, int val) {

		int[] temp = new int[arr.length + 1];
		for (int i = 0; i < arr.length; i++) {
			temp[i] = arr[i];
		}
		temp[arr.length] = val;

		int[] arrdone = temp;

		return arrdone;
	}

	public static boolean isANumber(String inRange) {
		try {
			Integer.parseInt(inRange);
			return true;
		} catch (/* GOOGLED */ NumberFormatException e) {
			return false;
		}
	}

	public static void GuessingGame() {
		boolean rangeCorrect = false;
		boolean correctGuess = false;
		int minval = 0, maxval = 100;
		int randomNumberGen = 50, guessCount = 0, guess = 51;
		int[] UserGuesses = new int[1];

		System.out.println("Please input a range");

		do {
			// MIMIMUM default is 0
			System.out.println("Minimum Value: ");
			String minvalS = scan.nextLine();
			// Send to check if it's a number
			if (isANumber(minvalS)) {
				minval = Integer.parseInt(minvalS);
			}

			// MAXIMUM default is 100
			System.out.println("Maximum Value: ");
			String maxvalS = scan.nextLine();
			if (isANumber(maxvalS)) {
				maxval = Integer.parseInt(maxvalS);
			}

			// NUMBERCHECK
			if (minval < maxval && (minval + 10) <= maxval) {
				rangeCorrect = true;
			} else {
				System.out.println("The minimum has to be less then the maximum, and at least 10 numbers apart "
						+ minval + " " + maxval);
			}

		} while (!rangeCorrect);
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

			guessCount++;
			addListNum(UserGuesses, guess);

			if (guess == randomNumberGen)
				correctGuess = true;
			else if (guess < randomNumberGen) {
				System.out.println("Too Low! Guess again");
			} else if (guess > randomNumberGen) {
				System.out.println("Too High! Guess again");
			}

			if (guessCount > 10) {
				break;
			}

		} while (!correctGuess);
		System.out.println(randomNumberGen + " was correct! It took you " + guessCount + " tries");

	}

	public static void ReverseGuessingGame() {

	}
}
