
package pavelgarmuyev.fastreader.applogic;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pavelgarmuyev
 */
public class UserInterface {
    
    private Scanner scanner;
    
    public UserInterface(Scanner scanner) {
        
        this.scanner = scanner;
    }
    
    public void init() {
        while (true) {
            String input = askText();

            if (input.equals(".exit")) {
                System.out.println("Goodbye!");
                break;
            }
            int speed = askSpeed();
            
            WordSequencer ws = new WordSequencer(input, speed);
            
            speed = ws.getSpeed();
            for (String s : ws.getWords()) {
                try {
                    Thread.sleep(60 / speed * 1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(WordSequencer.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println(s);
            }
        }
    }
    
    public String askText() {
        System.out.println("Copy plain text or type .exit to exit");
        System.out.print("> ");
        String input = scanner.nextLine();
        
        while (input.isEmpty()) {
            System.out.println("Empty input not valid. Try again.");
            System.out.print("> ");
            input = scanner.nextLine();
        }
        return input;
    }
    
    public int askSpeed() {
        System.out.println("Choose speed: ");
        System.out.println("> ");
        int speed = -1;
        while (speed < 0) {
            try {
                speed = Integer.parseInt(scanner.nextLine());
                if (speed < 0) {
                    System.out.println("Invalid speed, try again.");
                    System.out.print("> ");
                }
            } catch (Exception e) {
                System.out.println("Invalid speed, try again.");
                System.out.print("> ");
            }
        }
        return speed;
    }
}
