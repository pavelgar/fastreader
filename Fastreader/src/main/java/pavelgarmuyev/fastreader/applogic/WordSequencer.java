
package pavelgarmuyev.fastreader.applogic;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pavelgarmuyev
 */
public class WordSequencer {
    
    private List<String> wordList;
    private int speed;
    
    public WordSequencer(String list, int speed) {
        wordList = new LinkedList<>();
        for (String s : list.split(" ")) {
            wordList.add(s);
        }
        this.speed = speed;
    }
    
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    
    public int getSpeed() {
        return speed;
    }
    
    public void readWords() {
        
        for (String s : wordList) {
            System.out.println(s);
            try {
                Thread.sleep(60/speed*1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(WordSequencer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
