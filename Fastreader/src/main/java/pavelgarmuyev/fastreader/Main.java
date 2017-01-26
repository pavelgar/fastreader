
package pavelgarmuyev.fastreader;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author pavelgarmuyev
 */

public class Main {
    
    public static void main(String[] args) throws IOException {
        
        Scanner scanner = new Scanner(System.in);
        System.out.println("Copy plain text or give url");
        String input = scanner.nextLine();
        
        List words = new LinkedList<>();
        
        words.addAll(Arrays.asList(input.split(" ")));
        System.out.println("When ready, press enter (type exit to terminate)");
        String command = scanner.nextLine();
        
        if (!command.equals("exit")) {
            words.forEach(System.out::println);
        }
        File file = new File("log.txt");
        file.createNewFile();
    }
}
