/*
/*
Maman 11 - Question 1 - "Guess word Game"
Student: Alexey Vartanov - 321641086
 */

import java.util.Random;

/**
 * Dictionary of ten words in string format
 */
public class Dictionary {

    static String[] list = {"randomization", "minute", "accord", "evident", "practice", "intend", "concern", "issue",
            "policy", "stock"};

    /**
     * Throw random word form the initialized list
     * @return String format word
     */
    public static String GetRandom() {
        Random rand = new Random();
        int index = rand.nextInt(list.length);
        return list[index];
    }
}