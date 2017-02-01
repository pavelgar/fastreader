
package pavelgarmuyev.fastreader;

import java.util.Scanner;
import pavelgarmuyev.fastreader.applogic.UserInterface;

/**
 *
 * @author pavelgarmuyev
 */

public class Main {
    
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        UserInterface ui = new UserInterface(scanner);
        ui.init();
    }
}
