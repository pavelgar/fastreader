
package pavelgarmuyev.fastreader.applogic;

import java.util.Scanner;

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
            System.out.println("Copy plain text or type .exit to exit");
            System.out.print("> ");
            String input = scanner.nextLine();
            
            if (input.equals(".exit")) {
                System.out.println("Goodbye!");
                break;
            }
            System.out.print("Choose speed (100-500, increments of 50): ");
            int speed = 1;
            
            while (true) {
                try {
                    speed = Integer.parseInt(scanner.nextLine());
                    if (speed % 50 == 0) {
                        break;
                    }
                    System.out.print("Only increments of 50: ");
                } catch (Exception e) {
                    System.out.print("Only numbers allowed (100-500): ");
                }
            }
            
            WordSequencer ws = new WordSequencer(input, speed);
            ws.readWords();
            
        }
    }
}
