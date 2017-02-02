
package pavelgarmuyev.fastreader.applogic;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class WordSequencer {
    
    private List<String> wordList;
    private int speed;
    
    public WordSequencer(String text, int speed) {
        wordList = new LinkedList<>();

        Collections.addAll(wordList, text.split(" "));
    }
    
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    
    public int getSpeed() {
        return speed;
    }
    
    public List<String> getWords() {
        return wordList;
    }
}