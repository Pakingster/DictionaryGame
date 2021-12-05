/*
Maman 11 - Question 1 - "Guess word Game"
Student: Alexey Vartanov - 321641086
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Character.isLetter;

public class Game {

    private static final List<Character> usedCharacter = new ArrayList<>(); //list for used letters
    private static final Word word = new Word();                            //word to guess
    private static int numberOfTries = 0;                                   //number of tries to guess
    private static boolean gameOver;                                        //game status flag

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); //use same scanner for all scans in current class
        System.out.println("Welcome to the game, we will choose random word for you,\nyou will need to " +
                "guess the word by choosing letters from alphabet.\nYou can leave the game any time by type \"exit\"");
        gameOver = !IfWantPlay(sc);
        if(!gameOver) {
            PrepareNewGame();
            RunGame(sc);
        }
    }

    /**
     * Start the game
     * @param sc: scanner for input
     */
    private static void RunGame(Scanner sc){
        while (!gameOver){
            PrintWordStatus();
            System.out.println("Please enter letter:");
            String line = sc.nextLine();
            if(line.length()==1){
                char letter = line.charAt(0);
                if(VerifyAllowedCharacter(letter) )
                {
                    FindCharInWord(letter);
                }
                if(gameOver){
                    if(IfWantPlay(sc)) {
                        gameOver = false;
                        PrepareNewGame();
                    }
                }
            }
            else if(line.equals("exit")){
                System.out.println("Thanks, see you next time!");
                gameOver = true;
            }
            else {
                System.out.println("Only one character can be entered!");
            }
        }
    }

    /**
     * Ask the user if he interested to play play
     * @param sc: scanner to get input
     * @return true if user wants to play
     */
    private static boolean IfWantPlay(Scanner sc){
        System.out.println("\nIf want to start enter \"start\" or \"exit\" to exit");
        String line;
        do{
            line = sc.nextLine();
            if(line.equalsIgnoreCase("start")){
                return true;
            }
            else if(line.equalsIgnoreCase("exit")){
                System.out.println("See you next time!");
                return false;
            }
            else{
                System.out.println("Command not recognized, try \"start\" or \"exit\"");
            }
        }while (!line.equals("start"));
        return false;
    }

    /**
     * Print word Mask with already opened letters is exists,
     * Print possible letter to use in current turn
     */
    private static void PrintWordStatus(){
        System.out.printf("Your world is: \"%s\"\n",word.getWordMask());
        System.out.println("Possible letters to use: ");
        for (char i='a'; i<='z'; i++){
            if(!usedCharacter.contains(i))
                System.out.printf("%c ", i);
        }
        System.out.println();
    }

    /**
     * Prepare new game:
     * Clear used letter list, set number of tries to zero and
     * pick new word from dictionary
     */
    private static void PrepareNewGame(){
        if(!usedCharacter.isEmpty())
        {
            usedCharacter.clear();
        }
        word.setWord(Dictionary.GetRandom());
        numberOfTries = 0;
    }

    /**
     * Search for the letter in the word
     * if found open and add to used, else add to used and return print line
     * Param: ch - entered character
     */
    private static void FindCharInWord(char ch) {
        String openWord = word.getWord();
        if(word.getWord().indexOf(ch)!=-1){ //if char not in string -1 returned
            for (int i = 0; i< openWord.length(); i++)
            {
                if(ch == word.getWord().charAt(i)) {
                    word.updateWordMask(i, ch);
                }
            }
            System.out.println("You doing well!");
            if((word.IfOpened())){
                System.out.printf("Congratulation! You guessed the word: \"%s\" after  %d tries.\n", word.getWord(), numberOfTries);
                gameOver = true;
            }
        }
        else{
            System.out.println("Letter was not found, try again");
        }
    }

    /**
    Validate entered character, if not letter or already used, print message
    Else, count tries and add letter to the used list
    return: true if pass
    */
    private static boolean VerifyAllowedCharacter(char letter) {
        boolean result = false;
        if(!isLetter(letter)){
            System.out.println("Prohibited character entered, only alphabet allowed");
        }
        else if(usedCharacter.contains(letter)) {
            System.out.println("This letter already used, try again");
        }
        else {
            numberOfTries++;
            usedCharacter.add(letter);
            result = true;
        }
        return result;
    }
}
