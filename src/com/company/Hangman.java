package com.company;

import static com.company.Engine.*;

public class Hangman {

    public static void main(String[] args) {


        Engine.startMessage();
        Engine.buildWordArray();
        Engine.getWord();
        AsciiHangman.drawAscii();
        Engine.createHiddenWord();
        while (lives > 0) {
            Engine.printHiddenWord();
            Engine.userChosenLetter();
            Engine.checkIfWordContainsLetter();
            Engine.replaceHiddenWithGuessedLetter();
            Engine.addToListOfGuessedLetters();
            Engine.printListOfGuessedLetters();
            Engine.checkForCompletion();
        }
        System.out.println("Dude is now hanging, u lost *sadface* \n The word was:" + word);
    }
}
