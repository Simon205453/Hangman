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
    static int lives = 6;
    static char currentChosenLetter;
    static int chosenDifficulty;
    static File Hangman = new File("Resources/Hangman.csv");
    static Scanner scaninput = new Scanner(System.in);
    static Scanner scannerHangman;
    static int countOfEasyWords;
    static int amountOfNormalWords;
    static int amountOfHardWords;

    static {
        try {
            scannerHangman = new Scanner(Hangman);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void startMessage() {
        System.out.println("Welcome to Hangman");
    }

    public static int amountOfWordsToChose() {
        //lave en arraylist for alle ord i en difficulty, ændr getword method til rand index in array (.get(rand))
        // på den måde vil du kunne tilføje flere rod til listen uden det påvirker programmets flow, da ordene er fordelt mellem ---difficulty---
        // på den måde slipper du også for magicnumbers in bounds værdierne og en pænere opsætning

        while(scannerHangman.hasNextLine()) {
            if (scannerHangman.nextLine().equals("---Easy---")) {
                while(!scannerHangman.nextLine().equals("---Normal---")){
                    scannerHangman.nextLine();
                    countOfEasyWords++;
                }

                while(!scannerHangman.nextLine().equals("---Hard---")){
                    scannerHangman.nextLine();
                    amountOfNormalWords++;
                }

                while(scannerHangman.hasNextLine()){
                    scannerHangman.nextLine();
                    amountOfHardWords++;
                }
            }
        }
        return countOfEasyWords + amountOfNormalWords + amountOfHardWords;
    }

    public static int chooseDifficulty() {
        System.out.println("Choose difficulty\nType:\n 1 - Easy\n 2 - Normal\n 3 - Hard");
        chosenDifficulty = scaninput.nextInt();
        scaninput.nextLine();
        return chosenDifficulty;
    }
// +1 pga ---easy--- +2 pga ---normal---
    public static ArrayList getWord() {
        int wordNum = 0;
        Random random = new Random();
        if (chosenDifficulty == 1) {
            wordNum = random.nextInt(countOfEasyWords) + 2;
        } else if (chosenDifficulty == 2) {
            wordNum = random.nextInt(countOfEasyWords+amountOfNormalWords+2) + 4;
        } else if (chosenDifficulty == 3) {
            wordNum = random.nextInt(countOfEasyWords + amountOfNormalWords + amountOfHardWords+3) + 19;
        }
        for (int i = 0; i < wordNum; i++) {
            scannerHangman.nextLine();
        }
        String wordToGuess = scannerHangman.nextLine().toUpperCase();

        for (int i = 0; i < wordToGuess.length(); i++) {
            word.add(wordToGuess.charAt(i));
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
        currentChosenLetter  = scaninput.nextLine().toUpperCase().charAt(0);
        return currentChosenLetter;
    }

    public static ArrayList addToListOfGuessedLetters(){
        guessedLetters.add(currentChosenLetter);
        return guessedLetters;
    }
    public static void printListOfGuessedLetters(){
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
