
package pavelgarmuyev.fastreader.applogic;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author pavelgarmuyev
 */
public class WordSequencer {
    
    private List<String> wordList;
    private int speed;
    
    public WordSequencer(String text, int speed) {
        wordList = new LinkedList<>();
        
        for (String s : text.split(" ")) {
            wordList.add(s);
        }
        if (speed < 100) {
            this.speed = 100;
        } else if (speed > 500) {
            this.speed = 500;
        } else {
            this.speed = speed;
        }
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