package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Engine {

    // if hard is chosen fori = i = 3, if easy is chosen fori = i = 1
    // takes the easy first word or the hard 3rd word.
    static ArrayList<Character> word = new ArrayList<Character>();
    static ArrayList<Character> hiddenword = new ArrayList<Character>();
    static ArrayList<Character> guessedLetters = new ArrayList<Character>();
    static ArrayList<String> wordLists = new ArrayList<String>();
    static int lives = 6;
    static char currentChosenLetter;
    static String chosenDifficulty;
    static File HangmanEasy = new File("Resources/HangmanEasy.csv");
    static File HangmanNormal = new File("Resources/HangmanNormal.csv");
    static File HangmanHard = new File("Resources/HangmanHard.csv");
    static Scanner scaninput = new Scanner(System.in);
    static Scanner scannerHangmanEasy;
    static Scanner scannerHangmanNormal;
    static Scanner scannerHangmanHard;

    static {
        try {
            scannerHangmanEasy = new Scanner(HangmanEasy);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    static {
        try {
            scannerHangmanNormal = new Scanner(HangmanNormal);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    static {
        try {
            scannerHangmanHard = new Scanner(HangmanHard);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void startMessage() {
        System.out.println("Welcome to Hangman");
    }

    public static ArrayList<String> buildWordArray() {
        System.out.println("Choose difficulty\nType:\n 1 - Easy\n 2 - Normal\n 3 - Hard");
        chosenDifficulty = scaninput.nextLine();
        switch (chosenDifficulty) {
            case "1":
                while (scannerHangmanEasy.hasNext()) {
                    wordLists.add(scannerHangmanEasy.nextLine());
                }
                break;

            case "2":
                while (scannerHangmanNormal.hasNext()) {
                    wordLists.add(scannerHangmanNormal.nextLine());
                }
                break;
            case "3":
                while (scannerHangmanHard.hasNext()) {
                    wordLists.add(scannerHangmanHard.nextLine());
                }
                break;
            default:
                System.out.println("No words available");
        }
        return wordLists;
    }

    // +1 pga ---easy--- +2 pga ---normal---
    public static ArrayList getWord() {
        Random random = new Random();
        String wordToGuess;
        wordToGuess = wordLists.get(random.nextInt(wordLists.size()) + 1);
        for (int i = 0; i < wordToGuess.length(); i++) {
            word.add(wordToGuess.toUpperCase().charAt(i));
        }
        return word;
    }

    //Create an Arraylist for the word to guess
    public static ArrayList createHiddenWord() {
        for (int i = 0; i < word.size(); i++) {
            hiddenword.add('_');
        }
        return hiddenword;
    }

    public static boolean checkIfWordContainsLetter() {
        if (word.contains(currentChosenLetter)) {
            AsciiHangman.drawAscii();
            return true;
        }
        lives--;
        AsciiHangman.drawAscii();
        System.out.println("Wrong u lost a life");
        return false;
    }

    public static ArrayList replaceHiddenWithGuessedLetter() {
        for (int i = 0; i < word.size(); i++) {
            if (word.get(i) == currentChosenLetter) {
                hiddenword.set(i, currentChosenLetter);
            }
        }
        return hiddenword;
    }

    public static void printHiddenWord() {
        System.out.println(hiddenword);
    }

    public static char userChosenLetter() {
        System.out.println("Type in a letter");
        currentChosenLetter = scaninput.nextLine().toUpperCase().charAt(0);
        return currentChosenLetter;
    }

    public static ArrayList addToListOfGuessedLetters() {
        guessedLetters.add(currentChosenLetter);
        return guessedLetters;
    }

    public static void printListOfGuessedLetters() {
        System.out.println("You have guessed: " + guessedLetters);
    }

    public static void checkForCompletion() {
        if (hiddenword.equals(word)) {
            AsciiHangman.drawAscii();
            System.out.println("Congratulations!\nYou've guessed the word");
            System.out.println(hiddenword);
            System.exit(0);
        }
    }

}
