package pavelgarmuyev.fastreader.applogic;

import java.util.List;

public class Commands {

    private WordSequencer ws;

    public Commands(WordSequencer currentWS) {

        ws = currentWS;
    }

    public void toTextBeginning() {

        ws.setPos(0);
    }

    public void toNextSentence() {
        int pos = ws.getPos();
        List<Integer> dotList = ws.getdotList();

        for (int i = dotList.size() - 1; i >= 0; i--) {
            if (pos >= dotList.get(i)) {
                ws.setPos(dotList.get(i) + 1);
            }
        }
    }

    public void toBeginningOfCurrentSentence() {

        int pos = ws.getPos();
        List<Integer> dotList = ws.getdotList();

        for (int i = dotList.size() - 1; i >= 0; i--) {
            if (pos >= dotList.get(i)) {
                ws.setPos(dotList.get(i - 1) + 1);
                return;
            }
        }
    }

    public int getTotalWords() {
        return ws.getTotalWords();
    }
}
