/*
Maman 11 - Question 1 - "Guess word Game"
Student: Alexey Vartanov - 321641086
 */

/**
 * Word object, contains regular string representation of word
 * and word mask - represented by "_" character according letter number in the string word
 */
public class Word {

    private String word;
    private String wordMask;

    /**
     * Prepare mask according to created word
     */
    private void setWordMask(){
        wordMask = "";
        for (int i = 0; i < word.length(); i++) {
            wordMask += '_';
        }
    }

    /**
     * Gettter for mask
     * @return string in "____" format
     */
    public String getWordMask()
    {
        return this.wordMask;
    }

    /**
     * set word
     * @param word string format of regular word
     */
    public void setWord(String word){
        this.word = word;
        setWordMask();
    }

    /**
     * Getter fot word string
     * @return word
     */
    public String getWord(){
        return this.word;
    }

    /**
     * Verify if mask still contains closed letters
     * @return true if all letters were opened
     */
    public boolean IfOpened()
    {
        return !(wordMask.contains("_"));
    }

    /**
     * Update character in mask with provided letter
     * @param index: index of character to open
     * @param ch: letter to put on the index
     */
    public void updateWordMask(int index, char ch){
        wordMask = wordMask.substring(0, index)+ ch + wordMask.substring(index + 1);
    }
}