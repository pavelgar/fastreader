
package pavelgarmuyev.fastreader.applogic;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class WordSequencer {
    private List<String> list;
    private int speed, index;
    private boolean running;
    private StatisticsRecorder stats;
    private FileOpener fileOpener;

    /**
     * Luo uuden WordSequencer -olion, joka navigoi tiedoston sanoissa ja lauseissa eteen- ja taaksepäin.
     * @param statisticsPath    Polku tiedostolle, johon tallenetaan statistiikkaa.
     */
    public WordSequencer(String statisticsPath) {
        stats = new StatisticsRecorder(statisticsPath);
        fileOpener = new FileOpener();
        list = Arrays.asList("Fastreader", "on", "ohjelma,", "joka", "auttaa", "sinua", "lukemaan", "nopeammin.", 
                "Kohdista", "katseesi", "rivin", "keskelle.", 
                "Mitä", "rennompi", "olet,", "sitä", "paremmin", "pystyt", "lukemaan.", 
                "Ruudun", "alalaidassa", "voit", "vaihtaa", "lukunopeutta.", 
                "Yläpalkissa", "olevasta", "Open", "File", "napista", "voit", "avata", "omia", "tiedostoja.", 
                "Statistics", "nappi", "avaa", "ikkunan,", "jossa", "näet", "kiinnostavaa", "tietoa", "lukutottumuksistasi.", 
                "Tiedot", "tallenetaan,", "jotta", "voit", "nähdä", "saavutuksesi", "ohjelman", "parissa.", 
                "Mukavia", "lukuhetkiä!");
        index = 0;
        speed = stats.getPreferredSpeed();
        running = false;
    }
    
    public void setList(List<String> list) {
        this.list = list;
    }
    public boolean isRunning() {
        return running;
    }
    public void setRunning(boolean running) {
        this.running = running;
    }
    /**
     * Kutsuu statistiikkaluokan metodia <code>incrementPausesMade()</code>, joka korottaa taukojen määrää yhdellä.
     */
    public void incrementPauses() {
        stats.incrementPausesMade();
    }
    public int getSpeed() {
        return speed;
    }
    /**
     * Nopeuden asettamisen lisäksi tämä metodi kutsuu statisiikkaluokan metodia <code>setPreferredSpeed()</code>, joka tallentaa valitun nopeuden.
     * @param speed 
     */
    public void setSpeed(int speed) {
        this.speed = speed;
        stats.setPreferredSpeed(speed);
    }
    public int getIndex() {
        return index;
    }
    /**
     * Asettaa nykyisen sanan indeksin.
     * Jos annettu parametri on alle 0, asettaa metodi indeksin nollakis.
     * Jos annettu parametri on yli korkeimman indeksin, asettaa metodi indeksin korkeimmaksi mahdolliseksi.
     * @param index     Asetettava indeksi.
     */
    public void setIndex(int index) {
        if (index <= 0) {
            this.index = 0;
        } else if (index >= list.size() - 1) {
            this.index = list.size() - 1;
            setRunning(false);
        } else {
            this.index = index;
        }
    }
    /**
     * Asettaa indeksin yhden eteenpäin ja palauttaa uuden indeksin kohdalla olevan sanan.
     * @return      Palauttaa seuraavan sanan.
     */
    public String nextWord() {
        if (list.isEmpty()) {
            return null;
        }
        setIndex(index + 1);
        stats.incrementWordsRead();
        return list.get(index);
    }
    /**
     * Asettaa indeksin yhden taaksepäin ja palauttaa uuden indeksin kohdalla olevan sanan.
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
     * @return     Seuraavan lauseen ensimmäinen sana.
     */
    public String nextSentenceBeginning() {
        String word = list.get(index);
        char c = word.charAt(word.length() - 1);
        while (c != '.' && c != '!' && c != '?') {
            setIndex(index + 1);
            word = list.get(index);
            c = word.charAt(word.length() - 1);
        }
        setIndex(index + 1);
        return list.get(index);
    }
    /**
     * Laskee indeksiä yhdellä, kunnes edellinen sana loppuu lopetusmerkkiin: . ! ?.
     * Palauttaa tämän jälkeen seuraavan sanan.
     * @return      Nykyisen lauseen ensimmäinen sana.
     */
    public String currentSentenceBeginning() {
        if (index == 0) {
            return list.get(index);
        }
        setIndex(index - 1);
        String word = list.get(index);
        char c = word.charAt(word.length() - 1);
        boolean userWantsPrevSentence = (c == '.') || (c == '!') || (c == '?');

        if (userWantsPrevSentence) {
            setIndex(index - 1);
            word = list.get(index);
            c = word.charAt(word.length() - 1);
        }
        while (c != '.' && c != '!' && c != '?') {
            if (index == 0) {
                return list.get(index);
            }
            setIndex(index - 1);
            word = list.get(index);
            c = word.charAt(word.length() - 1);
        }
        setIndex(index + 1);
        return list.get(index);
    }
    /**
     * Asettaa indeksin 0 ja palauttaa indeksin kohdalla olevan sanan, joka on tekstin alku.
     * @return      Tekstin ensimmäinen sana.
     */
    public String toBeginningOfText() {
        index = 0;
        return list.get(index);
    }
    /**
     * Avaa tiedoston annetussa polussa.
     * @param path  Avattavan tiedoston polku.
     * @return      Palauttaa <code>true</code>, jos tiedosto on olemassa ja ei ole tyhjä. Muuten palauttaa <code>false</code>.
     */
    public boolean openPath(String path) {
        List<String> newList = fileOpener.openFile(path);
        if (newList == null) {
            return false;
        }
        if (!newList.isEmpty()) {
            list = newList;
            return true;
        }
        return false;
    }
    /**
     * Muokkaa HashMappiin tallenetut statistiikat luettavampaan muotoon;
     * Isot alkukirjaimet,
     * alaviivat välilyönneiksi,
     * kaksoispiste ja välilyönti jakajana.
     * Lisää perään muuttujan arvon ja rivinvaihdon, jotta kaikki muuttujat menisivät yhteen String olioon.
     * @return  String olio, joka kuvaa nykyisten statistiikkaparametrien arvoja.
     */
    public String getStatistics() {
        Map<String, String> statsMap = stats.getStatistics();
        StringBuilder sb = new StringBuilder();
        for (String key : statsMap.keySet()) {
            char[] keyChars = key.toCharArray();
            for (int i = 0; i < keyChars.length; i++) {
                if (i == 0) {
                    sb.append((char) (keyChars[i] - 32));
                } else if (keyChars[i] == '_') {
                    sb.append(' ');
                } else {
                    sb.append(keyChars[i]);
                }
            }
            sb.append(": ").append(statsMap.get(key)).append("\n");
        }
        return sb.toString();
    }
}