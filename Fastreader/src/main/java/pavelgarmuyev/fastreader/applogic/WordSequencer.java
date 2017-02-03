
package pavelgarmuyev.fastreader.applogic;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class WordSequencer {
    
    private List<String> wordList;
    private List<Integer> dotList;
    private int speed, pos;
    
    public WordSequencer(String text) {
        wordList = new ArrayList<>();
        dotList = new LinkedList<>();
        int index = 0;

        for (String s : text.split(" ")) {
            wordList.add(s);
            char c = s.charAt(s.length() - 1);
            if (c == '.' || c == '?' || c == '!') {
                dotList.add(index);
            }
            index++;
        }
        speed = 100;
        pos = 0;
    }

    public WordSequencer(List<String> wordList) {
        this.wordList = wordList;
        dotList = new LinkedList<>();
        int index = 0;

        for (String s : wordList) {
            char c = s.charAt(s.length() - 1);
            if (c == '.' || c == '?' || c == '!') {
                dotList.add(index);
            }
            index++;
        }
        speed = 100;
        pos = 0;
    }
    
    public void setSpeed(int speed) {

        this.speed = speed;
    }
    
    public int getSpeed() {

        return speed;
    }
    
    public String nextWord() {
        if (pos + 1 >= wordList.size()) {
            return null;
        }
        String s = wordList.get(pos);
        pos++;

        return s;
    }

    public List<String> getWords() {
        return wordList;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        if (pos >= 0) {
            this.pos = pos;
        }
    }

    public List<Integer> getdotList() {
        return dotList;
    }

    public int getTotalWords() {
        return wordList.size();
    }
}