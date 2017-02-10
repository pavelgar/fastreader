
package pavelgarmuyev.fastreader.applogic;

import java.util.List;

public class WordSequencer {
    private List<String> list;
    private int speed, index;

    public WordSequencer(List<String> list) {
        this.list = list;
        index = 0;
        speed = 100;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getIndex() {
        return index;
    }

    /**
     * Asettaa nykyisen sanan indeksin.
     * Jos annettu parametri on alle 0, asettaa metodi indeksin nollakis.
     * Jos annettu parametri on yli korkeimman indeksin, asettaa metodi indeksin korkeimmaksi mahdolliseksi.
     *
     * @param index     Asetettava indeksi.
     */
    public void setIndex(int index) {
        if (index < 0) {
            this.index = 0;
        } else if (index > list.size() - 1) {
            this.index = list.size() - 1;
        } else {
            this.index = index;
        }
    }

    /**
     * Palautaa sanojen maaran nykyisessä WordSequencerissä.
     *
     * @return      Palauttaa sanojen määrän.
     */
    public int totalWords() {
        return list.size();
    }

    /**
     * Asettaa indeksin yhden eteenpäin ja palauttaa uuden indeksin kohdalla olevan sanan.
     *
     * @return      Palauttaa seuraavan sanan.
     */
    public String nextWord() {
        if (list.isEmpty()) {
            return null;
        }

        setIndex(index + 1);
        return list.get(index);
    }

    /**
     * Asettaa indeksin yhden taaksepäin ja palauttaa uuden indeksin kohdalla olevan sanan.
     *
     * @return      Palauttaa edellisen sanan.
     */
    public String prevWord() {
        if (list.isEmpty()) {
            return null;
        }

        setIndex(index - 1);
        return list.get(index);
    }

    /**
     * Nostaa indeksiä yhdellä, kunnes seuraava sana loppuu lopetusmerkkiin: . ! ?.
     * Palauttaa tämän jälkeen seuraavan sanan, joka on seuraavan lauseen alku.
     *
     * @return     Seuraavan lauseen ensimmäinen sana.
     */
    public String nextSentenceBeginning() {
        String word = list.get(index);
        char c = word.charAt(word.length() - 1);
        
        while (c != '.' && c != '!' && c != '?') {
            word = nextWord();
            c = word.charAt(word.length() - 1);
        }
        return nextWord();
    }

    /**
     * Laskee indeksiä yhdellä, kunnes edellinen sana loppuu lopetusmerkkiin: . ! ?.
     * Palauttaa tämän jälkeen seuraavan sanan, joka on metodin alussa olevan lauseen ensimmäinen sana.
     *
     * @return      Nykyisen lauseen ensimmäinen sana.
     */
    public String currentSentenceBeginning() {
        String word = prevWord();
        char c = word.charAt(word.length() - 1);

        while (c != '.' && c != '!' && c != '?') {
            word = prevWord();
            c = word.charAt(word.length() - 1);
        }
        return nextWord();
    }

    /**
     * Asettaa indeksin 0 ja palauttaa indeksin kohdalla olevan sanan, joka on tekstin alku.
     * @return      Tekstin ensimmäinen sana.
     */
    public String toBeginningOfText() {
        index = 0;
        return list.get(index);
    }
}