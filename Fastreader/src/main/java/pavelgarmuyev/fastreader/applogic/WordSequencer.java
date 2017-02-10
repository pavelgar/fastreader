
package pavelgarmuyev.fastreader.applogic;

import java.util.List;
import java.util.Scanner;

public class WordSequencer {
    private List<String> list;
    private int speed, index;

    public WordSequencer(List<String> list) {
        this.list = list;
        index = -1;
        speed = 100;
    }
    
    public void setSpeed(int speed) {

        this.speed = speed;
    }
    
    public int getSpeed() {

        return speed;
    }
    
    public String nextWord() {
        if (list.isEmpty() || index >= list.size()) {
            return "";
        }

        index++;
        return list.get(index);
    }

    public String prevWord() {
        if (list.isEmpty()) {
            return "";
        }

        if (index <= 0) {
            return list.get(0);
        }

        index--;
        return list.get(index);
    }

    public String nextSentenceBeginning() {
        String word = list.get(index);
        char c = word.charAt(word.length() - 1);
        
        while (c != '.' && c != '!' && c != '?') {
            word = nextWord();
            c = word.charAt(word.length() - 1);
        }
        return nextWord();
    }

    public String currentSentenceBeginning() {
        String word = prevWord();
        char c = word.charAt(word.length() - 1);

        while (c != '.' && c != '!' && c != '?') {
            word = prevWord();
            c = word.charAt(word.length() - 1);
        }
        return nextWord();
    }
}